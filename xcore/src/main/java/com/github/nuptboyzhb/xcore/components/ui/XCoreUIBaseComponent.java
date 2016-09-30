package com.github.nuptboyzhb.xcore.components.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.github.nuptboyzhb.xcore.components.IXCoreComponent;
import com.github.nuptboyzhb.xcore.eventbus.XCoreBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version mochuan.zhb on 2016/8/16.
 * @Author Zheng Haibo
 * @Blog github.com/nuptboyzhb
 * @Company Alibaba Group
 * @Description UI组件
 */
public abstract class XCoreUIBaseComponent extends FrameLayout implements IXCoreComponent {

    public XCoreUIBaseComponent(Context context) {
        super(context);
        init(context);
    }

    public XCoreUIBaseComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public XCoreUIBaseComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        XCoreBus.getInstance().registerComponent(this);
        onCreateView(context);
        onViewCreated(context);
    }

    private void onCreateView(Context context) {
        int layoutId = getLayoutResId();
        try {
            if (layoutId <= 0) {
                return;
            }
            inflate(context, layoutId, this);
        } catch (Exception e) {
            e.printStackTrace();
            showErrorView(layoutId);
        }
    }

    private void showErrorView(int layoutId) {
        TextView textView = new TextView(getContext());
        textView.setText(String.format("inflate layout(%s) id failed.", layoutId));
        addView(textView);
    }

    public abstract int getLayoutResId();

    public abstract void onViewCreated(Context context);

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        onDestroy();
    }

    public void onDestroy() {
        XCoreBus.getInstance().unregisterComponent(this);
    }

}
