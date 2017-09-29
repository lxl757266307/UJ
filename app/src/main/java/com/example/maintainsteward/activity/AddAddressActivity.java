package com.example.maintainsteward.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.AddressBean;
import com.example.maintainsteward.bean.AddressListBean;
import com.example.maintainsteward.bean.CityListBean;
import com.example.maintainsteward.fragment.MyDialogFragment;
import com.example.maintainsteward.mvp_presonter.AddAddressPresonter;
import com.example.maintainsteward.mvp_presonter.ChooseLocationPresonter;
import com.example.maintainsteward.mvp_view.ChooseLocationListener;
import com.example.maintainsteward.utils.ToolUitls;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/5.
 */

public class AddAddressActivity extends FragmentActivity implements MyDialogFragment.OnAddressChoosedListener,
        AddAddressPresonter.OnAddAddressListener, ChooseLocationListener {

    public static final String TAG = "AddAddressActivity";


    /*名字*/
    @BindView(R.id.edit_user_name_add_address)
    EditText mEditUserNameAddAddress;
    /*手机号*/
    @BindView(R.id.edit_user_phone_add_address)
    EditText mEditUserPhoneAddAddress;
    /*头像*/
    @BindView(R.id.img_add_address)
    ImageView mImgAddAddress;
    /*地区*/
    @BindView(R.id.txt_user_district_add_address)
    TextView mTxtUserDistrictAddAddress;
    /*详细地址*/
    @BindView(R.id.edit_user_minute_add_address)
    EditText eDitUserMinuteAddAddress;
    /*提交按钮*/
    @BindView(R.id.btn_submit_add_address)
    Button mBtnSubmitAddAddress;

    /* 查询通讯录 请求码*/
    public static final int PICK_CONTACT = 0;
    @BindView(R.id.layout_add_address)
    LinearLayout layoutAddAddress;
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;


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
        initData();
        setContentView(R.layout.activity_add_address);
        ButterKnife.bind(this);
        initView();
        initCityAndDistrict();
    }

    String id = "";

    private void initView() {

        if (dataBean != null) {
            id = dataBean.getId();
            mEditUserNameAddAddress.setText(dataBean.getUser_name());
            mEditUserPhoneAddAddress.setText(dataBean.getTel());
            mTxtUserDistrictAddAddress.setText(dataBean.getCity_name() + dataBean.getDistrict_name());
            eDitUserMinuteAddAddress.setText(dataBean.getAddress());

        }

    }

    AddressListBean.DataBean dataBean;

    String flag = "";

    private void initData() {
        flag = this.getIntent().getStringExtra("flag");
        dataBean = (AddressListBean.DataBean) this.getIntent().getSerializableExtra("data");
    }

    private void initCityAndDistrict() {

        ChooseLocationPresonter chooseLocationPresonter = new ChooseLocationPresonter();
        chooseLocationPresonter.setChooseLocationListener(this);
        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("timestamp", time);
        String sign = ToolUitls.getSign(map);
        chooseLocationPresonter.getCityList(time, sign, Contacts.KEY);

    }


    @OnClick(R.id.txt_user_district_add_address)
    public void chooseDistrict() {
//        BaseDialog.setDialog(this).show();

        setDialog();

    }

    /*必须设置在Activity中否则不显示*/
    public void setDialog() {
        if (!mTxtUserDistrictAddAddress.getText().toString().equals("")) {
            mTxtUserDistrictAddAddress.setText("");
        }
        MyDialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.setAddress("");
        if (data != null) {
            dialogFragment.setData(data);
        }
        dialogFragment.setOnAddressChoosedListener(this);
        dialogFragment.show(getSupportFragmentManager(), "");
    }


    @OnClick(R.id.layout_add_address)
    public void getUserPhoneFromLoacl() {


        /*声明意图方式一*/
        Intent intent1 = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent1, PICK_CONTACT);

        /* 声明意图方式二*/
        // Intent intent2 = new Intent(Intent.ACTION_PICK);
        //  intent2.setType(ContactsContract.Contacts.CONTENT_TYPE);//vnd.android.cursor.dir/contact
        //  startActivityForResult(intent2, PICK_CONTACT);


    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);

        switch (reqCode) {
            case (PICK_CONTACT):
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c = managedQuery(contactData, null, null, null, null);
                    if (c.moveToFirst()) {
                        String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        String hasPhone = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                        String contactId = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                        String phoneNumber = null;
                        if (hasPhone.equalsIgnoreCase("1")) {
                            hasPhone = "true";
                        } else {
                            hasPhone = "false";
                        }
                        if (Boolean.parseBoolean(hasPhone)) {
                            Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                    null,
                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = "
                                            + contactId,
                                    null,
                                    null);
                            while (phones.moveToNext()) {
                                phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            }
                            phones.close();
                        }

                        /*注意 电话号码和 姓名可能为 null*/

                        if (name == null) {
                            name = "";
                        }
                        mEditUserNameAddAddress.setText(name);

                        if (phoneNumber == null) {
                            phoneNumber = "";
                        }
                        mEditUserPhoneAddAddress.setText(numberFormat(phoneNumber));


                    }
                }
                break;
        }
    }

    /**
     * 为防止 号码中含有符号 例如 131-1111-1111  显示不正常
     *
     * @param number 从通讯录返回的数据
     * @return 满足 纯数字格式的 字符串
     */
    public String numberFormat(String number) {
        String str = "";
        for (int i = 0; i < number.length(); i++) {
            String index = String.valueOf(number.charAt(i));
            if (index.matches("\\d")) {
                str += index;
            }
        }
        return str;
    }


    String location = "";
    String city = "";
    String district = "";

    @Override
    public void onAddressChoosed(String[] str, String address, String[] id) {

        city = id[0];
        district = id[1];
        for (int i = 0; i < str.length; i++) {
            if (str[i].equals("请选择") || str[i].equals("")) {
                return;
            } else {
                location += str[i];
            }
        }

        mTxtUserDistrictAddAddress.setText(address);

    }


    @OnClick(R.id.btn_submit_add_address)
    public void submit() {
        String userName = mEditUserNameAddAddress.getText().toString();
        if ("".equals(userName)) {
            ToolUitls.toast(this, "姓名不能为空");
            return;
        }

        String userPhone = mEditUserPhoneAddAddress.getText().toString();
        if ("".equals(userPhone)) {
            ToolUitls.toast(this, "手机号码不能为空");
            return;
        }
        String address = eDitUserMinuteAddAddress.getText().toString();
        if ("".equals(address)) {
            ToolUitls.toast(this, "地址不能为空");
            return;
        }


        SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
        String id = sharedPreferences.getString("id", null);
        String timeStamp = System.currentTimeMillis() + "";


        TreeMap<String, String> map = new TreeMap<>();
        map.put("address", address);
        map.put("city", city);
        map.put("district", district);
        map.put("timestamp", timeStamp);
        map.put("user_id", id);
        map.put("user_name", userName);
        map.put("user_phone", userPhone);


        String sign = ToolUitls.getSign(map);


        AddAddressPresonter addAddressPresonter = new AddAddressPresonter();
        addAddressPresonter.setOnAddAddressListener(this);
        addAddressPresonter.addAddress(address, city, district, timeStamp, id, userName, userPhone, sign
                , Contacts.KEY);


    }

    @Override
    public void addAddressSucess(AddressBean body) {


        switch (body.getStatus()) {
            case "1":
                Intent intent = new Intent(Contacts.ADD_ADDRESS_OK);
                sendBroadcast(intent);
                finish();
                break;
        }

    }

    @Override
    public void getCityLists(CityListBean body) {
        switch (body.getStatus()) {
            case "1":
                List<CityListBean.DataBean> data = body.getData();
                break;
        }
    }

    List<CityListBean.DataBean> data;

    @Override
    public void getCityList(CityListBean body) {
        switch (body.getStatus()) {
            case "1":
                data = body.getData();
                break;
        }

    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        finish();
    }

}
