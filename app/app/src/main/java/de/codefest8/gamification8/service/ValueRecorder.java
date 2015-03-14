package de.codefest8.gamification8.service;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

/**
 * Created by koerfer on 14.03.2015.
 */
public class ValueRecorder {

    BluetoothAdapter bluetoothAdapter;
    BluetoothDevice obdDevice;

    public ValueRecorder() {
        String deviceToken = "";
        if (deviceToken != "-") {
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            obdDevice = bluetoothAdapter.getRemoteDevice(deviceToken);
        }
    }

    public void startRecord() {

    }

    private Runnable record = new Runnable() {
        @Override
        public void run() {

        }
    };

    public void finishRecord() {

    }

}
