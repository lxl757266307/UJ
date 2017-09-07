package com.example.maintainsteward.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.maintainsteward.R;
import com.example.maintainsteward.bean.SearchKeyWordBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/7.
 */

public class SearchKeyWordAdapter extends BaseAbstactRecycleAdapter<SearchKeyWordBean.DataBean, SearchKeyWordAdapter.ViewHolder> {

    Context context;
    String keyWord;

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public SearchKeyWordAdapter(Context context, String keyWord) {
        this.context = context;
        this.keyWord = keyWord;
    }

    @Override
    public void getViewHolder(ViewHolder baseViewHolder, int position, List<SearchKeyWordBean.DataBean> list) {
        baseViewHolder.imgSearchInfoItem.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(context).load(list.get(position).getLogourl()).into(baseViewHolder.imgSearchInfoItem);
        String name = list.get(position).getName();
        boolean contains = name.contains(keyWord);
        if (contains) {
            baseViewHolder.txtNameSearchInfoItem.setText(name.substring(0, keyWord.length()));
            baseViewHolder.txtTypeSearchInfoItem.setText(name.substring(keyWord.length()));
        }

        String min_price = list.get(position).getMin_price();
        if ("0".equals(min_price)) {
            baseViewHolder.txtPriceSearchInfoItem.setText("面议");
        } else {
            baseViewHolder.txtPriceSearchInfoItem.setText(min_price);
        }


//        baseViewHolder.
    }

    @Override
    public ViewHolder creatHolder(ViewGroup parent) {

        View view = LayoutInflater.from(context).inflate(R.layout.search_info_item, parent, false);

        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_search_info_item)
        ImageView imgSearchInfoItem;
        @BindView(R.id.txt_name_search_info_item)
        TextView txtNameSearchInfoItem;
        @BindView(R.id.txt_type_search_info_item)
        TextView txtTypeSearchInfoItem;
        @BindView(R.id.txt_price_search_info_item)
        TextView txtPriceSearchInfoItem;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
