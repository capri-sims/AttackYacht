/*
********************************************************************************
*** FileTransferService.java
*** Group 5
********************************************************************************
*** Purpose:
*** A service that process each file transfer request i.e Intent by opening a
*** socket connection with the WiFi Direct Group Owner and writing the file
********************************************************************************
*** Date:
*** 11/xx/15
********************************************************************************
*** Change Log:
*** 11/27/15 - JB -
***
********************************************************************************
*/

// Project Package
package group5.attackyacht;

// Imported libraries
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class FileTransferService extends IntentService {

    private static final int SOCKET_TIMEOUT = 5000;
    public static final String ACTION_SEND_FILE = "jing.app.directwifi.SEND_FILE";
    public static final String EXTRAS_FILE_PATH = "file_url";
    public static final String EXTRAS_GROUP_OWNER_ADDRESS = "go_host";
    public static final String EXTRAS_GROUP_OWNER_PORT = "go_port";

    /*
********************************************************************************
*** FileTransferService
*** Group 5
********************************************************************************
*** Purpose:
***
*** Inputs:
*** String name
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/27/15
********************************************************************************
*/
    public FileTransferService(String name) {
        super(name);
    }

    /*
********************************************************************************
*** FileTransferService
*** Group 5
********************************************************************************
*** Purpose:
***
*** Inputs:
*** n/a
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/27/15
********************************************************************************
*/
    public FileTransferService() {
        super("FileTransferService");
    }

    /*
********************************************************************************
*** onHandleIntent
*** Group 5
********************************************************************************
*** Purpose:
***
*** Inputs:
*** Intent intent
*** Outputs:
*** n/a
********************************************************************************
*** Date
*** 11/27/15
********************************************************************************
*/
    @Override
    protected void onHandleIntent(Intent intent) {

        Context context = getApplicationContext();
        if (intent.getAction().equals(ACTION_SEND_FILE)) {
            String host = intent.getExtras().getString(EXTRAS_GROUP_OWNER_ADDRESS);
            Socket socket = new Socket();
            int port = intent.getExtras().getInt(EXTRAS_GROUP_OWNER_PORT);
            DataOutputStream stream = null;
            try {
                socket.connect((new InetSocketAddress(host, port)), SOCKET_TIMEOUT);
                stream = new DataOutputStream(socket.getOutputStream());
                stream.writeUTF("a string");
            } catch (IOException e) {
                //Log.e(MainActivity.TAG, e.getMessage());
            } finally {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (socket != null) {
                    if (socket.isConnected()) {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
    }
}