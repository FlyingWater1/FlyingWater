package com.feishuixiansheng.flyingwater.form;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.annotation.apt.form.FormItem;
import com.app.annotation.apt.form.FormItems;
import com.app.annotation.apt.form.FormLayout;
import com.app.annotation.apt.form.OnFormItemClick;
import com.feishuixiansheng.compiler.FormItemUtils;
import com.feishuixiansheng.flyingwater.R;

@FormItems(value = {
        @FormItem(leftName = "姓名1", leftImage = R.drawable.ic_common_back, rightNotNull = true, clickable = false),
        @FormItem(leftName = "姓名2", inputType = OnFormItemClick.InputType.Text),
        @FormItem(leftName = "姓名3"),
        @FormItem(leftName = "姓名4"),
        @FormItem(leftName = "姓名5"),
        @FormItem(leftName = "姓名6"),
        @FormItem(leftName = "姓名7"),
        @FormItem(leftName = "姓名8"),
        @FormItem(leftName = "姓名9"),
}, formLayout = R.id.sdfsafgdsgsfg)
//@AddText("这是apt添加的TextView")
public class FormActivity extends AppCompatActivity {

    String[] chooseSingle = new String[]{};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

//        View viewById = this.getWindow().getDecorView().findViewById(android.R.id.content);
//        if (viewById instanceof ViewGroup) {
//            ViewGroup viewGroup = (ViewGroup) viewById;
//            View childAt = viewGroup.getChildAt(0);
//            if (childAt instanceof ViewGroup) {
//                View viewById1 = childAt.findViewById(R.id.formLayout);
//
//            }
//        }

        View viewById = findViewById(R.id.formLayout);



        FormItemUtils.bind(this);
    }

//    @OnFormItemClick
//    public void onFormClick(String type) {
//        switch (type) {
//            case OnFormItemClick.InputType.Text:
//                Toast.makeText(this, "textterteterwte", Toast.LENGTH_LONG).show();
//                break;
//        }
//    }

}
