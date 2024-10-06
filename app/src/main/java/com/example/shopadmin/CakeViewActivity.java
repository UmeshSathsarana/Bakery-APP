package com.example.shopadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class CakeViewActivity extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake_view);
        RecyclerView rcvBook=findViewById(R.id.rcvCake);
        SetDB();
        Cake cake =new Cake();
        List<Cake> cakes = cake.Getcakes(db);
        CakeAdapter adapter=new CakeAdapter(db, cakes);
        RecyclerView rcvCake = null;
        rcvCake.setLayoutManager(new LinearLayoutManager(this));
        rcvCake.setAdapter(adapter);
    }
    private void SetDB()
    {
        try {
            db=openOrCreateDatabase("Cake DB",MODE_PRIVATE,null);
            db.execSQL("Create table " +
                    "if not exists "+
                    "Book(id integer primary key autoincrement " +
                    "   ,title text,description text,isbn text,price real,cover blob)");
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}