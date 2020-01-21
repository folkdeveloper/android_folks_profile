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


public class Update_splashScreen extends AppCompatActivity {

    private static int SPLASH_TIME = 2000;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_major_update);

        final String versionName = BuildConfig.VERSION_NAME;
        final TextView textView = findViewById(R.id.currentVersion);

        DocumentReference VersionDoc = db.document("Folkapp/version");
        final String sdk = String.valueOf(Build.VERSION.SDK_INT).trim();


        VersionDoc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.get("android."+sdk)!=null) {
                    String version_required = documentSnapshot.getString("android."+sdk);
                    String version_available = documentSnapshot.getString("android_temp."+sdk);

//                    if(versionName.equals(version_available)){
//                                Intent mainIntent = new Intent(Update_splashScreen.this, LoginActivity.class);
//                                Update_splashScreen.this.startActivity(mainIntent);
//                                Update_splashScreen.this.finish();
//                    }
                    if(versionName.equals(version_required)){
                        Intent mainIntent = new Intent(Update_splashScreen.this, Minor_update_splash_screen.class);
                        Update_splashScreen.this.startActivity(mainIntent);
                        Update_splashScreen.this.finish();
                    }
                    else{
                        final AlertDialog dialog = new AlertDialog.Builder(Update_splashScreen.this)
                                .setTitle("Important Update available!")
                                .setMessage("Minimum version "+version_required+" is Required")
                                .setIcon(R.drawable.ic_update_24px)
                                .setPositiveButton("Update", null)
                                .show();

                        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        positiveButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //update link
                                Intent mainIntent = new Intent(Update_splashScreen.this, Minor_update_splash_screen.class);
                                Update_splashScreen.this.startActivity(mainIntent);
                                Update_splashScreen.this.finish();
                                dialog.dismiss();
                            }
                        });
                    }
                }
            }
        });
    }
}
