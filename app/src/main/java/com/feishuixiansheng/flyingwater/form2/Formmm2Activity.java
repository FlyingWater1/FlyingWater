package com.feishuixiansheng.flyingwater.form2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.app.annotation.apt.form.FormItem;
import com.app.annotation.apt.form.FormItems;
import com.app.annotation.apt.form.OnFormItemClick;
import com.feishuixiansheng.compiler.FormItemUtils;
import com.feishuixiansheng.flyingwater.R;

@FormItems(value = {
        @FormItem(leftName = "姓名1", leftImage = R.drawable.ic_common_back, rightNotNull = true, clickable = false),
        @FormItem(leftName = "姓名2" ),
        @FormItem(leftName = "姓名3",inputType = OnFormItemClick.InputType.Text),
        @FormItem(leftName = "姓名4",inputType = OnFormItemClick.InputType.Edit),
        @FormItem(leftName = "姓名5",inputType = OnFormItemClick.InputType.Time),
        @FormItem(leftName = "姓名6",inputType = OnFormItemClick.InputType.Null),
        @FormItem(leftName = "姓名7"),
        @FormItem(leftName = "姓名8"),
        @FormItem(leftName = "姓名9"),
}, formLayout = R.id.sdfsafgdsgsfg1111111)
public class Formmm2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formmm2);

        FormItemUtils.bind(this);
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
