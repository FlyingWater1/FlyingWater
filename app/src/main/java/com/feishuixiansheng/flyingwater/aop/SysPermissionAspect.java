package com.feishuixiansheng.flyingwater.aop;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.app.annotation.aspect.Permission;
import com.feishuixiansheng.flyingwater.App;
import com.feishuixiansheng.flyingwater.impl.ContextImpl;
import com.feishuixiansheng.flyingwater.util.MPermissionUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by baixiaokang on 17/1/31.
 * 申请系统权限切片，根据注解值申请所需运行权限
 */
@Aspect
public class SysPermissionAspect {

    @Around("execution(@com.app.annotation.aspect.Permission * *(..)) && @annotation(permission)")
    public void aroundJoinPoint(final ProceedingJoinPoint joinPoint, Permission permission) throws Throwable {

        final ContextImpl o  = (ContextImpl) joinPoint.getTarget();

        MPermissionUtils.requestPermissions(o, 1, permission.value()
                , new MPermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        try {
                            joinPoint.proceed();//获得权限，执行原方法
                        } catch (Throwable e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onPermissionDenied() {
                        MPermissionUtils.showTipsDialog(o.getContextI());
                    }
                });
    }
}


