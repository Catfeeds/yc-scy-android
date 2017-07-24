package com.chengdai.eatproject.model.user.activitys;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.chengdai.eatproject.R;
import com.chengdai.eatproject.base.AbsBaseActivity;
import com.chengdai.eatproject.databinding.ActivityLoginBinding;
import com.chengdai.eatproject.model.common.activitys.MainActivity;
import com.chengdai.eatproject.model.common.model.EventBusModel;
import com.chengdai.eatproject.uitls.StringUtils;
import com.chengdai.eatproject.uitls.nets.RetrofitUtils;
import com.chengdai.eatproject.uitls.nets.RxTransformerHelper;
import com.chengdai.eatproject.widget.appmanager.MyConfig;
import com.chengdai.eatproject.widget.appmanager.SPUtilHelpr;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录
 */
public class LoginActivity extends AbsBaseActivity {

    private ActivityLoginBinding mBinding;
    /**
     * 打开当前页面
     * @param context
     */
    public static void open(Context context){
        if(context==null){
            return;
        }
        Intent intent= new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected boolean canLoadTopTitleView() {
        return false;
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_login, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        initListener();
    }

    /**
     * 设置事件
     */
    private void initListener() {
        //注册点击事件
      mBinding.txtRegister.setOnClickListener(v -> {
          RegisterActivity.open(this);
      });

        //登录
        mBinding.btnLogin.setOnClickListener(v -> {

            if(TextUtils.isEmpty(mBinding.edtPhone.getText().toString())){
                showToast("请输入帐号");
                return;
            }
            if(TextUtils.isEmpty(mBinding.edtPassword.getText().toString())){
                showToast("请输入登录密码");
                return;
            }
            loginRequest();
        });

        mBinding.txtForget.setOnClickListener(v -> {
            FindPwdActivity.open(this,"");
        });

    }


    /**
     * 登录请求
     */
    public void loginRequest(){

        Map<String,String> map=new HashMap<>();
        map.put("loginName",mBinding.edtPhone.getText().toString());
        map.put("loginPwd",mBinding.edtPassword.getText().toString());
        map.put("kind","taster");
        map.put("companyCode", MyConfig.COMPANYCODE);
        map.put("systemCode", MyConfig.SYSTEMCODE);

        mSubscription.add(RetrofitUtils.getLoaderServer().userLogin("805043", StringUtils.getJsonToString(map) )
                .compose(RxTransformerHelper.applySchedulerResult(this))
                .filter( data -> data!=null)
                .subscribe(data -> {

                    if(!TextUtils.isEmpty(data.getToken()) && !TextUtils.isEmpty(data.getUserId())){ //token 和 UserId不为空时

                        SPUtilHelpr.saveUserToken(data.getToken());
                        SPUtilHelpr.saveUserId(data.getUserId());

                        MainActivity.open(this);
                        finish();
                    }

                },Throwable::printStackTrace));


    }

}
