package com.example.maintainsteward2.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward2.R;
import com.example.maintainsteward2.adapter.ChooseCanNotUseHongBaoAdapter;
import com.example.maintainsteward2.adapter.ChooseCanUseHongBaoAdapter;
import com.example.maintainsteward2.base.BaseActivity;
import com.example.maintainsteward2.base.Contacts;
import com.example.maintainsteward2.bean.HongBaoBean;
import com.example.maintainsteward2.mvp_presonter.ChooseHongBaoPresonter;
import com.example.maintainsteward2.mvp_view.OnGetChooseHongBaoListener;
import com.example.maintainsteward2.utils.ToolUitls;
import com.example.maintainsteward2.view.MyListView;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/25.
 */

public class ChooseHongBaoActivity extends BaseActivity implements OnGetChooseHongBaoListener, ChooseCanUseHongBaoAdapter.OnItemClickListener {

    @BindView(R.id.layout_back)
    LinearLayout layoutBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_can_use_hongbao)
    TextView txtCanUseHongbao;
    @BindView(R.id.lv_can_use_hongbao)
    MyListView lvCanUseHongbao;
    @BindView(R.id.txt_can_not_use_hongbao)
    TextView txtCanNotUseHongbao;
    @BindView(R.id.lv_can_not_use)
    MyListView lvCanNotUse;

    String money;
    @BindView(R.id.img_wuhongbao)
    ImageView imgWuhongbao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosehongbao);
        ButterKnife.bind(this);


        initViews();

        initDatas();
    }

    private void initDatas() {
        TreeMap<String, String> map = new TreeMap<>();
        String timeStamp = System.currentTimeMillis() + "";
        map.put("timestamp", timeStamp);
        map.put("service_money", money);
        map.put("user_id", id);
        String sign = ToolUitls.getSign(map);
//        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "ChooseLuckMoney?" + "user_id=" + id +
//                "&service_money=" + 20 +
//                "&timestamp=" + timeStamp +
//                "&sign=" + sign +
//                "&key=" + Contacts.KEY);
        presonter.getHongBaoList(id, money, timeStamp, sign, Contacts.KEY);


    }

    SharedPreferences sharedPreferences;
    String id;
    ChooseHongBaoPresonter presonter;
    ChooseCanNotUseHongBaoAdapter chooseCanNotUseHongBaoAdapter;
    ChooseCanUseHongBaoAdapter chooseCanUseHongBaoAdapter;

    private void initViews() {
        money = getIntent().getStringExtra("money");
        sharedPreferences = getSharedPreferences(Contacts.USER, MODE_PRIVATE);
        id = sharedPreferences.getString("id", null);

        presonter = new ChooseHongBaoPresonter();
        presonter.setOnGetChooseHongBaoListener(this);
        chooseCanNotUseHongBaoAdapter = new ChooseCanNotUseHongBaoAdapter(this);
        chooseCanUseHongBaoAdapter = new ChooseCanUseHongBaoAdapter(this);
        chooseCanUseHongBaoAdapter.setOnItemClickListener(this);

        lvCanNotUse.setAdapter(chooseCanNotUseHongBaoAdapter);
        lvCanUseHongbao.setAdapter(chooseCanUseHongBaoAdapter);


    }

    @OnClick(R.id.layout_back)
    public void onViewClicked() {
        finish();
    }

    List<HongBaoBean.DataBean.LuckMoneyOkBean> luck_money_ok;

    @Override
    public void getHongBaoListener(HongBaoBean baoBean) {
        switch (baoBean.getStatus()) {

            case "1":
                HongBaoBean.DataBean data = baoBean.getData();
                List<HongBaoBean.DataBean.LuckMoneyNoBean> luck_money_no = data.getLuck_money_no();

                luck_money_ok = data.getLuck_money_ok();

                if (luck_money_no.size() == 0 && luck_money_ok.size() == 0) {
                    imgWuhongbao.setVisibility(View.INVISIBLE);
                }
                if (luck_money_no.size() == 0) {
                    txtCanNotUseHongbao.setVisibility(View.GONE);

                }

                if (luck_money_ok.size() == 0) {
                    txtCanUseHongbao.setVisibility(View.GONE);
                }

                chooseCanNotUseHongBaoAdapter.setLuck_money_no(luck_money_no);
                chooseCanUseHongBaoAdapter.setLuck_money_no(luck_money_ok);

                chooseCanNotUseHongBaoAdapter.notifyDataSetChanged();
                chooseCanUseHongBaoAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onItemClick(int position) {


        HongBaoBean.DataBean.LuckMoneyOkBean luckMoneyOkBean = luck_money_ok.get(position);
        Intent intent = new Intent();
        intent.putExtra("luckMoneyOkBean", luckMoneyOkBean);
        setResult(RESULT_OK, intent);
        finish();


    }
}
