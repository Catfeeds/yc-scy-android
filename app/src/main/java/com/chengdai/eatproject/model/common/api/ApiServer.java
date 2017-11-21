package com.chengdai.eatproject.model.common.api;


import com.chengdai.eatproject.model.common.model.CodeModel;
import com.chengdai.eatproject.model.common.model.IntroductionInfoModel;
import com.chengdai.eatproject.model.common.model.IsSuccessModes;
import com.chengdai.eatproject.model.common.model.MsgListModel;
import com.chengdai.eatproject.model.common.model.QiniuGetTokenModel;
import com.chengdai.eatproject.model.common.model.TypeInfoModel;
import com.chengdai.eatproject.model.helppay.model.MyFriendListModel;
import com.chengdai.eatproject.model.tryeat.model.GoodsListModel;
import com.chengdai.eatproject.model.tryeat.model.OrderListModel;
import com.chengdai.eatproject.model.tryeat.model.RetrulBIllScal;
import com.chengdai.eatproject.model.user.model.BillListMode;
import com.chengdai.eatproject.model.user.model.CreditAmountModel;
import com.chengdai.eatproject.model.user.model.MyAddressListModel;
import com.chengdai.eatproject.model.user.model.PhoneCodeSendState;
import com.chengdai.eatproject.model.user.model.UserInfo;
import com.chengdai.eatproject.model.user.model.UserLoginState;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 李先俊 on 2017/6/8.
 */

public interface ApiServer {

    public static String urlPath = "/";

    /**
     * 登录
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST(urlPath)
    Observable<BaseResponseModel<UserLoginState>> userLogin(@Field("code") String code, @Field("json") String json);

    /**
     * 找回密码
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST(urlPath)
    Observable<BaseResponseModel<IsSuccessModes>> findPassWord(@Field("code") String code, @Field("json") String json);


    /**
     * 发送验证码
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST(urlPath)
    Observable<BaseResponseModel<PhoneCodeSendState>> getPhoneCode(@Field("code") String code, @Field("json") String json);

    /**
     * 设置 更新 支付密码
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST(urlPath)
    Observable<BaseResponseModel<IsSuccessModes>> updatePayPwd(@Field("code") String code, @Field("json") String json);

    /**
     * 更新手机号
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST(urlPath)
    Observable<BaseResponseModel<IsSuccessModes>> updatePhoneNumber(@Field("code") String code, @Field("json") String json);

    /**
     * 获取用户信息
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST(urlPath)
    Observable<BaseResponseModel<UserInfo>> getUserInfo(@Field("code") String code, @Field("json") String json);


    /**
     * 获取用户详细信息
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST(urlPath)
    Observable<BaseResponseListModel<MyAddressListModel>> getAddress(@Field("code") String code, @Field("json") String json);


    /**
     * 添加收货地址
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST(urlPath)
    Observable<BaseResponseModel<IsSuccessModes>> updateAddress(@Field("code") String code, @Field("json") String json);

    /**
     * 根据ckey查询系统参数
     *
     * @return
     */
    @FormUrlEncoded
    @POST(urlPath)
    Observable<BaseResponseModel<IntroductionInfoModel>> getInfoByKey(@Field("code") String code, @Field("json") String json);

    /**
     * 骑牛
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST(urlPath)
    Observable<BaseResponseModel<QiniuGetTokenModel>> getQiniuTOken(@Field("code") String code, @Field("json") String json);

    /**
     * 更新用户头像
     *
     * @return
     */
    @FormUrlEncoded
    @POST(urlPath)
    Observable<BaseResponseModel<IsSuccessModes>> updateUserLogo(@Field("code") String code, @Field("json") String json);

    /**
     * 获取信用分
     *
     * @return
     */
    @FormUrlEncoded
    @POST(urlPath)
    Observable<BaseResponseListModel<CreditAmountModel>> getXngyong(@Field("code") String code, @Field("json") String json);

    /**
     * 获取信用 流水
     *
     * @return
     */
    @FormUrlEncoded
    @POST(urlPath)
    Observable<BaseResponseModel<BillListMode>> getBillList(@Field("code") String code, @Field("json") String json);

    /**
     * 获取信用 流水
     *
     * @return
     */
    @FormUrlEncoded
    @POST(urlPath)
    Observable<BaseResponseModel<CodeModel>> billReturn(@Field("code") String code, @Field("json") String json);

    /**
     * 发放信用额度
     *
     * @return
     */
    @FormUrlEncoded
    @POST(urlPath)
    Observable<BaseResponseModel<IsSuccessModes>> sendCredit(@Field("code") String code, @Field("json") String json);

    /**
     * 获取试吃产品
     *
     * @return
     */
    @FormUrlEncoded
    @POST(urlPath)
    Observable<BaseResponseModel<GoodsListModel>> getTryEatGoodsList(@Field("code") String code, @Field("json") String json);

    /**
     * 获取订单列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST(urlPath)
    Observable<BaseResponseModel<OrderListModel>> getOrderList(@Field("code") String code, @Field("json") String json);

    /**
     * 获取试吃产品
     *
     * @return
     */
    @FormUrlEncoded
    @POST(urlPath)
    Observable<BaseResponseModel<CodeModel>> tryEatOrderBook(@Field("code") String code, @Field("json") String json);

    /**
     * 获取推荐列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST(urlPath)
    Observable<BaseResponseModel<MyFriendListModel>> getFriendList(@Field("code") String code, @Field("json") String json);

    /**
     * 获取推荐列表
     *
     * @return
     */
    @FormUrlEncoded
    @POST(urlPath)
    Observable<BaseResponseModel<MsgListModel>> getMsgList(@Field("code") String code, @Field("json") String json);

    /**
     * 归账比例
     *
     * @return
     */
    @FormUrlEncoded
    @POST(urlPath)
    Observable<BaseResponseModel<RetrulBIllScal>> getRetrunScale(@Field("code") String code, @Field("json") String json);


    /**
     * 确认收货
     *
     * @return
     */
    @FormUrlEncoded
    @POST(urlPath)
    Observable<BaseResponseModel<IsSuccessModes>> confirmGetOrder(@Field("code") String code, @Field("json") String json);

    /**
     * 根据type查询系统参数
     * @return
     */
    @FormUrlEncoded
    @POST(urlPath)
    Observable<BaseResponseModel<TypeInfoModel>> getTypeSystemInfo(@Field("code") String code, @Field("json") String  json);
}
