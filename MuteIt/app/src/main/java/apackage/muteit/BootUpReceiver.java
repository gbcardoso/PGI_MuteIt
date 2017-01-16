package apackage.muteit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Miguel on 16/01/2017.
 */



public class BootUpReceiver extends BroadcastReceiver {

    static Context ctx;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            System.out.println("Boot");
            Intent i = new Intent(context, UpdateService.class);
            ctx=context;
            context.startService(i);
        }
    }
}
