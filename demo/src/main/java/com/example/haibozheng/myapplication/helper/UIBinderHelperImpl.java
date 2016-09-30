package com.example.haibozheng.myapplication.helper;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.github.nuptboyzhb.xcore.helper.XCoreUIBinderHelper;
import com.squareup.picasso.Picasso;

/**
 * @version mochuan.zhb on 16/9/28.
 * @Author Zheng Haibo
 * @Blog github.com/nuptboyzhb
 * @Company Alibaba Group
 * @Description Data Binding Manager
 */
public class UIBinderHelperImpl extends XCoreUIBinderHelper {

    public UIBinderHelperImpl(View view) {
        super(view);
    }

    @Override
    public XCoreUIBinderHelper setImageUrl(String url) {
        View view = ensureView();
        if (view == null) {
            return this;
        }
        if (view instanceof ImageView && !TextUtils.isEmpty(url)) {
            Picasso.with(view.getContext()).load(url).into((ImageView) view);
        }
        return this;
    }
}