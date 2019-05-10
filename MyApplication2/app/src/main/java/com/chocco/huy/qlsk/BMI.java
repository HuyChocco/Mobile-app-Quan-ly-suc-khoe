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
import android.widget.RadioGroup;
import android.widget.Toast;

public class BMI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        assert actionBar!=null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Button btnTinh=(Button) findViewById(R.id.btnTinh);
        final EditText txtCanNang=(EditText)findViewById(R.id.txtCanNang);
        final EditText txtChieuCao=(EditText)findViewById(R.id.txtChieuCao);
        final EditText txtTuoi=(EditText)findViewById(R.id.txtTuoi);


        final LinearLayout linearLayout=(LinearLayout)findViewById(R.id.linear_bmi);
        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtCanNang.getText().toString().length()>0&&txtChieuCao.getText().toString().length()>0
                        &&txtTuoi.getText().toString().length()>0) {
                    BMIResultDialogFragment bmiResultDialogFragment = new BMIResultDialogFragment(Float.parseFloat(txtCanNang.getText().toString())
                            , Float.parseFloat(txtChieuCao.getText().toString()) / 100);

                    bmiResultDialogFragment.show(getSupportFragmentManager(), "KET QUA");
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
            BMIInfoDialogFragment bmiInfoDialogFragment=new BMIInfoDialogFragment();
           bmiInfoDialogFragment.show(getSupportFragmentManager(),"BMI");

        }
        if(id==android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}
