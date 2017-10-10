package com.example.maintainsteward2.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.maintainsteward2.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/16.
 */

public class PhotoListAdapter3 extends BaseAbstactRecycleAdapter<String, PhotoListAdapter3.ViewHolder> {


    Context context;


    public PhotoListAdapter3(Context context) {
        this.context = context;
    }

    @Override
    public void getViewHolder(ViewHolder baseViewHolder, final int position, final List<String> mList) {

        Glide.with(context).load(mList.get(position)).into(baseViewHolder.imgPhoto);
        baseViewHolder.imgDelete.setVisibility(View.GONE);
        baseViewHolder.imgPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (onPhotoClickListener != null) {
                    onPhotoClickListener.onPhotoClick(mList.get(position));
                }
            }
        });

    }


    @Override
    public ViewHolder creatHolder(ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.imagview, parent, false);

        return new ViewHolder(view);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_photo)
        ImageView imgPhoto;
        @BindView(R.id.img_delete)
        ImageView imgDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    OnPhotoClickListener onPhotoClickListener;

    public void setOnPhotoClickListener(OnPhotoClickListener onPhotoClickListener) {
        this.onPhotoClickListener = onPhotoClickListener;
    }

    public interface OnPhotoClickListener {


        void onPhotoClick(String url);

        void onPhotoDelete(int position);

    }
}



