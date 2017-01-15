package apackage.muteit;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class creditosListaNomes extends AppCompatActivity{
    ArrayList<Evento> aux;
    //*********************Menu Bar*********************

    private ListView mdrawerList;
    private ArrayAdapter<String> mdrawerAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String ActivityTitle;


    private void addDrawerItems() {
        String[] list = {"Adicionar Evento", "Agenda", "Estatisticas", "Créditos"};
        mdrawerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        mdrawerList.setAdapter(mdrawerAdapter);
        mdrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent intent = new Intent(creditosListaNomes.this, AddEvent.class);
                    intent.putExtra("listaEventos",aux);
                    startActivity(intent);
                }else if(position==1){
                    Intent intent = new Intent(creditosListaNomes.this,ListEvents.class);
                    intent.putExtra("listaEventos",aux);
                    startActivity(intent);
                }
                else if(position==3){
                    Intent intent = new Intent(creditosListaNomes.this,creditosListaNomes.class);
                    intent.putExtra("listaEventos",aux);
                    startActivity(intent);
                }else {
                    Toast.makeText(creditosListaNomes.this, "Coming Soon!!", Toast.LENGTH_SHORT).show();
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
        getMenuInflater().inflate(R.menu.menustandard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

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
        inflater.inflate(R.menu.menustandard, menu);
        super.onPrepareOptionsMenu(menu);
        return true;
    }






    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creditos);
        mdrawerList = (ListView) findViewById(R.id.lista);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActivityTitle = getTitle().toString();
        addDrawerItems();
        setupDrawer();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        aux = (ArrayList<Evento>)getIntent().getExtras().get("listaEventos");
        //Getting hanle of list
        ListView list = (ListView) findViewById(R.id.listAutores);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        adapter.add("Carolina Rosa");
        adapter.add("Diogo Martins");
        adapter.add("Gabriel Cardoso");
        adapter.add("Inês Lopes");
        adapter.add("Miguel Arieiro");
        adapter.add("Sofia Oliveira");

        list.setAdapter(adapter);

    }



}