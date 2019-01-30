package com.feishuixiansheng.flyingwater.permission;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.annotation.aspect.Permission;
import com.feishuixiansheng.flyingwater.R;
import com.feishuixiansheng.flyingwater.annotation.LayoutId;
import com.feishuixiansheng.flyingwater.base.BaseActivity;

@LayoutId(R.layout.activity_permission)
public class PermissionActivity extends BaseActivity implements View.OnClickListener {

    private Button mTvTitle1;
    private Button mTvTitle2;
    private Button mTvTitle3;

    @Override
    protected void initDate() {

    }

    @Override
    protected void initView() {
        mTvTitle1 = (Button) findViewById(R.id.tv_title1);
        mTvTitle2 = (Button) findViewById(R.id.tv_title2);
        mTvTitle3 = (Button) findViewById(R.id.tv_title3);

        mTvTitle1.setOnClickListener(this);
        mTvTitle2.setOnClickListener(this);
        mTvTitle3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_title1:
                openCamera();
                break;
            case R.id.tv_title2:
                startActivity(new Intent(mContext,Permission1Activity.class));
                break;
            case R.id.tv_title3:
                startActivity(new Intent(mContext,Permission2Activity.class));
                break;
        }
    }

    @Permission(Manifest.permission.CAMERA)
    private void openCamera() {
        Toast.makeText(this,"申请权限成功",Toast.LENGTH_LONG).show();
    }

}
