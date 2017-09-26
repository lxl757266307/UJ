package com.example.maintainsteward.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/9/26.
 */

public class TaoCanFragmentAdapter extends FragmentPagerAdapter {

    List<Fragment> fragments;

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    public TaoCanFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }
}
