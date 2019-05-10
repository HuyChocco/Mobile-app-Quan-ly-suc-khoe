package com.chocco.huy.qlsk;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ACER on 5/7/2017.
 */

public class BuocChanInfoDialogFragment extends DialogFragment {
    int numSteps;

    public BuocChanInfoDialogFragment(int numSteps) {
        this.numSteps = numSteps;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String stringDate = dateFormat.format(date);
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Thông tin - "+stringDate);
        builder.setMessage("Tổng số bước chân của bạn : "+numSteps);
        builder.setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finish();
            }
        });

        return builder.create();

    }
}
