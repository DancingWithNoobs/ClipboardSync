package com.cedric.clipboardsync.sync;

import android.util.Log;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;

public class TcpClient
{
    private PrintWriter mBufferOut;

    public TcpClient(String serverIp, String serverPort)
    {
        int serverPort1 = Integer.parseInt(serverPort);

        try
        {
            InetAddress serverAddr = InetAddress.getByName(serverIp);
            Socket socket = new Socket(serverAddr, serverPort1);
            mBufferOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
        }
        catch (Exception ex)
        {
            Log.d("STATE", ex.getMessage() + Arrays.toString(ex.getStackTrace()));
        }
    }

    public void sendMessage(String message)
    {
        if (mBufferOut != null && !mBufferOut.checkError())
        {
            mBufferOut.println(message);
            mBufferOut.flush();
        }
    }

    public void stopClient()
    {
        if (mBufferOut != null)
        {
            mBufferOut.flush();
            mBufferOut.close();
        }

        mBufferOut = null;
    }
}