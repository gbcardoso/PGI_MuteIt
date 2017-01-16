package apackage.muteit;

/**
 * Created by Miguel on 15/01/2017.
 */

import android.app.Service;
import android.content.Context;
import android.media.AudioManager;
import android.content.Context;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;

public class ProfileUpdater extends Thread{

    AudioManager audioManager;
    int def;
    ProfileUpdater(){
        audioManager = (AudioManager) BootUpReceiver.ctx.getSystemService(Context.AUDIO_SERVICE);
        audioManager.getRingerMode();
    }

    public void run() {
        while(true){
            System.out.println("Thread: Hi");
            update ();
            try {
                sleep(15000);
            }catch (InterruptedException e) {
                // Restore interrupt status.
                Thread.currentThread().interrupt();
            }
        }
    }

    private int currentEventProfile(){
        ArrayList<Evento> aux = (ArrayList<Evento>) MainMenu.ReadArrayListFromSD(BootUpReceiver.ctx,"MuteItEventos");
        Date current= new Date();
        for (Evento e: aux) {
            if (!e.data_start.after(current)&&e.data_end.after(current)){
                return e.profile;
            }
        }
        return 3;
    }

    public void update(){
        int profile=currentEventProfile();
        if (profile==0){
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        }else if (profile==1){
            audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        }else if (profile==2){
            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        }else{
            audioManager.setRingerMode(def);
        }
    }
}
