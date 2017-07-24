package com.chengdai.eatproject.model.mycredit.adapters;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chengdai.eatproject.R;
import com.chengdai.eatproject.model.user.model.BillModel;
import com.chengdai.eatproject.uitls.BigDecimalUtils;
import com.chengdai.eatproject.uitls.LogUtil;
import com.chengdai.eatproject.uitls.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 李先俊 on 2017/7/19.
 */

public class MyBillListAdapter extends BaseQuickAdapter<BillModel, BaseViewHolder> {

    public MyBillListAdapter(@Nullable List<BillModel> data) {
        super(R.layout.item_bill, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BillModel item) {

        if(item==null){
            return;
        }


        TextView tvprice=helper.getView(R.id.txt_price);
        TextView tvInfo=helper.getView(R.id.txt_info);
        TextView txtDate=helper.getView(R.id.txt_date);
        TextView txtTime=helper.getView(R.id.txt_time);
        ImageView imgType=helper.getView(R.id.img_type);


        if(BigDecimalUtils.intValue(item.getTransAmount()) > 0){
            tvprice .setTextColor(ContextCompat.getColor(mContext,R.color.fontColor_orange));
            imgType.setImageResource(R.mipmap.bill_get);
            tvprice.setText("+"+ StringUtils.showCredit(item.getTransAmount()));
        }else {
            tvprice.setTextColor(ContextCompat.getColor(mContext,R.color.color_common_blue));
            imgType.setImageResource(R.mipmap.bill_pay);
            tvprice.setText(StringUtils.showCredit(item.getTransAmount()));
        }

        tvInfo.setText(item.getBizNote());

        if(!TextUtils.isEmpty(item.getCreateDatetime())){
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd日");
            Date date = new Date(item.getCreateDatetime());
            txtDate.setText(dateFormat.format(date));
            txtTime.setText(timeFormat.format(date));
        }

    }

}
