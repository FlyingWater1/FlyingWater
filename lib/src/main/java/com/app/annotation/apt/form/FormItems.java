package com.app.annotation.apt.form;

import com.app.annotation.apt.form.FormItem;

/**
 * @author dupengfei
 * @create 2019/1/31 0031
 * @Describe
 */
public @interface FormItems {
    FormItem[] value();

    int  rightImage() default 0;//通用右边图片

    int formItemLayout() default 0;//item的布局

    int formLayout();



}
