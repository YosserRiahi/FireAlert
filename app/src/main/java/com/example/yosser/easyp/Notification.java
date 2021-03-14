package com.example.yosser.easyp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class Notification extends AppCompatActivity {
    // private TextView value;
    private Firebase fb;


    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


        fb = new Firebase("https://parking-2dd90.firebaseio.com/comp");
        fb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String comp = (String) dataSnapshot.getValue();
                int result = Integer.parseInt(comp);
                //boolean b = Boolean.parseBoolean(result);
                boolean bool = (result == 1);

                if (bool==true) {
                    addNotification();
                }

            }
            @Override
            public void onCancelled(FirebaseError firebaseError) { }
        });


        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id ==R.id.help)
                {
                   // Intent j=new Intent(Notification.this,Help.class);
                    ///startActivity(j);
                    addNotification();
                }
                if (id ==R.id.out)
                {
                    Intent k=new Intent(Notification.this,acceuil.class);
                    startActivity(k);
                }

                switch(id)
                {
                    case R.id.compte:
                        Intent i=new Intent(Notification.this,Account.class);
                        startActivity(i);

                    default:
                        return true;
                }




            }
        });


    }





    private void addNotification() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR_0_1)
        {   NotificationChannel channel=
                new NotificationChannel("mynotification","mynotification",NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
            PendingIntent contentIntent =
                    PendingIntent.getActivity(this, 0, new Intent(this,Notification.class), 0);
        }
        NotificationCompat.Builder builder= new NotificationCompat.Builder(this,"mynotification")
                .setContentTitle("Alert")
                .setSmallIcon(R.drawable.launcher)
                .setAutoCancel(true)
                .setOngoing(true)
                .setContentText("WARNING: there is fire in the building !!");
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(uri);
        long[] v = {80, 260, 80};
        builder.setVibrate(v);
        builder.setColor(255);
        builder.setWhen(System.currentTimeMillis());
        NotificationManagerCompat manager= NotificationManagerCompat.from(this);
        manager.notify(999,builder.build());
    }


}
