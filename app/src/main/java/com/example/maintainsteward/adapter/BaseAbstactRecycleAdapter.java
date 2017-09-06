package com.example.maintainsteward.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/13.
 * <p>
 * 封装 RecycleView  的适配器  后期开发 全部 用 他 方便 又 简洁 功能强大
 */

public abstract class BaseAbstactRecycleAdapter<T, X extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<T> mList;

    public void setList(List<T> mList) {
        this.mList = mList;
    }

    public List<T> getmList() {
        return mList;
    }


    public void clear() {
        if (mList != null) {
            mList.clear();
        }
    }

    public BaseAbstactRecycleAdapter() {
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        getViewHolder((X) holder, position, mList);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return creatHolder(parent);
    }

    public abstract void getViewHolder(X baseViewHolder, int position, List<T> mList);

    public abstract X creatHolder(ViewGroup parent);

}
