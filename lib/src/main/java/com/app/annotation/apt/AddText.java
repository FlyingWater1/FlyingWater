package com.app.annotation.apt;

import android.support.annotation.IdRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;




/**
 * @author dupengfei
 * @create 2019/1/31 0031
 * @Describe
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
public @interface AddText {
     String value();

    @IdRes int df();

}
