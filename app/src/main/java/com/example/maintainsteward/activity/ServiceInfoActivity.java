package com.example.maintainsteward.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.adapter.SearviceInfoAdapter;
import com.example.maintainsteward.adapter.ServicePeiJianAdapter;
import com.example.maintainsteward.application.MyApplication;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.MySetMealBean;
import com.example.maintainsteward.bean.SearviceInfoBean;
import com.example.maintainsteward.bean.SecondKindsContent;
import com.example.maintainsteward.bean.ServiceGoodsGetBean;
import com.example.maintainsteward.mvp_presonter.MySetMealPresonter;
import com.example.maintainsteward.mvp_presonter.SearviceInfoPresonter;
import com.example.maintainsteward.mvp_view.MySetMealListener;
import com.example.maintainsteward.mvp_view.ServiceInfoListener;
import com.example.maintainsteward.utils.ToolUitls;
import com.example.maintainsteward.view.MyListView;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/8.
 * <p>
 * 服务详情页
 */

public class ServiceInfoActivity extends BaseActivity implements ServiceInfoListener,
        SearviceInfoAdapter.OnServiceNumberChangeListener,
        ServicePeiJianAdapter.OnPeiJianNumberChangeListener, MySetMealListener {

    public static final String TAG = "ServiceInfoActivity";

    /*返回键*/
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    /*分享按钮*/
    @BindView(R.id.layout_fenxiang)
    LinearLayout layoutFenxiang;
    /*服务列表*/
    @BindView(R.id.lv_service_serviceinfo)
    MyListView lvServiceServiceinfo;
    /*选择配件的图标箭头*/
    @BindView(R.id.img_jiantou_serviceinfo)
    ImageView imgJiantouServiceinfo;
    /*选择配件*/
    @BindView(R.id.laout_peijian_serviceinfo)
    LinearLayout laoutPeijianServiceinfo;
    /*配件列表*/
    @BindView(R.id.lv_peijian_serviceinfo)
    MyListView lvPeijianServiceinfo;
    /*加入会员*/
    @BindView(R.id.layout_huiyuan_serviceinfo)
    LinearLayout layoutHuiyuanServiceinfo;
    /*客服电话*/
    @BindView(R.id.img_kefu_serviceinfo)
    ImageView imgKefuServiceinfo;
    /*立即预约按钮*/
    @BindView(R.id.txt_yuyue_serviceinfo)
    TextView txtYuyueServiceinfo;
    @BindView(R.id.web_service_info)
    WebView webViewServiceInfo;
    @BindView(R.id.txt_service_total_serviceinfo)
    TextView txtServiceTotalServiceinfo;
    @BindView(R.id.txt_peijian_total_serviceinfo)
    TextView txtPeijianTotalServiceinfo;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_yang)
    TextView txtYang;


    @OnClick({R.id.layout_back,
            R.id.layout_fenxiang,
            R.id.laout_peijian_serviceinfo,
            R.id.layout_huiyuan_serviceinfo,
            R.id.img_kefu_serviceinfo,
            R.id.txt_yuyue_serviceinfo})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.layout_fenxiang:

                share();

                break;
            case R.id.laout_peijian_serviceinfo:


                if (peiJian != null && peiJian.size() > 0) {

                    if (lvPeijianServiceinfo.getVisibility() == View.GONE) {
                        lvPeijianServiceinfo.setVisibility(View.VISIBLE);
                        RotateAnimation rotateAnimation = new RotateAnimation(0f, 90f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        rotateAnimation.setDuration(500);
                        rotateAnimation.setFillAfter(true);
                        imgJiantouServiceinfo.startAnimation(rotateAnimation);
                    } else {
                        lvPeijianServiceinfo.setVisibility(View.GONE);
                        RotateAnimation rotateAnimation = new RotateAnimation(90f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        rotateAnimation.setDuration(500);
                        rotateAnimation.setFillAfter(true);
                        imgJiantouServiceinfo.startAnimation(rotateAnimation);
                    }


                } else {
                    ToolUitls.toast(this, "没有更多材料！");
                }


                break;
            case R.id.layout_huiyuan_serviceinfo:
                if (dataBean != null && set_meal != null && set_meal.size() > 0) {
                    Intent intent = new Intent(this, TaoCanGouMaiSucessActivity.class);
                    intent.putExtra("flag", "ServiceInfoActivity");
                    intent.putExtra("data", dataBean);
                    intent.putExtra("page", 3);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(this, TaoCanActivity.class));
                }
//                startActivity(new Intent(this,TaoCanActivity.class));

                break;
            case R.id.img_kefu_serviceinfo:
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:4008293331"));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                this.startActivity(intent);


                break;
            case R.id.txt_yuyue_serviceinfo: {

                SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
                boolean online = sharedPreferences.getBoolean("online", false);

                if (online) {
                    List<SearviceInfoBean.DataBean> service = new ArrayList<>();
                    List<ServiceGoodsGetBean.DataBean> material = new ArrayList<>();
                    if (data != null && data.size() > 0) {
                        for (int i = 0; i < data.size(); i++) {
                            if (data.get(i).getNumber() > 0) {
                                service.add(data.get(i));
                            }
                        }
                    }
                    if (peiJian != null && peiJian.size() > 0) {

                        for (int i = 0; i < peiJian.size(); i++) {

                            if (peiJian.get(i).getNumber() > 0)
                                material.add(peiJian.get(i));
                        }
                    }

                    if (service.size() > 0) {
                        Intent intent2 = new Intent(this, LiJiYuYueActivity.class);
                        intent2.putExtra("service", (Serializable) service);
                        intent2.putExtra("peijian", (Serializable) material);
                        intent2.putExtra("title", title);
                        intent2.putExtra("cat_id", id);
                        startActivity(intent2);
                    }
                } else {
                    ToolUitls.toast(this, "您还未登录，请先登录");
                    handler.sendEmptyMessageDelayed(2, 1500);


                }
            }
            break;
        }

    }

    ProgressDialog dialog;

    public void showDialog() {
        dialog = ProgressDialog.show(this, "", "正在加载....");
        handler.sendEmptyMessageDelayed(1, 1000);

    }

    public void hideDialog() {
        dialog.dismiss();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case 1:
                    hideDialog();

                    break;
                case 2:
                    startActivity(new Intent(ServiceInfoActivity.this, LoginActivity.class));
                    finish();
                    break;
            }

        }
    };


    public static final String MESSAGE = "U匠是一款方便于人们解决日常生活中有关家庭维修、家电清洗等各种家庭中遇到的疑难杂症的APP";

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (popupWindow != null && popupWindow.isShowing()) {
            return false;
        }
        return super.dispatchTouchEvent(ev);
    }

    PopupWindow popupWindow;

    private void share() {

        View view = LayoutInflater.from(this).inflate(R.layout.popuwindow_fenxiang, null);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        LinearLayout weiXin = (LinearLayout) view.findViewById(R.id.layout_weixin);
        LinearLayout pengYouQuan = (LinearLayout) view.findViewById(R.id.layout_pengyouquan);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(false);

        weiXin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WXWebpageObject object = new WXWebpageObject();
                object.webpageUrl = "http://wxtest.cnncsh.com/wx/appstore.html?codeid=0";
                WXMediaMessage mediaMessage = new WXMediaMessage(object);
                mediaMessage.description = "hahahahahaa1111111";
                mediaMessage.title = "多快好准,您的居家能手再此恭候多时了!";
                mediaMessage.description = MESSAGE;

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.logo3);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
                mediaMessage.thumbData = byteArrayOutputStream.toByteArray();

                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = String.valueOf(System.currentTimeMillis());
                req.message = mediaMessage;
                req.scene = SendMessageToWX.Req.WXSceneSession;
//
                api.sendReq(req);
                popupWindow.dismiss();
            }
        });
        pengYouQuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WXWebpageObject object = new WXWebpageObject();
                object.webpageUrl = "http://wxtest.cnncsh.com/wx/appstore.html?codeid=0";
                WXMediaMessage mediaMessage = new WXMediaMessage(object);
                mediaMessage.description = "hahahahahaa1111111";
                mediaMessage.title = "多快好准,您的居家能手再此恭候多时了!";
                mediaMessage.description = MESSAGE;

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.logo3);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
                mediaMessage.thumbData = byteArrayOutputStream.toByteArray();

                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = String.valueOf(System.currentTimeMillis());
                req.message = mediaMessage;
                req.scene = SendMessageToWX.Req.WXSceneTimeline;
//
                api.sendReq(req);
                popupWindow.dismiss();
            }
        });

        popupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);

    }

    IWXAPI api;
    View parentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getActivitiesList().add(this);
        api = MyApplication.api;
        initData();
        initPresonter();
        parentView = LayoutInflater.from(this).inflate(R.layout.activity_service_info, null);
        setContentView(parentView);


        ButterKnife.bind(this);
        txtTitle.setText(title);
        showDialog();
        initWebView();
        initAdapter();
        initService();
        initPeiJian();
        initKindsContent();
        getMySetMeal();

    }

    public void getMySetMeal() {
        SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, Activity.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", null);

        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("user_id", id);
        map.put("timestamp", time);
        String sign = ToolUitls.getSign(map);

//        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "MySetMeal?" + "user_id=" + id + "&timestamp=" + time + "&sign=" + sign + "&key=" + Contacts.KEY);
        mySetMealPresonter.getMySetMeal(id, time, sign, Contacts.KEY);
    }

    private void initKindsContent() {
        if (!"".equals(id) && id != null) {
            String time = System.currentTimeMillis() + "";
            TreeMap<String, String> map = new TreeMap<>();
            map.put("timestamp", time);
            map.put("id", id);
            String sign = ToolUitls.getSign(map);


//            ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "ServiceCategoryListSecondContent?id=" + id + "&timestamp=" + time + "&sign=" + sign + "&key=" + Contacts.KEY);

            searviceInfoPresonter.getSecondKindsContent(id, time, sign, Contacts.KEY);
        }

    }

    private void initPeiJian() {
        if (!"".equals(id) && id != null) {
            String time = System.currentTimeMillis() + "";
            TreeMap<String, String> map = new TreeMap<>();
            map.put("timestamp", time);
            map.put("cat_id", id);
            String sign = ToolUitls.getSign(map);


//            ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "ServiceGoodsGet?cat_id=" + id + "&timestamp=" + time + "&sign=" + sign + "&key=" + Contacts.KEY);

            searviceInfoPresonter.getServiceGoodsGet(id, time, sign, Contacts.KEY);
        }
    }

    private void initWebView() {
        webViewServiceInfo.getSettings().setUseWideViewPort(true);
        webViewServiceInfo.getSettings().setLoadWithOverviewMode(true);
        webViewServiceInfo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    SearviceInfoAdapter searviceInfoAdapter;

    private void initAdapter() {
        searviceInfoAdapter = new SearviceInfoAdapter(this);
        searviceInfoAdapter.setOnServiceNumberChangeListener(this);
    }

    private void initService() {

        if (!"".equals(id) && id != null) {
            String time = System.currentTimeMillis() + "";
            TreeMap<String, String> map = new TreeMap<>();
            map.put("timestamp", time);
            map.put("cat_id", id);
            String sign = ToolUitls.getSign(map);


//            ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "ServiceAll?cat_id=" + id + "&timestamp=" + time + "&sign=" + sign + "&key=" + Contacts.KEY);

            searviceInfoPresonter.getSearviceInfo(id, time, sign, Contacts.KEY);
        }


    }


    SearviceInfoPresonter searviceInfoPresonter;
    MySetMealPresonter mySetMealPresonter;

    private void initPresonter() {
        searviceInfoPresonter = new SearviceInfoPresonter();
        searviceInfoPresonter.setServiceInfoListener(this);
        mySetMealPresonter = new MySetMealPresonter();
        mySetMealPresonter.setMySetMealListener(this);
    }


    String id = "";
    String title = "";

    private void initData() {
        title = this.getIntent().getStringExtra("title");
        id = this.getIntent().getStringExtra("id");
    }

    List<SearviceInfoBean.DataBean> data;

    @Override
    public void getServiceAll(SearviceInfoBean bean) {
        switch (bean.getStatus()) {

            case "1":
                data = bean.getData();
                searviceInfoAdapter.setData(data);
                lvServiceServiceinfo.setAdapter(searviceInfoAdapter);
                searviceInfoAdapter.notifyDataSetChanged();
//                getServiceTotal();
                break;

        }
    }

    @Override
    public void getSecondKindsContent(SecondKindsContent bean) {

        switch (bean.getStatus()) {
            case "1":
                SecondKindsContent.DataBean data = bean.getData();
                String content = data.getContent();
                String url = data.getUrl();
                webViewServiceInfo.loadUrl(url);
                break;
        }

    }

    List<ServiceGoodsGetBean.DataBean> peiJian;

    ServicePeiJianAdapter adapter;

    @Override
    public void getServiceGoodsGet(ServiceGoodsGetBean bean) {
        switch (bean.getStatus()) {
            case "1":
                peiJian = bean.getData();
                adapter = new ServicePeiJianAdapter(this, peiJian);
                adapter.setOnPeiJianNumberChangeListener(this);
                lvPeijianServiceinfo.setAdapter(adapter);
                adapter.notifyDataSetChanged();
//                getPeiJianTotal();
                break;
        }
    }

    @Override
    public void add(int position) {

        if (data != null) {
            data.get(position).setNumber(data.get(position).getNumber() + 1);
            searviceInfoAdapter.setData(data);
            searviceInfoAdapter.notifyDataSetChanged();
        }

        getServiceTotal();
    }

    @Override
    public void reduce(int position) {


        if (data != null) {

            int number = data.get(position).getNumber();

            if (number > 0) {
                data.get(position).setNumber(data.get(position).getNumber() - 1);
                searviceInfoAdapter.setData(data);
                searviceInfoAdapter.notifyDataSetChanged();
            } else {
                return;
            }


        }

        getServiceTotal();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (popupWindow != null && popupWindow.isShowing()) {
                popupWindow.dismiss();
                return true;
            }

            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void addPeiJian(int position) {

        if (peiJian != null && peiJian.size() > 0) {
            peiJian.get(position).setNumber(peiJian.get(position).getNumber() + 1);
            adapter.setPeiJian(peiJian);
            adapter.notifyDataSetChanged();
        }
        getPeiJianTotal();
    }

    @Override
    public void reducePeiJian(int position) {

        if (peiJian != null && peiJian.size() > 0) {

            int number = peiJian.get(position).getNumber();
            if (number > 0) {
                peiJian.get(position).setNumber(number - 1);
                adapter.setPeiJian(peiJian);
                adapter.notifyDataSetChanged();
            } else {
                return;
            }
        }

        getPeiJianTotal();
    }


    /* 服务总价*/
    public void getServiceTotal() {
        double price = 0;

        if (data != null && data.size() > 0) {

            for (int i = 0; i < data.size(); i++) {
                if ("".equals(data.get(i).getExpenses()) || "面议".equals(data.get(i).getExpenses())) {
                    continue;
                }
                if (data.get(i).getNumber() > 0)
                    price += Double.parseDouble(data.get(i).getExpenses()) * data.get(i).getNumber();
            }

        }
        if (price == 0) {
            txtYang.setVisibility(View.INVISIBLE);
            txtServiceTotalServiceinfo.setText("面议");
        } else {
            txtYang.setVisibility(View.VISIBLE);
            txtServiceTotalServiceinfo.setText(price + "");
        }

    }

    /*材料总价*/
    public void getPeiJianTotal() {

        double total = 0;

        if (peiJian != null && peiJian.size() > 0) {

            for (int i = 0; i < peiJian.size(); i++) {

                String price = peiJian.get(i).getPrice();

                if ("".equals(price) || "面议".equals(price)) {

                    continue;
                }
                if (peiJian.get(i).getNumber() > 0)
                    total += Double.parseDouble(price) * peiJian.get(i).getNumber();
            }
        }


        txtPeijianTotalServiceinfo.setText("包含材料费￥" + total + "元");
    }

    MySetMealBean.DataBean dataBean;
    List<MySetMealBean.DataBean.SetMealBean> set_meal;


    @Override
    public void onLoadMySetMeal(MySetMealBean bean) {
        switch (bean.getStatus()) {
            case "1":
                dataBean = bean.getData();
                set_meal = dataBean.getSet_meal();

                break;
        }
    }
}
