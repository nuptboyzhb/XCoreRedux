package com.example.haibozheng.myapplication.reducers;

import com.example.haibozheng.myapplication.actions.ContactsActionCreator;
import com.example.haibozheng.myapplication.model.Contacts;
import com.example.haibozheng.myapplication.model.Title;
import com.example.haibozheng.myapplication.model.wrapper.ContactsWrapper;
import com.example.haibozheng.myapplication.model.wrapper.TitleWrapper;
import com.github.nuptboyzhb.xcore.actions.XCoreAction;
import com.github.nuptboyzhb.xcore.components.XCoreRecyclerAdapter;
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
public class ContactsReducer implements IXCoreReducer<List<XCoreRecyclerAdapter.IDataWrapper>> {

    /**
     * 添加联系人
     *
     * @param contactsWrappers
     * @param contacts
     * @return
     */
    private List<XCoreRecyclerAdapter.IDataWrapper> addOneContacts(List<XCoreRecyclerAdapter.IDataWrapper> contactsWrappers, Contacts contacts) {
        List<XCoreRecyclerAdapter.IDataWrapper> wrappers = new ArrayList<>(contactsWrappers);
        ContactsWrapper contactsWrapper = new ContactsWrapper();
        contactsWrapper.setContacts(contacts);
        wrappers.add(contactsWrapper);
        return wrappers;
    }

    /**
     * 添加标题
     *
     * @param contactsWrappers
     * @param value
     * @return
     */
    private List<XCoreRecyclerAdapter.IDataWrapper> addOneTitle(List<XCoreRecyclerAdapter.IDataWrapper> contactsWrappers, Title value) {
        List<XCoreRecyclerAdapter.IDataWrapper> wrappers = new ArrayList<>(contactsWrappers);
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
    private List<XCoreRecyclerAdapter.IDataWrapper> deleteLast(List<XCoreRecyclerAdapter.IDataWrapper> contactsWrappers) {
        List<XCoreRecyclerAdapter.IDataWrapper> wrappers = new ArrayList<>(contactsWrappers);
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
    private List<XCoreRecyclerAdapter.IDataWrapper> changeCheckBoxStatus(List<XCoreRecyclerAdapter.IDataWrapper> contactsWrappers, ContactsWrapper value) {
        value.isChecked = !value.isChecked;
        return contactsWrappers;
    }

    @Override
    public List<XCoreRecyclerAdapter.IDataWrapper> reduce(List<XCoreRecyclerAdapter.IDataWrapper> contactsWrappers, XCoreAction xcoreAction) {
        switch (xcoreAction.type) {
            case ContactsActionCreator.ADD_ITEM:
                return addOneContacts(contactsWrappers, (Contacts) xcoreAction.value);

            case ContactsActionCreator.ADD_TITLE:
                return addOneTitle(contactsWrappers, (Title) xcoreAction.value);

            case ContactsActionCreator.DELETE_LAST:
                return deleteLast(contactsWrappers);

            case ContactsActionCreator.CHECK_BOX:
                return changeCheckBoxStatus(contactsWrappers, (ContactsWrapper) xcoreAction.value);
        }
        return contactsWrappers;
    }
}
