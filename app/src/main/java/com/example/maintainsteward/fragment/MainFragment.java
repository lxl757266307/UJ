package com.example.maintainsteward.fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.maintainsteward.R;
import com.example.maintainsteward.base.Contacts;
import com.example.maintainsteward.bean.BannerBean;
import com.example.maintainsteward.mvp_presonter.main_fragment.MainFragmentPresonter;
import com.example.maintainsteward.mvp_view.main_fragement.OnLoadBannerListener;
import com.example.maintainsteward.utils.ToolUitls;
import com.example.maintainsteward.view.BannerViewPager;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/15.
 */

@RequiresApi(api = Build.VERSION_CODES.M)
public class MainFragment extends Fragment implements View.OnScrollChangeListener, BannerViewPager.OnBannerClick, OnLoadBannerListener {

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
                break;
            case R.id.img_tianjia_mainfragment:
                break;
            case R.id.layout_sousuo_mainfragment:
                break;
            case R.id.layout_service_mainfragment:
                break;
            case R.id.layout_choose_mainfragment:
                break;
            case R.id.layout_combo_mainfragment:
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


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    MainFragmentPresonter presonter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presonter = new MainFragmentPresonter();
        presonter.setOnLoadBannerListener(this);
        sign();

        vfMainfragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "vfMainfragment.getDisplayedChild()" + vfMainfragment.getDisplayedChild()
                );
            }
        });


    }

    ArrayList<ImageView> imageViews;

    public void setViewPager(List<BannerBean.DataBean.SlidePostsBean> slide_posts) {
        imageViews = new ArrayList<>();
        for (int i = 0; i < slide_posts.size(); i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType. CENTER_CROP);
            Glide.with(getActivity()).load(slide_posts.get(i).getSlide_pic()).into(imageView);
            imageViews.add(imageView);
        }
        vipMainFragment.setCanAUTO(true);
        vipMainFragment.setMarkerLocal(BannerViewPager.CENTER_MARKER);
        vipMainFragment.setOnBannerClick(this);
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
    }

    @Override
    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

    }

    @Override
    public void bannerClick(int realPosition) {

    }


    public static final String TAG = "MainFragment";

    public void sign() {

        String timestamp = System.currentTimeMillis() + "";
        Log.e(TAG, "timestamp====" + timestamp);
        TreeMap<String, String> map = new TreeMap<>();
        map.put("timestamp", timestamp);
//        map.put("key", Contacts.KEY);
        String sign = ToolUitls.getSign(map);
        Log.e(TAG, "sign====" + sign);

        presonter.getBanner(timestamp, sign, Contacts.KEY);


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
                Log.e(TAG, "slide_posts===" + slide_posts.size());
                Log.e(TAG, "information_lists===" + information_lists.size());


                setViewPager(slide_posts);
                setVfMainfragment(information_lists);

                break;
        }
    }
//
//    @Override
//    public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//
//        int imageHeight = txtMainTest.getHeight();
//         /* 使用scorllview  嵌套 动态设置内容的高度 实现滑动效果 因为 内容没有充满 则无法实现 滑动*/
//
//         /*注意三种状态 1. 未滑动 2.滑动中没有超过 背景图片的 高度  3，超过背景图片高度后的状态*/
//
//        if (scrollY <= 0) {// 未滑动
//            layoutMainHeader.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
//        } else if (scrollY > 0 && scrollY <= imageHeight) {//2.滑动中没有超过 背景图片的 高度
//            float scale = (float) scrollY / imageHeight;
//            float alpha = (255 * scale);
//            // 只是layout背景透明(仿知乎滑动效果)
//            layoutMainHeader.setBackgroundColor(Color.argb((int) alpha, 227, 29, 26));
//        } else {//3，超过背景图片高度后的状态
//            layoutMainHeader.setBackgroundColor(Color.argb((int) 255, 227, 29, 26));
//        }
//    }


    /*动态设置滑动  如果内容高度 没有超过 屏幕的高度 则无法实现滑动  只需要动态设置 让内容高度 超过屏幕的高度即可*/
//    public void setContentHeight() {
//
//        int screenHeight = ScreenUtils.getScreenHeight(getActivity());
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
//        layoutParams.height = screenHeight + screenHeight / 3;
//        layoutMainContent.setLayoutParams(layoutParams);
//
//
//    }
//
//    public void addItem() {
//
//        for (int i = 0; i < 10; i++) {
//            TextView textView = new TextView(getActivity());
//            textView.setText("测试");
//            layoutContent.addView(textView);
//        }
//    }
//
//    private void setListener() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            slvMain.setOnScrollChangeListener(this);
//        }
//    }


}
