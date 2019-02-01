package com.feishuixiansheng.flyingwater.form;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.app.annotation.apt.AddText;
import com.app.annotation.apt.FormItem;
import com.app.annotation.apt.FormItems;
import com.apt.com.feishuixiansheng.flyingwater.form.FormActivity_AddText;
import com.feishuixiansheng.flyingwater.R;

@FormItems(value = {
        @FormItem(leftName = "姓名1"),
        @FormItem(leftName = "姓名2"),
        @FormItem(leftName = "姓名3"),
        @FormItem(leftName = "姓名4"),
        @FormItem(leftName = "姓名5"),
        @FormItem(leftName = "姓名6"),
        @FormItem(leftName = "姓名7"),
        @FormItem(leftName = "姓名8"),
        @FormItem(leftName = "姓名9"),
})
@AddText("这是apt添加的TextView")
public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        new FormActivity_AddText(this);

    }

}
