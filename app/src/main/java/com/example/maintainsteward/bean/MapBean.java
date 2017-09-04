package com.example.maintainsteward.bean;

/**
 * Created by Administrator on 2017/8/4.
 */

public class MapBean {

    int locationType;
    double latitude;
    double longitude;
    float accuracy;
    String address;
    String country;
    String province;
    String city;
    String district;
    String street;
    String streetNum;
    String cityCode;
    String adCode;
    String aoiName;
    String buildingId;
    String floor;
    int gpsAccuracyStatus;
    String format;

    public MapBean(int locationType, double latitude, double longitude, float accuracy, String address, String country, String province, String city, String district, String street, String streetNum, String cityCode, String adCode, String aoiName, String buildingId, String floor, int gpsAccuracyStatus, String format) {
        this.locationType = locationType;
        this.latitude = latitude;
        this.longitude = longitude;
        this.accuracy = accuracy;
        this.address = address;
        this.country = country;
        this.province = province;
        this.city = city;
        this.district = district;
        this.street = street;
        this.streetNum = streetNum;
        this.cityCode = cityCode;
        this.adCode = adCode;
        this.aoiName = aoiName;
        this.buildingId = buildingId;
        this.floor = floor;
        this.gpsAccuracyStatus = gpsAccuracyStatus;
        this.format = format;
    }

    @Override
    public String toString() {
        return "MapBean{" +
                "locationType=" + locationType +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", accuracy=" + accuracy +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", streetNum='" + streetNum + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", adCode='" + adCode + '\'' +
                ", aoiName='" + aoiName + '\'' +
                ", buildingId='" + buildingId + '\'' +
                ", floor='" + floor + '\'' +
                ", gpsAccuracyStatus=" + gpsAccuracyStatus +
                ", format='" + format + '\'' +
                '}';
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getLocationType() {
        return locationType;
    }

    public void setLocationType(int locationType) {
        this.locationType = locationType;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(String streetNum) {
        this.streetNum = streetNum;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getAoiName() {
        return aoiName;
    }

    public void setAoiName(String aoiName) {
        this.aoiName = aoiName;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public int getGpsAccuracyStatus() {
        return gpsAccuracyStatus;
    }

    public void setGpsAccuracyStatus(int gpsAccuracyStatus) {
        this.gpsAccuracyStatus = gpsAccuracyStatus;
    }
}
