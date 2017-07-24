package com.chengdai.eatproject.model.mycredit.activitys;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.chengdai.eatproject.R;
import com.chengdai.eatproject.base.AbsBaseActivity;
import com.chengdai.eatproject.databinding.ActivityBillReturnBinding;
import com.chengdai.eatproject.model.common.model.EventBusModel;
import com.chengdai.eatproject.model.user.activitys.PayPwdModifyActivity;
import com.chengdai.eatproject.uitls.StringUtils;
import com.chengdai.eatproject.uitls.nets.RetrofitUtils;
import com.chengdai.eatproject.uitls.nets.RxTransformerHelper;
import com.chengdai.eatproject.widget.appmanager.EventTags;
import com.chengdai.eatproject.widget.appmanager.MyConfig;
import com.chengdai.eatproject.widget.appmanager.SPUtilHelpr;

import org.greenrobot.eventbus.EventBus;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**归账
 * Created by 李先俊 on 2017/7/18.
 */

public class BillRetrunActivity extends AbsBaseActivity{

    private ActivityBillReturnBinding mBinding;

    private String mWaitRetrnNum;//待归账信用分

    private double mCvalue=0;//归账比例


    /**
     * 打开当前页面
     * @param context
     */
    public static void open(Context context,String WaitRetrnNum){
        if(context==null){
            return;
        }
        Intent intent= new Intent(context,BillRetrunActivity.class);
        intent.putExtra("WaitRetrnNum",WaitRetrnNum);
        context.startActivity(intent);
    }


    @Override
    public View addMainView() {
        mBinding  = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_bill_return, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        if(getIntent()!=null){
            mWaitRetrnNum=getIntent().getStringExtra("WaitRetrnNum");
        }
        mBinding.tvPrice.setText("归账金额: 0");
        getRetrunScale();

        setSubLeftImgState(true);

        setTopTitle("归账");

        mBinding.tvWaitRetrun.setText("待归账信用分: "+mWaitRetrnNum);

       StringUtils.editSetPriceInputState(mBinding.editPriceRetrun);

        mBinding.btnBillretrun.setOnClickListener(v -> {

            if(TextUtils.isEmpty(mBinding.editPriceRetrun.getText().toString())){
                showToast("请输入归账信用分");
                return;
            }

            if(new BigDecimal(mBinding.editPriceRetrun.getText().toString()).doubleValue()<=0){
                showToast("信用分必须大于0.01");
                return;
            }

            if(TextUtils.isEmpty(mBinding.editPwd.getText().toString())){
                showToast("请输入支付密码");
                return;
            }

            if(SPUtilHelpr.isSetpaypwd()){
                retrunRequest();
            }else{
                showDoubleWarnListen("您还未设置过支付密码，请先设置支付密码。",view -> {
                    PayPwdModifyActivity.open(this,false,SPUtilHelpr.getUserphone());
                });
            }

        });

        mBinding.editPriceRetrun.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               if (TextUtils.isEmpty(s.toString())){
                   mBinding.tvPrice.setText("归账金额: 0");
                   return;
               }
               if(mCvalue>0){
                   mBinding.tvPrice.setText("归账金额: "+StringUtils.doubleFormatMoney(Double.valueOf(s.toString())*mCvalue));
                   return;
               }
                mBinding.tvPrice.setText("归账金额: "+StringUtils.doubleFormatMoney(Double.valueOf(s.toString())));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        getCardInfo();
        getCallPhoneNumberInf();
    }

    /**
     * 归账请求
     */
    public void retrunRequest(){

        Map<String,String> map=new HashMap<>();
        map.put("userId", SPUtilHelpr.getUserId());
        map.put("cbAmount",StringUtils.getRequestPrice(Double.valueOf(mBinding.editPriceRetrun.getText().toString())));
        map.put("tradePwd",mBinding.editPwd.getText().toString());
        map.put("reamrk","归账");

       mSubscription.add( RetrofitUtils.getLoaderServer().billReturn("802433", StringUtils.getJsonToString(map))
                .compose(RxTransformerHelper.applySchedulerResult(this))
               .filter(data-> data!=null && !TextUtils.isEmpty(data.getCode()))
                .subscribe(dataModel -> {
                   showToast("归账成功，我们将会对您的申请进行审核");

                    EventBusModel eventBusModel=new EventBusModel();
                    eventBusModel.setTag(EventTags.BillRetrunChangefresh);
                    EventBus.getDefault().post(eventBusModel);

                    finish();
                },Throwable::printStackTrace));

    }


    /**
     * 获取帐号信息
     */
    public void getCardInfo() {

        Map<String ,String > map=new HashMap<>();
        map.put("ckey","repayAccount");
        map.put("systemCode", MyConfig.SYSTEMCODE);
        map.put("token", SPUtilHelpr.getUserToken());
        mSubscription.add( RetrofitUtils.getLoaderServer().getInfoByKey("807717", StringUtils.getJsonToString(map))
                .compose(RxTransformerHelper.applySchedulerResult(null))
                .filter(s-> s!=null && !TextUtils.isEmpty(s.getNote()))
                .subscribe(s -> {
                   mBinding.tvCardInfo.setText(s.getNote());
                    /*mBinding.webView.getSettings().setDefaultTextEncodingName("UTF-8") ;
                    mBinding.webView.loadData(s.getNote(),"text/html;charset=UTF-8", "UTF-8");*/
//                    RichText.fromHtml(s.getNote()).into(mBinding.tvInfo);
                },Throwable::printStackTrace));
    }

    /**
     * 获取联系电话信息
     */
    public void getCallPhoneNumberInf() {

        Map<String ,String > map=new HashMap<>();
        map.put("ckey","repayRemark");
        map.put("systemCode", MyConfig.SYSTEMCODE);
        map.put("token", SPUtilHelpr.getUserToken());
        mSubscription.add( RetrofitUtils.getLoaderServer().getInfoByKey("807717", StringUtils.getJsonToString(map))
                .compose(RxTransformerHelper.applySchedulerResult(null))
                .filter(s-> s!=null && !TextUtils.isEmpty(s.getNote()))
                .subscribe(s -> {
                   mBinding.tvCallInfo.setText(s.getNote());
                    /*mBinding.webView.getSettings().setDefaultTextEncodingName("UTF-8") ;
                    mBinding.webView.loadData(s.getNote(),"text/html;charset=UTF-8", "UTF-8");*/
//                    RichText.fromHtml(s.getNote()).into(mBinding.tvInfo);
                },Throwable::printStackTrace));
    }

    /**
     * 归账比例
     */
    public void getRetrunScale(){

        Map<String,String> map=new HashMap<>();
        map.put("key","GUIZAHNG_RATE");
        map.put("systemCode", MyConfig.SYSTEMCODE);
        map.put("companyCode", MyConfig.COMPANYCODE);

       mSubscription.add( RetrofitUtils.getLoaderServer().getRetrunScale("802027", StringUtils.getJsonToString(map))
                .compose(RxTransformerHelper.applySchedulerResult(this))
               .filter(data-> data!=null && !TextUtils.isEmpty(data.getCvalue()))
               .map(data-> Double.valueOf(data.getCvalue()))
                .subscribe(data -> {
                    mCvalue=data;
                },Throwable::printStackTrace));

    }

}
