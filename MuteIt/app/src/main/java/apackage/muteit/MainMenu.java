package apackage.muteit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class MainMenu extends AppCompatActivity implements Serializable {
    ArrayList<Evento> listaEventos ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras != null) {
                if(extras.containsKey("listaEventos")) {
                    listaEventos = (ArrayList<Evento>) extras.get("listaEventos");
                    SaveArrayListToSD(getApplicationContext(),"MuteItEventos",listaEventos);
                }
                else {
                    listaEventos = ReadArrayListFromSD(getApplicationContext(),"MuteItEventos");
                }
            }
        }
    }

    public void addEvent(View view) {
        Intent intent = new Intent(this, AddEvent.class);
        intent.putExtra("listaEventos",listaEventos);
        startActivity(intent);
    }

    public void ListEvents(View view){
        Intent intent = new Intent(this,ListEvents.class);
        intent.putExtra("listaEventos",listaEventos);
        startActivity(intent);
    }

    public void goCredtis(View view){
        Intent intent = new Intent(this,creditosListaNomes.class);
        intent.putExtra("listaEventos",listaEventos);
        startActivity(intent);
    }

    public static void SaveArrayListToSD(Context mContext, String filename, ArrayList<Evento> list){
        try {

            FileOutputStream fos = mContext.openFileOutput(filename + ".dat", mContext.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Evento> ReadArrayListFromSD(Context mContext,String filename){
        ArrayList<Evento> obj = new ArrayList<>();
        try {
            FileInputStream fis = mContext.openFileInput(filename + ".dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            obj= (ArrayList<Evento>) ois.readObject();
            fis.close();
            return obj;

        } catch (Exception e) {
            e.printStackTrace();
            return obj;
        }
    }
}
