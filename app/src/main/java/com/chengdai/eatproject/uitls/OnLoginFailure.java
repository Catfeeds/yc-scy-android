package com.chengdai.eatproject.uitls;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.chengdai.eatproject.model.common.activitys.MainActivity;
import com.chengdai.eatproject.base.BaseApplication;
import com.chengdai.eatproject.widget.appmanager.SPUtilHelpr;


/**
 * 用于处理服务器 错误码
 * Created by Administrator on 2016-09-06.
 */
public class OnLoginFailure {

    public static void startDoFailure(final Context context, String errorMessage) {

        SPUtilHelpr.logOutClear();

        if (context == null) {
            Intent intent = new Intent(BaseApplication.getInstance(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("isStartMain", false);
            BaseApplication.getInstance().startActivity(intent);
            ToastUtil.show(BaseApplication.getInstance(),errorMessage);
            return;
        }

        try{
            if(context instanceof  Activity){
            }

        }catch (Exception e){

        }
    }

}
