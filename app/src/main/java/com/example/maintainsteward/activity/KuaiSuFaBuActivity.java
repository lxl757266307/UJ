package com.example.maintainsteward.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.example.maintainsteward.R;
import com.example.maintainsteward.adapter.PhotoListAdapter;
import com.example.maintainsteward.application.MyApplication;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.PublicBean;
import com.example.maintainsteward.main.MainActivity;
import com.example.maintainsteward.mvp_presonter.KuaiSuFaBuPresonter;
import com.example.maintainsteward.mvp_presonter.LiJiOrderPresonter;
import com.example.maintainsteward.mvp_presonter.UpLoadPhotoPresonter;
import com.example.maintainsteward.mvp_view.KuaiSuFaBuListener;
import com.example.maintainsteward.mvp_view.OnUpLoadPhotoListener;
import com.example.maintainsteward.utils.PhotoUtils;
import com.example.maintainsteward.utils.ToolUitls;
import com.example.maintainsteward.view.MyLayoutManager2;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/9/18.
 */

public class KuaiSuFaBuActivity extends BaseActivity implements OnUpLoadPhotoListener, PhotoListAdapter.OnPhotoClickListener, KuaiSuFaBuListener {

    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.edit_description_kuaisufabu)
    EditText editDescriptionKuaisufabu;
    @BindView(R.id.rv_kuaisufabu)
    RecyclerView rvKuaisufabu;
    @BindView(R.id.txt_yuyue_kuaisufabu)
    TextView txtYuyueKuaisufabu;
    @BindView(R.id.img_kefu_kuaisufabu)
    CircleImageView imgKefuKuaisufabu;

    PhotoListAdapter photoListAdapter;
    @BindView(R.id.txt_xieyi)
    TextView txtXieyi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getActivitiesList().add(this);
        setContentView(R.layout.activity_kuaisufabu);
        ButterKnife.bind(this);
        initPresonter();

    }

    UpLoadPhotoPresonter upLoadPhotoPresonter;
    KuaiSuFaBuPresonter kuaiSuFaBuPresonter;
    List<Bitmap> bitmaps;
    String[] imgArrays;

    private void initPresonter() {
        imgArrays = new String[6];
        for (int i = 0; i < imgArrays.length; i++) {
            imgArrays[i] = "";
        }
        bitmaps = new ArrayList<>();
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.mipmap.tianjiatupian));
        upLoadPhotoPresonter = new UpLoadPhotoPresonter();
        upLoadPhotoPresonter.setListener(this);
        photoListAdapter = new PhotoListAdapter(this);
        photoListAdapter.setOnPhotoClickListener(this);
        kuaiSuFaBuPresonter = new KuaiSuFaBuPresonter();
        kuaiSuFaBuPresonter.setKuaiSuFaBuListener(this);
        photoListAdapter.setList(bitmaps);
        rvKuaisufabu.setLayoutManager(new MyLayoutManager2(this, 4, LinearLayoutManager.VERTICAL, false));
        rvKuaisufabu.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(5, 5, 0, 0);
            }
        });
        rvKuaisufabu.setAdapter(photoListAdapter);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        finish();
    }

    @OnClick(R.id.txt_yuyue_kuaisufabu)
    public void submit(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
        boolean online = sharedPreferences.getBoolean("online", false);

        if (online) {

            setSureDialog();

        } else {
            ToolUitls.toast(this, "您还未登录，请先登录");
            handler.sendEmptyMessageDelayed(2, 1000);
        }


    }

    public static final int REQUREST_CODE = 1;
    public static final int CROP_CODE = 2;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {

                case REQUREST_CODE:
                    if (bitmaps.size() <= 6) {
                        Uri uri = data.getData();
                        PhotoUtils.crop(uri, this, CROP_CODE);
                    }
                    break;
                case CROP_CODE:
                    Bitmap bitmap = data.getParcelableExtra("data");
                    bitmaps.add(0, bitmap);
                    photoListAdapter.setList(bitmaps);
                    photoListAdapter.notifyDataSetChanged();
                    break;

            }
        }

    }

    int count = 0;
    List<Bitmap> photos;
    ArrayList<String> url;

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

    @Override
    public void onUpSucess(String key) {
        url.add(key);

        if (photos != null && count == photos.size()) {
            for (int i = 0; i < url.size(); i++) {
                imgArrays[i] = url.get(i);
            }
//            下单
            orderNow();
        }
    }

    public void orderNow() {
        String service_des = editDescriptionKuaisufabu.getText().toString();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String addTime = simpleDateFormat.format(date);
        SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
        String user_id = sharedPreferences.getString("id", null);
        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("timestamp", time);
        map.put("user_id", user_id);
        map.put("service_des", service_des);
        map.put("add_time", addTime);
        String sign = ToolUitls.getSign(map);

//        ToolUitls.print("----", "user_id=" + user_id + "  service_des=" + service_des + "  imgArrays[0]=" + imgArrays[0]
//                + "  imgArrays[1]=" + imgArrays[1] + " addTime=" + addTime);

//        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL+"ServiceOrderFastSubmit?user_id="+user_id+"&service_des="+service_des+
//        "&add_time="+addTime+"&img1="+imgArrays[0]+"&timestamp="+time+"&sign="+sign+"&key="+Contacts.KEY);

        kuaiSuFaBuPresonter.kuaiSuFaBu(user_id, service_des,
                imgArrays[0], imgArrays[1], imgArrays[2], imgArrays[3], imgArrays[4], imgArrays[5],
                addTime, time, sign, Contacts.KEY);


    }

    @Override
    public void onPhotoClick() {
        if (bitmaps.size() == 7) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        this.startActivityForResult(intent, REQUREST_CODE);

    }

    @Override
    public void onPhotoDelete(int position) {
        bitmaps.remove(position);
        photoListAdapter.setList(bitmaps);
        photoListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFaBuSucess(PublicBean bean) {

        switch (bean.getStatus()) {
            case "1":
                ToolUitls.toast(this, "上传成功");
                mWaitingAlertDialog.dismiss();
                handler.sendEmptyMessageDelayed(1, 1000);
                break;
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            快速发布的列表
            switch (msg.what) {
                case 1:

                    startActivity(new Intent(KuaiSuFaBuActivity.this, MainActivity.class));

                    break;
                case 2:
                    startActivity(new Intent(KuaiSuFaBuActivity.this, LoginActivity.class));
                    finish();
                    break;
            }


        }
    };


    @OnClick(R.id.txt_xieyi)
    public void xieyi() {
        startActivity(new Intent(this, XieYiActivity.class));
    }


    /*确认对话框*/
    public void setSureDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();

        alertDialog.show();

        View submitView = View.inflate(KuaiSuFaBuActivity.this, R.layout.dialog_submit, null);
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

        View submitView = View.inflate(KuaiSuFaBuActivity.this, R.layout.dialog_waitting, null);
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
}
