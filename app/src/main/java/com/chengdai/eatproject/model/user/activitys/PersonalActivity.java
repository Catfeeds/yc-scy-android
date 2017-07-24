package com.chengdai.eatproject.model.user.activitys;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.chengdai.eatproject.R;
import com.chengdai.eatproject.base.AbsBaseActivity;
import com.chengdai.eatproject.databinding.ActivityPersonalBinding;
import com.chengdai.eatproject.model.common.activitys.ImageSelectActivity;
import com.chengdai.eatproject.model.common.activitys.IntroductionActivity;
import com.chengdai.eatproject.model.common.model.EventBusModel;
import com.chengdai.eatproject.model.user.model.UserInfo;
import com.chengdai.eatproject.uitls.ImgUtils;
import com.chengdai.eatproject.uitls.LogUtil;
import com.chengdai.eatproject.uitls.QiNiuUtil;
import com.chengdai.eatproject.uitls.StringUtils;
import com.chengdai.eatproject.uitls.ToastUtil;
import com.chengdai.eatproject.uitls.nets.RetrofitUtils;
import com.chengdai.eatproject.uitls.nets.RxTransformerHelper;
import com.chengdai.eatproject.widget.appmanager.EventTags;
import com.chengdai.eatproject.widget.appmanager.MyConfig;
import com.chengdai.eatproject.widget.appmanager.SPUtilHelpr;
import com.qiniu.android.http.ResponseInfo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * 个人中心
 */
public class PersonalActivity extends AbsBaseActivity {

    private ActivityPersonalBinding mBinding;
    private UserInfo mUserInfoData;
    public final  int PHOTOFLAG=110;
    /**
     * 打开当前页面
     * @param context
     */
    public static void open(Context context, UserInfo data){
        if(context==null){
            return;
        }
        Intent intent= new Intent(context,PersonalActivity.class);
        intent.putExtra("data",data);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_personal, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        if(getIntent()!=null){
            mUserInfoData=getIntent().getParcelableExtra("data");
        }


        setShowData();

        setTopTitle(getString(R.string.txt_my_setting));
        setSubLeftImgState(true);

        initListener();
    }

    private void setShowData() {
        if(mUserInfoData==null){
            return;
        }

        mBinding.txtPhone.setText(mUserInfoData.getMobile());
        if(mUserInfoData.getUserExt()!=null)
            ImgUtils.loadLogo(this, MyConfig.IMGURL+mUserInfoData.getUserExt().getPhoto(),mBinding.imgPhoto);

        if("0".equals(mUserInfoData.getTradepwdFlag())){  //没有设置支付密码
           mBinding.tvPayPassword.setText(getString(R.string.txt_set_pay_password));
        }else{
            mBinding.tvPayPassword.setText(R.string.txt_update_pay_password);
        }

        mBinding.linPayPassword.setOnClickListener(v -> {
           //支付密码
            PayPwdModifyActivity.open(this,StringUtils.getIsSetPayPwd(mUserInfoData.getTradepwdFlag()),mUserInfoData.getMobile());

        });

    }

    /**
     * 事件
     */
    private void initListener() {

        //修改登录密码
        mBinding.layoutAccount.setOnClickListener(v -> {
            String phone="";
            if(mUserInfoData!=null){
                 phone=mUserInfoData.getMobile();
            }
            FindPwdActivity.open(this,phone);
        });
       //头像
        mBinding.layoutPhoto.setOnClickListener(v -> {
            ImageSelectActivity.launch(this,PHOTOFLAG);
        });
        //地址
        mBinding.layoutAddress.setOnClickListener(v -> {
             AddressInfoActivity.open(this);
        });
        //修改手机号
        mBinding.layoutPhoneNumber.setOnClickListener(v -> {
            UpdatePhoneActivity.open(this);
        });
        //关于我们
        mBinding.layoutAbout.setOnClickListener(v -> {
            IntroductionActivity.open(this,"aboutus","关于我们");
        });
        //退出登录
        mBinding.btnLogout.setOnClickListener(v -> {

            logOut();

        });
    }

    private void logOut() {
        SPUtilHelpr.logOutClear();

        EventBusModel eventBusModel=new EventBusModel();
        eventBusModel.setTag("AllFINISH");
        EventBus.getDefault().post(eventBusModel); //结束掉所有界面

        LoginActivity.open(this);

        finish();
    }


    @Subscribe
    public void updateDataEvent(EventBusModel e){
         if(e==null){
             return;
         }
         if(TextUtils.equals(e.getTag(),EventTags.PersonalActivity_phone_refresh)){  //修改电话成功刷新界面
             mUserInfoData.setMobile(e.getEvInfo());
             setShowData();

             SPUtilHelpr.saveUserPhone(mUserInfoData.getMobile());
         }

         if(TextUtils.equals(e.getTag(),EventTags.PersonalActivity_pay_pwd_refresh)){  //修改支付密码成功刷新界面
             mUserInfoData.setTradepwdFlag("1");
             setShowData();
             SPUtilHelpr.setIssetpaypwd(true);
         }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK || data == null) {
            return;
        }
        if (requestCode == PHOTOFLAG) {
            String path = data.getStringExtra(ImageSelectActivity.staticPath);
            new QiNiuUtil(this).getQiniuURL(new QiNiuUtil.QiNiuCallBack() {
                @Override
                public void onSuccess(String key, ResponseInfo info, JSONObject res) {

                    Map<String,String> map=new HashMap<String, String>();
                    map.put("userId",SPUtilHelpr.getUserId());
                    map.put("photo",key);
                    map.put("token", SPUtilHelpr.getUserToken());

                    mSubscription.add( RetrofitUtils.getLoaderServer().updateUserLogo("805077", StringUtils.getJsonToString(map))
                            .compose(RxTransformerHelper.applySchedulerResult(PersonalActivity.this))
                            .filter(isSuccessModes -> isSuccessModes!=null && isSuccessModes.isSuccess())
                            .subscribe(isSuccessModes -> {
                                ImgUtils.loadLogo(PersonalActivity.this,MyConfig.IMGURL+key,mBinding.imgPhoto);

                            },Throwable::printStackTrace));
                }

                @Override
                public void onFal(String info) {
                    ToastUtil.show(PersonalActivity.this,info);
                }
            },path);
        }

    }


}
