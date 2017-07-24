package com.chengdai.eatproject.model.common.activitys;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.chengdai.eatproject.R;
import com.chengdai.eatproject.base.AbsBaseActivity;
import com.chengdai.eatproject.databinding.ActivityMainBinding;
import com.chengdai.eatproject.model.helppay.activitys.FriendListActivity;
import com.chengdai.eatproject.model.helppay.activitys.HelpFriendOrderBook;
import com.chengdai.eatproject.model.mycredit.activitys.BillRetrunActivity;
import com.chengdai.eatproject.model.mycredit.activitys.MyCreditActivity;
import com.chengdai.eatproject.model.mycredit.activitys.MyCreditNumberActivity;
import com.chengdai.eatproject.model.tryeat.activitys.MyTryEatGoodsListActivity;
import com.chengdai.eatproject.model.tryeat.activitys.RepetTryEatOrderBook;
import com.chengdai.eatproject.model.user.activitys.PersonalActivity;
import com.chengdai.eatproject.model.user.model.UserInfo;
import com.chengdai.eatproject.uitls.ImgUtils;
import com.chengdai.eatproject.uitls.LogUtil;
import com.chengdai.eatproject.uitls.StringUtils;
import com.chengdai.eatproject.uitls.nets.RetrofitUtils;
import com.chengdai.eatproject.uitls.nets.RxTransformerHelper;
import com.chengdai.eatproject.widget.appmanager.MyConfig;
import com.chengdai.eatproject.widget.appmanager.SPUtilHelpr;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 主页
 */
public class MainActivity extends AbsBaseActivity {

    private ActivityMainBinding mBinding;
    private UserInfo mUserInfoData;
    /**
     * 打开当前页面
     * @param context
     */
    public static void open(Context context){
        if(context==null){
            return;
        }
        Intent intent= new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected boolean canLoadTopTitleView() {
        return false;
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        initListener();
        initRefreshLayout();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMsgListRequest();
        getUserInfoRequest(this);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_main, null, false);
        return mBinding.getRoot();
    }

    /**
     * 初始化刷新加载
     */
    private void initRefreshLayout() {
        mBinding.refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getMsgListRequest();
                getUserInfoRequest(null);
            }

            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
            }
        });
    }


    /**
     * 初始化事件
     */
    private void initListener() {

        //等级

        mBinding.tvLevel.setOnClickListener(v -> {
            IntroductionActivity.open(this,"medal","等级规则");
        });
       //消息列表页
        mBinding.bannerSwitch.setOnClickListener(v -> {
            MsgListActivity.open(this);
        });

        //个人中心
        mBinding.imgPhoto.setOnClickListener(v -> {
            PersonalActivity.open(this,mUserInfoData);
        });

        //我的试吃
        mBinding.layoutTryEat.setOnClickListener(v -> {
            if(mUserInfoData==null) return;
            MyTryEatGoodsListActivity.open(this,StringUtils.showPrice(mUserInfoData.getAmount()));
        });
        //信用信息
        mBinding.txtMoney.setOnClickListener(v -> {
            MyCreditNumberActivity.open(this);
        });
        //干点正事
        mBinding.layoutHelpPay.setOnClickListener(v -> {
            if(mUserInfoData==null) return;
            FriendListActivity.open(this,StringUtils.showPrice(mUserInfoData.getAmount()));
        });
          ;
        //我的信用
        mBinding.layoutMycredit.setOnClickListener(v -> {
            MyCreditActivity.open(this);
        });
        //好吃再来
        mBinding.layoutRepetEat.setOnClickListener(v -> {

            if(mUserInfoData==null){
                return;
            }

            UserInfo.AddressListBean address =null;

            if(mUserInfoData.getAddressList()!=null && mUserInfoData.getAddressList().size()>0){
                address=mUserInfoData.getAddressList().get(0);
            }
            RepetTryEatOrderBook.open(this,address,StringUtils.showPrice(mUserInfoData.getAmount()));
        });
    }

    /**
     * 获取用户信息
     */
    public void getUserInfoRequest(Context context){

       Map<String,String>  map=new HashMap<>();

        map.put("userId", SPUtilHelpr.getUserId());

        mSubscription.add(RetrofitUtils.getLoaderServer().getUserInfo("805506", StringUtils.getJsonToString(map))
                .compose(RxTransformerHelper.applySchedulerResult(context))

                .filter(userInfo -> userInfo!=null)

                .subscribe(userInfo->{
                    mUserInfoData=userInfo;
                    LogUtil.E("错误"+StringUtils.showPrice(new BigDecimal(1047990)));
                    LogUtil.E("错误"+StringUtils.showPrice(new BigDecimal(1048000)));
                    LogUtil.E("错误"+StringUtils.showPrice(new BigDecimal(9500000)));

                    mBinding.txtMoney.setText(StringUtils.showPrice(userInfo.getAmount()));
                    if(mUserInfoData.getUserExt()!=null)
                      ImgUtils.loadLogo(this, MyConfig.IMGURL+mUserInfoData.getUserExt().getPhoto(),mBinding.imgPhoto);

                    mBinding.tvLevel.setText(StringUtils.getLevelInfo(userInfo.getLevel()));

                    SPUtilHelpr.saveUserPhone(mUserInfoData.getMobile());
                    //Shi否设置过支付密码
                    SPUtilHelpr.setIssetpaypwd(StringUtils.getIsSetPayPwd(mUserInfoData.getTradepwdFlag()));

                    if(context == null){
                        mBinding.refreshLayout.finishRefresh();
                    }

                },throwable -> {
                    if(context == null){
                        mBinding.refreshLayout.finishRefresh();
                    }
                }));

    }


    /**
     * 获取消息列表
     */
    public void getMsgListRequest(){
        Map<String,String> map=new HashMap<>();
        map.put("channelType","4");
        map.put("token",SPUtilHelpr.getUserToken());
        map.put("start", "1");
        map.put("limit","1");
        map.put("pushType","41");
        map.put("toKind","4");
//        map.put("smsType","1");
        map.put("status","1");
        map.put("fromSystemCode", MyConfig.SYSTEMCODE);
        map.put("toSystemCode", MyConfig.SYSTEMCODE);

        mSubscription.add( RetrofitUtils.getLoaderServer().getMsgList("804040", StringUtils.getJsonToString(map))
                .compose(RxTransformerHelper.applySchedulerResult(null))
                .filter(data-> data!=null && data.getList()!=null && data.getList().size()>0)
                .map(data-> data.getList().get(0))
                .filter(data-> data!=null)
                .subscribe(dataModel -> {
                  mBinding.bannerSwitch.setText(dataModel.getSmsTitle());
                },throwable -> {
                }));

    }



}
