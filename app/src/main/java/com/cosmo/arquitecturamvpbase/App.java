package com.cosmo.arquitecturamvpbase;

import android.app.Application;
import android.content.IntentFilter;

import com.cosmo.arquitecturamvpbase.helper.DataBase;
import com.cosmo.arquitecturamvpbase.receivers.NetworkStateReceiver;
import com.cosmo.arquitecturamvpbase.synchronizer.Synchronizer;

/**
 * Created by victorhugosernasuarez on 30/9/17.
 */

public class App extends Application {

    private final NetworkStateReceiver NETWORK_STATE_RECEIVER = new NetworkStateReceiver();
    public static DataBase mdb;

    @Override
    public void onCreate() {
        super.onCreate();
        initDataBase();
        registerNetworkReceiver();
    }

    private void registerNetworkReceiver(){
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(NETWORK_STATE_RECEIVER, filter);
    }

    private void initDataBase() {
        mdb = new DataBase(this);
        mdb.open();
    }

    @Override
    public void onTerminate() {
        mdb.close();
        super.onTerminate();
    }

    public void onNetworkStatedChanged(boolean isConnected) {
        Synchronizer.getInstance().executeSyncLocalToServer(isConnected);

    }
}
