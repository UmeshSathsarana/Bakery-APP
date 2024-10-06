package com.example.shopadmin;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Cake {
    private String ISBN;
    private int Id;
    private String Title;
    private String Description;
    private double Price;
    private byte[] Cover;

    public void setISBN(String isbn) {
//        if (ISBN.length() >= 13)
            this.ISBN = isbn;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setPrice(double price) {
        if (Price >= 0)
            Price = price;
    }
    public void setCover(byte[] cover) {
        Cover = cover;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public double getPrice() {
        return Price;
    }

    public byte[] getCover() {
        return Cover;
    }

    public void Save(SQLiteDatabase db) {  //Method to save the data
        try {
            ContentValues values = new ContentValues();
            values.put("title", Title);
            values.put("description", Description);
            values.put("price", Price);
            values.put("isbn", ISBN);
            values.put("cover", Cover);
            db.insert("book", null, values);

        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<Cake> Getcakes(SQLiteDatabase db) {
        try {
            List<Cake> cakes = new ArrayList<Cake>();
            String query = "select id,title,isbn,description,price,cover from book ";
            Cursor cursor=db.rawQuery(query,null);
            if (cursor.moveToFirst())
            {
                do {
                    Cake cake = new Cake();
                    cake.setId(cursor.getInt(0));
                    cake.setTitle(cursor.getString(1));
                    cake.setISBN(cursor.getString(2));
                    cake.setDescription(cursor.getString(3));
                    cake.setPrice(cursor.getDouble(4));
                    cake.setCover(cursor.getBlob(5));
                    cakes.add(cake);
                }while (cursor.moveToNext());
            }
            return cakes;

        }
        catch (Exception ex)
        {
            throw ex;
        }
    }

    public void update(SQLiteDatabase db) {  //Method to save the data
        try {
            ContentValues values = new ContentValues();
            values.put("title", Title);
            values.put("description", Description);
            values.put("price", Price);
            values.put("ID", Id);
            values.put("cover", Cover);
            db.update("cake",values,"id= "+Id,null);

        } catch (Exception ex) {
            throw ex;
        }
    }

    public void Delete(SQLiteDatabase db) {  //Method to save the data
        try {
            db.delete("book","id= "+Id,null);

        } catch (Exception ex) {
            throw ex;
        }
    }





}
