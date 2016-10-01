package com.example.haibozheng.myapplication.components.container;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.example.haibozheng.myapplication.R;
import com.example.haibozheng.myapplication.actions.ContactsActionCreator;
import com.example.haibozheng.myapplication.helper.UIBinderHelperImpl;
import com.example.haibozheng.myapplication.model.Contacts;
import com.example.haibozheng.myapplication.model.Title;
import com.github.nuptboyzhb.xcore.components.XCoreRecyclerAdapter;
import com.github.nuptboyzhb.xcore.components.XCoreUIBaseComponent;
import com.github.nuptboyzhb.xcore.eventbus.XCoreBus;
import com.github.nuptboyzhb.xcore.stores.XCoreStore;

import java.util.List;

/**
 * @version mochuan.zhb on 16/9/28.
 * @Author Zheng Haibo
 * @Blog github.com/nuptboyzhb
 * @Company Alibaba Group
 * @Description RecyclerViewActivity 头部UI组件
 */
public class HeaderComponent extends XCoreUIBaseComponent implements
        XCoreStore.IStateChangeListener<List<XCoreRecyclerAdapter.IDataWrapper>>
        , View.OnClickListener {

    private UIBinderHelperImpl mUIBinderHelperImpl;

    public HeaderComponent(Context context) {
        super(context);
    }

    public HeaderComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HeaderComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.reycler_view_header;
    }

    @Override
    public void onViewCreated(View view) {
        mUIBinderHelperImpl = new UIBinderHelperImpl(getRootView());
        mUIBinderHelperImpl.from(R.id.add_contacts).setOnClickListener(this)
                .from(R.id.add_title).setOnClickListener(this)
                .from(R.id.delete_last).setOnClickListener(this);
    }

    @Override
    public void onStateChanged(List<XCoreRecyclerAdapter.IDataWrapper> state) {
        mUIBinderHelperImpl.from(R.id.list_count_tv).setText(String.format("count = %s", state.size()));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.add_contacts) {
            XCoreBus.getInstance().post(ContactsActionCreator.addContacts(new Contacts("李华", "156 6666 6666", "浙江省杭州市西湖区xxx号")));
        } else if (view.getId() == R.id.add_title) {
            XCoreBus.getInstance().post(ContactsActionCreator.addCategory(new Title("浙江省杭州市")));
        } else if (view.getId() == R.id.delete_last) {
            XCoreBus.getInstance().post(ContactsActionCreator.deleteLast());
        }
    }
}
