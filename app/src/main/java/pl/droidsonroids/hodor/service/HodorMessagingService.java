package pl.droidsonroids.hodor.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import pl.droidsonroids.hodor.Constants;
import pl.droidsonroids.hodor.HodorApplication;
import pl.droidsonroids.hodor.R;
import pl.droidsonroids.hodor.model.FCMMessage;

public class HodorMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String username = remoteMessage.getData().get(FCMMessage.USERNAME);
        HodorApplication.getInstance().getDatabaseHelper().getUserFromDatabase(username, user -> {
            String token = user.getToken();

            Intent hodorBackService = new Intent(this, HodorBackService.class);
            hodorBackService.putExtra("token", token);
            PendingIntent pendingIntent =  PendingIntent.getService(this, 0, hodorBackService, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_notification)
                    .setContentTitle("HODOR!")
                    .setContentText(username)
                    .setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.hodor2))
                    .addAction(0, "HODOR back", pendingIntent);

            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(Constants.NOTIFICATION_ID, mBuilder.build());
        });

    }
}
