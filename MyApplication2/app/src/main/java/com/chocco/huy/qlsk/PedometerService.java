package com.chocco.huy.qlsk;

import android.app.ProgressDialog;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.zhaoxiaodan.miband.ActionCallback;
import com.zhaoxiaodan.miband.MiBand;
import com.zhaoxiaodan.miband.listeners.NotifyListener;
import com.zhaoxiaodan.miband.listeners.RealtimeStepsNotifyListener;

import java.util.HashMap;
import java.util.Set;

import static android.content.ContentValues.TAG;
import static com.chocco.huy.qlsk.R.id.txtSteps;
import static com.github.mikephil.charting.charts.Chart.LOG_TAG;

public class PedometerService extends Service {
    public PedometerService() {
    }
    public static int numSteps;
    BluetoothDevice band1a;
    public static MiBand miband;;
    public static boolean startus = false;
    HashMap<String, BluetoothDevice> devices = new HashMap<String, BluetoothDevice>();
    String uuid = "C8:0F:10:11:A9:84";
  MiBand miBand;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate(){
        super.onCreate();
        Log.d(LOG_TAG,"START PADOMETER");
        miband = new MiBand(this);
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
//        Runnable connect = new Runnable() {
//            @Override
//            public void run() {
//                BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//                Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
//                if (pairedDevices.isEmpty()) {
//                    Log.e(TAG,
//                            "No devices paired...");
//                    // return ;
//                }
//                for (BluetoothDevice device : pairedDevices) {
//                    Log.d(TAG, "Device : address : " + device.getAddress() + " name :"
//                            + device.getName());
//                    if (uuid.equals(device.getAddress())) {
//                        band1a = device;
//                        break;
//                    }
//                }
//             final   ProgressDialog pd = ProgressDialog.show(PedometerService.this, "Kết nối với thiết bị", "Đang kết nối vào thiết bị ...");
//                Log.d(TAG, "Dừng quét...");
//                miband.connect(band1a, new ActionCallback() {
//                    @Override
//                    public void onSuccess(Object data) {
//                  pd.dismiss();
//                        Log.d(TAG, "Kết nối thành công");
//                        Pedometer.txtThongBao.setText("Kết nối thành công");
//
//                        miband.setDisconnectedListener(new NotifyListener() {
//                            @Override
//                            public void onNotify(byte[] data) {
//                                Log.d(TAG, "Ngắt kết nối");
//                            }
//                        });
//                    }
//                    @Override
//                    public void onFail(int errorCode, String msg) {
////                pd.dismiss();
//                        Log.d(TAG, "connect fail, code:" + errorCode + ",mgs:" + msg);
//                    }
//                });
////            }
////        };

        ///realtimestep
//       miband.setRealtimeStepsNotifyListener(new RealtimeStepsNotifyListener() {
//            @Override
//            public void onNotify(final int steps) {
//                ++numSteps;
//                Log.d(TAG, "RealtimeStepsNotifyListener:" + steps);
//
//            }
//        });

        return START_STICKY;
    }


}
