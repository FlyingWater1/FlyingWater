package com.app.annotation.apt.form;

/**
 * @author dupengfei
 * @create 2019/2/14 0014
 * @Describe
 */
public @interface RightValue {
    String leftName(); //用于匹配item

    String listName() default ""; //用于获取集合
}
