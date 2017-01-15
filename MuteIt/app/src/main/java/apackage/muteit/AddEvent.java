package apackage.muteit;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Configuration;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.api.client.util.DateTime;

import java.util.Date;

public class AddEvent extends AppCompatActivity {

//*****************Variable Declaration*****************
    ArrayList<Evento> aux;
    //*********************Menu Bar*********************

    private ListView mdrawerList;
    private ArrayAdapter<String> mdrawerAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String ActivityTitle;

    //*********************Time and Date Picker*********************
    private int mYear, mMonth, mDay, mHour, mMinute;
    CompoundButton ssilencio, ssom, svibrar;
    TextView dateini, dateend, timeinit, timeend;

    //*********************Add Event*********************
    String name=new String();String startTime=new String(); String endTime=new String(); String tag=new String();
    String startDate=new String(); String endDate=new String(); String totalstart=new String(); String totalend=new String();
    EditText nameText,hashtag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        mdrawerList = (ListView) findViewById(R.id.lista);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActivityTitle = getTitle().toString();
        addDrawerItems();
        setupDrawer();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        /**/
        aux = (ArrayList<Evento>)getIntent().getExtras().get("listaEventos");


        /*data butoes*/
        dateini = (TextView) findViewById(R.id.dateinit);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dfaux = new SimpleDateFormat("yyyy-MM-dd");
        dateini.setText(df.format(new Date()));
        startDate=dfaux.format(new Date());

        dateend = (TextView) findViewById(R.id.dateend);
        dateend.setText(df.format(new Date()));
        endDate=dfaux.format(new Date());
        DateFormat tf = new SimpleDateFormat("HH:mm");
        timeinit = (TextView) findViewById(R.id.hourinit);
        timeinit.setText(tf.format(new Date()));
        startTime="T"+timeinit.getText()+":00";
        timeend = (TextView) findViewById(R.id.hourend);
        timeend.setText(tf.format(new Date()));
        endTime="T"+timeend.getText()+":00";

        /*******************************/
        ssilencio = (CompoundButton) findViewById(R.id.ssilencio);
        ssom = (CompoundButton) findViewById(R.id.ssom);
        svibrar = (CompoundButton) findViewById(R.id.svibrar);
        nameText=(EditText) findViewById(R.id.nameText);
        hashtag=(EditText) findViewById(R.id.hash);
        ////*********************Listener Switches*********************

        CompoundButton.OnCheckedChangeListener multiListener = new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switch (buttonView.getId()) {
                    case R.id.ssom:
                        if (isChecked) {
                            ssilencio.setChecked(false);
                            svibrar.setChecked(false);
                        }
                        break;
                    case R.id.ssilencio:
                        if (isChecked) {
                            ssom.setChecked(false);
                            svibrar.setChecked(false);
                        }
                        break;
                    case R.id.svibrar:
                        if(isChecked) {
                            ssilencio.setChecked(false);
                            ssom.setChecked(false);
                        }
                        break;
                }
            }

        };

        //on each switch
        ((Switch) findViewById(R.id.ssilencio)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.ssom)).setOnCheckedChangeListener(multiListener);
        ((Switch) findViewById(R.id.svibrar)).setOnCheckedChangeListener(multiListener);


    }



   private void addDrawerItems() {
        String[] list = {"Adicionar Evento", "Agenda", "Estatisticas", "Créditos"};
        mdrawerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        mdrawerList.setAdapter(mdrawerAdapter);
        mdrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent intent = new Intent(AddEvent.this, AddEvent.class);
                    intent.putExtra("listaEventos",aux);
                    startActivity(intent);
                }else if(position==1){
                    Intent intent = new Intent(AddEvent.this,ListEvents.class);
                    intent.putExtra("listaEventos",aux);
                    startActivity(intent);
                }
                else if(position==3){
                    Intent intent = new Intent(AddEvent.this,creditosListaNomes.class);
                    intent.putExtra("listaEventos",aux);
                    startActivity(intent);
                }else {

                    Toast.makeText(AddEvent.this, "Coming Soon!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {


            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }


            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(ActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.clear();
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if(id==R.id.save){
            if(ssilencio.isChecked() || ssom.isChecked() || svibrar.isChecked()){
                Date startdate = new Date(Integer.parseInt((startDate.split("-")[0])),
                        Integer.parseInt(startDate.split("-")[1]),
                        Integer.parseInt(startDate.split("-")[2]),
                        Integer.parseInt(startTime.split("T")[1].split(":")[0]),
                        Integer.parseInt(startTime.split("T")[1].split(":")[1]));
                Date enddate = new Date(Integer.parseInt((endDate.split("-")[0])),
                        Integer.parseInt(endDate.split("-")[1]),
                        Integer.parseInt(endDate.split("-")[2]),
                        Integer.parseInt(endTime.split("T")[1].split(":")[0]),
                        Integer.parseInt(endTime.split("T")[1].split(":")[1]));
                if(enddate.after(startdate)) {
                    Toast.makeText(AddEvent.this, "Calendario " + startdate +"after "+enddate, Toast.LENGTH_SHORT).show();
                    create(startdate,enddate);

                }else{
                    Toast.makeText(AddEvent.this, "Valores de datas errados", Toast.LENGTH_SHORT).show();
                }




            }
            return true;
        }
        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        super.onPrepareOptionsMenu(menu);
        return true;
    }


    public void onClick(final View v) {

        if (v == dateini || v == dateend) {

// Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            final DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            if (v == dateini) {
                                dateini.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                startDate=year+"-"+(monthOfYear+1)+"-"+dayOfMonth;
                            } else {
                                dateend.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                endDate=year+"-"+(monthOfYear+1)+"-"+dayOfMonth;
                            }
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }


        if (v == timeinit || v == timeend) {

            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {
                            if (v == timeinit) {
                                if ((hourOfDay >= 0 && hourOfDay <= 9) && (minute >= 0 && minute <= 9))  {
                                    timeinit.setText("0" + hourOfDay + ":0" + minute);
                                    startTime = "T0" + hourOfDay + ":0" + minute + ":00";
                                }
                                else if((hourOfDay >= 0 && hourOfDay <= 9) && (minute >9)){
                                    timeinit.setText("0" + hourOfDay + ":" + minute);
                                    startTime = "T0" + hourOfDay + ":" + minute + ":00";
                                }
                                else if(hourOfDay>9 &&(minute>=0 &&minute<=9 )) {
                                    timeinit.setText("" + hourOfDay + ":0" + minute);
                                    startTime = "T" + hourOfDay + ":0" + minute + ":00";
                                }
                                else{
                                    timeinit.setText("" + hourOfDay + ":" + minute);
                                    startTime = "T" + hourOfDay + ":" + minute + ":00";
                                }

                            }

                            else{
                                if ((hourOfDay >= 0 && hourOfDay <= 9) && (minute >= 0 && minute <= 9))  {
                                    timeend.setText("0" + hourOfDay + ":0" + minute);
                                    endTime = "T0" + hourOfDay + ":0" + minute + ":00";
                                }
                                else if((hourOfDay >= 0 && hourOfDay <= 9) && (minute >9)){
                                    timeend.setText("0" + hourOfDay + ":" + minute);
                                    endTime = "T0" + hourOfDay + ":" + minute + ":00";
                                }
                                else if(hourOfDay>9 &&(minute>=0 &&minute<=9 )) {
                                    timeend.setText("" + hourOfDay + ":0" + minute);
                                    endTime = "T" + hourOfDay + ":0" + minute + ":00";
                                }
                                else{
                                    timeend.setText("" + hourOfDay + ":" + minute);
                                    endTime = "T" + hourOfDay + ":" + minute + ":00";
                                }

                            }
                        }
                    }, mHour, mMinute, true);
            timePickerDialog.show();
        }

    }

    public void create(Date startdate, Date enddate){
        int profile;
        name=nameText.getText().toString();
        tag = hashtag.getText().toString();
        if(ssilencio.isChecked())
            profile=0;
        else if(svibrar.isChecked())
            profile=1;
        else
            profile=2;
        Evento newEvento = new Evento(name,tag,startdate,enddate,profile);
        aux.add(newEvento);
        Intent i = new Intent(this, MainMenu.class);
        i.putExtra("listaEventos",aux);
        Toast.makeText(getApplicationContext(), "novo Evento criado com sucesso", Toast.LENGTH_SHORT).show();
        startActivity(i);
    }


}
