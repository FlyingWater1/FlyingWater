package com.app.apt.form;

import com.squareup.javapoet.TypeName;


import java.lang.annotation.Annotation;

/**
 * @author dupengfei
 * @create 2019/2/14 0014
 * @Describe
 */
public class MethodViewBinding {


    String simpleName;

    boolean b;

    Annotation annotation;

    public MethodViewBinding(String simpleName, boolean b, Annotation annotation) {
        this.simpleName = simpleName;
        this.b = b;
        this.annotation = annotation;
    }

}
