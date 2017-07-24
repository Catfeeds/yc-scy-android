package com.chengdai.eatproject.model.helppay.activitys;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;
import com.chengdai.eatproject.R;
import com.chengdai.eatproject.base.AbsBaseActivity;
import com.chengdai.eatproject.databinding.ActivityOrderRepetBinding;
import com.chengdai.eatproject.model.helppay.model.MyFriendListModel;
import com.chengdai.eatproject.model.tryeat.activitys.GoodsListSelectActivity;
import com.chengdai.eatproject.model.tryeat.activitys.OrderListActivity;
import com.chengdai.eatproject.model.tryeat.model.GoodsListModel;
import com.chengdai.eatproject.model.user.model.MyAddressListModel;
import com.chengdai.eatproject.uitls.BigDecimalUtils;
import com.chengdai.eatproject.uitls.StringUtils;
import com.chengdai.eatproject.uitls.nets.RetrofitUtils;
import com.chengdai.eatproject.uitls.nets.RxTransformerHelper;
import com.chengdai.eatproject.uitls.nets.RxTransformerListHelper;
import com.chengdai.eatproject.widget.appmanager.SPUtilHelpr;
import com.chengdai.eatproject.widget.dialog.CommonDialog;

import org.greenrobot.eventbus.Subscribe;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 好吃再来
 */
public class HelpFriendOrderBook extends AbsBaseActivity {

    private ActivityOrderRepetBinding mBinding;

    private MyAddressListModel mAddressData;//地址

    private String mCanUsePrice;//可用信用

    private GoodsListModel.ListBean mGoodsData;//商品数据

    private OptionsPickerView mGoodsSpecsPicker;//商品规格选择

    private GoodsListModel.ListBean.ProductSpecsListBean mPrducet;//所选产品规格

    private MyFriendListModel.ListBean mFriendData;

    /**
     * 打开当前页面
     *
     * @param context
     * @param price   可用信用
     */
    public static void open(Context context, MyFriendListModel.ListBean user, String price) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, HelpFriendOrderBook.class);
        intent.putExtra("price", price);
        intent.putExtra("user", user);
        context.startActivity(intent);
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        setTopTitle("下单");

        setSubRightTitleAndClick("查看", v -> {
            OrderListActivity.open(this);
        });

        if (getIntent() != null) {
            mCanUsePrice = getIntent().getStringExtra("price");
            mFriendData = getIntent().getParcelableExtra("user");
        }


        setSubLeftImgState(true);

        setShowData();

        initPrikerView();

        initListener();

        getAddressInfoRequest();
    }

    private void initPrikerView() {

        mGoodsSpecsPicker = new OptionsPickerView.Builder(this, (options1, options2, options3, v) -> {
            GoodsListModel.ListBean.ProductSpecsListBean selectPro = mGoodsData.getProductSpecsList().get(options1);
            mBinding.tvGoodsSize.setText(selectPro.getPickerViewText());

            mPrducet = selectPro;
            mBinding.tvPrice.setText(StringUtils.showPrice(selectPro.getPrice2()));
            mBinding.editGoodsNum.setText("1");

        }).setContentTextSize(18).setLineSpacingMultiplier(4).build();

    }

    private void initListener() {
        //选择商品
        mBinding.layoutGoodsName.setOnClickListener(v -> {

            GoodsListSelectActivity.open(this);

        });
        //选择型号
        mBinding.layoutGoodsSize.setOnClickListener(v -> {
            showPicker();
        });
        //数量
        mBinding.editGoodsNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if (!TextUtils.isEmpty(s.toString())) {
                    if (mGoodsData == null || mGoodsData.getProductSpecsList() == null) {
                        showToast("请先选择商品");
                        mBinding.editGoodsNum.setText("");
                        return;
                    }

                    if(mPrducet==null){
                        showToast("请选择规格");
                        mBinding.editGoodsNum.setText("");
                    }

                    //显示价格  数量和型号单价的乘积
                    mBinding.tvPrice.setText(StringUtils.showPrice(BigDecimalUtils.multiply(mPrducet.getPrice2(), new BigDecimal(Integer.valueOf(s.toString())))));
                } else {
                    mBinding.tvPrice.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //确定
        mBinding.btnSure.setOnClickListener(v -> {

            if (TextUtils.isEmpty(mBinding.addressLayout.editName.getText().toString())) {
                showToast("请填写收件人");
                return;
            }

            if (TextUtils.isEmpty(mBinding.addressLayout.editPhone.getText().toString())) {
                showToast("请填写收件手机");
                return;
            }
            if (TextUtils.isEmpty(mBinding.addressLayout.editAddresss.getText().toString())) {
                showToast("请填写收件地址");
                return;
            }

            if (mGoodsData == null || TextUtils.isEmpty(mBinding.tvGoodsName.getText().toString())) {
                showToast("请选择商品");
                return;
            }
            if (mGoodsData.getProductSpecsList() == null || TextUtils.isEmpty(mBinding.tvGoodsSize.getText().toString()) || mPrducet == null) {
                showToast("请选择商品规格");
                return;
            }

            if (TextUtils.isEmpty(mBinding.editGoodsNum.getText().toString()) || Double.valueOf(mBinding.editGoodsNum.getText().toString()) <= 0) {
                showToast("请输入正确数量");
                return;
            }

            CommonDialog commonDialog = new CommonDialog(this).builder()
                    .setTitle("提示").setContentMsg("是否确认支付")
                    .setPositiveBtn("是", view -> {
                        orderBookRequest();
                    })
                    .setNegativeBtn("否", null, false);

            commonDialog.show();

        });
    }

    private void showPicker() {
        if (mGoodsData == null || mGoodsData.getProductSpecsList() == null) {
            showToast("请先选择商品");
            return;
        }

        if (mGoodsData.getProductSpecsList().size() == 0) {
            showToast("暂无商品规格");
            return;
        }

        if (mGoodsSpecsPicker == null) {
            initPrikerView();
        }

        mGoodsSpecsPicker.setPicker(mGoodsData.getProductSpecsList());

        int select = 0;           //显示选中下标

        for (int i = 0; i < mGoodsData.getProductSpecsList().size(); i++) {
            GoodsListModel.ListBean.ProductSpecsListBean it = mGoodsData.getProductSpecsList().get(i);
            if (TextUtils.equals(it.getPickerViewText(), mBinding.tvGoodsSize.getText().toString())) {
                select = i;
                break;
            }
        }
        mGoodsSpecsPicker.setSelectOptions(select);
        mGoodsSpecsPicker.show();
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_order_repet, null, false);
        return mBinding.getRoot();
    }

    /**
     * 下单情请求
     */
    public void orderBookRequest() {

        Map<String, String> map = new HashMap<>();

        map.put("applyUser", SPUtilHelpr.getUserId());
        map.put("productSpecsCode", mPrducet.getCode());
        map.put("receiver", mBinding.addressLayout.editName.getText().toString());
        map.put("reMobile", mBinding.addressLayout.editPhone.getText().toString());
        map.put("reAddress", mBinding.addressLayout.editAddresss.getText().toString());
        map.put("quantity", Integer.valueOf(mBinding.editGoodsNum.getText().toString()) + "");

        mSubscription.add(RetrofitUtils.getLoaderServer().tryEatOrderBook("808061", StringUtils.getJsonToString(map))
                .compose(RxTransformerHelper.applySchedulerResult(this))

                .filter(userInfo -> userInfo != null && !TextUtils.isEmpty(userInfo.getCode()))

                .subscribe(userInfo -> {
                    showToast("下单成功");
                    OrderListActivity.open(this);
                    finish();

                }, Throwable::printStackTrace));
    }


    //设置显示数据
    public void setShowData() {
        mBinding.tvCanUserPrice.setText(mCanUsePrice);

        if (mFriendData != null) {
            mBinding.addressLayout.editPhone.setText(mFriendData.getMobile());
        }

    }

    public void setGoodsShow() {

        //商品显示数据
        if (mGoodsData != null) {
            mBinding.tvGoodsName.setText(mGoodsData.getName());

            if (mGoodsData.getProductSpecsList() != null && mGoodsData.getProductSpecsList().size() > 0) {
                GoodsListModel.ListBean.ProductSpecsListBean selectPro = mGoodsData.getProductSpecsList().get(0);
                mBinding.tvGoodsSize.setText(selectPro.getPickerViewText());
                mPrducet = selectPro;
                mBinding.editGoodsNum.setText("1");
            }
        }
    }


    @Subscribe
    public void updateDataEvent(GoodsListModel.ListBean e) {
        mGoodsData = e;
        setGoodsShow();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mGoodsSpecsPicker = null;
    }

    private void showAddressData() {
        if (mAddressData != null) {
            mBinding.addressLayout.editName.setText(mAddressData.getAddressee());
            mBinding.addressLayout.editPhone.setText(mAddressData.getMobile());
            mBinding.addressLayout.editAddresss.setText(mAddressData.getAllAddress());
            mBinding.addressLayout.editName.setSelection(mBinding.addressLayout.editName.getText().toString().length());
        }
    }

    /**
     * 获取收货地址数据
     */
    public void getAddressInfoRequest() {

        if (mFriendData == null) {
            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("userId", mFriendData.getUserId());
        map.put("token", SPUtilHelpr.getUserToken());

        mSubscription.add(RetrofitUtils.getLoaderServer().getAddress("805165", StringUtils.getJsonToString(map))
                .compose(RxTransformerListHelper.applySchedulerResult(null))

                .filter(myAddressListModels -> myAddressListModels != null && myAddressListModels != null && myAddressListModels.size() > 0)

                .map(myAddressListModels -> myAddressListModels.get(0))

                .subscribe(myAddress -> {
                    mAddressData = myAddress;
                    showAddressData();
                }, Throwable::printStackTrace));

    }

}
