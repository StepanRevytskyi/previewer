package com.arondillqs5328.moappspreviewer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserApp {
    @SerializedName("skip")
    @Expose
    private Integer skip;
    @SerializedName("take")
    @Expose
    private Integer take;
    @SerializedName("osType")
    @Expose
    private Integer osType;
    @SerializedName("userToken")
    @Expose
    private String userToken;

    public UserApp(Integer skip, Integer take, Integer osType, String userToken) {
        this.skip = skip;
        this.take = take;
        this.osType = osType;
        this.userToken = userToken;
    }

    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public Integer getTake() {
        return take;
    }

    public void setTake(Integer take) {
        this.take = take;
    }

    public Integer getOsType() {
        return osType;
    }

    public void setOsType(Integer osType) {
        this.osType = osType;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}