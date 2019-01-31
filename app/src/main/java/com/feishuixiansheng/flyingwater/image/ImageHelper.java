package com.feishuixiansheng.flyingwater.image;

import android.content.Context;
import android.widget.ImageView;

/**
 * @author dupengfei
 * @create 2019/1/31 0031
 * @Describe
 */
public class ImageHelper implements ImageLoader {

    private static ImageLoader mImageLoader;

    private static final ImageHelper ourInstance = new ImageHelper();

    public static ImageHelper getInstance() {
        return ourInstance;
    }

    private ImageHelper() {

    }

    /**
     * 这里需要在自定义Application中实现
     * @param imageLoader
     */
    public static void init(ImageLoader imageLoader){
        mImageLoader = imageLoader;
    }

    @Override
    public void setImage(Context context, ImageView imageView, String url) {
        mImageLoader.setImage(context,imageView,url);
    }
}
