package com.example.shopadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
    Button btnLogout, btncake, btnCust;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnLogout=findViewById(R.id.btnLogout);
        btncake=findViewById(R.id.btncake);
        btnCust=findViewById(R.id.btnCust);
        SharedPreference preference=new SharedPreference();
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preference.SaveBoolean(getApplicationContext(),false,SharedPreference.Key_Status);
                Intent intent= new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                HomeActivity.this.finish();
            }
        });

        btncake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), CakeActivity.class);
                startActivity(intent);
            }
        });

        btnCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), CustomerActivity.class);
                startActivity(intent);
            }
        });
    }
}