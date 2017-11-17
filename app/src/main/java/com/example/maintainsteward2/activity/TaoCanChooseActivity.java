package com.example.maintainsteward2.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward2.R;
import com.example.maintainsteward2.application.MyApplication;
import com.example.maintainsteward2.base.BaseActivity;
import com.example.maintainsteward2.base.Contacts;
import com.example.maintainsteward2.bean.MySetMealBean;
import com.example.maintainsteward2.mvp_presonter.MySetMealPresonter;
import com.example.maintainsteward2.mvp_view.MySetMealListener;
import com.example.maintainsteward2.utils.ToolUitls;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/8.
 */

public class TaoCanChooseActivity extends BaseActivity implements MySetMealListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.layout_365)
    LinearLayout layout365;
    @BindView(R.id.layout_zixuan)
    LinearLayout layoutZixuan;
    MySetMealPresonter mySetMealPresonter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getActivitiesList().add(this);
        setContentView(R.layout.activity_taocanchoose);
        ButterKnife.bind(this);
        mySetMealPresonter = new MySetMealPresonter();
        mySetMealPresonter.setMySetMealListener(this);
        getMySetMeal();

    }

    @OnClick(R.id.layout_back)
    public void onLayoutBackClicked() {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick(R.id.layout_365)
    public void onLayout365Clicked() {
        if (data != null && set_meal != null && set_meal.size() > 0) {
            Intent intent = new Intent(this, TaoCanGouMaiSucessActivity.class);
            intent.putExtra("data", data);
            intent.putExtra("page", 0);
            startActivity(intent);
        } else {
            startActivity(new Intent(this, TaoCanActivity.class));
        }
    }

    @OnClick(R.id.layout_zixuan)
    public void onLayoutZixuanClicked() {
        startActivity(new Intent(this, ZiXuanTaoCanActivity.class));
    }


    public void getMySetMeal() {
        SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, Activity.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", null);

        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("user_id", id);
        map.put("timestamp", time);
        String sign = ToolUitls.getSign(map);

//        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "MySetMeal?" + "user_id=" + id + "&timestamp=" + time + "&sign=" + sign + "&key=" + Contacts.KEY);
        mySetMealPresonter.getMySetMeal(id, time, sign, Contacts.KEY);
    }

    List<MySetMealBean.DataBean.SetMealBean> set_meal;
    MySetMealBean.DataBean data;

    @Override
    public void onLoadMySetMeal(MySetMealBean bean) {
        switch (bean.getStatus()) {
            case "1":
                data = bean.getData();
                set_meal = data.getSet_meal();
                break;
        }

    }
}
