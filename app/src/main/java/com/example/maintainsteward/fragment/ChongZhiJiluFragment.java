package com.example.maintainsteward.fragment;

import android.app.ProgressDialog;
import android.content.Context;
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
import com.example.maintainsteward.adapter.LogListAdapter;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.LogListBean;
import com.example.maintainsteward.mvp_presonter.QianBaoPresonter;
import com.example.maintainsteward.mvp_view.LogListListener;
import com.example.maintainsteward.utils.ToolUitls;

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

public class ChongZhiJiluFragment extends Fragment implements PtrHandler2, LogListListener {
    @BindView(R.id.recycle)
    RecyclerView recycle;
    Unbinder unbinder;
    @BindView(R.id.ptr_frame)
    PtrClassicFrameLayout ptrFrame;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.log_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    SharedPreferences sharedPreferences;
    String id;
    QianBaoPresonter qianBaoPresonter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        page = 1;
        sharedPreferences = getActivity().getSharedPreferences(Contacts.USER, Context.MODE_PRIVATE);
        id = sharedPreferences.getString("id", null);
        qianBaoPresonter = new QianBaoPresonter();
        qianBaoPresonter.setLogListListener(this);
        ptrFrame.setPtrHandler(this);
        initRecycle();
        getXiaoFeiJiLu(page);

    }

    LogListAdapter logListAdapter;
    List<LogListBean.DataBean> data;

    private void initRecycle() {
        data = new ArrayList<>();
        recycle.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        logListAdapter = new LogListAdapter(getActivity());
    }


    int page = 1;

    public void getXiaoFeiJiLu(int page) {
        TreeMap<String, String> map = new TreeMap<>();
        String timeStamp = System.currentTimeMillis() + "";
        String type = "2";
        map.put("user_id", id);
        map.put("type", type);
        map.put("page", page + "");
        map.put("timestamp", timeStamp);
        String sign = ToolUitls.getSign(map);
//        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "MyWalletBalance?" + "user_id=" + id + "&type=" + "1" + "&page=" + page + "&timestamp=" + timeStamp + "&sign=" + sign + "&key=" + Contacts.KEY);

        qianBaoPresonter.getMyWalletBalance(id, type, page + "", timeStamp, sign, Contacts.KEY);
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

    int index = 1;

    @Override
    public void onLoadMoreBegin(final PtrFrameLayout frame) {

        frame.postDelayed(new Runnable() {
            @Override
            public void run() {

                index++;
                getXiaoFeiJiLu(index);

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
    public void showDilog() {
        dialog = ProgressDialog.show(getActivity(), "", "正在加载...");
    }

    @Override
    public void hideDialog() {
        dialog.dismiss();
    }

    @Override
    public void OnGetLogListSucess(LogListBean bean) {

        switch (bean.getStatus()) {
            case "1":
                data.addAll(bean.getData());
                logListAdapter.setList(data);
                recycle.setAdapter(logListAdapter);
                logListAdapter.notifyDataSetChanged();

                break;
        }
    }
}
