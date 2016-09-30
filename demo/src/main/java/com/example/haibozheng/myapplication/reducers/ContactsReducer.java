package com.example.haibozheng.myapplication.reducers;

import com.example.haibozheng.myapplication.actions.ContactsAction;
import com.example.haibozheng.myapplication.model.Contacts;
import com.example.haibozheng.myapplication.model.Title;
import com.example.haibozheng.myapplication.model.wrapper.ContactsWrapper;
import com.example.haibozheng.myapplication.model.wrapper.TitleWrapper;
import com.github.nuptboyzhb.xcore.actions.XCoreAction;
import com.github.nuptboyzhb.xcore.adapter.XCoreRecyclerAdapter;
import com.github.nuptboyzhb.xcore.reducer.IXCoreReducer;

import java.util.ArrayList;
import java.util.List;

/**
 * @version mochuan.zhb on 16/9/28.
 * @Author Zheng Haibo
 * @Blog github.com/nuptboyzhb
 * @Company Alibaba Group
 * @Description 联系人的Reduce
 */
public class ContactsReducer implements IXCoreReducer<List<XCoreRecyclerAdapter.IDataComponent>> {

    /**
     * 添加
     *
     * @param contactsWrappers
     * @param contacts
     * @return
     */
    private List<XCoreRecyclerAdapter.IDataComponent> addOneContacts(List<XCoreRecyclerAdapter.IDataComponent> contactsWrappers, Contacts contacts) {
        List<XCoreRecyclerAdapter.IDataComponent> wrappers = new ArrayList<>(contactsWrappers);
        ContactsWrapper contactsWrapper = new ContactsWrapper();
        contactsWrapper.setContacts(contacts);
        wrappers.add(contactsWrapper);
        return wrappers;
    }

    /**
     * 添加
     *
     * @param contactsWrappers
     * @param value
     * @return
     */
    private List<XCoreRecyclerAdapter.IDataComponent> addOneTitle(List<XCoreRecyclerAdapter.IDataComponent> contactsWrappers, Title value) {
        List<XCoreRecyclerAdapter.IDataComponent> wrappers = new ArrayList<>(contactsWrappers);
        TitleWrapper titleWrapper = new TitleWrapper();
        titleWrapper.setTitle(value);
        wrappers.add(titleWrapper);
        return wrappers;
    }

    /**
     * 删除最后一个
     *
     * @param contactsWrappers
     * @return
     */
    private List<XCoreRecyclerAdapter.IDataComponent> deleteLast(List<XCoreRecyclerAdapter.IDataComponent> contactsWrappers) {
        List<XCoreRecyclerAdapter.IDataComponent> wrappers = new ArrayList<>(contactsWrappers);
        if (wrappers.size() > 0) {
            wrappers.remove(wrappers.size() - 1);
        }
        return wrappers;
    }

    /**
     * 设置选择状态
     *
     * @param contactsWrappers
     * @param value
     * @return
     */
    private List<XCoreRecyclerAdapter.IDataComponent> changeCheckBoxStatus(List<XCoreRecyclerAdapter.IDataComponent> contactsWrappers, ContactsWrapper value) {
        value.isChecked = !value.isChecked;
        return contactsWrappers;
    }

    @Override
    public List<XCoreRecyclerAdapter.IDataComponent> reduce(List<XCoreRecyclerAdapter.IDataComponent> contactsWrappers, XCoreAction XCoreAction) {
        switch (XCoreAction.type) {
            case ContactsAction.ADD_ITEM:
                return addOneContacts(contactsWrappers, (Contacts) XCoreAction.value);

            case ContactsAction.ADD_TITLE:
                return addOneTitle(contactsWrappers, (Title) XCoreAction.value);

            case ContactsAction.DELETE_LAST:
                return deleteLast(contactsWrappers);

            case ContactsAction.CHECK_BOX:
                return changeCheckBoxStatus(contactsWrappers, (ContactsWrapper) XCoreAction.value);
        }
        return contactsWrappers;
    }
}
