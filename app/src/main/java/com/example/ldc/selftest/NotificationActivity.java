package com.example.ldc.selftest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;

/**
 * Created by Ldc on 2016/10/25.
 *
 * 1>实例化一个NotificationCompat.Builder对象;如builder
 * 2>调用builder的相关方法对notification进行上面提到的各种设置
 * 3>调用builder.build()方法此方法返回一个notification对象。
 * 4>实例化一个NotificationManager对象；如：manager
 * 5>调用manager的notify方法。
 */
public class NotificationActivity extends Activity {

    private NotificationManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificationdemo1);
        manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }
    public void showNotification1(View view){
        //创建一个notification，需要使用NotificationCompatBuilder，需要导入v4包
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        //设置小图标，在状态栏上面的那个，必须设置否则会抛出异常
        builder.setSmallIcon(R.mipmap.ic_launcher)//图标
                .setContentTitle("标题")//标题
                .setContentText("我是通知");//内容
        //可选属性
        //设置通知默认的状态，包括：声音，震动，呼吸灯
        //使用DEFAULT_VIBRATE需要设置权限
        // 可选值NotificationCompat.DEFAULT_xxx
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setTicker("this is a tongzhi tongzhi tongzhi ")
                .setContentInfo("这是什么 这是什么")
                .setAutoCancel(true).setNumber(5);

        //创建通知
        Notification notification=builder.build();
        //通过NotificationManager发送通知
        //NotificationManagerCompat managerCompat=NotificationManagerCompat.from(this);
        //managerCompat.notify(111,notification);
        manager.notify(111,notification);


    }
    public void cancleNotification(View view){

    }
}
