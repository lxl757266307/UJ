package com.example.maintainsteward.activity;

import android.content.Intent;
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
import com.example.maintainsteward.bean.PublicBean;
import com.example.maintainsteward.mvp_presonter.UpdatePayPasswordPresonter;
import com.example.maintainsteward.mvp_view.OnUpdatePayPasswordListener;
import com.example.maintainsteward.utils.ToolUitls;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/20.
 */

public class UpdatePayPasswordActivity extends BaseActivity implements OnUpdatePayPasswordListener {

    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_old_phone)
    EditText txtOldPhone;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pay_password);
        ButterKnife.bind(this);

        payPasswordPresonter = new UpdatePayPasswordPresonter();
        payPasswordPresonter.setOnUpdatePayPasswordListener(this);

    }

    boolean isRuning;
    int time = 60;

    @OnClick({R.id.layout_back, R.id.txt_send_yan_zheng_ma, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                break;
            case R.id.txt_send_yan_zheng_ma:
                if (txtOldPhone.getText().toString().equals("")) {
                    ToolUitls.toast(this, "手机号码不能为空");
                    return;
                }


                if (isRuning) {
                    return;
                } else {
                    isRuning = true;
                }
                break;
            case R.id.btn_submit:
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
}
