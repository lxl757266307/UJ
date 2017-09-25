package com.example.maintainsteward.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maintainsteward.R;
import com.example.maintainsteward.adapter.KaJuanListAdapter;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.CanUseYouHuiQuanBean;
import com.example.maintainsteward.bean.KaJuanBean;
import com.example.maintainsteward.bean.KaJuanCountBean;
import com.example.maintainsteward.mvp_presonter.KaJuanPresonter;
import com.example.maintainsteward.mvp_view.KaJuanListener;
import com.example.maintainsteward.utils.ToolUitls;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler2;

/**
 * Created by Administrator on 2017/9/21.
 */

public class KaJuanYiShiYongFragment extends Fragment implements PtrHandler2, KaJuanListener, KaJuanListAdapter.OnKaJuanItemClickListener {
    @BindView(R.id.recycle)
    RecyclerView recycle;
    @BindView(R.id.prt_frame)
    PtrClassicFrameLayout prtFrame;
    Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kaujuan_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    SharedPreferences sharedPreferences;
    String id;
    int page = 1;
    String type = "2";
    KaJuanPresonter kaJuanPresonter;
    List<KaJuanBean.DataBean.ResultDataBean> resultData;

    KaJuanListAdapter kaJuanListAdapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prtFrame.setPtrHandler(this);
        sharedPreferences = getActivity().getSharedPreferences(Contacts.USER, Context.MODE_PRIVATE);
        id = sharedPreferences.getString("id", null);
        recycle.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        kaJuanPresonter = new KaJuanPresonter();
        kaJuanPresonter.setKaJuanListener(this);
        resultData = new ArrayList<>();
        kaJuanListAdapter = new KaJuanListAdapter(getActivity(), 0);
        kaJuanListAdapter.setOnKaJuanItemClickListener(this);
        getKaJuan();


    }


    public void getKaJuan() {
        TreeMap<String, String> map = new TreeMap<>();
        String timeStamp = System.currentTimeMillis() + "";
        map.put("user_id", id);
        map.put("page", page + "");
        map.put("type", type);
        map.put("timestamp", timeStamp);
        String sign = ToolUitls.getSign(map);
//        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "MyCoupons?" + "user_id=" + id + "&page=" + page + "&type=1" + "&timestamp=" + timeStamp + "&sign=" + sign + "&key=" + Contacts.KEY);
        kaJuanPresonter.getKaJuan(id, type, page + "", timeStamp, sign, Contacts.KEY);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public boolean checkCanDoLoadMore(PtrFrameLayout frame, View content, View footer) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recycle.getLayoutManager();
        //屏幕中最后一个可见子项的position
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        //当前屏幕所看到的子项个数
        int visibleItemCount = layoutManager.getChildCount();
        //当前RecyclerView的所有子项个数
        int totalItemCount = layoutManager.getItemCount();
        //RecyclerView的滑动状态
        int state = recycle.getScrollState();
        if (visibleItemCount > 0 && lastVisibleItemPosition == totalItemCount - 1 && state == recycle.SCROLL_STATE_IDLE) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onLoadMoreBegin(final PtrFrameLayout frame) {
        frame.postDelayed(new Runnable() {
            @Override
            public void run() {

                page++;
                getKaJuan();
                frame.refreshComplete();

            }
        }, 1000);
    }

    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return false;
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {

    }

    ProgressDialog dialog;

    @Override
    public void showDialog() {
        dialog = ProgressDialog.show(getActivity(), "", "正在加载...");
    }

    @Override
    public void hideDialog() {
        dialog.dismiss();
    }

    public static final String TAG = "KaJuanWeiShiYongFragment";

    @Override
    public void onGetKaJuanSucess(KaJuanBean bean) {
        switch (bean.getStatus()) {
            case "1":
                ToolUitls.print(TAG, "KaJuanBean==" + bean);
                KaJuanBean.DataBean data = bean.getData();
                resultData.addAll(data.getResultData());
                kaJuanListAdapter.setList(resultData);
                recycle.setAdapter(kaJuanListAdapter);
                kaJuanListAdapter.notifyDataSetChanged();


                break;
        }
    }

    @Override
    public void onGetKaJuanCountSucess(KaJuanCountBean bean) {

    }

    @Override
    public void onGetCanUseKaJuan(CanUseYouHuiQuanBean bean) {

    }

    @Override
    public void showBlank() {

    }

    @Override
    public void onItemClickItem(int postion) {

        KaJuanBean.DataBean.ResultDataBean resultDataBean = resultData.get(postion);
        Intent intent = new Intent();
        intent.putExtra("kajuan", (Serializable) resultDataBean);
        getActivity().setResult(Activity.RESULT_OK, intent);
        getActivity().finish();


    }
}
