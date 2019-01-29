package com.feishuixiansheng.flyingwater.util;


import com.apt.InstanceFactoryPresenter;

/**
 * Created by baixiaokang on 16/4/30.
 */
public class InstanceUtil {

    /**
     * 通过实例工厂去实例化相应类
     *
     * @param <T> 返回实例的泛型类型
     * @return
     */
    public static <T> T getInstance(Class clazz) {
        try {
            return (T) InstanceFactoryPresenter.create(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
