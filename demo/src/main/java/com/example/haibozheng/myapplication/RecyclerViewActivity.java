package com.example.haibozheng.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.haibozheng.myapplication.actions.ContactsActionCreator;
import com.example.haibozheng.myapplication.components.container.HeaderComponent;
import com.example.haibozheng.myapplication.components.item.ImageItemComponent;
import com.example.haibozheng.myapplication.components.item.TextItemComponent;
import com.example.haibozheng.myapplication.model.Contacts;
import com.example.haibozheng.myapplication.model.Title;
import com.example.haibozheng.myapplication.reducers.ContactsReducer;
import com.github.nuptboyzhb.xcore.actions.XCoreAction;
import com.github.nuptboyzhb.xcore.components.XCoreRecyclerAdapter;
import com.github.nuptboyzhb.xcore.components.impl.XCoreRecyclerViewComponent;
import com.github.nuptboyzhb.xcore.eventbus.XCoreBus;
import com.github.nuptboyzhb.xcore.stores.XCoreStore;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * @version mochuan.zhb on 16/9/27.
 * @Author Zheng Haibo
 * @Blog github.com/nuptboyzhb
 * @Company Alibaba Group
 * @Description 列表ActivityDemo
 */
public class RecyclerViewActivity extends AppCompatActivity {

    private XCoreRecyclerViewComponent mXCoreRecyclerViewComponent;
    private HeaderComponent mHeaderComponent;

    private XCoreStore<List<XCoreRecyclerAdapter.IDataWrapper>> mContactsListXCoreStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XCoreBus.getInstance().registerComponent(this);
        setContentView(R.layout.recycler_view_activity);

        //创建数据源的store
        mContactsListXCoreStore = XCoreStore.create(new ContactsReducer(), new ArrayList<XCoreRecyclerAdapter.IDataWrapper>());

        //创建RecyclerView的UI组件
        mXCoreRecyclerViewComponent = (XCoreRecyclerViewComponent) findViewById(R.id.recycler_view_component);
        //注册item组件模板
        mXCoreRecyclerViewComponent.registerItemComponent(new TextItemComponent())
                .registerItemComponent(new ImageItemComponent());

        //创建头部组件
        mHeaderComponent = (HeaderComponent) findViewById(R.id.recycler_view_header_component);

        //添加观察者
        mContactsListXCoreStore.subscribe(mXCoreRecyclerViewComponent);
        mContactsListXCoreStore.subscribe(mHeaderComponent);
        test();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        XCoreBus.getInstance().unregisterComponent(this);
    }

    @Subscribe
    public void transportAction(XCoreAction action) {
        mContactsListXCoreStore.dispatch(action);
    }

    private void test() {
        mContactsListXCoreStore.dispatch(ContactsActionCreator.addCategory(new Title("浙江省")));
        mContactsListXCoreStore.dispatch(ContactsActionCreator.addContacts(new Contacts("李华", "156 6666 6666", "浙江省杭州市西湖区xxx号")));
        mContactsListXCoreStore.dispatch(ContactsActionCreator.addContacts(new Contacts("李华2", "157 7777 7777", "浙江省杭州市西湖区xxx号")));
    }


}
