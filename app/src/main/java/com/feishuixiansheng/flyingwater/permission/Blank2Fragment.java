package com.feishuixiansheng.flyingwater.permission;


import android.Manifest;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.app.annotation.aspect.Permission;
import com.feishuixiansheng.flyingwater.R;
import com.feishuixiansheng.flyingwater.impl.ContextImpl;
import com.feishuixiansheng.flyingwater.util.MPermissionUtils;

/**
 * 介绍在android.app.Fragment下申请权限
 */
public class Blank2Fragment extends Fragment implements ContextImpl, View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(this);
        return view;
    }

    private void initView(View inflate) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                openCamera();
                break;
        }
    }

    @Permission(Manifest.permission.CAMERA)
    private void openCamera() {
        Toast.makeText(this.getActivity(),"申请权限成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getContextI() {
        return this.getActivity();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissionUtils.onRequestPermissionsResult(requestCode,permissions,grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
