package com.chengdai.eatproject.model.tryeat.activitys;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chengdai.eatproject.BR;
import com.chengdai.eatproject.R;
import com.chengdai.eatproject.base.BaseRefreshActivity;
import com.chengdai.eatproject.databinding.LayoutTryEatHeadBinding;
import com.chengdai.eatproject.model.common.model.EventBusModel;
import com.chengdai.eatproject.model.tryeat.model.GoodsListModel;
import com.chengdai.eatproject.model.user.activitys.AddressInfoActivity;
import com.chengdai.eatproject.model.user.activitys.UpdateAddressActivity;
import com.chengdai.eatproject.model.user.model.MyAddressListModel;
import com.chengdai.eatproject.uitls.DateUtil;
import com.chengdai.eatproject.uitls.ImgUtils;
import com.chengdai.eatproject.uitls.StringUtils;
import com.chengdai.eatproject.uitls.nets.RetrofitUtils;
import com.chengdai.eatproject.uitls.nets.RxTransformerHelper;
import com.chengdai.eatproject.uitls.nets.RxTransformerListHelper;
import com.chengdai.eatproject.widget.appmanager.EventTags;
import com.chengdai.eatproject.widget.appmanager.MyConfig;
import com.chengdai.eatproject.widget.appmanager.SPUtilHelpr;
import com.chengdai.eatproject.widget.views.MyDividerItemDecoration;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**我的试吃商品列表
 * Created by 李先俊 on 2017/7/18.
 */

public class MyTryEatGoodsListActivity extends BaseRefreshActivity<GoodsListModel.ListBean>{

    private LayoutTryEatHeadBinding mHeadBinding;//头部

    private MyAddressListModel mAddressData;//地址数据

    private String mCanUsePrice;//可使用额度

    /**
     * 打开当前页面
     * @param context
     * @param price 可用信用
     */
    public static void open(Context context,String price){
        if(context==null){
            return;
        }
        Intent intent= new Intent(context,MyTryEatGoodsListActivity.class);
        intent.putExtra("price",price);
        context.startActivity(intent);
    }


    @Override
    protected void onFirstLoad(Bundle savedInstanceState, int pageIndex, int limit) {

        setTopTitle(getString(R.string.txt_i_to_eat));
        setSubLeftImgState(true);

        if(getIntent()!=null){
            mCanUsePrice=getIntent().getStringExtra("price");
        }

        getAddressInfoRequest();
        getGoodeListDataReqeust(this,pageIndex,limit);

        mBinding.rv.addItemDecoration(new MyDividerItemDecoration(this, MyDividerItemDecoration.VERTICAL_LIST));

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
    protected BaseQuickAdapter onCreateAdapter(List mDataList) {

        BaseQuickAdapter mAdapter = getAdapter(mDataList);
        mAdapter.setHeaderView(getHeadView());
        mAdapter.setHeaderAndEmpty(true);

        return mAdapter;
    }

    @NonNull
    private BaseQuickAdapter getAdapter(final List mDataList) {
        return new BaseQuickAdapter<GoodsListModel.ListBean, BaseViewHolder>(R.layout.item_try_eat_goods,mDataList) {
                @Override
                protected void convert(BaseViewHolder helper, GoodsListModel.ListBean item) {
                    if(item==null){
                        return;
                    }

                   helper.setText(R.id.tv_goods_title,item.getName());
                    ImageView img=helper.getView(R.id.img_goods_title);
                    ImgUtils.loadImg(mContext, MyConfig.IMGURL+item.getSplitAdvPic(),img);

                    helper.setText(R.id.tv_goods_slogan,item.getSlogan());

                    if(TextUtils.equals("0",item.getIsTasted())){//未试吃
                        helper.setText(R.id.btn_state,"试吃");
                        helper.setBackgroundRes(R.id.btn_state, R.drawable.selector_red);
                        helper.setTextColor(R.id.btn_state, ContextCompat.getColor(MyTryEatGoodsListActivity.this,R.color.white));
                        helper.setVisible(R.id.tv_time,false);

                    }else if(TextUtils.equals("1",item.getIsTasted())){
                        helper.setText(R.id.btn_state,"已试吃");
                        helper.setBackgroundRes(R.id.btn_state,R.drawable.bg_white);
                        helper.setTextColor(R.id.btn_state,ContextCompat.getColor(MyTryEatGoodsListActivity.this,R.color.text_color));
                        helper.setVisible(R.id.tv_time,true);
                        helper.setText(R.id.tv_time,DateUtil.formatStringData(item.getTasteTime(),DateUtil.DATE_YMD));
                    }
                    helper.setOnClickListener(R.id.btn_state,v -> {

                        if(TextUtils.equals("0",item.getIsTasted())){//未试吃
                            TryEatOrderBook.open(MyTryEatGoodsListActivity.this,mAddressData,item,mCanUsePrice);
                        }else{
//                            TryEatOrderBook.open(MyTryEatGoodsListActivity.this,mAddressData,item,mCanUsePrice);
                          /*  showToast("评价");*/
                        }

                    });

                }
            };
    }

    /**
     * 获取头部
     * @return
     */
    private View getHeadView() {
        mHeadBinding= DataBindingUtil.inflate(getLayoutInflater(), R.layout.layout_try_eat_head, null, false);
        mHeadBinding.layoutAddress.setOnClickListener(v -> {
            UpdateAddressActivity.open(this,mAddressData);
        });
      return   mHeadBinding.getRoot();
    }

    @Override
    public String getEmptyInfo() {
        return "暂无试吃产品";
    }


    /**
     * 获取收货地址数据
     */
    public void getAddressInfoRequest(){
        Map<String,String> map=new HashMap<>();
        map.put("userId", SPUtilHelpr.getUserId());
        map.put("token",SPUtilHelpr.getUserToken());

        mSubscription.add(RetrofitUtils.getLoaderServer().getAddress("805165", StringUtils.getJsonToString(map))
                .compose(RxTransformerListHelper.applySchedulerResult(null))

                .filter(myAddressListModels-> myAddressListModels!=null && myAddressListModels!=null && myAddressListModels.size()>0)

                .map(myAddressListModels -> myAddressListModels.get(0))

                .subscribe(myAddress -> {
                    mAddressData=myAddress;
                    mHeadBinding.setVariable(BR.mAddress,myAddress);

                },Throwable::printStackTrace));

    }

    /**
     * 获取分页产品数据
     * @param context
     * @param page
     * @param limit
     */
    public void getGoodeListDataReqeust(Context context,int page,int limit){
        Map<String ,String> map=new HashMap<>();
        map.put("userId",SPUtilHelpr.getUserId());
        map.put("start",page+"");
        map.put("limit",limit+"");
        map.put("orderColumn","order_no");
        map.put("orderDir","asc");

       mSubscription.add( RetrofitUtils.getLoaderServer().getTryEatGoodsList("808029",StringUtils.getJsonToString(map))
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

        if(TextUtils.equals(e.getTag(),EventTags.AddressChangefresh)){  //修改地址成功
              getAddressInfoRequest();
        }else if(TextUtils.equals(e.getTag(),EventTags.TryEatOrderChangefresh)){  //试吃报名成功
            getGoodeListDataReqeust(null,1,10);
        }
    }


}
