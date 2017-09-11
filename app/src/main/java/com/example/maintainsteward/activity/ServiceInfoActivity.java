package com.example.maintainsteward.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.bean.SearchKeyWordBean;
import com.example.maintainsteward.view.MyListView;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/8.
 * <p>
 * 服务详情页
 */

public class ServiceInfoActivity extends BaseActivity {

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
        setContentView(R.layout.activity_service_info);
        ButterKnife.bind(this);
    }

    SearchKeyWordBean.DataBean data;

    private void initData() {
        data = (SearchKeyWordBean.DataBean) this.getIntent().getSerializableExtra("data");

    }


}
