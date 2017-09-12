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
 * Created by Administrator on 2017/9/5.
 */

public class CityListBean implements Serializable {


    @Override
    public String toString() {
        return "CityListBean{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    /**
     * status : 1
     * data : [{"id":"2","city_name":"西安市","district":[{"id":"4","district_name":"未央区","community":[{"id":"13","community_name":"海荣阳光城"},{"id":"14","community_name":"菁华名门"},{"id":"30","community_name":"海璟台北湾"},{"id":"31","community_name":"锦园君逸"},{"id":"32","community_name":"锦园新世纪"},{"id":"33","community_name":"世融嘉城"},{"id":"34","community_name":"家佳sport"}]},{"id":"20","district_name":"新城区","community":[]},{"id":"23","district_name":"灞桥区","community":[]},{"id":"24","district_name":"雁塔区","community":[]},{"id":"21","district_name":"碑林区","community":[]},{"id":"22","district_name":"莲湖区","community":[]},{"id":"25","district_name":"阎良区","community":[]},{"id":"26","district_name":"临潼区","community":[]},{"id":"27","district_name":"长安区","community":[]},{"id":"28","district_name":"高陵区","community":[]}]},{"id":"3","city_name":"咸阳市","district":[{"id":"6","district_name":"杨陵区","community":[]},{"id":"7","district_name":"秦都区","community":[]},{"id":"29","district_name":"渭城区","community":[]}]}]
     */


    private String status;
    private List<DataBean> data;

    public static CityListBean objectFromData(String str) {

        return new Gson().fromJson(str, CityListBean.class);
    }

    public static CityListBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), CityListBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<CityListBean> arrayCityListBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<CityListBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }

    public static List<CityListBean> arrayCityListBeanFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<CityListBean>>() {
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {

        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", city_name='" + city_name + '\'' +
                    ", district=" + district +
                    '}';
        }

        /**
         * id : 2
         * city_name : 西安市
         * district : [{"id":"4","district_name":"未央区","community":[{"id":"13","community_name":"海荣阳光城"},{"id":"14","community_name":"菁华名门"},{"id":"30","community_name":"海璟台北湾"},{"id":"31","community_name":"锦园君逸"},{"id":"32","community_name":"锦园新世纪"},{"id":"33","community_name":"世融嘉城"},{"id":"34","community_name":"家佳sport"}]},{"id":"20","district_name":"新城区","community":[]},{"id":"23","district_name":"灞桥区","community":[]},{"id":"24","district_name":"雁塔区","community":[]},{"id":"21","district_name":"碑林区","community":[]},{"id":"22","district_name":"莲湖区","community":[]},{"id":"25","district_name":"阎良区","community":[]},{"id":"26","district_name":"临潼区","community":[]},{"id":"27","district_name":"长安区","community":[]},{"id":"28","district_name":"高陵区","community":[]}]
         */

        private String id;
        private String city_name;
        private List<DistrictBean> district;

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

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public List<DistrictBean> getDistrict() {
            return district;
        }

        public void setDistrict(List<DistrictBean> district) {
            this.district = district;
        }

        public static class DistrictBean implements Serializable {

            @Override
            public String toString() {
                return "DistrictBean{" +
                        "id='" + id + '\'' +
                        ", district_name='" + district_name + '\'' +
                        ", community=" + community +
                        '}';
            }

            /**
             * id : 4
             * district_name : 未央区
             * community : [{"id":"13","community_name":"海荣阳光城"},{"id":"14","community_name":"菁华名门"},{"id":"30","community_name":"海璟台北湾"},{"id":"31","community_name":"锦园君逸"},{"id":"32","community_name":"锦园新世纪"},{"id":"33","community_name":"世融嘉城"},{"id":"34","community_name":"家佳sport"}]
             */

            private String id;
            private String district_name;
            private List<CommunityBean> community;

            public static DistrictBean objectFromData(String str) {

                return new Gson().fromJson(str, DistrictBean.class);
            }

            public static DistrictBean objectFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);

                    return new Gson().fromJson(jsonObject.getString(str), DistrictBean.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }

            public static List<DistrictBean> arrayDistrictBeanFromData(String str) {

                Type listType = new TypeToken<ArrayList<DistrictBean>>() {
                }.getType();

                return new Gson().fromJson(str, listType);
            }

            public static List<DistrictBean> arrayDistrictBeanFromData(String str, String key) {

                try {
                    JSONObject jsonObject = new JSONObject(str);
                    Type listType = new TypeToken<ArrayList<DistrictBean>>() {
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

            public String getDistrict_name() {
                return district_name;
            }

            public void setDistrict_name(String district_name) {
                this.district_name = district_name;
            }

            public List<CommunityBean> getCommunity() {
                return community;
            }

            public void setCommunity(List<CommunityBean> community) {
                this.community = community;
            }

            public static class CommunityBean implements Serializable  {

                @Override
                public String toString() {
                    return "CommunityBean{" +
                            "id='" + id + '\'' +
                            ", community_name='" + community_name + '\'' +
                            '}';
                }

                /**
                 * id : 13
                 * community_name : 海荣阳光城
                 */

                private String id;
                private String community_name;

                public static CommunityBean objectFromData(String str) {

                    return new Gson().fromJson(str, CommunityBean.class);
                }

                public static CommunityBean objectFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);

                        return new Gson().fromJson(jsonObject.getString(str), CommunityBean.class);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    return null;
                }

                public static List<CommunityBean> arrayCommunityBeanFromData(String str) {

                    Type listType = new TypeToken<ArrayList<CommunityBean>>() {
                    }.getType();

                    return new Gson().fromJson(str, listType);
                }

                public static List<CommunityBean> arrayCommunityBeanFromData(String str, String key) {

                    try {
                        JSONObject jsonObject = new JSONObject(str);
                        Type listType = new TypeToken<ArrayList<CommunityBean>>() {
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

                public String getCommunity_name() {
                    return community_name;
                }

                public void setCommunity_name(String community_name) {
                    this.community_name = community_name;
                }
            }
        }
    }
}
