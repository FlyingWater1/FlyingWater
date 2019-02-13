package com.feishuixiansheng.compiler;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author :Daniel_Y
 * @date ：2017/12/29 on 13:40
 * Description: 一些字符串操作的工具类
 */
public class StringUtils {
    public static String WheatherNull(String s, Context context) {
        return !TextUtils.isEmpty(s) ? s : "暂未填写";
    }

    private static String nums[] = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};

    private static String pos_units[] = {"", "十", "百", "千"};

    private static String weight_units[] = {"", "万", "亿"};

    /**
     * 数字转中文大写
     *
     * @param num
     * @return
     */
    public static String NumToString(int num) {
        if (num == 0) {
            return "零";
        }
        int weigth = 0;//节权位
        String chinese = "";
        String chinese_section = "";
        boolean setZero = false;//下一小节是否需要零，第一次没有上一小节所以为false
        while (num > 0) {
            int section = num % 10000;//得到最后面的小节
            if (setZero) {//判断上一小节的千位是否为零，是就设置零
                chinese = nums[0] + chinese;
            }
            chinese_section = sectionTrans(section);
            if (section != 0) {//判断是都加节权位
                chinese_section = chinese_section + weight_units[weigth];
            }
            chinese = chinese_section + chinese;
            chinese_section = "";
            setZero = (section < 1000) && (section > 0);
            num = num / 10000;
            weigth++;
        }
        if ((chinese.length() == 2 || (chinese.length() == 3)) && chinese.contains("一十")) {
            chinese = chinese.substring(1, chinese.length());
        }
        if (chinese.indexOf("一十") == 0) {
            chinese = chinese.replaceFirst("一十", "十");
        }
        return chinese;
    }

    public static String sectionTrans(int section) {
        StringBuilder section_chinese = new StringBuilder();
        int pos = 0;//小节内部权位的计数器
        boolean zero = true;//小节内部的置零判断，每一个小节只能有一个零。
        while (section > 0) {
            int v = section % 10;//得到最后一个数
            if (v == 0) {
                if (!zero) {
                    zero = true;//需要补零的操作，确保对连续多个零只是输出一个
                    section_chinese.insert(0, nums[0]);
                }
            } else {
                zero = false;//有非零数字就把置零打开
                section_chinese.insert(0, pos_units[pos]);
                section_chinese.insert(0, nums[v]);
            }
            pos++;
            section = section / 10;
        }

        return section_chinese.toString();
    }

    /**
     * 获取某集合中的StringList
     *
     * @param obj
     * @param params
     * @return
     */
    public static List<String> getArrayListData(Object obj, String params) {
        List<String> stringList = new ArrayList<>();
        List<Object> list = (List<Object>) obj;
        try {
            for (Object objs : list) {
                Method method = objs.getClass().getDeclaredMethod(params);
                method.setAccessible(true);
                String param = (String) method.invoke(objs);
                stringList.add(param);
//                LogUtils.e("string-->" + param);
            }
        } catch (Exception e) {
            e.printStackTrace();
//            Log.e("Exception--->" + e.toString());
            return stringList;
        }
//        LogUtils.e("size-->" + stringList.size());
        return stringList;
    }


    public static String String2Num(String str) {
        if (str.isEmpty()) return "0";
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return "0";
            }
        }
        return str;
    }

    //  删除ArrayList中重复元素，保持顺序
    public static List<Integer> d(List<Integer> list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);
        return list;
    }

}
