package com.app.annotation.apt.form;

/**
 * @author dupengfei
 * @create 2019/2/12 0012
 * @Describe 设置点击事件，根据右边的情况有以下情况
 *
 */

public @interface OnFormItemClick {



    String[] value() default {InputType.Text,InputType.Null,InputType.Edit,InputType.Single,InputType.Time};


    public class InputType{
        public final static String Text = "text";
        public final static String Null = "null";
        public final static String Edit = "edit";
        public final static String Single = "single";
        public final static String Time = "time";
    }


}
