package com.example.shopadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class CustomerActivity extends AppCompatActivity {
    EditText edtName,edtphone,edtAdd,edtEmail;
    TextView txvId;
    Button btnAdd,btnUpdate,btnView;

    FirebaseDatabase fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        edtName=findViewById(R.id.edtCust);
        edtAdd=findViewById(R.id.edtAddress);
        edtphone=findViewById(R.id.edtphone);
        edtEmail=findViewById(R.id.edtEmail);
        txvId=findViewById(R.id.txvCustId);
        btnAdd=findViewById(R.id.btnAddCust);
        btnUpdate=findViewById(R.id.btnCustUpdate);
        btnView=findViewById(R.id.btnCustView);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Customer customer=new Customer();
                    customer.setName(edtName.getText().toString());
                    customer.setPhone(edtphone.getText().toString());
                    customer.setAddress(edtAdd.getText().toString());
                    customer.setEmail(edtEmail.getText().toString());
                    customer.Add(fb);
                    Toast.makeText(getApplicationContext(),
                            "Customer added",Toast.LENGTH_LONG).show();
                }
                catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(),
                            "Error fail to add customer",Toast.LENGTH_LONG).show();
                }
            }

        });
        SetDB();
    }
    private void SetDB()
    {
        try {
            fb = FirebaseDatabase.getInstance();

        }
        catch (Exception ex) {


            Toast.makeText(getApplicationContext(),
                    "Error in DB" + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}