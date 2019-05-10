package com.chocco.huy.qlsk;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ACER on 4/28/2017.
 */

public class NhuCauNuocResultDialogFragment extends DialogFragment {
    private float cannang;


    public NhuCauNuocResultDialogFragment(float cannang) {
        this.cannang = cannang;

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View bmiDialog=getActivity().getLayoutInflater().inflate(R.layout.fragment_nhucaunuoc,null);
        builder.setView(bmiDialog);
        TextView txtTitle =(TextView)bmiDialog.findViewById(R.id.txtTitle);

        txtTitle.setText("Nhu cầu nước");

        TextView txtNhuCauNuoc=(TextView)bmiDialog.findViewById(R.id.txtNhuCauNuoc);


        float kq=cannang*2*0.5f*0.03f;

        txtNhuCauNuoc.setText(""+kq+"");
        builder.setNegativeButton("Quay Lại",null);
        return builder.create();

    }
}
