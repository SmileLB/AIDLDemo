package com.example.smile.mydemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Created by LiBing
 * Data:2017/7/17 16:52
 */

public class IRemoteService extends Service {

    private ArrayList<Person> persons;

    /**
     * 当客户端绑定到该服务的时候调用
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        persons = new ArrayList<>();
        return mIBinder;
    }

    private IBinder mIBinder = new IMyAidlInterface.Stub() {

        @Override
        public List<Person> add(Person person) throws RemoteException {
            persons.add(person);
            return persons;
        }
    };
}
