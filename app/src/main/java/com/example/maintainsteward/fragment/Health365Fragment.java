package com.example.maintainsteward.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.activity.WriteTaoCanInfoActivity;
import com.example.maintainsteward.adapter.TaoCanListAdapter;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.TaoCanListBean;
import com.example.maintainsteward.mvp_presonter.TaoCanPresonter;
import com.example.maintainsteward.mvp_view.TaoCanListener;
import com.example.maintainsteward.utils.ToolUitls;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/9/18.
 */

public class Health365Fragment extends Fragment implements TaoCanListener {
    @BindView(R.id.elv_list)
    ExpandableListView elvList;
    Unbinder unbinder;

    TaoCanListAdapter adapter;
    @BindView(R.id.txt_service_total_serviceinfo)
    TextView txtServiceTotalServiceinfo;
    @BindView(R.id.txt_goumai)
    TextView txtGoumai;

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
        TextView textView = new TextView(getActivity());
        textView.setText(getString(R.string.description));
        textView.setTextColor(Color.parseColor("#da0a0a"));
        textView.setTextSize(15);
        textView.setPadding(10, 10, 10, 10);
        textView.setBackgroundColor(Color.parseColor("#f4f4f4"));
        elvList.addFooterView(textView);

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

                CheckBox checkBox = (CheckBox) v.findViewById(R.id.cb_xuanzhong);

                if (groupData != null) {

                    if (groupData.get(groupPosition).getSet_meal().get(childPosition).isClickAble()) {

                        List<TaoCanListBean.DataBean.SetMealDataBean.SetMealBean> set_meal = groupData.get(groupPosition).getSet_meal();

                        for (int i = 0; i < set_meal.size(); i++) {
                            if (i == childPosition) {
                                set_meal.get(i).setCheck(true);
                            } else {
                                set_meal.get(i).setCheck(false);
                            }
                        }
                        adapter.setGroupData(groupData);
                        adapter.notifyDataSetChanged();
                    }


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

        switch (bean.getStatus()) {
            case "1":
                TaoCanListBean.DataBean data = bean.getData();
                groupData = data.getSet_meal_data();


                for (int i = 0; i < groupData.size(); i++) {
                    TaoCanListBean.DataBean.SetMealDataBean setMealDataBean = groupData.get(i);
                    List<TaoCanListBean.DataBean.SetMealDataBean.SetMealBean> set_meal = setMealDataBean.getSet_meal();

                    if (set_meal.size() == 1) {
                        set_meal.get(0).setCheck(true);
                        set_meal.get(0).setClickAble(false);
                    } else {
                        set_meal.get(0).setCheck(true);
                        for (int x = 0; x < set_meal.size(); x++) {
                            set_meal.get(x).setClickAble(true);
                        }
                    }
                }
                adapter.setGroupData(groupData);
                elvList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                for (int i = 0; i < groupData.size(); i++) {
                    elvList.expandGroup(i);
                }
                break;
        }
    }

    @OnClick(R.id.txt_goumai)
    public void onViewClicked() {
        if (groupData == null) {
            return;
        }
        JSONObject jsonObject = new JSONObject();

        for (int i = 0; i < groupData.size(); i++) {
            TaoCanListBean.DataBean.SetMealDataBean setMealDataBean = groupData.get(i);
            List<TaoCanListBean.DataBean.SetMealDataBean.SetMealBean> set_meal = setMealDataBean.getSet_meal();
            for (int x = 0; x < set_meal.size(); x++) {

                TaoCanListBean.DataBean.SetMealDataBean.SetMealBean setMealBean = set_meal.get(x);
                try {
                    if (setMealBean.isCheck()) {
                        String limit_num = setMealBean.getLimit_num();
                        String item_id = setMealBean.getItem_id();
                        jsonObject.put(item_id, limit_num);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }

        Intent intent = new Intent(getActivity(), WriteTaoCanInfoActivity.class);
        intent.putExtra("msg", jsonObject.toString());
        intent.putExtra("group", (Serializable) groupData);
        startActivity(intent);
    }
}
