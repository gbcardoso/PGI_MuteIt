package apackage.muteit;

import android.app.Activity;
import android.app.usage.UsageEvents;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.api.services.calendar.model.Event;
import com.google.common.collect.ArrayListMultimap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diogomartins on 16-12-2016.
 */

public class ListEvents extends AppCompatActivity {

    ArrayList <Evento> aux ;
    ArrayList <String> eventlist;
    ListView viewList;
    ArrayAdapter<String> adapter;
    static final int GET_EVENTS=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Create","ListEvents");
        viewList=(ListView)findViewById(R.id.list_events_view);
        setContentView(R.layout.list_events);

        if(getIntent().getExtras().getString("from").equals("CalendarObj")){
            Log.d("ENTER","INTENT RECIEVED");
            aux=(ArrayList)getIntent().getSerializableExtra("auxresult");
            ArrayList<String>eventlist=new ArrayList<>();
            for(Evento e:aux){
                String[]datainicio=e.data_start.toString().split("T");
                String[]datafim=e.data_end.toString().split("T");

                datainicio[1]=datainicio[1].substring(0,datainicio[1].length()-8);
                String auxano=datainicio[0].substring(0,4);
                String auxmes=datainicio[0].substring(5,7);
                String auxdia=datainicio[0].substring(8,datainicio[0].length());
                datainicio[0]=""+auxdia+"-"+auxmes+"-"+auxano;
                auxano=datafim[0].substring(0,4);
                auxmes=datafim[0].substring(5,7);
                auxdia=datafim[0].substring(8,datainicio[0].length());
                datafim[0]=""+auxdia+"-"+auxmes+"-"+auxano;

                datafim[1]=datafim[1].substring(0,datafim[1].length()-8);

                eventlist.add(""+e.name+"\n"+datainicio[0]+" "+datainicio[1]+" - "+datafim[0]+" "+datafim[1]);
            }
            setContentView(R.layout.list_events);
            addDrawerItems(eventlist);
        }
        else{
            Intent intent=new Intent (this,CalendarObj.class);
            intent.putExtra("from","Agenda");
            startActivity(intent);
            finish();

        }
    }

    private void addDrawerItems(ArrayList<String> eventlist) {
        viewList=(ListView)findViewById(R.id.list_events_view);
        viewList.setFocusableInTouchMode(false);
        viewList.setFocusable(false);
        viewList.setSelector(android.R.color.transparent);
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, eventlist){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text color of TextView (ListView Item)
                tv.setTextColor(Color.BLACK);

                // Generate ListView Item using TextView
                return view;
            }
        };
        viewList.setAdapter(adapter);

    }
}
