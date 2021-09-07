package com.example.crmsystem.salesstaff;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;
import com.anychart.core.cartesian.series.Line;
import com.anychart.data.Mapping;
import com.anychart.data.Set;
import com.anychart.data.View;
import com.anychart.enums.Anchor;
import com.anychart.enums.HoverMode;
import com.anychart.enums.MarkerType;
import com.anychart.enums.Position;
import com.anychart.enums.TooltipPositionMode;
import com.anychart.graphics.vector.Stroke;
import com.example.crmsystem.R;
import com.example.crmsystem.admin.LeadsGenAdapter;
import com.example.crmsystem.salesmanager.ListStaffAdapter;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static com.example.crmsystem.util.Constants.EMP_ID;
import static com.example.crmsystem.util.Constants.MYPREF;

public class SalesStaffDashboard extends AppCompatActivity {

    ArrayList<Number> total = new ArrayList<>();
    ArrayList<Number> sold = new ArrayList<>();
    ArrayList<Number> left = new ArrayList<>();
    ArrayList<Number> profit = new ArrayList<>();
    AnyChartView anyChartView;
    SharedPreferences sharedPreferences;
    RecyclerView sfdashrecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_staff_dashboard);
//         anyChartView = findViewById(R.id.any_chart_view);
//        anyChartView.setProgressBar(findViewById(R.id.progress_bar));
        sfdashrecycler = (RecyclerView) findViewById(R.id.sfdashrecycler);

//        final Cartesian cartesian = AnyChart.column();
//                loadData();

        final ArrayList<TrackLeadPojo> dbvalue = new ArrayList<>();
        final List<DataEntry> data = new ArrayList<>();
        sharedPreferences = getSharedPreferences(MYPREF, Context.MODE_PRIVATE);

        String empid = sharedPreferences.getString(EMP_ID,"");
        CollectionReference db = FirebaseFirestore.getInstance().collection(empid+"progress");

        db.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
                                @Nullable FirebaseFirestoreException e) {
                if (queryDocumentSnapshots != null) {
                    dbvalue.clear();
                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                        TrackLeadPojo dbvalueData = snapshot.toObject(TrackLeadPojo.class);

                        dbvalue.add(dbvalueData);


//
//                        for (int i=0;i<dbvalue.size();i++){
//
//                            TrackLeadPojo ld = dbvalue.get(i);
//
//
//                                int totalint =  Integer.parseInt(ld.getTotalstock());
//                                int soldint =  Integer.parseInt(ld.getStocksold());
//                                //int soldint =  Integer.parseInt(ld.getStocksold());
//                                total.add(totalint);
//                                sold.add(soldint);
//                               // left.add(ld.getStockleft());
//                                //profit.add(ld.getProfit());
//
//
//                            disbar(anyChartView, cartesian, data);
//                        }

                    }
                    sfdashrecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    sfdashrecycler.setAdapter(new LeadsGenAdapter(dbvalue));

                }


            }
        });



    }

    private void loadData() {






    }
    private class CustomDataEntry extends ValueDataEntry {

        CustomDataEntry(String x, Number value, Number value2, Number value3) {
            super(x, value);
            setValue("value2", value2);
            setValue("value3", value3);
        }

    }

    private  void disbar(AnyChartView anyChartView, Cartesian cartesian, List<DataEntry> data){

        for (int i = 1;i<total.size();i++) {
            data.add(new ValueDataEntry(total.get(i), sold.get(i)));


        }
        Column column = cartesian.column(data);

        column.tooltip()
                .titleFormat("{%X}")
                .position(Position.CENTER_BOTTOM)
                .anchor(Anchor.CENTER_BOTTOM)
                .offsetX(0d)
                .offsetY(5d)
                .format("{%Value}{groupsSeparator: }");

        cartesian.animation(true);
        cartesian.title("Active cases in India");

        cartesian.yScale().minimum(0d);

        cartesian.yAxis(0).labels().format("{%Value}{groupsSeparator: }");

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT);
        cartesian.interactivity().hoverMode(HoverMode.BY_X);

        cartesian.xAxis(0).title("States");
        cartesian.yAxis(0).title("Active Cases");

        anyChartView.setChart(cartesian);
    }
}