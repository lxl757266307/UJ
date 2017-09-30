package com.example.maintainsteward.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.application.MyApplication;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.MySetMealBean;
import com.example.maintainsteward.bean.TaoCanListBean;
import com.example.maintainsteward.main.MainActivity;
import com.example.maintainsteward.mvp_presonter.MySetMealPresonter;
import com.example.maintainsteward.mvp_view.MySetMealListener;
import com.example.maintainsteward.utils.ToolUitls;
import com.example.maintainsteward.view.MyTableRow;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/27.
 */

public class TaoCanGouMaiSucessActivity extends BaseActivity implements MySetMealListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_address)
    TextView txtAddress;
    @BindView(R.id.txt_youxiaoqi)
    TextView txtYouxiaoqi;
    @BindView(R.id.layout_table)
    TableLayout layoutTable;
    List<TaoCanListBean.DataBean.SetMealDataBean> groupData;
    MySetMealBean.DataBean data;
    MySetMealPresonter mySetMealPresonter;
    @BindView(R.id.txt_tuikuan)
    TextView txtTuikuan;

    int page = 0;
    String flag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getActivitiesList().add(this);

        data = (MySetMealBean.DataBean) getIntent().getSerializableExtra("data");
        page = this.getIntent().getIntExtra("page", -1);
        flag = this.getIntent().getStringExtra("flag");
        setContentView(R.layout.activity_taocangoumaichenggong);
        ButterKnife.bind(this);
        MyTableRow myTableRow1 = new MyTableRow(this);
        myTableRow1.setTitle("服务类别", "服务内容", "服务次数", "状态", "操作");
        layoutTable.addView(myTableRow1);

        if (data != null) {
            setTable();
        } else {
            getMySetMeal();
        }

    }

    private void setTable() {
        String address = data.getAddress();
        String term = data.getTerm();

        txtAddress.setText(address);
        txtYouxiaoqi.setText(term);


        List<MySetMealBean.DataBean.SetMealBean> set_meal = data.getSet_meal();

        for (int i = 0; i < set_meal.size(); i++) {
            MySetMealBean.DataBean.SetMealBean setMealBean = set_meal.get(i);

            MyTableRow myTableRow = new MyTableRow(this);
            myTableRow.setViews(setMealBean);
            layoutTable.addView(myTableRow);

        }
    }

    private void getMySetMeal() {
        mySetMealPresonter = new MySetMealPresonter();
        mySetMealPresonter.setMySetMealListener(this);
        SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
        String id = sharedPreferences.getString("id", null);

        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("user_id", id);
        map.put("timestamp", time);
        String sign = ToolUitls.getSign(map);

        mySetMealPresonter.getMySetMeal(id, time, sign, Contacts.KEY);
    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {

        if (flag != null && flag.equals("ServiceInfoActivity")) {
            finish();
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        if (page != -1) {
            intent.putExtra("page", page);
        }
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (flag != null && flag.equals("ServiceInfoActivity")) {
                finish();
                return true;
            }
            Intent intent = new Intent(this, MainActivity.class);
            if (page != -1) {
                intent.putExtra("page", page);
            }
            startActivity(intent);
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onLoadMySetMeal(MySetMealBean bean) {
        MySetMealBean.DataBean data = bean.getData();
        String address = data.getAddress();
        String term = data.getTerm();

        txtAddress.setText(address);
        txtYouxiaoqi.setText(term);
        List<MySetMealBean.DataBean.SetMealBean> set_meal = bean.getData().getSet_meal();

        for (int i = 0; i < set_meal.size(); i++) {
            MySetMealBean.DataBean.SetMealBean setMealBean = set_meal.get(i);

            MyTableRow myTableRow = new MyTableRow(this);
            myTableRow.setViews(setMealBean);
            layoutTable.addView(myTableRow);

        }
    }

    @OnClick(R.id.txt_tuikuan)
    public void onBtnClicked() {


    }
}
