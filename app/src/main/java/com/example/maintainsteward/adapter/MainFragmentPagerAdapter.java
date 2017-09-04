package com.example.maintainsteward.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/8/17.
 */

public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> list;


    public MainFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }
}
