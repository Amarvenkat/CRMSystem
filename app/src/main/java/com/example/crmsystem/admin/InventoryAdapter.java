package com.example.crmsystem.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmsystem.OrganicResults;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryViewHolder> {

    private Context context;
    private  List<OrganicResults> products;
    private int productcard;
    LayoutInflater inflater;

    public InventoryAdapter(Context applicationContext, List<OrganicResults> products, int productcard) {

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
        holder.invtitle.setText(products.get(position).getTitle());
        Picasso.with(context).load(products.get(position).getThumbnail()).fit().into(holder.invimg);
        if (products.get(position).getPricePerUnit().getAmount() != null) {
            holder.invprice.setText(products.get(position).getPricePerUnit().getAmount());
        }
        holder.invdec.setText(products.get(position).getDescription());
        holder.invquatity.setText(products.get(position).getQuantity());
        }
        catch (NullPointerException e){

        }

    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
