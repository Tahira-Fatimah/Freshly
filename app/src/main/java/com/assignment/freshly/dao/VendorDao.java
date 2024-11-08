package com.assignment.freshly.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.assignment.freshly.entity.Vendor;

import java.util.List;

@Dao
public interface VendorDao {
    @Insert
    public Long insertVendor(Vendor vendor);

    @Update
    public void updateVendor(Vendor vendor);

    @Delete
    public void deleteVendor(Vendor vendor);

    @Query("SELECT * FROM vendor WHERE username = :username AND password = :password LIMIT 1")
    LiveData<Vendor> authenticateVendor(String username, String password);

    @Query("SELECT * FROM vendor WHERE v_id = :vendorId")
    LiveData<Vendor> getVendorById(int vendorId);

    @Query("SELECT * FROM vendor")
    LiveData<List<Vendor>> getAllVendors();

}
