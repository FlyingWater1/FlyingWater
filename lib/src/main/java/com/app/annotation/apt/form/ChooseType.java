package com.app.annotation.apt.form;

/**
 * @author dupengfei
 * @create 2019/2/14 0014
 * @Describe 点击选择可以定义的种类
 */
public enum ChooseType {
    Type_String(String[].class),
    Type_St2ring(Enum.class);


    String[] valuesString;
    Enum anEnum;


    ChooseType(Class mClass) {

    }

    public String[] getValuesString() {
        return valuesString;
    }

    public void setValuesString(String[] valuesString) {
        this.valuesString = valuesString;
    }

    public Enum getAnEnum() {
        return anEnum;
    }

    public void setAnEnum(Enum anEnum) {
        this.anEnum = anEnum;
    }
}
