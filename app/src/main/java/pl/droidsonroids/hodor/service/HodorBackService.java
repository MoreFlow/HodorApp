package pl.droidsonroids.hodor.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import pl.droidsonroids.hodor.Constants;
import pl.droidsonroids.hodor.HodorApplication;
import pl.droidsonroids.hodor.HodorPreferences;

public class HodorBackService extends IntentService {

    public HodorBackService() {
        super("");
    }

    public HodorBackService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String token = intent.getStringExtra("token");
        Log.d("TOKENEN", token);
        HodorApplication.getInstance().getRestAdapter().sendPush(token, new HodorPreferences(getApplicationContext()).getUsername());
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(Constants.NOTIFICATION_ID);
    }
}
