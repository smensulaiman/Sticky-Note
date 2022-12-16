package com.acjl.foodmenu.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ColorModel {
    @PrimaryKey(autoGenerate = true)
    private int colorId;
    private int color;

    public ColorModel(int color) {
        this.color = color;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
