package com.github.nuptboyzhb.xcore.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.nuptboyzhb.xcore.components.IXCoreComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version mochuan.zhb on 2016/8/9.
 * @Author Zheng Haibo
 * @Blog github.com/nuptboyzhb
 * @Company Alibaba Group
 * @Description 通用的Adapter for RecyclerView
 */
public class XCoreRecyclerAdapter extends RecyclerView.Adapter<XCoreRecyclerAdapter.CommonViewHolder> {

    private IXCoreComponent mIXCoreComponent;//外层UI组件
    private List<IDataComponent> mDataSet = new ArrayList<IDataComponent>();//数据源
    private SparseArray<XCoreItemUIComponent> mConfigurationSparseArray = new SparseArray<XCoreItemUIComponent>();//集合:type对应的Item组件
    private Map<String, Integer> mViewTypeMap = new HashMap<String, Integer>();//type的string和int映射

    public XCoreRecyclerAdapter() {

    }

    public XCoreRecyclerAdapter(IXCoreComponent mIXCoreComponent) {
        this.mIXCoreComponent = mIXCoreComponent;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        //根据数据类型获取对应的item组件
        XCoreItemUIComponent XCoreItemUIComponent = mConfigurationSparseArray.get(type);
        if (XCoreItemUIComponent == null) {//如果未获取到,展示空item
            return getDefaultViewHolder(viewGroup.getContext());
        }
        try {
            //使用item组件创建一个新的View
            View view = XCoreItemUIComponent.onCreateView(LayoutInflater.from(viewGroup.getContext()), viewGroup);
            //使用View构建内部的ViewHolder
            CommonViewHolder commonViewHolder = new CommonViewHolder(view);
            //创建一个新的Item组件
            XCoreItemUIComponent realItem = XCoreItemUIComponent.getClass().newInstance();
            //将创建的View设置到真是的Item组件中
            realItem.setItemView(view);
            //使用内部ViewHolder
            commonViewHolder.setXCoreItemUIComponent(realItem);
            return commonViewHolder;
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return getDefaultViewHolder(viewGroup.getContext());
    }

    @Override
    public void onBindViewHolder(CommonViewHolder baseViewHolder, int pos) {
        baseViewHolder.bindView(mIXCoreComponent, this, mDataSet.get(pos), pos);
    }

    @Override
    public int getItemViewType(int position) {
        IDataComponent item = mDataSet.get(position);
        Integer integer = mViewTypeMap.get(item.getViewType());
        if (integer == null) {
            return -1;
        }
        return integer;
    }

    @Override
    public int getItemCount() {
        if (mDataSet != null) {
            return mDataSet.size();
        }
        return 0;
    }

    /**
     * 获取Adapter的数据源
     *
     * @return
     */
    public List<IDataComponent> getDataSet() {
        return mDataSet;
    }

    /**
     * 设置Adapter的数据源
     *
     * @param dataSet
     */
    public void setDataSet(List<IDataComponent> dataSet) {
        this.mDataSet = dataSet;
        notifyDataSetChanged();
    }

    /**
     * get the error view holder
     *
     * @param context
     * @return
     */
    protected CommonViewHolder getDefaultViewHolder(Context context) {
        return new CommonViewHolder(new View(context));
    }

    /**
     * get the unique int type
     *
     * @param name
     * @return
     */
    private int getUniqueIntType(String name) {
        if (TextUtils.isEmpty(name)) {
            return -1;
        }
        int type = name.hashCode();
        while (true) {
            XCoreItemUIComponent old = mConfigurationSparseArray.get(type);
            if (old != null) {
                String oldName = old.getViewType();
                if (!name.equals(oldName)) {
                    type = type + 1;
                } else {
                    return type;
                }
            } else {
                return type;
            }
        }
    }

    /**
     * 注册Item组件
     *
     * @param XCoreItemUIComponent
     * @return
     */
    public XCoreRecyclerAdapter registerItemUIComponent(XCoreItemUIComponent XCoreItemUIComponent) {
        if (XCoreItemUIComponent == null || TextUtils.isEmpty(XCoreItemUIComponent.getViewType())) {
            return this;
        }
        int viewTypeInt = getUniqueIntType(XCoreItemUIComponent.getViewType());
        mViewTypeMap.put(XCoreItemUIComponent.getViewType(), viewTypeInt);
        mConfigurationSparseArray.put(viewTypeInt, XCoreItemUIComponent);
        return this;
    }

    /**
     * 注销配置
     *
     * @param XCoreItemUIComponent
     * @return
     */
    public XCoreRecyclerAdapter unregisterItemUIComponent(XCoreItemUIComponent XCoreItemUIComponent) {
        if (XCoreItemUIComponent == null || TextUtils.isEmpty(XCoreItemUIComponent.getViewType())) {
            return this;
        }
        int index = mConfigurationSparseArray.indexOfValue(XCoreItemUIComponent);
        if (index == -1) {
            return this;
        }
        mConfigurationSparseArray.remove(index);
        return this;
    }

    /**
     * 数据源必须实现的接口
     */
    public interface IDataComponent {
        String getViewType();
    }

    /**
     * 使用CommonViewHolder代理XCoreItemUIComponent组件
     */
    public static class CommonViewHolder extends RecyclerView.ViewHolder {

        private XCoreItemUIComponent XCoreItemUIComponent;

        public void setXCoreItemUIComponent(XCoreItemUIComponent XCoreItemUIComponent) {
            this.XCoreItemUIComponent = XCoreItemUIComponent;
        }

        public XCoreItemUIComponent getXCoreItemUIComponent() {
            return XCoreItemUIComponent;
        }

        public CommonViewHolder(View itemView) {
            super(itemView);
        }

        public void bindView(IXCoreComponent mIXCoreComponent,
                             XCoreRecyclerAdapter XCoreRecyclerAdapter,
                             XCoreRecyclerAdapter.IDataComponent data,
                             int pos) {
            if (XCoreItemUIComponent == null) {
                return;
            }
            XCoreItemUIComponent.bindView(mIXCoreComponent, XCoreRecyclerAdapter, data
                    , pos);
        }

        public void onViewDetachedFromWindow() {
            if (XCoreItemUIComponent != null) {
                XCoreItemUIComponent.onViewDetachedFromWindow();
            }
        }
    }

    @Override
    public void onViewDetachedFromWindow(CommonViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.onViewDetachedFromWindow();
    }
}
