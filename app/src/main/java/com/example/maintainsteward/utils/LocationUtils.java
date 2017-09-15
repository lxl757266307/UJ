package com.example.maintainsteward.utils;

import com.example.maintainsteward.bean.CityListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/11.
 */

public class LocationUtils {

 /*  北京市（京）
    天津市（津）
    上海市（沪）
    重庆市（渝）
    河北省（冀）
    河南省（豫）
    云南省（云）
    辽宁省（辽）
    黑龙江省（黑）
    湖南省（湘）
    安徽省（皖）
    山东省（鲁）
    新疆维吾尔（新）
    江苏省（苏）
    浙江省（浙）
    江西省（赣）
    湖北省（鄂）
    广西壮族（桂）
    甘肃省（甘）
    山西省（晋）
    内蒙古（蒙）
    陕西省（陕）
    吉林省（吉）
    福建省（闽）
    贵州省（贵）
    广东省（粤）
    青海省（青）
    西藏（藏）
    四川省（川）
    宁夏回族（宁）
    海南省（琼）
    台湾省（台）
    香港特别行政区
    澳门特别行政区*/

    public static List<CityListBean.DataBean> data;

    public static List<CityListBean.DataBean> getPrivince() {
        return data;
    }

    public static void setData(List<CityListBean.DataBean> data) {
        LocationUtils.data = data;
    }
}
