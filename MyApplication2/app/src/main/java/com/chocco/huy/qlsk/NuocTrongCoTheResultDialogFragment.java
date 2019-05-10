package com.chocco.huy.qlsk;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ACER on 5/5/2017.
 */

public class NuocTrongCoTheResultDialogFragment extends DialogFragment {
    private float cannang,chieucao;

    public NuocTrongCoTheResultDialogFragment(float cannang, float chieucao) {
        this.cannang = cannang;
        this.chieucao = chieucao;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View nuocTrongCTDialog=getActivity().getLayoutInflater().inflate(R.layout.fragment_nuoctrongcothe,null);
        builder.setView(nuocTrongCTDialog);
        TextView txtTitle =(TextView)nuocTrongCTDialog.findViewById(R.id.txtTitle);

        txtTitle.setText("Nước trong cơ thể");

        TextView txtNuocTrongCoThe=(TextView)nuocTrongCTDialog.findViewById(R.id.txtNuocTrongCoThe);


        float kq=51.44f*cannang / chieucao+ 15.3f;


        txtNuocTrongCoThe.setText(""+kq+"");
        builder.setNegativeButton("Quay Lại",null);
        return builder.create();

    }
}
