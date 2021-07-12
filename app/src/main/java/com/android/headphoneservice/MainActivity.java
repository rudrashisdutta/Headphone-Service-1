package com.android.headphoneservice;

import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    BroadcastReceiver broadcastReceiver;
    boolean Microphone_Plugged_in = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                final String action = intent.getAction();
                int iii;
                if (Intent.ACTION_HEADSET_PLUG.equals(action)) {
                    iii = intent.getIntExtra("state", -1);
                    if (iii == 0) {
                        Microphone_Plugged_in = false;
                        Toast.makeText(getApplicationContext(), "Headset is currently disconnected!", Toast.LENGTH_LONG).show();
                    }
                    if (iii == 1) {
                        Microphone_Plugged_in = true;
                        Toast.makeText(getApplicationContext(), "Headset connected!",
                                Toast.LENGTH_LONG).show();
                        String textTitle="HEADSET CONNECTED";
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "No headphone connectivity found!",
                            Toast.LENGTH_LONG).show();
                }
            }
        };
        IntentFilter receiverFilter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(broadcastReceiver, receiverFilter);
    }
}