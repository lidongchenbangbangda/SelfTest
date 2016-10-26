package com.example.ldc.selftest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ldc on 2016/10/25.
 */
public class NotificationUtil {

    private NotificationManager manager=null;
    private Map<Integer,Notification> mNotifications=null;
    private Context mContext;

    public void Notification(Context context){
        manager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotifications=new HashMap<Integer,Notification>();
        this.mContext=context;
    }

    public void showNotification(){
        //创建通知对象
        Notification notification=new Notification();
        //设置滚动文字
        notification.tickerText="今天是个好日子啊！";
        //设置显示时间
        notification.when=System.currentTimeMillis();
        //设置图标
        notification.icon=R.mipmap.ic_launcher;
        //设置通知特性
        notification.flags=Notification.FLAG_AUTO_CANCEL;
        //设置点击通知栏的操作
        Intent intent=new Intent(mContext,MainActivity.class);
        PendingIntent pIntent=PendingIntent.getActivity(mContext,0,intent,0);
        notification.contentIntent=pIntent;
        //创建RemoteView对象
        RemoteViews remoteview=new RemoteViews(mContext.getPackageName(),R.layout.notificationdome1);
        //设置111按钮的操作
        remoteview.setOnClickPendingIntent(R.id.btn1_notificationdome1,pIntent);

    }
}
