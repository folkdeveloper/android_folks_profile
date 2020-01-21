package com.creation.android.folkapp2019;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Splash_screen extends AppCompatActivity {

    private static int SPLASH_TIME = 3000; // 4 seconds



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_login);

        final List<String> images = new ArrayList<>();
        final List<String> urls = new ArrayList<>();

//        images.add("@drawable/Folk_logo");
//        images.add("https://cdn.pixabay.com/photo/2016/11/11/23/34/cat-1817970_960_720.jpg");
//        images.add("https://cdn.pixabay.com/photo/2017/12/21/12/26/glowworm-3031704_960_720.jpg");
//        images.add("https://cdn.pixabay.com/photo/2017/11/07/00/07/fantasy-2925250_960_720.jpg");

        String currentDateString = java.text.DateFormat.getDateInstance().format(new Date()).trim();


        FirebaseFirestore db =  FirebaseFirestore.getInstance();
        CollectionReference imref = db.collection("Folkapp");

        imref.whereEqualTo("date",currentDateString).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){
                    if(documentSnapshot.get("image")!=null) {
                        urls.add(documentSnapshot.get("image").toString().trim());
                    }
                }
            }
        }).addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                images.addAll(urls);


                ViewPager viewPager = findViewById(R.id.view_pager);
                ViewPagerAdapter adapter = new ViewPagerAdapter(getApplicationContext(), images);
                viewPager.setAdapter(adapter);
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    }
                    @Override
                    public void onPageSelected(int position) {
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
                    public void onPageScrollStateChanged(int state) {
                    }
                });
            }
        });


    }
}
