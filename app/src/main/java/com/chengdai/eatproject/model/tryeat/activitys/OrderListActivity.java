package com.chengdai.eatproject.model.tryeat.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chengdai.eatproject.R;
import com.chengdai.eatproject.base.BaseRefreshActivity;
import com.chengdai.eatproject.model.common.model.EventBusModel;
import com.chengdai.eatproject.model.tryeat.model.OrderListModel;
import com.chengdai.eatproject.uitls.DateUtil;
import com.chengdai.eatproject.uitls.ImgUtils;
import com.chengdai.eatproject.uitls.StringUtils;
import com.chengdai.eatproject.uitls.nets.RetrofitUtils;
import com.chengdai.eatproject.uitls.nets.RxTransformerHelper;
import com.chengdai.eatproject.widget.appmanager.EventTags;
import com.chengdai.eatproject.widget.appmanager.MyConfig;
import com.chengdai.eatproject.widget.appmanager.SPUtilHelpr;
import com.chengdai.eatproject.widget.views.MyDividerItemDecoration;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 顶多列表
 * Created by 李先俊 on 2017/7/20.
 */

public class OrderListActivity extends BaseRefreshActivity<OrderListModel.ListBean> {

    /**
     * 打开当前页面
     * @param context
     */
    public static void open(Context context){
        if(context==null){
            return;
        }
        Intent intent= new Intent(context,OrderListActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onFirstLoad(Bundle savedInstanceState, int pageIndex, int limit) {

        setSubLeftImgState(true);

        setTopTitle("订单列表");

        mBinding.rv.addItemDecoration(new MyDividerItemDecoration(this, MyDividerItemDecoration.VERTICAL_LIST));
        getGoodeListDataReqeust(this,pageIndex,limit);
    }

    @Override
    protected void onMRefresh(int pageIndex, int limit) {
        getGoodeListDataReqeust(null,pageIndex,limit);
    }

    @Override
    protected void onMLoadMore(int pageIndex, int limit) {
        getGoodeListDataReqeust(null,pageIndex,limit);
    }

    @Override
    protected BaseQuickAdapter onCreateAdapter(List<OrderListModel.ListBean> mDataList) {
        BaseQuickAdapter mAdapter = getAdapter(mDataList);

        mAdapter.setOnItemClickListener((adapter, view, position) -> {

            OrderDetailsActivity.open(this, (OrderListModel.ListBean) mAdapter.getItem(position));

        });

        return mAdapter;
    }


    @NonNull
    private BaseQuickAdapter getAdapter(final List<OrderListModel.ListBean> mDataList) {
        return new BaseQuickAdapter<OrderListModel.ListBean, BaseViewHolder>(R.layout.item_order,mDataList) {
            @Override
            protected void convert(BaseViewHolder helper, OrderListModel.ListBean item) {

                if(item==null) return;

                if(item.getProduct()!=null){
                    ImgUtils.loadImg(OrderListActivity.this, MyConfig.IMGURL+item.getProduct().getSplitAdvPic(),helper.getView(R.id.img_good));

                }
                helper.setText(R.id.txt_number,item.getProductSpecsName() +"  x"+ item.getQuantity());
                helper.setText(R.id.txt_btn,StringUtils.getOrderState(item.getStatus()));

                helper.setText(R.id.txt_orderId,item.getCode());

                helper.setText(R.id.txt_name,item.getProductName());

                helper.setText(R.id.txt_price,StringUtils.getShowPriceSign(item.getPayAmount2()));

                helper.setText(R.id.txt_time, DateUtil.formatStringData(item.getApplyDatetime(),DateUtil.DEFAULT_DATE_FMT));


            }
       };
    }


    @Override
    public String getEmptyInfo() {
        return "暂无订单";
    }


    /**
     * 获取分页产品数据
     * @param context
     * @param page
     * @param limit
     */

    public void getGoodeListDataReqeust(Context context, int page, int limit){
        Map<String ,String> map=new HashMap<>();
        map.put("applyUser", SPUtilHelpr.getUserId());
        map.put("companyCode", MyConfig.COMPANYCODE);
        map.put("systemCode", MyConfig.SYSTEMCODE);
        map.put("start",page+"");
        map.put("limit",limit+"");

        mSubscription.add( RetrofitUtils.getLoaderServer().getOrderList("808068", StringUtils.getJsonToString(map))
                .compose(RxTransformerHelper.applySchedulerResult(context))
                .filter(goodsListModel -> goodsListModel!=null)
                .map(goodsListModel -> goodsListModel.getList())
                .subscribe(goods -> {
                    setData(goods);
                },throwable -> {
                    loadError();
                }));
    }

    @Subscribe
    public void updateDataEvent(EventBusModel e){
        if(e==null){
            return;
        }

        if(TextUtils.equals(e.getTag(), EventTags.GetOrderChangefresh)){  //收货
            getGoodeListDataReqeust(null,1,10);
        }
    }


}
