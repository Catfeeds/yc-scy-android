package com.chengdai.eatproject.model.helppay.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chengdai.eatproject.R;
import com.chengdai.eatproject.base.BaseRefreshActivity;
import com.chengdai.eatproject.model.helppay.model.MyFriendListModel;
import com.chengdai.eatproject.uitls.DateUtil;
import com.chengdai.eatproject.uitls.ImgUtils;
import com.chengdai.eatproject.uitls.StringUtils;
import com.chengdai.eatproject.uitls.nets.RetrofitUtils;
import com.chengdai.eatproject.uitls.nets.RxTransformerHelper;
import com.chengdai.eatproject.widget.appmanager.MyConfig;
import com.chengdai.eatproject.widget.appmanager.SPUtilHelpr;
import com.chengdai.eatproject.widget.views.MyDividerItemDecoration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**好友列表
 * Created by 李先俊 on 2017/7/18.
 */

public class FriendListActivity extends BaseRefreshActivity<MyFriendListModel.ListBean>{


    private String mCanUsePrice;//可用信用

    /**
     * 打开当前页面
     * @param context
     */
    public static void open(Context context,String price){
        if(context==null){
            return;
        }
        Intent intent= new Intent(context,FriendListActivity.class);
        intent.putExtra("price",price);
        context.startActivity(intent);
    }


    @Override
    protected void onFirstLoad(Bundle savedInstanceState, int pageIndex, int limit) {
        if(getIntent()!=null){
            mCanUsePrice=getIntent().getStringExtra("price");
        }

        mBinding.rv.addItemDecoration(new MyDividerItemDecoration(this, MyDividerItemDecoration.VERTICAL_LIST));
        setSubLeftImgState(true);
        setTopTitle("干点正事");

        setSubRightTitleAndClick("好友",v -> {
            HuoKeActivity.open(this);
        });

        getFriendList(this,pageIndex,limit);
    }


    @Override
    protected void onMRefresh(int pageIndex,int limit) {
        getFriendList(null,pageIndex,limit);
    }

    @Override
    protected void onMLoadMore(int pageIndex,int limit) {
        getFriendList(null,pageIndex,limit);
    }

    @Override
    protected BaseQuickAdapter onCreateAdapter(List<MyFriendListModel.ListBean> mDataList) {
       return getAdapter(mDataList);
    }

    @Override
    public String getEmptyInfo() {
        return "暂无获客";
    }


    private BaseQuickAdapter getAdapter(final List mDataList) {
        return new BaseQuickAdapter<MyFriendListModel.ListBean, BaseViewHolder>(R.layout.item_friend,mDataList) {
            @Override
            protected void convert(BaseViewHolder helper, MyFriendListModel.ListBean item) {
                if(item==null){
                    return;
                }

                helper.setText(R.id.tv_goods_title,item.getMobile());
                helper.setText(R.id.btn_state,"给TA来一单");
                helper.setText(R.id.tv_goods_slogan, DateUtil.formatStringData(item.getCreateDatetime(),DateUtil.DATE_YMD));

                ImageView img=helper.getView(R.id.img_goods_title);

               if(item.getUserExt()!=null){
                   ImgUtils.loadLogo(mContext,item.getUserExt().getPhoto(),img);
               }

               helper.setOnClickListener(R.id.btn_state,v -> {
                   HelpFriendOrderBook.open(FriendListActivity.this,item,mCanUsePrice);
               });
            }
        };
    }

    public void getFriendList(Context context,int page,int limit){
        Map<String,String> map=new HashMap<>();
        map.put("userReferee",SPUtilHelpr.getUserId());
        map.put("kind","f1");
        map.put("token",SPUtilHelpr.getUserToken());
        map.put("start", page+"");
        map.put("limit", limit+"");
        map.put("companyCode", MyConfig.COMPANYCODE);
        map.put("systemCode", MyConfig.SYSTEMCODE);

        mSubscription.add( RetrofitUtils.getLoaderServer().getFriendList("805054", StringUtils.getJsonToString(map))
                .compose(RxTransformerHelper.applySchedulerResult(context))
                .filter(data-> data!=null  )
                .subscribe(dataModel -> {
                 setData(dataModel.getList());
                },throwable -> {
                    loadError();
                }));

    }

}
