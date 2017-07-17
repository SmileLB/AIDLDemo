package com.example.smile.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.smile.mydemo.IMyAidlInterface;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText mNum1;
    private EditText mNum2;
    private EditText mNum3;
    private Button btn;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //回收资源
            mIMyAidlInterface = null;
        }
    };
    private IMyAidlInterface mIMyAidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        bindService();
    }

    private void initView() {
        mNum1 = (EditText) findViewById(R.id.et_num1);
        mNum2 = (EditText) findViewById(R.id.et_num2);
        mNum3 = (EditText) findViewById(R.id.et_num3);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int num1 = Integer.parseInt(mNum1.getText().toString());
        int num2 = Integer.parseInt(mNum2.getText().toString());

        try {

            int add = mIMyAidlInterface.add(num1, num2);
            mNum3.setText(add + "");

        } catch (RemoteException e) {
            e.printStackTrace();
            mNum3.setText("错误");
        }
    }

    private void bindService() {
        //获取到服务端
        Intent intent = new Intent();
        //新版本必须显示Intent启动绑定服务
        intent.setComponent(new ComponentName("com.example.smile.mydemo", "com.example.smile.mydemo.IRemoteService"));
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }
}
