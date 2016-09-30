package com.github.nuptboyzhb.xcore.adapter;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.nuptboyzhb.xcore.components.IXCoreComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version mochuan.zhb on 2016/8/12.
 * @Author Zheng Haibo
 * @Blog github.com/nuptboyzhb
 * @Company Alibaba Group
 * @Description item component 抽象类
 */
public abstract class XCoreItemUIComponent implements IXCoreComponent {

    /**
     * 创建View
     *
     * @param inflater
     * @param container
     * @return
     */
    public abstract View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container);

    /**
     * 在此做View的初始化操作
     *
     * @param view
     */
    public abstract void onViewCreated(View view);

    /**
     * item组件支持的ViewType,与IDataComponent的getViewType对应
     *
     * @return
     */
    public abstract String getViewType();

    /**
     * 绑定数据-频繁回调
     *
     * @param coreComponent       外出UI组件
     * @param coreRecyclerAdapter Adapter
     * @param data                对应的数据源
     * @param pos                 item所在列表的位置
     */
    public abstract void bindView(IXCoreComponent coreComponent,
                                  XCoreRecyclerAdapter coreRecyclerAdapter,
                                  XCoreRecyclerAdapter.IDataComponent data,
                                  int pos);

    /**
     * item组件销毁时调用
     */
    public abstract void onViewDetachedFromWindow();

    //必须有无参数的构造函数
    public View itemView;

    public void setItemView(View itemView) {
        this.itemView = itemView;
        onViewCreated(itemView);
    }

}
