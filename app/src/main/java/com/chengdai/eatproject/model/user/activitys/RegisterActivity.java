package com.chengdai.eatproject.model.user.activitys;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.chengdai.eatproject.R;
import com.chengdai.eatproject.base.AbsBaseActivity;
import com.chengdai.eatproject.databinding.ActivityMainBinding;
import com.chengdai.eatproject.databinding.ActivityRegisterBinding;

/**
 * 注册
 */
public class RegisterActivity extends AbsBaseActivity {

    private ActivityRegisterBinding mBinding;
    /**
     * 打开当前页面
     * @param context
     */
    public static void open(Context context){
        if(context==null){
            return;
        }
        Intent intent= new Intent(context,RegisterActivity.class);
        context.startActivity(intent);
    }



    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_register, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

    }


}
