package com.assignment.freshly.Activities.Vendor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.assignment.freshly.Activities.Customer.CustomerLogin;
import com.assignment.freshly.Activities.LandingPage.LandingPage;
import com.assignment.freshly.AsyncTask.Vendor.GetVendor;
import com.assignment.freshly.R;

public class VendorLogin extends AppCompatActivity {

    EditText loginUsername, loginPassword;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_login);
        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        loginButton.findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = loginUsername.getText().toString();
                String password = loginPassword.getText().toString();

                if(!username.isEmpty() && !password.isEmpty()){
                    new GetVendor(getApplicationContext(), new GetVendor.GetCustomerListener() {
                        @Override
                        public void GetCustomerListenerSuccess(int vendorId) {
                            SharedPreferences sharedPreferences = getSharedPreferences("Vendor_Details", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("Vendor Id", vendorId);
                            editor.apply();

                            Intent intent = new Intent(VendorLogin.this, LandingPage.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void GetCustomerListenerFailure() {
                            Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                        }
                    }).execute(username, password);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Provide Credentials to Login", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}