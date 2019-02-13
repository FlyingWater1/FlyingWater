package com.feishuixiansheng.compiler;

import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author dupengfei
 * @create 2019/2/12 0012
 * @Describe
 */
public class FormItemUtils {
    public static void bind(Object target){
        Class<?> targetClass = target.getClass();
        String clsName = targetClass.getName();
        try {
            Class<?> bindingClass = targetClass.getClassLoader().loadClass("com.apt."+clsName + "_FormItem");
            Constructor constructor =bindingClass.getConstructor(targetClass);
            constructor.newInstance(target);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
