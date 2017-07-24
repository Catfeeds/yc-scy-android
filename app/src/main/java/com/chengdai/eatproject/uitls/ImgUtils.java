package com.chengdai.eatproject.uitls;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chengdai.eatproject.R;
import com.chengdai.eatproject.uitls.glidetransforms.GlideCircleBorderTransform;
import com.chengdai.eatproject.uitls.glidetransforms.GlideCircleTransform;
import com.chengdai.eatproject.widget.appmanager.MyConfig;


/**
 * 图片加载工具类
 * Created by Administrator on 2016-09-14.
 */
public class ImgUtils {

    public static void  loadLogo(Context context,String imgid,ImageView img){

        if(context==null || img==null)
        {
            return;
        }
        LogUtil.E("图片"+imgid);

        Glide.with(context).load(imgid).placeholder(R.mipmap.photo_default).error(R.mipmap.photo_default).transform(new GlideCircleTransform(context)).into(img);
    }


    public static void  loadImg(Context context,String imgid,ImageView img){

        if(context==null || img==null)
        {
            return;
        }

        LogUtil.E("图片"+imgid);

        Glide.with(context).load(imgid).placeholder(R.mipmap.default_pic).error(R.mipmap.default_pic).into(img);
    }



}
