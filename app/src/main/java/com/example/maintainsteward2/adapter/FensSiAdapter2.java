package com.example.maintainsteward2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward2.R;
import com.example.maintainsteward2.bean.FenSiBean2;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/10/16.
 */

public class FensSiAdapter2 extends BaseExpandableListAdapter {

    Context context;


    public FensSiAdapter2(Context context) {
        this.context = context;
    }

    List<FenSiBean2.DataBeanX.DataBean> dataBeanList;

    public List<FenSiBean2.DataBeanX.DataBean> getDataBeanList() {
        return dataBeanList;
    }

    public void setDataBeanList(List<FenSiBean2.DataBeanX.DataBean> dataBeanList) {
        this.dataBeanList = dataBeanList;
    }

    @Override
    public int getGroupCount() {
        return dataBeanList == null ? 0 : dataBeanList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return dataBeanList.get(groupPosition).getChild() == null ? 0 : dataBeanList.get(groupPosition).getChild().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return dataBeanList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return dataBeanList.get(groupPosition).getChild().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    GroupHolder groupHolder = null;

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.rv_item_fensi, parent, false);
            groupHolder = new GroupHolder(convertView);
            convertView.setTag(groupHolder);
        } else {
            groupHolder = (GroupHolder) convertView.getTag();
        }
        FenSiBean2.DataBeanX.DataBean dataBean = dataBeanList.get(groupPosition);

        groupHolder.txtName.setText(dataBean.getUser_nicename());
        groupHolder.txtFensiNumber.setText(dataBean.getCreate_time().substring(0, 11));
        groupHolder.txtNumber.setText(dataBean.getCount());


        return convertView;
    }

    ChildHolder childHolder = null;

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.rv_item_fensi_child, parent, false);
            childHolder = new ChildHolder(convertView);
            convertView.setTag(childHolder);
        } else {
            childHolder = (ChildHolder) convertView.getTag();
        }

        FenSiBean2.DataBeanX.DataBean.ChildBean childBean = dataBeanList.get(groupPosition).getChild().get(childPosition);

        childHolder.txtName.setText(childBean.getUser_nicename());
        childHolder.txtNumber.setText(childBean.getCount());
        childHolder.txtFensiNumber.setText(childBean.getCreate_time().substring(0, 11));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    static class GroupHolder {
        @BindView(R.id.img_fensijibie)
        ImageView imgFensijibie;
        @BindView(R.id.txt_name)
        TextView txtName;
        @BindView(R.id.txt_number)
        TextView txtNumber;
        @BindView(R.id.txt_fensi_number)
        TextView txtFensiNumber;
        @BindView(R.id.img_open)
        ImageView imgOpen;
        @BindView(R.id.layout_group_item)
        LinearLayout layoutGroupItem;

        public GroupHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class ChildHolder {
        @BindView(R.id.img_fensijibie)
        ImageView imgFensijibie;
        @BindView(R.id.txt_name)
        TextView txtName;
        @BindView(R.id.txt_number)
        TextView txtNumber;
        @BindView(R.id.txt_fensi_number)
        TextView txtFensiNumber;

        public ChildHolder(View convertView) {
            ButterKnife.bind(this, convertView);
        }
    }

}
