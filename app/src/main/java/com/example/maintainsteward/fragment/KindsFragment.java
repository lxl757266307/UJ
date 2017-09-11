package com.example.maintainsteward.fragment;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.maintainsteward.R;
import com.example.maintainsteward.adapter.FragmentKindsLeftMenuAdapter;
import com.example.maintainsteward.adapter.SecondKindsAdapter;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.FirstKindsBean;
import com.example.maintainsteward.bean.SecondKindsBean;
import com.example.maintainsteward.mvp_presonter.KindsPresonter;
import com.example.maintainsteward.mvp_view.KindsListener;
import com.example.maintainsteward.mvp_presonter.SecondKindsPresonter;
import com.example.maintainsteward.utils.ToolUitls;
import com.example.maintainsteward.view.MyListView;

import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/17.
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class KindsFragment extends Fragment implements KindsListener, AdapterView.OnItemClickListener, SecondKindsAdapter.OnSecondeItemClickListener {
    public static final String TAG = "KindsFragment";

    @BindView(R.id.edit_sousuo_fragment_kinds)
    EditText editSousuoFragmentKinds;
    @BindView(R.id.img_tianjia_fragment_kinds)
    ImageView imgTianjiaFragmentKinds;

    @BindView(R.id.txt_listname_fragment_kinds)
    TextView txtListnameFragmentKinds;
    @BindView(R.id.rv_recycle_fragment_kinds)
    RecyclerView rvRecycleFragmentKinds;

    TextView[] txtArray = null;

    Unbinder unbinder;
    @BindView(R.id.layout_list)
    LinearLayout layoutList;
    @BindView(R.id.lv_fragment_kinds)
    MyListView lvFragmentKinds;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kinds, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    FragmentKindsLeftMenuAdapter menuAdapter;
    SecondKindsPresonter secondKindsPresonter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*初始化recycleview*/
        initRv();

        lvFragmentKinds.setOnItemClickListener(this);

        kindsPresonter = new KindsPresonter();
        kindsPresonter.setFirstKindsListener(this);

        secondKindsPresonter = new SecondKindsPresonter();
        secondKindsPresonter.setFirstKindsListener(this);

        /*获取一级分类*/
        getFirstKinds();


    }

    SecondKindsAdapter secondKindsAdapter;

    private void initRv() {
        rvRecycleFragmentKinds.setLayoutManager(new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false));
//        rvRecycleFragmentKinds.addItemDecoration(new RecyclerView.ItemDecoration() {
//            @Override
//            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                super.getItemOffsets(outRect, view, parent, state);
//
//                outRect.set(10, 10, 0, 0);
//
//            }
//        });
        secondKindsAdapter = new SecondKindsAdapter(getActivity());
        secondKindsAdapter.setOnSecondeItemClickListener(this);
        rvRecycleFragmentKinds.setAdapter(secondKindsAdapter);

    }

    public void getSecondKinds(int index) {
        if (data != null) {
            FirstKindsBean.DataBean dataBean = data.get(index);
            String id = dataBean.getId();
            String time = System.currentTimeMillis() + "";
            TreeMap<String, String> map = new TreeMap<>();
            map.put("timestamp", time);
            map.put("id", id);
//            map.put("cat_id", "21");
            String sign = ToolUitls.getSign(map);
//            ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL+"ServiceAll?"+"cat_id="+"21"+"&timestamp="+time+"&sign="+sign+"&key="+Contacts.KEY);
            secondKindsPresonter.getSecondKinds(id, time, sign, Contacts.KEY);

        }


    }

    KindsPresonter kindsPresonter;

    private void getFirstKinds() {

        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("timestamp", time);
        String sign = ToolUitls.getSign(map);
        kindsPresonter.getFirstKinds(time, sign, Contacts.KEY);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setTextViewBackground(int index) {

        if (data != null) {

            for (int i = 0; i < data.size(); i++) {

                if (i == index) {
                    data.get(i).setCheck(true);
                } else {
                    data.get(i).setCheck(false);
                }

            }
            if (menuAdapter != null) {
                menuAdapter.setData(data);
                menuAdapter.notifyDataSetChanged();
            }

        }

    }

    List<FirstKindsBean.DataBean> data;

    @Override
    public void getFirstKinds(FirstKindsBean bean) {

        switch (bean.getStatus()) {
            case "1":
                data = bean.getData();
                data.get(0).setCheck(true);
                menuAdapter = new FragmentKindsLeftMenuAdapter(getActivity(), data);
                lvFragmentKinds.setAdapter(menuAdapter);
                menuAdapter.notifyDataSetChanged();
                getSecondKinds(0);
                break;
        }

    }

    @Override
    public void getSecondKinds(SecondKindsBean bean) {

        switch (bean.getStatus()) {
            case "1":


                SecondKindsBean.DataBean data = bean.getData();

                if (data != null) {

                    String title = data.getTitle();
                    txtListnameFragmentKinds.setText(title);

                    List<SecondKindsBean.DataBean.ResultBean> result = data.getResult();
                    secondKindsAdapter.setList(result);
                    secondKindsAdapter.notifyDataSetChanged();

                }


                break;
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        setTextViewBackground(position);
        getSecondKinds(position);
    }


    @Override
    public void onItemClick(int position) {


    }
}
