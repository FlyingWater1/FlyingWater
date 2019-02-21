package com.feishuixiansheng.flyingwater.form;

import android.app.usage.EventStats;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.app.annotation.javassist.Bus;


import com.feishuixiansheng.flyingwater.EventTags;
import com.feishuixiansheng.flyingwater.R;
import com.feishuixiansheng.flyingwater.event.OkBus;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        //写在这里不起作用
        OkBus.getInstance().onEvent(EventTags.JUMP_TO_MAIN2);

//        findViewById(R.id.fdfsdsfsdf).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //写在这里可以起作用
////                OkBus.getInstance().onEvent(EventTags.JUMP_TO_MAIN);
////                OkBus.getInstance().onStickyEvent(EventTags.JUMP_TO_MAIN2);
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //写在这里可以起作用
//        OkBus.getInstance().onEvent(EventTags.JUMP_TO_MAIN2);
    }

    @Bus(value = EventTags.JUMP_TO_MAIN2)
    public void get(){
        Log.e("Main4Activity","aaaaaaaaaaaaaaaabbbb");
    }

}
