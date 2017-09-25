package com.example.maintainsteward.bean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/22.
 */

public class OrderInfoBean {


    @Override
    public String toString() {
        return "OrderInfoBean{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * status : 1
     * data : {"id":"355","order_no":"2017092308275562940","user_id":"363","worker_id":"0","order_status":"1","user_name":"app约谈","user_phone":"15353738776","province":"0","city":"西安市","district":"未央区","community":"0","address":"空军建军节","cat_id":"73","name":"吸顶灯安装","costs":"0","content":[""],"details":"","images":["http://os18w14e3.bkt.clouddn.com/20170923082754276.jpg"],"create_time":"2017-09-23 08:27:55","order_time":"2017-09-23 16:33:00","arrival_time":"0000-00-00 00:00:00","finish_time":"0000-00-00 00:00:00","total_amount":"0.00","serve_costs":"0.00","material_costs":"29.00","dangerous_work":"0.00","other_costs":"0.00","safe_costs":"0.00","set_meal_costs":"0.00","final_price":"0.00","user_note":"","admin_note":"","bonus_id":"0","bonus_price":"0.00","pay_type":"1","trade_no":"","is_comment":"0","pay_time":"0","deleted":"0","pay_status":"1","service":[{"id":"149","name":"直径40cm以内","count":"1","price":"30","unit":"/个"}],"timelong":"","send_status":"0","wxqrcode":"","is_self":"0","discount":"1","order_add":[],"cover":"http://os18w14e3.bkt.clouddn.com/595f0270d37b5.png","fee":"0","youhui_fee":"0","worker_info":{"phone_number":"","name":""},"goods_info":[{"goods_id":"18","goods_name":"LED圆贴片/白光/10W","goods_price":"29.00","total_price":"29.00","goods_num":"1","add_time":"2017-09-23 08:27:55"}]}
     */



    private String status;
    private DataBean data;

    public static OrderInfoBean objectFromData(String str) {

        return new Gson().fromJson(str, OrderInfoBean.class);
    }

    public static OrderInfoBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), OrderInfoBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<OrderInfoBean> arrayOrderInfoBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<OrderInfoBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<OrderInfoBean> arrayOrderInfoBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<OrderInfoBean>>() {
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

    public static class DataBean {

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", order_no='" + order_no + '\'' +
                    ", user_id='" + user_id + '\'' +
                    ", worker_id='" + worker_id + '\'' +
                    ", order_status='" + order_status + '\'' +
                    ", user_name='" + user_name + '\'' +
                    ", user_phone='" + user_phone + '\'' +
                    ", province='" + province + '\'' +
                    ", city='" + city + '\'' +
                    ", district='" + district + '\'' +
                    ", community='" + community + '\'' +
                    ", address='" + address + '\'' +
                    ", cat_id='" + cat_id + '\'' +
                    ", name='" + name + '\'' +
                    ", costs='" + costs + '\'' +
                    ", details='" + details + '\'' +
                    ", create_time='" + create_time + '\'' +
                    ", order_time='" + order_time + '\'' +
                    ", arrival_time='" + arrival_time + '\'' +
                    ", finish_time='" + finish_time + '\'' +
                    ", total_amount='" + total_amount + '\'' +
                    ", serve_costs='" + serve_costs + '\'' +
                    ", material_costs='" + material_costs + '\'' +
                    ", dangerous_work='" + dangerous_work + '\'' +
                    ", other_costs='" + other_costs + '\'' +
                    ", safe_costs='" + safe_costs + '\'' +
                    ", set_meal_costs='" + set_meal_costs + '\'' +
                    ", final_price='" + final_price + '\'' +
                    ", user_note='" + user_note + '\'' +
                    ", admin_note='" + admin_note + '\'' +
                    ", bonus_id='" + bonus_id + '\'' +
                    ", bonus_price='" + bonus_price + '\'' +
                    ", pay_type='" + pay_type + '\'' +
                    ", trade_no='" + trade_no + '\'' +
                    ", is_comment='" + is_comment + '\'' +
                    ", pay_time='" + pay_time + '\'' +
                    ", deleted='" + deleted + '\'' +
                    ", pay_status='" + pay_status + '\'' +
                    ", timelong='" + timelong + '\'' +
                    ", send_status='" + send_status + '\'' +
                    ", wxqrcode='" + wxqrcode + '\'' +
                    ", is_self='" + is_self + '\'' +
                    ", discount='" + discount + '\'' +
                    ", cover='" + cover + '\'' +
                    ", fee='" + fee + '\'' +
                    ", youhui_fee='" + youhui_fee + '\'' +
                    ", worker_info=" + worker_info +
                    ", content=" + content +
                    ", images=" + images +
                    ", service=" + service +
                    ", order_add=" + order_add +
                    ", goods_info=" + goods_info +
                    '}';
        }

        /**
         * id : 355
         * order_no : 2017092308275562940
         * user_id : 363
         * worker_id : 0
         * order_status : 1
         * user_name : app约谈
         * user_phone : 15353738776
         * province : 0
         * city : 西安市
         * district : 未央区
         * community : 0
         * address : 空军建军节
         * cat_id : 73
         * name : 吸顶灯安装
         * costs : 0
         * content : [""]
         * details :
         * images : ["http://os18w14e3.bkt.clouddn.com/20170923082754276.jpg"]
         * create_time : 2017-09-23 08:27:55
         * order_time : 2017-09-23 16:33:00
         * arrival_time : 0000-00-00 00:00:00
         * finish_time : 0000-00-00 00:00:00
         * total_amount : 0.00
         * serve_costs : 0.00
         * material_costs : 29.00
         * dangerous_work : 0.00
         * other_costs : 0.00
         * safe_costs : 0.00
         * set_meal_costs : 0.00
         * final_price : 0.00
         * user_note :
         * admin_note :
         * bonus_id : 0
         * bonus_price : 0.00
         * pay_type : 1
         * trade_no :
         * is_comment : 0
         * pay_time : 0
         * deleted : 0
         * pay_status : 1
         * service : [{"id":"149","name":"直径40cm以内","count":"1","price":"30","unit":"/个"}]
         * timelong :
         * send_status : 0
         * wxqrcode :
         * is_self : 0
         * discount : 1
         * order_add : []
         * cover : http://os18w14e3.bkt.clouddn.com/595f0270d37b5.png
         * fee : 0
         * youhui_fee : 0
         * worker_info : {"phone_number":"","name":""}
         * goods_info : [{"goods_id":"18","goods_name":"LED圆贴片/白光/10W","goods_price":"29.00","total_price":"29.00","goods_num":"1","add_time":"2017-09-23 08:27:55"}]
         */

        private String id;
        private String order_no;
        private String user_id;
        private String worker_id;
        private String order_status;
        private String user_name;
        private String user_phone;
        private String province;
        private String city;
        private String district;
        private String community;
        private String address;
        private String cat_id;
        private String name;
        private String costs;
        private String details;
        private String create_time;
        private String order_time;
        private String arrival_time;
        private String finish_time;
        private String total_amount;
        private String serve_costs;
        private String material_costs;
        private String dangerous_work;
        private String other_costs;
        private String safe_costs;
        private String set_meal_costs;
        private String final_price;
        private String user_note;
        private String admin_note;
        private String bonus_id;
        private String bonus_price;
        private String pay_type;
        private String trade_no;
        private String is_comment;
        private String pay_time;
        private String deleted;
        private String pay_status;
        private String timelong;
        private String send_status;
        private String wxqrcode;
        private String is_self;
        private String discount;
        private String cover;
        private String fee;
        private String youhui_fee;
        private WorkerInfoBean worker_info;
        private List<String> content;
        private List<String> images;
        private List<ServiceBean> service;
        private List<?> order_add;
        private List<GoodsInfoBean> goods_info;

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

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getWorker_id() {
            return worker_id;
        }

        public void setWorker_id(String worker_id) {
            this.worker_id = worker_id;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_phone() {
            return user_phone;
        }

        public void setUser_phone(String user_phone) {
            this.user_phone = user_phone;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getCommunity() {
            return community;
        }

        public void setCommunity(String community) {
            this.community = community;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
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

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getOrder_time() {
            return order_time;
        }

        public void setOrder_time(String order_time) {
            this.order_time = order_time;
        }

        public String getArrival_time() {
            return arrival_time;
        }

        public void setArrival_time(String arrival_time) {
            this.arrival_time = arrival_time;
        }

        public String getFinish_time() {
            return finish_time;
        }

        public void setFinish_time(String finish_time) {
            this.finish_time = finish_time;
        }

        public String getTotal_amount() {
            return total_amount;
        }

        public void setTotal_amount(String total_amount) {
            this.total_amount = total_amount;
        }

        public String getServe_costs() {
            return serve_costs;
        }

        public void setServe_costs(String serve_costs) {
            this.serve_costs = serve_costs;
        }

        public String getMaterial_costs() {
            return material_costs;
        }

        public void setMaterial_costs(String material_costs) {
            this.material_costs = material_costs;
        }

        public String getDangerous_work() {
            return dangerous_work;
        }

        public void setDangerous_work(String dangerous_work) {
            this.dangerous_work = dangerous_work;
        }

        public String getOther_costs() {
            return other_costs;
        }

        public void setOther_costs(String other_costs) {
            this.other_costs = other_costs;
        }

        public String getSafe_costs() {
            return safe_costs;
        }

        public void setSafe_costs(String safe_costs) {
            this.safe_costs = safe_costs;
        }

        public String getSet_meal_costs() {
            return set_meal_costs;
        }

        public void setSet_meal_costs(String set_meal_costs) {
            this.set_meal_costs = set_meal_costs;
        }

        public String getFinal_price() {
            return final_price;
        }

        public void setFinal_price(String final_price) {
            this.final_price = final_price;
        }

        public String getUser_note() {
            return user_note;
        }

        public void setUser_note(String user_note) {
            this.user_note = user_note;
        }

        public String getAdmin_note() {
            return admin_note;
        }

        public void setAdmin_note(String admin_note) {
            this.admin_note = admin_note;
        }

        public String getBonus_id() {
            return bonus_id;
        }

        public void setBonus_id(String bonus_id) {
            this.bonus_id = bonus_id;
        }

        public String getBonus_price() {
            return bonus_price;
        }

        public void setBonus_price(String bonus_price) {
            this.bonus_price = bonus_price;
        }

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public String getTrade_no() {
            return trade_no;
        }

        public void setTrade_no(String trade_no) {
            this.trade_no = trade_no;
        }

        public String getIs_comment() {
            return is_comment;
        }

        public void setIs_comment(String is_comment) {
            this.is_comment = is_comment;
        }

        public String getPay_time() {
            return pay_time;
        }

        public void setPay_time(String pay_time) {
            this.pay_time = pay_time;
        }

        public String getDeleted() {
            return deleted;
        }

        public void setDeleted(String deleted) {
            this.deleted = deleted;
        }

        public String getPay_status() {
            return pay_status;
        }

        public void setPay_status(String pay_status) {
            this.pay_status = pay_status;
        }

        public String getTimelong() {
            return timelong;
        }

        public void setTimelong(String timelong) {
            this.timelong = timelong;
        }

        public String getSend_status() {
            return send_status;
        }

        public void setSend_status(String send_status) {
            this.send_status = send_status;
        }

        public String getWxqrcode() {
            return wxqrcode;
        }

        public void setWxqrcode(String wxqrcode) {
            this.wxqrcode = wxqrcode;
        }

        public String getIs_self() {
            return is_self;
        }

        public void setIs_self(String is_self) {
            this.is_self = is_self;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public String getYouhui_fee() {
            return youhui_fee;
        }

        public void setYouhui_fee(String youhui_fee) {
            this.youhui_fee = youhui_fee;
        }

        public WorkerInfoBean getWorker_info() {
            return worker_info;
        }

        public void setWorker_info(WorkerInfoBean worker_info) {
            this.worker_info = worker_info;
        }

        public List<String> getContent() {
            return content;
        }

        public void setContent(List<String> content) {
            this.content = content;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public List<ServiceBean> getService() {
            return service;
        }

        public void setService(List<ServiceBean> service) {
            this.service = service;
        }

        public List<?> getOrder_add() {
            return order_add;
        }

        public void setOrder_add(List<?> order_add) {
            this.order_add = order_add;
        }

        public List<GoodsInfoBean> getGoods_info() {
            return goods_info;
        }

        public void setGoods_info(List<GoodsInfoBean> goods_info) {
            this.goods_info = goods_info;
        }

        public static class WorkerInfoBean {
            @Override
            public String toString() {
                return "WorkerInfoBean{" +
                        "phone_number='" + phone_number + '\'' +
                        ", name='" + name + '\'' +
                        '}';
            }

            /**
             * phone_number :
             * name :
             */

            private String phone_number;
            private String name;

            public static WorkerInfoBean objectFromData(String str) {

                return new Gson().fromJson(str, WorkerInfoBean.class);
            }

            public static WorkerInfoBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), WorkerInfoBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<WorkerInfoBean> arrayWorkerInfoBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<WorkerInfoBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<WorkerInfoBean> arrayWorkerInfoBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<WorkerInfoBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public String getPhone_number() {
                return phone_number;
            }

            public void setPhone_number(String phone_number) {
                this.phone_number = phone_number;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class ServiceBean {
            /**
             * id : 149
             * name : 直径40cm以内
             * count : 1
             * price : 30
             * unit : /个
             */

            private String id;
            private String name;
            private String count;
            private String price;
            private String unit;

            public static ServiceBean objectFromData(String str) {

                return new Gson().fromJson(str, ServiceBean.class);
            }

            public static ServiceBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), ServiceBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<ServiceBean> arrayServiceBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<ServiceBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<ServiceBean> arrayServiceBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<ServiceBean>>() {
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
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
        }

        public static class GoodsInfoBean {
            /**
             * goods_id : 18
             * goods_name : LED圆贴片/白光/10W
             * goods_price : 29.00
             * total_price : 29.00
             * goods_num : 1
             * add_time : 2017-09-23 08:27:55
             */

            private String goods_id;
            private String goods_name;
            private String goods_price;
            private String total_price;
            private String goods_num;
            private String add_time;

            public static GoodsInfoBean objectFromData(String str) {

                return new Gson().fromJson(str, GoodsInfoBean.class);
            }

            public static GoodsInfoBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), GoodsInfoBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<GoodsInfoBean> arrayGoodsInfoBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<GoodsInfoBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<GoodsInfoBean> arrayGoodsInfoBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<GoodsInfoBean>>() {
                    }.getType();

                    return new Gson().fromJson(jsonObject.getString(str), listType);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return new ArrayList();


            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
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

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }
        }
    }
}
