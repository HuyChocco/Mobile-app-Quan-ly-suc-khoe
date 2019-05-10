package com.chocco.huy.qlsk;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TheoDoiTrongLuong extends AppCompatActivity {
    LineChart chart;
    Button btnTietKiem;
    TextView txtCanNang;
    ArrayList<Entry> entries;
    ArrayList<Integer> numDataPoints;
    int count;
    LineDataSet dataSet;


    ArrayList<Integer> arrCanNang;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theo_doi_trong_luong);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        assert actionBar!=null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        chart = (LineChart) findViewById(R.id.chart);
        btnTietKiem=(Button)findViewById(R.id.btnTietKiem);
        txtCanNang=(TextView)findViewById(R.id.txtCanNang);
        entries=new ArrayList<>();
        arrCanNang=new ArrayList<>();
        numDataPoints=new ArrayList<>();
        i=0;
        btnTietKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text;
                if((text=(txtCanNang.getText().toString())).length()>0)
                {
                    chart.setVisibility(View.VISIBLE);
                    entries.clear();
                if(count>0)
                    numDataPoints.add(count++);
                else {

                    numDataPoints.add(i++);
                }
                arrCanNang.add(Integer.parseInt(txtCanNang.getText().toString()));
                for(int j=0;j< numDataPoints.size();j++) {
                    entries.add(new Entry(numDataPoints.get(j),arrCanNang.get(j)));
                }
                dataSet = new LineDataSet(entries, "DataSet");
                dataSet.setColor(Color.BLUE);
                chart.setData(new LineData(dataSet));
                XAxis xAxis = chart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setDrawGridLines(false);
                Description description=new Description();
                description.setText("Theo dõi trọng lượng");
                chart.setDescription(description);
                xAxis.setValueFormatter(new MyAxisValueFormatter());
                //chart.animateY(1000);
                chart.invalidate();
                }
                //entries.clear();
            }
        });
        //ArrayList<String> xAXES = new ArrayList<>();
       // ArrayList<Entry> yAXESsin = new ArrayList<>();
        //ArrayList<Entry> yAXEScos = new ArrayList<>();

       // double x = 0 ;
        //int numDataPoints = 3;
       // for(int i=0;i<numDataPoints;i++){
           // float sinFunction = Float.parseFloat(String.valueOf(Math.sin(x)));
           // float cosFunction = Float.parseFloat(String.valueOf(Math.cos(x)));
           // x = x + 0.1;
           // yAXESsin.add(new Entry(sinFunction,i));
           // yAXEScos.add(new Entry(cosFunction,i));
           // xAXES.add(i, String.valueOf(x));
          //  entries.add(new Entry(i,60+i*2));
       // }
        // add entries to dataset

        //dataSet.setValueTextColor(...);
       /* String[] xaxes = new String[xAXES.size()];
        for(int i=0; i<xAXES.size();i++){
            xaxes[i] = xAXES.get(i).toString();
        }

        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

        LineDataSet lineDataSet1 = new LineDataSet(yAXEScos,"cos");
        lineDataSet1.setDrawCircles(false);
        lineDataSet1.setColor(Color.BLUE);

        LineDataSet lineDataSet2 = new LineDataSet(yAXESsin,"sin");
        lineDataSet2.setDrawCircles(false);
        lineDataSet2.setColor(Color.RED);

        lineDataSets.add(lineDataSet1);
        lineDataSets.add(lineDataSet2);*/


        //chart.invalidate();

        //chart.setVisibleXRangeMaximum(65f);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bmi, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_info) {

        }
        if(id==android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
    public class MyAxisValueFormatter implements IAxisValueFormatter {

        private DecimalFormat mFormat;

        public MyAxisValueFormatter() {

            // format values to 1 decimal digit
            mFormat = new DecimalFormat("###,###,##0.0");
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            // "value" represents the position of the label on the axis (x or y)
            return mFormat.format(value);
        }

        /** this is only needed if numbers are returned, else return 0 */

    }
    @Override
    protected void onResume() {
        super.onResume();
        arrCanNang.clear();
        numDataPoints.clear();
        entries.clear();

        SharedPreferences sharedPreferences=getSharedPreferences("bieudo",MODE_PRIVATE);
        ArrayList<Integer> numsCN=new ArrayList<>();
        ArrayList<Integer> numsX=new ArrayList<>();
        count=sharedPreferences.getInt("SOLUONG",0);
        if(count>0)
        {

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
            XAxis xAxis = chart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setDrawGridLines(false);
            Description description=new Description();
            description.setText("Theo dõi trọng lượng");
            chart.setDescription(description);
            xAxis.setValueFormatter(new MyAxisValueFormatter());
            //chart.animateY(1000);
           // chart.invalidate();
        }

        else
            chart.setVisibility(View.INVISIBLE);

        chart.animateY(1000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences=getSharedPreferences("bieudo",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        int count1=0;
        for (int cn:arrCanNang) {

            editor.putInt("CANNANG"+count1,cn);
            count1++;
        }
        int count2=0;
        for(int i:numDataPoints) {

            editor.putInt("TRUCX"+count2, i);
            count2++;
        }
        editor.putInt("SOLUONG",arrCanNang.size());
        editor.commit();
    }


}
