package com.github.nuptboyzhb.xcore.reducer;

import com.github.nuptboyzhb.xcore.actions.XCoreAction;

/**
 * @version mochuan.zhb on 16/9/28.
 * @Author Zheng Haibo
 * @Blog github.com/nuptboyzhb
 * @Company Alibaba Group
 * @Description reduce interface
 */
public interface IXCoreReducer<State> {
    State reduce(State state, XCoreAction xcoreAction);
}
