package com.example.shopadmin;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class CakeActivity extends AppCompatActivity {
    TextView txvid;
    EditText edtISBN, edtTitle, edtPrice,edtDescription;
    Button btnAdd, btnUpdate, btnView;
    ImageButton ibtnCover;
    ImageView imvCover;
    SQLiteDatabase db;
    Bitmap pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake);
        txvid=findViewById(R.id.txvcakeid);
        edtISBN = findViewById(R.id.edtID);
        edtTitle = findViewById(R.id.edtTitle);
        edtPrice = findViewById(R.id.edtPrice);
        edtDescription = findViewById(R.id.edtDescription);
        btnAdd = findViewById(R.id.btnAdd);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnView = findViewById(R.id.btnView);
        imvCover = findViewById(R.id.imvCover);
        ibtnCover = findViewById(R.id.ibtnCover);
        SetDB();
        ActivityResultLauncher launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        Intent intent = o.getData();
                        pic = (Bitmap) intent.getExtras().get("data");
                        imvCover.setImageBitmap(pic);
                    }
                });

        ActivityResultLauncher launcher2 = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        Intent intent = o.getData();
                        Uri uri = intent.getData();
                        imvCover.setImageURI(uri);
                    }

                });
        ibtnCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ibtnCover.getContext());
                builder.setMessage("Please select the option you with to use");
                builder.setTitle("Select a book image");
                builder.setPositiveButton("Use the camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        launcher.launch(intent);
                    }
                });

                builder.setNegativeButton("Use the gallery",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                intent.setType("image/");
                                launcher2.launch(intent);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Cake cake = new Cake();
                    cake.setTitle(edtTitle.getText().toString());
                    cake.setISBN(edtISBN.getText().toString());
                    cake.setDescription(edtDescription.getText().toString());
                    cake.setPrice(Double.valueOf(edtPrice.getText().toString()));
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    pic.compress(Bitmap.CompressFormat.JPEG, 80, stream);
                    byte[] cover = stream.toByteArray();
                    cake.setCover(cover);

                    cake.Save(db);
                    Toast.makeText(getApplicationContext(),
                            "Cake added", Toast.LENGTH_LONG).show();
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        ActivityResultLauncher<Intent> launcherBookView = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if (o.getResultCode() == RESULT_OK) {
                            Intent intent = o.getData();
                            edtTitle.setText(intent.getStringExtra("title"));
                            edtPrice.setText(
                                    String.valueOf(intent.getDoubleExtra("price",-1)));
                            edtISBN.setText(intent.getStringExtra("isbn"));
                            edtDescription.setText(intent.getStringExtra("description"));
                            byte[] img = intent.getByteArrayExtra("cover");
                            Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
                            imvCover.setImageBitmap(bitmap);
                            txvid.setText(
                                    String.valueOf(intent.getIntExtra("id",-1)));
                        }
                    }
                });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CakeViewActivity.class);
                startActivity(intent);
            }

        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Cake cake = new Cake();
                    cake.setId(Integer.valueOf(txvid.getText().toString()));
                    cake.setTitle(edtTitle.getText().toString());
                    cake.setISBN(edtISBN.getText().toString());
                    cake.setDescription(edtDescription.getText().toString());
                    cake.setPrice(Double.valueOf(edtPrice.getText().toString()));
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    pic.compress(Bitmap.CompressFormat.JPEG, 80, stream);
                    byte[] cover = stream.toByteArray();
                    cake.setCover(cover);
                    cake.update(db);
                    Toast.makeText(getApplicationContext(),
                            "Cake update",Toast.LENGTH_LONG).show();

                    cake.Save(db);
                    Toast.makeText(getApplicationContext(),
                            "Cake added", Toast.LENGTH_LONG).show();
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void SetDB()
    {
        try {
            db=openOrCreateDatabase("BookDB",MODE_PRIVATE,null);
            db.execSQL("Create table " +
                    "if not exists "+
                    "Cake(id integer primary key autoincrement " +
                    "   ,title text,description text,isbn text,price real,cover blob)");
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}