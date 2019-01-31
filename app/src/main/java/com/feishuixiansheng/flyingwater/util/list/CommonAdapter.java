package com.feishuixiansheng.flyingwater.util.list;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SpinnerAdapter;

import java.util.List;

/**
 * @author dupengfei
 * 2019/1/31 0031
 * notifyDataSetChanged 只对RecyclerView有效
 *  ListView，GridView等AbsListView的更新应该使用notifyCommonAdapter();或者notifyDataSetInvalidated();
 *  没有验证
 *
 */
public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> implements ListAdapter, SpinnerAdapter {

    private List<T> mList;
    private OnItemClickListner onItemClickListner;//单击事件
    private OnItemLongClickListner onItemLongClickListner;//长按单击事件
    private boolean isRecyclerViewAdapter;

    public void setList(List<T> mList) {
        this.mList = mList;
        notifyCommonAdapter();
    }

    public void setIsRecyclerView(boolean isRecyclerViewAdapter) {
        this.isRecyclerViewAdapter = isRecyclerViewAdapter;
    }

    public void notifyCommonAdapter() {
        if (isRecyclerViewAdapter) {
            notifyDataSetChanged();
        } else {
            mDataSetObservable.notifyChanged();
        }
    }

    @NonNull
    @Override
    public CommonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(getLayoutId(), viewGroup, false);
        return new CommonViewHolder(view);
    }

    protected abstract int getLayoutId();

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(@NonNull final CommonViewHolder commonViewHolder, final int i) {
        bindData(commonViewHolder, mList.get(i), i);
        commonViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListner != null) {
                    onItemClickListner.onItemClickListner(v, i, mList.get(i));
                }
            }
        });

        commonViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemLongClickListner != null) {
                    onItemLongClickListner.onItemLongClickListner(v, i, mList.get(i));
                }
                return false;
            }
        });
    }

    protected abstract void bindData(CommonViewHolder commonViewHolder, T t, int i);

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setOnItemClickListner(OnItemClickListner<T> onItemClickListner) {
        this.onItemClickListner = onItemClickListner;
    }

    public void setOnItemLongClickListner(OnItemLongClickListner<T> onItemLongClickListner) {
        this.onItemLongClickListner = onItemLongClickListner;
    }

    public interface OnItemClickListner<T> {
        void onItemClickListner(View v, int position, T bean);
    }

    public interface OnItemLongClickListner<T> {
        void onItemLongClickListner(View v, int position, T bean);
    }

    private final DataSetObservable mDataSetObservable = new DataSetObservable();
    private CharSequence[] mAutofillOptions;

    public void registerDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.registerObserver(observer);
    }

    public void unregisterDataSetObserver(DataSetObserver observer) {
        mDataSetObservable.unregisterObserver(observer);
    }

    /**
     * Notifies the attached observers that the underlying data has been changed
     * and any View reflecting the data set should refresh itself.
     */
//    public void notifyDataSetChanged() {
//        mDataSetObservable.notifyChanged();
//    }

    /**
     * Notifies the attached observers that the underlying data is no longer valid
     * or available. Once invoked this adapter is no longer valid and should
     * not report further data set changes.
     */
    public void notifyDataSetInvalidated() {
        mDataSetObservable.notifyInvalidated();
    }

    public boolean areAllItemsEnabled() {
        return true;
    }

    public boolean isEnabled(int position) {
        return true;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    public int getItemViewType(int position) {
        return 0;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public boolean isEmpty() {
        return getCount() == 0;
    }

    @Override
    public CharSequence[] getAutofillOptions() {
        return mAutofillOptions;
    }

    /**
     * Sets the value returned by {@link #getAutofillOptions()}
     */
    public void setAutofillOptions(@Nullable CharSequence... options) {
        mAutofillOptions = options;
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressWarnings("unchecked")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CommonViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(), parent, false); //加载布局
            holder = new CommonViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (CommonViewHolder) convertView.getTag();
        }
        bindData(holder, mList.get(position), position);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListner != null) {
                    onItemClickListner.onItemClickListner(v, position, mList.get(position));
                }
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemLongClickListner != null) {
                    onItemLongClickListner.onItemLongClickListner(v, position, mList.get(position));
                }
                return false;
            }
        });
        return convertView;
    }
}
