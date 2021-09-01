package com.example.loginregistrationmoduleusingsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameEditText, emailEditText,usernameEditText,passwordEditText;
    private Button signUpButton;
    UserDetails userDetails;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameEditText = findViewById(R.id.signUpUsernameEditTextId);
        emailEditText = findViewById(R.id.signUpEmailEditTextId);
        usernameEditText = findViewById(R.id.signUpUsernameEditTextId);
        passwordEditText = findViewById(R.id.signUpPasswordEditTextId);
        signUpButton = findViewById(R.id.signUpButtonId);
        databaseHelper = new DatabaseHelper(this);

        userDetails = new UserDetails();
        signUpButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        userDetails.setName(name);
        userDetails.setEmail(email);
        userDetails.setUsername(username);
        userDetails.setPassword(password);

        long rowId = databaseHelper.insertData(userDetails);

        if(rowId>0){
            Toast.makeText(getApplicationContext(), "REGISTRATION SUCCESSFUL", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "REGISTRATION FAILED", Toast.LENGTH_SHORT).show();
        }
    }
}