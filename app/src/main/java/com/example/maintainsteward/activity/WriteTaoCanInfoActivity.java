package com.example.maintainsteward.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.application.MyApplication;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.MySetMealBean;
import com.example.maintainsteward.bean.CityListBean;
import com.example.maintainsteward.bean.PayInfoBean;
import com.example.maintainsteward.bean.TaoCallBackBean;
import com.example.maintainsteward.bean.TaoCanListBean;
import com.example.maintainsteward.fragment.MyDialogFragment;
import com.example.maintainsteward.mvp_presonter.ChooseLocationPresonter;
import com.example.maintainsteward.mvp_presonter.MySetMealPresonter;
import com.example.maintainsteward.mvp_presonter.Pay365Presonter;
import com.example.maintainsteward.mvp_presonter.TaoCanPayPresonter;
import com.example.maintainsteward.mvp_view.ChooseLocationListener;
import com.example.maintainsteward.mvp_view.MySetMealListener;
import com.example.maintainsteward.mvp_view.OnPayListener;
import com.example.maintainsteward.mvp_view.TaoCanPayLitener;
import com.example.maintainsteward.utils.ToolUitls;
import com.tencent.mm.opensdk.modelpay.PayReq;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/26 0026.
 */

public class WriteTaoCanInfoActivity extends BaseActivity implements MyDialogFragment.OnAddressChoosedListener, ChooseLocationListener, TaoCanPayLitener, OnPayListener, MySetMealListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.layout_xuanzedizhi)
    LinearLayout layoutXuanzedizhi;
    @BindView(R.id.edit_address)
    EditText editAddress;
    @BindView(R.id.edit_xingming)
    EditText editXingming;
    @BindView(R.id.edit_dianhua)
    EditText editDianhua;
    @BindView(R.id.cb_weixin)
    CheckBox cbWeixin;
    @BindView(R.id.layout_weixinzhifu)
    LinearLayout layoutWeixinzhifu;
    @BindView(R.id.cb_yue)
    CheckBox cbYue;
    @BindView(R.id.layout_yuezhifu)
    LinearLayout layoutYuezhifu;
    @BindView(R.id.btn_tijiao)
    Button btnTijiao;
    @BindView(R.id.txt_jutidizhi)
    TextView txtJutidizhi;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    String msg = "";
    String userId = "";
    TaoCanPayPresonter payPresonter;
    Pay365Presonter pay365Presonter;
    List<TaoCanListBean.DataBean.SetMealDataBean> groupData;
    MySetMealPresonter mySetMealPresonter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        msg = this.getIntent().getStringExtra("msg");
        groupData = (List<TaoCanListBean.DataBean.SetMealDataBean>) this.getIntent().getSerializableExtra("group");
        SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
        userId = sharedPreferences.getString("id", null);
        setContentView(R.layout.activity_weixiuxinxi);
        ButterKnife.bind(this);
        initCityAndDistrict();
        payPresonter = new TaoCanPayPresonter();
        payPresonter.setTaoCanPayLitener(this);
        pay365Presonter = new Pay365Presonter();
        pay365Presonter.setOnPayListener(this);
        mySetMealPresonter = new MySetMealPresonter();
        mySetMealPresonter.setMySetMealListener(this);
        register();
    }

    String payType = "1";
    String address = "";
    String tel = "";
    String taoCanId = "1";

    @OnClick({R.id.layout_back, R.id.layout_xuanzedizhi, R.id.layout_weixinzhifu, R.id.layout_yuezhifu, R.id.btn_tijiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                finish();
                break;
            case R.id.layout_xuanzedizhi:
                setDialog();
                break;
            case R.id.layout_weixinzhifu:
                if (cbYue.isChecked()) {
                    cbYue.setChecked(false);
                    cbYue.setVisibility(View.INVISIBLE);
                }
                cbWeixin.setChecked(true);
                cbWeixin.setVisibility(View.VISIBLE);
                payType = "1";


                break;
            case R.id.layout_yuezhifu:

                ToolUitls.toast(this, "拼命开发中....");
//                if (cbWeixin.isChecked()) {
//                    cbWeixin.setChecked(false);
//                    cbWeixin.setVisibility(View.INVISIBLE);
//                }
//                cbYue.setChecked(true);
//                cbYue.setVisibility(View.VISIBLE);
//                payType = "2";
                break;
            case R.id.btn_tijiao:


//                Intent intent = new Intent(this, TaoCanGouMaiSucessActivity.class);
//                intent.putExtra("group", (Serializable) groupData);
//                startActivity(intent);

                address = editAddress.getText().toString();

                tel = editDianhua.getText().toString();
                String name = editXingming.getText().toString();

                if ("".equals(address) || "".equals(tel) || "".equals(name) || "".equals(address)) {
                    ToolUitls.toast(this, "信息填写不完整，请填写完整信息");
                    return;
                }

                String time = System.currentTimeMillis() + "";
                TreeMap<String, String> map = new TreeMap<>();
                map.put("timestamp", time);
                map.put("user_id", userId);
                map.put("id", taoCanId);
                map.put("paytype", payType);
                map.put("city", city);
                map.put("district", district);
                map.put("address", address);
                map.put("msg", msg);
                map.put("tel", tel);

                String sign = ToolUitls.getSign(map);


//                ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "SetMealBuyOrder?" +
//                        "user_id=" + userId + "&id=" + taoCanId
//                        + "&paytype=" + payType + "&city=" + city +
//                        "&district=" + district + "&address=" + address +
//                        "&msg=" + msg + "&tel=" + tel + "&sign=" + sign + "&timestamp=" + time
//                        + "&key=" + Contacts.KEY);
                payPresonter.getTaoCanOrderNo(userId, taoCanId, payType, city, district, address, msg, tel, time, sign, Contacts.KEY);

                break;
        }
    }

    /*必须设置在Activity中否则不显示*/
    public void setDialog() {
        MyDialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.setAddress("");
        if (data != null) {
            dialogFragment.setData(data);
        }
        dialogFragment.setOnAddressChoosedListener(this);
        dialogFragment.show(getSupportFragmentManager(), "");
    }

    String location = "";
    String city = "";
    String district = "";


    private void initCityAndDistrict() {

        ChooseLocationPresonter chooseLocationPresonter = new ChooseLocationPresonter();
        chooseLocationPresonter.setChooseLocationListener(this);
        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("timestamp", time);
        String sign = ToolUitls.getSign(map);
        chooseLocationPresonter.getCityList(time, sign, Contacts.KEY);

    }

    List<CityListBean.DataBean> data;

    @Override
    public void getCityList(CityListBean body) {
        switch (body.getStatus()) {
            case "1":
                List<CityListBean.DataBean> dataBeen = body.getData();
                CityListBean.DataBean dataBean2 = dataBeen.get(0);
                data = new ArrayList<>();
                data.add(dataBean2);


                break;
        }
    }

    String order_sn = "";

    @Override
    public void orderTaoCan(TaoCallBackBean backBean) {

        switch (backBean.getStatus()) {
            case "1":
                TaoCallBackBean.DataBean data = backBean.getData();
                order_sn = data.getOrder_sn();
                pay365Presonter.getPayInfo(order_sn);
                break;
        }
    }

    @Override
    public void getYuZhiFuInfo(PayInfoBean bean) {

        switch (bean.getStatus()) {
            case 1:
                Contacts.PAY_FLAG = "taoCan";
                String appid = bean.getAppid();
                String noncestr = bean.getNoncestr();
                String packageX = bean.getPackageX();
                String partnerid = bean.getPartnerid();
                String prepayid = bean.getPrepayid();
                String sign = bean.getSign();
                int timestamp = bean.getTimestamp();
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

    @Override
    public void onPaySucess(String status) {
        if ("1".equals(status)) {
            ToolUitls.toast(this, "支付成功");
            getMySetMeal();

        }
    }


    public void getMySetMeal() {
        SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
        String id = sharedPreferences.getString("id", null);

        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("user_id", id);
        map.put("timestamp", time);
        String sign = ToolUitls.getSign(map);

//        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "MySetMeal?" + "user_id=" + id + "&timestamp=" + time + "&sign=" + sign + "&key=" + Contacts.KEY);
        mySetMealPresonter.getMySetMeal(userId, time, sign, Contacts.KEY);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(reciver);
    }

    FefreshReciver reciver;

    public void register() {
        reciver = new FefreshReciver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Contacts.PAY_BY_WEI_XIN);
        registerReceiver(reciver, filter);
    }

    @Override
    public void onLoadMySetMeal(MySetMealBean bean) {

        MySetMealBean.DataBean data = bean.getData();
        Intent intent = new Intent(this, TaoCanGouMaiSucessActivity.class);
        intent.putExtra("data", (Serializable) data);
        startActivity(intent);
        finish();

    }

    @Override
    public void onAddressChoosed(String[] array, String str, String[] id) {
        city = id[0];
        district = id[1];
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals("请选择") || array[i].equals("")) {
                return;
            } else {
                location += array[i];
            }
        }
        imageView.setVisibility(View.GONE);
        txtJutidizhi.setText(str);
    }

    class FefreshReciver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Contacts.PAY_FLAG.equals("taoCan")) {
                pay365Presonter.payForNowNew(order_sn, payType, "399");
            }
        }
    }


}
