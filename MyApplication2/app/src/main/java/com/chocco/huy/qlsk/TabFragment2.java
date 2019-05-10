package com.chocco.huy.qlsk;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by ACER on 4/7/2017.
 */

public class TabFragment2 extends Fragment{
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.tab_fragment_2,container,false);
        ImageButton imgBMI=(ImageButton)view.findViewById(R.id.imgBMI);
        ImageButton imgNangLuong=(ImageButton)view.findViewById(R.id.imgNangLuong);
        ImageButton imgTheTichMau=(ImageButton)view.findViewById(R.id.imgTheTichMau);

        ImageButton imgThuoc=(ImageButton)view.findViewById(R.id.imgThuoc);
        ImageButton imgNhuCauNuoc=(ImageButton)view.findViewById(R.id.imgNhuCauNuoc);

        ImageButton imgNuocTrongCT=(ImageButton)view.findViewById(R.id.imgNuocTrongCT);


        imgBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(),BMI.class);
                startActivity(intent);
            }
        });
        imgNangLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),NangLuong.class);
                startActivity(intent);
            }
        });
        imgTheTichMau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),TheTichMau.class);
                startActivity(intent);
            }
        });
        imgThuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),ChiPhiThuoc.class);
                startActivity(intent);
            }
        });
        imgNhuCauNuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),NhuCauNuoc.class);
                startActivity(intent);
            }
        });
        imgNuocTrongCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),NuocTrongCoThe.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
