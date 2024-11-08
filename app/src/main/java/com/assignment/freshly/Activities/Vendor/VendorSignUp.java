package com.assignment.freshly.Activities.Vendor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.assignment.freshly.Activities.Customer.CustomerLogin;
import com.assignment.freshly.Activities.Customer.CustomerSignUp;
import com.assignment.freshly.AsyncTask.Vendor.InsertVendor;
import com.assignment.freshly.Entity.Vendor;
import com.assignment.freshly.R;

public class VendorSignUp extends AppCompatActivity {
    EditText usernameTextView, passwordTextView, phoneTextView, addressTextView;
    Button signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_sign_up);
        usernameTextView = findViewById(R.id.signup_username);
        passwordTextView = findViewById(R.id.signup_password);
        phoneTextView = findViewById(R.id.signup_phone);
        addressTextView = findViewById(R.id.signup_address);
        signUpBtn = findViewById(R.id.signup_button);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameTextView.getText().toString();
                String password = passwordTextView.getText().toString();
                String phone = phoneTextView.getText().toString();
                String address = addressTextView.getText().toString();

                if(isUsernameValid(username) && isPasswordValid(password) && isValidPhoneNumber(phone) && isAddressValid(address)){
                    new InsertVendor(getApplicationContext(), new InsertVendor.InsertVendorListener() {
                        @Override
                        public void insertListenerSuccess() {
                            Intent intent = new Intent(VendorSignUp.this, VendorLogin.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void insertListenerFailure() {
                            Toast.makeText(getApplicationContext(), "Username Already Exists", Toast.LENGTH_SHORT).show();
                        }
                    }).execute(new Vendor(username, password, phone, address));
                }
                else {
                    Toast.makeText(getApplicationContext(), "Provide all neccessary details", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isUsernameValid(String username) {
        return username!= null;
    }

    private boolean isPasswordValid(String password) {
        String passwordRegex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}";
        return password.matches(passwordRegex);
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^03\\d{2}-\\d{7}$";
        return phoneNumber.matches(regex);
    }


    private boolean isAddressValid(String address){
        return address!=null && address.length()>10;
    }

    public void goToVendorLogin(){
        Intent intent = new Intent(VendorSignUp.this, VendorLogin.class);
        startActivity(intent);
        finish();
    }
}