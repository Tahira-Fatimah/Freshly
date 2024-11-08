package com.assignment.freshly.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.assignment.freshly.R;
import com.assignment.freshly.asynTask.Customer.InsertCustomer;
import com.assignment.freshly.entity.Customer;

public class CustomerSignUp extends AppCompatActivity {

    EditText emailTextView, passwordTextView;
    Button signupButton;
    RadioGroup genderRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_sign_up);
        emailTextView = findViewById(R.id.signup_email);
        passwordTextView = findViewById(R.id.signup_password);
        signupButton = findViewById(R.id.signup_button);
        genderRadioGroup = findViewById(R.id.gender_radio_group);

        signupButton.setOnClickListener(v -> {
            String email = emailTextView.getText().toString().trim();
            String password = passwordTextView.getText().toString().trim();
            int selectedGenderId = genderRadioGroup.getCheckedRadioButtonId();

            if (isEmailValid(email) && isPasswordValid(password) && isGenderValid(selectedGenderId)) {
                RadioButton selectedGenderButton = findViewById(selectedGenderId);
                String gender = selectedGenderButton.getText().toString();
                System.out.println("Gender " + gender);
                Customer customerToAdd = new Customer(email, password, gender);
                new InsertCustomer(getApplicationContext(), new InsertCustomer.CustomerSignupListener() {
                    @Override
                    public void onSignUpSuccess() {
                        Intent intent = new Intent(CustomerSignUp.this, CustomerLogin.class);
                        startActivity(intent);
                        finish();
                    }
                    @Override
                    public void onSignUpFailure() {
                        Toast.makeText(getApplicationContext(), "Email Already Exists", Toast.LENGTH_SHORT).show();
                    }
                }).execute(customerToAdd);

            } else {
                Toast.makeText(this, "Provide all necessary details", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void goToCustomerLogin(View view) {
        Intent intent = new Intent(this, CustomerLogin.class);
        startActivity(intent);
    }


    private boolean isEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    private boolean isPasswordValid(String password) {
        String passwordRegex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}";
        return password.matches(passwordRegex);
    }

    private boolean isGenderValid(int selectedGenderId) {
        return selectedGenderId != -1;
    }
}
