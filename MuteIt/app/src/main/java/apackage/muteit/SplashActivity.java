package apackage.muteit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by diogomartins on 15-12-2016.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent startIntent = new Intent(SplashActivity.this, ForegroundService.class);
        startService(startIntent);
        Intent intent = new Intent(this, MainMenu.class);
        intent.putExtra("from","Splash");
        startActivity(intent);
        finish();
    }
}