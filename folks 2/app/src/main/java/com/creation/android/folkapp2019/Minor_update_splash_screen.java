package com.creation.android.folkapp2019;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Minor_update_splash_screen extends AppCompatActivity {

    private static int SPLASH_TIME = 2000;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_minor_update);

        final String versionName = BuildConfig.VERSION_NAME;
        final TextView textView = findViewById(R.id.currentVersion);

        DocumentReference VersionDoc = db.document("Folkapp/version");
        final String sdk = String.valueOf(Build.VERSION.SDK_INT).trim();



        VersionDoc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.get("android_temp."+sdk)!=null) {
                    String version_available = documentSnapshot.getString("android_temp."+sdk);

                    if(versionName.equals(version_available)){
                        Intent mainIntent = new Intent(Minor_update_splash_screen.this, LoginActivity.class);
                        Minor_update_splash_screen.this.startActivity(mainIntent);
                        Minor_update_splash_screen.this.finish();
                    }
                    else{
                        final AlertDialog dialog = new AlertDialog.Builder(Minor_update_splash_screen.this)
                                .setTitle("Minor Update available!")
                                .setMessage("Version "+version_available+" is Available")
                                .setIcon(R.drawable.ic_update_24px)
                                .setNegativeButton("Remind me later", null)
                                .setPositiveButton("Update", null)
                                .show();

                        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        positiveButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent mainIntent = new Intent(Minor_update_splash_screen.this, LoginActivity.class);
                                Minor_update_splash_screen.this.startActivity(mainIntent);
                                Minor_update_splash_screen.this.finish();
                                dialog.dismiss();
                            }
                        });

                        Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                        negativeButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent mainIntent = new Intent(Minor_update_splash_screen.this, LoginActivity.class);
                                Minor_update_splash_screen.this.startActivity(mainIntent);
                                Minor_update_splash_screen.this.finish();
                                dialog.dismiss();
                            }
                        });
                    }
                }
                else {
                }
            }
        });
    }
}
