package com.suffixit.stickynote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DistrictModel {

    @SerializedName("districts")
    @Expose
    public List<District> districts = null;

    public DistrictModel() {
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    @Override
    public String toString() {
        return "DistrictModel{" +
                "districts=" + districts +
                '}';
    }
}
