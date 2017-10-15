package com.cedric.clipboardsync.sender;

import android.util.Log;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class TcpClient
{
    private  String serverIp;
    private int serverPort;

    private String mServerMessage;

    private PrintWriter mBufferOut;

    public TcpClient(String serverIp, String serverPort)
    {
        this.serverIp = serverIp;
        this.serverPort = Integer.parseInt(serverPort);

        try
        {
            InetAddress serverAddr = InetAddress.getByName(serverIp);

            Socket socket = new Socket(serverAddr, this.serverPort);

            mBufferOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

        }
        catch (Exception ex)
        {
            Log.d("STATE", ex.getMessage() +ex.getStackTrace());
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
        mServerMessage = null;
    }
}