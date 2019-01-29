package com.feishuixiansheng.flyingwater.base;


import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class BasePresenter<V> {


    //布局的若引用
    protected Reference<V> viewRef;


    /**
     * 获取View的弱引用
     *
     * @param v
     */
    public void attachView(V v) {
        viewRef = new WeakReference<V>(v);
    }

    /**
     * 获取View对象
     *
     * @return 对象(View)
     */
    protected V getView() {
        return viewRef.get();
    }

    /**
     * 判断是否引用了
     *
     * @return
     */
    public boolean isViewAttached() {
        return null != viewRef && null != viewRef.get();
    }

    /**
     * 解除引用,释放内存
     */
    public void detachView() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }
}
