package com.example.maintainsteward.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.FragmentBaseActivity;
import com.example.maintainsteward.dialog.BaseDialog;
import com.example.maintainsteward.fragment.CityFragment;
import com.example.maintainsteward.fragment.DistrictFragment;
import com.example.maintainsteward.fragment.MyDialogFragment;
import com.example.maintainsteward.fragment.ProvinceFragment;
import com.example.maintainsteward.R;
import com.example.maintainsteward.utils.LocationUtils;
import com.example.maintainsteward.utils.PermissionRegisterUtils;
import com.example.maintainsteward.utils.ToolUitls;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Administrator on 2017/8/5.
 */

public class AddAddressActivity extends FragmentActivity {

    public static final String TAG = "AddAddressActivity";

    @BindView(R.id.txt_base_back)
    ImageView mtxtBaseBack;
    @BindView(R.id.txt_base_title)
    TextView mtxtBaseTitle;

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


    View mRootView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootView = View.inflate(this, R.layout.activity_add_address, null);
        setContentView(mRootView);


        ButterKnife.bind(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @OnClick(R.id.txt_user_district_add_address)
    public void chooseDistrict() {
//        BaseDialog.setDialog(this).show();

        setDialog();

    }

    /*必须设置在Activity中否则不显示*/
    public void setDialog() {
        MyDialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.show(getSupportFragmentManager(),"");
    }


    @OnClick(R.id.btn_submit_add_address)
    public void submit() {

    }

    @OnClick(R.id.img_add_address)
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
                        ToolUitls.print(TAG, "name==" + name + "phoneNumber==" + numberFormat(phoneNumber));

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


}
