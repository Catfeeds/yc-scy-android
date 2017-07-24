package com.chengdai.eatproject.model.common.activitys;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.chengdai.eatproject.R;
import com.chengdai.eatproject.base.AbsBaseActivity;
import com.chengdai.eatproject.databinding.ActivityIntroductionBinding;
import com.chengdai.eatproject.uitls.StringUtils;
import com.chengdai.eatproject.uitls.nets.RetrofitUtils;
import com.chengdai.eatproject.uitls.nets.RxTransformerHelper;
import com.chengdai.eatproject.widget.appmanager.MyConfig;
import com.chengdai.eatproject.widget.appmanager.SPUtilHelpr;
import com.zzhoujay.richtext.RichText;

import java.util.HashMap;
import java.util.Map;

/**介绍页面
 * Created by 李先俊 on 2017/6/16.
 */

public class IntroductionActivity extends AbsBaseActivity {

    private ActivityIntroductionBinding mBinding;

    private String mCode;//用于请求

    /**
     * 打开当前页面
     * @param context
     */
    public static void open(Context context,String code,String title){
        if(context==null){
            return;
        }
        Intent intent=new Intent(context,IntroductionActivity.class);
        intent.putExtra("code",code);
        intent.putExtra("title",title);
        context.startActivity(intent);
    }



    @Override
    public View addMainView() {
        mBinding= DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_introduction, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        setSubLeftImgState(true);

        if(getIntent()!=null){
            mCode=getIntent().getStringExtra("code");
            setTopTitle(getIntent().getStringExtra("title"));
        }

        getDataReqeust();
    }

    public void getDataReqeust() {

        Map<String ,String > map=new HashMap<>();
        map.put("ckey",mCode);
        map.put("systemCode", MyConfig.SYSTEMCODE);
        map.put("token", SPUtilHelpr.getUserToken());
       mSubscription.add( RetrofitUtils.getLoaderServer().getInfoByKey("807717", StringUtils.getJsonToString(map))
                .compose(RxTransformerHelper.applySchedulerResult(null))
               .filter(s-> s!=null && !TextUtils.isEmpty(s.getNote()))
                .subscribe(s -> {
                    RichText.from(s.getNote()).into(mBinding.tv);
                    /*mBinding.webView.getSettings().setDefaultTextEncodingName("UTF-8") ;
                    mBinding.webView.loadData(s.getNote(),"text/html;charset=UTF-8", "UTF-8");*/
//                    RichText.fromHtml(s.getNote()).into(mBinding.tvInfo);
                },Throwable::printStackTrace));
    }
}
