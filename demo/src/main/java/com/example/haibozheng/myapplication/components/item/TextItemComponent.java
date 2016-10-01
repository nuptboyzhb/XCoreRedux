package com.example.haibozheng.myapplication.components.item;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haibozheng.myapplication.R;
import com.example.haibozheng.myapplication.helper.UIBinderHelperImpl;
import com.example.haibozheng.myapplication.model.wrapper.TitleWrapper;
import com.github.nuptboyzhb.xcore.components.item.XCoreItemUIComponent;
import com.github.nuptboyzhb.xcore.components.XCoreRecyclerAdapter;
import com.github.nuptboyzhb.xcore.components.IXCoreComponent;

/**
 * @version mochuan.zhb on 16/9/27.
 * @Author Zheng Haibo
 * @Blog github.com/nuptboyzhb
 * @Company Alibaba Group
 * @Description
 */
public class TextItemComponent extends XCoreItemUIComponent {

    private UIBinderHelperImpl mUIBinderHelperImpl;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.item_title, container, false);
    }

    @Override
    public void onViewCreated(View view) {
        mUIBinderHelperImpl = new UIBinderHelperImpl(view);
    }

    @Override
    public String getViewType() {
        return TextItemComponent.class.getSimpleName();
    }

    @Override
    public void bindView(IXCoreComponent coreComponent,
                         XCoreRecyclerAdapter coreRecyclerAdapter,
                         XCoreRecyclerAdapter.IDataWrapper data,
                         int pos) {
        TitleWrapper title = (TitleWrapper) data;
        mUIBinderHelperImpl.from(R.id.category_title_tv).setText(title.getCategoryTitle());
    }

    @Override
    public void onViewDetachedFromWindow() {

    }
}
