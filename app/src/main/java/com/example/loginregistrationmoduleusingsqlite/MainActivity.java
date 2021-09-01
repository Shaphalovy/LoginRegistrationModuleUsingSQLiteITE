package com.example.loginregistrationmoduleusingsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private Button signInButton,signUpHereButton;
    private EditText usernameEditText,passwordEditText;
    private Switch aSwitch;


    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aSwitch = findViewById(R.id.switchId);

        signInButton = findViewById(R.id.signInButtonId);
        signUpHereButton = findViewById(R.id.signUpHereButtonId);

        usernameEditText = findViewById(R.id.signInusernameEditTextId);
        passwordEditText = findViewById(R.id.signInpasswordEditTextId);

        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase =  databaseHelper.getWritableDatabase();

        signInButton.setOnClickListener(this);
        signUpHereButton.setOnClickListener(this);





        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    setLanguage(MainActivity.this,"ban");
                    }else {
                    setLanguage(MainActivity.this,"en");
                }
            }
        });

    } //Oncreate end bracket

    @Override
    public void onClick(View view) {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if(view.getId()==R.id.signInButtonId){
            Boolean result = databaseHelper.findPassword(username,password);

            if (result == true){
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }else{
                Toast.makeText(getApplicationContext(), "username and Password didn't Match", Toast.LENGTH_SHORT).show();
            }
        }
        else if(view.getId()==R.id.signUpHereButtonId){
            Intent intent  = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        }
    }


    public void setLanguage(Activity activity, String language){
        Locale locale = new Locale(language);
        Resources resources = activity.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration,resources.getDisplayMetrics());
    }

}//main activity class end