package com.chengdai.eatproject.model.user.activitys;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;


import com.chengdai.eatproject.R;
import com.chengdai.eatproject.base.AbsBaseActivity;
import com.chengdai.eatproject.databinding.ActivityModifyPhoneBinding;
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

/**更换手机号码
 * Created by 李先俊 on 2017/6/16.
 */

public class UpdatePhoneActivity extends AbsBaseActivity {

    private ActivityModifyPhoneBinding mBinding;

    /**
     * 打开当前页面
     * @param context
     */
    public static void open(Context context){
        if(context==null){
            return;
        }
        Intent intent=new Intent(context,UpdatePhoneActivity.class);

        context.startActivity(intent);
    }


    @Override
    public View addMainView() {
        mBinding= DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_modify_phone, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        setTopTitle("修改手机号");
        setSubLeftImgState(true);

        initListener();
    }

    private void initListener() {
        //发送验证码
        mBinding.btnSendNew.setOnClickListener(v -> {
            if(TextUtils.isEmpty(mBinding.edtPhoneNew.getText().toString())){
                showToast("请输入手机号");
                return;
            }
            sendCodeRequest();
        });

        mBinding.btnConfirm.setOnClickListener(v -> {
            if(TextUtils.isEmpty(mBinding.edtPhoneNew.getText().toString())){
                showToast("请输入手机号");
                return;
            }

            if(TextUtils.isEmpty(mBinding.edtCodeNew.getText().toString())){
                showToast("请输入验证码");
                return;
            }

            updatePhone();

        });

    }

    /**
     * 发送验证码
     */
    private void sendCodeRequest() {
        HashMap<String,String> hashMap=new LinkedHashMap<String, String>();

        hashMap.put("systemCode", MyConfig.SYSTEMCODE);
        hashMap.put("mobile",mBinding.edtPhoneNew.getText().toString());
        hashMap.put("bizType","805047");
        hashMap.put("kind","f1");

        mSubscription.add(RetrofitUtils.getLoaderServer().getPhoneCode("805904", StringUtils.getJsonToString(hashMap))                 //发送验证码
                .compose(RxTransformerHelper.applySchedulerResult(this))
                .subscribe(data -> {
                    if (data !=null && data.isSuccess()){
                        showToast("验证码已经发送请注意查收");
                        mSubscription.add(AppUtils.startCodeDown(60,mBinding.btnSendNew));//启动倒计时
                    }else{
                        showToast("验证码发送失败");
                    }
                },Throwable::printStackTrace));
    }

    /**
     * 更换手机号
     */
    private void updatePhone(){
        Map<String,String> map=new HashMap<>();
        map.put("userId", SPUtilHelpr.getUserId());
        map.put("newMobile",mBinding.edtPhoneNew.getText().toString());
        map.put("smsCaptcha",mBinding.edtCodeNew.getText().toString());
        map.put("token",SPUtilHelpr.getUserToken());

        mSubscription.add(RetrofitUtils.getLoaderServer().updatePhoneNumber("805047", StringUtils.getJsonToString(map))
                .compose(RxTransformerHelper.applySchedulerResult(this))
                .filter(data-> data!=null && data.isSuccess())
                .subscribe(data -> {
                     showToast("手机号修改成功");


                    EventBusModel eventBusModel=new EventBusModel();      //刷新上一页数据
                    eventBusModel.setTag(EventTags.PersonalActivity_phone_refresh);
                    eventBusModel.setEvInfo(mBinding.edtPhoneNew.getText().toString());

                   finish();
                },Throwable::printStackTrace));
    }

}
