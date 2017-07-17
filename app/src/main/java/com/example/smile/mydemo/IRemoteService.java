package com.example.smile.mydemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Description:
 * Created by LiBing
 * Data:2017/7/17 16:52
 */

public class IRemoteService extends Service {
    /**
     * 当客户端绑定到该服务的时候调用
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mIBinder;
    }

    private IBinder mIBinder = new IMyAidlInterface.Stub() {

        @Override
        public int add(int num1, int num2) throws RemoteException {
            return num1 + num2;
        }
    };
}
