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
import com.example.maintainsteward.adapter.DialogFragmentDistrictListViewAdapter;
import com.example.maintainsteward.bean.CityListBean;
import com.example.maintainsteward.inter.OnLocationItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/11.
 */

public class DistrictFragment extends Fragment implements AdapterView.OnItemClickListener {


    List<String> mDictrict;
    @BindView(R.id.lv_fragment_dialog)
    ListView lvFragmentDialog;
    Unbinder unbinder;

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

    List<CityListBean.DataBean.DistrictBean> list;

    public List<CityListBean.DataBean.DistrictBean> getList() {
        return list;
    }

    public void setList(List<CityListBean.DataBean.DistrictBean> list) {
        this.list = list;
        if (list != null) {
            adapter = new DialogFragmentDistrictListViewAdapter(list, getActivity());
            lvFragmentDialog.setAdapter(adapter);
        }
    }

    DialogFragmentDistrictListViewAdapter adapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lvFragmentDialog.setOnItemClickListener(this);
        if (list != null) {
            adapter = new DialogFragmentDistrictListViewAdapter(list, getActivity());
            lvFragmentDialog.setAdapter(adapter);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        TextView textView = (TextView) view.findViewById(R.id.txt_lv_item);

        for (int i = 0; i < list.size(); i++) {
            list.get(i).setCheck(false);
        }
        list.get(position).setCheck(true);
        adapter.setmData(list);
        adapter.notifyDataSetChanged();
        if (mOnLocationItemClickListener != null && list != null) {
            mOnLocationItemClickListener.districtClickListener(list.get(position).getDistrict_name(),list.get(position).getId());
        }
    }

}
