package com.feishuixiansheng.flyingwater;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.feishuixiansheng.flyingwater.annotation.LayoutId;
import com.feishuixiansheng.flyingwater.base.BaseMvpActivity;
import com.feishuixiansheng.flyingwater.data.AN;
import com.feishuixiansheng.flyingwater.util.AUtils;

@LayoutId(R.layout.activity_main)
public class MainActivity extends BaseMvpActivity<P> implements View.OnClickListener {

    private Button tv_main;

    @Override
    protected void initDate() {

    }

    @Override
    protected void initView() {
        Log.e("createView", (mPresenter == null) + "asddsa");
        tv_main = findViewById(R.id.tv_main);
        tv_main.setOnClickListener(this);
    }

    @Override
    public P createPresenter() {
        return new P();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_main:
                AUtils.go(AN.LOGIN);
                break;
        }
    }
}

