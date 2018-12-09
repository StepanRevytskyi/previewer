package com.arondillqs5328.moappspreviewer.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("userNick")
    public String mName;

    @SerializedName("password")
    public String mPassword;

    public User(String name, String password) {
        mName = name;
        mPassword = password;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
}
