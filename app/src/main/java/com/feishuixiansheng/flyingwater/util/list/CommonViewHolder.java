package com.feishuixiansheng.flyingwater.util.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.feishuixiansheng.flyingwater.image.ImageHelper;
import com.feishuixiansheng.flyingwater.image.ImageLoader;

/**
 * @author dupengfei
 * @create 2019/1/31 0031
 * @Describe
 */
public class CommonViewHolder extends RecyclerView.ViewHolder {

    public CommonViewHolder(@NonNull View itemView) {
        super(itemView);
        this.mViews = new SparseArray<View>();
    }

    private SparseArray<View> mViews;

    public View getView(int viewId) {
        View view = mViews.get(viewId);

        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }

        return view;
    }

    public CommonViewHolder setText(int viewId,String text){
        View view = getView(viewId);
        if (view instanceof TextView){
            ((TextView)view).setText(text);
        }
        return this;
    }

    public CommonViewHolder setOnClickListener(int viewId,View.OnClickListener onClickListener){
        getView(viewId).setOnClickListener(onClickListener);
        return this;
    }

    public CommonViewHolder setText(int viewId,String text,View.OnClickListener onClickListener){
        setText(viewId,text);
        setOnClickListener(viewId,onClickListener);
        return this;
    }

    public CommonViewHolder setImageView(int viewId,String imageUrl){
        View view = getView(viewId);
        if (view instanceof ImageView){
            ImageHelper.getInstance().setImage(itemView.getContext(),(ImageView) view,imageUrl);
        }
        return this;
    }

    public CommonViewHolder setImageView(int viewId,String imageUrl,View.OnClickListener onClickListener){
        setImageView(viewId,imageUrl);
        setOnClickListener(viewId,onClickListener);
        return this;
    }

}
