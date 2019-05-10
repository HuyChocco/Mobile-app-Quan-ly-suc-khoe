package com.chocco.huy.qlsk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by ACER on 12/29/2017.
 */

public class NotifyMeal extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {



        if (TabFragment5.switchMeal.isChecked())
        {
            Calendar currenttime = Calendar.getInstance();
            int hour = currenttime.get(Calendar.HOUR_OF_DAY);
            int minute = currenttime.get(Calendar.MINUTE);

            switch (hour)
            {
                case 8:
                    if (minute == 30)
                        TabFragment5.showDialogmeal();
                    break;
                case 12:
                    if (minute == 30)
                        TabFragment5.showDialogmeal();
                    break;
                case 16:
                    if (minute == 0)
                        TabFragment5.showDialogmeal();
                    break;
                case 19:
                    if (minute == 30)
                        TabFragment5.showDialogmeal();
                    break;
            }


        }
    }
}
