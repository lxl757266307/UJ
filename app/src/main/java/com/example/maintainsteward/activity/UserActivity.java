package com.example.maintainsteward.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.bumptech.glide.Glide;
import com.example.maintainsteward.R;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.PublicBean;
import com.example.maintainsteward.bean.UserInfoBean;
import com.example.maintainsteward.mvp_presonter.UpLoadPhotoPresonter;
import com.example.maintainsteward.mvp_presonter.UserInfoPresonter;
import com.example.maintainsteward.mvp_view.OnUpLoadPhotoListener;
import com.example.maintainsteward.mvp_view.UserInfoListener;
import com.example.maintainsteward.utils.PhotoUtils;
import com.example.maintainsteward.utils.ToolUitls;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/9/20.
 */

public class UserActivity extends BaseActivity implements UserInfoListener, OnUpLoadPhotoListener {
    @BindView(R.id.img_touxiang)
    CircleImageView imgTouxiang;
    @BindView(R.id.layout_edit_photo)
    LinearLayout layoutEditPhoto;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.layout_edit_name)
    LinearLayout layoutEditName;
    @BindView(R.id.txt_xingbie)
    TextView txtXingbie;
    @BindView(R.id.layout_edit_sex)
    LinearLayout layoutEditSex;
    @BindView(R.id.txt_shengri)
    TextView txtShengri;
    @BindView(R.id.layout_edit_shengri)
    LinearLayout layoutEditShengri;
    @BindView(R.id.txt_phone)
    TextView txtPhone;
    @BindView(R.id.layout_edit_phone)
    LinearLayout layoutEditPhone;
    @BindView(R.id.layout_password)
    LinearLayout layoutPassword;
    @BindView(R.id.layout_edit_password)
    LinearLayout layoutEditPassword;
    UserInfoBean.DataBean data;

    public static final String TAG = "UserActivity";
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;

    UserInfoPresonter userInfoPresonter;
    UpLoadPhotoPresonter upLoadPhotoPresonter;
    @BindView(R.id.btn_loginOut)
    Button btnLoginOut;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = (UserInfoBean.DataBean) this.getIntent().getSerializableExtra("data");
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        initViews();
        initPresonter();


    }

    private void initPresonter() {
        userInfoPresonter = new UserInfoPresonter();
        userInfoPresonter.setUserInfoListener(this);
        upLoadPhotoPresonter = new UpLoadPhotoPresonter();
        upLoadPhotoPresonter.setListener(this);
    }

    private void initViews() {
        if (!"".equals(data.getAvatar())) {
            Glide.with(this).load(data.getAvatar()).into(imgTouxiang);
        }
        if ("".equals(data.getUser_nicename())) {
            txtName.setText("未设置昵称");
        } else {
            txtName.setText(data.getUser_nicename());
        }
        txtPhone.setText(data.getUser_phone());
        if ("".equals(data.getBirthday())) {
            txtShengri.setText("未设置生日");
        } else {
            txtShengri.setText(data.getBirthday());
        }
        if ("0".equals(data.getSex())) {
            txtXingbie.setText("未设置性别");
        } else if ("1".equals(data.getSex())) {
            txtXingbie.setText("男");
        } else if ("2".equals(data.getSex())) {
            txtXingbie.setText("女");
        }
    }

    public static final int UPDATE_NAME = 3;
    public static final int UPDATE_PHONE = 4;

    @OnClick({R.id.layout_edit_photo, R.id.layout_edit_name, R.id.layout_edit_sex, R.id.layout_edit_shengri, R.id.layout_edit_phone, R.id.layout_password, R.id.layout_edit_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_edit_photo: {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                this.startActivityForResult(intent, PHOTO_REQUEST_CODE);
            }
            break;
            case R.id.layout_edit_name: {
                Intent intent = new Intent(this, UpdateUserNameActivity.class);
                String user_name = data.getUser_nicename();
                if (!"未设置昵称".equals(user_name) || !"".equals(user_name)) {
                    intent.putExtra("name", user_name);
                }
                startActivityForResult(intent, UPDATE_NAME);

            }

            break;
            case R.id.layout_edit_sex:
                updateSex();
                break;
            case R.id.layout_edit_shengri:
                initTimePicker();
                mTimePickerView.show();
                break;
            case R.id.layout_edit_phone: {
                Intent intent = new Intent(this, UpdatePhoneActivity.class);
                intent.putExtra("phone", data.getUser_phone());
                startActivityForResult(intent, UPDATE_PHONE);

            }


            break;
            case R.id.layout_password: {

                Intent intent = new Intent(this, SetPayPasswordActivity.class);
                intent.putExtra("phone", txtPhone.getText().toString());
                startActivity(intent);

            }
            break;
            case R.id.layout_edit_password: {
                Intent intent = new Intent(this, UpdatePayPasswordActivity.class);
                intent.putExtra("phone", txtPhone.getText().toString());
                startActivity(intent);
            }
            break;
        }
    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {

        Intent intent = new Intent();
        intent.putExtra("name", txtName.getText().toString());
        intent.putExtra("bitmap", bitmap);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            Intent intent = new Intent();
            intent.putExtra("name", txtName.getText().toString());
            intent.putExtra("bitmap", bitmap);
            setResult(RESULT_OK, intent);
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    public static final int PHOTO_REQUEST_CODE = 1;
    public static final int CROP_REQUEST_CODE = 2;
    File file;
    Bitmap bitmap;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PHOTO_REQUEST_CODE:
                    Uri uri = data.getData();
                    PhotoUtils.crop(uri, this, CROP_REQUEST_CODE);
                    break;

                case CROP_REQUEST_CODE:
                    bitmap = data.getParcelableExtra("data");
                    imgTouxiang.setImageBitmap(bitmap);
                    upLoadPhotoPresonter.getToken();
                    break;

                case UPDATE_NAME:
                    String name = data.getStringExtra("name");
                    if (name != null) {
                        txtName.setText(name);
                    }
                    break;

                case UPDATE_PHONE:
                    String phone = data.getStringExtra("phone");
                    if (phone != null) {
                        txtPhone.setText(phone);
                    }

                    break;
            }
        }
    }

    public void updateUserInfo(String type, String values) {
        SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
        String id = sharedPreferences.getString("id", null);
        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("timestamp", time);
        map.put("user_id", id);
        map.put("type", type);
        map.put("values", values);
        String sign = ToolUitls.getSign(map);

        userInfoPresonter.updateUserInfo(id, type, values, time, sign, Contacts.KEY);


    }

    @Override
    public void getUserInfo(UserInfoBean bean) {

    }

    @Override
    public void editUserInfoSucess(PublicBean bean) {
        switch (bean.getStatus()) {
            case "1":
                ToolUitls.toast(this, "修改成功");
                Intent intent = new Intent(Contacts.UPDATE_USER);
                sendBroadcast(intent);

                break;
        }
    }

    @Override
    public void getToken(String token) {
        if (bitmap != null) {
            upLoadPhotoPresonter.qiNiuYunUpload(bitmap, token);
        }
    }

    @Override
    public void onUpSucess(String key) {
        updateUserInfo("avatar", key);
    }


    /**
     * 修改性别时弹出PopupWindow
     */
    PopupWindow popupWindow;
    LinearLayout linearLayout;
    RelativeLayout relativeLayout;
    Button bt_man;
    Button bt_women;
    Button bt_cancel;

    public void updateSex() {
        popupWindow = new PopupWindow(UserActivity.this);
        View view = getLayoutInflater().inflate(R.layout.sex_popupwindow,
                null);
        linearLayout = (LinearLayout) view.findViewById(R.id.ll_popup);

        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setContentView(view);

        relativeLayout = (RelativeLayout) view.findViewById(R.id.parent);
        bt_man = (Button) view.findViewById(R.id.man);
        bt_women = (Button) view.findViewById(R.id.women);
        bt_cancel = (Button) view.findViewById(R.id.item_popupwindows_cancel);
        bt_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String man = bt_man.getText().toString();
                txtXingbie.setText(man);
                updateUserInfo("sex", "1");
                linearLayout.clearAnimation();
                popupWindow.dismiss();
            }
        });
        bt_women.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String woman = bt_women.getText().toString();
                txtXingbie.setText(woman);
                updateUserInfo("sex", "2");
                linearLayout.clearAnimation();
                popupWindow.dismiss();
            }
        });
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.clearAnimation();
                popupWindow.dismiss();
            }
        });
        linearLayout.startAnimation(AnimationUtils.loadAnimation(UserActivity.this, R.anim.activity_translate_in));
        popupWindow.showAtLocation(linearLayout, Gravity.BOTTOM, 0, 0);

    }

    TimePickerView mTimePickerView;

    /* 初始化时间选择器*/
    private void initTimePicker() {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 1, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2099, 12, 31);
        //时间选择器
        mTimePickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                /*btn_Time.setText(getTime(date));*/

                txtShengri.setText(getTime(date));
                updateUserInfo("birthday", getTime(date));
                /* 按时间查询*/

            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "", "", "")
                .isCenterLabel(false)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(18)
                .setCancelColor(Color.parseColor("#fd6b07"))
                .setSubmitColor(Color.parseColor("#fd6b07"))
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setBackgroundId(0x00FFFFFF) //设置外部遮罩颜色
                .setDecorView(null)
                .build();
    }

    /* 格式化时间*/
    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @OnClick(R.id.btn_loginOut)
    public void loginOut() {

        SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean("online", false);
        edit.commit();


        startActivity(new Intent(this, LoginActivity.class));

    }
}
