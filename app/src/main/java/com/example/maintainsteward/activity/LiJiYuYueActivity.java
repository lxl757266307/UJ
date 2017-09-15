package com.example.maintainsteward.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.adapter.OrderPeiJianListAdapter;
import com.example.maintainsteward.adapter.OrderServiceListAdapter;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.SearviceInfoBean;
import com.example.maintainsteward.bean.ServiceGoodsGetBean;
import com.example.maintainsteward.main.MainActivity;
import com.example.maintainsteward.utils.ToolUitls;
import com.example.maintainsteward.view.MyListView;
import com.example.maintainsteward.view.MyViewGroup;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/15.
 */

public class LiJiYuYueActivity extends BaseActivity {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_service_title_lijiyuyue)
    TextView txtServiceTitleLijiyuyue;
    @BindView(R.id.layout_choose_service_lijiyuyue)
    LinearLayout layoutChooseServiceLijiyuyue;
    @BindView(R.id.lv_service_list_lijiyuyue)
    MyListView lvServiceListLijiyuyue;
    @BindView(R.id.lv_peijian_list_lijiyuyue)
    MyListView lvPeijianListLijiyuyue;
    @BindView(R.id.layout_choose_address_lijiyuyue)
    LinearLayout layoutChooseAddressLijiyuyue;
    @BindView(R.id.txt_address_lijiyuyue)
    TextView txtAddressLijiyuyue;
    @BindView(R.id.txt_username_lijiyuyue)
    TextView txtUsernameLijiyuyue;
    @BindView(R.id.txt_phone_lijiyuyue)
    TextView txtPhoneLijiyuyue;
    @BindView(R.id.layout_address_lijiyuyue)
    LinearLayout layoutAddressLijiyuyue;
    @BindView(R.id.layout_choose_repair_time_lijiyuyue)
    LinearLayout layoutChooseRepairTimeLijiyuyue;
    @BindView(R.id.edit_repair_descrription)
    EditText editRepairDescrription;
    @BindView(R.id.vg_photo_lijiyuyue)
    MyViewGroup vgPhotoLijiyuyue;

    public static final int REQUEST_CODE = 1;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    int page = 1;

    @OnClick({R.id.layout_back, R.id.layout_choose_service_lijiyuyue, R.id.layout_choose_address_lijiyuyue, R.id.layout_choose_repair_time_lijiyuyue})
    public void click(View view) {

        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.layout_choose_service_lijiyuyue:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("page", 1);
                startActivity(intent);
                break;
            case R.id.layout_choose_address_lijiyuyue:
//
                SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
                String id = sharedPreferences.getString("id", null);
//
                TreeMap<String, String> map = new TreeMap<>();
                String timeStamp = System.currentTimeMillis() + "";
                map.put("timestamp", timeStamp);
                map.put("user_id", id);
                map.put("page", page + "");
                String sign = ToolUitls.getSign(map);

                ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "GetAddress?" +
                        "user_id=" + id + "&timestamp="
                        + timeStamp + "&page="
                        + page + "&sign=" + sign
                        + "&key=" + Contacts.KEY);

                Intent intent1 = new Intent(this, AddressManagerActivity.class);
                startActivityForResult(intent1, REQUEST_CODE);

                break;
            case R.id.layout_choose_repair_time_lijiyuyue:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        setContentView(R.layout.activity_lijiyuyue);
        ButterKnife.bind(this);

        initList();

    }

    /*服务列表*/
    List<SearviceInfoBean.DataBean> data;
    /*配件列表*/
    List<ServiceGoodsGetBean.DataBean> peiJian;

    private void initList() {

        txtTitle.setText(title);

        if (data != null && data.size() > 0) {
            layoutChooseServiceLijiyuyue.setVisibility(View.GONE);
            OrderServiceListAdapter orderServiceListAdapter = new OrderServiceListAdapter(data, this);
            lvServiceListLijiyuyue.setAdapter(orderServiceListAdapter);
            orderServiceListAdapter.notifyDataSetChanged();
        }
        if (peiJian != null && peiJian.size() > 0) {
            OrderPeiJianListAdapter orderPeiJianListAdapter = new OrderPeiJianListAdapter(peiJian, this);
            lvPeijianListLijiyuyue.setAdapter(orderPeiJianListAdapter);
            orderPeiJianListAdapter.notifyDataSetChanged();
        }

    }


    String title = "";

    private void initData() {
        data = (List<SearviceInfoBean.DataBean>) this.getIntent().getSerializableExtra("service");
        peiJian = (List<ServiceGoodsGetBean.DataBean>) this.getIntent().getSerializableExtra("peijian");
        title = this.getIntent().getStringExtra("title");
    }
}
