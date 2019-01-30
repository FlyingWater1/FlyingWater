package com.feishuixiansheng.flyingwater.permission;

import com.feishuixiansheng.flyingwater.R;
import com.feishuixiansheng.flyingwater.annotation.LayoutId;
import com.feishuixiansheng.flyingwater.base.BaseActivity;

@LayoutId(R.layout.activity_permission1)
public class Permission2Activity extends BaseActivity {

    @Override
    protected void initDate() {

    }

    @Override
    protected void initView() {
        getFragmentManager().beginTransaction()
                .add(R.id.frame, new Blank2Fragment()).commit();
    }

}
