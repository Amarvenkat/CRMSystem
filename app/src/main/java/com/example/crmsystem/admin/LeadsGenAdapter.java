package com.example.crmsystem.admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmsystem.R;
import com.example.crmsystem.salesmanager.ListStaffAdapter;
import com.example.crmsystem.salesstaff.SalesStaffListCreate;
import com.example.crmsystem.salesstaff.TrackLeadPojo;

import java.util.ArrayList;

public class LeadsGenAdapter extends RecyclerView.Adapter<LeadsGenAdapter.LeadGenViewHolder>{

    ArrayList<TrackLeadPojo> dbvalue ;
    public LeadsGenAdapter(ArrayList<TrackLeadPojo> dbvalue) {
        this.dbvalue = dbvalue;
    }

    @NonNull
    @Override
    public LeadsGenAdapter.LeadGenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.leadgenview,parent,false);

        return new LeadGenViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeadsGenAdapter.LeadGenViewHolder holder, int position) {
        TrackLeadPojo ld =dbvalue.get(position);

        holder.leadgenname.setText(ld.getLeadname());
        holder.leadgencontact.setText(ld.getLeadcontact());
        holder.leadgenaddress.setText(ld.getLeadaddress());
        holder.leadgenprofit.setText(ld.getProfit());
        holder.leadgenleft.setText(ld.getStockleft());
        holder.leadgensold.setText(ld.getStocksold());
        holder.leadgenstocks.setText(ld.getTotalstock());
    }

    @Override
    public int getItemCount() {
        return dbvalue.size();
    }

    public class LeadGenViewHolder extends  RecyclerView.ViewHolder {
        TextView leadgenname,leadgencontact,leadgenaddress,leadgenprofit,leadgensold,leadgenleft,leadgenstocks;
        public LeadGenViewHolder(@NonNull View itemView) {
            super(itemView);
            leadgenname = (TextView) itemView.findViewById(R.id.leadgenname);
            leadgencontact = (TextView) itemView.findViewById(R.id.leadgencontact);
            leadgenaddress = (TextView) itemView.findViewById(R.id.leadgenaddress);
            leadgenprofit = (TextView) itemView.findViewById(R.id.leadgenprofit);
            leadgensold = (TextView) itemView.findViewById(R.id.leadgensold);
            leadgenleft = (TextView) itemView.findViewById(R.id.leadgenleft);
            leadgenstocks = (TextView) itemView.findViewById(R.id.leadgenstocks);


        }
    }
}
