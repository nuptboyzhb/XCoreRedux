package com.github.nuptboyzhb.xcore.components.item;

import android.view.View;

import com.github.nuptboyzhb.xcore.components.IXCoreComponent;
import com.github.nuptboyzhb.xcore.components.XCoreRecyclerAdapter;

/**
 * @version mochuan.zhb on 2016/8/12.
 * @Author Zheng Haibo
 * @Blog github.com/nuptboyzhb
 * @Company Alibaba Group
 * @Description item组件 component 抽象类
 */
public abstract class XCoreItemUIComponent implements IXCoreComponent {

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
                                  XCoreRecyclerAdapter.IDataWrapper data,
                                  int pos);

    /**
     * item组件销毁时调用
     */
    public void onViewDetachedFromWindow() {
        //TODO
    }

    //必须有无参数的构造函数
    public View itemView;

    public void setItemView(View itemView) {
        this.itemView = itemView;
        onViewCreated(itemView);
    }

}
