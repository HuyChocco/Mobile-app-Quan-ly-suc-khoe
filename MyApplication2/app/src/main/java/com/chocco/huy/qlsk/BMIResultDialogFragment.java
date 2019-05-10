package com.chocco.huy.qlsk;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ACER on 4/19/2017.
 */

public class BMIResultDialogFragment extends DialogFragment {
    private float cannang,chieucao;
    public BMIResultDialogFragment() {
        super();
    }

    public BMIResultDialogFragment(float cannang, float chieucao) {
        this.cannang = cannang;
        this.chieucao = chieucao;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View bmiDialog=getActivity().getLayoutInflater().inflate(R.layout.fragment_bmi,null);
        builder.setView(bmiDialog);
        TextView txtTitle =(TextView)bmiDialog.findViewById(R.id.txtTitle);
        TextView txtBMI =(TextView)bmiDialog.findViewById(R.id.txtBMI);
        txtTitle.setText("BMI");

        TextView txtKetQua=(TextView)bmiDialog.findViewById(R.id.txtKetQua);


        float kq=cannang/(chieucao*chieucao);
        if(kq<18.5)
            txtKetQua.setText("Thiếu cân");
        else if(18.5<=kq&&kq<=24.9)
            txtKetQua.setText("Trọng lượng bình thường");
        else if(25<=kq&&kq<=29.9)
            txtKetQua.setText("Thừa cân");
        else if(kq>=30)
            txtKetQua.setText("Béo phì");
        txtBMI.setText(""+kq+"");
        builder.setNegativeButton("Quay Lại",null);
        return builder.create();

    }
}
