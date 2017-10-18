package com.cedric.clipboardsync.broadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.cedric.clipboardsync.services.ClipboardChecker;

public class ServiceStarter extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED))
        {
            Toast.makeText( context, "RECEIVER STARTED", Toast.LENGTH_LONG).show();
            ComponentName service = context.startService(new Intent(context, ClipboardChecker.class));
        }
    }
}
