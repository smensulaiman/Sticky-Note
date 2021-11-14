package com.suffixit.stickynote.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ColorModel {
    @PrimaryKey(autoGenerate = true)
    private int colorId;
    private String color;

    public ColorModel(String color) {
        this.color = color;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
