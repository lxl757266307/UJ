package com.example.maintainsteward.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.utils.ToolUitls;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/16.
 */

public class PhotoListAdapter extends BaseAbstactRecycleAdapter<Bitmap, PhotoListAdapter.ViewHolder> {


    Context context;


    public PhotoListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void getViewHolder(ViewHolder baseViewHolder, final int position, final List<Bitmap> mList) {

        baseViewHolder.imgPhoto.setImageBitmap(mList.get(position));

        if (6 == position) {
            baseViewHolder.imgPhoto.setVisibility(View.GONE);
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

    }

    @Override
    public ViewHolder creatHolder(ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.imagview, parent, false);

        return new ViewHolder(view);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_photo)
        ImageView imgPhoto;

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

    }
}



