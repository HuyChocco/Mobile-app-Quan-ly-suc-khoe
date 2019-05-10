package com.chocco.huy.qlsk;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by ACER on 4/19/2017.
 */

public class BMIInfoDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Thông tin");
        builder.setMessage(R.string.message_bmi);
        builder.setNegativeButton("Quay lại",null);

        return builder.create();

    }
}
