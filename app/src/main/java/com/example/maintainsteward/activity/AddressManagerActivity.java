package com.example.maintainsteward.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.adapter.AddressListAdapter;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.AddressListBean;
import com.example.maintainsteward.mvp_presonter.AddressManagerPresonter;
import com.example.maintainsteward.mvp_view.GetAddressListListener;
import com.example.maintainsteward.utils.ToolUitls;
import com.example.maintainsteward.view.MyLayoutManager;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/10.
 * <p>
 * 地址管理界面
 */

public class AddressManagerActivity extends BaseActivity implements GetAddressListListener, AddressListAdapter.OnAddressListListener {
    public static final String TAG = "AddressManagerActivity";

    public static final int REQUEST_CODE = 1;
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.rv_address_manager)
    RecyclerView rvAddressManager;
    @BindView(R.id.layout_addaddress_addressmanager)
    LinearLayout layoutAddaddressAddressmanager;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manager);
        ButterKnife.bind(this);
        initPresonter();
        initRv();
        getAddress();
        registerReciver();
    }

    AddressListAdapter addressListAdapter;

    private void initRv() {

        rvAddressManager.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        addressListAdapter = new AddressListAdapter(this);
        addressListAdapter.setOnAddressListListener(this);
        rvAddressManager.setAdapter(addressListAdapter);

    }

    @OnClick(R.id.layout_addaddress_addressmanager)
    public void click() {
        Intent intent = new Intent(this, AddAddressActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(reciver);
    }

    int page = 1;
    AddressManagerPresonter presonter;

    public void initPresonter() {
        presonter = new AddressManagerPresonter();
        presonter.setListListener(this);
    }

    public void getAddress() {
        SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
        String id = sharedPreferences.getString("id", null);
        TreeMap<String, String> map = new TreeMap<>();
        String timeStamp = System.currentTimeMillis() + "";
        map.put("timestamp", timeStamp);
        map.put("user_id", id);
        map.put("page", page + "");
        String sign = ToolUitls.getSign(map);


        presonter.getAddressList(id, page + "", timeStamp, sign, Contacts.KEY);


//        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "GetAddress?" +
//                "user_id=" + id + "&timestamp="
//                + timeStamp + "&page="
//                + page + "&sign=" + sign
//                + "&key=" + Contacts.KEY);
    }

    GetAddressReciver reciver;

    public void registerReciver() {

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Contacts.ADD_ADDRESS_OK);
        reciver = new GetAddressReciver();
        this.registerReceiver(reciver, intentFilter);

    }

    List<AddressListBean.DataBean> data;

    @Override
    public void getAddressList(AddressListBean addressListBean) {

        ToolUitls.print(TAG, "addressListBean===" + addressListBean);
        switch (addressListBean.getStatus()) {
            case "1":
                data = addressListBean.getData();
                if (data != null) {
                    addressListAdapter.setList(data);
                    addressListAdapter.notifyDataSetChanged();
                }
                break;
        }

    }

    @Override
    public void onEdit(int postion) {
        AddressListBean.DataBean dataBean = data.get(postion);
        Intent intent = new Intent(this, AddAddressActivity.class);
        intent.putExtra("flag", TAG);
        intent.putExtra("data", dataBean);
        startActivity(intent);


    }

    @Override
    public void onDelete(int postion) {

//        presonter;


    }

    @Override
    public void onItemClick(int postion) {

    }

    class GetAddressReciver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {


            if (intent.getAction().equals(Contacts.ADD_ADDRESS_OK)) {

                if (presonter != null) {
                    getAddress();
                }
            }

        }
    }
}
