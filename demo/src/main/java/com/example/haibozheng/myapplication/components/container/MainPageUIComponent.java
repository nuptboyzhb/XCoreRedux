package com.example.haibozheng.myapplication.components.container;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;

import com.example.haibozheng.myapplication.R;
import com.example.haibozheng.myapplication.RecyclerViewActivity;
import com.github.nuptboyzhb.xcore.components.ui.XCoreUIBaseComponent;

/**
 * @version mochuan.zhb on 16/9/15.
 * @Author Zheng Haibo
 * @Blog github.com/nuptboyzhb
 * @Company Alibaba Group
 * @Description 首页UI组件
 */
public class MainPageUIComponent extends XCoreUIBaseComponent {

    public MainPageUIComponent(Context context) {
        super(context);
    }

    public MainPageUIComponent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MainPageUIComponent(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.xcore_activity_main;
    }

    @Override
    public void onViewCreated(Context context) {
        findViewById(R.id.text_hello).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getContext().startActivity(new Intent(view.getContext(), RecyclerViewActivity.class));
            }
        });
    }

    @Override
    public String getComponentId() {
        return "main_page";
    }
}
