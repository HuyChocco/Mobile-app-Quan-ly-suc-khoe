package com.chocco.huy.qlsk;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class ChiPhiThuoc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_phi_thuoc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        assert actionBar!=null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        Button btnTinh=(Button) findViewById(R.id.btnTinh);
        final EditText txtSoThuocHangNgay=(EditText)findViewById(R.id.txtSoThuocHangNgay);
        final EditText txtTongSoThuoc=(EditText)findViewById(R.id.txtTongSoThuoc);
        final EditText txtChiPhiMotGoi=(EditText)findViewById(R.id.txtChiPhiMotGoi);


        final LinearLayout linearLayout=(LinearLayout)findViewById(R.id.linear_chiphithuoc);
        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtSoThuocHangNgay.getText().toString().length()>0&&txtTongSoThuoc.getText().toString().length()>0
                        &&txtChiPhiMotGoi.getText().toString().length()>0) {
                    ChiPhiThuocResultDialogFragment chiphithuocResultDialogFragment = new ChiPhiThuocResultDialogFragment(
                            Float.parseFloat(txtSoThuocHangNgay.getText().toString())
                            , Float.parseFloat(txtTongSoThuoc.getText().toString())
                            ,Float.parseFloat(txtChiPhiMotGoi.getText().toString()));

                    chiphithuocResultDialogFragment.show(getSupportFragmentManager(), "KET QUA");
                }
                else
                    Snackbar.make(linearLayout,"Bạn vui lòng nhập đầy đủ thông tin",Snackbar.LENGTH_SHORT).show();
            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bmi, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_info) {
            ChiPhiThuocInfoDialogFragment chiPhiThuocInfoDialogFragment=new ChiPhiThuocInfoDialogFragment();
            chiPhiThuocInfoDialogFragment.show(getSupportFragmentManager(),"CHI PHI THUOC");
        }
        if(id==android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

}
