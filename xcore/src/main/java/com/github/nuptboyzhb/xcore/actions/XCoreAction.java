package com.github.nuptboyzhb.xcore.actions;

/**
 * @version mochuan.zhb on 16/9/28.
 * @Author Zheng Haibo
 * @Blog github.com/nuptboyzhb
 * @Company Alibaba Group
 * @Description XCoreRedux框架的action类
 */
public class XCoreAction {

    //Action的类型
    public final String type;
    //Action携带的value,可为空
    public final Object value;

    public XCoreAction(String type, Object value) {
        this.type = type;
        this.value = value;
    }

    public XCoreAction(String type) {
        this(type, null);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        XCoreAction xCoreAction = (XCoreAction) object;

        if (type != null ? !type.equals(xCoreAction.type) : xCoreAction.type != null) {
            return false;
        }
        return value != null ? value.equals(xCoreAction.value) : xCoreAction.value == null;

    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
