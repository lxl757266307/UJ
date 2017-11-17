package com.example.maintainsteward2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.maintainsteward2.R;
import com.example.maintainsteward2.adapter.ZiXuanListAdapter;
import com.example.maintainsteward2.application.MyApplication;
import com.example.maintainsteward2.base.BaseActivity;
import com.example.maintainsteward2.base.Contacts;
import com.example.maintainsteward2.bean.ZiXuanListBean;
import com.example.maintainsteward2.mvp_presonter.ZiXuanListPresonter;
import com.example.maintainsteward2.mvp_view.OnZiXuanListListener;
import com.example.maintainsteward2.utils.ToolUitls;
import com.example.maintainsteward2.view.MyListView;
import com.example.maintainsteward2.view.MyViewGroup;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/8.
 */

public class ZiXuanTaoCanActivity extends BaseActivity implements OnZiXuanListListener, ZiXuanListAdapter.OnServiceChangeListener {

    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.lv_zixuan)
    MyListView lvZixuan;
    @BindView(R.id.layout_table)
    TableLayout layoutTable;
    @BindView(R.id.txt_taocanjia)
    TextView txtTaocanjia;
    @BindView(R.id.txt_yuanjia)
    TextView txtYuanjia;
    @BindView(R.id.txt_goumai)
    TextView txtGoumai;
    @BindView(R.id.group)
    MyViewGroup group;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getActivitiesList().add(this);
        setContentView(R.layout.activity_zixuan);
        ButterKnife.bind(this);
        initLv();

        getZiXuanTaoCan();
    }

    ZiXuanListPresonter ziXuanListPresonter;
    ZiXuanListAdapter ziXuanListAdapter;
    List<ZiXuanListBean.DataBean.SetMealDataBean> list;

    private void initLv() {
        list = new ArrayList<>();
        ziXuanListAdapter = new ZiXuanListAdapter(this);
        ziXuanListAdapter.setOnServiceChangeListener(this);
        lvZixuan.setAdapter(ziXuanListAdapter);
        ziXuanListPresonter = new ZiXuanListPresonter();
        ziXuanListPresonter.setOnZiXuanListListener(this);


    }

    @OnClick(R.id.layout_back)
    public void back() {
        finish();
    }

    double zheKou = 0;

    @OnClick(R.id.txt_goumai)
    public void payFor() {
        Intent intent = new Intent(this, ZiXuanXinXiActivity.class);
        intent.putExtra("zhe", zheKou);
        intent.putExtra("list", (Serializable) list);
        intent.putExtra("yuanJia", yuanJia);
        intent.putExtra("taoCanJia", taoCanJia);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void getZiXuanTaoCan() {
        TreeMap<String, String> map = new TreeMap<>();
        String timeStamp = System.currentTimeMillis() + "";
        map.put("timestamp", timeStamp);
        String sign = ToolUitls.getSign(map);
//        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "SelfService?" + "timestamp=" + timeStamp + "&sign=" + sign + "&key=" + Contacts.KEY);
        ziXuanListPresonter.getZiXuanList(timeStamp, sign, Contacts.KEY);

    }

    List<ZiXuanListBean.DataBean.SetMealDataBean> set_meal_data;

    @Override
    public void getZiXuanBean(ZiXuanListBean bean) {
        switch (bean.getStatus()) {
            case "1":

                ZiXuanListBean.DataBean data = bean.getData();
                set_meal_data = data.getSet_meal_data();
                ziXuanListAdapter.setSet_meal_data(set_meal_data);
                ziXuanListAdapter.notifyDataSetChanged();

                break;
            default:
                break;
        }
    }

    @Override
    public void addService(int position) {
        if (set_meal_data != null) {
            ZiXuanListBean.DataBean.SetMealDataBean setMealDataBean = set_meal_data.get(position);
            setMealDataBean.setCount(setMealDataBean.getCount() + 1);
            ziXuanListAdapter.setSet_meal_data(set_meal_data);
            ziXuanListAdapter.notifyDataSetChanged();
            getPrice();
        }
    }

    @Override
    public void reduceService(int position) {

        if (set_meal_data != null) {
            ZiXuanListBean.DataBean.SetMealDataBean setMealDataBean = set_meal_data.get(position);
            int count = setMealDataBean.getCount();
            if (count == 0) {
                return;
            }
            setMealDataBean.setCount(setMealDataBean.getCount() - 1);
            ziXuanListAdapter.setSet_meal_data(set_meal_data);
            ziXuanListAdapter.notifyDataSetChanged();
            getPrice();
        }
    }

    double yuanJia = 0;
    double taoCanJia = 0;

    public void getPrice() {
        list.clear();
        group.removeAllViews();

        int count = 0;

        for (int i = 0; i < set_meal_data.size(); i++) {
            ZiXuanListBean.DataBean.SetMealDataBean setMealDataBean = set_meal_data.get(i);
            if (setMealDataBean.getCount() > 0) {
                list.add(setMealDataBean);
            }

            yuanJia += Double.parseDouble(setMealDataBean.getExpenses()) * setMealDataBean.getCount();
            count += setMealDataBean.getCount();
        }

        txtYuanjia.setText("原价:￥" + yuanJia + "元");
        if (count == 2) {
            zheKou = 0.95;
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String format = decimalFormat.format(yuanJia * zheKou);
            taoCanJia = Double.parseDouble(format);
            txtTaocanjia.setText(format);
        } else if (count == 3) {
            zheKou = 0.9;
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String format = decimalFormat.format(yuanJia * zheKou);
            taoCanJia = Double.parseDouble(format);
            txtTaocanjia.setText(format);
        } else if (count >= 4) {
            zheKou = 0.85;
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            String format = decimalFormat.format(yuanJia * zheKou);
            taoCanJia = Double.parseDouble(format);
            txtTaocanjia.setText(format);
        } else {
            zheKou = 1;
            txtTaocanjia.setText(yuanJia + "");
            taoCanJia = yuanJia;
        }

        int height = 0;
        if (list.size() > 0) {
            group.setVisibility(View.VISIBLE);
        } else {
            group.setVisibility(View.INVISIBLE);
        }

        for (int i = 0; i < list.size(); i++) {
            ZiXuanListBean.DataBean.SetMealDataBean setMealDataBean = list.get(i);
            TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.button, null);
            textView.setText(setMealDataBean.getName() + "X" + setMealDataBean.getCount());
            group.addView(textView);
        }
    }

    @OnClick(R.id.txt_goumai)
    public void onViewClicked() {
    }
}
