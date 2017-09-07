package com.example.maintainsteward.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.LoginCallBackBean;
import com.example.maintainsteward.bean.YanZhengMaCallBackBean;
import com.example.maintainsteward.main.MainActivity;
import com.example.maintainsteward.mvp_presonter.LoginPresonter;
import com.example.maintainsteward.mvp_view.LoginListener;
import com.example.maintainsteward.utils.PermissionRegisterUtils;
import com.example.maintainsteward.utils.ToolUitls;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Administrator on 2017/8/5.
 */

public class LoginActivity extends BaseActivity implements OnCheckedChangeListener,
        View.OnClickListener,
        LoginListener,
        EasyPermissions.PermissionCallbacks,
        TextWatcher {


    @BindView(R.id.edit_user_name_login)
    EditText editUserNameLogin;
    @BindView(R.id.edit_user_password_login)
    EditText editUserPasswordLogin;
    @BindView(R.id.txt_yanzhengma_login)
    TextView txtYanzhengmaLogin;
    @BindView(R.id.cb_xieyi_login)
    CheckBox cbXieyiLogin;
    @BindView(R.id.txt_accept_login)
    TextView txtAcceptLogin;
    @BindView(R.id.txt_xieyi_login)
    TextView txtXieyiLogin;
    @BindView(R.id.btn_login)
    Button btnLogin;

    public static final String TAG = "LoginActivity";

    LoginPresonter loginPresonter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        PermissionRegisterUtils.registerPermission(this);

        initSharedPrefence();
        initListener();
        initPresonter();
    }

    SharedPreferences sharedPreferences;

    private void initSharedPrefence() {

        sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);


    }

    private void initPresonter() {
        loginPresonter = new LoginPresonter();
        loginPresonter.setLoginListener(this);
    }

    private void initListener() {
        editUserNameLogin.addTextChangedListener(this);
        cbXieyiLogin.setOnCheckedChangeListener(this);
        btnLogin.setOnClickListener(this);
        txtYanzhengmaLogin.setOnClickListener(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked) {
            btnLogin.setBackgroundResource(R.mipmap.denglu_press);
        } else {
            btnLogin.setBackgroundResource(R.mipmap.denglu_normal);
        }

    }

    String phone = "";
    String type = "";
    String timeStamp = "";
    String sign = "";
    boolean isLoading;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login: {

                if (phone.equals("")) {
                    return;
                }
                String password = editUserPasswordLogin.getText().toString();
                if (cbXieyiLogin.isChecked() &&
                        !"".equals(password) && password.length() >= 4) {
                    ToolUitls.print(TAG, "点击了");
                    String myUUID = ToolUitls.getMyUUID(this);
                    String time = System.currentTimeMillis() + "";
                    TreeMap<String, String> map = new TreeMap<>();
                    map.put("phone", phone);
                    map.put("code", password);
                    map.put("chinaid", myUUID);
                    map.put("ioschinaid", "1234");
                    map.put("login_type", "0");
                    map.put("timestamp", time);
                    String sign = ToolUitls.getSign(map);
                    ToolUitls.print(TAG, "sign2222222222===" + sign);
                    loginPresonter.login(phone, password, myUUID, "1234", "0", time, sign, Contacts.KEY);
                } else {
                    return;
                }
            }
            break;
            case R.id.txt_yanzhengma_login:

                boolean isFirst = sharedPreferences.getBoolean("isFirst", false);
                if (isFirst) {
                    type = "1";
                    sharedPreferences.edit().putBoolean("isFirst", false).commit();
                } else {
                    type = "3";
                }
                if (!isLoading) {
                    isLoading = true;
                    txtYanzhengmaLogin.setBackground(getResources().getDrawable(R.drawable.circle_box_grey));
                    txtYanzhengmaLogin.setTextColor(Color.parseColor("#bbbbbb"));

                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            while (isLoading) {
                                mills--;
                                if (mills == 1) {
                                    isLoading = false;
                                    handler.sendEmptyMessage(2);
                                    return;
                                }
                                handler.sendEmptyMessage(1);
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }


                        }
                    }).start();

                    phone = editUserNameLogin.getText().toString();
                    timeStamp = System.currentTimeMillis() + "";

                    TreeMap<String, String> map = new TreeMap<>();
                    map.put("phone", phone);
                    map.put("type", type);
                    map.put("timestamp", System.currentTimeMillis() + "");
                    sign = ToolUitls.getSign(map);

                    loginPresonter.getCode(phone, type, timeStamp, sign, Contacts.KEY);

                } else {
                    return;
                }
                break;
        }
    }

    int mills = 60;

    Handler handler = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:

                    txtYanzhengmaLogin.setText("" + mills + "s后重发");

                    break;
                case 2:
                    isLoading = false;
                    txtYanzhengmaLogin.setBackground(getResources().getDrawable(R.drawable.circle_box_3r_red));
                    txtYanzhengmaLogin.setTextColor(Color.parseColor("#da0a0a"));
                    txtYanzhengmaLogin.setText("发送验证码");
                    mills = 60;
                    break;
            }
        }
    };


    String verifycode = "";

    @Override
    public void getVerifyCode(YanZhengMaCallBackBean backBean) {
        switch (backBean.getStatus()) {
            case "1":
                verifycode = backBean.getData();
                ToolUitls.print(TAG, "code==" + verifycode);
                break;
        }

    }

    @Override
    public void getLoginCallBack(LoginCallBackBean body) {
        switch (body.getStatus()) {
            case "1":
                ToolUitls.print(TAG, "BODY==" + body);
                LoginCallBackBean.DataBean data = body.getData();
                String id = data.getId();
                String user_phone = data.getUser_phone();
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString("id", id);
                edit.putBoolean("online", true);
                edit.putString("phone", user_phone);
                edit.commit();

                startActivity(new Intent(this, MainActivity.class));

                break;
        }

    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void afterTextChanged(Editable s) {
        if ("".equals(editUserNameLogin.getText().toString())) {
            handler.sendEmptyMessage(2);

        }
    }
}
