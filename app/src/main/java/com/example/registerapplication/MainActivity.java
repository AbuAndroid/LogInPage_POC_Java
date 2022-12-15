package com.example.registerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView name,email,password;
    Button btn;
    Spinner spin;
    boolean isAllFieldsChecked = false;
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[@#$%^&+=])" +     // at least 1 special character
                    "(?=\\S+$)" +            // no white spaces
                    ".{4,}" +                // at least 4 characters
                    "$");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.username);
        email = findViewById(R.id.useremail);
        password = findViewById(R.id.userpassword);
        btn = findViewById(R.id.submit);
        spin = findViewById(R.id.usergender);

        String[] Gender = {"Male","Female","transgender"};

        spin.setOnItemSelectedListener(this);

        ArrayAdapter items = new ArrayAdapter(this, android.R.layout.simple_spinner_item,Gender);
        items.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(items);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                isAllFieldsChecked = CheckAllFields();

                String uname = name.getText().toString().trim();
                String uemail = email.getText().toString().trim();
                String upass = password.getText().toString().trim();
                String ugender = spin.getSelectedItem().toString();



                Toast.makeText(getApplicationContext(),uname+" "+uemail+" "+upass+" "+ugender,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                intent.putExtra("username",uname);
                intent.putExtra("useremail",uemail);
                intent.putExtra("userpassword",upass);
                intent.putExtra("usergender",ugender);

                if(validateEmail() == true && validatePassword() == true &&  valideUsername() == true){
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(),"button working",Toast.LENGTH_LONG).show();
                }


            }
        });



    }

    private boolean valideUsername() {
        if (name.length() == 0) {
            name.setError("This field is required");
            return false;
        }else{
            return true;
        }
    }

    private boolean validateEmail() {


        String emailInput = email.getText().toString().trim();
        if (emailInput.isEmpty()) {
            email.setError("Field can not be empty");
            return false;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            email.setError("Please enter a valid email address");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = password.getText().toString().trim();
        // if password field is empty
        // it will display error message "Field can not be empty"
        if (passwordInput.isEmpty()) {
            password.setError("Field can not be empty");
            return false;
        }

        // if password does not matches to the pattern
        // it will display an error message "Password is too weak"
        else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password.setError("Password is too weak");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }


//        private boolean CheckAllFields() {
//        if (username.length() == 0) {
//            username.setError("This field is required");
//            return false;
//        }
//
//        if (email.length() == 0) {
//            email.setError("This field is required");
//            return false;
//        }
//        if (password.length() == 0) {
//            password.setError("Password is required");
//            return false;
//        } else if (password.length() < 8) {
//            password.setError("Password must be minimum 8 characters");
//            return false;
//        }
//
//        return true;
//    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}