package com.feishuixiansheng.flyingwater.exception;

/**
 * @author dupengfei
 * @create 2019/1/28 0028
 * @Describe
 */
public class NoPresenterInstanceException extends RuntimeException {
    public NoPresenterInstanceException(String presenter) {
        super("The presenter :" + presenter + " has no Instance _ 1.InstanceFactory _2.createPresenter()");
    }
}
