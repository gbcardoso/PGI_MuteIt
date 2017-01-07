package apackage.muteit;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;

public class ForegroundService extends Service {

    static ForegroundService instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        if (startService(new Intent(this, ForegroundEnablingService.class)) == null)
            throw new RuntimeException("Couldn't find " + ForegroundEnablingService.class.getSimpleName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        instance = null;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
