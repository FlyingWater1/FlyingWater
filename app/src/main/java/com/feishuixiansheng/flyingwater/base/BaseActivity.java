package com.feishuixiansheng.flyingwater.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.feishuixiansheng.flyingwater.R;
import com.feishuixiansheng.flyingwater.annotation.LayoutId;
import com.feishuixiansheng.flyingwater.annotation.TitleBarInfo;
import com.feishuixiansheng.flyingwater.exception.NoLayoutIdException;
import com.feishuixiansheng.flyingwater.util.SettingData;

import java.util.Objects;


public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    private ImageView btnBack;
    private TextView tvTitle;
    private ImageView imgToolbarRight;
    private TextView tvToolbarRight;
    private LinearLayout llContent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            //APP下载安装后，点击“直接打开”，启动应用后，按下HOME键，再次点击桌面上的应用，会重启一个新的应用问题
            finish();
        }

        if (SettingData.isAlwaysVerticalScreen) {
            //总为竖屏的代码为
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            //总为横屏的代码为
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        if (SettingData.isNoTitle){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Objects.requireNonNull(getSupportActionBar()).hide();
            }
        }
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.toolbar_custom);
        initView1();
        setContent();

//        TitleBarInfo
        TitleBarInfo titleBarInfo = getClass().getAnnotation(TitleBarInfo.class);
        if (null != titleBarInfo) {
            tvTitle.setText(titleBarInfo.titleText());
        }
        initView();
        initDate();
    }

    protected abstract void initDate();

    protected abstract void initView();


    private void setContent() {
        //通过注解LayoutId 进行 setContentView
        LayoutId layoutId = getClass().getAnnotation(LayoutId.class);

        int layoutIdV = layoutId==null?getContentViewId():layoutId.value();

        Log.e("setContentsetContent",layoutIdV+"setContent" );
        if (layoutIdV != 0){
            LayoutInflater.from(this).inflate(layoutIdV,llContent,true);
        } else {
            throw new NoLayoutIdException(this.getClass().getCanonicalName());
        }
    }

    public int getContentViewId() {
        return 0;
    }


    private void initView1() {
        btnBack = (ImageView) findViewById(R.id.btn_back);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        imgToolbarRight = (ImageView) findViewById(R.id.img_toolbar_right);
        tvToolbarRight = (TextView) findViewById(R.id.tv_toolbar_right);
        llContent = (LinearLayout) findViewById(R.id.ll_content);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setOnRightTextClickListener(View.OnClickListener onRightTextClickListener) {
        tvToolbarRight.setOnClickListener(onRightTextClickListener);
    }

//    public void setOnRightImageClickListener(View.OnClickListener onRightImageClickListener) {
//        imgToolbarRight.setOnClickListener(onRightImageClickListener);
//    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }




}
