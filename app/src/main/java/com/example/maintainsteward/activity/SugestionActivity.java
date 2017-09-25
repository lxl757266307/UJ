package com.example.maintainsteward.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.base.BaseActivity;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import static com.igexin.push.core.g.R;

/**
 * Created by Administrator on 2017/8/18.
 */

public class SugestionActivity extends BaseActivity  {

//    @BindView(R.id.img_back)
//    ImageView imgBack;
//    @BindView(R.id.txt_title)
//    TextView txtTitle;
//    @BindView(R.id.txt_function_exception_sugestion_activity)
//    TextView txtFunctionException;
//    @BindView(R.id.txt_experience_exception_sugestion_activity)
//    TextView txtExperienceException;
//    @BindView(R.id.txt_new_function_sugestion_activity)
//    TextView txtNewFunctionSugestion;
//    @BindView(R.id.txt_other_sugestion_activity)
//    TextView txtOtherSugestion;
//    @BindView(R.id.edit_sugestion_sugestion_activity)
//    EditText editSugestion;
//    @BindView(R.id.txt_text_number_sugestion_activity)
//    TextView txtTextNumber;
//
//    @BindView(R.id.img_add_photo_sugestion_activity)
//    ImageView imgAddPhoto;
//    @BindView(R.id.btn_submit_sugestion_activity)
//    Button btnSubmit;
//
//    SugestionPhotoAdapter adapter;
//
//    TextView[] textArray;
//
//    public static final int REQEST_CODE = 1;
//    public static final int REQEST_CODE_CROP = 2;
//    @BindView(R.id.layout_photo)
//    LinearLayout layoutPhoto;
//
//    /*储存从相册返回剪裁后的大图 以便于 预览*/
//    List<Bitmap> bitmaps;
//
//    int id;
//    @BindView(R.id.layout_back)
//    LinearLayout layoutBack;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        MyApplication.getInstance().addActivities(this);
//        id = this.getIntent().getIntExtra("user_id", -1);
//        setContentView(R.layout.activity_sugestion);
//        ButterKnife.bind(this);
//        initAddPhotoWidth();
//        setArray();
//        initPresonter();
//        setEditextBox();
//        initBitmaps();
//        initTitle();
//    }
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            finish();
//            return true;
//        }
//
//        return super.onKeyDown(keyCode, event);
//    }
//
//    private void initAddPhotoWidth() {
//
//        int width = this.getWindowManager().getDefaultDisplay().getWidth();
//        ViewGroup.LayoutParams layoutParams = imgAddPhoto.getLayoutParams();
//        layoutParams.height = width / 4 - 10;
//        layoutParams.width = width / 4 - 10;
//        imgAddPhoto.setLayoutParams(layoutParams);
//
//    }
//
//
//    public void initPresonter() {
//        sugestionresonter = new Sugestionresonter();
//        sugestionresonter.setOnUpLoadBitmapListener(this);
//        sugestionresonter.getSugestionType();
//
//    }
//
//    private void initTitle() {
//        txtTitle.setText("意见反馈");
//    }
//
//    @OnClick(R.id.layout_back)
//    public void back() {
//        finish();
//    }
//
//    private void initBitmaps() {
//        bitmaps = new ArrayList<>();
//    }
//
//    public void setArray() {
//
//        textArray = new TextView[4];
//        textArray[0] = txtFunctionException;
//        textArray[1] = txtExperienceException;
//        textArray[2] = txtNewFunctionSugestion;
//        textArray[3] = txtOtherSugestion;
//
//    }
//
//    int type;
//
//    @OnClick({R.id.txt_function_exception_sugestion_activity,
//            R.id.txt_experience_exception_sugestion_activity,
//            R.id.txt_new_function_sugestion_activity,
//            R.id.txt_other_sugestion_activity
//    })
//    public void textViewClick(View view) {
//        switch (view.getId()) {
//            case R.id.txt_function_exception_sugestion_activity:
//                setTextViewBackgroundAndEditextContent(0);
//                type = 1;
//                break;
//            case R.id.txt_experience_exception_sugestion_activity:
//                setTextViewBackgroundAndEditextContent(1);
//                type = 2;
//                break;
//            case R.id.txt_new_function_sugestion_activity:
//                setTextViewBackgroundAndEditextContent(2);
//                type = 3;
//                break;
//            case R.id.txt_other_sugestion_activity:
//                setTextViewBackgroundAndEditextContent(3);
//                type = 4;
//                break;
//        }
//
//    }
//
//    public void setTextViewBackgroundAndEditextContent(int index) {
//
//        for (int i = 0; i < textArray.length; i++) {
//
//            if (i == index) {
//                textArray[i].setBackground(getResources().getDrawable(R.drawable.sugestion_txt_pressed));
//                textArray[i].setTextColor(Color.WHITE);
//            } else {
//                textArray[i].setBackground(getResources().getDrawable(R.drawable.sugestion_txt_normal));
//                textArray[i].setTextColor(Color.BLACK);
//            }
//
//        }
//
//        if (data != null && data.size() > 0) {
//
//            switch (index) {
//                case 0:
//                    editSugestion.setHint(data.get(0).getMsg());
//                    break;
//                case 1:
//                    editSugestion.setHint(data.get(1).getMsg());
//                    break;
//                case 2:
//                    editSugestion.setHint(data.get(2).getMsg());
//                    break;
//                case 3:
//                    editSugestion.setHint(data.get(3).getMsg());
//                    break;
//            }
//
//        }
//
//    }
//
//    public void setEditextBox() {
//        editSugestion.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    editSugestion.setBackground(getResources().getDrawable(R.drawable.box_pressed));
//                } else {
//                    editSugestion.setBackground(getResources().getDrawable(R.drawable.box_normal));
//                }
//            }
//        });
//
//    }
//
//
//    @OnTextChanged(R.id.edit_sugestion_sugestion_activity)
//    public void onTextChanger(CharSequence s, int start, int before, int count) {
//        txtTextNumber.setText(editSugestion.getText().length() + "/500");
//    }
//
//
//    @OnClick(R.id.img_add_photo_sugestion_activity)
//    public void addPhoto() {
//
//        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        intent.setType("image");
//        startActivityForResult(intent, REQEST_CODE);
//  /*      Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setDataAndType(
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image*//*");
//        startActivityForResult(intent, REQEST_CODE);*/
//
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK) {
//
//
//            switch (requestCode) {
//
//                case REQEST_CODE:
//                    Uri uri = data.getData();
//                    PhotoUtils.crop(uri, this, REQEST_CODE_CROP);
//
//                    break;
//                case REQEST_CODE_CROP:
//                    Bitmap bitmap = data.getParcelableExtra("data");
//                    bitmaps.add(bitmap);
//                    final View view = (View) LayoutInflater.from(this).inflate(R.layout.imageview, null);
//                    view.setTag(bitmap);
//                    ImageView imageView = (ImageView) view.findViewById(R.id.img_item);
//                    int windowWidth = this.getWindowManager().getDefaultDisplay().getWidth();
//                    ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
//                    layoutParams.width = windowWidth / 4;
//                    layoutParams.height = windowWidth / 4;
//                    view.setLayoutParams(layoutParams);
//                    imageView.setLayoutParams(layoutParams);
//
//                    ImageView deleteView = (ImageView) view.findViewById(R.id.img_delete);
//
//                    deleteView.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            layoutPhoto.removeView(view);
//                            bitmaps.remove(view.getTag());
//                            if (bitmaps.size() < 3) {
//                                imgAddPhoto.setVisibility(View.VISIBLE);
//                            }
//
//
//                        }
//                    });
//
//                    int width = windowWidth / 4;
//                    int height = windowWidth / 4;
//                    float widthScal = width / (float) bitmap.getWidth();
//                    float heightScal = height / (float) bitmap.getHeight();
//
//
//                    //获取想要缩放的matrix
//                    Matrix matrix = new Matrix();
//                    matrix.postScale(widthScal, heightScal);
//
//
//                    Bitmap showBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
//
//
//                    imageView.setImageBitmap(showBitmap);
//
//                    layoutPhoto.addView(view);
//
//                    if (bitmaps.size() == 3) {
//                        imgAddPhoto.setVisibility(View.INVISIBLE);
//                    }
//                    break;
//            }
//        }
//
//    }
//
//    Sugestionresonter sugestionresonter;
//
//    @OnClick(R.id.btn_submit_sugestion_activity)
//    public void submit() {
//        if (type == 0) {
//            Tools.toast(this, "请选择问题类型！");
//            return;
//        }
//        if (editSugestion.getText().toString().equals("")) {
//            Tools.toast(this, "请填写您的意见！");
//            return;
//        }
//
//        submitDialog();
//    }
//
//
//    StringBuilder builder;
//
//    @Override
//    public void onTokenSucess(String token) {
//        builder = new StringBuilder();
//        for (int i = 0; i < bitmaps.size(); i++) {
//            sugestionresonter.qiNiuYunUpload(bitmaps.get(i), token);
//        }
//
//    }
//
//
//    int count;
//
//    @Override
//    public void onUpSucess(String url) {
////        Log.e(TAG, "onUpSucess=    " + url);
//        builder.append(url).append(",");
////        Log.e(TAG, "builder=    " + builder.toString());
//
//        count++;
//
//        if (count == bitmaps.size()) {
//
//            String trim = editSugestion.getText().toString().trim();
//            if (trim == null) {
//                trim = "";
//            }
//
//            String substrin = "";
//            if (builder.length() > 0) {
//                substrin = builder.substring(0, builder.length() - 1);
//            }
////            Log.e(TAG, "id=  " + id + "   type==" + type + "   trim=" + trim + "   sub=" + substrin);
//        /*此处上传数据*/
//            sugestionresonter.submit(id, type, trim, substrin);
//
//        }
//
//
//    }
//
//    List<SugestionTypeBean.DataBean> data;
//
//    @Override
//    public void getSugestionType(SugestionTypeBean sugestionTypeBean) {
//
//        int state = sugestionTypeBean.getState();
//        if (state == 1) {
//            data = sugestionTypeBean.getData();
//
//            if (data != null && data.size() > 0) {
//                for (int i = 0; i < data.size(); i++) {
////                    Log.e(TAG, "getSugestionType=    " + data.get(i).getType_name() + "    " + data.get(i).getMsg());
//                    textArray[i].setText(data.get(i).getType_name());
//                }
//            }
//
//        }
//
//
//    }
//
//    public static final String TAG = "SugestionActivity";
//
//    @Override
//    public void onSubmitCallBack(SugestionCallBackBean sugestionCallBackBean) {
////        Log.e(TAG, "state=" + sugestionCallBackBean.getState() + "   getState_msg==" + sugestionCallBackBean.getState_msg());
//        switch (sugestionCallBackBean.getState()) {
//
//            case 0:
//                Tools.toast(this, "数据错误，请重试");
//                break;
//            case 1:
//                Tools.toast(this, "提交成功，感谢您的建议，您的反馈是我们努力的动力!");
//                handle.sendEmptyMessageDelayed(1, 2000);
//                break;
//        }
//        alertDialog.dismiss();
//    }
//
//    Handler handle = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            Tools.startActivitytoOther(SugestionActivity.this, NewMainActivity2.class);
//            SugestionActivity.this.finish();
//
//        }
//    };
//
//
//    public void submitDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        final AlertDialog alertDialog = builder.create();
//
//        alertDialog.show();
//
//        View submitView = View.inflate(SugestionActivity.this, R.layout.dialog_submit, null);
//        TextView cancle = (TextView) submitView.findViewById(R.id.txt_dialog_submit_cancle);
//        TextView sure = (TextView) submitView.findViewById(R.id.txt_dialog_submit_sure);
//
//        Window window = alertDialog.getWindow();
//        window.setBackgroundDrawable(getResources().getDrawable(R.drawable.write_form_dialog));
//        window.setContentView(submitView);
//        WindowManager windowManager = this.getWindowManager();
//
//        Display defaultDisplay = windowManager.getDefaultDisplay();
//
//        WindowManager.LayoutParams attributes = window.getAttributes();
//        attributes.width = (int) (defaultDisplay.getWidth() * 0.8);
//        window.setAttributes(attributes);
//        alertDialog.setCanceledOnTouchOutside(false);
//        cancle.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//            }
//        });
//        sure.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//                waittingProgressBar();
//                sugestionresonter.getToken();
//            }
//        });
//
//    }
//
//
//    AlertDialog alertDialog;
//
//    /*等待对话框*/
//    public void waittingProgressBar() {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        alertDialog = builder.create();
//
//        alertDialog.show();
//
//        View submitView = View.inflate(SugestionActivity.this, R.layout.dialog_waitting, null);
//        TextView textView = (TextView) submitView.findViewById(R.id.txt_waitdialog);
//        textView.setText("正在上传...");
//        CircularProgressView processView = (CircularProgressView) submitView.findViewById(R.id.progress_view);
//        processView.setVisibility(View.VISIBLE);
//        processView.setIndeterminate(true);
//        processView.startAnimation();
//
//        Window window = alertDialog.getWindow();
//        window.setBackgroundDrawable(getResources().getDrawable(R.drawable.write_form_dialog));
//        window.setContentView(submitView);
//        WindowManager windowManager = this.getWindowManager();
//
//        Display defaultDisplay = windowManager.getDefaultDisplay();
//
//        WindowManager.LayoutParams attributes = window.getAttributes();
//        attributes.width = (int) (defaultDisplay.getWidth() * 0.6);
//        attributes.height = (int) (defaultDisplay.getHeight() * 0.3);
//        window.setAttributes(attributes);
//        alertDialog.setCanceledOnTouchOutside(false);
//
//    }

}
