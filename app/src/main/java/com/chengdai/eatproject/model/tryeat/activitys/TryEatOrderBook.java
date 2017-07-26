package com.chengdai.eatproject.model.tryeat.activitys;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;
import com.chengdai.eatproject.R;
import com.chengdai.eatproject.base.AbsBaseActivity;
import com.chengdai.eatproject.databinding.ActivityOrderWriteBinding;
import com.chengdai.eatproject.model.common.model.EventBusModel;
import com.chengdai.eatproject.model.tryeat.model.GoodsListModel;
import com.chengdai.eatproject.model.user.model.MyAddressListModel;
import com.chengdai.eatproject.uitls.StringUtils;
import com.chengdai.eatproject.uitls.nets.RetrofitUtils;
import com.chengdai.eatproject.uitls.nets.RxTransformerHelper;
import com.chengdai.eatproject.widget.appmanager.EventTags;
import com.chengdai.eatproject.widget.appmanager.SPUtilHelpr;
import com.chengdai.eatproject.widget.dialog.CommonDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

/**
 * 试吃下单
 */
public class TryEatOrderBook extends AbsBaseActivity {

    private ActivityOrderWriteBinding mBinding;

    private MyAddressListModel mAddressData;//地址

    private String mCanUsePrice;//可用信用

    private GoodsListModel.ListBean mGoodsData;//

    private OptionsPickerView mGoodsSpecsPicker;//商品规格选择

    private GoodsListModel.ListBean.ProductSpecsListBean mPrducet;//所选产品规格

    /**
     * 打开当前页面
     * @param context
     * @param address 地址
     * @param goodsData 商品数据
     * @param price  可用信用
     */
    public static void open(Context context, MyAddressListModel address, GoodsListModel.ListBean goodsData,String price){
        if(context==null){
            return;
        }
        Intent intent= new Intent(context,TryEatOrderBook.class);
        intent.putExtra("addressData",address);
        intent.putExtra("goodsData",goodsData);
        intent.putExtra("price",price
        );
        context.startActivity(intent);
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        setTopTitle("下单");

        setSubRightTitleAndClick("查看",v -> {
            OrderListActivity.open(this);
        });

        if(getIntent()!=null){
            mAddressData=getIntent().getParcelableExtra("addressData");
            mGoodsData=getIntent().getParcelableExtra("goodsData");
            mCanUsePrice=getIntent().getStringExtra("price");
        }


        setSubLeftImgState(true);

        setShowData();

        initPrikerView();

        initListener();

    }

    private void initListener() {
        mBinding.layoutGoodsSize.setOnClickListener(v -> {

            if(mGoodsData==null || mGoodsData.getProductSpecsList()==null){
                showToast("请先选择商品");
                return;
            }

            if(mGoodsData.getProductSpecsList().size()==0){
                showToast("暂无商品规格");
                return;
            }

            if(mGoodsSpecsPicker==null){
                initPrikerView();
            }

            mGoodsSpecsPicker.setPicker(mGoodsData.getProductSpecsList());

            int select=0;           //显示选中下标

            for (int i = 0; i < mGoodsData.getProductSpecsList().size(); i++) {
                GoodsListModel.ListBean.ProductSpecsListBean it=mGoodsData.getProductSpecsList().get(i);
                if (TextUtils.equals(it.getPickerViewText(),mBinding.tvGoodsSize.getText().toString())){
                    select=i;
                    break;
                }
            }
            mGoodsSpecsPicker.setSelectOptions(select);
            mGoodsSpecsPicker.show();

        });

        mBinding.btnSure.setOnClickListener(v -> {

            if(TextUtils.isEmpty(mBinding.addressLayout.editName.getText().toString())){
                showToast("请填写收件人");
                return;
            }

            if(TextUtils.isEmpty(mBinding.addressLayout.editPhone.getText().toString())){
                showToast("请填写收件手机");
                return;
            }
             if(TextUtils.isEmpty(mBinding.addressLayout.editAddresss.getText().toString())){
                showToast("请填写收件地址");
                return;
            }

            if(mPrducet==null || TextUtils.isEmpty(mBinding.tvGoodsSize.getText().toString())){
                showToast("请选择产品规格");
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

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_order_write, null, false);
        return mBinding.getRoot();
    }

    /**
     * 下单情请求
     */
    public void orderBookRequest(){


       Map<String,String>  map=new HashMap<>();

        map.put("applyUser", SPUtilHelpr.getUserId());
        map.put("productSpecsCode",mPrducet.getCode() );
        map.put("receiver",mBinding.addressLayout.editName.getText().toString() );
        map.put("reMobile",mBinding.addressLayout.editPhone.getText().toString() );
        map.put("reAddress",mBinding.addressLayout.editAddresss.getText().toString());

        mSubscription.add(RetrofitUtils.getLoaderServer().tryEatOrderBook("808060", StringUtils.getJsonToString(map))
                .compose(RxTransformerHelper.applySchedulerResult(this))

                .filter(userInfo -> userInfo!=null && !TextUtils.isEmpty(userInfo.getCode()))

                .subscribe(userInfo->{
                    showToast("报名成功");
                    OrderListActivity.open(this);

                    EventBusModel eventBus=new EventBusModel();
                    eventBus.setTag(EventTags.TryEatOrderChangefresh);
                    EventBus.getDefault().post(eventBus);

                    finish();
                },Throwable::printStackTrace));
    }

    private void initPrikerView() {

        mGoodsSpecsPicker = new OptionsPickerView.Builder(this,(options1, options2, options3, v) -> {
            GoodsListModel.ListBean.ProductSpecsListBean selectPro= mGoodsData.getProductSpecsList().get(options1);
            mBinding.tvGoodsSize.setText(selectPro.getPickerViewText());
            mPrducet=selectPro;
        }).setContentTextSize(18).setLineSpacingMultiplier(4).build();

    }



//设置显示数据
    public void setShowData() {
        //地址显示数据
        if (mAddressData!=null){
            mBinding.addressLayout.editName.setText(mAddressData.getAddressee());
            mBinding.addressLayout.editPhone.setText(mAddressData.getMobile());
            mBinding.addressLayout.editAddresss.setText(mAddressData.getAllAddress());
            mBinding.addressLayout.editName.setSelection(mBinding.addressLayout.editName.getText().toString().length());
        }
         //商品显示数据
        if(mGoodsData!=null){
              mBinding.tvTitle.setText(mGoodsData.getName());
            if(mGoodsData.getProductSpecsList()!=null && mGoodsData.getProductSpecsList().size()>0){
                mPrducet=mGoodsData.getProductSpecsList().get(0);
                mBinding.tvGoodsSize.setText(mPrducet.getPickerViewText());
            }
        }


        mBinding.tvCanUserPrice.setText(mCanUsePrice);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mGoodsSpecsPicker!=null){
            mGoodsSpecsPicker.dismiss();
        }
        mGoodsSpecsPicker=null;
    }
}
