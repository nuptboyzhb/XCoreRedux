package com.example.haibozheng.myapplication.components.item;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.example.haibozheng.myapplication.R;
import com.example.haibozheng.myapplication.actions.ContactsAction;
import com.example.haibozheng.myapplication.helper.UIBinderHelperImpl;
import com.example.haibozheng.myapplication.model.wrapper.ContactsWrapper;
import com.github.nuptboyzhb.xcore.adapter.XCoreItemUIComponent;
import com.github.nuptboyzhb.xcore.adapter.XCoreRecyclerAdapter;
import com.github.nuptboyzhb.xcore.components.IXCoreComponent;
import com.github.nuptboyzhb.xcore.eventbus.XCoreBus;

/**
 * @version mochuan.zhb on 16/9/27.
 * @Author Zheng Haibo
 * @Blog github.com/nuptboyzhb
 * @Company Alibaba Group
 * @Description
 */
public class ImageItemComponent extends XCoreItemUIComponent implements View.OnClickListener {

    private UIBinderHelperImpl mUIBinderHelperImpl;

    private ContactsWrapper mContactsWrapper;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container) {
        return inflater.inflate(R.layout.item_detail, container, false);
    }

    @Override
    public void onViewCreated(View view) {
        mUIBinderHelperImpl = new UIBinderHelperImpl(view);
    }

    @Override
    public String getViewType() {
        return ImageItemComponent.class.getSimpleName();
    }

    @Override
    public void bindView(IXCoreComponent coreComponent,
                         XCoreRecyclerAdapter coreRecyclerAdapter,
                         XCoreRecyclerAdapter.IDataComponent data,
                         int pos) {
        mContactsWrapper = (ContactsWrapper) data;
        mUIBinderHelperImpl.from(R.id.item_content_tv).setText(mContactsWrapper.bindContentText())
                .from(R.id.item_pic_iv).setImageUrl(mContactsWrapper.getAvatarUrl())
                .from(R.id.item_title_tv).setText(mContactsWrapper.bindItemTitle())
                .from(R.id.checkbox).setButtonDrawable(mContactsWrapper.isChecked ? R.mipmap.checkbox_checked : R.mipmap.checkbox_normal)
                .setOnClickListener(this);
    }

    @Override
    public void onViewDetachedFromWindow() {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.checkbox) {
            XCoreBus.getInstance().post(ContactsAction.checkBoxClick(mContactsWrapper));
        }
    }
}
