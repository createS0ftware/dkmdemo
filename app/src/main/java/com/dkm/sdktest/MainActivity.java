package com.dkm.sdktest;

import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSONObject;
import com.dkm.dkmdevice.DkmDevice;
import com.dkm.dkmdevice.RegCallBack;
import com.dkm.dkmdevice.SendOutCallBack;
import com.hjq.toast.ToastUtils;


public class MainActivity extends AppCompatActivity {

    private String TAG = "DKM";
    Button sendButton;
    EditText editMachineId;

    DkmDevice dkmDevice = DkmDevice.get();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniView();

        initLibs();
    }

    private void initLibs() {
        Log.i(TAG, "initLibs....");
        String machineId = editMachineId.getText().toString();


        if (!machineId.isEmpty()) {
            Pair<Integer, String> result = initMachine(machineId);
            if (result.first != 1) {
                ToastUtils.show("operation failed:" + result.second);
            }
        } else {
            ToastUtils.show("Please enter a machine Id");
        }
    }

    private Pair<Integer, String> initMachine(String machineId) {
        Pair<Integer, String> result = dkmDevice.regDevice(machineId, "ttyS1", 115200, new RegCallBack() {
            @Override
            public void onResult(int code, String msg, JSONObject jsonObject) {
                if (code == 1) {
                    Log.i(TAG, "register ok");
                    ToastUtils.show("register ok");
                    shipTest();
                } else {
                    Log.i(TAG, "register error:" + msg);
                    ToastUtils.show("register error:" + msg);
                }
            }
        });
        return result;
    }

    private void iniView() {
        sendButton = findViewById(R.id.btn1);
        editMachineId = findViewById(R.id.editMachineId);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initMachine(editMachineId.getText().toString());

            }
        });
    }

    private void shipTest() {
        Pair<Integer, String> result = dkmDevice.sendOut("1", new SendOutCallBack() {
            @Override
            public void onResult(int code, String resultData, JSONObject jsonObject) {
                if (code == 1) {
                    ToastUtils.show("Successfully shipped：" + resultData);
                } else {
                    ToastUtils.show("Shipment failed：" + resultData);
                }
            }
        });
        if (result.first != 1) {
            ToastUtils.show("operation failed:" + result.second);
        }
    }
}
