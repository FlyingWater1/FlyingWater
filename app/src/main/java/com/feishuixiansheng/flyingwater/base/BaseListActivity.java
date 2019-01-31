package com.feishuixiansheng.flyingwater.base;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;

import com.app.annotation.apt.InstanceFactory;
import com.feishuixiansheng.flyingwater.P;
import com.feishuixiansheng.flyingwater.R;
import com.feishuixiansheng.flyingwater.util.InstanceUtil;
import com.feishuixiansheng.flyingwater.util.list.CommonAdapter;

import java.lang.reflect.ParameterizedType;

/**
 * @author dupengfei
 * @create 2019/1/31 0031
 * @Describe
 */

public abstract class BaseListActivity<T, LV extends View, A extends CommonAdapter> extends BaseActivity implements CommonAdapter.OnItemClickListner<T>, CommonAdapter.OnItemLongClickListner<T> {

    protected LV lvBaseList;

    protected A adapter;

    @Override
    protected void initView() {
        lvBaseList = findViewById(R.id.lvBaseList);

        Class<P> tClass = (Class<P>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
        InstanceFactory instanceFactory = tClass.getAnnotation(InstanceFactory.class);

        if (instanceFactory!=null){
            adapter = InstanceUtil.getInstance(tClass);
        } else {
            adapter =  createAdapter();
        }

        if (adapter != null){
            setAdapter(adapter);
        }

        createView();
    }

    protected abstract void createView();

    public A createAdapter(){
        return null;
    }

    @SuppressWarnings("unchecked")
    public void setAdapter(A adapter) {
        this.adapter = adapter;

        if (lvBaseList instanceof RecyclerView) {
            adapter.setIsRecyclerView(true);
            ((RecyclerView) lvBaseList).setAdapter(adapter);
            ((RecyclerView) lvBaseList).setLayoutManager(new LinearLayoutManager(this));
        }

        else if (lvBaseList instanceof AbsListView) {
            adapter.setIsRecyclerView(false);
            ((AbsListView) lvBaseList).setAdapter(adapter);
        }

        adapter.setOnItemClickListner(this);
        adapter.setOnItemLongClickListner(this);

    }

}
