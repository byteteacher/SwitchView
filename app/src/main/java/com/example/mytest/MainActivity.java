package com.example.mytest;

import android.util.Log;
import android.widget.CompoundButton;

import com.byteteacher.library.SwitchView;
import com.cj.library.base.BaseActivity;
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

        myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e(TAG, "onCheckedChanged: " + isChecked);
                Log.e(TAG, "onCheckedChanged: " + buttonView.isChecked());
            }
        });

    }


}
