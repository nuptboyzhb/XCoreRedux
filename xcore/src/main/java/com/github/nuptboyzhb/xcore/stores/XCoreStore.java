package com.github.nuptboyzhb.xcore.stores;

import com.github.nuptboyzhb.xcore.actions.XCoreAction;
import com.github.nuptboyzhb.xcore.reducer.IXCoreReducer;

import java.util.ArrayList;
import java.util.List;

/**
 * @version mochuan.zhb on 16/9/28.
 * @Author Zheng Haibo
 * @Blog github.com/nuptboyzhb
 * @Company Alibaba Group
 * @Description store: 分发action、保存数据源state
 */
public class XCoreStore<State> {
    private final IXCoreReducer<State> mIXCoreReducer;//数据处理器-reducer
    private final List<IStateChangeListener<State>> listeners = new ArrayList<>();//观察者
    private volatile State state;//Store存储的数据

    private XCoreStore(IXCoreReducer<State> mIXCoreReducer, State state) {
        this.mIXCoreReducer = mIXCoreReducer;
        this.state = state;
    }

    /**
     * 内部dispatch
     *
     * @param xCoreAction
     */
    private void dispatchAction(final XCoreAction xCoreAction) throws Throwable {
        synchronized (this) {
            state = mIXCoreReducer.reduce(state, xCoreAction);
        }
        for (IStateChangeListener<State> listener : listeners) {
            listener.onStateChanged(state);
        }
    }


    /**
     * 创建Store
     *
     * @param reducer
     * @param initialState
     * @param <S>
     * @return
     */
    public static <S> XCoreStore<S> create(IXCoreReducer<S> reducer, S initialState) {
        return new XCoreStore<>(reducer, initialState);
    }

    public State getState() {
        return state;
    }


    public void dispatch(final XCoreAction action) {
        try {
            dispatchAction(action);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void dispatchAsync(final XCoreAction action, final CallBack callBack) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dispatch(action);
                if (callBack != null) {
                    callBack.onFinished();
                }
            }
        }).start();
    }

    public void subscribe(final IStateChangeListener<State> listener) {
        listeners.add(listener);
    }

    public interface IStateChangeListener<S> {
        void onStateChanged(S state);
    }

    public interface CallBack {
        void onFinished();
    }

}
