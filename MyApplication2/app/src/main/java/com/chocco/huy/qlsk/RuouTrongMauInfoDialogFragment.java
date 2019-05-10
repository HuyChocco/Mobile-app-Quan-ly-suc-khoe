package com.chocco.huy.qlsk;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

/**
 * Created by ACER on 4/21/2017.
 */

public class RuouTrongMauInfoDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Thông tin");
        builder.setMessage(R.string.message_ruoutrongmau);
        builder.setNegativeButton("Quay lại",null);

        return builder.create();
    }
}
