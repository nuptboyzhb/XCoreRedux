package com.example.haibozheng.myapplication.model.wrapper;

import com.example.haibozheng.myapplication.R;
import com.example.haibozheng.myapplication.components.item.ImageItemComponent;
import com.example.haibozheng.myapplication.model.Contacts;
import com.github.nuptboyzhb.xcore.adapter.XCoreRecyclerAdapter;

/**
 * @version mochuan.zhb on 16/9/28.
 * @Author Zheng Haibo
 * @Blog github.com/nuptboyzhb
 * @Company Alibaba Group
 * @Description 数据模型的包装类
 */
public class ContactsWrapper implements XCoreRecyclerAdapter.IDataComponent {

    private Contacts contacts;

    public Contacts getContacts() {
        return contacts;
    }

    public boolean isChecked = false;

    public void setContacts(Contacts contacts) {
        this.contacts = contacts;
    }

    @Override
    public String getViewType() {
        return ImageItemComponent.class.getSimpleName();
    }

    public String bindContentText() {
        return String.format("电话:%s 住址:%s", contacts.getTelephone(), contacts.getAddress());
    }

    public String bindItemTitle() {
        return String.format("姓名: %s", contacts.getName());
    }

    public String getAvatarUrl() {
        return contacts.getAvatarUrl();
    }

    @Override
    public String toString() {
        return "ContactsWrapper{" +
                "contacts=" + contacts +
                '}';
    }

    public int getAvatarResId() {
        return R.drawable.contacts;
    }
}
