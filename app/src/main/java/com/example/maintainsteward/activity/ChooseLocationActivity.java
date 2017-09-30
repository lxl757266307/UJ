package com.example.maintainsteward.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.adapter.CityListAdapter;
import com.example.maintainsteward.application.MyApplication;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.CityListBean;
import com.example.maintainsteward.mvp_presonter.ChooseLocationPresonter;
import com.example.maintainsteward.mvp_view.ChooseLocationListener;
import com.example.maintainsteward.utils.LocationUtils;
import com.example.maintainsteward.utils.ToolUitls;
import com.example.maintainsteward.view.MyListView;

import java.io.Serializable;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/12.
 */

public class ChooseLocationActivity extends BaseActivity implements ChooseLocationListener, AdapterView.OnItemClickListener {
    public static final String TAG = "ChooseLocationActivity";

    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_city_and_district_chooselocation)
    TextView txtCityAndDistrictChooselocation;
    @BindView(R.id.lv_chooselocation)
    MyListView lvChooselocation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getActivitiesList().add(this);
        setContentView(R.layout.activity_choose);
        ButterKnife.bind(this);
        lvChooselocation.setOnItemClickListener(this);
        initData();
    }


    private void initData() {
        ChooseLocationPresonter presonter = new ChooseLocationPresonter();
        presonter.setChooseLocationListener(this);
        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("timestamp", time);
        String sign = ToolUitls.getSign(map);
        presonter.getCityList(time, sign, Contacts.KEY);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    List<CityListBean.DataBean> data;
    CityListAdapter cityListAdapter;

    @Override
    public void getCityList(CityListBean cityListBean) {


        switch (cityListBean.getStatus()) {
            case "1":
                data = cityListBean.getData();
                LocationUtils.data = data;
                cityListAdapter = new CityListAdapter(this, data);
                lvChooselocation.setAdapter(cityListAdapter);
                cityListAdapter.notifyDataSetChanged();

                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (data != null && data.size() > 0) {
            CityListBean.DataBean dataBean = data.get(position);

            List<CityListBean.DataBean.DistrictBean> district = dataBean.getDistrict();

            Intent intent = new Intent(this, DistrictActivity.class);
            intent.putExtra("district", (Serializable) district);
            intent.putExtra("city", data.get(position).getCity_name());
            startActivity(intent);

        }

    }
}
