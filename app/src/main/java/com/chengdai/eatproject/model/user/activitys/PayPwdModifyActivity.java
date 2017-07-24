package com.chengdai.eatproject.model.user.activitys;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.chengdai.eatproject.R;
import com.chengdai.eatproject.base.AbsBaseActivity;
import com.chengdai.eatproject.databinding.ActivityModifyPayPasswordBinding;
import com.chengdai.eatproject.model.common.model.EventBusModel;
import com.chengdai.eatproject.uitls.AppUtils;
import com.chengdai.eatproject.uitls.StringUtils;
import com.chengdai.eatproject.uitls.nets.RetrofitUtils;
import com.chengdai.eatproject.uitls.nets.RxTransformerHelper;
import com.chengdai.eatproject.widget.appmanager.EventTags;
import com.chengdai.eatproject.widget.appmanager.MyConfig;
import com.chengdai.eatproject.widget.appmanager.SPUtilHelpr;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/** 修改 设置 支付密码
 * Created by 李先俊 on 2017/6/29.
 */

public class PayPwdModifyActivity extends AbsBaseActivity {

    private ActivityModifyPayPasswordBinding mBinding;

    private boolean mIsSetPwd;//是否设置过密码


    /**
     *
     * @param context
     * @param isSetPwd//是否设置过支付密码
     */
    public static void open(Context context, boolean isSetPwd,String mobile){
        if(context==null){
            return;
        }
        Intent intent=new Intent(context,PayPwdModifyActivity.class);
        intent.putExtra("isSetPwd",isSetPwd);
        intent.putExtra("mobile",mobile);
        context.startActivity(intent);
    }



    @Override
    public View addMainView() {
        mBinding= DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_modify_pay_password, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        setSubLeftImgState(true);

        if(getIntent()!=null){
            mIsSetPwd=getIntent().getBooleanExtra("isSetPwd",false);
            mBinding.edtPhone.setText(getIntent().getStringExtra("mobile"));
            mBinding.edtPhone.setSelection(mBinding.edtPhone.getText().length());
        }

        if(mIsSetPwd){
            setTopTitle("修改支付密码");
        }else{
            setTopTitle("设置支付密码");
        }

        setListener();
    }

    /**
     * 设置事件
     */
    private void setListener() {

        mBinding.btnSend.setOnClickListener(v -> {
            if(TextUtils.isEmpty(mBinding.edtPhone.getText())){
                showToast("请输入手机号");
                return;
            }
            sendCodeRequest();
        });

        mBinding.btnConfirm.setOnClickListener(v -> {
            if(TextUtils.isEmpty(mBinding.edtPhone.getText())){
                showToast("请输入手机号");
                return;
            }
            if(TextUtils.isEmpty(mBinding.edtCode.getText())){
                showToast("请输入验证码");
                return;
            }

            if(TextUtils.isEmpty(mBinding.edtRepassword.getText())){
                showToast("请输入支付密码");
                return;
            }
            if(mBinding.edtRepassword.getText().length()<6){
                showToast("支付密码不能低于6位数");
                return;
            }

            setPwd();
        });
    }


    private void setPwd(){

        Map<String,String> object=new HashMap<>();

            object.put("userId", SPUtilHelpr.getUserId());
            object.put("token", SPUtilHelpr.getUserToken());
            if (mIsSetPwd) {
                object.put("newTradePwd",mBinding.edtRepassword.getText().toString().trim());
            } else {
                object.put("tradePwd", mBinding.edtRepassword.getText().toString().trim());
            }

            object.put("smsCaptcha", mBinding.edtCode.getText().toString().toString());
            object.put("tradePwdStrength", "1");

        String code = "";
        if (mIsSetPwd) {
            code = "805057";
        } else {
            code = "805045";
        }

       mSubscription.add( RetrofitUtils.getLoaderServer().updatePayPwd(code, StringUtils.getJsonToString(object))
                .compose(RxTransformerHelper.applySchedulerResult(this))
                .filter(isSuccess-> isSuccess!=null && isSuccess.isSuccess())
                .subscribe(isSuccess-> {
                    if (mIsSetPwd) {
                      showToast("修改成功");
                    } else {
                        showToast("设置成功");

                        SPUtilHelpr.setIssetpaypwd(true);

                        EventBusModel eventBusModel=new EventBusModel();
                        eventBusModel.setTag(EventTags.PersonalActivity_pay_pwd_refresh);
                        EventBus.getDefault().post(eventBusModel);

                    }

                    finish();
                },Throwable::printStackTrace));

    }


    /**
     * 发送验证码
     */
    private void sendCodeRequest() {
        HashMap<String,String> hashMap=new LinkedHashMap<String, String>();

        hashMap.put("systemCode", MyConfig.SYSTEMCODE);
        hashMap.put("mobile",mBinding.edtPhone.getText().toString());
          if (mIsSetPwd) {
                hashMap.put("bizType", "805057");
            } else {
                hashMap.put("bizType", "805045");
            }
        hashMap.put("kind","f1");
        mSubscription.add(RetrofitUtils.getLoaderServer().getPhoneCode("805904", StringUtils.getJsonToString(hashMap))                 //发送验证码
                .compose(RxTransformerHelper.applySchedulerResult(this))
                .subscribe(data -> {
                    if (data !=null && data.isSuccess()){
                        showToast("验证码已经发送请注意查收");
                        mSubscription.add(AppUtils.startCodeDown(60,mBinding.btnSend));//启动倒计时
                    }else{
                        showToast("验证码发送失败");
                    }
                },Throwable::printStackTrace));
    }



}
