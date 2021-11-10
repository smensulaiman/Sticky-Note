package com.suffixit.stickynote.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.suffixit.stickynote.database.NoteDatabase;
import com.suffixit.stickynote.model.CategoryModel;

import java.util.List;

public class CategoryRepository {

    private CategoryDao categoryDao;
    private LiveData<List<CategoryModel>> allCategories;

    public CategoryRepository(Application application) {
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        categoryDao = noteDatabase.categoryDao();
        allCategories = categoryDao.getAllCategories();
    }

    public void insetCategory(CategoryModel category) {
        new InsertCategoryModelAsync(categoryDao).execute(category);
    }

    public void updateCategory(CategoryModel category) {
        new UpdateCategoryModelAsync(categoryDao).execute(category);
    }

    public void deleteCategory(CategoryModel category) {
        new DeleteCategoryModelAsync(categoryDao).execute(category);
    }

    public void deleteAllCategoryModels() {
        new DeleteAllCategoryModelsAsync(categoryDao).execute();
    }

    public LiveData<List<CategoryModel>> getAllCategories(){
        return allCategories;
    }

    public static class InsertCategoryModelAsync extends AsyncTask<CategoryModel, Void, Void> {

        CategoryDao categoryDao;

        InsertCategoryModelAsync(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(CategoryModel... categorys) {
            categoryDao.insetCategory(categorys[0]);
            return null;
        }
    }

    public static class UpdateCategoryModelAsync extends AsyncTask<CategoryModel, Void, Void> {

        CategoryDao categoryDao;

        UpdateCategoryModelAsync(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(CategoryModel... categorys) {
            categoryDao.updateCategory(categorys[0]);
            return null;
        }
    }

    public static class DeleteCategoryModelAsync extends AsyncTask<CategoryModel, Void, Void> {

        CategoryDao categoryDao;

        DeleteCategoryModelAsync(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(CategoryModel... categorys) {
            categoryDao.deleteCategory(categorys[0]);
            return null;
        }
    }

    public static class DeleteAllCategoryModelsAsync extends AsyncTask<Void, Void, Void> {

        CategoryDao categoryDao;

        DeleteAllCategoryModelsAsync(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            categoryDao.deleteAllCategories();
            return null;
        }
    }

}
