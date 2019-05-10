package com.chocco.huy.qlsk;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TaoHoSo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tao_ho_so);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        assert actionBar!=null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        SharedPreferences preferenceManager=getSharedPreferences("hoso",MODE_PRIVATE);
        final SharedPreferences.Editor editor=preferenceManager.edit();
        final TextView txtTen=(TextView)findViewById(R.id.txtTen);
        TextView txtChieuCao=(TextView)findViewById(R.id.txtChieuCao);
        final TextView txtCanNang=(TextView)findViewById(R.id.txtCanNang);
        final TextView txtThatLung=(TextView)findViewById(R.id.txtThatLung);
        Button btnTaoHS=(Button)findViewById(R.id.btnTaoHS);
        btnTaoHS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("TEN",txtTen.getText().toString());
                editor.putString("CHIEUCAO",txtCanNang.getText().toString());
                editor.putString("THATLUNG",txtThatLung.getText().toString());
                editor.apply();
                finish();
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
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}
