package com.chocco.huy.qlsk;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.zhaoxiaodan.miband.ActionCallback;
import com.zhaoxiaodan.miband.MiBand;
import com.zhaoxiaodan.miband.listeners.NotifyListener;
import com.zhaoxiaodan.miband.listeners.RealtimeStepsNotifyListener;
import com.zhaoxiaodan.miband.model.VibrationMode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import static android.content.ContentValues.TAG;

public class Pedometer extends AppCompatActivity //implements SensorEventListener
{
    //SensorManager sensorManager;
    //TextView txtStep;
    //TextView txtX;
   // TextView txtY;
    //TextView txtZ;
   // Sensor sensor;
   // float currentY;
   // float previousY;
    public static int numSteps;
  //  float acceleration;
  //  int threshold;

    private static final String TAG = "==[mibandtest]==";
//    public static MiBand miband = PedometerService.miband;
    BluetoothDevice band1a;
    HashMap<String, BluetoothDevice> devices = new HashMap<String, BluetoothDevice>();
    String uuid = "C8:0F:10:11:A9:84";
   // String uuid = "C8:0F:10:17:74:35";
    ArrayList<Integer> totalStep;
    ArrayList<Integer> index;
    Chart chart;
    ArrayList<BarEntry> barEntries;
    Button btnDoBuocChan;
    Button btnRung;
    public static Button btnKetThuc;
    TextView txtThongBaoDo;
    TextView txtSteps;
    public static TextView txtThongBao;
    int solan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        totalStep=new ArrayList<>();
        index=new ArrayList<>();
        barEntries=new ArrayList<>();
        setContentView(R.layout.activity_pedometer);
        chart=(Chart)findViewById(R.id.chart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        //assert actionBar!=null;
        actionBar.setDisplayHomeAsUpEnabled(true);
       // actionBar.setDisplayShowHomeEnabled(true);
        txtSteps=(TextView)findViewById(R.id.txtSteps);
        txtThongBao=(TextView)findViewById(R.id.txtThongBao);
       // threshold=10;

       // acceleration=0.00f;
        final Intent intent=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(intent,0);
//        miband = new MiBand(this);
        Button btnMiband=(Button)findViewById(R.id.btnMiband);
       // final ArrayAdapter adapter1 = new ArrayAdapter<String>(this, R.layout.activity_pedometer, new ArrayList<String>());
        btnMiband.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Intent toConnect = new Intent(Pedometer.this,ConnectDevice.class);
                startActivity(toConnect);

            }

        });
        final TextView txtThongBaoDo=(TextView)findViewById(R.id.txtThongBaoDo);
        btnRung=(Button)findViewById(R.id.btnRung);

        btnRung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PedometerService.miband.startVibration(VibrationMode.VIBRATION_10_TIMES_WITH_LED);
            }
        });
          btnDoBuocChan=(Button)findViewById(R.id.btnDoBuocChan);


        btnDoBuocChan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtThongBaoDo.setText("Đang nhận số liệu số bước chân ...");
                realtimestep();
            }
        });
      btnKetThuc=(Button)findViewById(R.id.btnKetThuc);
        btnKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finishandrecord();
            }
        });

    }

    public void realtimestep()
    {
        numSteps= PedometerService.numSteps;

        PedometerService.miband.setRealtimeStepsNotifyListener(new RealtimeStepsNotifyListener() {
            @Override
            public void onNotify(final int steps) {
                ++PedometerService.numSteps;
                Log.d(TAG, "RealtimeStepsNotifyListener:" + steps);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txtSteps.setText(PedometerService.numSteps +"");
                    }
                });
            }
        });
    }
    public  void searchDevicebluetooth()
    {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices.isEmpty()) {
            Log.e(TAG,
                    "No devices paired...");
            // return ;
        }
        for (BluetoothDevice device : pairedDevices) {
            Log.d(TAG, "Device : address : " + device.getAddress() + " name :"
                    + device.getName());
            if (uuid.equals(device.getAddress())) {
                band1a = device;
                break;
            }
        }
    }
    public void connectWithDevice ()
    {
        final ProgressDialog pd = ProgressDialog.show(Pedometer.this, "", "Đang kết nối vào thiết bị ...");
        Log.d(TAG, "Dừng quét...");
        PedometerService.miband.connect(band1a, new ActionCallback() {
            @Override
            public void onSuccess(Object data) {
                // btnDoBuocChan.setEnabled(true);
                // btnRung.setEnabled(true);
                pd.dismiss();
                Log.d(TAG, "Kết nối thành công");
                PedometerService.startus=true;
                txtThongBao.setText("Kết nối thành công");

                PedometerService.miband.setDisconnectedListener(new NotifyListener() {
                    @Override
                    public void onNotify(byte[] data) {
                        Log.d(TAG, "Ngắt kết nối");
                    }
                });
            }
            @Override
            public void onFail(int errorCode, String msg) {
                pd.dismiss();
                Log.d(TAG, "connect fail, code:" + errorCode + ",mgs:" + msg);
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
           return true ;
        }
        if(id==android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Intent i = new Intent(Pedometer.this, PedometerService.class);
        startService(i);
    }

    @Override
    protected void onResume() {
//        Intent x = new Intent(Pedometer.this, PedometerService.class);
//        stopService(x);
//        searchDevicebluetooth();
//        connectWithDevice();
       // realtimestep();
        super.onResume();
        SharedPreferences sharedPreferences=getSharedPreferences("buocchan",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        int a=sharedPreferences.getInt("SOBUOCCHAN",0);
        String date=sharedPreferences.getString("THOIGIAN","");
        int b=sharedPreferences.getInt("SOLAN",0);
        if(b>0)
        {
            solan=b;
            editor.putInt("LAN"+solan,a);

            editor.commit();

            for(int j=0;j<solan;j++)
            {
                int i=j+1;

                int bc=sharedPreferences.getInt("LAN"+i,0);

                barEntries.add(new BarEntry(i,bc));
            }

            TextView txtLichSu=(TextView)findViewById(R.id.txtLichSu);
            txtLichSu.setText(date +" Số bước chân : "+a);
        }

      //  Toast.makeText(getApplication(),a+" "+date+" "+solan,Toast.LENGTH_LONG).show();

        BarDataSet barDataSet=new BarDataSet(barEntries,"Số bước chân");
        chart.setData(new BarData(barDataSet));
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        Description description=new Description();
        description.setText("Theo dõi bước đi");
        chart.setDescription(description);
        chart.animateY(1000);
    }
    public void finishandrecord()
    {
        ++solan;
        SharedPreferences sharedPreferences=getSharedPreferences("buocchan",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("SOBUOCCHAN",PedometerService.numSteps);
        editor.putInt("SOLAN",solan);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String stringDate = dateFormat.format(date);
        editor.putString("THOIGIAN",stringDate);
        editor.commit();
        BuocChanInfoDialogFragment buocChanInfoDialogFragment=new BuocChanInfoDialogFragment(PedometerService.numSteps);
        buocChanInfoDialogFragment.show(getSupportFragmentManager(),"THONG BAO");
    }
//    /*@Override
//    protected void onPause() {
//        super.onPause();
//        sensorManager.unregisterListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER));
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
//        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        if(sensor!=null)
//        {
//        sensorManager.registerListener(this,sensor
//                ,SensorManager.SENSOR_DELAY_UI);}
//        else
//            Toast.makeText(this,"Sensor not found ! ",Toast.LENGTH_LONG).show();
//
//    }
//
//    @Override
//    public void onSensorChanged(SensorEvent event) {
//
//        float x=event.values[0];
//        float y=event.values[0];
//        float z=event.values[0];
//
//        currentY=y;
//
//        if(Math.abs(currentY-previousY)>threshold)
//        {
//            numSteps++;
//            txtStep.setText(String.valueOf(numSteps));
//        }
//        previousY=y;
//        txtX.setText("X="+String.valueOf(x));
//        txtY.setText("Y="+String.valueOf(y));
//        txtZ.setText("Z="+String.valueOf(z));
//
//    }
//
//    @Override
//    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//    }*/
}
