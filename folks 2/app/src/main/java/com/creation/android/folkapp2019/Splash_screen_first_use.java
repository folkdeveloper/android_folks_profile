package com.creation.android.folkapp2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Splash_screen_first_use extends AppCompatActivity {

    SharedPreferences prefs = null;
    Button Skip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_first_use);

        prefs = getSharedPreferences("com.Folk.FolkApp", MODE_PRIVATE);
        Skip = findViewById(R.id.skip);

        if (prefs.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            Toast.makeText(this, "First time ", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent mainIntent = new Intent(Splash_screen_first_use.this, Splash_screen.class);
                    Splash_screen_first_use.this.startActivity(mainIntent);
                    Splash_screen_first_use.this.finish();
                }
            }, 6000);
            prefs.edit().putBoolean("firstrun", false).apply();
        }
        else{
            Intent mainIntent = new Intent(Splash_screen_first_use.this, Splash_screen.class);
            Splash_screen_first_use.this.startActivity(mainIntent);
            Splash_screen_first_use.this.finish();
        }
        Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(Splash_screen_first_use.this, Splash_screen.class);
                Splash_screen_first_use.this.startActivity(mainIntent);
                Splash_screen_first_use.this.finish();
            }
        });
    }
}
