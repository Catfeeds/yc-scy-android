package com.chengdai.eatproject.model.helppay.activitys;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chengdai.eatproject.R;
import com.chengdai.eatproject.base.AbsBaseActivity;
import com.chengdai.eatproject.databinding.ActivityHuokeBinding;
import com.chengdai.eatproject.databinding.ActivityOrderDetailsBinding;
import com.chengdai.eatproject.model.tryeat.model.OrderListModel;
import com.chengdai.eatproject.uitls.DateUtil;
import com.chengdai.eatproject.uitls.ImgUtils;
import com.chengdai.eatproject.uitls.StringUtils;
import com.chengdai.eatproject.uitls.nets.RetrofitUtils;
import com.chengdai.eatproject.uitls.nets.RxTransformerHelper;
import com.chengdai.eatproject.widget.appmanager.MyConfig;
import com.chengdai.eatproject.widget.appmanager.SPUtilHelpr;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**获客
 * Created by 李先俊 on 2017/7/20.
 */

public class HuoKeActivity extends AbsBaseActivity {

    private ActivityHuokeBinding mBinding;

    /**
     * 打开当前页面
     * @param context
     */
    public static void open(Context context){
        if(context==null){
            return;
        }
        Intent intent= new Intent(context,HuoKeActivity.class);


        context.startActivity(intent);
    }


    @Override
    public View addMainView() {
        mBinding= DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_huoke,null,false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        setTopTitle("获客");

        setSubLeftImgState(true);
        getDataReqeust();
    }

    public void getDataReqeust() {

        Map<String ,String > map=new HashMap<>();
        map.put("ckey","domainUrl");
        map.put("systemCode", MyConfig.SYSTEMCODE);
        map.put("token", SPUtilHelpr.getUserToken());
        mSubscription.add( RetrofitUtils.getLoaderServer().getInfoByKey("807717", StringUtils.getJsonToString(map))
                .compose(RxTransformerHelper.applySchedulerResult(null))
                .filter(s-> s!=null && !TextUtils.isEmpty(s.getNote()))

                .map(s-> CodeUtils.createImage(s.getNote()+ "?userRefereeKind=taster"+"&userReferee="+ SPUtilHelpr.getUserId(),400, 400, null))

                .filter(bitmap -> bitmap!=null)

                .map(bitmap -> {

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);

                    byte[] bytes=baos.toByteArray();

                    return bytes;

                }).subscribe(bytes -> {
                    Glide.with(this)
                            .load(bytes)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .into(mBinding.imgQRCode);

                },Throwable::printStackTrace));
    }

}
