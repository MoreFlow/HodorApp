package pl.droidsonroids.hodor.service;

import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class HodorMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Toast.makeText(HodorMessagingService.this, "HODORED by " + remoteMessage.getFrom(), Toast.LENGTH_SHORT).show();
    }
}
