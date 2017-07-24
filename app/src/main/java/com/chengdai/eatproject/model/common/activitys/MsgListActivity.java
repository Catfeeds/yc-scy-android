package com.chengdai.eatproject.model.common.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chengdai.eatproject.R;
import com.chengdai.eatproject.base.BaseRefreshActivity;
import com.chengdai.eatproject.model.common.model.MsgListModel;
import com.chengdai.eatproject.uitls.DateUtil;
import com.chengdai.eatproject.uitls.StringUtils;
import com.chengdai.eatproject.uitls.nets.RetrofitUtils;
import com.chengdai.eatproject.uitls.nets.RxTransformerHelper;
import com.chengdai.eatproject.widget.appmanager.MyConfig;
import com.chengdai.eatproject.widget.appmanager.SPUtilHelpr;
import com.chengdai.eatproject.widget.views.MyDividerItemDecoration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**消息里列表页
 * Created by 李先俊 on 2017/7/18.
 */

public class MsgListActivity extends BaseRefreshActivity<MsgListModel.ListBean>{


    /**
     * 打开当前页面
     * @param context
     */
    public static void open(Context context){
        if(context==null){
            return;
        }
        Intent intent= new Intent(context,MsgListActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onFirstLoad(Bundle savedInstanceState, int pageIndex, int limit) {

        setSubLeftImgState(true);

        setTopTitle("消息中心");
        getMsgListRequest(this,pageIndex,limit);
        mBinding.rv.addItemDecoration(new MyDividerItemDecoration(this, MyDividerItemDecoration.VERTICAL_LIST));
    }


    @Override
    protected void onMRefresh(int pageIndex,int limit) {
        getMsgListRequest(null,pageIndex,limit);
    }

    @Override
    protected void onMLoadMore(int pageIndex,int limit) {
        getMsgListRequest(null,pageIndex,limit);
    }

    @Override
    protected BaseQuickAdapter onCreateAdapter(List<MsgListModel.ListBean> mDataList) {
        return getAdapter(mDataList);
    }

    @Override
    public String getEmptyInfo() {
        return "暂无消息";
    }


    @NonNull
    private BaseQuickAdapter getAdapter(final List mDataList) {
        return new BaseQuickAdapter<MsgListModel.ListBean, BaseViewHolder>(R.layout.item_msg,mDataList) {
            @Override
            protected void convert(BaseViewHolder helper, MsgListModel.ListBean item) {
                if(item==null){
                    return;
                }

                helper.setText(R.id.txt_title,item.getSmsTitle());
                helper.setText(R.id.txt_time, DateUtil.formatStringData(item.getCreateDatetime(),DateUtil.DATE_YMD));
                helper.setText(R.id.txt_content,item.getSmsContent());

            }
        };
    }


    /**
     * 获取消息列表
     * @param context
     * @param page
     * @param limit
     */
    public void getMsgListRequest(Context context,int page,int limit){
        Map<String,String> map=new HashMap<>();
        map.put("channelType","4");
        map.put("token",SPUtilHelpr.getUserToken());
        map.put("start", page+"");
        map.put("limit", limit+"");
        map.put("pushType","41");
        map.put("toKind","4");
//        map.put("smsType","1");
        map.put("status","1");
        map.put("fromSystemCode", MyConfig.SYSTEMCODE);
        map.put("toSystemCode", MyConfig.SYSTEMCODE);

        mSubscription.add( RetrofitUtils.getLoaderServer().getMsgList("804040", StringUtils.getJsonToString(map))
                .compose(RxTransformerHelper.applySchedulerResult(context))
                .filter(data-> data!=null)
                .subscribe(dataModel -> {
                 setData(dataModel.getList());
                },throwable -> {
                    loadError();
                }));

    }

}
