package apackage.muteit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
    }

    public void addEvent(View view) {
        Intent intent = new Intent(this, AddEvent.class);
        startActivity(intent);
    }

    public void agenda(View view){
        Intent intent = new Intent(this, CalendarObj.class);
        intent.putExtra("from","Agenda");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    public void goCredtis(View view){
        Intent intent = new Intent(this,creditosListaNomes.class);
        startActivity(intent);
    }

}
