package com.fourbtech.stickynote.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.fourbtech.stickynote.model.CategoryModel;
import com.fourbtech.stickynote.repository.CategoryRepository;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    private CategoryRepository categoryRepository;
    private LiveData<List<CategoryModel>> allCategoryModels;

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        categoryRepository = new CategoryRepository(application);
        allCategoryModels = categoryRepository.getAllCategories();
    }

    public void insetCategoryModel(CategoryModel categoryModel) {
        categoryRepository.insetCategory(categoryModel);
    }

    public void updateCategoryModel(CategoryModel categoryModel) {
        categoryRepository.updateCategory(categoryModel);
    }

    public void deleteCategoryModel(CategoryModel categoryModel) {
        categoryRepository.deleteCategory(categoryModel);
    }

    public void deleteAllCategoryModels() {
        categoryRepository.deleteAllCategoryModels();
    }

    public CategoryModel getCategoryById(int id){
        return categoryRepository.getCategoryById(id);
    }

    public LiveData<List<CategoryModel>> getAllCategories(){
        return categoryRepository.getAllCategories();
    }

}
