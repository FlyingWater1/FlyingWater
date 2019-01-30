package com.feishuixiansheng.flyingwater.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.feishuixiansheng.flyingwater.App;
import com.feishuixiansheng.flyingwater.LoginActivity;

/**
 * @author dupengfei
 * @create 2019/1/30 0030
 * @Describe
 */
public class AUtils {
    public static void go(String name){
        Context mContext=App.getAppContext();

        //查找name对应的Activity

        Intent intent = new Intent(mContext,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
        mContext.startActivity(intent);
    }
}
