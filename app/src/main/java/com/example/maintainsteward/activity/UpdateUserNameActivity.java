package com.example.maintainsteward.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.application.MyApplication;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.PublicBean;
import com.example.maintainsteward.bean.UserInfoBean;
import com.example.maintainsteward.mvp_presonter.UserInfoPresonter;
import com.example.maintainsteward.mvp_view.UserInfoListener;
import com.example.maintainsteward.utils.ToolUitls;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/20.
 */

public class UpdateUserNameActivity extends BaseActivity implements UserInfoListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.edit_name)
    EditText editName;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    UserInfoPresonter userInfoPresonter;
    String name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getActivitiesList().add(this);
        name = this.getIntent().getStringExtra("name");
        setContentView(R.layout.activity_update_user_name);
        ButterKnife.bind(this);
        if (name != null) {
            editName.setHint(name);
        }
        userInfoPresonter = new UserInfoPresonter();
        userInfoPresonter.setUserInfoListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    @OnClick({R.id.layout_back, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:

                finish();

                break;
            case R.id.btn_submit:

                String name = editName.getText().toString();
                if ("".equals(name)) {
                    return;
                }

                if (name.length() > 10) {
                    ToolUitls.toast(this, "昵称长度不能超过5位");
                    return;
                }

                SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
                String id = sharedPreferences.getString("id", null);
                String time = System.currentTimeMillis() + "";
                TreeMap<String, String> map = new TreeMap<>();
                map.put("timestamp", time);
                map.put("user_id", id);
                map.put("type", "user_nicename");
                map.put("values", name);
                String sign = ToolUitls.getSign(map);
                userInfoPresonter.updateUserInfo(id, "user_nicename", name, time, sign, Contacts.KEY);

                break;
        }
    }

    @Override
    public void getUserInfo(UserInfoBean bean) {

    }

    public static final String TAG = "UpdateUserNameActivity";

    @Override
    public void editUserInfoSucess(PublicBean bean) {

        switch (bean.getStatus()) {
            case "1":
                ToolUitls.toast(this, "修改成功");
                Intent intent = new Intent();
                intent.putExtra("name", editName.getText().toString());
                setResult(RESULT_OK, intent);

                Intent intent1 = new Intent(Contacts.UPDATE_USER);
                sendBroadcast(intent1);
                finish();

                break;
        }
    }
}
