package com.chocco.huy.qlsk;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.zhaoxiaodan.miband.ActionCallback;
import com.zhaoxiaodan.miband.listeners.NotifyListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ConnectDevice extends AppCompatActivity {
    ListView main;
    Button list;
    List<BluetoothDevice> devicebluetooth = new ArrayList<BluetoothDevice>();
    private static final String TAG = "==[mibandtest]==";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_device);
        main = (ListView) findViewById(R.id.lvmaindevice);
        list = (Button) findViewById(R.id.btnMainDevice);
        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchDevicebluetooth();
            }
        });
        main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s = devicebluetooth.get(position).getAddress();
                s = s.substring(0,8);

                if(s.equals("C8:0F:10"))
                {
                    connectWithDevice(devicebluetooth.get(position));

                }
              else
                {
                    Toast.makeText(ConnectDevice.this,"Thiết bị này không được hổ trợ !",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public  void searchDevicebluetooth()
    {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices.isEmpty()) {
            Toast.makeText(ConnectDevice.this,"Bạn chưa có thiết bị nào !",Toast.LENGTH_SHORT).show();
            Log.e(TAG, "No devices paired...");
            // return ;
        }
        List<String> namedevice = new ArrayList<String>();

        for (BluetoothDevice device : pairedDevices) {
            Log.d(TAG, "Device : address : " + device.getAddress() + " name :" + device.getName());
            devicebluetooth.add(device);
            namedevice.add(device.getName()+" | "+device.getAddress());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,namedevice);
        main.setAdapter(adapter);
    }
    public void connectWithDevice (BluetoothDevice band1a)
    {
        final ProgressDialog pd = ProgressDialog.show(ConnectDevice.this, "", "Đang kết nối vào thiết bị ...");
        Log.d(TAG, "Dừng quét...");
        PedometerService.miband.connect(band1a, new ActionCallback() {
            @Override
            public void onSuccess(Object data) {
                pd.dismiss();
                Log.d(TAG, "Kết nối thành công");
                PedometerService.startus=true;
                Toast.makeText(ConnectDevice.this,"Kết nối thành công !",Toast.LENGTH_SHORT).show();
                finish();
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
                Toast.makeText(ConnectDevice.this,"Kết nối thất bại !",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
