package com.feishuixiansheng.flyingwater.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.app.annotation.apt.InstanceFactory;
import com.feishuixiansheng.flyingwater.exception.NoPresenterInstanceException;
import com.feishuixiansheng.flyingwater.util.InstanceUtil;
//import com.feishuixiansheng.flyingwater.util.InstanceUtil;

import java.lang.reflect.ParameterizedType;


/**
 * @author dupengfei
 * @create 2019/1/28 0028
 * @Describe 填入泛型的BaseMvpActivity的子类自动实例化
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Class<P> tClass = (Class<P>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        InstanceFactory instanceFactory = tClass.getAnnotation(InstanceFactory.class);

        if (instanceFactory!=null){
            mPresenter = InstanceUtil.getInstance(tClass);
        } else {
            mPresenter =  createPresenter();
        }

        if (mPresenter == null){
            throw new NoPresenterInstanceException(tClass.getCanonicalName());
        } else {
            mPresenter.attachView(this);
        }
    }

    public  P createPresenter(){
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.detachView();
        }
    }


}
