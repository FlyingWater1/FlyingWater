package com.feishuixiansheng.flyingwater.form2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.annotation.apt.form.FormItem;
import com.app.annotation.apt.form.FormItems;
import com.app.annotation.apt.form.OnFormItemClick;
import com.feishuixiansheng.compiler.FormItemUtils;
import com.feishuixiansheng.compiler.FormItemView;
import com.feishuixiansheng.flyingwater.R;

@FormItems(value = {
        @FormItem(leftName = "姓名1", leftImage = R.drawable.ic_common_back, rightNotNull = true, clickable = false),
        @FormItem(leftName = "姓名2" ),
        @FormItem(leftName = "姓名3",inputType = OnFormItemClick.InputType.Text),
        @FormItem(leftName = "姓名4",inputType = OnFormItemClick.InputType.Edit),
        @FormItem(leftName = "姓名5",inputType = OnFormItemClick.InputType.Time),
        @FormItem(leftName = "姓名6",inputType = OnFormItemClick.InputType.Null),
}, formLayout = R.id.sdfsafgdsgsfg1111111)
public class Formmm2Activity extends AppCompatActivity {

    FormItemView formItems0;

    FormItemView formItems1;

    FormItemView formItems2;

    FormItemView formItems3;

    FormItemView formItems4;

    FormItemView formItems5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formmm2);

        FormItemUtils.init(this);

//        View formLayout = this.findViewById(R.id.sdfsafgdsgsfg1111111);
//        if (formLayout instanceof LinearLayout) {
//            formItems0 = new FormItemView(this);
//            formItems0.setName("姓名1");
//            formItems0.setLeftIma(2131099734);
//            formItems0.isNotNull(true);
//            ((LinearLayout)formLayout).addView(formItems0);
//            formItems1 = new FormItemView(this);
//            formItems1.setName("姓名2");
//            ((LinearLayout)formLayout).addView(formItems1);
//            formItems2 = new FormItemView(this);
//            formItems2.setName("姓名3");
//            formItems2.setInputType("text");
//            ((LinearLayout)formLayout).addView(formItems2);
//            formItems3 = new FormItemView(this);
//            formItems3.setName("姓名4");
//            formItems3.setInputType("edit");
//            ((LinearLayout)formLayout).addView(formItems3);
//            formItems4 = new FormItemView(this);
//            formItems4.setName("姓名5");
//            formItems4.setInputType("time");
//            ((LinearLayout)formLayout).addView(formItems4);
//            formItems5 = new FormItemView(this);
//            formItems5.setName("姓名6");
//            formItems5.setInputType("null");
//            ((LinearLayout)formLayout).addView(formItems5);
//        }




    }
//
//    @OnFormItemClick
//    public void onFormClick(String type) {
//        switch (type) {
//            case OnFormItemClick.InputType.Text:
//                Toast.makeText(this, "textterteterwteText", Toast.LENGTH_LONG).show();
//                break;
//            case OnFormItemClick.InputType.Edit:
//                Toast.makeText(this, "textterteterwteEdit", Toast.LENGTH_LONG).show();
//                break;
//            case OnFormItemClick.InputType.Time:
//                Toast.makeText(this, "textterteterwteTime", Toast.LENGTH_LONG).show();
//                break;
//            case OnFormItemClick.InputType.Null:
//                Toast.makeText(this, "AAAAA", Toast.LENGTH_LONG).show();
//                break;
//        }
//    }
}
