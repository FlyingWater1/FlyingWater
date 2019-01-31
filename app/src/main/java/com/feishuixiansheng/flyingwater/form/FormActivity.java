package com.feishuixiansheng.flyingwater.form;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.annotation.apt.AddTextView;
import com.app.annotation.apt.FormItem;
import com.app.annotation.apt.FormItems;
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
@AddTextView
public class FormActivity extends AppCompatActivity {

    private ConstraintLayout xxx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        initView();
    }

    private void initView() {
        View viewById = getWindow().getDecorView().findViewById(android.R.id.content);

        if (viewById instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) viewById;
            Log.e("dsadfafd", viewGroup.getChildCount() + "");

            View childAt = viewGroup.getChildAt(0);
            if (childAt instanceof ViewGroup){
                TextView textView = new TextView(this);
                textView.setText("DSAFASDFSD");
                ((ViewGroup) childAt).addView(textView);
            }
        }
    }
}
