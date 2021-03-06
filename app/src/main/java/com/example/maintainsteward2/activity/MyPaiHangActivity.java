package com.example.maintainsteward2.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.maintainsteward2.R;
import com.example.maintainsteward2.adapter.PaiHangBangAdapter;
import com.example.maintainsteward2.application.MyApplication;
import com.example.maintainsteward2.base.BaseActivity;
import com.example.maintainsteward2.base.Contacts;
import com.example.maintainsteward2.bean.PaiHangBean;
import com.example.maintainsteward2.mvp_presonter.PaiHangBangPresonter;
import com.example.maintainsteward2.mvp_view.OnPaiHangBangListener;
import com.example.maintainsteward2.utils.ToolUitls;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;

/**
 * Created by Administrator on 2017/10/7.
 */

public class MyPaiHangActivity extends BaseActivity implements OnPaiHangBangListener {
    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_count)
    TextView txtCount;
    @BindView(R.id.txt_wei)
    TextView txtWei;
    @BindView(R.id.txt_sanji_numner)
    TextView txtSanjiNumner;
    @BindView(R.id.linearLayout3)
    LinearLayout linearLayout3;
    @BindView(R.id.img_p2)
    CircleImageView imgP2;
    @BindView(R.id.txt_name_p2)
    TextView txtNameP2;
    @BindView(R.id.txt_type_p2)
    TextView txtTypeP2;
    @BindView(R.id.txt_number_p2)
    TextView txtNumberP2;
    @BindView(R.id.img_p3)
    CircleImageView imgP3;
    @BindView(R.id.txt_name_p3)
    TextView txtNameP3;
    @BindView(R.id.txt_type_p3)
    TextView txtTypeP3;
    @BindView(R.id.txt_number_p3)
    TextView txtNumberP3;
    @BindView(R.id.img_p1)
    CircleImageView imgP1;
    @BindView(R.id.txt_name_p1)
    TextView txtNameP1;
    @BindView(R.id.txt_type_p1)
    TextView txtTypeP1;
    @BindView(R.id.txt_number_p1)
    TextView txtNumberP1;
    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.ptr_frame)
    PtrClassicFrameLayout ptrFrame;
    @BindView(R.id.txt_type_choose)
    TextView txtTypeChoose;
    View parentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getActivitiesList().add(this);
        parentView = LayoutInflater.from(this).inflate(R.layout.activity_fensipaihang2, null);
        setContentView(parentView);
        ButterKnife.bind(this);
        initUserInfo();
        initRecycle();
//        initPopuWindow();
        initInfo();
    }

    PaiHangBangPresonter paiHangBangPresonter;
    PaiHangBangAdapter paiHangBangAdapter;

    private void initRecycle() {
        paiHangBangAdapter = new PaiHangBangAdapter(this);
        recycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycle.setAdapter(paiHangBangAdapter);
        paiHangBangPresonter = new PaiHangBangPresonter();
        paiHangBangPresonter.setOnPaiHangBangListener(this);

    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    String id;

    private void initUserInfo() {
        SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, Context.MODE_PRIVATE);
        id = sharedPreferences.getString("id", null);
    }

    int page = 1;
    String type = "1";

    private void initInfo() {
        TreeMap<String, String> map = new TreeMap<>();
        String timeStamp = System.currentTimeMillis() + "";
        map.put("timestamp", timeStamp);
        map.put("type", type);
        map.put("page", page + "");
        map.put("user_id", id);
        String sign = ToolUitls.getSign(map);
//        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "ExtendSort?" + "user_id=" + id + "&type=" + type + "&page=" + page + "&timestamp=" + timeStamp + "&key=" + Contacts.KEY + "&sign=" + sign);

        paiHangBangPresonter.getExtendSort(id, type, page + "", timeStamp, sign, Contacts.KEY);
    }

    List<PaiHangBean.DataBeanX.DataBean> dataBeen;

    @Override
    public void getPaiHangBang(PaiHangBean bean) {

        switch (bean.getStatus()) {

            case "1":
                PaiHangBean.DataBeanX data = bean.getData();
                txtCount.setText(data.getMy_rank());
                List<PaiHangBean.DataBeanX.DataBean> dlist = data.getData();

                if (dlist != null && dlist.size() > 0) {

                    if (dlist.get(0) != null) {

                        PaiHangBean.DataBeanX.DataBean dataBean = dlist.get(0);
                        txtNameP1.setText(dataBean.getUser_nicename());
                        txtNumberP1.setText(dataBean.getCounts());
                        Glide.with(this).load(dataBean.getAvatar()).into(imgP1);

                        switch (type) {
                            case "1":
                                txtTypeP1.setText("月排行");
                                break;
                            case "2":
                                txtTypeP1.setText("日排行");
                                break;
                            case "3":
                                txtTypeP1.setText("总排行");
                                break;
                        }

                    } else if (dlist.get(1) != null) {
                        PaiHangBean.DataBeanX.DataBean dataBean = dlist.get(1);
                        txtNameP2.setText(dataBean.getUser_nicename());
                        txtNumberP2.setText(dataBean.getCounts());
                        Glide.with(this).load(dataBean.getAvatar()).into(imgP2);

                        switch (type) {
                            case "1":
                                txtTypeP2.setText("月排行");
                                break;
                            case "2":
                                txtTypeP2.setText("日排行");
                                break;
                            case "3":
                                txtTypeP2.setText("总排行");
                                break;
                        }
                    } else if (dlist.get(2) != null) {
                        PaiHangBean.DataBeanX.DataBean dataBean = dlist.get(2);
                        txtNameP3.setText(dataBean.getUser_nicename());
                        txtNumberP3.setText(dataBean.getCounts());
                        Glide.with(this).load(dataBean.getAvatar()).into(imgP3);

                        switch (type) {
                            case "1":
                                txtTypeP3.setText("月排行");
                                break;
                            case "2":
                                txtTypeP3.setText("日排行");
                                break;
                            case "3":
                                txtTypeP3.setText("总排行");
                                break;
                        }
                    }

                }

                if (dlist != null && dlist.size() > 3) {
                    dataBeen = dlist.subList(3, dlist.size());
                    for (int i = 0; i < dataBeen.size(); i++) {

                        if (type.equals("1")) {
                            dataBeen.get(i).setType(1);
                        }
                        if (type.equals("2")) {
                            dataBeen.get(i).setType(2);
                        }
                        if (type.equals("3")) {
                            dataBeen.get(i).setType(3);
                        }

                    }


                    paiHangBangAdapter.setList(dataBeen);
                    paiHangBangAdapter.notifyDataSetChanged();
                }


                break;
        }
    }


    @OnClick(R.id.txt_type_choose)
    public void onTypeChoose() {
        showDialog();
    }


    /*获取粉丝类型*/
    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();

        alertDialog.show();

        View view = View.inflate(this, R.layout.popu_fensi_type, null);
        TextView txtZong = (TextView) view.findViewById(R.id.txt_zong);
        TextView txtYue = (TextView) view.findViewById(R.id.txt_yue);
        TextView txtRi = (TextView) view.findViewById(R.id.txt_ri);

        Window window = alertDialog.getWindow();
        window.setBackgroundDrawable(getResources().getDrawable(R.drawable.write_form_dialog2));
        window.setContentView(view);
        WindowManager windowManager = this.getWindowManager();

        Display defaultDisplay = windowManager.getDefaultDisplay();

        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = (int) (defaultDisplay.getWidth() * 1.0);
        attributes.gravity = Gravity.BOTTOM;
        window.setAttributes(attributes);
        alertDialog.setCanceledOnTouchOutside(true);
        txtZong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataBeen != null) {
                    dataBeen.clear();
                    paiHangBangAdapter.setList(dataBeen);
                    paiHangBangAdapter.notifyDataSetChanged();
                }

                txtTypeChoose.setText("总粉丝");
                type = "3";
                initInfo();
                alertDialog.dismiss();
            }
        });
        txtYue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataBeen != null) {
                    dataBeen.clear();
                    paiHangBangAdapter.setList(dataBeen);
                    paiHangBangAdapter.notifyDataSetChanged();
                }
                txtTypeChoose.setText("月粉丝");
                type = "1";
                initInfo();
                alertDialog.dismiss();
            }
        });

        txtRi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataBeen != null) {
                    dataBeen.clear();
                    paiHangBangAdapter.setList(dataBeen);
                    paiHangBangAdapter.notifyDataSetChanged();
                }
                txtTypeChoose.setText("日粉丝");
                type = "2";
                initInfo();
                alertDialog.dismiss();
            }
        });
    }
}
