package com.arondillqs5328.moappspreviewer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApplicationListPesponse {
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public ApplicationListPesponse() {

    }

    public ApplicationListPesponse(List<Datum> data) {
        this.data = data;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }
}
