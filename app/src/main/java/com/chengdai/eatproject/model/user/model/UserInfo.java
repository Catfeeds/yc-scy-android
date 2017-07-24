package com.chengdai.eatproject.model.user.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**用户信息
 * Created by 李先俊 on 2017/7/18.
 */

public class UserInfo implements Parcelable {


    /**
     * userId : U201707171821095167197
     * loginName : 15268501481
     * nickname : 95167197
     * loginPwdStrength : 1
     * kind : taster
     * level : 0
     * mobile : 15268501481
     * realName : 郑海清
     * status : 0
     * createDatetime : Jul 17, 2017 6:21:09 PM
     * updater : admin
     * updateDatetime : Jul 17, 2017 8:32:07 PM
     * remark : 解封
     * companyCode : CD-CYC000009
     * userExt : {"userId":"U201707171821095167197","photo":"IOS_1500358375601802_1668_2500.jpg","province":"浙江省","city":"杭州市","area":"西湖区","systemCode":"CD-CYC000009","loginName":"15268501481","mobile":"15268501481","realName":"郑海清"}
     * identityFlag : 0
     * tradepwdFlag : 0
     * addressList : [{"code":"AD201707171821095615418","userId":"U201707171821095167197","addressee":"郑海清","mobile":"15268501481","province":"浙江省","city":"杭州市","district":"江干区","detailAddress":"梦想小镇天使123","systemCode":"CD-CYC000009"}]
     * amount : 0
     */

    private String userId;
    private String loginName;
    private String nickname;
    private String loginPwdStrength;
    private String kind;
    private String level;
    private String mobile;
    private String realName;
    private String status;
    private String createDatetime;
    private String updater;
    private String updateDatetime;
    private String remark;
    private String companyCode;
    private UserExtBean userExt;
    private String identityFlag;
    private String tradepwdFlag;
    private BigDecimal amount;
    private List<AddressListBean> addressList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLoginPwdStrength() {
        return loginPwdStrength;
    }

    public void setLoginPwdStrength(String loginPwdStrength) {
        this.loginPwdStrength = loginPwdStrength;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(String updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public UserExtBean getUserExt() {
        return userExt;
    }

    public void setUserExt(UserExtBean userExt) {
        this.userExt = userExt;
    }

    public String getIdentityFlag() {
        return identityFlag;
    }

    public void setIdentityFlag(String identityFlag) {
        this.identityFlag = identityFlag;
    }

    public String getTradepwdFlag() {
        return tradepwdFlag;
    }

    public void setTradepwdFlag(String tradepwdFlag) {
        this.tradepwdFlag = tradepwdFlag;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<AddressListBean> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressListBean> addressList) {
        this.addressList = addressList;
    }

    public static class UserExtBean implements Parcelable {
        /**
         * userId : U201707171821095167197
         * photo : IOS_1500358375601802_1668_2500.jpg
         * province : 浙江省
         * city : 杭州市
         * area : 西湖区
         * systemCode : CD-CYC000009
         * loginName : 15268501481
         * mobile : 15268501481
         * realName : 郑海清
         */

        private String userId;
        private String photo;
        private String province;
        private String city;
        private String area;
        private String systemCode;
        private String loginName;
        private String mobile;
        private String realName;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
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

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getSystemCode() {
            return systemCode;
        }

        public void setSystemCode(String systemCode) {
            this.systemCode = systemCode;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.userId);
            dest.writeString(this.photo);
            dest.writeString(this.province);
            dest.writeString(this.city);
            dest.writeString(this.area);
            dest.writeString(this.systemCode);
            dest.writeString(this.loginName);
            dest.writeString(this.mobile);
            dest.writeString(this.realName);
        }

        public UserExtBean() {
        }

        protected UserExtBean(Parcel in) {
            this.userId = in.readString();
            this.photo = in.readString();
            this.province = in.readString();
            this.city = in.readString();
            this.area = in.readString();
            this.systemCode = in.readString();
            this.loginName = in.readString();
            this.mobile = in.readString();
            this.realName = in.readString();
        }

        public static final Creator<UserExtBean> CREATOR = new Creator<UserExtBean>() {
            @Override
            public UserExtBean createFromParcel(Parcel source) {
                return new UserExtBean(source);
            }

            @Override
            public UserExtBean[] newArray(int size) {
                return new UserExtBean[size];
            }
        };
    }

    public static class AddressListBean implements Parcelable {
        /**
         * code : AD201707171821095615418
         * userId : U201707171821095167197
         * addressee : 郑海清
         * mobile : 15268501481
         * province : 浙江省
         * city : 杭州市
         * district : 江干区
         * detailAddress : 梦想小镇天使123
         * systemCode : CD-CYC000009
         */

        private String code;
        private String userId;
        private String addressee;
        private String mobile;
        private String province;
        private String city;
        private String district;
        private String detailAddress;
        private String systemCode;


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

        public String getSystemCode() {
            return systemCode;
        }

        public void setSystemCode(String systemCode) {
            this.systemCode = systemCode;
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
            dest.writeString(this.systemCode);
        }

        public AddressListBean() {
        }

        protected AddressListBean(Parcel in) {
            this.code = in.readString();
            this.userId = in.readString();
            this.addressee = in.readString();
            this.mobile = in.readString();
            this.province = in.readString();
            this.city = in.readString();
            this.district = in.readString();
            this.detailAddress = in.readString();
            this.systemCode = in.readString();
        }

        public static final Creator<AddressListBean> CREATOR = new Creator<AddressListBean>() {
            @Override
            public AddressListBean createFromParcel(Parcel source) {
                return new AddressListBean(source);
            }

            @Override
            public AddressListBean[] newArray(int size) {
                return new AddressListBean[size];
            }
        };
    }

    public UserInfo() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.loginName);
        dest.writeString(this.nickname);
        dest.writeString(this.loginPwdStrength);
        dest.writeString(this.kind);
        dest.writeString(this.level);
        dest.writeString(this.mobile);
        dest.writeString(this.realName);
        dest.writeString(this.status);
        dest.writeString(this.createDatetime);
        dest.writeString(this.updater);
        dest.writeString(this.updateDatetime);
        dest.writeString(this.remark);
        dest.writeString(this.companyCode);
        dest.writeParcelable(this.userExt, flags);
        dest.writeString(this.identityFlag);
        dest.writeString(this.tradepwdFlag);
        dest.writeSerializable(this.amount);
        dest.writeTypedList(this.addressList);
    }

    protected UserInfo(Parcel in) {
        this.userId = in.readString();
        this.loginName = in.readString();
        this.nickname = in.readString();
        this.loginPwdStrength = in.readString();
        this.kind = in.readString();
        this.level = in.readString();
        this.mobile = in.readString();
        this.realName = in.readString();
        this.status = in.readString();
        this.createDatetime = in.readString();
        this.updater = in.readString();
        this.updateDatetime = in.readString();
        this.remark = in.readString();
        this.companyCode = in.readString();
        this.userExt = in.readParcelable(UserExtBean.class.getClassLoader());
        this.identityFlag = in.readString();
        this.tradepwdFlag = in.readString();
        this.amount = (BigDecimal) in.readSerializable();
        this.addressList = in.createTypedArrayList(AddressListBean.CREATOR);
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel source) {
            return new UserInfo(source);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };
}
