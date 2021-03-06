package com.feishuixiansheng.flyingwater;

import android.app.Application;
import android.content.Context;

import com.feishuixiansheng.flyingwater.image.GlideImage;
import com.feishuixiansheng.flyingwater.image.ImageHelper;

/**
 * @author dupengfei
 * @create 2019/1/30 0030
 * @Describe
 */
public class App extends Application {

    private static Context mContext;
    private static App mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mApp = this;

        ImageHelper.init(new GlideImage());
    }

    public static App getAppContext() {
        return mApp;
    }
}
