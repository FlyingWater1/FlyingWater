package com.feishuixiansheng.flyingwater.exception;

/**
 * @author dupengfei
 * @create 2019/1/28 0028
 * @Describe
 */
public class NoLayoutIdException extends RuntimeException {
    public NoLayoutIdException(String activityName) {
        super("The activity :" + activityName + " has no annotation _ 1.@LayoutId, _2 getContentViewId");
    }
}
