package com.chocco.huy.qlsk;

import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import java.util.Calendar;

/**
 * Created by ACER on 4/7/2017.
 */

public class TabFragment5 extends Fragment {
    static public Switch switchWater;
    static public Switch switchMeal;
    static public Dialog dialogWater;
    static public Dialog dialogMeal;
    static public RadioGroup waterRadioGroup;
    static public int minnote;
    static public int watercondition;

    public Calendar currenttime;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         View view=inflater.inflate(R.layout.tab_fragment_5,container,false);
        switchWater = (Switch) view.findViewById(R.id.NoteWaterOnOff);
        switchMeal = (Switch) view.findViewById(R.id.NoteMealOnOff);

        dialogWater = new Dialog(getActivity());
        dialogMeal = new Dialog(getActivity());

        waterRadioGroup = (RadioGroup) view.findViewById(R.id.waterGroup);
        waterRadioGroup.clearCheck();

        waterRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                                                           {
                                                               @Override
                                                               public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                                   currenttime = Calendar.getInstance();

                                                                   minnote = currenttime.get(Calendar.MINUTE);

                                                                   RadioButton rb = (RadioButton) group.findViewById(checkedId);
                                                                   if (rb.getId() == R.id.NoteWater1)
                                                                   {
                                                                       watercondition = 1;
                                                                   }
                                                                   switch (rb.getId()) {
                                                                       case R.id.NoteWater1:
                                                                           watercondition = 1;
                                                                           break;
                                                                       case R.id.NoteWater2:
                                                                           watercondition = 2;
                                                                           break;
                                                                       case R.id.NoteWater3:
                                                                           watercondition = 3;
                                                                           break;
                                                                       case R.id.NoteWater4:
                                                                           watercondition = 4;
                                                                           break;
                                                                   }

                                                               }
                                                           }
        );

        getActivity().registerReceiver(new NotifyWater(), new IntentFilter(Intent.ACTION_TIME_TICK));
        getActivity().registerReceiver(new NotifyMeal(), new IntentFilter(Intent.ACTION_TIME_TICK));
        return view;


    }
    static public void showDialogwater() {
        dialogWater.setTitle("Nhắc Nhở");
        dialogWater.setContentView(R.layout.dialogwater);
        dialogWater.show();
    }

    static public void showDialogmeal() {
        dialogMeal.setTitle("Nhắc Nhở");
        dialogMeal.setContentView(R.layout.dialogmeal);
        dialogMeal.show();
    }
}
