package com.example.crmsystem.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmsystem.R;
import com.example.crmsystem.salesmanager.ListStaffAdapter;
import com.example.crmsystem.salesstaff.AttendencePojo;
import com.example.crmsystem.salesstaff.SalesStaffListCreate;

import java.util.ArrayList;
import java.util.List;

public class AttendenceAdapter extends RecyclerView.Adapter<AttendenceAdapter.AttendenceViewHolder>  {

    private List<AttendencePojo> listData;
    public AttendenceAdapter(ArrayList<AttendencePojo> dbvalue) {

        this.listData = dbvalue;

    }

    @NonNull
    @Override
    public AttendenceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.attendencelistholder,parent,false);

        return new AttendenceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendenceViewHolder holder, int position) {

        AttendencePojo ld =listData.get(position);

        holder.attempid.setText(ld.getEmpid());
        holder.attdate.setText(ld.getDate());
        holder.attlat.setText(ld.getLat());
        holder.attlon.setText(ld.getLon());
    }



    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class AttendenceViewHolder extends RecyclerView.ViewHolder {

        TextView attempid,attdate,attlat,attlon;
        public AttendenceViewHolder(@NonNull View itemView) {
            super(itemView);
            attempid = itemView.findViewById(R.id.attempid);
            attdate = itemView.findViewById(R.id.attdate);
            attlat = itemView.findViewById(R.id.attlat);
            attlon = itemView.findViewById(R.id.attlon);


        }
    }
}
