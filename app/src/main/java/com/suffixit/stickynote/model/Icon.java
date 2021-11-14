package com.suffixit.stickynote.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Icon {
    @PrimaryKey(autoGenerate = true)
    private int iconId;
    private int icon;

    public Icon(int icon) {
        this.icon = icon;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Icon{" +
                "iconId=" + iconId +
                ", icon=" + icon +
                '}';
    }
}
