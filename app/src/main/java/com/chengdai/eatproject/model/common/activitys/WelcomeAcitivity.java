package com.chengdai.eatproject.model.common.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.chengdai.eatproject.R;
import com.chengdai.eatproject.base.BaseActivity;
import com.chengdai.eatproject.model.user.activitys.LoginActivity;
import com.chengdai.eatproject.widget.appmanager.SPUtilHelpr;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

/**
 * 启动页
 */

public class WelcomeAcitivity extends Activity {

    protected CompositeDisposable mSubscription;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // 用于第一次安装APP，进入到除这个启动activity的其他activity，点击home键，再点击桌面启动图标时，
        // 系统会重启此activty，而不是直接打开之前已经打开过的activity，因此需要关闭此activity

       if (getIntent()!=null && (getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
                finish();
                return;
            }

        mSubscription = new CompositeDisposable();
        setContentView(R.layout.activity_welcom);
        mSubscription.add( Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {//延迟进行跳转
                    if (SPUtilHelpr.isLoginNoStart()) {
                        MainActivity.open(this);
                    } else {

                        LoginActivity.open(this);
                    }
                    finish();
                }, Throwable::printStackTrace));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSubscription != null && !mSubscription.isDisposed()) {
            mSubscription.dispose();
            mSubscription.clear();
        }
    }
}
