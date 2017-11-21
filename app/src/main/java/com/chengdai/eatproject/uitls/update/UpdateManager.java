package com.chengdai.eatproject.uitls.update;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.chengdai.eatproject.model.common.model.EventBusModel;
import com.chengdai.eatproject.model.common.model.TypeInfoModel;
import com.chengdai.eatproject.uitls.AppUtils;
import com.chengdai.eatproject.uitls.StringUtils;
import com.chengdai.eatproject.uitls.nets.RetrofitUtils;
import com.chengdai.eatproject.uitls.nets.RxTransformerHelper;
import com.chengdai.eatproject.widget.appmanager.MyConfig;
import com.chengdai.eatproject.widget.dialog.CommonDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * apk下载更新管理
 * Created by 李先俊 on 2017/8/19.
 */

public class UpdateManager {

    protected CompositeDisposable mSubscription;

    private String mAppName;

    public UpdateManager(String appName) {
        mAppName = appName;
        mSubscription = new CompositeDisposable();
    }

    private UpdateManager() {

    }

    public void checkNewApp(final Context context) {

        Map<String, String> map = new HashMap<>();
        map.put("companyCode", MyConfig.COMPANYCODE);
        map.put("systemCode", MyConfig.SYSTEMCODE);
        map.put("type", "android-t");

        mSubscription.add(RetrofitUtils.getLoaderServer().getTypeSystemInfo("805918", StringUtils.getJsonToString(map))
                .compose(RxTransformerHelper.applySchedulerResult(context))
                .filter(typeInfoModel -> typeInfoModel != null && !TextUtils.isEmpty(typeInfoModel.getDownloadUrl()))
                .subscribe(new Consumer<TypeInfoModel>() {
                    @Override
                    public void accept(TypeInfoModel data) throws Exception {
                        //比对版本号判断是否更新
                        if (!TextUtils.equals(data.getVersion(), AppUtils.getAppVersionName(context)) && !TextUtils.isEmpty(AppUtils.getAppVersionName(context))) {
                            if (TextUtils.equals(data.getForceUpdate(), "1")) {//强制更新
                                showUpdateDialog2(context, data.getDownloadUrl(), data.getNote());
                            } else {
                                showUpdateDialog(context, data.getDownloadUrl(), data.getNote());
                            }
                        }
                    }
                }, Throwable::printStackTrace));

    }

    public void clear() {
        if (mSubscription != null) {
            mSubscription.dispose();
            mSubscription.clear();
        }
    }


    /**
     * 展示更新信息
     */
    private void showUpdateDialog(final Context context, final String url, final String note) {
        new CommonDialog(context).builder().setTitle("发现新版本！").setContentMsg(note)
                .setPositiveBtn("立刻更新", new CommonDialog.OnPositiveListener() {
                    @Override
                    public void onPositive(View view) {
                        AppUtils.startWeb(context, url);
                    }
                })
                .setNegativeBtn("取消", null).setCancelable(false).show();

    }


    /**
     * 展示更新信息
     */
    private void showUpdateDialog2(final Context context, final String url, final String note) {

        new CommonDialog(context).builder().setTitle("发现新版本！").setContentMsg(note)
                .setPositiveBtn("立刻更新", new CommonDialog.OnPositiveListener() {
                    @Override
                    public void onPositive(View view) {
                        AppUtils.startWeb(context, url);

                        EventBusModel eventBusMode2=new EventBusModel();//结束主页
                        eventBusMode2.setTag("MainActivityFinish");
                        EventBus.getDefault().post(eventBusMode2);

                        EventBusModel eventBusModel = new EventBusModel();
                        eventBusModel.setTag("AllFINISH");
                        EventBus.getDefault().post(eventBusModel); //结束掉所有界面


                    }
                })
                .setCancelable(false).show();

    }

}
