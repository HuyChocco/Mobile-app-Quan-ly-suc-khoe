package com.chocco.huy.qlsk;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ACER on 4/20/2017.
 */

public class MauResultDialogFragment extends DialogFragment {
    private float cannang,chieucao;
    private boolean nam,nu;

    public MauResultDialogFragment(float cannang, float chieucao, boolean nam, boolean nu) {
        this.cannang = cannang;
        this.chieucao = chieucao;
        this.nam = nam;
        this.nu = nu;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View mauDialog=getActivity().getLayoutInflater().inflate(R.layout.fragment_thetichmau,null);
        builder.setView(mauDialog);
        TextView txtTitle =(TextView)mauDialog.findViewById(R.id.txtTitle);
        TextView txtTheTichMau =(TextView)mauDialog.findViewById(R.id.txtTheTichMau);
        txtTitle.setText("THỂ TÍCH MÁU");
        float kq;


      if(nam) {
           kq = 0.3669f * (chieucao * chieucao * chieucao) + (0.03219f * cannang) + 0.6041f;
          txtTheTichMau.setText(""+kq+"");
      }
      if(nu){
          kq = 0.3561f * (chieucao * chieucao * chieucao) + ( 0.03308f * cannang) + 0.1833f;
          txtTheTichMau.setText(""+kq+"");
      }

        builder.setNegativeButton("Quay Lại",null);
        return builder.create();

    }

}
