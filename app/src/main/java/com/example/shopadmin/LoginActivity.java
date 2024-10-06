package com.example.shopadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    TextView txvName;

    EditText edtLoginPin;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin=findViewById(R.id.btnLogin);
        edtLoginPin=findViewById(R.id.edtLoginPin);
        txvName=findViewById(R.id.txvName);
        SharedPreference preference=new SharedPreference();
        String name=preference.GetString(getApplicationContext(),SharedPreference.Key_Name);
        txvName.setText(name);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int Pin = preference.GetInt(getApplicationContext(), SharedPreference.Key_Pin);
                if (Integer.valueOf(edtLoginPin.getText().toString()) == Pin) {
                    preference.SaveBoolean(getApplicationContext(), true, SharedPreference.Key_Status);
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                } else
                    Toast.makeText(getApplicationContext(), "Incorrect Pin", Toast.LENGTH_LONG).show();
            }
        });
    }
}