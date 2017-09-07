package com.example.maintainsteward.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.adapter.SearchKeyWordAdapter;
import com.example.maintainsteward.base.BaseActivity;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.SearchKeyWordBean;
import com.example.maintainsteward.mvp_presonter.SearchInfoPresonter;
import com.example.maintainsteward.mvp_view.SearchKeyWordListener;
import com.example.maintainsteward.utils.ToolUitls;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler2;

/**
 * Created by Administrator on 2017/9/6.
 */

public class SearInfoActivity extends BaseActivity implements PtrHandler2, SearchKeyWordListener, TextView.OnEditorActionListener {
    @BindView(R.id.edit_search_info_activity)
    EditText editSearchInfoActivity;
    @BindView(R.id.img_qingkong_search_info_activity)
    ImageView imgQingkongSearchInfoActivity;
    @BindView(R.id.txt_cancle_search_info_activity)
    TextView txtCancleSearchInfoActivity;
    @BindView(R.id.rv_search_info_activity)
    RecyclerView mRecycleView;
    @BindView(R.id.ptr_framelayout_serach_info)
    PtrClassicFrameLayout ptrFramelayoutSerachInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        setContentView(R.layout.activity_search_info);
        ButterKnife.bind(this);
        initListener();
        initRecycleview();
        initPresonter();


    }

    private void initListener() {
        editSearchInfoActivity.setOnEditorActionListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    List<SearchKeyWordBean.DataBean> list;
    SearchKeyWordAdapter searchKeyWordAdapter;

    private void initRecycleview() {
        list = new ArrayList<>();
        ptrFramelayoutSerachInfo.setPtrHandler(this);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        searchKeyWordAdapter = new SearchKeyWordAdapter(this, keyword);
        mRecycleView.setAdapter(searchKeyWordAdapter);
    }

    int page = 1;

    SearchInfoPresonter searchInfoPresonter;
    String timestamp = "";
    String sign = "";

    private void initPresonter() {
        searchInfoPresonter = new SearchInfoPresonter();
        searchInfoPresonter.setSearchHotWordListener(this);
        timestamp = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("timestamp", timestamp);
        map.put("keywords", keyword);
        map.put("page", page + "");
        sign = ToolUitls.getSign(map);
        ToolUitls.print("sign", "sign===" + sign);

        searchInfoPresonter.serach(keyword, page, timestamp, sign, Contacts.KEY);


//        String path = Contacts.TEST_BASE_URL + "ServiceSearchByKeywords?"
//                + "timestamp=" + timestamp
//                + "&keywords=" + keyword
//                + "&page=" + page
//                + "&sign=" + sign
//                + "&key=" + Contacts.KEY;
//        ToolUitls.print("path", "path===" + path);
//        ToolUitls.getCallBackStr(path);

    }

    String keyword;

    private void initData() {
        keyword = this.getIntent().getStringExtra("keyword");
    }

    @Override
    public boolean checkCanDoLoadMore(PtrFrameLayout frame, View content, View footer) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) mRecycleView.getLayoutManager();
        //屏幕中最后一个可见子项的position
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        //当前屏幕所看到的子项个数
        int visibleItemCount = layoutManager.getChildCount();
        //当前RecyclerView的所有子项个数
        int totalItemCount = layoutManager.getItemCount();
        //RecyclerView的滑动状态
        int state = mRecycleView.getScrollState();
        if (visibleItemCount > 0 && lastVisibleItemPosition == totalItemCount - 1 && state == mRecycleView.SCROLL_STATE_IDLE) {
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

                searchInfoPresonter.serach(keyword, ++page, timestamp, sign, Contacts.KEY);
                frame.refreshComplete();
            }
        }, 2000);
    }

    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return false;
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {

    }

    public static final String TAG = "SearInfoActivity";

    @Override
    public void onSearchSucess(SearchKeyWordBean hotWordBean) {
        ToolUitls.print(TAG, "hotWordBean==" + hotWordBean);
        switch (hotWordBean.getStatus()) {
            case "1":
                List<SearchKeyWordBean.DataBean> data = hotWordBean.getData();
                list.addAll(data);
                ToolUitls.print(TAG, "list==" + list.size());
                searchKeyWordAdapter.setList(list);
                searchKeyWordAdapter.notifyDataSetChanged();
                break;
            default:
                ToolUitls.toast(this, "暂无更多数据");
                break;
        }

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        if (actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_SEND) {
            if (searchInfoPresonter != null && !"".equals(sign) && !"".equals(timestamp) && !"".equals(keyword)) {
                list.clear();
                searchKeyWordAdapter.setList(list);
                searchKeyWordAdapter.notifyDataSetChanged();
                ToolUitls.print(TAG, "点击了");
                String searchInfo = editSearchInfoActivity.getText().toString().trim();
                searchKeyWordAdapter.setKeyWord(searchInfo);
                TreeMap<String, String> map = new TreeMap<>();
                String timeStamp = System.currentTimeMillis() + "";
                map.put("timestamp", timeStamp);
                map.put("keywords", searchInfo);
                map.put("page", page + "");
                String sign = ToolUitls.getSign(map);
                searchInfoPresonter.serach(searchInfo, page, timeStamp, sign, Contacts.KEY);
            }
        }

        return false;
    }
}
