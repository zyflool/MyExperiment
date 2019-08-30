package com.example.myexperiment.Notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RemoteViews;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myexperiment.R;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private RadioGroup mRadioGroup;
    private Button normalButton;
    private Button hangButton;
    private Button foldButton;
    private Notification.Builder builder;
    private Intent mIntent;
    private PendingIntent pendingIntent;
    private NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        init();
    }

    private void init () {
        mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/itachi85/"));
        builder = new Notification.Builder(this);
        pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.icon_add);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.icon_add));
        builder.setAutoCancel(true);

        mRadioGroup = findViewById(R.id.rg_notification);
        normalButton = findViewById(R.id.normal_notification);
        foldButton = findViewById(R.id.fold_notification);
        hangButton = findViewById(R.id.hang_notification);

        mRadioGroup.setOnCheckedChangeListener(this);

        normalButton.setOnClickListener(this);
        foldButton.setOnClickListener(this);
        hangButton.setOnClickListener(this);
    }

   /* private void selectNotofovatiomLevel(Notification.Builder builder) {
        switch (mRadioGroup.getCheckedRadioButtonId()) {
            case R.id.rb_public:
                builder.setVisibility(Notification.VISIBILITY_PUBLIC);
                builder.setContentText("public");
                break;
            case R.id.rb_private:
                builder.setVisibility(Notification.VISIBILITY_PRIVATE);
                builder.setContentText("private");
            case R.id.rb_secret:
                builder.setVisibility(Notification.VISIBILITY_SECRET);
                builder.setContentText("secret");
                break;
            default:
                builder.setVisibility(Notification.VISIBILITY_PUBLIC);
                builder.setContentText("public");
                break;
        }
    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.normal_notification:
                builder.setContentTitle("normal notification");
                Notification NormalNotification = builder.build();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    // 第三个参数表示通知的重要程度，默认则只在通知栏闪烁一下
                    NotificationChannel notificationChannel = new NotificationChannel("AppTestNotificationId", "AppTestNotificationName", NotificationManager.IMPORTANCE_DEFAULT);
                    // 注册通道，注册后除非卸载再安装否则不改变
                    mNotificationManager.createNotificationChannel(notificationChannel);
                    builder.setChannelId("AppTestNotificationId");
                }
                // 发出通知
                mNotificationManager.notify(1, NormalNotification);
                break;
            case R.id.fold_notification:
                RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.view_fold);
                builder.setContentTitle("folded notification");
                Notification FoldNotification = builder.build();
                FoldNotification.bigContentView = remoteViews;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    // 第三个参数表示通知的重要程度，默认则只在通知栏闪烁一下
                    NotificationChannel notificationChannel = new NotificationChannel("AppTestNotificationId", "AppTestNotificationName", NotificationManager.IMPORTANCE_DEFAULT);
                    // 注册通道，注册后除非卸载再安装否则不改变
                    mNotificationManager.createNotificationChannel(notificationChannel);
                    builder.setChannelId("AppTestNotificationId");
                }
                // 发出通知
                mNotificationManager.notify(2, FoldNotification);
                break;
            case R.id.hang_notification:
                builder.setContentTitle("hang notification");
                Intent hangIntent = new Intent();
                hangIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                hangIntent.setClass(this, NotificationActivity.class);
                PendingIntent hangPendingIntent = PendingIntent.getActivity(this, 0, hangIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                builder.setFullScreenIntent(hangPendingIntent, true);
                Notification HangNotification = builder.build();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    // 第三个参数表示通知的重要程度，默认则只在通知栏闪烁一下
                    NotificationChannel notificationChannel = new NotificationChannel("AppTestNotificationId", "AppTestNotificationName", NotificationManager.IMPORTANCE_DEFAULT);
                    // 注册通道，注册后除非卸载再安装否则不改变
                    mNotificationManager.createNotificationChannel(notificationChannel);
                    builder.setChannelId("AppTestNotificationId");
                }
                // 发出通知
                mNotificationManager.notify(3,HangNotification);
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_public:
                builder.setVisibility(Notification.VISIBILITY_PUBLIC);
                builder.setContentText("public");
                break;
            case R.id.rb_private:
                builder.setVisibility(Notification.VISIBILITY_PRIVATE);
                builder.setContentText("private");
            case R.id.rb_secret:
                builder.setVisibility(Notification.VISIBILITY_SECRET);
                builder.setContentText("secret");
                break;
            default:
                builder.setVisibility(Notification.VISIBILITY_PUBLIC);
                builder.setContentText("public");
                break;
        }
    }
}