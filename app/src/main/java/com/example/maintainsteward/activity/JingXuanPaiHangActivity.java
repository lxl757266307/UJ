package com.example.maintainsteward.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.adapter.JingXuanListAdapter;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.JingXuanBean;
import com.example.maintainsteward.mvp_presonter.JingXuanPresonter;
import com.example.maintainsteward.mvp_view.JingXuanListener;
import com.example.maintainsteward.utils.ToolUitls;
import com.example.maintainsteward.view.MyListView;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/23.
 */

public class JingXuanPaiHangActivity extends BaseActivity implements JingXuanListener, OnScrollListener, AdapterView.OnItemClickListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.lv_list)
    MyListView lvList;
    @BindView(R.id.img_fabu)
    ImageView imgFabu;
    @BindView(R.id.img_huidaodingbu)
    ImageView imgHuidaodingbu;

    JingXuanPresonter presonter;
    JingXuanListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jingxuan);
        ButterKnife.bind(this);
        data = new ArrayList<>();
        lvList.setOnScrollListener(this);
        lvList.setOnItemClickListener(this);
        presonter = new JingXuanPresonter();
        presonter.setJingXuanListener(this);
        adapter = new JingXuanListAdapter(this);
        lvList.setAdapter(adapter);
        getList();
    }

    int page = 1;

    public void getList() {
        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("timestamp", time);
        map.put("page", page + "");
        String sign = ToolUitls.getSign(map);

//        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "CarefullySelectedSort?" + "page=" + page + "&sign=" + sign + "&timestamp=" + time + "&key=" + Contacts.KEY);

        presonter.getJingXuan(page + "", time, sign, Contacts.KEY);
    }


    public void getSugestion() {

        SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
        String id = sharedPreferences.getString("id", null);
        String time = System.currentTimeMillis() + "";
        String typeId = "1";
        String contents = "hahahaha";
        String type = "5";
        String img1 = "5";
        String img2 = "5";
        String img3 = "5";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("timestamp", time);
        map.put("user_id", id);
        map.put("type_id", typeId);
        map.put("type", type);
        map.put("contents", contents);
        map.put("img1", img1 + "");
        map.put("img2", img2 + "");
        map.put("img3", img3 + "");
        String sign = ToolUitls.getSign(map);

    }

    @OnClick({R.id.img_fabu, R.id.img_huidaodingbu, R.id.layout_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_fabu:
                startActivity(new Intent(this, KuaiSuFaBuActivity.class));

                break;
            case R.id.img_huidaodingbu:
                lvList.setSelection(0);
                lvList.setSelectionAfterHeaderView();
                lvList.smoothScrollToPosition(0);
                adapter.notifyDataSetChanged();
                imgHuidaodingbu.setVisibility(View.GONE);
                break;
            case R.id.layout_back:
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            finish();
        }

        return super.onKeyDown(keyCode, event);
    }

    List<JingXuanBean.DataBean> data;

    @Override
    public void getJingXuan(JingXuanBean bean) {
        switch (bean.getStatus()) {
            case "1":
                List<JingXuanBean.DataBean> beansList = bean.getData();
                for (int i = 0; i < beansList.size(); i++) {
                    JingXuanBean.DataBean dataBean = beansList.get(i);
                    dataBean.setIndex(i);
                    data.add(dataBean);
                }
                adapter.setData(this.data);
                adapter.notifyDataSetChanged();


                break;
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        if (adapter != null) {

            adapter.notifyDataSetChanged();
        }


        if (firstVisibleItem == 0) {
            View firstVisibleItemView = view.getChildAt(0);
            if (firstVisibleItemView != null && firstVisibleItemView.getTop() == 0) {
                imgHuidaodingbu.setVisibility(View.GONE);
            } else {
                imgHuidaodingbu.setVisibility(View.VISIBLE);

            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ToolUitls.print("------","dddddddd");

        String index = data.get(position).getId();
        Intent intent = new Intent(this, ServiceInfoActivity.class);
        intent.putExtra("id", index + "");
        startActivity(intent);

    }
}
