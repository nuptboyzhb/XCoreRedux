package com.example.haibozheng.myapplication.actions;

import com.example.haibozheng.myapplication.model.Contacts;
import com.example.haibozheng.myapplication.model.Title;
import com.example.haibozheng.myapplication.model.wrapper.ContactsWrapper;
import com.github.nuptboyzhb.xcore.actions.XCoreAction;

/**
 * @version mochuan.zhb on 16/9/28.
 * @Author Zheng Haibo
 * @Blog github.com/nuptboyzhb
 * @Company Alibaba Group
 * @Description 联系人 ActionCreator
 */
public class ContactsActionCreator {

    public static final String ADD_ITEM = "AddContacts";
    public static final String ADD_TITLE = "addCategory";
    public static final String DELETE_LAST = "deleteLast";
    public static final String CHECK_BOX = "contactsCheck";

    public static XCoreAction addContacts(Contacts contacts) {
        return new XCoreAction(ADD_ITEM, contacts);
    }

    public static XCoreAction addCategory(Title title) {
        return new XCoreAction(ADD_TITLE, title);
    }

    public static XCoreAction deleteLast() {
        return new XCoreAction(DELETE_LAST);
    }

    public static XCoreAction checkBoxClick(ContactsWrapper contactsWrapper) {
        return new XCoreAction(CHECK_BOX, contactsWrapper);
    }
}
