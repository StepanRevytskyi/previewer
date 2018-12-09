package com.arondillqs5328.moappspreviewer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("applicationToken")
    @Expose
    private String applicationToken;
    @SerializedName("isPayment")
    @Expose
    private Boolean isPayment;
    @SerializedName("applicationStatus")
    @Expose
    private Boolean applicationStatus;
    @SerializedName("applicationName")
    @Expose
    private String applicationName;
    @SerializedName("endOfPayment")
    @Expose
    private Object endOfPayment;
    @SerializedName("applicationIcoUrl")
    @Expose
    private String applicationIcoUrl;
    @SerializedName("applicationUrl")
    @Expose
    private String applicationUrl;

    public Datum(String applicationToken, Boolean isPayment, Boolean applicationStatus, String applicationName, Object endOfPayment, String applicationIcoUrl, String applicationUrl) {
        this.applicationToken = applicationToken;
        this.isPayment = isPayment;
        this.applicationStatus = applicationStatus;
        this.applicationName = applicationName;
        this.endOfPayment = endOfPayment;
        this.applicationIcoUrl = applicationIcoUrl;
        this.applicationUrl = applicationUrl;
    }

    public String getApplicationToken() {
        return applicationToken;
    }

    public void setApplicationToken(String applicationToken) {
        this.applicationToken = applicationToken;
    }

    public Boolean getPayment() {
        return isPayment;
    }

    public void setPayment(Boolean payment) {
        isPayment = payment;
    }

    public Boolean getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(Boolean applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public Object getEndOfPayment() {
        return endOfPayment;
    }

    public void setEndOfPayment(Object endOfPayment) {
        this.endOfPayment = endOfPayment;
    }

    public String getApplicationIcoUrl() {
        return applicationIcoUrl;
    }

    public void setApplicationIcoUrl(String applicationIcoUrl) {
        this.applicationIcoUrl = applicationIcoUrl;
    }

    public String getApplicationUrl() {
        return applicationUrl;
    }

    public void setApplicationUrl(String applicationUrl) {
        this.applicationUrl = applicationUrl;
    }
}
