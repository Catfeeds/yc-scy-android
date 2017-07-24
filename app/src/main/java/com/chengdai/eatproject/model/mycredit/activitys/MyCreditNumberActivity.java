package com.chengdai.eatproject.model.mycredit.activitys;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.chengdai.eatproject.R;
import com.chengdai.eatproject.base.AbsBaseActivity;
import com.chengdai.eatproject.databinding.ActivityReportBinding;
import com.chengdai.eatproject.model.common.model.EventBusModel;
import com.chengdai.eatproject.model.user.model.CreditAmountModel;
import com.chengdai.eatproject.uitls.BigDecimalUtils;
import com.chengdai.eatproject.uitls.StringUtils;
import com.chengdai.eatproject.uitls.nets.RetrofitUtils;
import com.chengdai.eatproject.uitls.nets.RxTransformerListHelper;
import com.chengdai.eatproject.widget.appmanager.EventTags;
import com.chengdai.eatproject.widget.appmanager.SPUtilHelpr;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

/**我的信用积分显示
 * Created by 李先俊 on 2017/7/18.
 */

public class MyCreditNumberActivity extends AbsBaseActivity{

    private ActivityReportBinding mBinding;

    private CreditAmountModel mData;

    /**
     * 打开当前页面
     * @param context
     */
    public static void open(Context context){
        if(context==null){
            return;
        }
        Intent intent= new Intent(context,MyCreditNumberActivity.class);
        context.startActivity(intent);
    }


    @Override
    public View addMainView() {
        mBinding  = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_report, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        setSubLeftImgState(true);

        setTopTitle("我要归账");

        mBinding.layoutBill.setOnClickListener(v -> {
            if( mData== null) return;
            BillListAcitivity.open(this,mData.getAccountNumber());
        });

        mBinding.btnBillretrun.setOnClickListener(v -> {
            if( mData== null) return;

            BillRetrunActivity.open(this,StringUtils.showCredit( BigDecimalUtils.subtract(mData.getInAmount(),mData.getOutAmount())));
        });
        getInfoData();
    }


    public void getInfoData(){

        Map<String,String> map=new HashMap<>();
        map.put("userId", SPUtilHelpr.getUserId());
        map.put("currency","CB");
        map.put("token",SPUtilHelpr.getUserToken());

       mSubscription.add( RetrofitUtils.getLoaderServer().getXngyong("802503", StringUtils.getJsonToString(map))
                .compose(RxTransformerListHelper.applySchedulerResult(this))
               .filter(data-> data!=null && data.size()>0)
               .map(data->data.get(0))
                .subscribe(dataModel -> {
                    mData=dataModel;
                    mBinding.tvAmount.setText(StringUtils.showCredit(dataModel.getAmount()));
                    mBinding.txtTotal.setText(StringUtils.showCredit(dataModel.getOutAmount()));
                    mBinding.txtUsed.setText(StringUtils.showCredit(dataModel.getInAmount().subtract(dataModel.getOutAmount())));

                },Throwable::printStackTrace));
    }

    @Subscribe
    public void updateDataEvent(EventBusModel e){
        if(e==null){
            return;
        }

        if(TextUtils.equals(e.getTag(), EventTags.BillRetrunChangefresh)){  //归账本成功
            getInfoData();
        }
    }



}
