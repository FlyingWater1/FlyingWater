package com.feishuixiansheng.compiler;

import android.widget.TextView;

import com.app.annotation.apt.form.RightValue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.Format;
import java.util.List;

/**
 * @author dupengfei
 * @create 2019/2/12 0012
 * @Describe
 */
public class FormItemUtils {

    private static Object object;

    private static Object targetClz = null;

    public static void init(Object target) {
        targetClz = target;
        bind();
    }


    public static void bind() {
        Class<?> targetClass = targetClz.getClass();
        String clsName = targetClass.getName();
        try {
            Class<?> bindingClass = targetClass.getClassLoader().loadClass(clsName + "_FormItem");
            Constructor<? extends Object> constructor = bindingClass.getConstructor(targetClass);
            object = constructor.newInstance(targetClz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        changeValueList();

    }

    public static void changeValueList() {
        Field[] _formItemFields = object.getClass().getDeclaredFields();

        for (int i = 0; i < _formItemFields.length; i++) {
            Field _formItemField = _formItemFields[i];
            _formItemField.setAccessible(true);

            try {
                Object formItemView = _formItemField.get(object);

                Field mChooseList = FormItemView.class.getDeclaredField("mChooseList");
                Field listName = FormItemView.class.getDeclaredField("listName");

                mChooseList.setAccessible(true);
                listName.setAccessible(true);

                Object valueList = null;

                Field[] declaredFields = targetClz.getClass().getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    declaredField.setAccessible(true);
                    RightValue rightValue = declaredField.getAnnotation(RightValue.class);

                    if (rightValue != null && check(formItemView, rightValue)) {

                        valueList = declaredField.get(targetClz);

                        mChooseList.set(formItemView, valueList);

                        listName.set(formItemView, rightValue.listName());

                    }
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取chooseList
     *
     */
    public static Object getChooseList(int position) {
        Field listName = null;
        Field mChooseList = null;
        try {
            listName = FormItemView.class.getDeclaredField("listName");
            mChooseList = FormItemView.class.getDeclaredField("mChooseList");

            Field[] _formItemFields = object.getClass().getDeclaredFields();
            _formItemFields[position].setAccessible(true);
            FormItemView formItemView = (FormItemView) _formItemFields[position].get(object);
            listName.setAccessible(true);
            mChooseList.setAccessible(true);
            String listNameStr = (String) listName.get(formItemView);
            return mChooseList.get(formItemView);
//            if (listNameStr == null || listNameStr.equals("")) {
//                return mChooseList.get(formItemView);
//            } else {
//                return StringUtils.getArrayListData(mChooseList.get(formItemView),listNameStr);
//            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * 获取显示的集合
     * @param position item位置
     * @return
     */
    public static Object getValueStrList(int position) {
        Field listName = null;
        Field mChooseList = null;
        try {
            listName = FormItemView.class.getDeclaredField("listName");
            mChooseList = FormItemView.class.getDeclaredField("mChooseList");

            Field[] _formItemFields = object.getClass().getDeclaredFields();
            _formItemFields[position].setAccessible(true);
            FormItemView formItemView = (FormItemView) _formItemFields[position].get(object);
            listName.setAccessible(true);
            mChooseList.setAccessible(true);
            String listNameStr = (String) listName.get(formItemView);
            if (listNameStr == null || listNameStr.equals("")) {
                return mChooseList.get(formItemView);
            } else {
                return StringUtils.getArrayListData(mChooseList.get(formItemView),listNameStr);
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;

    }


    private static boolean check(Object formItemView, RightValue rightValue) {
        try {
            Field name = FormItemView.class.getDeclaredField("name");
            name.setAccessible(true);
            String string = (String) name.get(formItemView);

            return rightValue.leftName().equals(string);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void finish(int po, int position) {

        List<String> namename = (List<String>) FormItemUtils.getValueStrList(po);

        assert namename != null;
        String valueS = namename.get(position);

        List chooseList = (List) getChooseList(po);

        try {
            Field field = object.getClass().getDeclaredField("formItems" + po);

            field.setAccessible(true);

            FormItemView formItemView = (FormItemView) field.get(object);

            Field value = FormItemView.class.getDeclaredField("value");

            value.setAccessible(true);

            value.set(formItemView, chooseList.get(position));

            Field tvChoose = FormItemView.class.getDeclaredField("tvChoose");

            tvChoose.setAccessible(true);

            TextView textView = (TextView) tvChoose.get(formItemView);

            textView.setText(valueS);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public static Object getValueChoosed(int position,String chooseName) {
        Field value = null;
        try {
            value = FormItemView.class.getDeclaredField("value");
            value.setAccessible(true);

            Field[] _formItemFields = object.getClass().getDeclaredFields();
            _formItemFields[position].setAccessible(true);

            FormItemView formItemView = (FormItemView) _formItemFields[position].get(object);
            Object valueObject = value.get(formItemView);

            if (valueObject != null){
                Field chooseNameFiled = valueObject.getClass().getDeclaredField(chooseName);
                chooseNameFiled.setAccessible(true);
                return chooseNameFiled.get(valueObject);
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;

    }


}
