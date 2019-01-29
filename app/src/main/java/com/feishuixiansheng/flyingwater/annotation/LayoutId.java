package com.feishuixiansheng.flyingwater.annotation;

import android.support.annotation.LayoutRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author dupengfei
 * @create 2019/1/28 0028
 * @Describe setContentView
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LayoutId {
    @LayoutRes int value() default 0;

}
