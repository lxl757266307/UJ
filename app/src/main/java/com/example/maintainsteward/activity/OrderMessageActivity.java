package com.example.maintainsteward.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.utils.ToolUitls;
import com.example.maintainsteward.view.MyListView;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.igexin.push.core.g.s;

/**
 * Created by Administrator on 2017/9/20.
 */

public class OrderMessageActivity extends BaseActivity {


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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderId = this.getIntent().getStringExtra("id");
        setContentView(R.layout.activity_order_message);
        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
        id = sharedPreferences.getString("id", null);

        getOrderInfo();
    }

    @OnClick({R.id.layout_back, R.id.txt_worker_phone, R.id.txt_lijiyuyue})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
                break;
            case R.id.txt_worker_phone:
                break;
            case R.id.txt_lijiyuyue:
                break;
        }
    }

    public void getOrderInfo() {
        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("timestamp", time);
        map.put("user_id", id);
        map.put("id", orderId);
        String sign = ToolUitls.getSign(map);

        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "OrderDetails?" + "user_id=" + id + "&id=" + orderId + "&timestamp=" + time + "&sign=" + sign + "&key=" + Contacts.KEY);

    }
}
