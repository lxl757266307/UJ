package com.example.maintainsteward.inter;

/**
 * Created by Administrator on 2017/8/14.
 */

public interface OnLocationItemClickListener {

    /*省 对应条目*/
    void provinceClickListener(String province);

    /*市 对应条目*/
    void cityClickListener(String city);

    /*区 对应条目*/
    void districtClickListener(String district);
}
