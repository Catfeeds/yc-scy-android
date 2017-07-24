package com.chengdai.eatproject.model.tryeat.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.bigkoo.pickerview.model.IPickerViewData;
import com.chengdai.eatproject.uitls.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**产品列表
 * Created by 李先俊 on 2017/7/19.
 */

public class GoodsListModel implements Parcelable {


    /**
     * pageNO : 1
     * start : 0
     * pageSize : 10
     * totalCount : 1
     * totalPage : 1
     * list : [{"code":"YcProductB","category":"FL201705252109099874007","type":"FL201707061617042164538","name":"余姚杨梅5斤装","strain":"杨梅品种","slogan":"精品杨梅，顺丰发售","advPic":"14_1496589040937.jpeg","saleStatus":"良好","logisticsDate":"配送时间","logisticsSum":1,"originalPrice":268000,"price1":208000,"price2":205000,"price3":0,"pic":"5_1496589093560.jpeg||8_1496589100785.jpeg","description":"<p><img src=\"http://oq4vi26fi.bk","location":"1","orderNo":3,"status":"3","updater":"yaocheng","updateDatetime":"Jul 19, 2017 4:43:18 PM","remark":"","boughtCount":4,"isTaste":"1","companyCode":"CD-CYC000009","systemCode":"CD-CYC000009","productSpecsList":[{"code":"PS201707191641243503290","name":"余姚杨梅5斤装","productCode":"YcProductB","originalPrice":268000,"price1":208000,"price2":205000,"price3":0,"province":"浙江省","weight":0,"quantity":1000,"orderNo":3,"isTaste":"1","companyCode":"CD-CYC000009","systemCode":"CD-CYC000009"}],"isTasted":"0"}]
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
         * description : <p><img src="http://oq4vi26fi.bk
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
         * productSpecsList : [{"code":"PS201707191641243503290","name":"余姚杨梅5斤装","productCode":"YcProductB","originalPrice":268000,"price1":208000,"price2":205000,"price3":0,"province":"浙江省","weight":0,"quantity":1000,"orderNo":3,"isTaste":"1","companyCode":"CD-CYC000009","systemCode":"CD-CYC000009"}]
         * isTasted : 0
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
        private int price2;
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
        private String isTasted;

        public String getTasteTime() {
            return tasteTime;
        }

        public void setTasteTime(String tasteTime) {
            this.tasteTime = tasteTime;
        }

        private String tasteTime;
        private List<ProductSpecsListBean> productSpecsList;

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

        public int getPrice2() {
            return price2;
        }

        public void setPrice2(int price2) {
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

        public String getIsTasted() {
            return isTasted;
        }

        public void setIsTasted(String isTasted) {
            this.isTasted = isTasted;
        }

        public List<ProductSpecsListBean> getProductSpecsList() {
            return productSpecsList;
        }

        public void setProductSpecsList(List<ProductSpecsListBean> productSpecsList) {
            this.productSpecsList = productSpecsList;
        }

        public static class ProductSpecsListBean implements Parcelable, IPickerViewData {
            /**
             * code : PS201707191641243503290
             * name : 余姚杨梅5斤装
             * productCode : YcProductB
             * originalPrice : 268000
             * price1 : 208000
             * price2 : 205000
             * price3 : 0
             * province : 浙江省
             * weight : 0
             * quantity : 1000
             * orderNo : 3
             * isTaste : 1
             * companyCode : CD-CYC000009
             * systemCode : CD-CYC000009
             */

            private String code;
            private String name;
            private String productCode;
            private int originalPrice;
            private BigDecimal price1;
            private BigDecimal price2;
            private BigDecimal price3;
            private String province;
            private Double weight;
            private int quantity;
            private int orderNo;
            private String isTaste;
            private String companyCode;
            private String systemCode;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProductCode() {
                return productCode;
            }

            public void setProductCode(String productCode) {
                this.productCode = productCode;
            }

            public int getOriginalPrice() {
                return originalPrice;
            }

            public void setOriginalPrice(int originalPrice) {
                this.originalPrice = originalPrice;
            }

            public BigDecimal getPrice1() {
                return price1;
            }

            public void setPrice1(BigDecimal price1) {
                this.price1 = price1;
            }

            public BigDecimal getPrice2() {
                return price2;
            }

            public void setPrice2(BigDecimal price2) {
                this.price2 = price2;
            }

            public BigDecimal getPrice3() {
                return price3;
            }

            public void setPrice3(BigDecimal price3) {
                this.price3 = price3;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public Double getWeight() {
                return weight;
            }

            public void setWeight(Double weight) {
                this.weight = weight;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public int getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(int orderNo) {
                this.orderNo = orderNo;
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

            public ProductSpecsListBean() {
            }

            @Override
            public String getPickerViewText() {

                if(weight<=0 && TextUtils.isEmpty(province)){ //没有重量 和收货地址
                    return name;
                }else if(weight<=0){

                    return name +"  重量:"+" 发货地:"+province;

                }else if(TextUtils.isEmpty(province)){

                    return name +"  重量:"+weight+"kg";
                }

                return name +"  重量:"+weight+"kg 发货地:"+province;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.code);
                dest.writeString(this.name);
                dest.writeString(this.productCode);
                dest.writeInt(this.originalPrice);
                dest.writeSerializable(this.price1);
                dest.writeSerializable(this.price2);
                dest.writeSerializable(this.price3);
                dest.writeString(this.province);
                dest.writeValue(this.weight);
                dest.writeInt(this.quantity);
                dest.writeInt(this.orderNo);
                dest.writeString(this.isTaste);
                dest.writeString(this.companyCode);
                dest.writeString(this.systemCode);
            }

            protected ProductSpecsListBean(Parcel in) {
                this.code = in.readString();
                this.name = in.readString();
                this.productCode = in.readString();
                this.originalPrice = in.readInt();
                this.price1 = (BigDecimal) in.readSerializable();
                this.price2 = (BigDecimal) in.readSerializable();
                this.price3 = (BigDecimal) in.readSerializable();
                this.province = in.readString();
                this.weight = (Double) in.readValue(Double.class.getClassLoader());
                this.quantity = in.readInt();
                this.orderNo = in.readInt();
                this.isTaste = in.readString();
                this.companyCode = in.readString();
                this.systemCode = in.readString();
            }

            public static final Creator<ProductSpecsListBean> CREATOR = new Creator<ProductSpecsListBean>() {
                @Override
                public ProductSpecsListBean createFromParcel(Parcel source) {
                    return new ProductSpecsListBean(source);
                }

                @Override
                public ProductSpecsListBean[] newArray(int size) {
                    return new ProductSpecsListBean[size];
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
            dest.writeInt(this.price2);
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
            dest.writeString(this.isTasted);
            dest.writeString(this.tasteTime);
            dest.writeTypedList(this.productSpecsList);
        }

        protected ListBean(Parcel in) {
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
            this.price2 = in.readInt();
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
            this.isTasted = in.readString();
            this.tasteTime = in.readString();
            this.productSpecsList = in.createTypedArrayList(ProductSpecsListBean.CREATOR);
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

    public GoodsListModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.list);
    }

    protected GoodsListModel(Parcel in) {
        this.list = in.createTypedArrayList(ListBean.CREATOR);
    }

    public static final Creator<GoodsListModel> CREATOR = new Creator<GoodsListModel>() {
        @Override
        public GoodsListModel createFromParcel(Parcel source) {
            return new GoodsListModel(source);
        }

        @Override
        public GoodsListModel[] newArray(int size) {
            return new GoodsListModel[size];
        }
    };
}
