package com.example.crmsystem.salesstaff;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crmsystem.R;
import com.example.crmsystem.salesmanager.LeadUploadPojo;

import java.util.ArrayList;
import java.util.List;

public class SalesStaffLeadAdapter extends RecyclerView.Adapter<SalesStaffLeadAdapter.SalesStaffLeadViewHolder> {
    private List<LeadUploadPojo> listData;
    Context context;
    private static final String NAME = "NAME" ;
    private static final String ADDRESS = "ADDRESS";
    private static final String CONTACT = "CONTACT" ;
    private static final String TOTAL_NO_OF_STOCK = "TOTAL_NO_OF_STOCK";
    private static final String SOLD = "SOLD" ;
    private static final String LEFTOVER = "LEFTOVER";
    private static final String PROFIT = "PROFIT";
    public SalesStaffLeadAdapter(ArrayList<LeadUploadPojo> dbvalue, Context applicationContext) {

        this.listData= dbvalue;
        this.context = applicationContext;
    }

    @NonNull
    @Override
    public SalesStaffLeadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.salesstaffleadgrid,parent,false);

        return new SalesStaffLeadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesStaffLeadViewHolder holder, int position) {

        LeadUploadPojo ld =listData.get(position);
        int leadpos = position +1;
        holder.leadnogrid.setText("Lead "+leadpos );
        holder.leadnamegrid.setText(ld.getLeadname());
        holder.leadaddressgrid.setText(ld.getLeadaddress());
        holder.leadcontactgrid.setText(ld.getLeadcontact());
        holder.leadcardgrid.setTag(holder);
        holder.leadcardgrid.setOnClickListener(clickListner);
    }

    View.OnClickListener clickListner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            SalesStaffLeadViewHolder viewHolder = (SalesStaffLeadViewHolder) view.getTag();
            int position = viewHolder.getPosition();
            LeadUploadPojo ld =listData.get(position);
            Toast.makeText(context,
                    "Successfully uploaded "+ld.getEnddate(), Toast.LENGTH_SHORT).show();


            Intent intent = new Intent(context, TrackLead.class);
            Bundle bundle = new Bundle();
            bundle.putString(NAME, ld.getLeadname());
            bundle.putString(ADDRESS,ld.getLeadaddress());
            bundle.putString(CONTACT, ld.getLeadcontact());
            intent.putExtras(bundle);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(intent);

        }
    };

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class SalesStaffLeadViewHolder extends RecyclerView.ViewHolder {
        TextView leadnogrid,leadnamegrid,leadaddressgrid,leadcontactgrid;
        CardView leadcardgrid;
        public SalesStaffLeadViewHolder(@NonNull View itemView) {
            super(itemView);

            leadcardgrid = (CardView) itemView.findViewById(R.id.leadcardgrid);
            leadnogrid = (TextView) itemView.findViewById(R.id.leadnogrid);
            leadnamegrid = (TextView) itemView.findViewById(R.id.leadnamegrid);
            leadaddressgrid = (TextView) itemView.findViewById(R.id.leadaddressgrid);
            leadcontactgrid = (TextView) itemView.findViewById(R.id.leadcontactgrid);




        }
    }
}
