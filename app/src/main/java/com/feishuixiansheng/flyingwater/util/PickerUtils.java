package com.feishuixiansheng.flyingwater.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.bigkoo.pickerview.OptionsPickerView;
import com.feishuixiansheng.flyingwater.App;
import com.feishuixiansheng.flyingwater.R;


import java.util.ArrayList;
import java.util.List;

import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.TimePicker;


/**
 * @author Daniel_Y
 * @date ：2017/12/15 on 9:51
 * Description:android picker 日期选择相关的设置
 */
public class PickerUtils {

    @SuppressLint("StaticFieldLeak")
    private static OptionsPickerView mOptionsPickerView;

    public static void pickerSet(DatePicker picker, String msg, Context context) {
        //顶部标题栏高度
        picker.setTopHeight(50);
        //顶部标题栏下划线颜色
        picker.setTopLineColor(context.getResources().getColor(R.color.bg_default));
        //顶部标题栏下划线高度
        picker.setTopLineHeight(1);
        picker.setTitleText(msg);
        //顶部标题颜色
        picker.setTitleTextColor(context.getResources().getColor(R.color.text_999));
        //顶部标题文字大小
        picker.setTitleTextSize(14);
        //顶部取消按钮文字颜色
        picker.setCancelTextColor(context.getResources().getColor(R.color.text_999));
        picker.setCancelTextSize(16);
        //顶部确定按钮文字颜色
        picker.setSubmitTextColor(context.getResources().getColor(R.color.blue_toolbar));
        picker.setSubmitTextSize(16);
        //中间滚动项文字颜色
        picker.setTextColor(context.getResources().getColor(R.color.blue_toolbar), context.getResources().getColor(R.color.text_999));
        picker.setTextSize(16);
    }

    public static void pickerTimerSet(TimePicker picker, String msg, Context context) {
        //顶部标题栏高度
        picker.setTopHeight(50);
        //顶部标题栏下划线颜色
        picker.setTopLineColor(context.getResources().getColor(R.color.bg_default));
        //顶部标题栏下划线高度
        picker.setTopLineHeight(1);
        picker.setTitleText(msg);
        //顶部标题颜色
        picker.setTitleTextColor(context.getResources().getColor(R.color.text_999));
        //顶部标题文字大小
        picker.setTitleTextSize(14);
        //顶部取消按钮文字颜色
        picker.setCancelTextColor(context.getResources().getColor(R.color.text_999));
        picker.setCancelTextSize(16);
        //顶部确定按钮文字颜色
        picker.setSubmitTextColor(context.getResources().getColor(R.color.blue_toolbar));
        picker.setSubmitTextSize(16);
        //中间滚动项文字颜色
        picker.setTextColor(context.getResources().getColor(R.color.blue_toolbar), context.getResources().getColor(R.color.text_999));
        picker.setTextSize(16);
    }

    /**
     * 默认特定模板的PickerView
     *
     * @param context
     * @param list
     * @param onOptionsSelects
     * @return
     */
    public static OptionsPickerView setDefaultPickerView(Context context, List<String> list, OnOptionsSelects onOptionsSelects) {
        mOptionsPickerView = new OptionsPickerView.Builder(context, (options1, options2, options3, v) ->
                onOptionsSelects.onOptions(options1))
                .setLayoutRes(R.layout.item_conference_select, v -> {
                    View sure = v.findViewById(R.id.tv_sure);
                    sure.setOnClickListener(v1 -> {
                        mOptionsPickerView.returnData();
                        mOptionsPickerView.dismiss();
                    });
                }).setTextColorCenter(Color.parseColor("#00a2e0"))
                .build();
        mOptionsPickerView.setPicker(list);
        return mOptionsPickerView;
    }

//    public static OptionsPickerView setDefaultPickerView(Context context, OptionsPickerView optionsPickerView, ArrayList<MeetingPlaceBean> list, OnMoreOptionsSelects onOptionsSelects) {
//        optionsPickerView = new OptionsPickerView.Builder(context, (options1, options2, options3, v) ->
//                onOptionsSelects.onOptions(options1))
//                .setLayoutRes(R.layout.item_conference_select, v -> {
//                    View sure = v.findViewById(R.id.tv_sure);
//                    onOptionsSelects.setOnClickListener(sure);
//                }).setTextColorCenter(Color.parseColor("#00a2e0"))
//                .build();
//        optionsPickerView.setPicker(list);
//        return optionsPickerView;
//    }

    public interface OnOptionsSelects {
        void onOptions(int position);
    }

    public interface OnMoreOptionsSelects {
        void onOptions(int position);

        void setOnClickListener(View view);
    }


    public interface OnDatePickHandler {
        void OnDatePicker(String date);
    }

    public interface OnDateChoose {
        void onChoose(String year, String mouth, String day);
    }

    /**
     * 初始化默认Picker
     *
     * @param context
     * @return
     */
    public static DatePicker initPicker(Activity context) {
        DatePicker picker = new DatePicker(context);
        picker.setTopPadding(12);
        picker.setRangeStart(2017, 1, 1);
        picker.setRangeEnd(2107, 12, 31);
        pickerSet(picker, "请选择日期", App.getAppContext());
        return picker;
    }


    /**
     * 选择时间
     *
     * @param picker
     * @param handler
     */
    public static void setTimeSelectPicker(DatePicker picker, OnDatePickHandler handler) {
        picker.setOnDatePickListener((DatePicker.OnYearMonthDayPickListener) (year, month, day) -> {
            handler.OnDatePicker(String.format("%s-%s-%s", year, month, day));
        });
        picker.show();
    }

    /**
     * 选择时间
     */
    public static void setTimeSelectPicker(DatePicker picker, OnDateChoose handler) {
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                handler.onChoose(year,month,day);
            }
        });
        picker.show();
    }


}
