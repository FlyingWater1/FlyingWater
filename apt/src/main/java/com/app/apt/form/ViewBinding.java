package com.app.apt.form;

import javax.annotation.Nullable;

/**
 * @author dupengfei
 * @create 2019/2/13 0013
 * @Describe
 */
public class ViewBinding {
    public final @Nullable FieldViewBinding fieldBinding;
    public final @Nullable MethodViewBinding methodViewBinding;



    public ViewBinding(@Nullable FieldViewBinding fieldBinding,@Nullable MethodViewBinding methodViewBinding ) {
        this.fieldBinding = fieldBinding;
        this.methodViewBinding = methodViewBinding;
    }


    public static class Builder {
        FieldViewBinding fieldBinding;
        MethodViewBinding methodViewBinding;

        public Builder(String qualifiedName) {

        }

        public void addMethodBinding(MethodViewBinding methodViewBinding) {
            this.methodViewBinding = methodViewBinding;
        }

        public void setFieldBinding(FieldViewBinding fieldBinding) {
            this.fieldBinding = fieldBinding;
        }

        public ViewBinding build() {
            return new ViewBinding(fieldBinding,methodViewBinding);
        }
    }
}
