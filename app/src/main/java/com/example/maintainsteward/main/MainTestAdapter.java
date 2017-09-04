package com.example.maintainsteward.main;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 */

public class MainTestAdapter extends BaseAdapter {

    List<String> list;

    public MainTestAdapter(List<String> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView textView = new TextView(convertView.getContext());
        textView.setText(list.get(position));
        return textView;
    }
}
