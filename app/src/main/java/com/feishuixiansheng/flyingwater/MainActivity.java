package com.feishuixiansheng.flyingwater;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.annotation.javassist.Bus;
import com.feishuixiansheng.flyingwater.base.BaseMvpActivity;
import com.feishuixiansheng.flyingwater.event.OkBus;
import com.feishuixiansheng.flyingwater.form.FormActivity;
import com.feishuixiansheng.flyingwater.form.Main4Activity;
import com.feishuixiansheng.flyingwater.list.RecyclerViewActivity;
import com.feishuixiansheng.flyingwater.permission.PermissionActivity;
import com.feishuixiansheng.flyingwater.R;

//import com.feishuixiansheng.flyingwater.event.EventTags;
//import com.feishuixiansheng.flyingwater.util.AUtils;

public class MainActivity extends BaseMvpActivity<P> implements View.OnClickListener {

    private Button tv_main;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;

    @Override
    protected void initDate() {

    }

    @Override
    protected void initView() {
        Log.e("createView", (mPresenter == null) + "asddsa");
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);

        OkBus.getInstance().onStickyEvent(EventTags.JUMP_TO_MAIN);
    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View v) {
        Class mClass = null;
        switch (v.getId()) {
            case R.id.button1:
                //aop申请权限
//                AUtils.go(AN.LOGIN);
                mClass = PermissionActivity.class;
                break;
            case R.id.button2:
                //注解实现表单
                mClass = FormActivity.class;
//                AUtils.go(AN.LOGIN);
                break;
            case R.id.button3:
                //通用的Adapter
                mClass = RecyclerViewActivity.class;
//                AUtils.go(AN.LOGIN);
                break;
            case R.id.button4:
                //通用的Adapter
                mClass = Main4Activity.class;
//                AUtils.go(AN.LOGIN);
                break;
        }
        startActivity(new Intent(this,mClass));
    }

    @Bus(EventTags.JUMP_TO_MAIN)
    public void jumpToMainPage() {

        Toast.makeText(this,"dasf",Toast.LENGTH_LONG).show();
    }
}

