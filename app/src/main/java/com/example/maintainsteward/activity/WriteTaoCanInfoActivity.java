package com.example.maintainsteward.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.CityListBean;
import com.example.maintainsteward.fragment.MyDialogFragment;
import com.example.maintainsteward.mvp_presonter.ChooseLocationPresonter;
import com.example.maintainsteward.mvp_view.ChooseLocationListener;
import com.example.maintainsteward.utils.ToolUitls;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/26 0026.
 */

public class WriteTaoCanInfoActivity extends BaseActivity implements MyDialogFragment.OnAddressChoosedListener, ChooseLocationListener {
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixiuxinxi);
        ButterKnife.bind(this);
        initCityAndDistrict();
    }

    @OnClick({R.id.layout_back, R.id.layout_xuanzedizhi, R.id.layout_weixinzhifu, R.id.layout_yuezhifu, R.id.btn_tijiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_back:
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


                break;
            case R.id.layout_yuezhifu:

                if (cbWeixin.isChecked()) {
                    cbWeixin.setChecked(false);
                    cbWeixin.setVisibility(View.INVISIBLE);
                }
                cbYue.setChecked(true);
                cbYue.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_tijiao:
                break;
        }
    }

    /*必须设置在Activity中否则不显示*/
    public void setDialog() {
        MyDialogFragment dialogFragment = new MyDialogFragment();
        if (data != null) {
            dialogFragment.setData(data);
        }
        dialogFragment.setOnAddressChoosedListener(this);
        dialogFragment.show(getSupportFragmentManager(), "");
    }

    String location = "";
    String city = "";
    String district = "";

    @Override
    public void onAddressChoosed(String[] str, String[] id) {
        city = id[0];
        district = id[1];
        for (int i = 0; i < str.length; i++) {
            if (str[i].equals("请选择") || str[i].equals("")) {
                return;
            } else {
                location += str[i];
            }
        }
        imageView.setVisibility(View.GONE);
        txtJutidizhi.setText(location);

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

    List<CityListBean.DataBean> data;

    @Override
    public void getCityList(CityListBean body) {
        switch (body.getStatus()) {
            case "1":
                data = body.getData();
                break;
        }
    }
}
