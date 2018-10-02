package id.web.wfebriadi.submissionkamus;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppPreference {
    SharedPreferences prefs;
    Context context;

    public AppPreference(Context context){
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }
    public void setFirstRun(Boolean input){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("App first run", input);
        editor.commit();
    }
    public Boolean getFirstRun(){
        return prefs.getBoolean("App first Run", true);
    }
}
