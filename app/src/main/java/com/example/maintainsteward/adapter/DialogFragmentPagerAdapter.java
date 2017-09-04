package com.example.maintainsteward.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.maintainsteward.utils.ToolUitls;

import java.util.List;

/**
 * Created by Administrator on 2017/8/11.
 */

public class DialogFragmentPagerAdapter extends FragmentPagerAdapter {

    public static final String TAG = "DialogFragmentPagerAdapter";

    List<Fragment> list;
    String[] array;

    public DialogFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;

        ToolUitls.print(TAG, "size==" + list.size());
    }





    public List<Fragment> getList() {
        return list;
    }

    public void setList(List<Fragment> list) {
        this.list = list;
        ToolUitls.print(TAG, "list.size=" + list.size());
    }

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {

        return array == null ? "" : array[position];
    }

}
