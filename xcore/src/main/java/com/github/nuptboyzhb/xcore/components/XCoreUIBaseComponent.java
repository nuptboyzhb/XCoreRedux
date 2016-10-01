package com.github.nuptboyzhb.xcore.components;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.github.nuptboyzhb.xcore.components.IXCoreComponent;
import com.github.nuptboyzhb.xcore.eventbus.XCoreBus;

/**
 * @version mochuan.zhb on 2016/8/16.
 * @Author Zheng Haibo
 * @Blog github.com/nuptboyzhb
 * @Company Alibaba Group
 * @Description UI组件抽象类:普通组件
 */
public abstract class XCoreUIBaseComponent extends FrameLayout implements IXCoreComponent {

    public XCoreUIBaseComponent(Context context) {
        super(context);
        init();
    }

    public XCoreUIBaseComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public XCoreUIBaseComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected void init() {
        XCoreBus.getInstance().registerComponent(this);
        onCreateView(null, this);
        onViewCreated(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container) {
        int layoutId = getLayoutResId();
        try {
            if (layoutId <= 0) {
                return showErrorView(layoutId);
            }
            inflate(getContext(), layoutId, container);
        } catch (Throwable e) {
            e.printStackTrace();
            showErrorView(layoutId);
        }
        return this;
    }

    protected View showErrorView(int layoutId) {
        TextView textView = new TextView(getContext());
        textView.setText(String.format("inflate layout(%s) id failed.", layoutId));
        addView(textView);
        return this;
    }

    /**
     * 布局的layout资源ID
     *
     * @return
     */
    public abstract int getLayoutResId();

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        onDestroy();
    }

    protected void onDestroy() {
        XCoreBus.getInstance().unregisterComponent(this);
    }

}
