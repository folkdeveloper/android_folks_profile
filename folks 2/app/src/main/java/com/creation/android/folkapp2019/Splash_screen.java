package com.creation.android.folkapp2019;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Splash_screen extends AppCompatActivity {

    private static int SPLASH_TIME = 3000; // 4 seconds
    private TextView image_text;
    private Button moreDetails;
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_login);

        final List<String> images = new ArrayList<>();
        final List<String> text = new ArrayList<>();
        final List<String> urls = new ArrayList<>();

        image_text = findViewById(R.id.image_text);
        moreDetails = findViewById(R.id.url);
        tabLayout = findViewById(R.id.tab_layout);

        String currentDateString = java.text.DateFormat.getDateInstance().format(new Date()).trim();

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String ddate = formatter.format(date);
        Date date1 = null;
        long time = 0;
        try {
            date1=new SimpleDateFormat("dd/MM/yyyy").parse(ddate);
            time = date1.getTime()/1000;
            Log.d("TAG", "onCreate: "+time);
        } catch (ParseException e) {
            e.printStackTrace();
            Intent mainIntent = new Intent(Splash_screen.this, MainActivity.class);
            Splash_screen.this.startActivity(mainIntent);
            Splash_screen.this.finish();
        }


        Log.d("TAG", "onCreate: akhefg");

        FirebaseFirestore db =  FirebaseFirestore.getInstance();
        CollectionReference imref = db.collection("Folkapp");

        imref.whereEqualTo("date",time).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){
                    if(documentSnapshot.get("image")!=null) {
                        images.add(documentSnapshot.get("image").toString().trim());
                    }
                    if(documentSnapshot.get("text")!=null){
                        text.add(documentSnapshot.get("text").toString().trim());
                    }
                    if(documentSnapshot.get("url")!=null){
                        urls.add(documentSnapshot.get("url").toString().trim());
                    }
                }
            }
        }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(images.size()==0) {
                    Intent mainIntent = new Intent(Splash_screen.this, MainActivity.class);
                    Splash_screen.this.startActivity(mainIntent);
                    Splash_screen.this.finish();
                }
                ViewPager viewPager = findViewById(R.id.view_pager);
                ViewPagerAdapter adapter = new ViewPagerAdapter(getApplicationContext(), images);
                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager,true);


                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(final int position, float positionOffset, int positionOffsetPixels) {
                        image_text.setText(text.get(position));
                        moreDetails.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urls.get(position)));
                                startActivity(browserIntent);
                            }
                        });
                        if(position==images.size()-1) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent mainIntent = new Intent(Splash_screen.this, MainActivity.class);
                                    Splash_screen.this.startActivity(mainIntent);
                                    Splash_screen.this.finish();
                                }
                            }, SPLASH_TIME);
                        }
                    }
                    @Override
                    public void onPageSelected(int position) {
                    }
                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                });
            }
        });
    }
}
