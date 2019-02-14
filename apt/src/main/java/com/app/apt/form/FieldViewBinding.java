package com.app.apt.form;

import com.app.annotation.apt.form.FormItem;
import com.squareup.javapoet.TypeName;

/**
 * @author dupengfei
 * @create 2019/2/13 0013
 * @Describe
 */
public class FieldViewBinding {

    TypeName typeName; String simpleName; boolean b; FormItem[] formItems;int formLayout;

    public FieldViewBinding(TypeName typeName, String simpleName, boolean b, FormItem[] formItems, int formLayout) {
        this.typeName = typeName;
        this.simpleName = simpleName;
        this.b = b;
        this.formItems = formItems;
        this.formLayout = formLayout;
    }

}
