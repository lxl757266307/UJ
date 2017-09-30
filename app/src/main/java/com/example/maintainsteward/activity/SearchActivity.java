package com.example.maintainsteward.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.application.MyApplication;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.HotWordBean;
import com.example.maintainsteward.mvp_presonter.SearchPresonter;
import com.example.maintainsteward.mvp_view.SearchHotWordListener;
import com.example.maintainsteward.utils.ToolUitls;
import com.example.maintainsteward.view.MyViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/6.
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class SearchActivity extends BaseActivity implements SearchHotWordListener, TextView.OnEditorActionListener {


    @BindView(R.id.edit_search_activity)
    EditText editSearchActivity;
    @BindView(R.id.img_qingkong_search_activity)
    ImageView imgQingkongSearchActivity;
    @BindView(R.id.txt_cancle_search_activity)
    TextView txtCancleSearchActivity;
    @BindView(R.id.vg_search_activity)
    MyViewGroup vgSearchActivity;


    @OnClick({R.id.txt_cancle_search_activity, R.id.img_qingkong_search_activity})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.txt_cancle_search_activity:
                finish();
                break;
            case R.id.img_qingkong_search_activity:
                editSearchActivity.setText("");
                break;

        }
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getActivitiesList().add(this);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        editSearchActivity.setOnEditorActionListener(this);
        initPresonter();
    }

    SearchPresonter presonter;
    String timestamp = "";
    String sign = "";

    private void initPresonter() {
        presonter = new SearchPresonter();
        presonter.setSearchHotWordListener(this);

        timestamp = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("timestamp", timestamp);
        sign = ToolUitls.getSign(map);
        presonter.getHotWord(timestamp, sign, Contacts.KEY);


    }

    public static final String TAG = "SearchActivity";

    private void initListener() {
        for (int i = 0; i < btnArray.length; i++) {

            btnArray[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    TextView item = (TextView) v;

                    Intent intent = new Intent(SearchActivity.this, SearInfoActivity.class);
                    intent.putExtra("keyword", item.getText().toString().trim());
                    SearchActivity.this.startActivity(intent);

                }
            });
        }
    }

    TextView[] btnArray;


    @Override
    public void onSearchSucess(HotWordBean hotWordBean) {
        switch (hotWordBean.getStatus()) {
            case "1":

                List<HotWordBean.DataBean> data = hotWordBean.getData();

                if (data != null && data.size() > 0) {
                    btnArray = new TextView[data.size()];
                    for (int i = 0; i < data.size(); i++) {
                        TextView button = (TextView) LayoutInflater.from(this).inflate(R.layout.button, null);
                        button.setText(data.get(i).getName());
                        btnArray[i] = button;
                        vgSearchActivity.addView(button);
                    }
                }

                if (btnArray != null && btnArray.length > 0) {
                    initListener();
                }

                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            if (presonter != null && !"".equals(sign) && !"".equals(timestamp)) {
//                presonter.getHotWord(timestamp, sign, Contacts.KEY);

                Intent intent = new Intent(SearchActivity.this, SearInfoActivity.class);
                intent.putExtra("keyword", editSearchActivity.getText().toString().trim());
                SearchActivity.this.startActivity(intent);

            }
        }
        return false;
    }
}
