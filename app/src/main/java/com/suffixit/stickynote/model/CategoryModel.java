package com.suffixit.stickynote.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category_table")
public class CategoryModel {

    @PrimaryKey(autoGenerate = true)
    private int categoryId;
    private String categoryTitle;
    private int icon;

    public CategoryModel(String categoryTitle, int icon) {
        this.categoryTitle = categoryTitle;
        this.icon = icon;
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
}
