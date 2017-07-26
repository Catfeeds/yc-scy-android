package com.chengdai.eatproject.model.mycredit.activitys;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.chengdai.eatproject.R;
import com.chengdai.eatproject.base.AbsBaseActivity;
import com.chengdai.eatproject.databinding.ActivityMyCreditBinding;
import com.chengdai.eatproject.model.common.model.EventBusModel;
import com.chengdai.eatproject.model.user.model.CreditAmountModel;
import com.chengdai.eatproject.uitls.BigDecimalUtils;
import com.chengdai.eatproject.uitls.StringUtils;
import com.chengdai.eatproject.uitls.nets.RetrofitUtils;
import com.chengdai.eatproject.uitls.nets.RxTransformerHelper;
import com.chengdai.eatproject.uitls.nets.RxTransformerListHelper;
import com.chengdai.eatproject.widget.appmanager.EventTags;
import com.chengdai.eatproject.widget.appmanager.SPUtilHelpr;

import org.greenrobot.eventbus.Subscribe;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**我的信用
 * Created by 李先俊 on 2017/7/18.
 */

public class MyCreditActivity extends AbsBaseActivity{

    private ActivityMyCreditBinding mBinding;

    private CreditAmountModel mData;

    /**
     * 打开当前页面
     * @param context
     */
    public static void open(Context context){
        if(context==null){
            return;
        }
        Intent intent= new Intent(context,MyCreditActivity.class);
        context.startActivity(intent);
    }


    @Override
    public View addMainView() {
        mBinding  = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_my_credit, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        setSubLeftImgState(true);

        setSubRightTitleAndClick("归账",v -> {
//            BillRetrunActivity.open(this,StringUtils.getShowPriceSign( BigDecimalUtils.subtract(mData.getInAmount(),mData.getOutAmount())));
            MyCreditNumberActivity.open(this);
        });

        setTopTitle("代销代发");

        StringUtils.editSetPriceInputState(mBinding.edtLimit);

        initListener();

        getCreditNumberInfoData();
    }

    private void initListener() {
        //查看账单流水
        mBinding.layoutBill.setOnClickListener(v -> {
            if( mData== null) return;
            BillListAcitivity.open(this,mData.getAccountNumber());
        });

        //确认发放
        mBinding.btnConfirm.setOnClickListener(v -> {
           if(TextUtils.isEmpty(mBinding.editPhone.getText().toString())){
               showToast("请输入手机号");
               return;
           }

          if(TextUtils.isEmpty(mBinding.edtLimit.getText().toString())){
               showToast("请输入发放额度");
               return;
           }

            if(new BigDecimal(mBinding.edtLimit.getText().toString()).doubleValue()<=0){
                showToast("发放额度必须大于0.01");
                return;
            }

            sureSendReuqest();
        });

    }

    /**
     * 获取信用值
     */
    public void getCreditNumberInfoData(){

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

                },Throwable::printStackTrace));
    }
    /**
     *确认发放
     */
     public void sureSendReuqest(){

        Map<String,String> map=new HashMap<>();
        map.put("fromUserId", SPUtilHelpr.getUserId());
        map.put("fromCurrency","CB");
        map.put("toCurrency","CB");
        map.put("mobile",mBinding.editPhone.getText().toString());
        map.put("amount",StringUtils.getRequestPrice(Double.valueOf(mBinding.edtLimit.getText().toString())));
        map.put("token",SPUtilHelpr.getUserToken());

       mSubscription.add( RetrofitUtils.getLoaderServer().sendCredit("802412", StringUtils.getJsonToString(map))
                .compose(RxTransformerHelper.applySchedulerResult(this))
               .filter(data-> data!=null && data.isSuccess())
                .subscribe(dataModel -> {
                    showToast("发放成功");
                    getCreditNumberInfoData();
                    mBinding.editPhone.setText("");
                    mBinding.edtLimit.setText("");
                },Throwable::printStackTrace));
    }





    @Subscribe
    public void updateDataEvent(EventBusModel e){
        if(e==null){
            return;
        }

        if(TextUtils.equals(e.getTag(), EventTags.BillRetrunChangefresh)){  //归账本成功
            getCreditNumberInfoData();
        }
    }



}
