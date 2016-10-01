package com.github.nuptboyzhb.xcore.components.impl;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.github.nuptboyzhb.xcore.R;
import com.github.nuptboyzhb.xcore.components.item.XCoreItemUIComponent;
import com.github.nuptboyzhb.xcore.components.XCoreRecyclerAdapter;
import com.github.nuptboyzhb.xcore.components.XCoreUIBaseComponent;
import com.github.nuptboyzhb.xcore.stores.XCoreStore;

import java.util.List;


/**
 * @version mochuan.zhb on 16/9/22.
 * @Author Zheng Haibo
 * @Mail mochuan.zhb@alibaba-inc.com
 * @Company Alibaba Group
 * @Description 内置 RecyclerViewComponent,列表组件,也就是普通组件
 */
public class XCoreRecyclerViewComponent extends XCoreUIBaseComponent implements XCoreStore.IStateChangeListener<List<XCoreRecyclerAdapter.IDataWrapper>> {

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private XCoreRecyclerAdapter mXCoreRecyclerAdapter;

    public XCoreRecyclerViewComponent(Context context) {
        super(context);
    }

    public XCoreRecyclerViewComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XCoreRecyclerViewComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.xcore_recyclerview_component;
    }

    @Override
    public void onViewCreated(View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.xcore_refresh_layout);
        mSwipeRefreshLayout.setEnabled(false);
        mRecyclerView = (RecyclerView) findViewById(R.id.xcore_rv);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mXCoreRecyclerAdapter = new XCoreRecyclerAdapter(this);
        mRecyclerView.setAdapter(mXCoreRecyclerAdapter);
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return mSwipeRefreshLayout;
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return mLayoutManager;
    }

    public XCoreRecyclerAdapter getXCoreRecyclerAdapter() {
        return mXCoreRecyclerAdapter;
    }

    @Override
    public void onStateChanged(List<XCoreRecyclerAdapter.IDataWrapper> status) {
        mXCoreRecyclerAdapter.setDataSet(status);
        mXCoreRecyclerAdapter.notifyDataSetChanged();
    }

    public XCoreRecyclerViewComponent registerItemComponent(XCoreItemUIComponent xCoreItemUIComponent) {
        mXCoreRecyclerAdapter.registerItemUIComponent(xCoreItemUIComponent);
        return this;
    }

    public void setRefreshEnable(boolean enable) {
        mSwipeRefreshLayout.setEnabled(enable);
    }
}
