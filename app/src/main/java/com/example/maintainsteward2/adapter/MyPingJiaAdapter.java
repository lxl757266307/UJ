package com.example.maintainsteward2.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.maintainsteward2.R;
import com.example.maintainsteward2.bean.MyPingJiaListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/10.
 */

public class MyPingJiaAdapter extends BaseAbstactRecycleAdapter<MyPingJiaListBean.DataBean, MyPingJiaAdapter.ViewHolder> {

    Context context;


    public MyPingJiaAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void getViewHolder(ViewHolder baseViewHolder, final int position, List<MyPingJiaListBean.DataBean> mList) {


        MyPingJiaListBean.DataBean dataBean = mList.get(position);

        String add_time = dataBean.getAdd_time();
        String content = dataBean.getContent();
        String evaluation = dataBean.getEvaluation();
        String icon = dataBean.getIcon();
        String phone_number = dataBean.getPhone_number();
        String worker_name = dataBean.getWorker_name();
        String star_level = dataBean.getStar_level();
        final List<String> smeta = dataBean.getSmeta();
        for (int i = 0; i < smeta.size(); i++) {
            ImageView imageView = (ImageView) LayoutInflater.from(context).inflate(R.layout.imageview, null);
            final int finalI = i;
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onPhotoClick != null) {
                        onPhotoClick.onPhotoClick(smeta.get(finalI));
                    }
                }
            });
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(context).load(smeta.get(i)).into(imageView);
            baseViewHolder.layoutPhotoList.addView(imageView);

        }

        baseViewHolder.txtTime.setText(add_time);

        Glide.with(context).load(icon).into(baseViewHolder.imgPhoto);
        baseViewHolder.txtName.setText(worker_name);
        baseViewHolder.txtPhone.setText(phone_number);
        baseViewHolder.txtContent.setText(content);
        baseViewHolder.cbFuwu1.setClickable(false);
        baseViewHolder.cbFuwu2.setClickable(false);
        baseViewHolder.cbFuwu3.setClickable(false);
        baseViewHolder.cbFuwu4.setClickable(false);
        baseViewHolder.cbFuwu5.setClickable(false);

        switch (star_level) {
            case "1":
                baseViewHolder.cbFuwu1.setChecked(true);
                break;
            case "2":
                baseViewHolder.cbFuwu1.setChecked(true);
                baseViewHolder.cbFuwu2.setChecked(true);
                break;
            case "3":
                baseViewHolder.cbFuwu1.setChecked(true);
                baseViewHolder.cbFuwu2.setChecked(true);
                baseViewHolder.cbFuwu3.setChecked(true);

                break;
            case "4":
                baseViewHolder.cbFuwu1.setChecked(true);
                baseViewHolder.cbFuwu2.setChecked(true);
                baseViewHolder.cbFuwu3.setChecked(true);
                baseViewHolder.cbFuwu4.setChecked(true);
                break;
            case "5":
                baseViewHolder.cbFuwu1.setChecked(true);
                baseViewHolder.cbFuwu2.setChecked(true);
                baseViewHolder.cbFuwu3.setChecked(true);
                baseViewHolder.cbFuwu4.setChecked(true);
                baseViewHolder.cbFuwu5.setChecked(true);

                break;
        }


    }

    @Override
    public ViewHolder creatHolder(ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.my_pingjia_item, parent, false);
        return new ViewHolder(v);
    }

    OnPhotoClick onPhotoClick;

    public void setOnPhotoClick(OnPhotoClick onPhotoClick) {
        this.onPhotoClick = onPhotoClick;
    }

    public interface OnPhotoClick {

        void onPhotoClick(String url);

    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_photo)
        ImageView imgPhoto;
        @BindView(R.id.txt_name)
        TextView txtName;
        @BindView(R.id.txt_phone)
        TextView txtPhone;
        @BindView(R.id.cb_fuwu1)
        CheckBox cbFuwu1;
        @BindView(R.id.cb_fuwu2)
        CheckBox cbFuwu2;
        @BindView(R.id.cb_fuwu3)
        CheckBox cbFuwu3;
        @BindView(R.id.cb_fuwu4)
        CheckBox cbFuwu4;
        @BindView(R.id.cb_fuwu5)
        CheckBox cbFuwu5;
        @BindView(R.id.txt_content)
        TextView txtContent;
        @BindView(R.id.layout_photo_list)
        LinearLayout layoutPhotoList;
        @BindView(R.id.txt_time)
        TextView txtTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
