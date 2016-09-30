package com.github.nuptboyzhb.xcore.eventbus;


import com.github.nuptboyzhb.xcore.components.IXCoreComponent;
import com.squareup.otto.Bus;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @version mochuan.zhb on 2016/8/16.
 * @Author Zheng Haibo
 * @Blog github.com/nuptboyzhb
 * @Company Alibaba Group
 * @Description 组件之间通信
 */
public class XCoreBus {

    private static XCoreBus sXCoreBus;
    private Bus mBus = null;


    private XCoreBus() {
        mBus = new Bus();
    }

    public static XCoreBus getInstance() {
        synchronized (XCoreBus.class) {
            if (sXCoreBus == null) {
                sXCoreBus = new XCoreBus();
            }
        }
        return sXCoreBus;
    }

    public void registerComponent(Object object) {
        if (object == null) {
            return;
        }
        mBus.register(object);
    }

    public void unregisterComponent(Object object) {
        if (object == null) {
            return;
        }
        mBus.unregister(object);
    }

    public void post(Object event) {
        mBus.post(event);
    }

}
