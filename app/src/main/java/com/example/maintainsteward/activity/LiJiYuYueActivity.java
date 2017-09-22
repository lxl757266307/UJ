package com.example.maintainsteward.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.example.maintainsteward.R;
import com.example.maintainsteward.adapter.OrderPeiJianListAdapter;
import com.example.maintainsteward.adapter.OrderServiceListAdapter;
import com.example.maintainsteward.adapter.PhotoListAdapter;
import com.example.maintainsteward.adapter.PhotoListAdapter.OnPhotoClickListener;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.AddressListBean;
import com.example.maintainsteward.bean.OrderSucessBean;
import com.example.maintainsteward.bean.SearviceInfoBean;
import com.example.maintainsteward.bean.ServiceGoodsGetBean;
import com.example.maintainsteward.main.MainActivity;
import com.example.maintainsteward.mvp_presonter.LiJiOrderPresonter;
import com.example.maintainsteward.mvp_presonter.UpLoadPhotoPresonter;
import com.example.maintainsteward.mvp_view.OnUpLoadPhotoListener;
import com.example.maintainsteward.mvp_view.OrderListener;
import com.example.maintainsteward.utils.PhotoUtils;
import com.example.maintainsteward.utils.ToolUitls;
import com.example.maintainsteward.view.MyLayoutManager2;
import com.example.maintainsteward.view.MyListView;
import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/15.
 */

public class LiJiYuYueActivity extends BaseActivity implements OnPhotoClickListener, OnUpLoadPhotoListener, OrderListener {
    private static final int PHOTO_REQEST_CODE = 2;
    private static final int REQEST_CODE_CROP = 3;
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_service_title_lijiyuyue)
    TextView txtServiceTitleLijiyuyue;
    @BindView(R.id.layout_choose_service_lijiyuyue)
    LinearLayout layoutChooseServiceLijiyuyue;
    @BindView(R.id.lv_service_list_lijiyuyue)
    MyListView lvServiceListLijiyuyue;
    @BindView(R.id.lv_peijian_list_lijiyuyue)
    MyListView lvPeijianListLijiyuyue;
    @BindView(R.id.layout_choose_address_lijiyuyue)
    LinearLayout layoutChooseAddressLijiyuyue;
    @BindView(R.id.txt_address_lijiyuyue)
    TextView txtAddressLijiyuyue;
    @BindView(R.id.txt_username_lijiyuyue)
    TextView txtUsernameLijiyuyue;
    @BindView(R.id.txt_phone_lijiyuyue)
    TextView txtPhoneLijiyuyue;
    @BindView(R.id.layout_address_lijiyuyue)
    LinearLayout layoutAddressLijiyuyue;
    @BindView(R.id.layout_choose_repair_time_lijiyuyue)
    LinearLayout layoutChooseRepairTimeLijiyuyue;
    @BindView(R.id.edit_repair_descrription)
    EditText editRepairDescrription;


    public static final int REQUEST_CODE = 1;
    @BindView(R.id.txt_total_service_lijiyuyue)
    TextView txtTotalServiceLijiyuyue;
    @BindView(R.id.txt_total_peijian_lijiyuyue)
    TextView txtTotalPeijianLijiyuyue;
    @BindView(R.id.txt_repair_time_lijiyuyue)
    TextView txtRepairTimeLijiyuyue;
    @BindView(R.id.rv_photo_lijiyuyue)
    RecyclerView rvPhotoLijiyuyue;
    @BindView(R.id.txt_submit_lijiyuyue)
    TextView txtSubmitLijiyuyue;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    int page = 1;
    public static final String TAG = "LiJiYuYueActivity";

    @OnClick({R.id.layout_back,
            R.id.layout_choose_service_lijiyuyue,
            R.id.layout_choose_address_lijiyuyue,
            R.id.layout_choose_repair_time_lijiyuyue,
            R.id.txt_submit_lijiyuyue})
    public void click(View view) {

        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.layout_choose_service_lijiyuyue:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("page", 1);
                startActivity(intent);
                break;
            case R.id.layout_choose_address_lijiyuyue:
//
//                SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
//                String id = sharedPreferences.getString("id", null);
////
//                TreeMap<String, String> map = new TreeMap<>();
//                String timeStamp = System.currentTimeMillis() + "";
//                map.put("timestamp", timeStamp);
//                map.put("user_id", id);
//                map.put("page", page + "");
//                String sign = ToolUitls.getSign(map);
//
//                ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "GetAddress?" +
//                        "user_id=" + id + "&timestamp="
//                        + timeStamp + "&page="
//                        + page + "&sign=" + sign
//                        + "&key=" + Contacts.KEY);

                Intent intent1 = new Intent(this, AddressManagerActivity.class);
                intent1.putExtra("flag", TAG);
                startActivityForResult(intent1, REQUEST_CODE);

                break;
            case R.id.layout_choose_repair_time_lijiyuyue:
                ToolUitls.print(TAG, "选择时间");
                initTimePicker();
                mTimePickerView.show();
                break;
            case R.id.txt_submit_lijiyuyue:

                if (dataBean == null) {
                    ToolUitls.toast(this, "地址不能为空");
                    return;
                }
                if (!txtRepairTimeLijiyuyue.getText().toString().matches("\\d{4}.\\d{0,2}.\\d{0,2}.\\d{0,2}.\\d{0,2}")) {
                    ToolUitls.toast(this, "时间格式错误");
                    return;
                }


                setSureDialog();
                break;

        }
    }

    AddressListBean.DataBean dataBean;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            switch (requestCode) {
                case REQUEST_CODE:
                    dataBean = (AddressListBean.DataBean) data.getSerializableExtra("address");
                    if (dataBean != null) {
                        layoutChooseAddressLijiyuyue.setVisibility(View.GONE);
                        layoutAddressLijiyuyue.setVisibility(View.VISIBLE);
                        txtAddressLijiyuyue.setText(dataBean.getAddress());
                        txtUsernameLijiyuyue.setText(dataBean.getUser_name());
                        txtPhoneLijiyuyue.setText(dataBean.getTel());
                    }

                    break;

                case PHOTO_REQEST_CODE:
                    if (bitmaps.size() <= 6) {
                        Uri uri = data.getData();
                        PhotoUtils.crop(uri, this, REQEST_CODE_CROP);
                    }

                    break;
                case REQEST_CODE_CROP:
                    Bitmap bitmap = data.getParcelableExtra("data");
                    bitmaps.add(0, bitmap);
                    photoListAdapter.setList(bitmaps);
                    photoListAdapter.notifyDataSetChanged();

            }
        }


    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        setContentView(R.layout.activity_lijiyuyue);
        ButterKnife.bind(this);
        txtServiceTitleLijiyuyue.setText(title);

        initList();
        initPhotoList();

    }

    PhotoListAdapter photoListAdapter;

    List<Bitmap> bitmaps;

    private void initPhotoList() {
        bitmaps = new ArrayList<>();
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.mipmap.tianjiatupian));
        rvPhotoLijiyuyue.setLayoutManager(new MyLayoutManager2(this, 4, LinearLayoutManager.VERTICAL, false));
        rvPhotoLijiyuyue.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(5, 5, 0, 0);
            }
        });

        photoListAdapter = new PhotoListAdapter(this);
        photoListAdapter.setOnPhotoClickListener(this);
        photoListAdapter.setList(bitmaps);
        rvPhotoLijiyuyue.setAdapter(photoListAdapter);
        photoListAdapter.notifyDataSetChanged();

    }


    /*服务列表*/
    List<SearviceInfoBean.DataBean> data;
    /*配件列表*/
    List<ServiceGoodsGetBean.DataBean> peiJian;

    private void initList() {


        if (data != null && data.size() > 0) {
            layoutChooseServiceLijiyuyue.setVisibility(View.GONE);
            OrderServiceListAdapter orderServiceListAdapter = new OrderServiceListAdapter(data, this);
            lvServiceListLijiyuyue.setAdapter(orderServiceListAdapter);
            orderServiceListAdapter.notifyDataSetChanged();
        }
        if (peiJian != null && peiJian.size() > 0) {
            OrderPeiJianListAdapter orderPeiJianListAdapter = new OrderPeiJianListAdapter(peiJian, this);
            lvPeijianListLijiyuyue.setAdapter(orderPeiJianListAdapter);
            orderPeiJianListAdapter.notifyDataSetChanged();
        }

        setTotalPrice();
    }


    String title = "";
    String cat_id = "";

    private void initData() {
        data = (List<SearviceInfoBean.DataBean>) this.getIntent().getSerializableExtra("service");
        peiJian = (List<ServiceGoodsGetBean.DataBean>) this.getIntent().getSerializableExtra("peijian");
        title = this.getIntent().getStringExtra("title");
        cat_id = this.getIntent().getStringExtra("cat_id");

    }


    public void setTotalPrice() {

        double price = 0;
        if (data != null && data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {

                SearviceInfoBean.DataBean dataBean = data.get(i);
                String expenses = dataBean.getExpenses();
                if (!"".equals(expenses) || !"面议".equals(expenses)) {
                    price += Double.parseDouble(expenses);
                }

            }
        }

        txtTotalServiceLijiyuyue.setText(price + "");

        double materialPrice = 0;

        if (peiJian != null && peiJian.size() > 0) {

            for (int i = 0; i < peiJian.size(); i++) {
                String material = peiJian.get(i).getPrice();
                if (!"".equals(material) || !"面议".equals(material)) {
                    materialPrice += Double.parseDouble(material);
                }

            }
        }
        txtTotalPeijianLijiyuyue.setText("包含材料费" + materialPrice + "元");


    }


    TimePickerView mTimePickerView;

    /* 初始化时间选择器*/
    private void initTimePicker() {
        //控制时间范围(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
        //因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11


        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH), selectedDate.get(Calendar.DAY_OF_MONTH), 24 - selectedDate.get(Calendar.HOUR), 60 - selectedDate.get(Calendar.MINUTE));
        Calendar endDate = Calendar.getInstance();
        endDate.set(2099, 12, 31, 24, 59);
        //时间选择器
        mTimePickerView = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                // 这里回调过来的v,就是show()方法里面所添加的 View 参数，如果show的时候没有添加参数，v则为null
                /*btn_Time.setText(getTime(date));*/

                txtRepairTimeLijiyuyue.setText(getTime(date));
                /* 按时间查询*/

            }
        })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{false, true, true, true, true, false})
                .setLabel("", "月", "日", "时", "分", "")
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    @Override
    public void onPhotoClick() {
        if (bitmaps.size() == 7) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        LiJiYuYueActivity.this.startActivityForResult(intent, PHOTO_REQEST_CODE);


    }

    int count = 0;
    List<Bitmap> photos;

    @Override
    public void getToken(String token) {
        url = new ArrayList<>();

        if (bitmaps != null && bitmaps.size() > 0) {
            photos = bitmaps.subList(0, bitmaps.size() - 1);

            for (int i = 0; i < photos.size(); i++) {
                count++;

                upLoadPhotoPresonter.qiNiuYunUpload(photos.get(i), token);
            }
        }

    }

    ArrayList<String> url;


    String[] imgArrays;


    @Override
    public void onUpSucess(String key) {
        url.add(key);

        ToolUitls.print(TAG, "count==" + count);
        ToolUitls.print(TAG, "photos==" + photos.size());
        if (photos != null && count == photos.size() ) {
            for (int i = 0; i < url.size(); i++) {
                imgArrays[i] = url.get(i);
            }
            orderNow();

//            ToolUitls.toast(LiJiYuYueActivity.this, "提交成功");
//            mWaitingAlertDialog.dismiss();
        }
    }

    private void orderNow() {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
        String user_id = sharedPreferences.getString("id", null);
        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("timestamp", time);
        map.put("user_id", user_id);
        map.put("cat_id", cat_id);
        map.put("name", title);
        map.put("type", "1");
        map.put("address_id", dataBean.getId());
        String dataJson = gson.toJson(data);
        String peiJianJson = gson.toJson(peiJian);
        String content = editRepairDescrription.getText().toString();
        String img1 = imgArrays[0];
        String img2 = imgArrays[1];
        String img3 = imgArrays[2];
        String img4 = imgArrays[3];
        String img5 = imgArrays[4];
        String img6 = imgArrays[5];
        String orderTime = txtRepairTimeLijiyuyue.getText().toString();

        map.put("service_con", dataJson);
        map.put("material", peiJianJson);
        map.put("content", content);
        map.put("order_time", orderTime);

        String sign = ToolUitls.getSign(map);


        liJiOrderPresonter.order(user_id, cat_id, title, dataBean.getId(), dataJson, peiJianJson, content, img1, img2, img3, img4, img5, img6, orderTime, time, sign, Contacts.KEY);
//        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "ServiceOrderSubmit?" + "user_id=" + user_id + "&cat_id=" + cat_id
//                + "&name=" + title + "&address_id=" + dataBean.getId() + "&service_con=" + dataJson + "&material=" + peiJianJson
//                + "&content=" + content + "&img1=" + img1 + "&img2=" + img2 + "&img3=" + img3 + "&img4=" + img4 + "&img5=" + img5
//                + "&img6=" + img6 + "&order_time=" + orderTime + "&timestamp=" + time + "&sign=" + sign + "&key=" + Contacts.KEY + "&type=1");
    }

    UpLoadPhotoPresonter upLoadPhotoPresonter;
    LiJiOrderPresonter liJiOrderPresonter;

    /*确认对话框*/
    public void setSureDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();

        alertDialog.show();

        View submitView = View.inflate(LiJiYuYueActivity.this, R.layout.dialog_submit, null);
        TextView cancle = (TextView) submitView.findViewById(R.id.txt_dialog_submit_cancle);
        TextView sure = (TextView) submitView.findViewById(R.id.txt_dialog_submit_sure);

        Window window = alertDialog.getWindow();
        window.setBackgroundDrawable(getResources().getDrawable(R.drawable.write_form_dialog));
        window.setContentView(submitView);
        WindowManager windowManager = this.getWindowManager();

        Display defaultDisplay = windowManager.getDefaultDisplay();

        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = (int) (defaultDisplay.getWidth() * 0.8);
        window.setAttributes(attributes);
        alertDialog.setCanceledOnTouchOutside(false);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waittingProgressBar();
                alertDialog.dismiss();

                upLoadPhotoPresonter = new UpLoadPhotoPresonter();
                upLoadPhotoPresonter.setListener(LiJiYuYueActivity.this);

                liJiOrderPresonter = new LiJiOrderPresonter();
                liJiOrderPresonter.setOrderListener(LiJiYuYueActivity.this);
                imgArrays = new String[6];
                for (int i = 0; i < imgArrays.length; i++) {
                    imgArrays[i] = "";
                }
                if (bitmaps != null && bitmaps.size() > 1) {
                    upLoadPhotoPresonter.getToken();
                } else {
                    orderNow();
                }


            }
        });
    }

    AlertDialog mWaitingAlertDialog;

    /*等待对话框*/
    public void waittingProgressBar() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        mWaitingAlertDialog = builder.create();

        mWaitingAlertDialog.show();

        View submitView = View.inflate(LiJiYuYueActivity.this, R.layout.dialog_waitting, null);
        CircularProgressView mCircularProgressView = (CircularProgressView) submitView.findViewById(R.id.progress_view);
        mCircularProgressView.setVisibility(View.VISIBLE);
        mCircularProgressView.setIndeterminate(true);
        mCircularProgressView.startAnimation();

        Window window = mWaitingAlertDialog.getWindow();
        window.setBackgroundDrawable(getResources().getDrawable(R.drawable.write_form_dialog));
        window.setContentView(submitView);
        WindowManager windowManager = this.getWindowManager();

        Display defaultDisplay = windowManager.getDefaultDisplay();

        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = (int) (defaultDisplay.getWidth() * 0.6);
        attributes.height = (int) (defaultDisplay.getHeight() * 0.3);
        window.setAttributes(attributes);
        mWaitingAlertDialog.setCanceledOnTouchOutside(false);

    }

    @Override
    public void orderSucess(OrderSucessBean bean) {
        String status = bean.getStatus();
        switch (status) {
            case "1":
                mWaitingAlertDialog.dismiss();
                ToolUitls.toast(this, "下单成功");
                handler.sendEmptyMessageDelayed(1, 2000);
                break;
        }
    }

    @Override
    public void orderFaild(String message) {
        ToolUitls.toast(this, message);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Intent intent = new Intent(LiJiYuYueActivity.this, OrderSucessActivity.class);
            startActivity(intent);


        }
    };
}
