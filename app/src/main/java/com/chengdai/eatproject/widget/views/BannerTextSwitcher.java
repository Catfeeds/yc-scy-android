package com.chengdai.eatproject.widget.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.chengdai.eatproject.R;
import com.chengdai.eatproject.uitls.LogUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**广告文字上下切换
 * Created by 李先俊 on 2017/7/17.
 */

public class BannerTextSwitcher extends TextSwitcher implements ViewSwitcher.ViewFactory{

    private CompositeDisposable mSubscription;

    private int mShowIndex;

    public int getmShowIndex() {
        return mShowIndex;
    }

    private List<String> mDatas;

    private int mDelayTime;

    public BannerTextSwitcher(Context context) {
        this(context,null);
    }

    public BannerTextSwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {

        // 设置淡入淡出的动画效果
        Animation in = AnimationUtils.loadAnimation(context,
                R.anim.textswitcher_banner_in);
        Animation out = AnimationUtils.loadAnimation(context,
                R.anim.textswitcher_banner_out);

        setInAnimation(in);
        setOutAnimation(out);
        setFactory(this);
        mShowIndex=0;
    }

    public void setShowTextData(List<String> datas,int delayTime){
         this.mDatas=datas;
        this.mDelayTime=delayTime;
    }

    public void start(){
         stop();

            mSubscription=new CompositeDisposable();
            mSubscription.add(Observable.interval(0,mDelayTime,TimeUnit.SECONDS)
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aLong -> {
                        if(mDatas!=null){

                            if(mShowIndex<mDatas.size()-1){
                                mShowIndex++;
                            }else{
                                mShowIndex=0;
                            }
                            setText(mDatas.get(mShowIndex));
                        }
                    },Throwable::printStackTrace));

    }

    public void stop(){
        if (mSubscription != null && !mSubscription.isDisposed()) {
            mSubscription.dispose();
            mSubscription.clear();
            mSubscription=null;
        }
    }

    @Override
    public View makeView() {
        TextView textView = new TextView(getContext());
        textView.setSingleLine();
        textView.setEllipsize(TextUtils.TruncateAt.END);
        FrameLayout.LayoutParams lp = new  FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER_VERTICAL;
        textView.setLayoutParams(lp);

        return textView;
    }
}
