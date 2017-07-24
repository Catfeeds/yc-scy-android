package com.chengdai.eatproject.model.user.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 李先俊 on 2017/7/18.
 */

public class MyAddressListModel implements Parcelable {
    /**
     * code : AD201605241439231656
     * userId : MU000001
     * addressee : 谢延径
     * mobile : 18767101909
     * province : 浙江省
     * city : 杭州市
     * district : 西湖区
     * detailAddress : 杭州
     * isDefault : 1
     */

    private String code;
    private String userId;
    private String addressee;
    private String mobile;
    private String province;
    private String city;
    private String district;
    private String detailAddress;
    private String isDefault;

    private String allAddress;

    public String getAllAddress(){
        allAddress=province+city+district+detailAddress;
        return allAddress;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.userId);
        dest.writeString(this.addressee);
        dest.writeString(this.mobile);
        dest.writeString(this.province);
        dest.writeString(this.city);
        dest.writeString(this.district);
        dest.writeString(this.detailAddress);
        dest.writeString(this.isDefault);
        dest.writeString(this.allAddress);
    }

    public MyAddressListModel() {
    }

    protected MyAddressListModel(Parcel in) {
        this.code = in.readString();
        this.userId = in.readString();
        this.addressee = in.readString();
        this.mobile = in.readString();
        this.province = in.readString();
        this.city = in.readString();
        this.district = in.readString();
        this.detailAddress = in.readString();
        this.isDefault = in.readString();
        this.allAddress = in.readString();
    }

    public static final Parcelable.Creator<MyAddressListModel> CREATOR = new Parcelable.Creator<MyAddressListModel>() {
        @Override
        public MyAddressListModel createFromParcel(Parcel source) {
            return new MyAddressListModel(source);
        }

        @Override
        public MyAddressListModel[] newArray(int size) {
            return new MyAddressListModel[size];
        }
    };
}
