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
public class FormItemView<T> extends RelativeLayout {

    private boolean isNotNull;
    private FormRightType formRightType;
    private FormChooseType formChooseType;
    private String name;
    private String hint;
    private String value = "";
    private List<T> mChooseList;
//    private OptionsPickerView mConferencePickerView;
    private OnSingleChooseFinishListener onSingleChooseFinishListener;
    private View viewLine;
    private String valueMethod;
    private T chooseItem;
    private View view;
    private TextView tvIsNotNull;
    private TextView tvName;
    private TextView tvChoose;
    private ImageView ivRightIma;
    private ImageView ivLeftIma;
    private String inputType;
//    private EditText etEdit;

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

    public String getValue() {
        return value;
    }

    public FormItemView(Context context, boolean isNotNull, FormRightType formRightType, FormChooseType formChooseType, String name) {
        super(context);
        this.isNotNull = isNotNull;
        this.formRightType = formRightType;
        this.formChooseType = formChooseType;
        this.name = name;
//        this.hint = hint;
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


//        etEdit.setSelection(etEdit.getText().length());


//        switch (formRightType) {
//            case EDIT:
////                etEdit.setVisibility(VISIBLE);
//                tvChoose.setVisibility(GONE);
//                ivRightIma.setVisibility(GONE);
////                etEdit.setHint("请输入");
////                etEdit.setText(value);
//                break;
//            case NULL:
////                etEdit.setVisibility(GONE);
//                tvChoose.setVisibility(GONE);
//                ivRightIma.setVisibility(GONE);
//                break;
//            case CHOOSE:
//                tvChoose.setVisibility(VISIBLE);
//                ivRightIma.setVisibility(VISIBLE);
////                etEdit.setVisibility(GONE);
//                tvChoose.setHint("请选择");
//                tvChoose.setText(value);
//                break;
//        }

//        switch (formChooseType) {
//            case CHOOSE_TIME:
//
//                break;
//            case CHOOSE_SINGLE:
//                /**
//                 * 关闭软键盘
//                 */
//                View view2 = ((Activity) this.getContext()).getWindow().peekDecorView();
//                if (view != null) {
//                    InputMethodManager inputmanger = (InputMethodManager) ((Activity) this.getContext()).getSystemService(Context.INPUT_METHOD_SERVICE);
//                    inputmanger.hideSoftInputFromWindow(view2.getWindowToken(), 0);
//                }

//                tvChoose.setOnClickListener(new OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        //todo 局限：对于mChooseList本身就是String的集合的处理？valueMethod是空没有处理
//                        List<String> arrayListData = StringUtils.getArrayListData(mChooseList, valueMethod);
//
//
//                        mConferencePickerView = PickerUtils.setDefaultPickerView(getContext(), arrayListData, position -> {
//                            if (onSingleChooseFinishListener != null) {
//                                onSingleChooseFinishListener.finish(position, arrayListData.get(position));
//                            }
//
//                            chooseItem = mChooseList.get(position);
//                            value = arrayListData.get(position);
//                            tvChoose.setText(value);
//                            mConferencePickerView.dismiss();
//                        });
//                        mConferencePickerView.show();
//
//                    }
//                });
//                break;
//        }

//        etEdit.addTextChangedListener(new TextWatcher() {
////            @Override
////            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
////
////            }
////
////            @Override
////            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
////                value = etEdit.getText().toString();
////            }
////
////            @Override
////            public void afterTextChanged(Editable editable) {
////
////            }
////        });


        tvName.setText(name);


    }


    public enum FormRightType {
        EDIT, CHOOSE, NULL
    }

    public enum FormChooseType {
        //选择时间，单选
        CHOOSE_TIME, CHOOSE_SINGLE, NULL
    }

    public interface OnSingleChooseFinishListener {
        void finish(int position, String value);
    }

    public void hiddenViewLine() {
        viewLine.setVisibility(INVISIBLE);
    }

    public void setOnSingleChooseFinishListener(OnSingleChooseFinishListener onSingleChooseFinishListener) {
        this.onSingleChooseFinishListener = onSingleChooseFinishListener;
    }

    public void setmChooseList(List<T> mChooseList, String valueMethod) {
        this.mChooseList = mChooseList;
        this.valueMethod = valueMethod;
    }

    public String getStringContent(String methodName) {
        String value = null;
        if (chooseItem == null) {
            return "";
        } else {
            Method method;
            try {
                method = chooseItem.getClass().getDeclaredMethod(methodName);
                method.setAccessible(true);
                value = String.valueOf(method.invoke(chooseItem));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            if (value == null) {
                return "";
            }
            return value;
        }
    }


    public T getChooseItem() {
        return chooseItem;
    }

    public void setName(String name) {
        tvName.setText(name);
    }

    public void setLeftIma(int resId) {
        ivLeftIma.setBackgroundResource(resId);
    }

    public void isNotNull( boolean isNotNull) {
        if (isNotNull) {
            tvIsNotNull.setVisibility(VISIBLE);
        } else {
            tvIsNotNull.setVisibility(INVISIBLE);
        }
    }

//    tv_is_not_null


    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }
}
