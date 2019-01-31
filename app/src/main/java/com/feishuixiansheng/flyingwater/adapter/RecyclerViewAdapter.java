package com.feishuixiansheng.flyingwater.adapter;

import android.widget.TextView;

import com.app.annotation.apt.InstanceFactory;
import com.feishuixiansheng.flyingwater.Bean;
import com.feishuixiansheng.flyingwater.R;
import com.feishuixiansheng.flyingwater.util.list.CommonAdapter;
import com.feishuixiansheng.flyingwater.util.list.CommonViewHolder;

/**
 * @author dupengfei
 * @create 2019/1/31 0031
 * @Describe
 */
@InstanceFactory
public class RecyclerViewAdapter extends CommonAdapter<Bean> {
    @Override
    protected int getLayoutId() {
        return R.layout.item_normal;
    }

    @Override
    protected void bindData(CommonViewHolder commonViewHolder, Bean bean, int i) {
        ((TextView)commonViewHolder.getView(R.id.item_tx)).setText(bean.getName());

        //支持链式调用
        commonViewHolder.setImageView(R.id.image,"https://img5.duitang.com/uploads/item/201411/07/20141107164412_v284V.jpeg");

    }

}
