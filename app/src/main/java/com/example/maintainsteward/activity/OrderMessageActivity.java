package com.example.maintainsteward.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.adapter.OrderInfoPeiJianAdapter;
import com.example.maintainsteward.adapter.OrderInfoServiceAdapter;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.CanUseYouHuiQuanBean;
import com.example.maintainsteward.bean.KaJuanBean;
import com.example.maintainsteward.bean.OrderInfoBean;
import com.example.maintainsteward.bean.PayInfoBean;
import com.example.maintainsteward.mvp_presonter.LiJiOrderPresonter;
import com.example.maintainsteward.mvp_presonter.OrderInfoPresonter;
import com.example.maintainsteward.mvp_presonter.PayPresonter;
import com.example.maintainsteward.mvp_presonter.UpLoadPhotoPresonter;
import com.example.maintainsteward.mvp_view.OnPayListener;
import com.example.maintainsteward.mvp_view.OrderInfoListener;
import com.example.maintainsteward.utils.ToolUitls;
import com.example.maintainsteward.view.MyListView;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/9/20.
 */

public class OrderMessageActivity extends BaseActivity implements OrderInfoListener, TextWatcher, OnPayListener {


    String orderId;
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.img_yitijiao)
    CircleImageView imgYitijiao;
    @BindView(R.id.txt_time_yitijiao)
    TextView txtTimeYitijiao;
    @BindView(R.id.txt_yitijiao)
    TextView txtYitijiao;
    @BindView(R.id.img1)
    ImageView img1;
    @BindView(R.id.img_yuyuechenggong)
    CircleImageView imgYuyuechenggong;
    @BindView(R.id.txt_time_yuyuechenggong)
    TextView txtTimeYuyuechenggong;
    @BindView(R.id.txt_yuyuechenggong)
    TextView txtYuyuechenggong;
    @BindView(R.id.img2)
    ImageView img2;
    @BindView(R.id.img_zhengzaiweixiu)
    CircleImageView imgZhengzaiweixiu;
    @BindView(R.id.txt_time_zhengzaiweixiu)
    TextView txtTimeZhengzaiweixiu;
    @BindView(R.id.txt_zhengzaiweixiu)
    TextView txtZhengzaiweixiu;
    @BindView(R.id.img3)
    ImageView img3;
    @BindView(R.id.img_yiwancheng)
    CircleImageView imgYiwancheng;
    @BindView(R.id.txt_time_yiwancheng)
    TextView txtTimeYiwancheng;
    @BindView(R.id.txt_yiwancheng)
    TextView txtYiwancheng;
    @BindView(R.id.txt_service_name)
    TextView txtServiceName;
    @BindView(R.id.txt_status)
    TextView txtStatus;
    @BindView(R.id.txt_dingdanbianhao)
    TextView txtDingdanbianhao;
    @BindView(R.id.txt_worker_name)
    TextView txtWorkerName;
    @BindView(R.id.txt_worker_phone)
    TextView txtWorkerPhone;
    @BindView(R.id.txt_my_info)
    TextView txtMyInfo;
    @BindView(R.id.txt_user_name)
    TextView txtUserName;
    @BindView(R.id.txt_user_phone)
    TextView txtUserPhone;
    @BindView(R.id.txt_user_address)
    TextView txtUserAddress;
    @BindView(R.id.txt_yuyueshijian)
    TextView txtYuyueshijian;
    @BindView(R.id.lv_servicce)
    MyListView lvServicce;
    @BindView(R.id.txt_taocanjianmian)
    TextView txtTaocanjianmian;
    @BindView(R.id.lv_peijian)
    MyListView lvPeijian;
    @BindView(R.id.txt_qitafeiyong)
    TextView txtQitafeiyong;
    @BindView(R.id.txt_weixianzuoye)
    TextView txtWeixianzuoye;
    @BindView(R.id.txt_jianmian)
    TextView txtJianmian;
    @BindView(R.id.txt_zongjia)
    TextView txtZongjia;
    @BindView(R.id.txt_youhuijia)
    TextView txtYouhuijia;
    @BindView(R.id.txt_lijiyuyue)
    TextView txtLijiyuyue;
    @BindView(R.id.img_kefu)
    CircleImageView imgKefu;
    SharedPreferences sharedPreferences;
    String id;

    OrderInfoPresonter orderInfoPresonter;
    OrderInfoServiceAdapter orderInfoServiceAdapter;
    OrderInfoPeiJianAdapter orderInfoPeiJianAdapter;
    @BindView(R.id.layout_365)
    LinearLayout layout365;
    @BindView(R.id.layout_youhuiquanxuanze)
    LinearLayout layoutYouhuiquanxuanze;
    @BindView(R.id.txt_jiantou)
    TextView txtJiantou;

    PayPresonter payPresonter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderId = this.getIntent().getStringExtra("id");
        setContentView(R.layout.activity_order_message);
        ButterKnife.bind(this);

        txtZongjia.addTextChangedListener(this);
        txtJianmian.addTextChangedListener(this);

        sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
        id = sharedPreferences.getString("id", null);
        orderInfoPresonter = new OrderInfoPresonter();
        orderInfoPresonter.setOrderInfoListener(this);
        orderInfoServiceAdapter = new OrderInfoServiceAdapter(this);
        orderInfoPeiJianAdapter = new OrderInfoPeiJianAdapter(this);
        payPresonter = new PayPresonter();
        payPresonter.setOnPayListener(this);
        getOrderInfo();
        register();
    }

    @OnClick({R.id.layout_back, R.id.txt_worker_phone, R.id.txt_lijiyuyue, R.id.img_kefu, R.id.layout_youhuiquanxuanze})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.txt_worker_phone:

                String phone = txtWorkerPhone.getText().toString();

                if (!phone.matches("\\d{11}"))
                    return;

                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + phone));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                this.startActivity(intent);
                break;
            case R.id.txt_lijiyuyue:
                switch (data.getOrder_status()) {
                    case "4":
                        setSureDialog();
                        break;
                    case "1":
                    case "2":
                        quXiaoDialog();
                        break;
                    default:
                        break;
                }


                break;
            case R.id.img_kefu:

                Intent intent2 = new Intent(Intent.ACTION_CALL);
                intent2.setData(Uri.parse("tel:4008293331"));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                this.startActivity(intent2);
                break;
            case R.id.layout_youhuiquanxuanze:

                Intent intent3 = new Intent(this, YouHuiQuanActivity.class);
                intent3.putExtra("order_no", data.getOrder_no());
                intent3.putExtra("count", data.getTotal_amount());
                startActivityForResult(intent3, YOU_HUI_QUAN_CODE);
                break;
        }
    }

    public static final int YOU_HUI_QUAN_CODE = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            switch (requestCode) {
                case YOU_HUI_QUAN_CODE:
                    CanUseYouHuiQuanBean.DataBean resultDataBean = (CanUseYouHuiQuanBean.DataBean) data.getSerializableExtra("kajuan");

                    if (resultDataBean != null) {
                        String bonus_amount = resultDataBean.getBonus_amount();
                        txtJianmian.setText("￥" + bonus_amount);
                        txtJiantou.setVisibility(View.GONE);
                    }


                    break;
            }
        }

    }

    public void getOrderInfo() {
        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("timestamp", time);
        map.put("user_id", id);
        map.put("id", orderId);
        String sign = ToolUitls.getSign(map);
        orderInfoPresonter.getOrderInfo(id, orderId, time, sign, Contacts.KEY);
//        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "OrderDetails?" + "user_id=" + id + "&id=" + orderId + "&timestamp=" + time + "&sign=" + sign + "&key=" + Contacts.KEY);

    }

    ProgressDialog dialog;

    @Override
    public void showDialog() {
//        dialog = ProgressDialog.show(this, "", "正在加载...");
    }

    @Override
    public void hideDialog() {
//        dialog.dismiss();

    }

    @Override
    public void quXiaoOrder() {
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra("page", 0);
        startActivity(intent);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    OrderInfoBean.DataBean data;

    @Override
    public void getOrderInfoSucess(OrderInfoBean orderInfoBean) {
        switch (orderInfoBean.getStatus()) {

            case "1":

                data = orderInfoBean.getData();
                switch (data.getOrder_status()) {

                    case "1":
                        img1.setImageResource(R.mipmap.xiantiao3);
                        imgYitijiao.setImageResource(R.mipmap.yitijiao2);
                        txtYitijiao.setTextColor(Color.parseColor("#da0a0a"));
                        txtTimeYitijiao.setTextColor(Color.parseColor("#da0a0a"));
                        txtTimeYitijiao.setText(data.getCreate_time().substring(5, 16));
                        txtStatus.setText("已提交");
                        txtLijiyuyue.setText("取消订单");
                        break;
                    case "2":
                        img1.setImageResource(R.mipmap.xiantiao2);
                        img2.setImageResource(R.mipmap.xiantiao3);
                        imgYuyuechenggong.setImageResource(R.mipmap.yuyuechengong2);
                        txtYuyuechenggong.setTextColor(Color.parseColor("#da0a0a"));
                        txtTimeYuyuechenggong.setTextColor(Color.parseColor("#da0a0a"));
                        txtTimeYuyuechenggong.setText(data.getOrder_time().substring(5, 16));
                        txtTimeYitijiao.setText(data.getCreate_time().substring(5, 16));
                        txtStatus.setText("预约成功");
                        txtLijiyuyue.setText("取消订单");
                        break;
                    case "3":
                        img2.setImageResource(R.mipmap.xiantiao2);
                        img3.setImageResource(R.mipmap.xiantiao3);
                        imgZhengzaiweixiu.setImageResource(R.mipmap.zhengzaiweixiu2);
                        txtZhengzaiweixiu.setTextColor(Color.parseColor("#da0a0a"));
                        txtTimeZhengzaiweixiu.setTextColor(Color.parseColor("#da0a0a"));
                        txtTimeZhengzaiweixiu.setText(data.getArrival_time().substring(5, 16));
                        txtTimeYuyuechenggong.setText(data.getOrder_time().substring(5, 16));
                        txtTimeYitijiao.setText(data.getCreate_time().substring(5, 16));
                        txtStatus.setText("已到达");
                        txtLijiyuyue.setText("已付款");
                        break;
                    case "4":
                        img1.setImageResource(R.mipmap.xiantiao2);
                        img2.setImageResource(R.mipmap.xiantiao3);
                        imgYuyuechenggong.setImageResource(R.mipmap.yuyuechengong2);
                        txtYuyuechenggong.setTextColor(Color.parseColor("#da0a0a"));
                        txtTimeYuyuechenggong.setTextColor(Color.parseColor("#da0a0a"));
                        txtTimeYuyuechenggong.setText(data.getOrder_time().substring(5, 16));
                        txtTimeYitijiao.setText(data.getCreate_time().substring(5, 16));
                        txtStatus.setText("待付款");
                        txtLijiyuyue.setText("立即付款");

                        break;
                    case "5":
                        txtStatus.setText("已付款");
                        txtLijiyuyue.setText("已付款");

                        img1.setImageResource(R.mipmap.xiantiao2);
                        img2.setImageResource(R.mipmap.xiantiao3);
                        imgYuyuechenggong.setImageResource(R.mipmap.yuyuechengong2);
                        txtYuyuechenggong.setTextColor(Color.parseColor("#da0a0a"));
                        txtTimeYuyuechenggong.setTextColor(Color.parseColor("#da0a0a"));
                        txtTimeYuyuechenggong.setText(data.getOrder_time().substring(5, 16));
                        txtTimeYitijiao.setText(data.getCreate_time().substring(5, 16));
                        break;

                    case "6":
                        img3.setImageResource(R.mipmap.xiantiao2);
                        imgYiwancheng.setImageResource(R.mipmap.yiwancheng2);
                        txtYiwancheng.setTextColor(Color.parseColor("#da0a0a"));
                        txtTimeYiwancheng.setTextColor(Color.parseColor("#da0a0a"));
                        txtTimeZhengzaiweixiu.setText(data.getArrival_time().substring(5, 16));
                        txtTimeYuyuechenggong.setText(data.getOrder_time().substring(5, 16));
                        txtTimeYitijiao.setText(data.getCreate_time().substring(5, 16));
                        txtTimeYiwancheng.setText(data.getFinish_time().substring(5, 16));
                        txtStatus.setText("已完成");
                        txtLijiyuyue.setText("已完成");
                        break;

                    case "7":
                        txtStatus.setText("已评价");
                        txtLijiyuyue.setText("已评价");
                        break;
                    case "8":
                        txtStatus.setText("已取消");
                        txtLijiyuyue.setText("已取消");
                        break;
                    default:
//                        txtStatus.setText("待接单");
//                        txtLijiyuyue.setText("已取消");
                        break;

                }

                txtServiceName.setText(data.getName());
                txtUserName.setText(data.getUser_name());
                txtUserPhone.setText(data.getUser_phone());
                txtUserAddress.setText(data.getCity() + data.getDistrict() + data.getAddress());
                txtYuyueshijian.setText(data.getCreate_time());
                txtQitafeiyong.setText("￥" + data.getOther_costs());
                txtWeixianzuoye.setText("￥" + data.getDangerous_work());
                txtDingdanbianhao.setText(data.getOrder_no());
                String youhui_fee = data.getYouhui_fee();
                if (!"0".equals(youhui_fee)) {
                    txtTaocanjianmian.setText("￥" + data.getSet_meal_costs());
                    layout365.setVisibility(View.VISIBLE);
                }


                txtYouhuijia.setText("￥" + data.getBonus_price());
                txtZongjia.setText("￥" + data.getTotal_amount());
                OrderInfoBean.DataBean.WorkerInfoBean worker_info = data.getWorker_info();
                txtWorkerName.setText(worker_info.getName());
                String phone_number = worker_info.getPhone_number();
                if ("".equals(phone_number)) {
                    txtWorkerPhone.setText("待接单");
                } else {
                    txtWorkerPhone.setText(phone_number);
                }


                List<OrderInfoBean.DataBean.ServiceBean> service = data.getService();
                orderInfoServiceAdapter.setService(service);
                lvServicce.setAdapter(orderInfoServiceAdapter);
                orderInfoServiceAdapter.notifyDataSetChanged();

                List<OrderInfoBean.DataBean.GoodsInfoBean> goods_info = data.getGoods_info();
                orderInfoPeiJianAdapter.setService(goods_info);
                lvPeijian.setAdapter(orderInfoPeiJianAdapter);
                orderInfoPeiJianAdapter.notifyDataSetChanged();

                break;
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String str = txtJianmian.getText().toString();

        String youhui_fee = data.getYouhui_fee();
        double taoCan = 0;
        if (!"".equals(youhui_fee)) {
            taoCan = Double.parseDouble(youhui_fee);

        }
        double taotal = Double.parseDouble(data.getTotal_amount());
        if ("".equals(str)) {
            txtYouhuijia.setText("￥" + (taotal - taoCan));

        } else {
            double youHuiJuan = Double.parseDouble(str.substring(1));
            txtYouhuijia.setText("￥" + (taotal - taoCan - youHuiJuan));
        }


    }


    /*确认对话框*/
    public void setSureDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();

        alertDialog.show();

        View submitView = View.inflate(OrderMessageActivity.this, R.layout.dialog_submit, null);
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
//                waittingProgressBar();
                alertDialog.dismiss();

                TreeMap<String, String> map = new TreeMap<String, String>();
                String time = System.currentTimeMillis() + "";
                String order_sn = data.getOrder_no();
                map.put("timestamp", time);
                map.put("order_sn", order_sn);
                String sign = ToolUitls.getSign(map);
//                ToolUitls.getCallBackStr(Contacts.WX_PAY_URL + "pay?order_sn=" + order_sn + "&timestamp=" + time + "&sign=" + sign + "&key=" + Contacts.KEY);
                payPresonter.getPayInfo(order_sn);

            }
        });
    }

    AlertDialog mWaitingAlertDialog;

    /*等待对话框*/
    public void waittingProgressBar() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        mWaitingAlertDialog = builder.create();

        mWaitingAlertDialog.show();

        View submitView = View.inflate(OrderMessageActivity.this, R.layout.dialog_waitting, null);
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


    /*确认对话框*/
    public void quXiaoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();

        alertDialog.show();

        View submitView = View.inflate(OrderMessageActivity.this, R.layout.dialog_submit, null);
        TextView cancle = (TextView) submitView.findViewById(R.id.txt_dialog_submit_cancle);
        TextView sure = (TextView) submitView.findViewById(R.id.txt_dialog_submit_sure);
        TextView message = (TextView) submitView.findViewById(R.id.txt_messgae);
        message.setText("确认取消该订单？");

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
//                waittingProgressBar();
                alertDialog.dismiss();

                String time = System.currentTimeMillis() + "";
                TreeMap<String, String> map = new TreeMap<>();
                map.put("timestamp", time);
                map.put("user_id", id);
                map.put("id", orderId);
                String sign = ToolUitls.getSign(map);
                orderInfoPresonter.orderCancle(id, orderId, time, sign, Contacts.KEY);
            }
        });
    }

    @Override
    public void getYuZhiFuInfo(PayInfoBean bean) {


        switch (bean.getStatus()) {
            case 1:

                Intent intent = new Intent(this, PayChooseActivity.class);
                intent.putExtra("data", bean);
                startActivity(intent);

                break;
        }


    }

    @Override
    public void onPaySucess(String status) {
        switch (status) {
            case "1":
                ToolUitls.toast(this, "支付成功");
                Intent intent = new Intent(this, OrderActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    PaySucessReciver paySucessReciver;

    public void register() {
        paySucessReciver = new PaySucessReciver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Contacts.PAY_BY_WEI_XIN);
        registerReceiver(paySucessReciver, filter);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(paySucessReciver);
    }

    public class PaySucessReciver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (Contacts.PAY_FLAG.equals("normal")) {
                payPresonter.payForNowNew(data.getOrder_no(), "1", txtYouhuijia.getText().toString().substring(1));
            }

//            ToolUitls.getCallBackStr(Contacts.WX_PAY_URL + "payForNowNew?" + "out_trade_no=" + data.getOrder_no() + "&paytpe=1" + "&total_fee=" + txtYouhuijia.getText().toString());
        }
    }


}
