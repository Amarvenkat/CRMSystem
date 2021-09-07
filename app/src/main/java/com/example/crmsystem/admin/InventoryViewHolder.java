package com.example.crmsystem.admin;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmsystem.R;

public class InventoryViewHolder extends RecyclerView.ViewHolder {
    ImageView invimg;
    TextView invtitle,invprice,invdec,invquatity;
    public InventoryViewHolder(@NonNull View itemView) {
        super(itemView);

        invimg = (ImageView) itemView.findViewById(R.id.invimg);
        invtitle = (TextView) itemView.findViewById(R.id.invtitle);
        invprice = (TextView) itemView.findViewById(R.id.invprice);
        invdec = (TextView) itemView.findViewById(R.id.invdec);
        invquatity = (TextView) itemView.findViewById(R.id.invquatity);



    }
}
