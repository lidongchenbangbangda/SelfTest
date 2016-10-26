package com.example.ldc.selftest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Environment;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button play,stop;
    private Intent intent;
    private MyServiceConn conn;
    private PalyService.MyService myService;
    private String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()+"/yanyuan.mp3";
        play = (Button) findViewById(R.id.btn_play);
        stop= (Button) findViewById(R.id.btn_stop);
        play.setOnClickListener(this);
        stop.setOnClickListener(this);
        intent=new Intent(this,PalyService.class);
        conn=new MyServiceConn();
        bindService(intent,conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_play:
                myService.doPlay(path);
                break;
            case R.id.btn_stop:
                myService.doStop();
                break;
        }
    }

    class MyServiceConn implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myService= (PalyService.MyService) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }
}
