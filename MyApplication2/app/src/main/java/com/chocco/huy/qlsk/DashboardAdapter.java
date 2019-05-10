package com.chocco.huy.qlsk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

/**
 * Created by ACER on 4/8/2017.
 */

public class DashboardAdapter extends BaseAdapter {
    String[] title;
    String[] hint;
    Context context;
    int[] imageId;
    Chart chart;
    LinearLayout linearLayoutChart;
    ArrayList<Entry> entries;
    ArrayList<Integer> numDataPoints;
    int count;
    LineDataSet dataSet;
    ArrayList<Integer> arrCanNang;
int solan;
    ArrayList<Entry>barEntries;
  //  RelativeLayout layout;
    public DashboardAdapter(Context context, String[] title, int[] imageId,String[] hint) {
        this.context = context;
        this.title = title;
        this.imageId = imageId;
        this.hint=hint;
    }
    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);


        SharedPreferences sharedPreferences=context.getSharedPreferences("bieudo",Context.MODE_PRIVATE);
        count=sharedPreferences.getInt("SOLUONG",0);
        View row=inflater.inflate(R.layout.layout_dashboard2,null);

        entries=new ArrayList<>();
        arrCanNang=new ArrayList<>();
        numDataPoints=new ArrayList<>();
        barEntries=new ArrayList<>();
        if(count>0) {
            row = inflater.inflate(R.layout.layout_dashboard, null);
           // layout=(RelativeLayout) row.findViewById(R.id.layout);
           // Snackbar.make(layout,"Trượt xuống để thấy nhiều chức năng hơn",Snackbar.LENGTH_LONG).show();
            chart =(Chart) row.findViewById(R.id.chart);
            chart.animateY(1000);

            arrCanNang.clear();
            numDataPoints.clear();
            entries.clear();


            ArrayList<Integer> numsCN=new ArrayList<>();
            ArrayList<Integer> numsX=new ArrayList<>();



              /*  for(int j=0;j<count;j++)
                {
                    numsCN.add(sharedPreferences.getInt("CANNANG"+j,0));
                    numsX.add(sharedPreferences.getInt("TRUCX"+j,0));

                }
                for(int arrCN:numsCN)
                    arrCanNang.add(arrCN);
                for(int arrX:numsX)
                    numDataPoints.add(arrX);
                for(int j=0;j< numDataPoints.size();j++) {
                    entries.add(new Entry(numDataPoints.get(j),arrCanNang.get(j)));
                }
                dataSet = new LineDataSet(entries, "DataSet");
                dataSet.setColor(Color.BLUE);
                chart.setData(new LineData(dataSet));

                XAxis xAxis = chart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setGridColor(Color.BLUE);
                xAxis.setDrawGridLines(true);
                Description description=new Description();
                description.setText("Theo dõi trọng lượng");
                chart.setDescription(description);
                // xAxis.setValueFormatter(new TheoDoiTrongLuong.MyAxisValueFormatter());
                //chart.animateY(1000);
                // chart.invalidate();
                */
                switch ((int)getItemId(position))
                {
                    case 1:

                        SharedPreferences sharedPreferences2=context.getSharedPreferences("buocchan",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences2.edit();


                        int b=sharedPreferences2.getInt("SOLAN",0);
                        int a=sharedPreferences2.getInt("SOBUOCCHAN",0);
                        if(b>0)
                        {
                           // barEntries.clear();
                            solan=b;
                            //editor.putInt("LAN"+solan,a);
                           // Toast.makeText(context,solan+"",Toast.LENGTH_LONG).show();

                            editor.putInt("LAN"+solan,a);

                            editor.commit();
                            for(int j=0;j<solan;j++)
                            {
                                int i=j+1;

                                int bc=sharedPreferences2.getInt("LAN"+i,0);

                                barEntries.add(new BarEntry(i,bc));
                            }
                            LineDataSet barDataSet=new LineDataSet(barEntries,"Số bước chân");
                            chart.setData(new LineData(barDataSet));
                            XAxis xAxis2 = chart.getXAxis();
                            xAxis2.setPosition(XAxis.XAxisPosition.BOTTOM);
                            xAxis2.setDrawGridLines(false);
                            Description description2=new Description();
                            description2.setText("Theo dõi bước đi");
                            chart.setDescription(description2);
                            chart.animateY(1000);
                        }
                        else
                            chart.setVisibility(View.INVISIBLE);
                        //  Toast.makeText(getApplication(),a+" "+date+" "+solan,Toast.LENGTH_LONG).show();


                        break;
                    case 0:
                        for(int j=0;j<count;j++)
                        {
                            numsCN.add(sharedPreferences.getInt("CANNANG"+j,0));
                            numsX.add(sharedPreferences.getInt("TRUCX"+j,0));

                        }
                        for(int arrCN:numsCN)
                            arrCanNang.add(arrCN);
                        for(int arrX:numsX)
                            numDataPoints.add(arrX);
                        for(int j=0;j< numDataPoints.size();j++) {
                            entries.add(new Entry(numDataPoints.get(j),arrCanNang.get(j)));
                        }
                        dataSet = new LineDataSet(entries, "DataSet");
                        dataSet.setColor(Color.BLUE);
                        chart.setData(new LineData(dataSet));

                        XAxis xAxis3 = chart.getXAxis();
                        xAxis3.setPosition(XAxis.XAxisPosition.BOTTOM);
                        xAxis3.setGridColor(Color.BLUE);
                        xAxis3.setDrawGridLines(false);
                        Description description3=new Description();
                        description3.setText("Theo dõi trọng lượng");
                        chart.setDescription(description3);
                        break;
                }


        }


        TextView txtTitle=(TextView) row.findViewById(R.id.txtTitle);
        txtTitle.setText(title[position]);
        TextView txtHint=(TextView) row.findViewById(R.id.txtHint);
        txtHint.setText(hint[position]);
        ImageView img=(ImageView)row.findViewById(R.id.img);
        img.setImageResource(imageId[position]);
        FloatingActionButton floatingActionButton=(FloatingActionButton)row.findViewById(R.id.fabAction);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch ((int)getItemId(position))
                {
                    case 0: {
                       Intent intent = new Intent(context, TheoDoiTrongLuong.class);
                       context.startActivity(intent);
                        break;
                    }
                    case 1:{
                       Intent intent = new Intent(context, Pedometer.class);
                        context.startActivity(intent);
                    break;}
                   case 2:{
                     Intent intent = new Intent(context, TaoHoSo.class);
                       context.startActivity(intent);
                   break;}


                }
            }
        });

        return row;
    }


}
