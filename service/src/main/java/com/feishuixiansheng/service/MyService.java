package com.feishuixiansheng.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.feishuixiansheng.flyingwater.aidl.MyAIDLService;

/**
 * @author dupengfei
 * @create 2019/2/27 0027
 * @Describe
 */
public class MyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return new Mybind();
    }

    class Mybind extends MyAIDLService.Stub {

        @Override
        public String getString() throws RemoteException {
            return "我是从服务起返回的";
        }
    }
}
