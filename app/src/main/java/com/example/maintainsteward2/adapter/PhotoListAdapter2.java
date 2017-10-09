package com.example.maintainsteward2.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.maintainsteward2.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/16.
 */

public class PhotoListAdapter2 extends BaseAbstactRecycleAdapter<Bitmap, PhotoListAdapter2.ViewHolder> {


    Context context;


    public PhotoListAdapter2(Context context) {
        this.context = context;
    }

    @Override
    public void getViewHolder(ViewHolder baseViewHolder, final int position, final List<Bitmap> mList) {

        baseViewHolder.imgPhoto.setImageBitmap(mList.get(position));

        if (3== position) {
            baseViewHolder.imgPhoto.setVisibility(View.GONE);
        }

        if (position == mList.size() - 1) {
            baseViewHolder.imgDelete.setVisibility(View.GONE);
        } else {
            baseViewHolder.imgDelete.setVisibility(View.VISIBLE);
        }

        baseViewHolder.imgPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == mList.size() - 1) {
                    if (onPhotoClickListener != null) {
                        onPhotoClickListener.onPhotoClick();
                    }
                } else {
                    return;
                }

            }
        });
        baseViewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPhotoClickListener != null) {
                    onPhotoClickListener.onPhotoDelete(position);
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


        void onPhotoClick();

        void onPhotoDelete(int position);

    }
}



