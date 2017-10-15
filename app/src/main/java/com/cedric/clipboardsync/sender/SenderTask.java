package com.cedric.clipboardsync.sender;

import android.os.AsyncTask;
import android.util.Base64;

public class SenderTask extends AsyncTask<String, String, String>
{
    TcpClient sender;
    @Override
    protected String doInBackground(String... strings)
    {
        sender = new TcpClient(strings[0], strings[1]);
        sender.sendMessage(strings[2]);
        sender.stopClient();
        return null;
    }
}