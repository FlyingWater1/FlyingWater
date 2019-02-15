package com.feishuixiansheng.compiler;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;


/**
 * @author dupengfei
 * @create 2018/12/6 0006
 * @Describe
 */
public class FormItemView extends RelativeLayout {

    private boolean isNotNull;
    private String name;
    private String hint;
    private Object value;
    private List mChooseList;
    private View viewLine;
    private String listName;
    private TextView tvChoose;
    private View view;
    private TextView tvIsNotNull;
    private TextView tvName;
    private ImageView ivRightIma;
    private ImageView ivLeftIma;
    private String inputType = "";
    private Class tClass;

    public FormItemView(Context context) {
        this(context, null);
    }

    private FormItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private FormItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initFormItem();
    }

    private void initFormItem() {
        view = LayoutInflater.from(getContext()).inflate(R.layout.form_item_view, this, true);
        tvIsNotNull = view.findViewById(R.id.tv_is_not_null);
        tvName = view.findViewById(R.id.tv_name);
        tvChoose = view.findViewById(R.id.tv_choose);
        ivRightIma = view.findViewById(R.id.iv_right_ima);
//        etEdit = view.findViewById(R.id.et_edit);
        viewLine = view.findViewById(R.id.view_line);
        ivLeftIma = view.findViewById(R.id.leftImage);
    }

    public void setName(String name) {
        this.name = name;
        tvName.setText(name);
    }

    public void setLeftIma(int resId) {
        ivLeftIma.setBackgroundResource(resId);
    }

    public void isNotNull(boolean isNotNull) {
        if (isNotNull) {
            tvIsNotNull.setVisibility(VISIBLE);
        } else {
            tvIsNotNull.setVisibility(INVISIBLE);
        }
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }
}
