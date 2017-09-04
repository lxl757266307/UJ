package com.example.maintainsteward.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.adapter.DialogFragmentListViewAdapter;
import com.example.maintainsteward.inter.OnLocationItemClickListener;
import com.example.maintainsteward.utils.LocationUtils;
import com.example.maintainsteward.utils.ToolUitls;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/11.
 */

public class ProvinceFragment extends Fragment implements AdapterView.OnItemClickListener {

    public static final String TAG = "ProvinceFragment";

    List<String> mProvince;
    @BindView(R.id.lv_fragment_dialog)
    ListView lvFragmentDialog;
    Unbinder unbinder;

    public void setmProvince(List<String> mProvince) {
        this.mProvince = mProvince;
    }

    OnLocationItemClickListener mOnLocationItemClickListener;

    public void setmOnLocationItemClickListener(OnLocationItemClickListener mOnLocationItemClickListener) {
        this.mOnLocationItemClickListener = mOnLocationItemClickListener;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frament_list_view, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    List<String> list;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvFragmentDialog.setOnItemClickListener(this);
        list = LocationUtils.getPrivince();
        DialogFragmentListViewAdapter adapter = new DialogFragmentListViewAdapter(list, getActivity());
        lvFragmentDialog.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        TextView textView = (TextView) view.findViewById(R.id.txt_lv_item);
        textView.setTextColor(Color.RED);

        if (mOnLocationItemClickListener != null && list != null) {
            mOnLocationItemClickListener.provinceClickListener(list.get(position));
            ToolUitls.print(TAG, "条目被点击");
        }
    }

}
