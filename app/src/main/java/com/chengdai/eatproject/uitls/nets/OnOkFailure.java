package com.chengdai.eatproject.uitls.nets;

import android.content.Context;
import android.content.Intent;

import com.chengdai.eatproject.base.BaseApplication;
import com.chengdai.eatproject.model.common.model.EventBusModel;
import com.chengdai.eatproject.model.user.activitys.LoginActivity;
import com.chengdai.eatproject.uitls.ToastUtil;
import com.chengdai.eatproject.widget.appmanager.SPUtilHelpr;

import org.greenrobot.eventbus.EventBus;

/**
 * 用于处理服务器 错误码
 * Created by Administrator on 2016-09-06.
 */
public class OnOkFailure {

    public static void StartDoFailure(final Context context, String errorMessage) {

        SPUtilHelpr.logOutClear();

        EventBusModel eventBusMode2 = new EventBusModel();//结束主页
        eventBusMode2.setTag("MainActivityFinish");
        EventBus.getDefault().post(eventBusMode2);


        EventBusModel eventBusModel = new EventBusModel();
        eventBusModel.setTag("AllFINISH");
        EventBus.getDefault().post(eventBusModel); //结束掉所有界面

        EventBusModel eventBusModel2 = new EventBusModel();
        eventBusModel2.setTag("LOGINSTATEREFHSH");
        eventBusModel2.setEvBoolean(false);
        EventBus.getDefault().post(eventBusModel2); //刷新未登录数据

        try {
            ToastUtil.show(BaseApplication.getInstance(), errorMessage);
            Intent intent = new Intent(BaseApplication.getInstance(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApplication.getInstance().startActivity(intent);
        } catch (Exception e) {

        }
    }

}
