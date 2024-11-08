package com.assignment.freshly.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.assignment.freshly.Activities.LandingPage.LandingPage;
import com.assignment.freshly.R;
import com.assignment.freshly.asynTask.Customer.GetCustomer;

public class CustomerLogin extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);

        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (!email.isEmpty() && !password.isEmpty()) {
                new GetCustomer(this, new GetCustomer.OnLoginResultListener() {
                    @Override
                    public void onLoginSuccess(int customerId) {
                        SharedPreferences sharedPreferences = getSharedPreferences("Current_User_details", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("Customer_Id", customerId);
                        editor.apply();
                        Intent intent = new Intent(CustomerLogin.this, LandingPage.class);
                        startActivity(intent);
                        finish();
                    }
                    @Override
                    public void onLoginFailed() {
                        Toast.makeText(CustomerLogin.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                    }
                }).execute(email, password);
            } else {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void goToCustomerSignup(){
        Intent intent = new Intent(this, CustomerSignUp.class);
        startActivity(intent);
    }
}