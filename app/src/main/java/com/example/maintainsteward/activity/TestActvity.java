package com.example.maintainsteward.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/17.
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class TestActvity extends BaseActivity {


    TextView[] txtArray = null;
    @BindView(R.id.txt_base_back)
    ImageView txtBaseBack;
    @BindView(R.id.txt_base_title)
    TextView txtBaseTitle;
    @BindView(R.id.base_layout)
    LinearLayout baseLayout;
    @BindView(R.id.txt_order_all_fragment_order)
    TextView txtOrderAllFragmentOrder;
    @BindView(R.id.txt_commit_fragment_order)
    TextView txtCommitFragmentOrder;
    @BindView(R.id.txt_wait_pay_fragment_order)
    TextView txtWaitPayFragmentOrder;
    @BindView(R.id.txt_wait_evaluation_fragment_order)
    TextView txtWaitEvaluationFragmentOrder;
    @BindView(R.id.txt_cancled_fragment_order)
    TextView txtCancledFragmentOrder;
    @BindView(R.id.vip_fragment_order)
    ViewPager vipFragmentOrder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_order);
        ButterKnife.bind(this);
        setArray();
    }

    public void setArray() {
        txtArray = new TextView[5];
        txtArray[0] = txtOrderAllFragmentOrder;
        txtArray[1] = txtCommitFragmentOrder;
        txtArray[2] = txtWaitPayFragmentOrder;
        txtArray[3] = txtWaitEvaluationFragmentOrder;
        txtArray[4] = txtCancledFragmentOrder;
    }

    @OnClick({R.id.txt_order_all_fragment_order,
            R.id.txt_commit_fragment_order,
            R.id.txt_wait_pay_fragment_order,
            R.id.txt_wait_evaluation_fragment_order,
            R.id.txt_cancled_fragment_order,
    })
    public void setListener(View view) {
        switch (view.getId()) {
            case R.id.txt_order_all_fragment_order:
                setTextViewBackground(0);
                break;
            case R.id.txt_commit_fragment_order:
                setTextViewBackground(1);
                break;
            case R.id.txt_wait_pay_fragment_order:
                setTextViewBackground(2);
                break;
            case R.id.txt_wait_evaluation_fragment_order:
                setTextViewBackground(3);
                break;
            case R.id.txt_cancled_fragment_order:
                setTextViewBackground(4);
                break;

        }

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setTextViewBackground(int index) {

        for (int i = 0; i < txtArray.length; i++) {

            if (index == i) {
                txtArray[i].setBackground(getResources().getDrawable(R.drawable.border_bottom));
                txtArray[i].setTextColor(getResources().getColor(R.color.red));
            } else {
                txtArray[i].setBackgroundResource(R.color.background);
                txtArray[i].setTextColor(getResources().getColor(R.color.black));
            }

        }

    }
}
