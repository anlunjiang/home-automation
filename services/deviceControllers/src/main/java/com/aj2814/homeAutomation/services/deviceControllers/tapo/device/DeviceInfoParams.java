package com.aj2814.homeAutomation.services.deviceControllers.tapo.device;

/**
 * Device infor parameters for tapo smart bulb. This is
 * the generic get state response that pertains all info re
 * the bulb
 */
public class DeviceInfoParams {
    private String device_id;
    private String fw_ver;
    private String hw_ver;
    private String type;
    private String model;
    private String mac;
    private String hw_id;
    private String fw_id;
    private String oem_id;
    private Boolean overheated;
    private String ip;
    private Integer time_diff;
    private String ssid;
    private Integer rssi;
    private Integer signal_level;
    private Integer latitude;
    private Integer longitude;
    private String lang;
    private String avatar;
    private String region;
    private String specs;
    private String nickname;
    private Boolean has_set_location_info;
    private Boolean device_on;
    private Integer brightness;
    private Integer hue;
    private Integer saturation;
    private Integer color_temp;
    private DefaultStates default_states;
    @Deprecated
    private String location;

    public DeviceInfoParams() {
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getFw_ver() {
        return fw_ver;
    }

    public String getHw_ver() {
        return hw_ver;
    }

    public String getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public String getMac() {
        return mac;
    }

    public String getHw_id() {
        return hw_id;
    }

    public String getFw_id() {
        return fw_id;
    }

    public String getOem_id() {
        return oem_id;
    }

    public Boolean getOverheated() {
        return overheated;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getTime_diff() {
        return time_diff;
    }

    public String getSsid() {
        return ssid;
    }

    public Integer getRssi() {
        return rssi;
    }

    public Integer getSignal_level() {
        return signal_level;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public String getLang() {
        return lang;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRegion() {
        return region;
    }

    public String getSpecs() {
        return specs;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Boolean getHas_set_location_info() {
        return has_set_location_info;
    }

    public Boolean getDevice_on() {
        return device_on;
    }

    public void setDevice_on(Boolean device_on) {
        this.device_on = device_on;
    }

    public Integer getBrightness() {
        return brightness;
    }

    public void setBrightness(Integer brightness) {
        this.brightness = brightness;
    }

    public Integer getHue() {
        return hue;
    }

    public void setHue(Integer hue) {
        this.hue = hue;
    }

    public Integer getSaturation() {
        return saturation;
    }

    public void setSaturation(Integer saturation) {
        this.saturation = saturation;
    }

    public Integer getColor_temp() {
        return color_temp;
    }

    public void setColor_temp(Integer color_temp) {
        this.color_temp = color_temp;
    }

    public DefaultStates getDefault_states() {
        return default_states;
    }

    public void setDefault_states(DefaultStates default_states) {
        this.default_states = default_states;
    }

    public String getLocation() {
        return location;
    }
}