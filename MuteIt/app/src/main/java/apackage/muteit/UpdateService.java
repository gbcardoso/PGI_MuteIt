package apackage.muteit;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Miguel on 16/01/2017.
 */


public class UpdateService extends Service {



    static ProfileUpdater updater=null;

    @Override
    public void onCreate() {
        updater= new ProfileUpdater();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        updater.run();
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
