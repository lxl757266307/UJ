package com.example.maintainsteward.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/18.
 */

public class OrderListBean implements Serializable {


    @Override
    public String toString() {
        return "OrderListBean{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * status : 1
     * data : {"demand_order_data":[{"id":"344","order_no":"2017091810052592998","cat_id":"74","create_time":"2017-09-18 10:05:25","name":"水晶灯安装","costs":"0","order_status":"1","total_amount":"0.00","address":"空军建军节","worker_id":"0","worker_name":"","phone_number":"","cover":"http://os18w14e3.bkt.clouddn.com/595f02b2ea092.png","service_item":[{"name":"直径51-70cm","num":"1","price":"85","unit":"/个","is_add":"0"}],"goods":[{"goods_name":"","goods_price":"0.00","total_price":"0.00","goods_num":"0"}]},{"id":"343","order_no":"2017091810024274022","cat_id":"74","create_time":"2017-09-18 10:02:42","name":"水晶灯安装","costs":"0","order_status":"1","total_amount":"0.00","address":"空军建军节","worker_id":"0","worker_name":"","phone_number":"","cover":"http://os18w14e3.bkt.clouddn.com/595f02b2ea092.png","service_item":[{"name":"直径51-70cm","num":"1","price":"85","unit":"/个","is_add":"0"},{"name":"直径101-140cm","num":"1","price":"150","unit":"/个","is_add":"0"},{"name":"直径140cm以上","num":"1","price":"0","unit":"面议","is_add":"0"}],"goods":[]},{"id":"342","order_no":"2017091810022067551","cat_id":"73","create_time":"2017-09-18 10:02:20","name":"吸顶灯安装","costs":"0","order_status":"1","total_amount":"0.00","address":"空军建军节","worker_id":"0","worker_name":"","phone_number":"","cover":"http://os18w14e3.bkt.clouddn.com/595f0270d37b5.png","service_item":[{"name":"直径120cm以上","num":"1","price":"85","unit":"/个","is_add":"0"}],"goods":[]},{"id":"341","order_no":"2017091810021020912","cat_id":"74","create_time":"2017-09-18 10:02:10","name":"水晶灯安装","costs":"0","order_status":"1","total_amount":"0.00","address":"空军建军节","worker_id":"0","worker_name":"","phone_number":"","cover":"http://os18w14e3.bkt.clouddn.com/595f02b2ea092.png","service_item":[{"name":"直径51-70cm","num":"1","price":"85","unit":"/个","is_add":"0"}],"goods":[{"goods_name":"","goods_price":"0.00","total_price":"0.00","goods_num":"0"}]},{"id":"340","order_no":"2017091810013475379","cat_id":"73","create_time":"2017-09-18 10:01:34","name":"吸顶灯安装","costs":"0","order_status":"1","total_amount":"0.00","address":"空军建军节","worker_id":"0","worker_name":"","phone_number":"","cover":"http://os18w14e3.bkt.clouddn.com/595f0270d37b5.png","service_item":[{"name":"直径120cm以上","num":"1","price":"85","unit":"/个","is_add":"0"}],"goods":[]},{"id":"333","order_no":"2017091809585758334","cat_id":"74","create_time":"2017-09-18 09:58:57","name":"水晶灯安装","costs":"0","order_status":"1","total_amount":"0.00","address":"空军建军节","worker_id":"0","worker_name":"","phone_number":"","cover":"http://os18w14e3.bkt.clouddn.com/595f02b2ea092.png","service_item":[{"name":"直径51-70cm","num":"1","price":"85","unit":"/个","is_add":"0"}],"goods":[{"goods_name":"","goods_price":"0.00","total_price":"0.00","goods_num":"0"}]},{"id":"330","order_no":"2017091809572986665","cat_id":"74","create_time":"2017-09-18 09:57:29","name":"水晶灯安装","costs":"0","order_status":"1","total_amount":"0.00","address":"空军建军节","worker_id":"0","worker_name":"","phone_number":"","cover":"http://os18w14e3.bkt.clouddn.com/595f02b2ea092.png","service_item":[{"name":"直径51-70cm","num":"1","price":"85","unit":"/个","is_add":"0"}],"goods":[{"goods_name":"","goods_price":"0.00","total_price":"0.00","goods_num":"0"}]}],"totalcount":"10","totalpage":"1"}
     */

    private String status;
    private DataBean data;

    public static OrderListBean objectFromData(String str) {

        return new Gson().fromJson(str, OrderListBean.class);
    }

    public static OrderListBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), OrderListBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<OrderListBean> arrayOrderListBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<OrderListBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<OrderListBean> arrayOrderListBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<OrderListBean>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {

        @Override
        public String toString() {
            return "DataBean{" +
                    "totalcount='" + totalcount + '\'' +
                    ", totalpage='" + totalpage + '\'' +
                    ", demand_order_data=" + demand_order_data +
                    '}';
        }

        /**
         * demand_order_data : [{"id":"344","order_no":"2017091810052592998","cat_id":"74","create_time":"2017-09-18 10:05:25","name":"水晶灯安装","costs":"0","order_status":"1","total_amount":"0.00","address":"空军建军节","worker_id":"0","worker_name":"","phone_number":"","cover":"http://os18w14e3.bkt.clouddn.com/595f02b2ea092.png","service_item":[{"name":"直径51-70cm","num":"1","price":"85","unit":"/个","is_add":"0"}],"goods":[{"goods_name":"","goods_price":"0.00","total_price":"0.00","goods_num":"0"}]},{"id":"343","order_no":"2017091810024274022","cat_id":"74","create_time":"2017-09-18 10:02:42","name":"水晶灯安装","costs":"0","order_status":"1","total_amount":"0.00","address":"空军建军节","worker_id":"0","worker_name":"","phone_number":"","cover":"http://os18w14e3.bkt.clouddn.com/595f02b2ea092.png","service_item":[{"name":"直径51-70cm","num":"1","price":"85","unit":"/个","is_add":"0"},{"name":"直径101-140cm","num":"1","price":"150","unit":"/个","is_add":"0"},{"name":"直径140cm以上","num":"1","price":"0","unit":"面议","is_add":"0"}],"goods":[]},{"id":"342","order_no":"2017091810022067551","cat_id":"73","create_time":"2017-09-18 10:02:20","name":"吸顶灯安装","costs":"0","order_status":"1","total_amount":"0.00","address":"空军建军节","worker_id":"0","worker_name":"","phone_number":"","cover":"http://os18w14e3.bkt.clouddn.com/595f0270d37b5.png","service_item":[{"name":"直径120cm以上","num":"1","price":"85","unit":"/个","is_add":"0"}],"goods":[]},{"id":"341","order_no":"2017091810021020912","cat_id":"74","create_time":"2017-09-18 10:02:10","name":"水晶灯安装","costs":"0","order_status":"1","total_amount":"0.00","address":"空军建军节","worker_id":"0","worker_name":"","phone_number":"","cover":"http://os18w14e3.bkt.clouddn.com/595f02b2ea092.png","service_item":[{"name":"直径51-70cm","num":"1","price":"85","unit":"/个","is_add":"0"}],"goods":[{"goods_name":"","goods_price":"0.00","total_price":"0.00","goods_num":"0"}]},{"id":"340","order_no":"2017091810013475379","cat_id":"73","create_time":"2017-09-18 10:01:34","name":"吸顶灯安装","costs":"0","order_status":"1","total_amount":"0.00","address":"空军建军节","worker_id":"0","worker_name":"","phone_number":"","cover":"http://os18w14e3.bkt.clouddn.com/595f0270d37b5.png","service_item":[{"name":"直径120cm以上","num":"1","price":"85","unit":"/个","is_add":"0"}],"goods":[]},{"id":"333","order_no":"2017091809585758334","cat_id":"74","create_time":"2017-09-18 09:58:57","name":"水晶灯安装","costs":"0","order_status":"1","total_amount":"0.00","address":"空军建军节","worker_id":"0","worker_name":"","phone_number":"","cover":"http://os18w14e3.bkt.clouddn.com/595f02b2ea092.png","service_item":[{"name":"直径51-70cm","num":"1","price":"85","unit":"/个","is_add":"0"}],"goods":[{"goods_name":"","goods_price":"0.00","total_price":"0.00","goods_num":"0"}]},{"id":"330","order_no":"2017091809572986665","cat_id":"74","create_time":"2017-09-18 09:57:29","name":"水晶灯安装","costs":"0","order_status":"1","total_amount":"0.00","address":"空军建军节","worker_id":"0","worker_name":"","phone_number":"","cover":"http://os18w14e3.bkt.clouddn.com/595f02b2ea092.png","service_item":[{"name":"直径51-70cm","num":"1","price":"85","unit":"/个","is_add":"0"}],"goods":[{"goods_name":"","goods_price":"0.00","total_price":"0.00","goods_num":"0"}]}]
         * totalcount : 10
         * totalpage : 1
         */

        private String totalcount;
        private String totalpage;
        private List<DemandOrderDataBean> demand_order_data;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public static DataBean objectFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);

                return new Gson().fromJson(jsonObject.getString(str), DataBean.class);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        public static List<DataBean> arrayDataBeanFromData(String str) {

            Type listType = new TypeToken<ArrayList<DataBean>>() {
            }.getType();

            return new Gson().fromJson(str, listType);
        }

        public static List<DataBean> arrayDataBeanFromData(String str, String key) {

            try {
                JSONObject jsonObject = new JSONObject(str);
                Type listType = new TypeToken<ArrayList<DataBean>>() {
                }.getType();

                return new Gson().fromJson(jsonObject.getString(str), listType);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new ArrayList();


        }

        public String getTotalcount() {
            return totalcount;
        }

        public void setTotalcount(String totalcount) {
            this.totalcount = totalcount;
        }

        public String getTotalpage() {
            return totalpage;
        }

        public void setTotalpage(String totalpage) {
            this.totalpage = totalpage;
        }

        public List<DemandOrderDataBean> getDemand_order_data() {
            return demand_order_data;
        }

        public void setDemand_order_data(List<DemandOrderDataBean> demand_order_data) {
            this.demand_order_data = demand_order_data;
        }

        public static class DemandOrderDataBean implements Serializable {

            @Override
            public String toString() {
                return "DemandOrderDataBean{" +
                        "id='" + id + '\'' +
                        ", order_no='" + order_no + '\'' +
                        ", cat_id='" + cat_id + '\'' +
                        ", create_time='" + create_time + '\'' +
                        ", name='" + name + '\'' +
                        ", costs='" + costs + '\'' +
                        ", order_status='" + order_status + '\'' +
                        ", total_amount='" + total_amount + '\'' +
                        ", address='" + address + '\'' +
                        ", worker_id='" + worker_id + '\'' +
                        ", worker_name='" + worker_name + '\'' +
                        ", phone_number='" + phone_number + '\'' +
                        ", cover='" + cover + '\'' +
                        ", service_item=" + service_item +
                        ", goods=" + goods +
                        '}';
            }

            /**
             * id : 344
             * order_no : 2017091810052592998
             * cat_id : 74
             * create_time : 2017-09-18 10:05:25
             * name : 水晶灯安装
             * costs : 0
             * order_status : 1
             * total_amount : 0.00
             * address : 空军建军节
             * worker_id : 0
             * worker_name :
             * phone_number :
             * cover : http://os18w14e3.bkt.clouddn.com/595f02b2ea092.png
             * service_item : [{"name":"直径51-70cm","num":"1","price":"85","unit":"/个","is_add":"0"}]
             * goods : [{"goods_name":"","goods_price":"0.00","total_price":"0.00","goods_num":"0"}]
             */

            private String id;
            private String order_no;
            private String cat_id;
            private String create_time;
            private String name;
            private String costs;
            private String order_status;
            private String total_amount;
            private String address;
            private String worker_id;
            private String worker_name;
            private String phone_number;
            private String cover;
            private List<ServiceItemBean> service_item;
            private List<GoodsBean> goods;

            public static DemandOrderDataBean objectFromData(String str) {

                return new Gson().fromJson(str, DemandOrderDataBean.class);
            }

            public static DemandOrderDataBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), DemandOrderDataBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<DemandOrderDataBean> arrayDemandOrderDataBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<DemandOrderDataBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<DemandOrderDataBean> arrayDemandOrderDataBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<DemandOrderDataBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOrder_no() {
                return order_no;
            }

            public void setOrder_no(String order_no) {
                this.order_no = order_no;
            }

            public String getCat_id() {
                return cat_id;
            }

            public void setCat_id(String cat_id) {
                this.cat_id = cat_id;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCosts() {
                return costs;
            }

            public void setCosts(String costs) {
                this.costs = costs;
            }

            public String getOrder_status() {
                return order_status;
            }

            public void setOrder_status(String order_status) {
                this.order_status = order_status;
            }

            public String getTotal_amount() {
                return total_amount;
            }

            public void setTotal_amount(String total_amount) {
                this.total_amount = total_amount;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getWorker_id() {
                return worker_id;
            }

            public void setWorker_id(String worker_id) {
                this.worker_id = worker_id;
            }

            public String getWorker_name() {
                return worker_name;
            }

            public void setWorker_name(String worker_name) {
                this.worker_name = worker_name;
            }

            public String getPhone_number() {
                return phone_number;
            }

            public void setPhone_number(String phone_number) {
                this.phone_number = phone_number;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public List<ServiceItemBean> getService_item() {
                return service_item;
            }

            public void setService_item(List<ServiceItemBean> service_item) {
                this.service_item = service_item;
            }

            public List<GoodsBean> getGoods() {
                return goods;
            }

            public void setGoods(List<GoodsBean> goods) {
                this.goods = goods;
            }

            public static class ServiceItemBean implements Serializable {
                /**
                 * name : 直径51-70cm
                 * num : 1
                 * price : 85
                 * unit : /个
                 * is_add : 0
                 */

                private String name;
                private String num;
                private String price;
                private String unit;
                private String is_add;

                public static ServiceItemBean objectFromData(String str) {

                    return new Gson().fromJson(str, ServiceItemBean.class);
                }

                public static ServiceItemBean objectFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);

                        return new Gson().fromJson(jsonObject.getString(str), ServiceItemBean.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return null;
                }

                public static List<ServiceItemBean> arrayServiceItemBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<ServiceItemBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public static List<ServiceItemBean> arrayServiceItemBeanFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        Type listType = new TypeToken<ArrayList<ServiceItemBean>>() {
                        }.getType();

                        return new Gson().fromJson(jsonObject.getString(str), listType);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return new ArrayList();


                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getNum() {
                    return num;
                }

                public void setNum(String num) {
                    this.num = num;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getUnit() {
                    return unit;
                }

                public void setUnit(String unit) {
                    this.unit = unit;
                }

                public String getIs_add() {
                    return is_add;
                }

                public void setIs_add(String is_add) {
                    this.is_add = is_add;
                }
            }

            public static class GoodsBean implements Serializable {
                /**
                 * goods_name :
                 * goods_price : 0.00
                 * total_price : 0.00
                 * goods_num : 0
                 */

                private String goods_name;
                private String goods_price;
                private String total_price;
                private String goods_num;

                public static GoodsBean objectFromData(String str) {

                    return new Gson().fromJson(str, GoodsBean.class);
                }

                public static GoodsBean objectFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);

                        return new Gson().fromJson(jsonObject.getString(str), GoodsBean.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return null;
                }

                public static List<GoodsBean> arrayGoodsBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<GoodsBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public static List<GoodsBean> arrayGoodsBeanFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        Type listType = new TypeToken<ArrayList<GoodsBean>>() {
                        }.getType();

                        return new Gson().fromJson(jsonObject.getString(str), listType);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return new ArrayList();


                }

                public String getGoods_name() {
                    return goods_name;
                }

                public void setGoods_name(String goods_name) {
                    this.goods_name = goods_name;
                }

                public String getGoods_price() {
                    return goods_price;
                }

                public void setGoods_price(String goods_price) {
                    this.goods_price = goods_price;
                }

                public String getTotal_price() {
                    return total_price;
                }

                public void setTotal_price(String total_price) {
                    this.total_price = total_price;
                }

                public String getGoods_num() {
                    return goods_num;
                }

                public void setGoods_num(String goods_num) {
                    this.goods_num = goods_num;
                }
            }
        }
    }
}
