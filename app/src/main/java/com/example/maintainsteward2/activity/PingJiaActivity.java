package com.example.maintainsteward2.activity;

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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.maintainsteward2.R;
import com.example.maintainsteward2.adapter.PhotoListAdapter2;
import com.example.maintainsteward2.base.BaseActivity;
import com.example.maintainsteward2.base.Contacts;
import com.example.maintainsteward2.bean.PublicBean;
import com.example.maintainsteward2.mvp_presonter.SubmitPingJiaPresonter;
import com.example.maintainsteward2.mvp_presonter.UpLoadPhotoPresonter;
import com.example.maintainsteward2.mvp_view.OnSubmitPingJiaListener;
import com.example.maintainsteward2.mvp_view.OnUpLoadPhotoListener;
import com.example.maintainsteward2.utils.PhotoUtils;
import com.example.maintainsteward2.utils.ToolUitls;
import com.example.maintainsteward2.view.MyLayoutManager2;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/9.
 */

public class PingJiaActivity extends BaseActivity implements TextWatcher, PhotoListAdapter2.OnPhotoClickListener, OnUpLoadPhotoListener, OnSubmitPingJiaListener {

    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.img_photo)
    ImageView imgPhoto;
    @BindView(R.id.txt_service_name)
    TextView txtServiceName;
    @BindView(R.id.img_bumanyi)
    ImageView imgBumanyi;
    @BindView(R.id.txt_bumanyi)
    TextView txtBumanyi;
    @BindView(R.id.img_henmanyi)
    ImageView imgHenmanyi;
    @BindView(R.id.txt_henmanyi)
    TextView txtHenmanyi;
    @BindView(R.id.img_manyi)
    ImageView imgManyi;
    @BindView(R.id.txt_manyi)
    TextView txtManyi;
    @BindView(R.id.cb_fuwu1)
    CheckBox cbFuwu1;
    @BindView(R.id.cb_fuwu2)
    CheckBox cbFuwu2;
    @BindView(R.id.cb_fuwu3)
    CheckBox cbFuwu3;
    @BindView(R.id.cb_fuwu4)
    CheckBox cbFuwu4;
    @BindView(R.id.cb_fuwu5)
    CheckBox cbFuwu5;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.cb_jineng1)
    CheckBox cbJineng1;
    @BindView(R.id.cb_jineng2)
    CheckBox cbJineng2;
    @BindView(R.id.cb_jineng3)
    CheckBox cbJineng3;
    @BindView(R.id.cb_jineng4)
    CheckBox cbJineng4;
    @BindView(R.id.cb_jineng5)
    CheckBox cbJineng5;
    @BindView(R.id.cb_fuzhuang1)
    CheckBox cbFuzhuang1;
    @BindView(R.id.cb_fuzhuang2)
    CheckBox cbFuzhuang2;
    @BindView(R.id.cb_fuzhuang3)
    CheckBox cbFuzhuang3;
    @BindView(R.id.cb_fuzhuang4)
    CheckBox cbFuzhuang4;
    @BindView(R.id.cb_fuzhuang5)
    CheckBox cbFuzhuang5;
    @BindView(R.id.edit_desription)
    EditText editDesription;
    @BindView(R.id.txt_number)
    TextView txtNumber;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.recycle)
    RecyclerView recycle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pingjia);
        ButterKnife.bind(this);
        initInfo();
        initViews();

    }

    String order_id;
    String worker_id;
    String order_no;

    private void initInfo() {
        String cover = getIntent().getStringExtra("cover");
        String name = getIntent().getStringExtra("name");
        order_id = getIntent().getStringExtra("order_id");
        worker_id = getIntent().getStringExtra("worker_id");
        order_no = getIntent().getStringExtra("order_no");

        if (name != null) {
            txtServiceName.setText(name);
        }
        if (!"".equals(cover)) {
            Glide.with(this).load(cover).into(imgPhoto);
        }
    }

    List<CheckBox> fuWus;
    List<CheckBox> jiNeng;
    List<CheckBox> fuZhuang;

    PhotoListAdapter2 photoListAdapter;
    List<Bitmap> bitmaps;
    String[] imgArrays;
    UpLoadPhotoPresonter upLoadPhotoPresonter;
    SubmitPingJiaPresonter submitPingJiaPresonter;

    private void initViews() {

        fuWus = new ArrayList<>();
        jiNeng = new ArrayList<>();
        fuZhuang = new ArrayList<>();

        fuWus.add(cbFuwu1);
        fuWus.add(cbFuwu2);
        fuWus.add(cbFuwu3);
        fuWus.add(cbFuwu4);
        fuWus.add(cbFuwu5);
        jiNeng.add(cbJineng1);
        jiNeng.add(cbJineng2);
        jiNeng.add(cbJineng3);
        jiNeng.add(cbJineng4);
        jiNeng.add(cbJineng5);
        fuZhuang.add(cbFuzhuang1);
        fuZhuang.add(cbFuzhuang2);
        fuZhuang.add(cbFuzhuang3);
        fuZhuang.add(cbFuzhuang4);
        fuZhuang.add(cbFuzhuang5);

        editDesription.addTextChangedListener(this);

        bitmaps = new ArrayList<>();
        bitmaps.add(BitmapFactory.decodeResource(getResources(), R.mipmap.tianjiatupian));
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
        photoListAdapter = new PhotoListAdapter2(this);
        recycle.setAdapter(photoListAdapter);
        photoListAdapter.setOnPhotoClickListener(this);
        photoListAdapter.setList(bitmaps);
        photoListAdapter.notifyDataSetChanged();
        upLoadPhotoPresonter = new UpLoadPhotoPresonter();
        upLoadPhotoPresonter.setListener(this);
        submitPingJiaPresonter = new SubmitPingJiaPresonter();
        submitPingJiaPresonter.setOnSubmitPingJiaListener(this);
    }


    public void setFuWuCkeckBox(int size) {
        for (int i = 0; i < fuWus.size(); i++) {
            fuWus.get(i).setChecked(false);
        }
        for (int i = 0; i < size; i++) {
            fuWus.get(i).setChecked(true);
        }
    }

    public void setJiNengCkeckBox(int size) {
        for (int i = 0; i < jiNeng.size(); i++) {
            jiNeng.get(i).setChecked(false);
        }
        for (int i = 0; i < size; i++) {
            jiNeng.get(i).setChecked(true);
        }
    }

    public void setFuZhuangCkeckBox(int size) {
        for (int i = 0; i < fuZhuang.size(); i++) {
            fuZhuang.get(i).setChecked(false);
        }
        for (int i = 0; i < size; i++) {
            fuZhuang.get(i).setChecked(true);
        }
    }


    @OnClick(R.id.layout_back)
    public void onLayoutBackClicked() {
        finish();
    }


    /*满意度*/
    String star_level;
    /*服务质量满意度*/
    String level1;
    /*技能满意度*/
    String level2;
    /*穿着满意度*/
    String level3;


    @OnClick(R.id.cb_fuwu1)
    public void onCbFuwu1Clicked() {
        setFuWuCkeckBox(1);
        level1 = "1";
    }

    @OnClick(R.id.cb_fuwu2)
    public void onCbFuwu2Clicked() {
        setFuWuCkeckBox(2);
        level1 = "2";
    }

    @OnClick(R.id.cb_fuwu3)
    public void onCbFuwu3Clicked() {
        setFuWuCkeckBox(3);
        level1 = "3";
    }

    @OnClick(R.id.cb_fuwu4)
    public void onCbFuwu4Clicked() {
        setFuWuCkeckBox(4);
        level1 = "4";
    }

    @OnClick(R.id.cb_fuwu5)
    public void onCbFuwu5Clicked() {
        setFuWuCkeckBox(5);
        level1 = "5";
    }

    @OnClick(R.id.cb_jineng1)
    public void onCbJineng1Clicked() {
        setJiNengCkeckBox(1);
        level2 = "1";
    }

    @OnClick(R.id.cb_jineng2)
    public void onCbJineng2Clicked() {
        setJiNengCkeckBox(2);
        level2 = "2";
    }

    @OnClick(R.id.cb_jineng3)
    public void onCbJineng3Clicked() {
        setJiNengCkeckBox(3);
        level2 = "3";
    }

    @OnClick(R.id.cb_jineng4)
    public void onCbJineng4Clicked() {
        setJiNengCkeckBox(4);
        level2 = "4";
    }

    @OnClick(R.id.cb_jineng5)
    public void onCbJineng5Clicked() {
        setJiNengCkeckBox(5);
        level2 = "5";
    }

    @OnClick(R.id.cb_fuzhuang1)
    public void onCbFuzhuang1Clicked() {
        setFuZhuangCkeckBox(1);
        level3 = "1";
    }

    @OnClick(R.id.cb_fuzhuang2)
    public void onCbFuzhuang2Clicked() {
        setFuZhuangCkeckBox(2);
        level3 = "2";
    }

    @OnClick(R.id.cb_fuzhuang3)
    public void onCbFuzhuang3Clicked() {
        setFuZhuangCkeckBox(3);
        level3 = "3";
    }

    @OnClick(R.id.cb_fuzhuang4)
    public void onCbFuzhuang4Clicked() {
        setFuZhuangCkeckBox(4);
        level3 = "4";
    }

    @OnClick(R.id.cb_fuzhuang5)
    public void onCbFuzhuang5Clicked() {
        setFuZhuangCkeckBox(5);
        level3 = "5";
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        int length = editDesription.getText().length();
        txtNumber.setText(length + "/500");
    }


    @OnClick({R.id.img_bumanyi, R.id.img_henmanyi, R.id.img_manyi, R.id.txt_bumanyi, R.id.txt_henmanyi, R.id.txt_manyi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_bumanyi:
            case R.id.txt_bumanyi:
                imgBumanyi.setImageResource(R.mipmap.bumanyi2);
                imgHenmanyi.setImageResource(R.mipmap.henmanyi1);
                imgManyi.setImageResource(R.mipmap.manyi1);
                star_level = "3";
                break;
            case R.id.img_henmanyi:
            case R.id.txt_henmanyi:
                imgBumanyi.setImageResource(R.mipmap.bumanyi1);
                imgHenmanyi.setImageResource(R.mipmap.henmanyi2);
                imgManyi.setImageResource(R.mipmap.manyi1);
                star_level = "1";
                break;
            case R.id.img_manyi:
            case R.id.txt_manyi:
                imgBumanyi.setImageResource(R.mipmap.bumanyi1);
                imgHenmanyi.setImageResource(R.mipmap.henmanyi1);
                imgManyi.setImageResource(R.mipmap.manyi2);
                star_level = "2";
                break;
        }
    }

    @Override
    public void onPhotoClick() {
        if (bitmaps.size() == 4) {
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

    private void orderNow() {

        String content = editDesription.getText().toString();
        String fromtype = "app";
        SharedPreferences sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "");
        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("timestamp", time);
        map.put("user_id", id);
        map.put("order_id", order_id);
        map.put("worker_id", worker_id);
        map.put("star_level", star_level);
        map.put("level1", level1);
        map.put("level2", level2);
        map.put("level3", level3);
        map.put("content", content);
        map.put("fromtype", fromtype);
        String sign = ToolUitls.getSign(map);


        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "OrderEvaluate?" + "user_id=" + id + "&order_id=" + order_id + "&worker_id=" + worker_id
                + "&star_level=" + star_level + "&level1=" + level1 + "&level2=" + level2 + "&level3=" + level3 + "&content=" + content
                + "&fromtype=" + fromtype + "&img1=" + imgArrays[0] + "&img2=" + imgArrays[1] + "&img2=" + imgArrays[2] + "&timestamp=" + time + "&sign=" + sign
                + "&key=" + Contacts.KEY);
//        submitPingJiaPresonter.submit(id, order_id, worker_id, star_level, level1, level2, level3, content, imgArrays[0], imgArrays[1], imgArrays[2], time, sign, Contacts.KEY);

    }


    public static final int CHECK_PHOTO_CODE = 2;
    public static final int CROP_PHOTO_CODE = 3;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {

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

    @Override
    public void submitPingJia(PublicBean publicBean) {
        switch (publicBean.getStatus()) {
            case "1":
                mWaitingAlertDialog.dismiss();
                ToolUitls.toast(this, "评价成功");
                handler.sendEmptyMessageDelayed(1, 1000);
                break;
        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


        }
    };

    @OnClick(R.id.btn_submit)
    public void onViewClicked() {

        setSureDialog();
    }
}
