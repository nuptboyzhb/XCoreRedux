package com.github.nuptboyzhb.xcore.helper;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @version mochuan.zhb on 16/9/28.
 * @Author Zheng Haibo
 * @Blog github.com/nuptboyzhb
 * @Company Alibaba Group
 * @Description 链式数据绑定 Data Binding Helper
 */
public abstract class XCoreUIBinderHelper {

    public final View rootView;

    private SparseArray<View> mCacheView = new SparseArray<View>();

    private int currentViewId = -1;

    public XCoreUIBinderHelper(View view) {
        this.rootView = view;
    }

    public XCoreUIBinderHelper from(int viewId) {
        this.currentViewId = viewId;
        mCacheView.put(viewId, rootView.findViewById(viewId));
        return this;
    }

    public XCoreUIBinderHelper end() {
        this.currentViewId = -1;
        return this;
    }

    /**
     * 获取当前的View
     *
     * @return
     */
    public View getCurrentView() {
        return ensureView();
    }

    public View getRootView() {
        return rootView;
    }

    protected View ensureView(int viewId) {
        this.currentViewId = viewId;
        View view = mCacheView.get(viewId);
        if (view == null) {
            view = rootView.findViewById(viewId);
        }
        if (view != null) {
            mCacheView.put(viewId, view);
        }
        if (view == null && currentViewId == -1) {
            return rootView;
        }
        return view;
    }

    protected View ensureView() {
        return ensureView(currentViewId);
    }

    public XCoreUIBinderHelper setText(String content) {
        View view = ensureView();
        if (view == null) {
            return this;
        }
        if (view instanceof TextView) {
            if (!TextUtils.isEmpty(content)) {
                ((TextView) view).setText(content);
            } else if ("".equals(content)) {
                ((TextView) view).setText("");
            }
        }
        return this;
    }

    public XCoreUIBinderHelper setVisibleOrInVisible(boolean visibleOrInVisible) {
        View view = ensureView();
        if (view == null) {
            return this;
        }
        view.setVisibility(visibleOrInVisible ? View.VISIBLE : View.INVISIBLE);
        return this;
    }

    public XCoreUIBinderHelper setVisibleOrGone(boolean visibleOrGone) {
        View view = ensureView();
        if (view == null) {
            return this;
        }
        view.setVisibility(visibleOrGone ? View.VISIBLE : View.GONE);
        return this;
    }

    public XCoreUIBinderHelper setVisibilityByEmpty(String content) {
        View view = ensureView();
        if (view == null) {
            return this;
        }
        view.setVisibility(TextUtils.isEmpty(content) ? View.GONE : View.VISIBLE);
        return this;
    }

    public XCoreUIBinderHelper setEnabled(boolean enable) {
        View view = ensureView();
        if (view == null) {
            return this;
        }
        view.setEnabled(enable);
        return this;
    }

    public XCoreUIBinderHelper setChecked(boolean checked) {
        View view = ensureView();
        if (view == null) {
            return this;
        }
        if (view instanceof CompoundButton) {
            ((CompoundButton) view).setChecked(checked);
        }
        return this;
    }

    public XCoreUIBinderHelper setOnClickListener(final View.OnClickListener onClickListener) {
        View view = ensureView();
        if (view == null) {
            return this;
        }
        if (onClickListener == null) {
            view.setOnClickListener(null);
        } else {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClick(v);
                }
            });
        }
        return this;
    }

    public XCoreUIBinderHelper setOnLongClickListener(final View.OnLongClickListener onLongClickListener) {
        View view = ensureView();
        if (view == null) {
            return this;
        }
        if (onLongClickListener == null) {
            view.setOnClickListener(null);
        } else {
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onLongClickListener.onLongClick(view);
                    return true;
                }
            });
        }
        return this;
    }

    /**
     * setOnCheckedChangeListener for compoundButton
     *
     * @param onCheckedChangeListener
     * @return
     */
    public XCoreUIBinderHelper setOnCheckedChangeListener(final CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        View view = ensureView();
        if (view == null) {
            return this;
        }
        if (view instanceof CompoundButton) {
            ((CompoundButton) view).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (onCheckedChangeListener != null) {
                        onCheckedChangeListener.onCheckedChanged(buttonView, isChecked);
                    }
                }
            });
        }
        return this;
    }

    public XCoreUIBinderHelper setBackgroundColor(int color) {
        View view = ensureView();
        if (view == null) {
            return this;
        }
        view.setBackgroundColor(color);
        return this;
    }

    public XCoreUIBinderHelper setButtonDrawable(int resId) {
        View view = ensureView();
        if (view == null) {
            return this;
        }
        if (view instanceof CompoundButton) {
            CompoundButton compoundButton = (CompoundButton) view;
            compoundButton.setButtonDrawable(resId);
        }
        return this;
    }

    public XCoreUIBinderHelper setBackgroundDrawable(Drawable background) {
        View view = ensureView();
        if (view == null) {
            return this;
        }
        view.setBackgroundDrawable(background);
        return this;
    }

    public XCoreUIBinderHelper setImageResource(int resId) {
        View view = ensureView();
        if (view == null) {
            return this;
        }
        if (view instanceof ImageView) {
            ((ImageView) view).setImageResource(resId);
        }
        return this;
    }

    public XCoreUIBinderHelper setImageBitmap(Bitmap bitmap) {
        View view = ensureView();
        if (view == null) {
            return this;
        }
        if (view instanceof ImageView) {
            ((ImageView) view).setImageBitmap(bitmap);
        }
        return this;
    }

    /**
     * 设置删除线
     *
     * @return
     */
    public XCoreUIBinderHelper setDeleteLine() {
        View view = ensureView();
        if (view == null) {
            return this;
        }
        if (view instanceof TextView) {
            ((TextView) view).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }
        return this;
    }

    public boolean isChecked() {
        View view = ensureView();
        if (view == null) {
            return false;
        }
        if (view instanceof CompoundButton) {
            return ((CompoundButton) view).isChecked();
        }
        return false;
    }

    public CharSequence getText() {
        View view = ensureView();
        if (view == null) {
            return null;
        }
        if (view instanceof TextView) {
            return ((TextView) view).getText();
        }
        return null;
    }

    public XCoreUIBinderHelper addView(View childView) {
        View view = ensureView();
        if (view == null) {
            return this;
        }
        if (view instanceof ViewGroup) {
            ((ViewGroup) view).addView(childView);
        }
        return this;
    }

    public XCoreUIBinderHelper removeAllViews() {
        View view = ensureView();
        if (view == null) {
            return this;
        }
        if (view instanceof ViewGroup) {
            ((ViewGroup) view).removeAllViews();
        }
        return this;
    }

    /**
     * 设置TextView的Drawable是否可见，比如drawableLeft，drawableRight等
     *
     * @param isVisible 是否可见
     * @param pos       drawable的位置，只能是0,1,2,3 分别是左上右下
     * @return
     */
    public XCoreUIBinderHelper setTextViewDrawableVisible(boolean isVisible, int pos) {
        View view = ensureView();
        if (view == null) {
            return this;
        }
        if (view instanceof TextView) {
            Drawable[] drawables = ((TextView) view).getCompoundDrawables();
            if (drawables.length == 4 && pos >= 0 && pos < 4) {
                Drawable drawable = drawables[pos];
                if (drawable != null) {
                    drawable.setAlpha(isVisible ? 255 : 0);
                }
            }
        }
        return this;
    }

    public abstract XCoreUIBinderHelper setImageUrl(final String url);

}