package com.github.nuptboyzhb.xcore.actions;

/**
 * @version mochuan.zhb on 16/9/28.
 * @Author Zheng Haibo
 * @Blog github.com/nuptboyzhb
 * @Company Alibaba Group
 * @Description action
 */
public class XCoreAction {

    public final String type;
    public final Object value;

    public XCoreAction(String type, Object value) {
        this.type = type;
        this.value = value;
    }


    public XCoreAction(String type) {
        this(type, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        XCoreAction XCoreAction = (XCoreAction) o;

        if (type != null ? !type.equals(XCoreAction.type) : XCoreAction.type != null) return false;
        return value != null ? value.equals(XCoreAction.value) : XCoreAction.value == null;

    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
