package com.feishuixiansheng.flyingwater.list;

import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.feishuixiansheng.flyingwater.Bean;
import com.feishuixiansheng.flyingwater.R;
import com.feishuixiansheng.flyingwater.adapter.RecyclerViewAdapter;
import com.feishuixiansheng.flyingwater.annotation.LayoutId;
import com.feishuixiansheng.flyingwater.annotation.TitleBarInfo;
import com.feishuixiansheng.flyingwater.base.BaseListActivity;

import java.util.ArrayList;
import java.util.List;

@LayoutId(R.layout.activity_grid_view)
@TitleBarInfo(titleText = "GridView")
public class GridViewActivity extends BaseListActivity<Bean,GridView,RecyclerViewAdapter> {

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
        Toast.makeText(this,"点击name"+bean.getName()+"list",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void createView() {
//        setTitle("ListViewActivity");

    }

    @Override
    public void onItemLongClickListner(View v, int position, Bean bean) {
        Toast.makeText(this,"Long点击name"+bean.getName()+"list",Toast.LENGTH_LONG).show();
    }

}
