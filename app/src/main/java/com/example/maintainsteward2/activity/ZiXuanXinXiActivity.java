package com.example.maintainsteward2.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.example.maintainsteward2.R;
import com.example.maintainsteward2.adapter.PhotoListAdapter;
import com.example.maintainsteward2.adapter.ZiXuanXinXiListAdapter;
import com.example.maintainsteward2.application.MyApplication;
import com.example.maintainsteward2.base.BaseActivity;
import com.example.maintainsteward2.base.Contacts;
import com.example.maintainsteward2.bean.AddressListBean;
import com.example.maintainsteward2.bean.PayInfoBean;
import com.example.maintainsteward2.bean.PublicBean;
import com.example.maintainsteward2.bean.ZiXuanGouMaiCallBackBean;
import com.example.maintainsteward2.bean.ZiXuanListBean;
import com.example.maintainsteward2.bean.ZiXuanServiceBean;
import com.example.maintainsteward2.mvp_presonter.UpLoadPhotoPresonter;
import com.example.maintainsteward2.mvp_presonter.ZiXuanPayPresonter;
import com.example.maintainsteward2.mvp_presonter.ZiXuanTaoCanSubmitPresonter;
import com.example.maintainsteward2.mvp_view.OnOrderZiXuanTaoCanListener;
import com.example.maintainsteward2.mvp_view.OnUpLoadPhotoListener;
import com.example.maintainsteward2.service.OnZiXuanPayListener;
import com.example.maintainsteward2.utils.PhotoUtils;
import com.example.maintainsteward2.utils.ToolUitls;
import com.example.maintainsteward2.view.MyLayoutManager;
import com.example.maintainsteward2.view.MyLayoutManager2;
import com.example.maintainsteward2.view.MyListView;
import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;

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
 * Created by Administrator on 2017/10/8.
 */

public class ZiXuanXinXiActivity extends BaseActivity implements PhotoListAdapter.OnPhotoClickListener, OnUpLoadPhotoListener, OnOrderZiXuanTaoCanListener, OnZiXuanPayListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.lv_service)
    MyListView lvService;
    @BindView(R.id.txt_jutidizhi)
    TextView txtJutidizhi;
    @BindView(R.id.layout_xuanzedizhi)
    LinearLayout layoutXuanzedizhi;
    @BindView(R.id.txt_time)
    TextView txtTime;
    @BindView(R.id.layout_time)
    LinearLayout layoutTime;
    @BindView(R.id.cb_weixin)
    CheckBox cbWeixin;
    @BindView(R.id.layout_weixinzhifu)
    LinearLayout layoutWeixinzhifu;
    @BindView(R.id.cb_yue)
    CheckBox cbYue;
    @BindView(R.id.layout_yuezhifu)
    LinearLayout layoutYuezhifu;
    @BindView(R.id.txt_taocanjia)
    TextView txtTaocanjia;
    @BindView(R.id.txt_yuanjia)
    TextView txtYuanjia;
    @BindView(R.id.txt_goumai)
    TextView txtGoumai;
    @BindView(R.id.img_dizhi)
    ImageView imgDizhi;
    @BindView(R.id.img_time)
    ImageView imgTime;
    @BindView(R.id.edit_description)
    EditText editDescription;
    @BindView(R.id.recycle)
    RecyclerView recycle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getActivitiesList().add(this);
        initInfo();
        setContentView(R.layout.activity_zixuanxinxi);
        ButterKnife.bind(this);
        initViews();
        initTimePicker();
        registerReciver();
    }

    ZiXuanXinXiListAdapter ziXuanXinXiListAdapter;
    PhotoListAdapter photoListAdapter;
    List<Bitmap> bitmaps;
    String[] imgArrays;
    UpLoadPhotoPresonter upLoadPhotoPresonter;
    ZiXuanTaoCanSubmitPresonter ziXuanTaoCanSubmitPresonter;
    ZiXuanPayPresonter ziXuanPayPresonter;

    private void initViews() {
        bitmaps = new ArrayList<>();
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.mipmap.tianjiatupian));
        txtYuanjia.setText("原价:￥" + yuanJia + "元");
        txtTaocanjia.setText(taoCanJia + "");
        ziXuanXinXiListAdapter = new ZiXuanXinXiListAdapter(this);
        lvService.setAdapter(ziXuanXinXiListAdapter);
        ziXuanXinXiListAdapter.setList(list);
        ziXuanXinXiListAdapter.notifyDataSetChanged();
        ziXuanTaoCanSubmitPresonter = new ZiXuanTaoCanSubmitPresonter();
        ziXuanTaoCanSubmitPresonter.setOnOrderZiXuanTaoCanListener(this);
        imgArrays = new String[6];
        for (int i = 0; i < imgArrays.length; i++) {
            imgArrays[i] = "";
        }
        recycle.setLayoutManager(new MyLayoutManager2(this, 4, LinearLayoutManager.VERTICAL, false));
        recycle.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(5, 5, 0, 0);
            }
        });
        photoListAdapter = new PhotoListAdapter(this);
        recycle.setAdapter(photoListAdapter);
        photoListAdapter.setOnPhotoClickListener(this);
        photoListAdapter.setList(bitmaps);
        photoListAdapter.notifyDataSetChanged();
        upLoadPhotoPresonter = new UpLoadPhotoPresonter();
        upLoadPhotoPresonter.setListener(this);
        ziXuanPayPresonter = new ZiXuanPayPresonter();
        ziXuanPayPresonter.setZiXuanPayListener(this);

    }

    List<ZiXuanListBean.DataBean.SetMealDataBean> list;
    double yuanJia;
    double taoCanJia;
    double discount;

    private void initInfo() {
        discount = getIntent().getDoubleExtra("zhe", -1);
        list = (List<ZiXuanListBean.DataBean.SetMealDataBean>) getIntent().getSerializableExtra("list");
        yuanJia = getIntent().getDoubleExtra("yuanJia", -1);
        taoCanJia = getIntent().getDoubleExtra("taoCanJia", -1);
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

                txtTime.setText(getTime(date));
                imgTime.setVisibility(View.INVISIBLE);
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

    @OnClick(R.id.layout_back)
    public void onLayoutBackClicked() {
        finish();
    }

    @OnClick(R.id.layout_time)
    public void onLayoutTimeClicked() {
        mTimePickerView.show();
    }

    @OnClick(R.id.layout_weixinzhifu)
    public void onLayoutWeixinzhifuClicked() {
        if (cbYue.isChecked()) {
            cbYue.setChecked(false);
            cbYue.setVisibility(View.INVISIBLE);
        }
        cbWeixin.setChecked(true);
        cbWeixin.setVisibility(View.VISIBLE);
    }

    public static final int ADDRESS_REQUEST_CODE = 1;

    @OnClick(R.id.layout_xuanzedizhi)
    public void onLayoutDiZhiClicked() {
        Intent intent = new Intent(this, AddressManagerActivity.class);
        intent.putExtra("flag", "ZiXuanXinXiActivity");
        startActivityForResult(intent, ADDRESS_REQUEST_CODE);
    }

    String payType = "1";

    @OnClick(R.id.layout_yuezhifu)
    public void onLayoutYuezhifuClicked() {
        if (cbWeixin.isChecked()) {
            cbWeixin.setChecked(false);
            cbWeixin.setVisibility(View.INVISIBLE);
        }
        cbYue.setChecked(true);
        cbYue.setVisibility(View.VISIBLE);
        payType = "2";
    }

    int status = 1;

    @OnClick(R.id.txt_goumai)
    public void onTxtGoumaiClicked() {


        switch (status) {
            case 1:
                if (address_id == null) {
                    ToolUitls.toast(this, "请选择地址");
                    return;
                }
                order_time = txtTime.getText().toString();
                if (order_time.equals("")) {
                    ToolUitls.toast(this, "请选择预约时间");
                    return;
                }
                setSureDialog();
                break;
            case 2:
                /*支付*/

                Contacts.PAY_FLAG = "ziXuan";
                PayReq payReq = new PayReq();
                payReq.appId = appid;
                payReq.partnerId = partnerid;
                payReq.prepayId = prepayid;
                payReq.packageValue = packageX;
                payReq.nonceStr = noncestr;
                payReq.timeStamp = timestamp + "";
                payReq.sign = sign;
                MyApplication.api.sendReq(payReq);

                break;
        }

    }

    public static final int CHECK_PHOTO_CODE = 2;
    public static final int CROP_PHOTO_CODE = 3;
    String address_id;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ADDRESS_REQUEST_CODE:

                    AddressListBean.DataBean dataBean = (AddressListBean.DataBean) data.getSerializableExtra("address");
                    String address = dataBean.getAddress();
                    String city_name = dataBean.getCity_name();
                    String district_name = dataBean.getDistrict_name();
                    txtJutidizhi.setText(city_name + district_name + address);
                    imgDizhi.setVisibility(View.INVISIBLE);
                    address_id = dataBean.getId();

                    break;
                case CHECK_PHOTO_CODE:
                    if (bitmaps.size() <= 6) {
                        Uri uri = data.getData();
                        PhotoUtils.crop(uri, this, CROP_PHOTO_CODE);
                    }

                    break;
                case CROP_PHOTO_CODE:
                    Bitmap bitmap = data.getParcelableExtra("data");
                    bitmaps.add(0, bitmap);
                    photoListAdapter.setList(bitmaps);
                    photoListAdapter.notifyDataSetChanged();
                    break;
            }

        }

    }

    @Override
    public void onPhotoClick() {
        if (bitmaps.size() == 7) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, CHECK_PHOTO_CODE);

    }

    @Override
    public void onPhotoDelete(int position) {
        bitmaps.remove(position);
        photoListAdapter.setList(bitmaps);
        photoListAdapter.notifyDataSetChanged();
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


    @Override
    public void onUpSucess(String key) {
        url.add(key);

        if (photos != null && count == photos.size()) {
            for (int i = 0; i < url.size(); i++) {
                imgArrays[i] = url.get(i);
            }
            orderNow();
        }
    }

    String order_time;

    public void orderNow() {
        SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
        String user_id = sharedPreferences.getString("id", null);

        List<ZiXuanServiceBean> serviceBeanList = new ArrayList<>();
        Gson gson = new Gson();
        for (int i = 0; i < list.size(); i++) {
            ZiXuanListBean.DataBean.SetMealDataBean setMealDataBean = list.get(i);
            serviceBeanList.add(new ZiXuanServiceBean(
                    setMealDataBean.getName(),
                    setMealDataBean.getCount() + "",
                    setMealDataBean.getExpenses()
                    , setMealDataBean.getUnit(),
                    setMealDataBean.getService_id()));

        }
        String service_item = gson.toJson(serviceBeanList);


        int counts = 0;
        for (ZiXuanListBean.DataBean.SetMealDataBean setMealDataBean : list) {
            counts += setMealDataBean.getCount();
        }
        String description = editDescription.getText().toString();
        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("timestamp", time);
        map.put("user_id", user_id);
        map.put("address_id", address_id);
        map.put("order_time", order_time);
        map.put("service_item", service_item);
        map.put("counts", counts + "");
        map.put("discount", discount + "");
        map.put("total_price", yuanJia + "");
        map.put("final_price", taoCanJia + "");
        map.put("details", description);
        String sign = ToolUitls.getSign(map);

//        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "SelfServiceBuy?" + "user_id=" + user_id
//                + "&address_id=" + address_id + "&service_item=" + service_item + "&counts=" + counts + "&order_time=" + order_time + "&discount=" + discount
//                + "&total_price=" + yuanJia + "&final_price=" + taoCanJia + "&details=" + description + "&img1=" + imgArrays[0]
//                + "&img2=" + imgArrays[1] + "&img3=" + imgArrays[2] + "&img4=" + imgArrays[3] + "&img5=" + imgArrays[4] + "&img6=" + imgArrays[5]
//                + "&timestamp=" + time + "&sign=" + sign + "&key=" + Contacts.KEY
//        );
//        ToolUitls.print("-----------"," imgArrays[0]=="+ imgArrays[0]+" imgArrays[1]="+ imgArrays[1]);
        ziXuanTaoCanSubmitPresonter.orderNow(user_id, address_id, order_time, service_item, counts + "", discount + "", yuanJia + "",
                taoCanJia + "", description, imgArrays[0], imgArrays[1], imgArrays[2], imgArrays[3], imgArrays[4], imgArrays[5], time, sign, Contacts.KEY);


    }

    String order_sn;

    @Override
    public void orderSucess(ZiXuanGouMaiCallBackBean bean) {
        switch (bean.getStatus()) {
            case "1":
                mWaitingAlertDialog.dismiss();
                ToolUitls.toast(this, "下单成功,请立即支付！");
                /*提交订单成功获取的订单编号*/
                order_sn = bean.getData().getOrder_sn();
                ziXuanPayPresonter.getZiXuanPayInfo(order_sn);
                status = 2;
                txtGoumai.setText("立即支付");
                break;
            default:
                mWaitingAlertDialog.dismiss();
                ToolUitls.toast(this, "下单失败，请重试！");
                break;
        }
    }


    /*确认对话框*/
    public void setSureDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();

        alertDialog.show();

        View submitView = View.inflate(this, R.layout.dialog_submit, null);
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

        View submitView = View.inflate(this, R.layout.dialog_waitting, null);
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

    String appid;
    String noncestr;
    String packageX;
    String partnerid;
    String prepayid;
    String sign;
    int timestamp;

    @Override
    public void getZiXuanPayInfo(PayInfoBean payInfoBean) {

        switch (payInfoBean.getStatus()) {
            case 1:
                appid = payInfoBean.getAppid();
                noncestr = payInfoBean.getNoncestr();
                packageX = payInfoBean.getPackageX();
                partnerid = payInfoBean.getPartnerid();
                prepayid = payInfoBean.getPrepayid();
                sign = payInfoBean.getSign();
                timestamp = payInfoBean.getTimestamp();
                break;
        }
    }

    @Override
    public void payForNow(PublicBean publicBean) {

        switch (publicBean.getStatus()) {
            case "1":
                ToolUitls.toast(this, "购买成功！");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(ziXuanReciver);
    }

    ZiXuanReciver ziXuanReciver;

    public void registerReciver() {
        ziXuanReciver = new ZiXuanReciver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Contacts.PAY_BY_WEI_XIN);
        registerReceiver(ziXuanReciver, intentFilter);

    }

    class ZiXuanReciver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (Contacts.PAY_FLAG.equals("ziXuan")) {
                ToolUitls.toast(ZiXuanXinXiActivity.this, "购买成功！");
                ziXuanPayPresonter.payForNow(order_sn, "1", taoCanJia + "");
                startActivity(new Intent(ZiXuanXinXiActivity.this, OrderActivity.class));
            }

        }
    }
}
