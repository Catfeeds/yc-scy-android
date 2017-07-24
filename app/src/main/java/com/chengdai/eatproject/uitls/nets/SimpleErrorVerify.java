package com.chengdai.eatproject.uitls.nets;


import android.content.Context;


import com.chengdai.eatproject.base.BaseApplication;
import com.chengdai.eatproject.uitls.LogUtil;
import com.chengdai.eatproject.uitls.ToastUtil;

import java.lang.ref.SoftReference;

/**
 * Created by jiaoyu on 17/4/20.
 */
public class SimpleErrorVerify implements ErrorVerify {
   //软引用
    private SoftReference<Context> mContext;

    public SimpleErrorVerify(Context mContext) {
        this.mContext = new SoftReference<>(mContext);
    }

    @Override
    public void call(String code, String desc) {

        LogUtil.E("请求错误 "+desc);
        ToastUtil.show(BaseApplication.getInstance(),desc);
    }

}
