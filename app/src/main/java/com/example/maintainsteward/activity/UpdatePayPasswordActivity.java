package com.example.maintainsteward.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.LoginCallBackBean;
import com.example.maintainsteward.bean.PublicBean;
import com.example.maintainsteward.bean.UserInfoBean;
import com.example.maintainsteward.bean.YanZhengMaCallBackBean;
import com.example.maintainsteward.mvp_presonter.LoginPresonter;
import com.example.maintainsteward.mvp_presonter.UpdatePayPasswordPresonter;
import com.example.maintainsteward.mvp_presonter.UserInfoPresonter;
import com.example.maintainsteward.mvp_view.LoginListener;
import com.example.maintainsteward.mvp_view.OnUpdatePayPasswordListener;
import com.example.maintainsteward.utils.MD5;
import com.example.maintainsteward.utils.ToolUitls;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/20.
 */

public class UpdatePayPasswordActivity extends BaseActivity implements OnUpdatePayPasswordListener, LoginListener {

    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_old_phone)
    TextView txtOldPhone;
    @BindView(R.id.edit_yan_zheng_ma)
    EditText editYanZhengMa;
    @BindView(R.id.txt_send_yan_zheng_ma)
    TextView txtSendYanZhengMa;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.edit_password_sure)
    EditText editPasswordSure;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    UpdatePayPasswordPresonter payPasswordPresonter;

    String phone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        phone = this.getIntent().getStringExtra("phone");
        setContentView(R.layout.activity_update_pay_password);
        ButterKnife.bind(this);
        txtOldPhone.setText(phone);

        payPasswordPresonter = new UpdatePayPasswordPresonter();
        payPasswordPresonter.setOnUpdatePayPasswordListener(this);
        loginPresonter = new LoginPresonter();
        loginPresonter.setLoginListener(this);

    }

    LoginPresonter loginPresonter;

    boolean isRuning;
    int time = 60;
    public static final String TAG = "UpdatePayPasswordActivity";

    @OnClick({R.id.layout_back, R.id.txt_send_yan_zheng_ma, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.txt_send_yan_zheng_ma: {
                if (txtOldPhone.getText().toString().equals("")) {
                    ToolUitls.toast(this, "手机号码不能为空");
                    return;
                }


                if (isRuning) {
                    return;
                } else {
                    isRuning = true;
                }
                String timeStamp = System.currentTimeMillis() + "";
                TreeMap<String, String> map = new TreeMap<>();
                String phone = txtOldPhone.getText().toString().trim();
                map.put("phone", phone);
                map.put("type", "7");
                map.put("timestamp", timeStamp);
                String sign = ToolUitls.getSign(map);

                loginPresonter.getCode(phone, "7", timeStamp, sign, Contacts.KEY);

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        while (isRuning) {

                            time--;
                            handler.sendEmptyMessage(1);

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            if (time == 1) {
                                handler.sendEmptyMessage(2);
                                isRuning = false;
                                time = 60;
                            }

                        }

                    }
                }).start();
            }
            break;
            case R.id.btn_submit: {

                SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
                String oldPassword = sharedPreferences.getString("payPassword", null);

                if (oldPassword == null) {
                    ToolUitls.toast(this, "您还未设置支付密码");
                    return;
                }


                if (editYanZhengMa.getText().toString().equals("")) {
                    ToolUitls.toast(this, "验证码不能为空");
                    return;
                }

                if (editPassword.getText().toString().equals("") || editPasswordSure.getText().toString().equals("")) {

                    ToolUitls.toast(this, "密码不能为空");
                    return;
                }

                if (!editPassword.getText().toString().equals(editPasswordSure.getText().toString())) {
                    ToolUitls.toast(this, "两次输入密码不一致");
                    return;
                }


                String id = sharedPreferences.getString("id", null);
                String timeStamp = System.currentTimeMillis() + "";
                String newPassword = MD5.getMessageDigest(editPassword.getText().toString().getBytes());
                TreeMap<String, String> map = new TreeMap<>();
                String phone = txtOldPhone.getText().toString();
                map.put("user_id", id);
                map.put("phone", phone);
                map.put("newpwd", newPassword);
                map.put("oldpwd", oldPassword);
                map.put("timestamp", timeStamp);
                String sign = ToolUitls.getSign(map);
                payPasswordPresonter.updatePayPassword(id, phone, newPassword, oldPassword, timeStamp, sign, Contacts.KEY);
            }
            break;
        }
    }

    @Override
    public void onSetPasswordSucess(PublicBean bean) {


    }

    @Override
    public void onUpdatePasswordSucess(PublicBean bean) {
        switch (bean.getStatus()) {
            case "1":
                ToolUitls.toast(this, "修改成功");
                handler.sendEmptyMessageDelayed(3, 1000);
                break;
            default:
                ToolUitls.toast(this, bean.getData());
                break;
        }

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    txtSendYanZhengMa.setText(time + "后重新发送");

                    break;
                case 2:
                    txtSendYanZhengMa.setText("发送验证码");

                    break;
                case 3:
//                    Intent intent = new Intent();
//                    intent.putExtra("phone", txtOldPhone.getText().toString());
//                    setResult(RESULT_OK, intent);
                    finish();
                    break;
            }


        }
    };
    String statusCode = "";

    @Override
    public void getVerifyCode(YanZhengMaCallBackBean backBean) {

        statusCode = backBean.getStatus();
        switch (backBean.getStatus()) {
            case "1":
                ToolUitls.toast(this, "获取成功");
                break;
            default:
                String data = backBean.getData();
                ToolUitls.toast(this, "" + data);
                break;
        }
    }

    @Override
    public void getLoginCallBack(LoginCallBackBean body) {

    }
}
