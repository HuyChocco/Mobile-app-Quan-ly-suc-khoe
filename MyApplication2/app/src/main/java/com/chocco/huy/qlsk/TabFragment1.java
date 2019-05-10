package com.chocco.huy.qlsk;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.Chart;

/**
 * Created by ACER on 4/10/2017.
 */

public class TabFragment1 extends Fragment {
    ListView list;
    String ten;

    int[] icons={R.drawable.weighttracker,R.drawable.pedometer};
    String[] texts={"Theo dõi trọng lượng","Theo dõi chạy bộ"};
    String[] hints={"bấm vào đây để thêm trọng lượng","nhấn vào đây để theo dõi các buổi đi bộ của bạn"};
    DashboardAdapter dashboardAdapter;
    ListView listView;

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("hoso", Context.MODE_PRIVATE);

        ten=sharedPreferences.getString("TEN","");


    }

    @Override
    public void onResume() {
        super.onResume();
        Snackbar.make(linearLayout,"Trượt xuống để thấy nhiều chức năng hơn",Snackbar.LENGTH_LONG).show();
    }

    LinearLayout linearLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.tab_fragment_1b,container,false);

         linearLayout=(LinearLayout)view.findViewById(R.id.linear_dashboard);

        listView =(ListView)view.findViewById(R.id.list);
        dashboardAdapter=new DashboardAdapter(getActivity(),texts,icons,hints);
        listView.setAdapter(dashboardAdapter);


        return view;
    }
}
