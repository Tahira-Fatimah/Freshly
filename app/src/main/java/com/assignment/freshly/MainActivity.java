package com.assignment.freshly;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.assignment.freshly.Activities.Customer.CustomerSignUp;
import com.assignment.freshly.Activities.Vendor.VendorSignUp;
import com.assignment.freshly.AsyncTask.Category.InsertCategory;
import com.assignment.freshly.AsyncTask.Product.InsertProduct;
import com.assignment.freshly.AsyncTask.Vendor.InsertVendor;
import com.assignment.freshly.Entity.Category;
import com.assignment.freshly.Entity.Product;
import com.assignment.freshly.Entity.Vendor;

public class MainActivity extends AppCompatActivity {

    Button asVendor, asCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        new InsertCategory(this).execute(new Category("vegetable"));
//        new InsertVendor(this).execute(new Vendor("Vendor 1", "password", "ImagePath", "03068557774", "Address"));
//        new InsertProduct(this).execute(new Product("Cabbage", "not yum", 1, 1));
//        new InsertProduct(this).execute(new Product("Tomato", "not yum", 1, 1));
//        new InsertProduct(this).execute(new Product("Gobi", "not yum", 1, 1));

        asVendor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomerSignUp.class);
                startActivity(intent);
                finish();
            }
        });

        asCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VendorSignUp.class);
                startActivity(intent);
                finish();
            }
        });


    }
}