package apackage.muteit;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

/**
 * Created by diogomartins on 17-12-2016.
 */

public class ForegroundEnablingService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (ForegroundService.instance == null)
            throw new RuntimeException(ForegroundService.class.getSimpleName() + " not running");

        //Set both services to foreground using the same notification id, resulting in just one notification
        startForeground(ForegroundService.instance);
        startForeground(this);

        //Cancel this service's notification, resulting in zero notifications
        stopForeground(true);

        //Stop this service so we don't waste RAM.
        //Must only be called *after* doing the work or the notification won't be hidden.
        stopSelf();

        return START_NOT_STICKY;
    }

    private static final int NOTIFICATION_ID = 10;

    private static void startForeground(Service service) {
        Notification notification = new NotificationCompat.Builder(service).getNotification();
        service.startForeground(NOTIFICATION_ID, notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
