package com.chengdai.eatproject.model.user.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;

/**
 * Created by 李先俊 on 2017/7/18.
 */

public class CreditAmountModel implements Parcelable {

    /**
     * accountNumber : A201707181621322293533626016
     * userId : U20170718162132217422
     * realName : 13765051712
     * type : taster
     * status : 0
     * currency : CB
     * amount : 500000
     * frozenAmount : 0
     * md5 : 23c9876f53b5d60100ac8f6ec6969dfb
     * addAmount : 500000
     * inAmount : 500000
     * outAmount : 0
     * createDatetime : Jul 18, 2017 4:21:32 PM
     * lastOrder : AJ201707181935421546289284138
     * systemCode : CD-CYC000009
     * companyCode : CD-CYC000009
     */

    private String accountNumber;
    private String userId;
    private String realName;
    private String type;
    private String status;
    private String currency;
    private BigDecimal amount;
    private int frozenAmount;
    private String md5;
    private BigDecimal addAmount;
    private BigDecimal inAmount;
    private BigDecimal outAmount;
    private String createDatetime;
    private String lastOrder;
    private String systemCode;
    private String companyCode;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public int getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(int frozenAmount) {
        this.frozenAmount = frozenAmount;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public BigDecimal getAddAmount() {
        return addAmount;
    }

    public void setAddAmount(BigDecimal addAmount) {
        this.addAmount = addAmount;
    }

    public BigDecimal getInAmount() {
        return inAmount;
    }

    public void setInAmount(BigDecimal inAmount) {
        this.inAmount = inAmount;
    }

    public BigDecimal getOutAmount() {
        return outAmount;
    }

    public void setOutAmount(BigDecimal outAmount) {
        this.outAmount = outAmount;
    }

    public BigDecimal getAmount() {

        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getLastOrder() {
        return lastOrder;
    }

    public void setLastOrder(String lastOrder) {
        this.lastOrder = lastOrder;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.accountNumber);
        dest.writeString(this.userId);
        dest.writeString(this.realName);
        dest.writeString(this.type);
        dest.writeString(this.status);
        dest.writeString(this.currency);
        dest.writeSerializable(this.amount);
        dest.writeInt(this.frozenAmount);
        dest.writeString(this.md5);
        dest.writeSerializable(this.addAmount);
        dest.writeSerializable(this.inAmount);
        dest.writeSerializable(this.outAmount);
        dest.writeString(this.createDatetime);
        dest.writeString(this.lastOrder);
        dest.writeString(this.systemCode);
        dest.writeString(this.companyCode);
    }

    public CreditAmountModel() {
    }

    protected CreditAmountModel(Parcel in) {
        this.accountNumber = in.readString();
        this.userId = in.readString();
        this.realName = in.readString();
        this.type = in.readString();
        this.status = in.readString();
        this.currency = in.readString();
        this.amount = (BigDecimal) in.readSerializable();
        this.frozenAmount = in.readInt();
        this.md5 = in.readString();
        this.addAmount = (BigDecimal) in.readSerializable();
        this.inAmount = (BigDecimal) in.readSerializable();
        this.outAmount = (BigDecimal) in.readSerializable();
        this.createDatetime = in.readString();
        this.lastOrder = in.readString();
        this.systemCode = in.readString();
        this.companyCode = in.readString();
    }

    public static final Parcelable.Creator<CreditAmountModel> CREATOR = new Parcelable.Creator<CreditAmountModel>() {
        @Override
        public CreditAmountModel createFromParcel(Parcel source) {
            return new CreditAmountModel(source);
        }

        @Override
        public CreditAmountModel[] newArray(int size) {
            return new CreditAmountModel[size];
        }
    };
}
