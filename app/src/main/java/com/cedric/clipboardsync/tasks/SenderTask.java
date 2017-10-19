package com.cedric.clipboardsync.tasks;

import android.os.AsyncTask;
import com.cedric.clipboardsync.sync.TcpClient;

public class SenderTask extends AsyncTask<String, String, String>
{
    @Override
    protected String doInBackground(String... strings)
    {
        TcpClient sender = new TcpClient(strings[0], strings[1]);
        sender.sendMessage(strings[2]);
        sender.stopClient();
        return null;
    }
}