package com.app.annotation.apt.form;



import java.util.List;

/**
 * @author dupengfei
 * @create 2019/1/31 0031
 * @Describe
 */
public @interface FormItem {

    int leftImage() default 0;//左边图片
    String leftName();
    int  rightImage() default 0;//右边图片
    boolean rightNotNull() default false;
    String rightHint() default "";
    LineWidth lineWidth() default LineWidth.thin;
    boolean clickable() default true;
    String inputType() default "";

    enum LineWidth {
        thick, thin
    }
}
