package com.feishuixiansheng.flyingwater.annotation;

import android.support.annotation.DrawableRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author dupengfei
 * @create 2019/1/28 0028
 * @Describe
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TitleBarInfo {
    //标题
    String titleText();

    //是否有返回的箭头
    boolean hasBackImage() default true;

    //右边的文字
    String rightText() default "";

    //右边的图片
    @DrawableRes int rightImage() default -1;
}
