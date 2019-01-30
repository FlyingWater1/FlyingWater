package com.feishuixiansheng.flyingwater.permission;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.feishuixiansheng.flyingwater.R;

public class Permission1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission1);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame, new BlankFragment()).commit();

    }

}
