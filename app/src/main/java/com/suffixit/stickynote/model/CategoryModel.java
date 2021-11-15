package com.suffixit.stickynote.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category_table")
public class CategoryModel {

    @PrimaryKey(autoGenerate = true)
    private int categoryId;
    private String categoryTitle;
    private int icon;
    private int color;

    public CategoryModel(String categoryTitle, int icon, int color) {
        this.categoryTitle = categoryTitle;
        this.icon = icon;
        this.color = color;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "CategoryModel{" +
                "categoryId=" + categoryId +
                ", categoryTitle='" + categoryTitle + '\'' +
                ", icon=" + icon +
                ", color='" + color + '\'' +
                '}';
    }
}
