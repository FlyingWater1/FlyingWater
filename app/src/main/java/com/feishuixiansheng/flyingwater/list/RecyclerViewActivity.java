package com.feishuixiansheng.flyingwater.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.feishuixiansheng.flyingwater.Bean;
import com.feishuixiansheng.flyingwater.R;
import com.feishuixiansheng.flyingwater.adapter.RecyclerViewAdapter;
import com.feishuixiansheng.flyingwater.annotation.LayoutId;
import com.feishuixiansheng.flyingwater.base.BaseListActivity;

import java.util.ArrayList;
import java.util.List;

@LayoutId(R.layout.activity_recycler_view)
public class RecyclerViewActivity extends BaseListActivity<Bean,RecyclerView,RecyclerViewAdapter> {

    @Override
    protected void initDate() {
        List<Bean> beans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Bean bean1 = new Bean();
            bean1.setName("bean"+i);
            beans.add(bean1);
        }
        adapter.setList(beans );
    }

    @Override
    public void onItemClickListner(View v, int position, Bean bean) {
        Toast.makeText(this,"点击name"+bean.getName()+position,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void createView() {

    }

    @Override
    public void onItemLongClickListner(View v, int position, Bean bean) {
        Toast.makeText(this,"长点即点击name"+bean.getName()+position,Toast.LENGTH_LONG).show();
    }

}
