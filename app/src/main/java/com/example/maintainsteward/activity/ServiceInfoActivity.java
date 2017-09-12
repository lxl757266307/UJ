package com.example.maintainsteward.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.adapter.SearviceInfoAdapter;
import com.example.maintainsteward.adapter.ServicePeiJianAdapter;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.SearviceInfoBean;
import com.example.maintainsteward.bean.SecondKindsContent;
import com.example.maintainsteward.bean.ServiceGoodsGetBean;
import com.example.maintainsteward.mvp_presonter.SearviceInfoPresonter;
import com.example.maintainsteward.mvp_view.ServiceInfoListener;
import com.example.maintainsteward.utils.ToolUitls;
import com.example.maintainsteward.view.MyListView;

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
        ServicePeiJianAdapter.OnPeiJianNumberChangeListener {

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


                }


                break;
            case R.id.layout_huiyuan_serviceinfo:
                break;
            case R.id.img_kefu_serviceinfo:
                break;
            case R.id.txt_yuyue_serviceinfo:
                break;
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initPresonter();
        setContentView(R.layout.activity_service_info);
        ButterKnife.bind(this);
        initWebView();
        initAdapter();
        initService();
        initPeiJian();
        initKindsContent();

    }

    private void initKindsContent() {
        if (!"".equals(id) && id != null) {
            String time = System.currentTimeMillis() + "";
            TreeMap<String, String> map = new TreeMap<>();
            map.put("timestamp", time);
            map.put("id", id);
            String sign = ToolUitls.getSign(map);

            ToolUitls.print(TAG, "sign333333===" + sign);

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

            ToolUitls.print(TAG, "sign222222222===" + sign);

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

            ToolUitls.print(TAG, "sign===" + sign);

//            ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "ServiceAll?cat_id=" + id + "&timestamp=" + time + "&sign=" + sign + "&key=" + Contacts.KEY);

            searviceInfoPresonter.getSearviceInfo(id, time, sign, Contacts.KEY);
        }


    }


    SearviceInfoPresonter searviceInfoPresonter;

    private void initPresonter() {
        searviceInfoPresonter = new SearviceInfoPresonter();
        searviceInfoPresonter.setServiceInfoListener(this);

    }


    String id = "";

    private void initData() {
        id = this.getIntent().getStringExtra("id");
        ToolUitls.print(TAG,"ID==="+id);
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
                ToolUitls.print(TAG, "URL==========" + url);
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


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
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

    }
}
