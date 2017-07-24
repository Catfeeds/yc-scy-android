package com.chengdai.eatproject.model.tryeat.activitys;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.chengdai.eatproject.R;
import com.chengdai.eatproject.base.AbsBaseActivity;
import com.chengdai.eatproject.databinding.ActivityOrderDetailsBinding;
import com.chengdai.eatproject.model.common.model.EventBusModel;
import com.chengdai.eatproject.model.tryeat.model.OrderListModel;
import com.chengdai.eatproject.uitls.DateUtil;
import com.chengdai.eatproject.uitls.ImgUtils;
import com.chengdai.eatproject.uitls.StringUtils;
import com.chengdai.eatproject.uitls.nets.RetrofitUtils;
import com.chengdai.eatproject.uitls.nets.RxTransformerHelper;
import com.chengdai.eatproject.widget.appmanager.EventTags;
import com.chengdai.eatproject.widget.appmanager.MyConfig;
import com.chengdai.eatproject.widget.appmanager.SPUtilHelpr;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 李先俊 on 2017/7/20.
 */

public class OrderDetailsActivity extends AbsBaseActivity {

    private ActivityOrderDetailsBinding mBinding;

    private OrderListModel.ListBean mOrderData;

    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context, OrderListModel.ListBean OrderData) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, OrderDetailsActivity.class);

        intent.putExtra("data", OrderData);

        context.startActivity(intent);
    }


    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_order_details, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        if (getIntent() != null) {
            mOrderData = getIntent().getParcelableExtra("data");
        }
        setTopTitle("订单详情");

        setSubLeftImgState(true);

        setShowData();

    }

    /**
     *
     */
    private void setShowData() {
        if (mOrderData == null) {
            return;
        }
        mBinding.tvCode.setText(mOrderData.getCode());
        mBinding.tvOrderState.setText(StringUtils.getOrderState(mOrderData.getStatus()));
        mBinding.tvPrice.setText(StringUtils.getShowPriceSign(mOrderData.getPayAmount2()));

        mBinding.tvNum.setText(mOrderData.getProductSpecsName() + "  x" + mOrderData.getQuantity()); //规格

        mBinding.txtPhone.setText(mOrderData.getReceiver() + " " + mOrderData.getReMobile()); //下单人信息
        mBinding.txtAddress.setText(mOrderData.getReAddress());

        if (mOrderData.getProduct() != null) {                         // 产品信息
            mBinding.tvName.setText(mOrderData.getProduct().getName());
            ImgUtils.loadImg(this, MyConfig.IMGURL + mOrderData.getProduct().getSplitAdvPic(), mBinding.imgTitle);
        }
        //下单时间
        if (!TextUtils.isEmpty(mOrderData.getApplyDatetime())) {
            mBinding.tvPayDate.setText(DateUtil.formatStringData(mOrderData.getApplyDatetime(), DateUtil.DEFAULT_DATE_FMT));
        }

        //物流信息
        mBinding.txtLogisticsCompany.setText(StringUtils.getLogisticsCompany(mOrderData.getLogisticsCompany()));
        mBinding.txtLogisticsCode.setText(TextUtils.isEmpty(mOrderData.getLogisticsCode())?"无":mOrderData.getLogisticsCode());

        if (TextUtils.equals(MyConfig.ORDERTYPEWAITSHOUHUO, mOrderData.getStatus())) {//待收货状态
            mBinding.txtBtn.setVisibility(View.VISIBLE);
            mBinding.layoutWuliu.setVisibility(View.VISIBLE);

            mBinding.txtBtn.setOnClickListener(v -> {
                showDoubleWarnListen("确认收货？", view -> {
                    confirmGetOrder(mOrderData.getCode());
                });
            });

        } else if(TextUtils.equals(MyConfig.ORDERTYPEYISHOUHUO, mOrderData.getStatus())){ //已收货状态
            mBinding.layoutWuliu.setVisibility(View.VISIBLE);
            mBinding.txtBtn.setVisibility(View.GONE);
        }else{
            mBinding.layoutWuliu.setVisibility(View.GONE);
            mBinding.txtBtn.setVisibility(View.GONE);
        }
    }


    public void confirmGetOrder(String code) {

        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        map.put("updater", SPUtilHelpr.getUserId());
        map.put("remark", "确认收货");
        mSubscription.add(RetrofitUtils.getLoaderServer().confirmGetOrder("808057", StringUtils.getJsonToString(map))
                .compose(RxTransformerHelper.applySchedulerResult(this))
                .filter(isSuccessModes -> isSuccessModes != null && isSuccessModes.isSuccess())
                .subscribe(isSuccessModes -> {
                    showToast("确认收货成功");

                    EventBusModel e = new EventBusModel();
                    e.setTag(EventTags.GetOrderChangefresh);
                    EventBus.getDefault().post(e);

                    mBinding.tvOrderState.setText("已收货");
                    mBinding.txtBtn.setVisibility(View.GONE);
                    mOrderData.setStatus(MyConfig.ORDERTYPEYISHOUHUO);


                }, Throwable::printStackTrace));
    }

}
