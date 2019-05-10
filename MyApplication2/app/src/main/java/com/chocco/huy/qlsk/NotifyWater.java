package com.chocco.huy.qlsk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.BaseAdapter;

import java.util.Calendar;

/**
 * Created by ACER on 12/29/2017.
 */

public class NotifyWater extends BroadcastReceiver {
    private static final String TAG = "TipCalculatorActivity";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (TabFragment5.switchWater.isChecked())
        {
            Calendar currenttime = Calendar.getInstance();
            TabFragment5.showDialogwater();
            int currentmin = currenttime.get(Calendar.MINUTE);

            int diffmin = currentmin - TabFragment5.minnote;

            if (diffmin == 60 * 60 * TabFragment5.watercondition) {
                TabFragment5.showDialogwater();

                TabFragment5.switchWater.setChecked(false);
            }
        }
    }
}
