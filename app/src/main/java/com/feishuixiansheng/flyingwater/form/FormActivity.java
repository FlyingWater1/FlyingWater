package com.feishuixiansheng.flyingwater.form;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.annotation.apt.form.FormItem;
import com.app.annotation.apt.form.FormItems;
import com.app.annotation.apt.form.OnFormItemClick;
import com.app.annotation.apt.form.RightValue;
import com.feishuixiansheng.compiler.FormItemUtils;
import com.feishuixiansheng.flyingwater.R;
import com.feishuixiansheng.flyingwater.bean.TestBean;
import com.feishuixiansheng.flyingwater.util.PickerUtils;

import java.util.ArrayList;
import java.util.List;

@FormItems(value = {
        @FormItem(leftName = "姓名1", leftImage = R.drawable.ic_common_back, rightNotNull = true, clickable = false),
        @FormItem(leftName = "姓名2", inputType = OnFormItemClick.InputType.Text),
        @FormItem(leftName = "姓名3", inputType = OnFormItemClick.InputType.Single),
        @FormItem(leftName = "姓名4", inputType = OnFormItemClick.InputType.Single),
        @FormItem(leftName = "姓名5"),
        @FormItem(leftName = "姓名6"),
        @FormItem(leftName = "姓名7"),
        @FormItem(leftName = "姓名8"),
        @FormItem(leftName = "姓名9"),
}, formLayout = R.id.sdfsafgdsgsfg)
public class FormActivity extends AppCompatActivity {

    @RightValue(leftName = "姓名3", listName = "getNamename")
    List<TestBean.Bean2> mString3 = new ArrayList<>();

    @RightValue(leftName = "姓名4")
    List<String> mStrings4 = new ArrayList<>();

    TextView btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        FormItemUtils.init(this);

        btnSubmit = findViewById(R.id.btnSubmit);
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mString3 = new ArrayList<>();
                        TestBean.Bean2 sdad1 = new TestBean.Bean2();
                        sdad1.setNamename("asdasdfda1");
                        sdad1.setId("1111");
                        TestBean.Bean2 sdad2 = new TestBean.Bean2();
                        sdad2.setNamename("asdasdfda2");
                        sdad2.setId("2222");
                        TestBean.Bean2 sdad3 = new TestBean.Bean2();
                        sdad3.setNamename("asdasdfda3");
                        sdad3.setId("3333");
                        mString3.add(sdad1);
                        mString3.add(sdad2);
                        mString3.add(sdad3);
                        FormItemUtils.changeValueList();
                    }
                });
            }
        }).start();


        mStrings4.add("44444444_1");
        mStrings4.add("44444444_2");
        mStrings4.add("44444444_3");
        mStrings4.add("44444444_4");
        FormItemUtils.changeValueList();


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    private void submit() {
        Object getIdidididid = FormItemUtils.getValueChoosed(2, "id");
        if (getIdidididid!=null){
            Toast.makeText(this, "姓名3 对应的id为"+(String)getIdidididid, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "姓名3 请选择", Toast.LENGTH_LONG).show();
        }

    }

    @OnFormItemClick(value = {OnFormItemClick.InputType.Text, OnFormItemClick.InputType.Single})
    public void onFormClick(String type, int po) {
        switch (type) {
            case OnFormItemClick.InputType.Text:
                Toast.makeText(this, "显示的是文字", Toast.LENGTH_LONG).show();
                break;
            case OnFormItemClick.InputType.Single:
                List<String> namename = (List<String>) FormItemUtils.getValueStrList(po);
                if (namename != null && namename.size() > 0) {
                    PickerUtils.setDefaultPickerView(this, namename, position -> {
                        FormItemUtils.finish(po, position);
                    }).show();
                }
                break;

        }
    }
}
