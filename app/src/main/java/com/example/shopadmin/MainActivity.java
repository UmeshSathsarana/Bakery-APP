package com.example.shopadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edtEmail,edtName, edtPin;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtName=findViewById(R.id.edtName);
        edtEmail=findViewById(R.id.edtEmail);
        edtPin=findViewById(R.id.edtPin);
        btnRegister=findViewById(R.id.btnRegister);
        SharedPreference preference= new SharedPreference();
        String name= preference.GetString(getApplicationContext(),SharedPreference.Key_Name);
        boolean status=preference.GetBoolean(getApplicationContext(),SharedPreference.Key_Status);
        if (name !=null)
        {
            if (status)  //If already loged in then he will redirect to Home page
            {
                Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
                this.finish();
            }
            else //else redirected to login page
            {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                this.finish();

            }
        }
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Adding details
                preference.SaveString(getApplicationContext()
                        ,edtName.getText().toString(),SharedPreference.Key_Name);
                preference.SaveString(getApplicationContext()
                        ,edtEmail.getText().toString(),SharedPreference.Key_Email);
                preference.SaveInt(getApplicationContext()
                        ,Integer.valueOf(edtPin.getText().toString()),SharedPreference.Key_Pin);

                //After adding Redirect to login page
                Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                getParent().finish();
            }
        });
    }
}