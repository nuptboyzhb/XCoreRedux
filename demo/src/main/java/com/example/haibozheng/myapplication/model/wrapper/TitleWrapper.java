package com.example.haibozheng.myapplication.model.wrapper;

import com.example.haibozheng.myapplication.components.item.TextItemComponent;
import com.example.haibozheng.myapplication.model.Title;
import com.github.nuptboyzhb.xcore.adapter.XCoreRecyclerAdapter;

/**
 * @version mochuan.zhb on 16/9/28.
 * @Author Zheng Haibo
 * @Blog github.com/nuptboyzhb
 * @Company Alibaba Group
 * @Description 包装类
 */
public class TitleWrapper implements XCoreRecyclerAdapter.IDataComponent {

    private Title title;

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    @Override
    public String getViewType() {
        return TextItemComponent.class.getSimpleName();
    }

    public String getCategoryTitle() {
        return title.getCategoryTitle();
    }

    @Override
    public String toString() {
        return "TitleWrapper{" +
                "title=" + title +
                '}';
    }
}
