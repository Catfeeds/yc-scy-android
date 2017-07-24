package com.chengdai.eatproject.model.tryeat.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.chengdai.eatproject.uitls.BigDecimalUtils;
import com.chengdai.eatproject.uitls.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李先俊 on 2017/7/20.
 */

public class OrderListModel implements Parcelable {


    /**
     * pageNO : 1
     * start : 0
     * pageSize : 1
     * totalCount : 2
     * totalPage : 2
     * list : [{"code":"DD2017071919554998032854","type":"1","toUser":"SYS_USER_YAOCHENG","receiver":"郑海清","reMobile":"15268501481","reAddress":"浙江省杭州市西湖区梦想小镇","applyUser":"U201707171821095167197","applyNote":"试吃员好吃再来一单","applyDatetime":"Jul 19, 2017 7:55:49 PM","productCode":"YcProductB","productName":"余姚杨梅5斤装","productStrain":"杨梅品种","productSpecsCode":"PS201707191641243503290","productSpecsName":"余姚杨梅5斤装","quantity":1,"price1":0,"price2":205000,"price3":0,"amount1":0,"amount2":205000,"amount3":0,"yunfei":0,"status":"2","payType":"50","payDatetime":"Jul 19, 2017 7:55:49 PM","payAmount1":0,"payAmount2":205000,"payAmount3":0,"promptTimes":0,"updater":"U201707171821095167197","updateDatetime":"Jul 19, 2017 7:55:49 PM","remark":"试吃员下单","isFiled":"0","companyCode":"CD-CYC000009","systemCode":"CD-CYC000009","user":{"userId":"U201707171821095167197","kind":"taster","loginName":"15268501481","nickname":"95167197","photo":"IOS_1500358375601802_1668_2500.jpg","mobile":"15268501481","identityFlag":"0"},"product":{"code":"YcProductB","category":"FL201705252109099874007","type":"FL201707061617042164538","name":"余姚杨梅5斤装","strain":"杨梅品种","slogan":"精品杨梅，顺丰发售","advPic":"14_1496589040937.jpeg","saleStatus":"良好","logisticsDate":"配送时间","logisticsSum":1,"originalPrice":268000,"price1":208000,"price2":205000,"price3":0,"pic":"5_1496589093560.jpeg||8_1496589100785.jpeg","description":"<p><img src=\"http:/ >","location":"1","orderNo":3,"status":"3","updater":"yaocheng","updateDatetime":"Jul 19, 2017 4:43:18 PM","remark":"","boughtCount":4,"isTaste":"1","companyCode":"CD-CYC000009","systemCode":"CD-CYC000009"}}]
     */

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Parcelable {
        /**
         * code : DD2017071919554998032854
         * type : 1
         * toUser : SYS_USER_YAOCHENG
         * receiver : 郑海清
         * reMobile : 15268501481
         * reAddress : 浙江省杭州市西湖区梦想小镇
         * applyUser : U201707171821095167197
         * applyNote : 试吃员好吃再来一单
         * applyDatetime : Jul 19, 2017 7:55:49 PM
         * productCode : YcProductB
         * productName : 余姚杨梅5斤装
         * productStrain : 杨梅品种
         * productSpecsCode : PS201707191641243503290
         * productSpecsName : 余姚杨梅5斤装
         * quantity : 1
         * price1 : 0
         * price2 : 205000
         * price3 : 0
         * amount1 : 0
         * amount2 : 205000
         * amount3 : 0
         * yunfei : 0
         * status : 2
         * payType : 50
         * payDatetime : Jul 19, 2017 7:55:49 PM
         * payAmount1 : 0
         * payAmount2 : 205000
         * payAmount3 : 0
         * promptTimes : 0
         * updater : U201707171821095167197
         * updateDatetime : Jul 19, 2017 7:55:49 PM
         * remark : 试吃员下单
         * isFiled : 0
         * companyCode : CD-CYC000009
         * systemCode : CD-CYC000009
         * user : {"userId":"U201707171821095167197","kind":"taster","loginName":"15268501481","nickname":"95167197","photo":"IOS_1500358375601802_1668_2500.jpg","mobile":"15268501481","identityFlag":"0"}
         * product : {"code":"YcProductB","category":"FL201705252109099874007","type":"FL201707061617042164538","name":"余姚杨梅5斤装","strain":"杨梅品种","slogan":"精品杨梅，顺丰发售","advPic":"14_1496589040937.jpeg","saleStatus":"良好","logisticsDate":"配送时间","logisticsSum":1,"originalPrice":268000,"price1":208000,"price2":205000,"price3":0,"pic":"5_1496589093560.jpeg||8_1496589100785.jpeg","description":"<p><img src=\"http:/ >","location":"1","orderNo":3,"status":"3","updater":"yaocheng","updateDatetime":"Jul 19, 2017 4:43:18 PM","remark":"","boughtCount":4,"isTaste":"1","companyCode":"CD-CYC000009","systemCode":"CD-CYC000009"}
         */

        private String code;
        private String type;
        private String toUser;
        private String receiver;
        private String reMobile;
        private String reAddress;
        private String applyUser;
        private String applyNote;
        private String applyDatetime;
        private String productCode;
        private String productName;
        private String productStrain;
        private String productSpecsCode;
        private String productSpecsName;
        private int quantity;
        private int price1;
        private BigDecimal price2;
        private int price3;
        private int amount1;
        private int amount2;
        private int amount3;
        private int yunfei;
        private String status;
        private String payType;
        private String payDatetime;
        private int payAmount1;
        private BigDecimal payAmount2;
        private int payAmount3;
        private int promptTimes;
        private String updater;
        private String updateDatetime;
        private String remark;
        private String isFiled;
        private String companyCode;
        private String systemCode;
        private UserBean user;
        private ProductBean product;
        private String logisticsCode;
        private String logisticsCompany;

        public String getLogisticsCode() {
            return logisticsCode;
        }

        public void setLogisticsCode(String logisticsCode) {
            this.logisticsCode = logisticsCode;
        }

        public String getLogisticsCompany() {
            return logisticsCompany;
        }

        public void setLogisticsCompany(String logisticsCompany) {
            this.logisticsCompany = logisticsCompany;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getToUser() {
            return toUser;
        }

        public void setToUser(String toUser) {
            this.toUser = toUser;
        }

        public String getReceiver() {
            return receiver;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public String getReMobile() {
            return reMobile;
        }

        public void setReMobile(String reMobile) {
            this.reMobile = reMobile;
        }

        public String getReAddress() {
            return reAddress;
        }

        public void setReAddress(String reAddress) {
            this.reAddress = reAddress;
        }

        public String getApplyUser() {
            return applyUser;
        }

        public void setApplyUser(String applyUser) {
            this.applyUser = applyUser;
        }

        public String getApplyNote() {
            return applyNote;
        }

        public void setApplyNote(String applyNote) {
            this.applyNote = applyNote;
        }

        public String getApplyDatetime() {
            return applyDatetime;
        }

        public void setApplyDatetime(String applyDatetime) {
            this.applyDatetime = applyDatetime;
        }

        public String getProductCode() {
            return productCode;
        }

        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductStrain() {
            return productStrain;
        }

        public void setProductStrain(String productStrain) {
            this.productStrain = productStrain;
        }

        public String getProductSpecsCode() {
            return productSpecsCode;
        }

        public void setProductSpecsCode(String productSpecsCode) {
            this.productSpecsCode = productSpecsCode;
        }

        public String getProductSpecsName() {
            return productSpecsName;
        }

        public void setProductSpecsName(String productSpecsName) {
            this.productSpecsName = productSpecsName;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getPrice1() {
            return price1;
        }

        public void setPrice1(int price1) {
            this.price1 = price1;
        }

        public BigDecimal getPrice2() {
            return price2;
        }

        public void setPrice2(BigDecimal price2) {
            this.price2 = price2;
        }

        public int getPrice3() {
            return price3;
        }

        public void setPrice3(int price3) {
            this.price3 = price3;
        }

        public int getAmount1() {
            return amount1;
        }

        public void setAmount1(int amount1) {
            this.amount1 = amount1;
        }

        public int getAmount2() {
            return amount2;
        }

        public void setAmount2(int amount2) {
            this.amount2 = amount2;
        }

        public int getAmount3() {
            return amount3;
        }

        public void setAmount3(int amount3) {
            this.amount3 = amount3;
        }

        public int getYunfei() {
            return yunfei;
        }

        public void setYunfei(int yunfei) {
            this.yunfei = yunfei;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getPayDatetime() {
            return payDatetime;
        }

        public void setPayDatetime(String payDatetime) {
            this.payDatetime = payDatetime;
        }

        public int getPayAmount1() {
            return payAmount1;
        }

        public void setPayAmount1(int payAmount1) {
            this.payAmount1 = payAmount1;
        }

        public BigDecimal getPayAmount2() {
            return payAmount2;
        }

        public void setPayAmount2(BigDecimal payAmount2) {
            this.payAmount2 = payAmount2;
        }

        public int getPayAmount3() {
            return payAmount3;
        }

        public void setPayAmount3(int payAmount3) {
            this.payAmount3 = payAmount3;
        }

        public int getPromptTimes() {
            return promptTimes;
        }

        public void setPromptTimes(int promptTimes) {
            this.promptTimes = promptTimes;
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

        public String getIsFiled() {
            return isFiled;
        }

        public void setIsFiled(String isFiled) {
            this.isFiled = isFiled;
        }

        public String getCompanyCode() {
            return companyCode;
        }

        public void setCompanyCode(String companyCode) {
            this.companyCode = companyCode;
        }

        public String getSystemCode() {
            return systemCode;
        }

        public void setSystemCode(String systemCode) {
            this.systemCode = systemCode;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public ProductBean getProduct() {
            return product;
        }

        public void setProduct(ProductBean product) {
            this.product = product;
        }

        public static class UserBean implements Parcelable {
            /**
             * userId : U201707171821095167197
             * kind : taster
             * loginName : 15268501481
             * nickname : 95167197
             * photo : IOS_1500358375601802_1668_2500.jpg
             * mobile : 15268501481
             * identityFlag : 0
             */

            private String userId;
            private String kind;
            private String loginName;
            private String nickname;
            private String photo;
            private String mobile;
            private String identityFlag;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getKind() {
                return kind;
            }

            public void setKind(String kind) {
                this.kind = kind;
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

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getIdentityFlag() {
                return identityFlag;
            }

            public void setIdentityFlag(String identityFlag) {
                this.identityFlag = identityFlag;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.userId);
                dest.writeString(this.kind);
                dest.writeString(this.loginName);
                dest.writeString(this.nickname);
                dest.writeString(this.photo);
                dest.writeString(this.mobile);
                dest.writeString(this.identityFlag);
            }

            public UserBean() {
            }

            protected UserBean(Parcel in) {
                this.userId = in.readString();
                this.kind = in.readString();
                this.loginName = in.readString();
                this.nickname = in.readString();
                this.photo = in.readString();
                this.mobile = in.readString();
                this.identityFlag = in.readString();
            }

            public static final Creator<UserBean> CREATOR = new Creator<UserBean>() {
                @Override
                public UserBean createFromParcel(Parcel source) {
                    return new UserBean(source);
                }

                @Override
                public UserBean[] newArray(int size) {
                    return new UserBean[size];
                }
            };
        }

        public static class ProductBean implements Parcelable {
            /**
             * code : YcProductB
             * category : FL201705252109099874007
             * type : FL201707061617042164538
             * name : 余姚杨梅5斤装
             * strain : 杨梅品种
             * slogan : 精品杨梅，顺丰发售
             * advPic : 14_1496589040937.jpeg
             * saleStatus : 良好
             * logisticsDate : 配送时间
             * logisticsSum : 1
             * originalPrice : 268000
             * price1 : 208000
             * price2 : 205000
             * price3 : 0
             * pic : 5_1496589093560.jpeg||8_1496589100785.jpeg
             * description : <p><img src="http:/ >
             * location : 1
             * orderNo : 3
             * status : 3
             * updater : yaocheng
             * updateDatetime : Jul 19, 2017 4:43:18 PM
             * remark :
             * boughtCount : 4
             * isTaste : 1
             * companyCode : CD-CYC000009
             * systemCode : CD-CYC000009
             */

            private String code;
            private String category;
            private String type;
            private String name;
            private String strain;
            private String slogan;
            private String advPic;
            private String saleStatus;
            private String logisticsDate;
            private int logisticsSum;
            private int originalPrice;
            private int price1;
            private BigDecimal price2;
            private int price3;
            private String pic;
            private String description;
            private String location;
            private int orderNo;
            private String status;
            private String updater;
            private String updateDatetime;
            private String remark;
            private int boughtCount;
            private String isTaste;
            private String companyCode;
            private String systemCode;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStrain() {
                return strain;
            }

            public void setStrain(String strain) {
                this.strain = strain;
            }

            public String getSlogan() {
                return slogan;
            }

            public void setSlogan(String slogan) {
                this.slogan = slogan;
            }

            public String getAdvPic() {
                return advPic;
            }

            public String getSplitAdvPic() {

                List<String> s= StringUtils.splitBannerList(advPic);
                if(s .size()>1)
                {
                    return s.get(0);
                }

                return advPic;
            }

            public void setAdvPic(String advPic) {
                this.advPic = advPic;
            }

            public String getSaleStatus() {
                return saleStatus;
            }

            public void setSaleStatus(String saleStatus) {
                this.saleStatus = saleStatus;
            }

            public String getLogisticsDate() {
                return logisticsDate;
            }

            public void setLogisticsDate(String logisticsDate) {
                this.logisticsDate = logisticsDate;
            }

            public int getLogisticsSum() {
                return logisticsSum;
            }

            public void setLogisticsSum(int logisticsSum) {
                this.logisticsSum = logisticsSum;
            }

            public int getOriginalPrice() {
                return originalPrice;
            }

            public void setOriginalPrice(int originalPrice) {
                this.originalPrice = originalPrice;
            }

            public int getPrice1() {
                return price1;
            }

            public void setPrice1(int price1) {
                this.price1 = price1;
            }


            public BigDecimal getPrice2() {
                return price2;
            }

            public void setPrice2(BigDecimal price2) {
                this.price2 = price2;
            }

            public int getPrice3() {
                return price3;
            }

            public void setPrice3(int price3) {
                this.price3 = price3;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public int getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(int orderNo) {
                this.orderNo = orderNo;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
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

            public int getBoughtCount() {
                return boughtCount;
            }

            public void setBoughtCount(int boughtCount) {
                this.boughtCount = boughtCount;
            }

            public String getIsTaste() {
                return isTaste;
            }

            public void setIsTaste(String isTaste) {
                this.isTaste = isTaste;
            }

            public String getCompanyCode() {
                return companyCode;
            }

            public void setCompanyCode(String companyCode) {
                this.companyCode = companyCode;
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
                dest.writeString(this.category);
                dest.writeString(this.type);
                dest.writeString(this.name);
                dest.writeString(this.strain);
                dest.writeString(this.slogan);
                dest.writeString(this.advPic);
                dest.writeString(this.saleStatus);
                dest.writeString(this.logisticsDate);
                dest.writeInt(this.logisticsSum);
                dest.writeInt(this.originalPrice);
                dest.writeInt(this.price1);
                dest.writeSerializable(this.price2);
                dest.writeInt(this.price3);
                dest.writeString(this.pic);
                dest.writeString(this.description);
                dest.writeString(this.location);
                dest.writeInt(this.orderNo);
                dest.writeString(this.status);
                dest.writeString(this.updater);
                dest.writeString(this.updateDatetime);
                dest.writeString(this.remark);
                dest.writeInt(this.boughtCount);
                dest.writeString(this.isTaste);
                dest.writeString(this.companyCode);
                dest.writeString(this.systemCode);
            }

            public ProductBean() {
            }

            protected ProductBean(Parcel in) {
                this.code = in.readString();
                this.category = in.readString();
                this.type = in.readString();
                this.name = in.readString();
                this.strain = in.readString();
                this.slogan = in.readString();
                this.advPic = in.readString();
                this.saleStatus = in.readString();
                this.logisticsDate = in.readString();
                this.logisticsSum = in.readInt();
                this.originalPrice = in.readInt();
                this.price1 = in.readInt();
                this.price2 = (BigDecimal) in.readSerializable();
                this.price3 = in.readInt();
                this.pic = in.readString();
                this.description = in.readString();
                this.location = in.readString();
                this.orderNo = in.readInt();
                this.status = in.readString();
                this.updater = in.readString();
                this.updateDatetime = in.readString();
                this.remark = in.readString();
                this.boughtCount = in.readInt();
                this.isTaste = in.readString();
                this.companyCode = in.readString();
                this.systemCode = in.readString();
            }

            public static final Creator<ProductBean> CREATOR = new Creator<ProductBean>() {
                @Override
                public ProductBean createFromParcel(Parcel source) {
                    return new ProductBean(source);
                }

                @Override
                public ProductBean[] newArray(int size) {
                    return new ProductBean[size];
                }
            };
        }

        public ListBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.code);
            dest.writeString(this.type);
            dest.writeString(this.toUser);
            dest.writeString(this.receiver);
            dest.writeString(this.reMobile);
            dest.writeString(this.reAddress);
            dest.writeString(this.applyUser);
            dest.writeString(this.applyNote);
            dest.writeString(this.applyDatetime);
            dest.writeString(this.productCode);
            dest.writeString(this.productName);
            dest.writeString(this.productStrain);
            dest.writeString(this.productSpecsCode);
            dest.writeString(this.productSpecsName);
            dest.writeInt(this.quantity);
            dest.writeInt(this.price1);
            dest.writeSerializable(this.price2);
            dest.writeInt(this.price3);
            dest.writeInt(this.amount1);
            dest.writeInt(this.amount2);
            dest.writeInt(this.amount3);
            dest.writeInt(this.yunfei);
            dest.writeString(this.status);
            dest.writeString(this.payType);
            dest.writeString(this.payDatetime);
            dest.writeInt(this.payAmount1);
            dest.writeSerializable(this.payAmount2);
            dest.writeInt(this.payAmount3);
            dest.writeInt(this.promptTimes);
            dest.writeString(this.updater);
            dest.writeString(this.updateDatetime);
            dest.writeString(this.remark);
            dest.writeString(this.isFiled);
            dest.writeString(this.companyCode);
            dest.writeString(this.systemCode);
            dest.writeParcelable(this.user, flags);
            dest.writeParcelable(this.product, flags);
            dest.writeString(this.logisticsCode);
            dest.writeString(this.logisticsCompany);
        }

        protected ListBean(Parcel in) {
            this.code = in.readString();
            this.type = in.readString();
            this.toUser = in.readString();
            this.receiver = in.readString();
            this.reMobile = in.readString();
            this.reAddress = in.readString();
            this.applyUser = in.readString();
            this.applyNote = in.readString();
            this.applyDatetime = in.readString();
            this.productCode = in.readString();
            this.productName = in.readString();
            this.productStrain = in.readString();
            this.productSpecsCode = in.readString();
            this.productSpecsName = in.readString();
            this.quantity = in.readInt();
            this.price1 = in.readInt();
            this.price2 = (BigDecimal) in.readSerializable();
            this.price3 = in.readInt();
            this.amount1 = in.readInt();
            this.amount2 = in.readInt();
            this.amount3 = in.readInt();
            this.yunfei = in.readInt();
            this.status = in.readString();
            this.payType = in.readString();
            this.payDatetime = in.readString();
            this.payAmount1 = in.readInt();
            this.payAmount2 = (BigDecimal) in.readSerializable();
            this.payAmount3 = in.readInt();
            this.promptTimes = in.readInt();
            this.updater = in.readString();
            this.updateDatetime = in.readString();
            this.remark = in.readString();
            this.isFiled = in.readString();
            this.companyCode = in.readString();
            this.systemCode = in.readString();
            this.user = in.readParcelable(UserBean.class.getClassLoader());
            this.product = in.readParcelable(ProductBean.class.getClassLoader());
            this.logisticsCode = in.readString();
            this.logisticsCompany = in.readString();
        }

        public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
            @Override
            public ListBean createFromParcel(Parcel source) {
                return new ListBean(source);
            }

            @Override
            public ListBean[] newArray(int size) {
                return new ListBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.list);
    }

    public OrderListModel() {
    }

    protected OrderListModel(Parcel in) {
        this.list = new ArrayList<ListBean>();
        in.readList(this.list, ListBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<OrderListModel> CREATOR = new Parcelable.Creator<OrderListModel>() {
        @Override
        public OrderListModel createFromParcel(Parcel source) {
            return new OrderListModel(source);
        }

        @Override
        public OrderListModel[] newArray(int size) {
            return new OrderListModel[size];
        }
    };
}
