package com.chengdai.eatproject.model.tryeat.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chengdai.eatproject.R;
import com.chengdai.eatproject.base.BaseRefreshActivity;
import com.chengdai.eatproject.model.tryeat.model.GoodsListModel;
import com.chengdai.eatproject.uitls.ImgUtils;
import com.chengdai.eatproject.uitls.StringUtils;
import com.chengdai.eatproject.uitls.nets.RetrofitUtils;
import com.chengdai.eatproject.uitls.nets.RxTransformerHelper;
import com.chengdai.eatproject.widget.appmanager.MyConfig;
import com.chengdai.eatproject.widget.views.MyDividerItemDecoration;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品列表选择
 * Created by 李先俊 on 2017/7/20.
 */

public class GoodsListSelectActivity extends BaseRefreshActivity<GoodsListModel.ListBean> {

    /**
     * 打开当前页面
     * @param context
     */
    public static void open(Context context){
        if(context==null){
            return;
        }
        Intent intent= new Intent(context,GoodsListSelectActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onFirstLoad(Bundle savedInstanceState, int pageIndex, int limit) {

        setSubLeftImgState(true);

        setTopTitle("商品列表");

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
    protected BaseQuickAdapter onCreateAdapter(List<GoodsListModel.ListBean> mDataList) {
        BaseQuickAdapter mAdapter = getAdapter(mDataList);
        return mAdapter;
    }

    @NonNull
    private BaseQuickAdapter getAdapter(final List<GoodsListModel.ListBean> mDataList) {
        return new BaseQuickAdapter<GoodsListModel.ListBean, BaseViewHolder>(R.layout.item_try_eat_goods,mDataList) {
                @Override
                protected void convert(BaseViewHolder helper, GoodsListModel.ListBean item) {
                    if(item==null){
                        return;
                    }
                    helper.setText(R.id.tv_goods_title,item.getName());

               /*     if (item.getProductSpecsList()!=null && item.getProductSpecsList().size()>0 && item.getProductSpecsList().get(0)!=null){
                        helper.setText(R.id.tv_goods_slogan,item.getProductSpecsList().get(0).getName());
                    }
*/
                    helper.setText(R.id.tv_goods_slogan,item.getSlogan());

                    helper.setText(R.id.btn_state,"选择");


                    helper.setOnClickListener(R.id.btn_state,v -> {
                        EventBus.getDefault().post(item); //发送选择的商品数据
                        finish();
                    });

                    ImageView img=helper.getView(R.id.img_goods_title);
                    ImgUtils.loadImg(mContext, MyConfig.IMGURL+item.getSplitAdvPic(),img);
                }
            };
    }


    @Override
    public String getEmptyInfo() {
        return "暂无试吃产品";
    }


    /**
     * 获取分页产品数据
     * @param context
     * @param page
     * @param limit
     */

    public void getGoodeListDataReqeust(Context context, int page, int limit){
        Map<String ,String> map=new HashMap<>();
        map.put("status", "3");
        map.put("companyCode", MyConfig.COMPANYCODE);
        map.put("systemCode", MyConfig.SYSTEMCODE);
        map.put("start",page+"");
        map.put("limit",limit+"");
        map.put("category","normal");
        map.put("orderColumn","order_no");
        map.put("orderDir","asc");


        mSubscription.add( RetrofitUtils.getLoaderServer().getTryEatGoodsList("808025", StringUtils.getJsonToString(map))
                .compose(RxTransformerHelper.applySchedulerResult(context))
                .filter(goodsListModel -> goodsListModel!=null)
                .map(goodsListModel -> goodsListModel.getList())
                .subscribe(goods -> {
                    setData(goods);
                },throwable -> {
                    loadError();
                }));
    }

}
