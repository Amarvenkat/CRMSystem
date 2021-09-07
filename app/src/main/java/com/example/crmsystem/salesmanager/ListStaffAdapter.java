package com.example.crmsystem.salesmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmsystem.R;
import com.example.crmsystem.salesstaff.SalesStaffList;
import com.example.crmsystem.salesstaff.SalesStaffListCreate;

import java.util.ArrayList;
import java.util.List;

public class ListStaffAdapter extends RecyclerView.Adapter<ListStaffAdapter.ListStaffViewHolder>  {

    private List<SalesStaffListCreate> listData;

    public  ListStaffAdapter(ArrayList<SalesStaffListCreate> dbvalue) {

        this.listData= dbvalue;

    }

    @NonNull
    @Override
    public ListStaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.stafflistholder,parent,false);

        return new ListStaffViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListStaffViewHolder holder, int position) {

        SalesStaffListCreate ld =listData.get(position);
        holder.liststaffname.setText(ld.getName());
        holder.liststaffphone.setText(ld.getPhoneno());
        holder.liststaffgender.setText(ld.getGender());
        holder.liststaffaddress.setText(ld.getAddress());
        holder.liststaffemail.setText(ld.getMail());
        holder.liststaffemployeeid.setText(ld.getEmpid());

        holder.liststaffstateandcity.setText(ld.getCity());
        holder.liststaffproductcategory.setText(ld.getProcate());
        holder.liststaffshift.setText(ld.getShift());



    }


    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ListStaffViewHolder extends RecyclerView.ViewHolder {

        public TextView liststaffname,liststaffphone,liststaffgender,liststaffaddress,liststaffemail,liststaffemployeeid,liststaffstateandcity,liststaffproductcategory,liststaffshift;

        public ListStaffViewHolder(@NonNull View itemView) {
            super(itemView);

            liststaffname = (TextView)itemView.findViewById(R.id.liststaffname);
            liststaffphone = (TextView)itemView.findViewById(R.id.liststaffphone);
            liststaffgender = (TextView)itemView.findViewById(R.id.liststaffgender);
            liststaffaddress = (TextView)itemView.findViewById(R.id.liststaffaddress);
            liststaffemail = (TextView)itemView.findViewById(R.id.liststaffemail);
            liststaffemployeeid = (TextView)itemView.findViewById(R.id.liststaffemployeeid);

            liststaffstateandcity = (TextView)itemView.findViewById(R.id.liststaffstateandcity);
            liststaffproductcategory = (TextView)itemView.findViewById(R.id.liststaffproductcategory);
            liststaffshift = (TextView)itemView.findViewById(R.id.liststaffshift);


        }
    }
}
