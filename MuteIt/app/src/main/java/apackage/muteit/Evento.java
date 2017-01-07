package apackage.muteit;

import android.media.AudioManager;

import com.google.api.client.util.DateTime;

import java.io.Serializable;
import java.util.Calendar;


/**
 * Created by diogomartins on 14-12-2016.
 */

public class Evento implements Serializable{

    String name;
    String hashtag;
    DateTime data_start;
    DateTime data_end;
    int profile;


    public Evento(String name, String hashtag, DateTime data_start, DateTime data_end, int profile){
        /*profile must be 1,2,3*/
        this.name = name;
        this.hashtag = hashtag;
        this.data_start = data_start;
        this.data_end = data_end;
        this.profile = profile;
    }

    /*public void compareEvento(Evento newe){
        *//*new event has more probability to be the one u atend
        * so if 2 events are overlapped, change the old one*//*
        if(this.data_end.compareTo(newe.data_start) == -1 )
            *//*old event ends before the new event starts*//*
            return;
        else if(this.data_start.compareTo(newe.data_end) == 1)
            *//*old event starts after the new event ends*//*
            return;
        else if(this.data_start.compareTo(newe.data_start) == -1  && this.data_end.compareTo(newe.data_start) == 1) {
        *//*new event starts in the middle of the old one
        * change the old event end date to *//*
            newe.data_start = this.data_end;
            return;
        }
        else if(this.data_start.compareTo(newe.data_end) == -1  && this.data_end.compareTo(newe.data_end) == 1) {
        *//*new event ends in the middle of the ond one
        * change the start data of the old to the end of the new*//*
            this.data_start = newe.data_end;
            return;
        }
     */
    //}



}
