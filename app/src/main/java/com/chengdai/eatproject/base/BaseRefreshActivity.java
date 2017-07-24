package com.chengdai.eatproject.base;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chengdai.eatproject.R;
import com.chengdai.eatproject.databinding.EmptyViewBinding;
import com.chengdai.eatproject.databinding.LayoutCommonRecyclerRefreshBinding;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

/**实现下拉刷新 上拉加载 分页逻辑
 * Created by 李先俊 on 2017/7/19.
 */

public abstract class BaseRefreshActivity<T> extends AbsBaseActivity{

    protected LayoutCommonRecyclerRefreshBinding mBinding;

    private int mPageIndex;//分页下标

    private int mLimit;//分页数量

    private List<T> mDataList;

    private BaseQuickAdapter mAdapter;

    protected EmptyViewBinding mEmptyBinding;

    @Override
    public View addMainView() {
        mBinding= DataBindingUtil.inflate(getLayoutInflater(), R.layout.layout_common_recycler_refresh, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

         mPageIndex=1;//分页下标

         mLimit=10;//分页数量

        mDataList=new ArrayList<T>();

        mAdapter=onCreateAdapter(mDataList);

        mEmptyBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.empty_view, null, false);

        mBinding.rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        if(mAdapter!=null){
            mBinding.rv.setAdapter(mAdapter);
        }

        initRefreshLayout();

        onFirstLoad(savedInstanceState,mPageIndex,mLimit);
    }

    /**
     * 初始化刷新加载
     */
    private void initRefreshLayout() {
        mBinding.refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPageIndex=1;
                onMRefresh(mPageIndex,mLimit);
            }

            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                if(mDataList.size() >0){
                    mPageIndex++;
                }
                onMLoadMore(mPageIndex,mLimit);
            }
        });
    }


    //第一次加载
    protected   abstract void onFirstLoad(Bundle savedInstanceState,int pageIndex,int limit);
    //刷新
    protected   abstract void onMRefresh(int pageIndex,int limit);
    //加载
    protected   abstract  void onMLoadMore(int pageIndex,int limit);

    protected  abstract BaseQuickAdapter onCreateAdapter(List<T> mDataList);

    public abstract String getEmptyInfo();


    public void loadError(){
        mEmptyBinding.tv.setText("加载错误");
        mAdapter.setEmptyView(mEmptyBinding.getRoot());
        if(mPageIndex == 1 && mBinding.refreshLayout.isRefreshing()){
             mBinding.refreshLayout.finishRefresh();
        }else if(mPageIndex >1 && mBinding.refreshLayout.isLoading()){
              mBinding.refreshLayout.finishLoadmore();
        }
    }

    /**
     * 设置加载数据
     * @param datas
     */
    protected void setData(List<T> datas){

        if(mPageIndex == 1){
            if(mBinding.refreshLayout.isRefreshing()) mBinding.refreshLayout.finishRefresh();
            if(datas != null){
                mDataList.clear();
                mDataList.addAll(datas);
                if(mAdapter!=null){
                    mAdapter.notifyDataSetChanged();
                }
            }

        }else if(mPageIndex>1){
            if(mBinding.refreshLayout.isLoading()) mBinding.refreshLayout.finishLoadmore();
            if(datas == null || datas.size()<=0){
                mPageIndex--;
            }else{
                mDataList.addAll(datas);

                if(mAdapter!=null){
                    mAdapter.notifyDataSetChanged();
                }
            }

        }

        if(mDataList.size()==0){
            mEmptyBinding.tv.setText(getEmptyInfo());
            if(mAdapter!=null) mAdapter.setEmptyView(mEmptyBinding.getRoot());
            if(mBinding.refreshLayout.isLoading())  mBinding.refreshLayout.finishLoadmore();

        }
    }

}
