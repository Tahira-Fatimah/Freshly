package com.assignment.freshly;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.assignment.freshly.Activities.CustomerSignUp;
import com.assignment.freshly.asynTask.Category.InsertCategory;
import com.assignment.freshly.asynTask.Product.InsertProduct;
import com.assignment.freshly.asynTask.Vendor.InsertVendor;
import com.assignment.freshly.entity.Category;
import com.assignment.freshly.entity.Product;
import com.assignment.freshly.entity.Vendor;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new InsertCategory(this).execute(new Category("vegetable"));
        new InsertVendor(this).execute(new Vendor("Vendor 1", "password", "ImagePath", "03068557774", "Address"));
        new InsertProduct(this).execute(new Product("Cabbage", "not yum", 1, 1));
        new InsertProduct(this).execute(new Product("Tomato", "not yum", 1, 1));
        new InsertProduct(this).execute(new Product("Gobi", "not yum", 1, 1));

        Intent intent = new Intent(MainActivity.this, CustomerSignUp.class);
        startActivity(intent);

        finish();

    }
}