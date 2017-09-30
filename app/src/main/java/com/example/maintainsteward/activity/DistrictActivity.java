package com.example.maintainsteward.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.example.maintainsteward.R;
import com.example.maintainsteward.adapter.DistrictAdapter;
import com.example.maintainsteward.application.MyApplication;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.CityListBean;
import com.example.maintainsteward.main.MainActivity;
import com.example.maintainsteward.view.MyListView;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/12.
 */

public class DistrictActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.lv_district)
    MyListView lvDistrict;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getActivitiesList().add(this);
        initData();
        setContentView(R.layout.activity_district);
        ButterKnife.bind(this);
        initAdapter();

    }

    List<CityListBean.DataBean.DistrictBean> district;
    String city = "";

    private void initData() {
        district = (List<CityListBean.DataBean.DistrictBean>) this.getIntent().getSerializableExtra("district");
        city = this.getIntent().getStringExtra("city");
    }

    private void initAdapter() {
        lvDistrict.setOnItemClickListener(this);
        DistrictAdapter districtAdapter = new DistrictAdapter(district, this);
        lvDistrict.setAdapter(districtAdapter);
        districtAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String district_name = district.get(position).getDistrict_name();

        SharedPreferences sharedPreferences = getSharedPreferences(MyApplication.LOCATION, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();

        edit.putString("city", city);
        edit.putString("district", district_name);
        edit.commit();

        Intent intent2 = new Intent(this, MainActivity.class);
        startActivity(intent2);


    }
}
