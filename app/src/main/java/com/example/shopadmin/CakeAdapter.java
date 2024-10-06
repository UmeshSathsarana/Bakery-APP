package com.example.shopadmin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CakeAdapter extends RecyclerView.Adapter<CakeAdapter.ViewHolder> {
    SQLiteDatabase db;
    List<Cake> cakeList;

    public CakeAdapter(SQLiteDatabase _db, List<Cake> cakes)
    {
        db=_db;
        cakeList = cakes;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View items= inflater.inflate(R.layout.cake_item,parent,false);
        ViewHolder holder=new ViewHolder(items);

        return holder;
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { //Binding to the book holder
        Cake cake = cakeList.get(position);
        holder.txvId.setText(String.valueOf(cake.getId()));
        holder.txvTitle.setText(cake.getTitle());
        holder.txvDes.setText(cake.getDescription());
        holder.txvID.setText(cake.getId ());
        holder.txvPrice.setText(String.valueOf(cake.getPrice()));

        Bitmap bitmap= BitmapFactory.decodeByteArray(cake.getCover(),0, cake.getCover().length);
        holder.imvCover.setImageBitmap(bitmap);

        holder.ibtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("id", cake.getId());
                intent.putExtra("title", cake.getTitle());
                intent.putExtra("price", cake.getPrice());
                intent.putExtra("description", cake.getDescription());
                intent.putExtra("ID", cake.getId());
                intent.putExtra("cover", cake.getCover());
                ((Activity)view.getContext()).setResult(Activity.RESULT_OK,intent);
                ((Activity)view.getContext()).finish();

            }
        });
        holder.ibtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(holder.ibtnDelete.getContext());
                builder.setMessage("Are you sure,ou want to delete?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cake.Delete(db);
                        cakeList.remove(cake);
                        notifyItemRemoved(position);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog= builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() { //Error to Error to Error
        return cakeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txvID,txvDes,txvPrice,txvTitle,txvId;
        ImageView imvCover;
        ImageButton ibtnEdit,ibtnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txvID=itemView.findViewById(R.id.txvID);
            txvId=itemView.findViewById(R.id.txvID);
            txvDes=itemView.findViewById(R.id.txvDes);
            txvTitle=itemView.findViewById(R.id.txvTitle);
            txvPrice=itemView.findViewById(R.id.txvPrice);
            ibtnDelete=itemView.findViewById(R.id.ibtnDelete);
            ibtnEdit=itemView.findViewById(R.id.ibtnEdit);
            imvCover=itemView.findViewById(R.id.imvVCover);
        }
    }
}
