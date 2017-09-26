package com.example.maintainsteward.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.adapter.TaoCanListAdapter;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.TaoCanListBean;
import com.example.maintainsteward.mvp_presonter.TaoCanPresonter;
import com.example.maintainsteward.mvp_view.TaoCanListener;
import com.example.maintainsteward.utils.ToolUitls;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/9/18.
 */

public class Health365Fragment extends Fragment implements TaoCanListener {
    @BindView(R.id.elv_list)
    ExpandableListView elvList;
    Unbinder unbinder;

    TaoCanListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_365, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    TaoCanPresonter presonter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presonter = new TaoCanPresonter();
        presonter.setTaoCanListener(this);
        adapter = new TaoCanListAdapter(getActivity());
        getList();
        elvList.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        elvList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                ToolUitls.print("------------", "子条目点击了");
                CheckBox checkBox = (CheckBox) v.findViewById(R.id.cb_xuanzhong);

                if (groupData != null) {
                    if (checkBox.isChecked()) {
                        checkBox.setChecked(false);
                        groupData.get(groupPosition).getSet_meal().get(childPosition).setCheck(false);
                    } else {
                        checkBox.setChecked(true);
                        groupData.get(groupPosition).getSet_meal().get(childPosition).setCheck(true);
                    }
                    adapter.setGroupData(groupData);
                    adapter.notifyDataSetChanged();
                }


                return false;
            }
        });


    }


    public void getList() {
        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("timestamp", time);
        String sign = ToolUitls.getSign(map);
//        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "SetMealDetails?" + "timestamp=" + time + "&sign=" + sign + "&key=" + Contacts.KEY);
        presonter.taoCanList(time, sign, Contacts.KEY);

    }

    List<TaoCanListBean.DataBean.SetMealDataBean> groupData;

    @Override
    public void onLoadListSucess(TaoCanListBean bean) {

        ToolUitls.print("-----------", "bean===" + bean);
        switch (bean.getStatus()) {
            case "1":
                TaoCanListBean.DataBean data = bean.getData();
                groupData = data.getSet_meal_data();
                adapter.setGroupData(groupData);
                elvList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                for (int i = 0; i < groupData.size(); i++) {
                    elvList.expandGroup(i);
                }
                break;
        }
    }
}
