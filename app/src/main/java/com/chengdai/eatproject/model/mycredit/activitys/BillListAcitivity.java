package com.chengdai.eatproject.model.mycredit.activitys;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chengdai.eatproject.R;
import com.chengdai.eatproject.base.AbsBaseActivity;
import com.chengdai.eatproject.base.BaseRefreshActivity;
import com.chengdai.eatproject.databinding.LayoutCommonRecyclerRefreshBinding;
import com.chengdai.eatproject.model.mycredit.adapters.MyBillListAdapter;
import com.chengdai.eatproject.model.user.model.BillModel;
import com.chengdai.eatproject.uitls.StringUtils;
import com.chengdai.eatproject.uitls.nets.RetrofitUtils;
import com.chengdai.eatproject.uitls.nets.RxTransformerHelper;
import com.chengdai.eatproject.widget.appmanager.SPUtilHelpr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**我的账单流水
 * Created by 李先俊 on 2017/7/18.
 */

public class BillListAcitivity extends BaseRefreshActivity<BillModel>{

    private String mCode;

    private MyBillListAdapter myBillListAdapter;

    /**
     * 打开当前页面
     * @param context
     */
    public static void open(Context context,String code){
        if(context==null){
            return;
        }
        Intent intent= new Intent(context,BillListAcitivity.class);
        intent.putExtra("code",code);
        context.startActivity(intent);
    }


    @Override
    protected void onFirstLoad(Bundle savedInstanceState, int pageIndex, int limit) {
        if(getIntent()!=null){
            mCode=getIntent().getStringExtra("code");
        }

        setSubLeftImgState(true);

        setTopTitle("账单");
        getInfoData(this,pageIndex,limit);
    }


    @Override
    protected void onMRefresh(int pageIndex,int limit) {
        getInfoData(null,pageIndex,limit);
    }

    @Override
    protected void onMLoadMore(int pageIndex,int limit) {
        getInfoData(null,pageIndex,limit);
    }

    @Override
    protected BaseQuickAdapter onCreateAdapter(List<BillModel> mDataList) {
        myBillListAdapter = new MyBillListAdapter(mDataList);
        return myBillListAdapter;
    }

    @Override
    public String getEmptyInfo() {
        return "暂无账单流水";
    }


    public void getInfoData(Context context,int page,int limit){
        Map<String,String> map=new HashMap<>();
        map.put("accountNumber",mCode);
        map.put("token",SPUtilHelpr.getUserToken());
        map.put("start", page+"");
        map.put("limit", limit+"");


        mSubscription.add( RetrofitUtils.getLoaderServer().getBillList("802524", StringUtils.getJsonToString(map))
                .compose(RxTransformerHelper.applySchedulerResult(context))
                .filter(data-> data!=null)
                .subscribe(dataModel -> {
                 setData(dataModel.getList());
                },throwable -> {
                    loadError();
                }));

    }

}
