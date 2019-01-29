package com.feishuixiansheng.flyingwater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.feishuixiansheng.flyingwater.annotation.LayoutId;
import com.feishuixiansheng.flyingwater.base.BaseActivity;
import com.feishuixiansheng.flyingwater.base.BaseMvpActivity;

@LayoutId(R.layout.activity_main)
public class MainActivity extends BaseMvpActivity<P> {

    @Override
    protected void initDate() {

    }

    @Override
    protected void initView() {
        Log.e("createView", (mPresenter == null) +"asddsa" );
    }

    @Override
    public P createPresenter() {
        return new P();
    }
}

