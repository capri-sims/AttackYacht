package group5.attackyacht; //TODO: write dummy device for testing

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectActivity extends AppCompatActivity implements View.OnClickListener,   android.content.DialogInterface.OnClickListener, WifiP2pManager.ConnectionInfoListener {

    private WifiP2pManager mManager;
    private WifiP2pManager.Channel mChannel;
    private BroadcastReceiver mReceiver;
    private IntentFilter mIntentFilter;
    private Button mDiscover;
    public ArrayAdapter mAdapter;
    private ArrayList<WifiP2pDevice> mDeviceList = new ArrayList<WifiP2pDevice>();
    public static final String TAG = "YOUR-TAG-NAME";
    private String m_Text = "";
    public String outputText;

    int flag = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        //start bluetooth
        //get devices
        //populate listview


        //BUTTONS//
        Button buttonConnect = (Button) findViewById(R.id.button_connect);
        buttonConnect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //give bluetooth the selected device
                //if successful

                Intent intent = new Intent(ConnectActivity.this, SetupActivity.class);
                startActivity(intent);
                //else... try again
            }
        });

        //here down Jonathan Added

        mDiscover = (Button) findViewById(R.id.button_connect);
        mDiscover.setOnClickListener(this); //huh?

        //mDevices = (TextView) findViewById(R.id.peers);

        mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = mManager.initialize(this, getMainLooper(), null);
        mReceiver = new WiFiDirectReceiver(mManager, mChannel, this);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);


    }
    //i did not add this
    public void onClickConnect(View v){


    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }

//    @Override //NO MENU
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

    private class WiFiDirectReceiver extends BroadcastReceiver {

        private WifiP2pManager mManager;
        private WifiP2pManager.Channel mChannel;
        private ConnectActivity mActivity;

        public WiFiDirectReceiver(WifiP2pManager manager, WifiP2pManager.Channel channel, ConnectActivity activity) {
            super();
            mManager = manager;
            mChannel = channel;
            mActivity = activity;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {

                int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
                if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                    String title = "ANDROID_ID[" + getAndroid_ID() + "]";
                    title += "   MAC[" + getMACAddress() + "]";
                    Toast.makeText(mActivity, "Wi-Fi Direct is enabled." + title, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mActivity, "Wi-Fi Direct is disabled.", Toast.LENGTH_SHORT).show();
                }

            } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {

                if (mManager != null) {
                    mManager.requestPeers(mChannel, new WifiP2pManager.PeerListListener() {

                        @Override
                        public void onPeersAvailable(WifiP2pDeviceList peers) {
                            if (peers != null) {
                                mDeviceList.addAll(peers.getDeviceList());
                                ArrayList<String> deviceNames = new ArrayList<String>();
                                for (WifiP2pDevice device : mDeviceList) {
                                    deviceNames.add(device.deviceName);
                                }
                                if (deviceNames.size() > 0) {
                                    mAdapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_list_item_1, deviceNames);
                                    if (flag == 0) {
                                        flag = 1;
                                        showDeviceListDialog();
                                    }
                                } else {
                                    Toast.makeText(mActivity, "Device list is empty.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
                }

            } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {


            } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {

            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_connect:
                onDiscover();
                break;
        }
    }

    private void onDiscover() {
        mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {

            @Override
            public void onSuccess() {
                Toast.makeText(ConnectActivity.this, "Discover peers successfully.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(int reason) {
                Toast.makeText(ConnectActivity.this, "Discover peers failed.", Toast.LENGTH_SHORT).show();

                //TAKE THIS OUT LATER
                //THIS ALLOWS THE APP TO MOVE FORWARD FOR TESTING
                Intent intent = new Intent(ConnectActivity.this, SetupActivity.class);
                startActivity(intent);
                ///////////////////
            }
        });
    }

    private void showDeviceListDialog() {
        DeviceListDialog deviceListDialog = new DeviceListDialog();
        deviceListDialog.show(getFragmentManager(), "devices");
    }

    public void sendInfo(String info){
        new FileServerAsyncTask(getApplicationContext()).execute();
    }

    public String getInfo(){
        return outputText;
    }


    private class DeviceListDialog extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Select a device")
                    .setSingleChoiceItems(mAdapter, 0, ConnectActivity.this)
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }

                    });

            return builder.create();
        }

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        onDeviceSelected(which);
        dialog.dismiss();
    }

    private void onDeviceSelected(int which) {
        WifiP2pDevice device = mDeviceList.get(which);
        if (device == null) {
            return;
        }

        WifiP2pConfig config = new WifiP2pConfig();
        config.deviceAddress = device.deviceAddress;
        mManager.connect(mChannel, config, new WifiP2pManager.ActionListener() {

            @Override
            //this is where the call is
            public void onSuccess() {
                //FileServerAsyncTask(getActivity(), mContentView.findViewById(R.id.status_text)).execute();
                //Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //intent.setType("image/*");
                //startActivityForResult(intent, CHOOSE_FILE_RESULT_CODE);   Toast.makeText(ConnectActivity.this, "Connected", Toast.LENGTH_SHORT).show();
                //new
                // new FileServerAsyncTask(getApplicationContext()).execute();
            }

            @Override
            public void onFailure(int reason) {
                Toast.makeText(ConnectActivity.this, "Failed to connect", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * ANDROID_ID
     */
    private String getAndroid_ID() {
        return Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * Wi-Fi MAC
     */
    private String getMACAddress() {
        WifiManager manager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = manager.getConnectionInfo();
        String mac = wifiInfo.getMacAddress();

        // After the group negotiation, we assign the group owner as the file
        // server. The file server is single threaded, single connection server
        // socket.

        new FileServerAsyncTask(getApplicationContext())
                .execute();


        return mac;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        // User has picked an image. Transfer it to group owner i.e peer using
        // FileTransferService.
        Uri uri = data.getData();

        Log.d(ConnectActivity.TAG, "Intent----------- " + uri);
        Intent serviceIntent = new Intent(ConnectActivity.this, FileTransferService.class);
        serviceIntent.setAction(FileTransferService.ACTION_SEND_FILE);
        serviceIntent.putExtra(FileTransferService.EXTRAS_FILE_PATH, uri.toString());
        serviceIntent.putExtra(FileTransferService.EXTRAS_GROUP_OWNER_ADDRESS,
                getMACAddress());
        serviceIntent.putExtra(FileTransferService.EXTRAS_GROUP_OWNER_PORT, 8988);
        startService(serviceIntent);
    }

    /**
     * A simple server socket that accepts connection and writes some data on
     * the stream.
     */
    public class FileServerAsyncTask extends AsyncTask<Void, Void, String> {

        private Context context;

        public FileServerAsyncTask(Context context) {
            this.context = context;

        }

        @Override
        protected String doInBackground(Void... params) {
            ServerSocket serverSocket = null;
            Socket client = null;
            DataInputStream inputstream = null;
            try {
                serverSocket = new ServerSocket(8988);
                client = serverSocket.accept();
                inputstream = new DataInputStream(client.getInputStream());
                String str = inputstream.readUTF();
                serverSocket.close();
                return str;
            } catch (IOException e) {
                Log.e(ConnectActivity.TAG, e.getMessage());
                return null;
            }finally{
                if(inputstream != null){
                    try{
                        inputstream.close();
                    } catch (IOException e) {
                        Log.e(ConnectActivity.TAG, e.getMessage());
                    }
                }
                if(client != null){
                    try{
                        client.close();
                    } catch (IOException e) {
                        Log.e(ConnectActivity.TAG, e.getMessage());
                    }
                }
                if(serverSocket != null){
                    try{
                        serverSocket.close();
                    } catch (IOException e) {
                        Log.e(ConnectActivity.TAG, e.getMessage());
                    }
                }
            }
        }
        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();;
                outputText = result;
            }

        }

        /*
         * (non-Javadoc)
         * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
         */


        /*
         * (non-Javadoc)
         * @see android.os.AsyncTask#onPreExecute()
         */
        @Override
        protected void onPreExecute() {

        }

    }

    public static boolean copyFile(InputStream inputStream, OutputStream out) {
        byte buf[] = new byte[1024];
        int len;
        long startTime = System.currentTimeMillis();

        try {
            while ((len = inputStream.read(buf)) != -1) {
                out.write(buf, 0, len);
            }
            out.close();
            inputStream.close();
            long endTime = System.currentTimeMillis() - startTime;
            Log.v("", "Time taken to transfer all bytes is : " + endTime);

        } catch (IOException e) {
            Log.d("exp", e.toString());
            return false;
        }
        return true;
    }

    @Override
    public void onConnectionInfoAvailable(WifiP2pInfo info) {
        // TODO Auto-generated method stub

        Toast.makeText(getApplicationContext(), "connectioninfoo", Toast.LENGTH_LONG).show();

    }
}