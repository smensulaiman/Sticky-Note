package com.acjl.foodmenu.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.acjl.foodmenu.model.CategoryModel;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    void insetCategory(CategoryModel note);

    @Update
    void updateCategory(CategoryModel note);

    @Delete
    void deleteCategory(CategoryModel note);

    @Query("DELETE FROM category_table")
    void deleteAllCategories();

    @Query("SELECT * FROM category_table where categoryId = :id")
    CategoryModel getCategoryById(int id);

    @Query("SELECT * FROM category_table ORDER BY categoryTitle ASC")
    LiveData<List<CategoryModel>> getAllCategories();

}
