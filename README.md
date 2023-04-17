# SwitchView
一个开关控件，基于Android Switch控件封装。

### 效果

![](https://raw.githubusercontent.com/byteteacher/byteteacher.github.io/main/imgbed/switchview/switchview.gif)

### 使用方式

#### 添加依赖
```groovy
implementation 'com.github.byteteacher:switchview:1.0.0'
```

#### 1、xml使用

```xml
    <com.byteteacher.library.SwitchView
        android:id="@+id/myswitch"
        android:layout_width="wrap_content"
        app:thumbSize="26dp"
        app:thumbColor="#FFF"
        app:trackViewUncheckedColor="#626260"
        app:trackViewCheckedColor="#14C893"
        app:checked="true"
        android:layout_height="wrap_content"/>
```

#### 2、代码使用

```java
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
```



### 属性介绍

| 属性                    | 类型    | 功能                 |
| ----------------------- | ------- | -------------------- |
| thumbSize               | dimens  | 设置开关大小         |
| thumbColor              | color   | 设置开关滑块的颜色   |
| trackViewUncheckedColor | color   | 设置关闭状态背景色   |
| trackViewCheckedColor   | color   | 设置开启状态背景颜色 |
| checked                 | boolean | 设置初始开关状态     |

