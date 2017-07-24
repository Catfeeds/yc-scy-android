package com.chengdai.eatproject.model.user.activitys;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.chengdai.eatproject.R;
import com.chengdai.eatproject.base.AbsBaseActivity;
import com.chengdai.eatproject.databinding.ActivityAddressBinding;
import com.chengdai.eatproject.model.common.model.EventBusModel;
import com.chengdai.eatproject.model.user.model.MyAddressListModel;
import com.chengdai.eatproject.model.user.model.UserInfo;
import com.chengdai.eatproject.uitls.StringUtils;
import com.chengdai.eatproject.uitls.nets.RetrofitUtils;
import com.chengdai.eatproject.uitls.nets.RxTransformerListHelper;
import com.chengdai.eatproject.widget.appmanager.EventTags;
import com.chengdai.eatproject.widget.appmanager.SPUtilHelpr;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

/**收货地址
 * Created by 李先俊 on 2017/7/18.
 */

public class AddressInfoActivity extends AbsBaseActivity{

    private ActivityAddressBinding mBinding;

    private MyAddressListModel mAddressData;

    /**
     * 打开当前页面
     * @param context
     */
    public static void open(Context context){
        if(context==null){
            return;
        }
        Intent intent= new Intent(context,AddressInfoActivity.class);
        context.startActivity(intent);
    }



    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_address, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        setTopTitle(getString(R.string.txt_address));
        setSubLeftImgState(true);


        mBinding.layoutAddress.setOnClickListener(v -> {
            UpdateAddressActivity.open(this,mAddressData);
        });

        getAddressInfoRequest(this);
    }

    public void getAddressInfoRequest(Context c){
        Map<String,String> map=new HashMap<>();
        map.put("userId", SPUtilHelpr.getUserId());
        map.put("token",SPUtilHelpr.getUserToken());

        mSubscription.add(RetrofitUtils.getLoaderServer().getAddress("805165", StringUtils.getJsonToString(map))
                .compose(RxTransformerListHelper.applySchedulerResult(c))

                .filter(myAddressListModels-> myAddressListModels!=null && myAddressListModels!=null && myAddressListModels.size()>0)

                .map(myAddressListModels -> myAddressListModels.get(0))

                .subscribe(myAddress -> {
                  mAddressData=myAddress;
                  mBinding.setMAddress(myAddress);

                },Throwable::printStackTrace));
    }

    @Subscribe
    public void updateDataEvent(EventBusModel e){
        if(e==null){
            return;
        }

        if(TextUtils.equals(e.getTag(), EventTags.AddressChangefresh)){  //修改地址成功
            getAddressInfoRequest(null);
        }
    }

}
