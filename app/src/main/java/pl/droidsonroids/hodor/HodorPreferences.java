package pl.droidsonroids.hodor;

import android.content.Context;
import android.content.SharedPreferences;

public class HodorPreferences {

    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public HodorPreferences(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(Constants.PREFS_NAME_USERNAME, Context.MODE_PRIVATE);
    }

    public void setUsername(String username) {
        editor = sharedPreferences.edit();
        editor.putString(Constants.PREFS_USERNAME, username);
        editor.apply();
    }

    public String getUsername() {
        return sharedPreferences.getString(Constants.PREFS_USERNAME, null);
    }
}