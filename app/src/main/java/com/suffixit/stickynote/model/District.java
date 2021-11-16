package com.suffixit.stickynote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class District {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("division_id")
    @Expose
    public String divisionId;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("bn_name")
    @Expose
    public String bnName;
    @SerializedName("lat")
    @Expose
    public String lat;
    @SerializedName("long")
    @Expose
    public String _long;

    public District() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBnName() {
        return bnName;
    }

    public void setBnName(String bnName) {
        this.bnName = bnName;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String get_long() {
        return _long;
    }

    public void set_long(String _long) {
        this._long = _long;
    }

    @Override
    public String toString() {
        return "District{" +
                "id='" + id + '\'' +
                ", divisionId='" + divisionId + '\'' +
                ", name='" + name + '\'' +
                ", bnName='" + bnName + '\'' +
                ", lat='" + lat + '\'' +
                ", _long='" + _long + '\'' +
                '}';
    }
}
