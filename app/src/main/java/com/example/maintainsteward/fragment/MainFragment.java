package com.example.maintainsteward.fragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.maintainsteward.R;
import com.example.maintainsteward.activity.ChooseLocationActivity;
import com.example.maintainsteward.activity.HotNewsActivity;
import com.example.maintainsteward.activity.JingXuanPaiHangActivity;
import com.example.maintainsteward.activity.LiJiYuYueActivity;
import com.example.maintainsteward.activity.SearchActivity;
import com.example.maintainsteward.activity.TaoCanActivity;
import com.example.maintainsteward.activity.TaoCanGouMaiSucessActivity;
import com.example.maintainsteward.application.MyApplication;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.MySetMealBean;
import com.example.maintainsteward.bean.AppIndexCategoryBean;
import com.example.maintainsteward.bean.BannerBean;
import com.example.maintainsteward.bean.HotNewsList;
import com.example.maintainsteward.inter.OnMainServiceClickListener;
import com.example.maintainsteward.mvp_presonter.HotNewPresonter;
import com.example.maintainsteward.mvp_presonter.MainFragmentPresonter;
import com.example.maintainsteward.mvp_presonter.MySetMealPresonter;
import com.example.maintainsteward.mvp_view.HotNewsListener;
import com.example.maintainsteward.mvp_view.MySetMealListener;
import com.example.maintainsteward.mvp_view.OnLoadBannerListener;
import com.example.maintainsteward.utils.ToolUitls;
import com.example.maintainsteward.view.BannerViewPager;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/15.
 */

public class MainFragment extends Fragment implements View.OnScrollChangeListener, BannerViewPager.OnBannerClick, OnLoadBannerListener, MySetMealListener, HotNewsListener {

    Unbinder unbinder;
    /*定位图标*/
    @BindView(R.id.img_dingwei_mainfragment)
    ImageView imgDingweiMainfragment;
    /*定位文字布局*/
    @BindView(R.id.layout_dingwei_mianfragment)
    LinearLayout layoutDingweiMianfragment;
    /*添加按钮*/
    @BindView(R.id.img_tianjia_mainfragment)
    ImageView imgTianjiaMainfragment;
    /*搜索按钮*/
    @BindView(R.id.layout_sousuo_mainfragment)
    LinearLayout layoutSousuoMainfragment;
    /*服务种类按钮*/
    @BindView(R.id.layout_service_mainfragment)
    LinearLayout layoutServiceMainfragment;
    /*精选排行按钮*/
    @BindView(R.id.layout_choose_mainfragment)
    LinearLayout layoutChooseMainfragment;
    /*购买套餐按钮*/
    @BindView(R.id.layout_combo_mainfragment)
    LinearLayout layoutComboMainfragment;
    /*意见反馈按钮*/
    @BindView(R.id.layout_sugestion_mainfragment)
    LinearLayout layoutSugestionMainfragment;
    /*轮播图*/
    @BindView(R.id.vip_main_fragment)
    BannerViewPager vipMainFragment;
    /*垂直轮播图*/
    @BindView(R.id.vf_mainfragment)
    ViewFlipper vfMainfragment;
    /*更多按钮*/
    @BindView(R.id.txt_gengduo_mainfragment)
    TextView txtGengduoMainfragment;
    /*空调清洗条目*/
    @BindView(R.id.layout_kongtiaoqingxi_mainfragment)
    LinearLayout layoutKongtiaoqingxiMainfragment;
    /*冰箱清洗按钮*/
    @BindView(R.id.layout_bingxiangqingxi_mainfragment)
    LinearLayout layoutBingxiangqingxiMainfragment;
    /*洗衣机清洗按钮*/
    @BindView(R.id.layout_xiyijiqingxi_mainfragment)
    LinearLayout layoutXiyijiqingxiMainfragment;
    /*抽油烟机清洗条目按钮*/
    @BindView(R.id.layout_chouyouyanji_mainfragment)
    LinearLayout layoutChouyouyanjiMainfragment;

    /*上啦加载下拉刷新的 scrollview*/
    @BindView(R.id.scrollview_main_fragment)
    PullToRefreshScrollView scrollviewMainFragment;
    @BindView(R.id.txt_title1)
    TextView txtTitle1;
    @BindView(R.id.layout_item1)
    LinearLayout layoutItem1;
    @BindView(R.id.txt_title2)
    TextView txtTitle2;
    @BindView(R.id.layout_item2)
    LinearLayout layoutItem2;
    @BindView(R.id.txt_title3)
    TextView txtTitle3;
    @BindView(R.id.layout_item3)
    LinearLayout layoutItem3;
    @BindView(R.id.txt_title4)
    TextView txtTitle4;
    @BindView(R.id.layout_item4)
    LinearLayout layoutItem4;
    @BindView(R.id.img_item1)
    ImageView imgItem1;
    @BindView(R.id.img_item2)
    ImageView imgItem2;
    @BindView(R.id.img_item3)
    ImageView imgItem3;
    @BindView(R.id.img_item4)
    ImageView imgItem4;
    @BindView(R.id.txt_city_mainfragment)
    TextView txtCityMainfragment;
    @BindView(R.id.txt_district_mainfragment)
    TextView txtDistrictMainfragment;


    @OnClick({R.id.img_dingwei_mainfragment,
            R.id.layout_dingwei_mianfragment,
            R.id.img_tianjia_mainfragment,
            R.id.layout_sousuo_mainfragment,
            R.id.layout_service_mainfragment,
            R.id.layout_choose_mainfragment,
            R.id.layout_combo_mainfragment,
            R.id.layout_sugestion_mainfragment,
            R.id.txt_gengduo_mainfragment,
            R.id.layout_kongtiaoqingxi_mainfragment,
            R.id.layout_bingxiangqingxi_mainfragment,
            R.id.layout_xiyijiqingxi_mainfragment,
            R.id.layout_chouyouyanji_mainfragment
    })
    public void click(View view) {
        switch (view.getId()) {
            case R.id.img_dingwei_mainfragment:
            case R.id.layout_dingwei_mianfragment:
                startActivity(new Intent(getActivity(), ChooseLocationActivity.class));
                break;
            case R.id.img_tianjia_mainfragment:
                startActivity(new Intent(getActivity(), LiJiYuYueActivity.class));

                break;
            case R.id.layout_sousuo_mainfragment:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
            case R.id.layout_service_mainfragment:
                if (onMainServiceClickListener != null) {
                    onMainServiceClickListener.onServiceClick();
                }
                break;
            case R.id.layout_choose_mainfragment:
                startActivity(new Intent(getActivity(), JingXuanPaiHangActivity.class));
                break;
            case R.id.layout_combo_mainfragment:
                if (data != null && set_meal != null && set_meal.size() > 0) {
                    Intent intent = new Intent(getActivity(), TaoCanGouMaiSucessActivity.class);
                    intent.putExtra("data", data);
                    intent.putExtra("page", 0);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(getActivity(), TaoCanActivity.class));
                }
                break;
            case R.id.layout_sugestion_mainfragment:
                break;
            case R.id.txt_gengduo_mainfragment:
                break;
            case R.id.layout_kongtiaoqingxi_mainfragment:
                break;
            case R.id.layout_bingxiangqingxi_mainfragment:
                break;
            case R.id.layout_xiyijiqingxi_mainfragment:
                break;
            case R.id.layout_chouyouyanji_mainfragment:
                break;
        }
    }

    public void setOnMainServiceClickListener(OnMainServiceClickListener onMainServiceClickListener) {
        this.onMainServiceClickListener = onMainServiceClickListener;
    }

    OnMainServiceClickListener onMainServiceClickListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    MainFragmentPresonter presonter;
    MySetMealPresonter mySetMealPresonter;

    HotNewPresonter hotNewPresonter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initLocation();
        presonter = new MainFragmentPresonter();
        presonter.setOnLoadBannerListener(this);
        vipMainFragment.setMarkerLocal(BannerViewPager.CENTER_MARKER);
        vipMainFragment.setOnBannerClick(this);
        mySetMealPresonter = new MySetMealPresonter();
        mySetMealPresonter.setMySetMealListener(this);
        hotNewPresonter = new HotNewPresonter();
        hotNewPresonter.setHotNewsListener(this);
        sign1();
        sign2();
        rigesterReceiver();

        vfMainfragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = System.currentTimeMillis() + "";
                TreeMap<String, String> map = new TreeMap<>();
                map.put("page", "1");
                map.put("timestamp", time);
                String sign = ToolUitls.getSign(map);
                hotNewPresonter.getHotNewList("1", time, sign, Contacts.KEY);

            }
        });
        getMySetMeal();

    }

    public void getMySetMeal() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Contacts.USER, Activity.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", null);

        String time = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("user_id", id);
        map.put("timestamp", time);
        String sign = ToolUitls.getSign(map);

//        ToolUitls.getCallBackStr(Contacts.TEST_BASE_URL + "MySetMeal?" + "user_id=" + id + "&timestamp=" + time + "&sign=" + sign + "&key=" + Contacts.KEY);
        mySetMealPresonter.getMySetMeal(id, time, sign, Contacts.KEY);
    }

    private void initLocation() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(MyApplication.LOCATION, Context.MODE_PRIVATE);
        String city = sharedPreferences.getString("city", "null");
        String district = sharedPreferences.getString("district", "null");


        if (!"null".equals(city))
            txtCityMainfragment.setText(city);
        if (!"null".equals(district))
            txtDistrictMainfragment.setText(district);

    }

    ArrayList<ImageView> imageViews;

    public void setViewPager(List<BannerBean.DataBean.SlidePostsBean> slide_posts) {
        imageViews = new ArrayList<>();
        for (int i = 0; i < slide_posts.size(); i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(getActivity()).load(slide_posts.get(i).getSlide_pic()).into(imageView);
            imageViews.add(imageView);
        }
//        vipMainFragment.setCanAUTO(true);

        vipMainFragment.setViews(imageViews, true);

    }


    public void setVfMainfragment(List<BannerBean.DataBean.InformationListsBean> informationListsBeen) {

        for (int i = 0; i < informationListsBeen.size(); i++) {
            LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.vf_item, null);
            TextView content = (TextView) linearLayout.findViewById(R.id.txt_vf);
            content.setText(informationListsBeen.get(i).getTitle());
            vfMainfragment.addView(linearLayout);
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        getActivity().unregisterReceiver(locationReciver);
    }

    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

    }

    @Override
    public void bannerClick(int realPosition) {

    }


    public final String TAG = "MainFragment";

    public void sign1() {

        String timestamp = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("timestamp", timestamp);
        String sign = ToolUitls.getSign(map);

        presonter.getBanner(timestamp, sign, Contacts.KEY);


    }

    int page = 1;

    public void sign2() {

        String timestamp = System.currentTimeMillis() + "";
        TreeMap<String, String> map = new TreeMap<>();
        map.put("page", page + "");
        map.put("timestamp", timestamp);
        String sign = ToolUitls.getSign(map);

        presonter.getAppIndexCategory(timestamp, Contacts.KEY, sign, 1);


    }

    List<BannerBean.DataBean.InformationListsBean> information_lists;
    List<BannerBean.DataBean.SlidePostsBean> slide_posts;

    @Override
    public void onLoadBanner(BannerBean body) {
        switch (body.getStatus()) {
            case "1":
                BannerBean.DataBean data = body.getData();
                information_lists = data.getInformation_lists();
                slide_posts = data.getSlide_posts();

                setViewPager(slide_posts);
                setVfMainfragment(information_lists);

                break;
        }
    }

    @Override
    public void onLoadAppIndexCategory(AppIndexCategoryBean bean) {

        switch (bean.getStatus()) {
            case "1":

                List<AppIndexCategoryBean.DataBean> data = bean.getData();


                if (data != null && data.size() > 0) {
                    for (int i = 0; i < data.size(); i++) {
                        switch (i) {
                            case 0:
                                txtTitle1.setText(data.get(0).getName());
                                Glide.with(getActivity()).load(data.get(0).getLogourl()).into(imgItem1);
                                setThird(data, 0, layoutItem1);
                                break;
                            case 1:
                                txtTitle2.setText(data.get(1).getName());
                                Glide.with(getActivity()).load(data.get(1).getLogourl()).into(imgItem2);
                                setThird(data, 1, layoutItem2);
                                break;
                            case 2:
                                txtTitle3.setText(data.get(2).getName());
                                Glide.with(getActivity()).load(data.get(2).getLogourl()).into(imgItem3);
                                setThird(data, 2, layoutItem3);
                                break;
                            case 3:
                                txtTitle4.setText(data.get(3).getName());
                                Glide.with(getActivity()).load(data.get(3).getLogourl()).into(imgItem3);
                                setThird(data, 3, layoutItem4);
                                break;
                        }

                    }
                }


                break;
        }

    }

    private void setThird(List<AppIndexCategoryBean.DataBean> data, int index, LinearLayout linearLayout) {
        List<AppIndexCategoryBean.ThirdBean> third = data.get(index).getThird();
        if (third != null && third.size() > 0) {

            for (int x = 0; x < third.size(); x++) {
                AppIndexCategoryBean.ThirdBean thirdBean = third.get(x);
                TextView textView = new TextView(getActivity());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(5, 0, 0, 0);
                textView.setLayoutParams(layoutParams);
                textView.setTextSize(13);
                textView.setTextColor(Color.parseColor("#181818"));
                textView.setText(thirdBean.getName());
                linearLayout.addView(textView);
            }

        }
    }

    LocationReciver locationReciver;

    public void rigesterReceiver() {
        locationReciver = new LocationReciver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Contacts.LOCATION);

        getActivity().registerReceiver(locationReciver, intentFilter);

    }

    List<MySetMealBean.DataBean.SetMealBean> set_meal;
    MySetMealBean.DataBean data;

    @Override
    public void onLoadMySetMeal(MySetMealBean bean) {
        switch (bean.getStatus()) {
            case "1":
                data = bean.getData();
                set_meal = data.getSet_meal();
                break;
        }
    }

    @Override
    public void getHotNewsSucess(HotNewsList hotNewsList) {

        switch (hotNewsList.getStatus()) {
            case "1":
                List<HotNewsList.DataBean> data = hotNewsList.getData();
                Intent intent = new Intent(getActivity(), HotNewsActivity.class);
                intent.putExtra("data", (Serializable) data);
                startActivity(intent);
                break;
        }
    }


    public class LocationReciver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            String district_name = intent.getStringExtra("district_name");
            String city = intent.getStringExtra("city");


            txtCityMainfragment.setText(city);
            txtDistrictMainfragment.setText(district_name);

        }
    }


}
