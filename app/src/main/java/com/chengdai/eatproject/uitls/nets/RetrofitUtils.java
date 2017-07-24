package com.chengdai.eatproject.uitls.nets;


import com.chengdai.eatproject.BuildConfig;
import com.chengdai.eatproject.model.common.api.ApiServer;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;


/**
 *
 * 服务器api
 * Created by Administrator on 2016/9/1.
 */
public class RetrofitUtils {
    private static RetrofitUtils retrofitUtils;

    private ApiServer apiServer;

    public RetrofitUtils() {
        apiServer = new Retrofit.Builder()
                .baseUrl(getBaseURL())
                .client(OkHttpUtils.getInstance())
                .addConverterFactory(FastJsonConVerter.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(ApiServer.class);
    }

    /**
     * 服务接口单例
     *
     * @return Retrofit
     */
    public static RetrofitUtils bulid() {
        if (retrofitUtils == null) {
            retrofitUtils = new RetrofitUtils();
        }
        return retrofitUtils;
    }


    private ApiServer getApiServer() {
        return apiServer;
    }

    public static ApiServer getLoaderServer() {
        return bulid().getApiServer();
    }
    /**
     * 获取URL  根据版本切换不同版本
     *
     * @return
     */
    public static String getBaseURL() {
        if (BuildConfig.IS_DEBUG){

            return "http://121.43.101.148:9901/forward-service/";//开发
//        return "http://118.178.124.16:3501/forward-service/";//测试

        }else {
//        return "http://118.178.124.16:3501/forward-service/";//测试
            return "http://121.40.113.128:5301/forward-service/";//线上
        }


    }

}
