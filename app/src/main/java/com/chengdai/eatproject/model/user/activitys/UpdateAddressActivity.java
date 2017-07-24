package com.chengdai.eatproject.model.user.activitys;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.chengdai.eatproject.R;
import com.chengdai.eatproject.base.AbsBaseActivity;
import com.chengdai.eatproject.databinding.ActivityAddAddressBinding;
import com.chengdai.eatproject.model.common.model.EventBusModel;
import com.chengdai.eatproject.model.user.model.MyAddressListModel;
import com.chengdai.eatproject.uitls.StringUtils;
import com.chengdai.eatproject.uitls.nets.RetrofitUtils;
import com.chengdai.eatproject.uitls.nets.RxTransformerHelper;
import com.chengdai.eatproject.widget.appmanager.EventTags;
import com.chengdai.eatproject.widget.appmanager.MyConfig;
import com.chengdai.eatproject.widget.appmanager.SPUtilHelpr;
import com.lljjcoder.citypickerview.widget.CityPicker;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

/**更新收货地址
 * Created by 李先俊 on 2017/6/17.
 */

public class UpdateAddressActivity extends AbsBaseActivity {

    private ActivityAddAddressBinding mBinding;
    private String mProvince;
    private String mCity;
    private String mDistrict;

    private MyAddressListModel mAddressData;


    /**
     * 打开当前页面
     * @param context
     */
    public static void open(Context context, MyAddressListModel data){
        if(context==null){
            return;
        }
        Intent intent=new Intent(context,UpdateAddressActivity.class);

        intent.putExtra("data",data);

        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding= DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_add_address, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        setSubLeftImgState(true);

        setTopTitle("修改收货地址");

        if(getIntent() !=null) {
            mAddressData=getIntent().getParcelableExtra("data");
        }

        setShowData();

        getLocationInfo();

        initViews();

        mBinding.edtName.setSelection(mBinding.edtName.getText().toString().length());

    }

    /**
     * 设置传递过来的数据
     */
    private void setShowData() {

        if(mAddressData == null){
            return;
        }

        mProvince=mAddressData.getProvince();
        mCity=mAddressData.getCity();
        mDistrict=mAddressData.getDistrict();
        mBinding.txtAddress.setText(mAddressData.getProvince()+" "+mAddressData.getCity()+" "+mAddressData.getDistrict());
        mBinding.edtName.setText(mAddressData.getAddressee());
        mBinding.edtPhone.setText(mAddressData.getMobile());
        mBinding.edtDetailed.setText(mAddressData.getDetailAddress());

    }

    private void initViews() {

        mBinding.txtAddress.setText(mProvince +" "+mCity+"  "+mDistrict);

        mBinding.txtAddress.setOnClickListener(v -> {
            cityPicker();
        });

        mBinding.txtConfirm.setOnClickListener(v -> {

            if(TextUtils.isEmpty(mBinding.edtName.getText().toString())){
                showToast("请输入姓名");
                return;
            }

            if(TextUtils.isEmpty(mBinding.edtPhone.getText().toString())){
                showToast("请输入手机号");
                return;
            }

            if(TextUtils.isEmpty(mBinding.txtAddress.getText().toString())){
                showToast("请选择省市区");
                return;
            }

          if(TextUtils.isEmpty(mBinding.edtDetailed.getText().toString())){
                showToast("请输入详细地址");
                return;
            }

            editAddressRequest();
        });
    }


    private  void editAddressRequest(){
        Map<String,String> object=new HashMap<>();
        object.put("code", mAddressData.getCode());
        object.put("userId", SPUtilHelpr.getUserId());
        object.put("addressee",mBinding.edtName.getText().toString().trim());
        object.put("mobile", mBinding.edtPhone.getText().toString().trim());
        object.put("province", mProvince);
        object.put("city", mCity);
        object.put("district", mDistrict);
        object.put("detailAddress", mBinding.edtDetailed.getText().toString().trim());
        object.put("token", SPUtilHelpr.getUserToken());
        object.put("systemCode", MyConfig.SYSTEMCODE);

      mSubscription.add(  RetrofitUtils.getLoaderServer().updateAddress("805162",StringUtils.getJsonToString(object))
                .compose(RxTransformerHelper.applySchedulerResult(this))
              .filter(isSuccessModes -> isSuccessModes!=null && isSuccessModes.isSuccess())
                .subscribe(isSuccessModes -> {
                    showToast("修改成功");

                    EventBusModel eventBusModel=new EventBusModel();
                    eventBusModel.setTag(EventTags.AddressChangefresh);
                    EventBus.getDefault().post(eventBusModel);

                    finish();
                },Throwable::printStackTrace));

    }


    private void cityPicker(){

        CityPicker cityPicker = new CityPicker.Builder(UpdateAddressActivity.this)
                .textSize(18)
                .titleBackgroundColor("#ffffff")
                .titleTextColor("#ffffff")
                .backgroundPop(0xa0000000)
                .confirTextColor("#FE4332")
                .cancelTextColor("#FE4332")
                .province(mProvince)
                .city(mCity)
                .district(mDistrict)
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();

        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                mProvince = citySelected[0];
                //城市
                mCity = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                mDistrict = citySelected[2];
                //邮编
//                String code = citySelected[3];

                mBinding.txtAddress.setText(mProvince+" "+ mCity +" "+ mDistrict);
            }
        });
    }


    public void getLocationInfo() {
        if(!TextUtils.isEmpty(mProvince) && !TextUtils.isEmpty(mCity) && !TextUtils.isEmpty(mDistrict)){

        }else{
            mProvince="北京市";
            mCity="北京市";
            mDistrict="昌平区";
        }
    }
}
