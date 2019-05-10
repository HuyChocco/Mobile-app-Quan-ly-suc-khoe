package com.chocco.huy.qlsk;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ACER on 4/27/2017.
 */

public class ChiPhiThuocResultDialogFragment extends DialogFragment {
    private float sothuochangngay,sothuocmotgoi,chiphimotgoi;


    public ChiPhiThuocResultDialogFragment(float sothuochangngay, float sothuocmotgoi, float chiphimotgoi) {
        this.sothuochangngay = sothuochangngay;
        this.sothuocmotgoi = sothuocmotgoi;
        this.chiphimotgoi = chiphimotgoi;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View chiphithuocDialog=getActivity().getLayoutInflater().inflate(R.layout.fragment_chiphithuoc,null);
        builder.setView(chiphithuocDialog);
        TextView txtTitle1 =(TextView)chiphithuocDialog.findViewById(R.id.txtTitle1);
        TextView txtChiPhiThang =(TextView)chiphithuocDialog.findViewById(R.id.txtChiPhiThang);
        TextView txtTitle2 =(TextView)chiphithuocDialog.findViewById(R.id.txtTitle2);
        TextView txtChiPhiNam =(TextView)chiphithuocDialog.findViewById(R.id.txtChiPhiNam);
        txtTitle1.setText("Chi phí hàng tháng");
        txtTitle2.setText("Chi phí hàng năm");

        float chiphimotthang=((sothuochangngay*30)/sothuocmotgoi)*chiphimotgoi;
        txtChiPhiThang.setText(""+chiphimotthang+"");
        float chiphimotnam=((sothuochangngay*365)/sothuocmotgoi)*chiphimotgoi;
        txtChiPhiNam.setText(""+chiphimotnam+"");

        builder.setNegativeButton("Quay Lại",null);
        return builder.create();

    }
}
