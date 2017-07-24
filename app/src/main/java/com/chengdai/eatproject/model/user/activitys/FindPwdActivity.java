package com.chengdai.eatproject.model.user.activitys;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.chengdai.eatproject.R;
import com.chengdai.eatproject.base.AbsBaseActivity;
import com.chengdai.eatproject.databinding.ActivityModifyPasswordBinding;
import com.chengdai.eatproject.uitls.AppUtils;
import com.chengdai.eatproject.uitls.StringUtils;
import com.chengdai.eatproject.uitls.nets.RetrofitUtils;
import com.chengdai.eatproject.uitls.nets.RxTransformerHelper;
import com.chengdai.eatproject.widget.appmanager.MyConfig;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 找回密码
 */
public class FindPwdActivity extends AbsBaseActivity {

    private ActivityModifyPasswordBinding mBinding;

    private String mPhoneNumber;
    /**
     * 打开当前页面
     * @param context
     */
    public static void open(Context context,String mPhoneNumber){
        if(context==null){
            return;
        }
        Intent intent= new Intent(context,FindPwdActivity.class);
        intent.putExtra("phonenumber",mPhoneNumber);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_modify_password, null, false);
        return mBinding.getRoot();
    }


    @Override
    public void afterCreate(Bundle savedInstanceState) {
        setTopTitle(getString(R.string.update_password));
        setSubLeftImgState(true);

        if(getIntent()!=null){
            mPhoneNumber=getIntent().getStringExtra("phonenumber");
        }

        if(!TextUtils.isEmpty(mPhoneNumber)){
            mBinding.edtPhone.setText(mPhoneNumber);
            mBinding.edtPhone.setSelection(mBinding.edtPhone.getText().toString().length());
        }

        initListener();
    }

    /**
     *
     */
    private void initListener() {

        //发送验证码
        mBinding.btnSend.setOnClickListener(v -> {
            if(TextUtils.isEmpty(mBinding.edtPhone.getText().toString())){
                showToast("请输入手机号");
                return;
            }

            sendCodeRequest();
        });


        //确定
        mBinding.btnConfirm.setOnClickListener(v -> {
            if(TextUtils.isEmpty(mBinding.edtPhone.getText().toString())){
                showToast("请输入手机号");
                return;
            }

            if(TextUtils.isEmpty(mBinding.edtCode.getText().toString())){
                showToast("请输入验证码");
                return;
            }

            if(TextUtils.isEmpty(mBinding.edtPassword.getText().toString())){
                showToast("请输入密码");
                return;
            }
            if(TextUtils.isEmpty(mBinding.edtRepassword.getText().toString())){
                showToast("请重新输入密码");
                return;
            }

            if(mBinding.edtPassword.getText().length()<6){
                showToast("密码不少于6位");
                return;
            }

            if(!mBinding.edtPassword.getText().toString().equals(mBinding.edtRepassword.getText().toString())){
                showToast("两次密码输入不一致");
                return;
            }

            findPwdReqeust();
        });
    }


    /**
     * 发送验证码
     */
    private void sendCodeRequest() {
        HashMap<String,String> hashMap=new LinkedHashMap<String, String>();

        hashMap.put("systemCode", MyConfig.SYSTEMCODE);
        hashMap.put("mobile",mBinding.edtPhone.getText().toString());
        hashMap.put("bizType","805048");
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


    /**
     * 找回密码请求
     */
    private void findPwdReqeust() {

        HashMap<String,String> hashMap=new LinkedHashMap<String, String>();

        hashMap.put("mobile",mBinding.edtPhone.getText().toString());
        hashMap.put("newLoginPwd",mBinding.edtPassword.getText().toString());
        hashMap.put("smsCaptcha",mBinding.edtCode.getText().toString());
        hashMap.put("kind","taster");
        hashMap.put("systemCode", MyConfig.SYSTEMCODE);

        mSubscription.add(RetrofitUtils.getLoaderServer().findPassWord("805048", StringUtils.getJsonToString(hashMap) )
                .compose(RxTransformerHelper.applySchedulerResult(this))
                .subscribe(data -> {
                    if(data!=null && data.isSuccess()){
                        showToast("密码修改成功");
                        finish();
                    }else{
                        showToast("密码修改失败");
                    }
                },Throwable::printStackTrace));

    }


}
