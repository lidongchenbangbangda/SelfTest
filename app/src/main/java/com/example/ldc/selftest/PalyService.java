package com.example.ldc.selftest;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Ldc on 2016/10/17.
 */
public class PalyService extends Service{

    private MediaPlayer player;
    private MyService myService;
    private int current_state;
    private int null_state=1;
    private int playing_state=2;
    private int pause=3;

    @Override
    public void onCreate() {
        super.onCreate();
        player=new MediaPlayer();
        myService=new MyService();
        current_state=null_state;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myService;
    }



    @Override
    public void onDestroy() {
        if(player.isPlaying()){
            stop();
            player.release();
            player=null;
        }
        super.onDestroy();
    }

    public void play(String path){
        Log.e("playService", "play: +current_state"+current_state );

        //第一次进入，播放
        if(current_state ==null_state){
            Log.e("playService", "1 " );
            try {
                player.reset();
                player.setDataSource(path);
                player.prepare();
                player.start();
                current_state =playing_state;
            } catch (IOException e) {
                e.printStackTrace();
            }
            //正在播放-->暂停
        }else if(current_state ==playing_state){
            Log.e("playService", "2" );
            player.pause();
            current_state =pause;
            //正在暂停-->播放
        }else if(current_state ==pause){
            Log.e("playService", "3 " );
            player.start();
            current_state =playing_state;
        }

    }

    public void stop(){
        Log.e("playService", "stop: " );
        player.stop();
        current_state =null_state;
    }
    class MyService extends Binder{
        public void doPlay(String path){
            play(path);
        }
        public void doStop(){
            stop();
        }
    }
}
