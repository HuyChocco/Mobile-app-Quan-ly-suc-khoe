package com.chocco.huy.qlsk;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ACER on 4/20/2017.
 */

public class NangLuongResultDialogFragment extends DialogFragment {
    private int cannang,chieucao;
    private int tuoi;
    private boolean nam,nu;

    public NangLuongResultDialogFragment(int cannang, int chieucao, int tuoi, boolean nam, boolean nu) {
        this.cannang = cannang;
        this.chieucao = chieucao;
        this.tuoi = tuoi;
        this.nam = nam;
        this.nu = nu;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        View nangluongDialog=getActivity().getLayoutInflater().inflate(R.layout.fragment_nangluong,null);
        builder.setView(nangluongDialog);
        TextView txtTitle =(TextView)nangluongDialog.findViewById(R.id.txtTitle);
        ListView lvNangLuong =(ListView) nangluongDialog.findViewById(R.id.lvNangLuong);
        txtTitle.setText("Tiêu hao năng lượng");
        ArrayList<String> kq = new ArrayList<>();
        if(nam) {

            kq.add("Ít vận động     " + ((13.397 * cannang) + (4.799 * chieucao) - (5.677 * tuoi) + 88.362)*1.2);
            kq.add("Vận động nhẹ   "+((13.397 * cannang) + (4.799 * chieucao) - (5.677 * tuoi) + 88.362)*1.375);
            kq.add("Vận động vừa phải   "+((13.397 * cannang) + (4.799 * chieucao) - (5.677 * tuoi) + 88.362)*1.55);
            kq.add("Vận động nhiều   "+((13.397 * cannang) + (4.799 * chieucao) - (5.677 * tuoi) + 88.362)*1.725);
            kq.add("Vận động nặng  "+((13.397 * cannang) + (4.799 * chieucao) - (5.677 * tuoi) + 88.362)*1.9);
        }
        if(nu)
        {

            kq.add("Ít vận động     " + ((9.247 * cannang) + (3.098 * chieucao) - (4.330 * tuoi) + 447.593)*1.2);
            kq.add("Vận động nhẹ   "+((9.247 * cannang) + (3.098 * chieucao) - (4.330 * tuoi) +447.593)*1.375);
            kq.add("Vận động vừa phải   "+((9.247 * cannang) + (3.098 * chieucao) - (4.330 * tuoi) + 447.593)*1.55);
            kq.add("Vận động nhiều   "+((9.247 * cannang) + (3.098 * chieucao) - (4.330 * tuoi) + 447.593)*1.725);
            kq.add("Vận động nặng  "+((9.247 * cannang) + (3.098 * chieucao) - (4.330 * tuoi) + 447.593)*1.9);
        }
        lvNangLuong.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,kq));
        builder.setNegativeButton("Quay Lại",null);
        return builder.create();

    }
}
