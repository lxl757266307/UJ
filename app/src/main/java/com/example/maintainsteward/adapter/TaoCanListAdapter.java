package com.example.maintainsteward.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.bean.TaoCanListBean;
import com.example.maintainsteward.utils.ToolUitls;

import java.util.List;

/**
 * Created by Administrator on 2017/9/26.
 */

public class TaoCanListAdapter extends BaseExpandableListAdapter {

    Context context;

    public TaoCanListAdapter(Context context) {
        this.context = context;
    }

    List<TaoCanListBean.DataBean.SetMealDataBean> groupData;

    public void setGroupData(List<TaoCanListBean.DataBean.SetMealDataBean> groupData) {
        this.groupData = groupData;


    }

    @Override
    public int getGroupCount() {
        return groupData == null ? 0 : groupData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groupData.get(groupPosition).getSet_meal() == null ? 0 : groupData.get(groupPosition).getSet_meal().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groupData.get(groupPosition).getSet_meal().get(childPosition);
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
        return true;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder = null;


        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_group_item, parent, false);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.serviceName = (TextView) convertView.findViewById(R.id.txt_group_name);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        groupViewHolder.serviceName.setText(groupData.get(groupPosition).getName());

        return convertView;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ToolUitls.print("----", "2222222222222222222");
        ChildViewHolder childViewHolder=null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.lv_child_item, parent, false);
            childViewHolder = new ChildViewHolder();
            childViewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.cb_xuanzhong);
            childViewHolder.item_name = (TextView) convertView.findViewById(R.id.txt_item_name);
            childViewHolder.price = (TextView) convertView.findViewById(R.id.txt_price);
            childViewHolder.number = (TextView) convertView.findViewById(R.id.txt_number);
            childViewHolder.days = (TextView) convertView.findViewById(R.id.txt_days);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        TaoCanListBean.DataBean.SetMealDataBean.SetMealBean setMealBean = groupData.get(groupPosition).getSet_meal().get(childPosition);
        childViewHolder.item_name.setText(setMealBean.getName());
        childViewHolder.price.setText("原价:" + setMealBean.getExpenses() + "元");
        if (setMealBean.getLimit_num().equals("-1")) {
            childViewHolder.number.setText("无限次");
        } else {
            childViewHolder.number.setText("一户一次");
        }
        childViewHolder.days.setText(setMealBean.getEnd_days() + "天");
        childViewHolder.checkBox.setChecked(setMealBean.isCheck());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    static class GroupViewHolder {
        TextView serviceName;
    }

    static class ChildViewHolder {
        CheckBox checkBox;
        TextView item_name;
        TextView price;
        TextView number;
        TextView days;
    }
}
