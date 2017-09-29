package com.example.maintainsteward.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.bean.AddressListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/15.
 */

public class AddressListAdapter extends BaseAbstactRecycleAdapter<AddressListBean.DataBean, AddressListAdapter.ViewHolder> {


    Context context;


    public AddressListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void getViewHolder(ViewHolder baseViewHolder, final int position, List<AddressListBean.DataBean> mList) {

        baseViewHolder.txtUserNameAddressmanager.setText(mList.get(position).getUser_name());
        baseViewHolder.txtUserAddressAddressmanager.setText(mList.get(position).getAddress());
        baseViewHolder.txtUserPhoneAddressmanager.setText(mList.get(position).getTel());

        baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAddressListListener != null) {
                    onAddressListListener.onItemClick(position);
                }
            }
        });
        baseViewHolder.layoutShanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAddressListListener != null) {
                    onAddressListListener.onDelete(position);
                }
            }
        });
        baseViewHolder.txtEditorAddressmanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAddressListListener != null) {
                    onAddressListListener.onEdit(position);
                }
            }
        });

    }

    @Override
    public ViewHolder creatHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_address_manager, parent, false);

        return new ViewHolder(view);
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.layout_shanchu)
        LinearLayout layoutShanchu;
        @BindView(R.id.txt_user_name_addressmanager)
        TextView txtUserNameAddressmanager;
        @BindView(R.id.txt_user_phone_addressmanager)
        TextView txtUserPhoneAddressmanager;
        @BindView(R.id.txt_user_address_addressmanager)
        TextView txtUserAddressAddressmanager;
        @BindView(R.id.txt_editor_addressmanager)
        TextView txtEditorAddressmanager;
        @BindView(R.id.txt_delete_addressmanager)
        TextView txtDeleteAddressmanager;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    OnAddressListListener onAddressListListener;

    public void setOnAddressListListener(OnAddressListListener onAddressListListener) {
        this.onAddressListListener = onAddressListListener;
    }

    public interface OnAddressListListener {

        void onEdit(int postion);

        void onDelete(int postion);

        void onItemClick(int postion);
    }
}
