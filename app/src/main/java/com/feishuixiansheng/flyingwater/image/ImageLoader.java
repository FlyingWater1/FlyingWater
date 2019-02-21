package com.feishuixiansheng.flyingwater.image;

import android.content.Context;
import android.widget.ImageView;

/**
 * @author dupengfei
 * @create 2019/1/31 0031
 * @Describe
 */
public interface ImageLoader {
    void setImage(Context context, ImageView imageView, String url);
}
