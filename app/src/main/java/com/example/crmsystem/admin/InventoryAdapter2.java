package com.example.crmsystem.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmsystem.OrganicResults;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class InventoryAdapter2 extends RecyclerView.Adapter<InventoryViewHolder> {

    private Context context;
    private List<ProductsPojo> products;
    private int productcard;
    LayoutInflater inflater;

    public InventoryAdapter2(Context applicationContext, List<ProductsPojo> products, int productcard) {

        this.context = applicationContext;
        this.products = products;
        this.productcard = productcard;
        inflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(productcard,parent,false);
        InventoryViewHolder inventoryViewHolder = new InventoryViewHolder(view);
        return inventoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryViewHolder holder, int position) {

        try{
            holder.invtitle.setText(products.get(position).getStringproductname());
            Picasso.with(context).load(products.get(position).getUrl()).fit().into(holder.invimg);

            holder.invprice.setText(products.get(position).getStringprice());

            holder.invdec.setText(products.get(position).getStringproductdescription());
            holder.invquatity.setText(products.get(position).getStringstockavailability());
        }
        catch (NullPointerException e){

        }

    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
