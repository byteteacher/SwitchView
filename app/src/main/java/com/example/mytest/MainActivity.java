package com.example.mytest;

import android.graphics.Color;
import android.util.Log;
import android.widget.CompoundButton;

import com.byteteacher.library.switchview.SwitchView;
import com.byteteacher.library.base.BaseActivity;
import com.example.myswitchview.R;


public class MainActivity extends BaseActivity {

    private static final String TAG = "cj";


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        SwitchView myswitch = findViewById(R.id.myswitch);
        //设置开关状态
        myswitch.setChecked(true);
        //设置滑块颜色
        myswitch.setThumbColor(Color.GREEN);
        //设置滑块大小
        myswitch.setThumbSize(dp2px(30));
        //设置背景两种状态的颜色
        myswitch.setTrackColor(Color.YELLOW, Color.CYAN);
        //设置开关监听
        myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e(TAG, "onCheckedChanged: " + isChecked);
                Log.e(TAG, "onCheckedChanged: " + buttonView.isChecked());
            }
        });

    }

    /**
     * dp 转 px(像素)
     */
    public int dp2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
