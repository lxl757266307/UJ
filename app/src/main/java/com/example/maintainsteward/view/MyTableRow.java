package com.example.maintainsteward.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.activity.ServiceInfoActivity;
import com.example.maintainsteward.base.MySetMealBean;
import com.example.maintainsteward.bean.TaoCanListBean;
import com.example.maintainsteward.utils.ToolUitls;

/**
 * Created by Administrator on 2017/9/27.
 */

public class MyTableRow extends TableRow {
    Context context;

    public MyTableRow(Context context) {
        this(context, null);
        this.context = context;
    }

    TextView name;
    TextView type;
    TextView number;

    TextView status;
    TextView xiaDan;

    public MyTableRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = inflate(context, R.layout.tablerow, null);
        name = (TextView) view.findViewById(R.id.txt_service_name);
        type = (TextView) view.findViewById(R.id.txt_service_type);
        number = (TextView) view.findViewById(R.id.txt_service_number);

        status = (TextView) view.findViewById(R.id.txt_service_status);
        xiaDan = (TextView) view.findViewById(R.id.txt_service_click);
        this.addView(view);

    }

    public void setViews(final MySetMealBean.DataBean.SetMealBean bean) {
        name.setText(bean.getName());

        String na = bean.getContent_desc();
        type.setText(na);
        String limit_num = bean.getLimit_num();
        if ("-1".equals(limit_num)) {
            number.setText("无限次");
        } else {
            number.setText(limit_num + "次");
        }
        xiaDan.setText("立即下单");

        String sta = bean.getNot_use_num();

        if ("0".equals(sta)) {
            status.setText("已使用");
            status.setTextColor(Color.parseColor("#bbbbbb"));
            xiaDan.setTextColor(Color.parseColor("#bbbbbb"));
        } else {
            status.setText("未使用");
            xiaDan.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ServiceInfoActivity.class);
                    intent.putExtra("id", bean.getSecond_id());
                    context.startActivity(intent);

                }
            });
        }


    }

    public void setTitle(String gropName, String ty, String num, String sta, String caoZuo) {
        xiaDan.setTextColor(Color.parseColor("#181818"));
        name.setTextColor(Color.parseColor("#181818"));
        type.setTextColor(Color.parseColor("#181818"));
        number.setTextColor(Color.parseColor("#181818"));
        status.setTextColor(Color.parseColor("#181818"));
        xiaDan.setBackgroundColor(Color.parseColor("#FF6565"));
        name.setBackgroundColor(Color.parseColor("#FF6565"));
        type.setBackgroundColor(Color.parseColor("#FF6565"));
        number.setBackgroundColor(Color.parseColor("#FF6565"));
        status.setBackgroundColor(Color.parseColor("#FF6565"));
        name.setText(gropName);
        type.setText(ty);
        number.setText(num);
        status.setText(sta);
        xiaDan.setText(caoZuo);
    }
}
