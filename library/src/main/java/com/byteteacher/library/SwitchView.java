package com.byteteacher.library;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.annotation.ColorInt;

import java.lang.reflect.Method;

/**
 * 通用的开关按钮，可以设置三个部位的颜色
 * 关闭状态的背景颜色
 * 开启状态的背景颜色
 * 圆形指示器的颜色
 * 1、switch控件不支持设置宽度，控件会根据thumb大小自动按比例改变大小，所以只需要设置thumb大小即可。
 */
public class SwitchView extends LinearLayout {

    private static final String TAG = "cj";
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch switchView;

    public SwitchView(Context context) {
        this(context, null);
    }

    public SwitchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public SwitchView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SwitchView, defStyleAttr, defStyleRes);
        int thumbColor = typedArray.getColor(R.styleable.SwitchView_thumbColor, Color.WHITE);
        int checkedColor = typedArray.getColor(R.styleable.SwitchView_trackViewCheckedColor, Color.parseColor("#14C893"));
        int unCheckedColor = typedArray.getColor(R.styleable.SwitchView_trackViewUncheckedColor, Color.parseColor("#DBDBDB"));
        int size = typedArray.getDimensionPixelSize(R.styleable.SwitchView_thumbSize, dp2px(30.0F));
        boolean checked = typedArray.getBoolean(R.styleable.SwitchView_checked, false);
        setThumbColor(thumbColor);
        setThumbSize(size);
        setTrackColor(unCheckedColor, checkedColor);
        setChecked(checked);
        typedArray.recycle();
    }

    /**
     * 初始化为通用样式
     */
    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.switchview, this, true);
        switchView = findViewById(R.id.switchview);
    }


    public boolean isChecked() {
        return switchView.isChecked();
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckChangeListener) {
        switchView.setOnCheckedChangeListener(onCheckChangeListener);
    }

    public void setThumbColor(@ColorInt int colorInt) {
        GradientDrawable thumbDrawable = (GradientDrawable) switchView.getThumbDrawable();
        thumbDrawable.setColor(colorInt);
    }

    public void setTrackColor(@ColorInt int unCheckedColor, @ColorInt int checkedColor) {

        //selector对应的是StateListDrawable
        StateListDrawable trackDrawable = (StateListDrawable) switchView.getTrackDrawable();

        int checked = android.R.attr.state_checked;
//        Log.e(TAG, "checked: " + checked);

        Class<StateListDrawable> slDraClass = StateListDrawable.class;
        try {
            Method getStateCountMethod = slDraClass.getDeclaredMethod("getStateCount",  (Class<?>[])null);
            Method getStateSetMethod = slDraClass.getDeclaredMethod("getStateSet", int.class);
            Method getDrawableMethod = slDraClass.getDeclaredMethod("getStateDrawable", int.class);

            int count = (Integer) getStateCountMethod.invoke(trackDrawable, (Object[]) null);
//            Log.e(TAG, "state count =" + count);
            for (int i = 0; i < count; i++) {
                int[] stateSet = (int[]) getStateSetMethod.invoke(trackDrawable, i);
                if (stateSet == null || stateSet.length == 0) {
                    Log.e(TAG, "state is null");
                } else {
                    for (int k : stateSet) {
//                        Log.e(TAG, "state =" + stateSet[j]);
                        if (k == -checked) {
                            Drawable drawable = (Drawable) getDrawableMethod.invoke(trackDrawable, i);//这就是你要获得的checked为false时候的drawable
                            drawable.setColorFilter(new PorterDuffColorFilter(unCheckedColor, PorterDuff.Mode.SRC_IN));
                        } else if (k == checked) {
                            Drawable drawable = (Drawable) getDrawableMethod.invoke(trackDrawable, i);//这就是你要获得的checked为true时候的drawable
                            drawable.setColorFilter(new PorterDuffColorFilter(checkedColor, PorterDuff.Mode.SRC_IN));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置大小
     */
    public void setThumbSize(int size) {
        GradientDrawable thumbDrawable = (GradientDrawable) switchView.getThumbDrawable();
        thumbDrawable.setSize(size, size);
    }


    /**
     * dp 转 px(像素)
     */
    public int dp2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 手动设置打开关闭状态
     * @param checked 状态
     */
    public void setChecked(boolean checked){
        switchView.setChecked(checked);
    }
}
