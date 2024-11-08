package com.assignment.freshly.dao;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Dao;

import com.assignment.freshly.entity.Category;

import java.util.List;

@Dao
public interface CategoryDao {
    @Insert
    public Long insertCategory(Category category);

    @Query("SELECT * FROM category")
    LiveData<List<Category>> getAllCategories();

    @Query("SELECT * FROM category WHERE ca_id = :categoryId")
    LiveData<Category> getCategoryById(int categoryId);

    @Delete
    void deleteCategory(Category category);

    @Update
    void updateCategory(Category category);
}
