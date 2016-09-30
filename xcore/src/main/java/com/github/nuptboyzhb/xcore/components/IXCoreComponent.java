package com.github.nuptboyzhb.xcore.components;


import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @version mochuan.zhb on 2016/8/16.
 * @Author Zheng Haibo
 * @Blog github.com/nuptboyzhb
 * @Company Alibaba Group
 * @Description 组件接口
 */
public interface IXCoreComponent {

    View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container);

}
