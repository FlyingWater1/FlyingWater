package com.feishuixiansheng.flyingwater.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @author dupengfei
 * @create 2019/1/31 0031
 * @Describe
 */
public class GlideImage implements ImageLoader {

    @Override
    public void setImage(Context context, ImageView imageView, String url) {
        Glide.with(context).load(url).into(imageView);
    }
}
