package com.suffixit.stickynote.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.suffixit.stickynote.model.CategoryModel;
import com.suffixit.stickynote.model.Note;
import com.suffixit.stickynote.repository.CategoryRepository;
import com.suffixit.stickynote.repository.NoteRepository;

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

    public LiveData<List<CategoryModel>> getAllCategories(){
        return categoryRepository.getAllCategories();
    }

}
