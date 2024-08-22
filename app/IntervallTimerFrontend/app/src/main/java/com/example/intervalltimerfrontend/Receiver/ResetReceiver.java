// ResetReceiver.java
package com.example.intervalltimerfrontend.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class ResetReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Send a local broadcast-message
        Log.e("ResetReceive", "Reset-Timer Broadcast empfangen");
        Intent localIntent = new Intent("RESET_TIMER_ACTION");
        localIntent.setPackage(context.getPackageName());
        context.sendBroadcast(localIntent);
    }
}
