package com.creation.android.folkapp2019.fragments.fragments_talents;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ToggleButton;

import com.creation.android.folkapp2019.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class Graphics extends Fragment {

    private String phone;

    private Button college_movie,district_movie,state_movie,national_movie,international_movie;
    private Button college_photography,district_photography,state_photography,national_photography,international_photography;
    private Button college_poster,district_poster,state_poster,national_poster,international_poster;
    private Button college_video,district_video,state_video,national_video,international_video;
    private Button college_animation,district_animation,state_animation,national_animation,international_animation;

    private ToggleButton toggleButton_movie, toggleButton_photography, toggleButton_poster, toggleButton_video, toggleButton_animation;

    public Graphics() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_talents_graphics, container, false);

        college_movie = view.findViewById(R.id.college_movie);
        district_movie = view.findViewById(R.id.District_movie);
        state_movie = view.findViewById(R.id.State_movie);
        national_movie = view.findViewById(R.id.national_movie);
        international_movie = view.findViewById(R.id.International_movie);
        toggleButton_movie = view.findViewById(R.id.select_movie);

        college_photography = view.findViewById(R.id.college_photography);
        district_photography = view.findViewById(R.id.District_photography);
        state_photography = view.findViewById(R.id.State_photography);
        national_photography = view.findViewById(R.id.national_photography);
        international_photography = view.findViewById(R.id.International_photography);
        toggleButton_photography = view.findViewById(R.id.select_photography);

        college_poster = view.findViewById(R.id.college_poster);
        district_poster = view.findViewById(R.id.District_poster);
        state_poster= view.findViewById(R.id.State_poster);
        national_poster = view.findViewById(R.id.national_poster);
        international_poster = view.findViewById(R.id.International_poster);
        toggleButton_poster = view.findViewById(R.id.select_poster);

        college_video = view.findViewById(R.id.college_video);
        district_video = view.findViewById(R.id.District_video);
        state_video = view.findViewById(R.id.State_video);
        national_video = view.findViewById(R.id.national_video);
        international_video = view.findViewById(R.id.International_video);
        toggleButton_video = view.findViewById(R.id.select_video);

        college_animation = view.findViewById(R.id.college_animation);
        district_animation = view.findViewById(R.id.District_animation);
        state_animation = view.findViewById(R.id.State_animation);
        national_animation = view.findViewById(R.id.national_animation);
        international_animation = view.findViewById(R.id.International_animation);
        toggleButton_animation = view.findViewById(R.id.select_animation);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        phone = user.getPhoneNumber().substring(3);

        FirebaseFirestore db =  FirebaseFirestore.getInstance();
        CollectionReference ProfileCollection = db.collection("Profile");

        ProfileCollection
                .whereEqualTo("mobile",phone)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){

                            //_______________short movie making__________________
                            if(documentSnapshot.get("talent.graphics_talent.skills.short_movie_making")!=null) {
                                toggleButton_movie.setChecked(true);
                                if (documentSnapshot.get("talent.graphics_talent.participation.college_level") != null) {
                                    college_movie.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.graphics_talent.participation.district_level") != null) {
                                    district_movie.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.graphics_talent.participation.state_level") != null) {
                                    state_movie.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.graphics_talent.participation.national_level") != null) {
                                    national_movie.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.graphics_talent.participation.international_level") != null) {
                                    international_movie.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }

                            //_______________photography__________________
                            if(documentSnapshot.get("talent.graphics_talent.skills.photography")!=null) {
                                toggleButton_photography.setChecked(true);
                                if (documentSnapshot.get("talent.graphics_talent.participation.college_level") != null) {
                                    college_photography.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.graphics_talent.participation.district_level") != null) {
                                    district_photography.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.graphics_talent.participation.state_level") != null) {
                                    state_photography.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.graphics_talent.participation.national_level") != null) {
                                    national_photography.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.graphics_talent.participation.international_level") != null) {
                                    international_photography.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }

                            //_______________poster making__________________
                            if(documentSnapshot.get("talent.graphics_talent.skills.poster_making")!=null) {
                                toggleButton_poster.setChecked(true);
                                if (documentSnapshot.get("talent.graphics_talent.participation.college_level") != null) {
                                    college_poster.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.graphics_talent.participation.district_level") != null) {
                                    district_poster.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.graphics_talent.participation.state_level") != null) {
                                    state_poster.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.graphics_talent.participation.national_level") != null) {
                                    national_poster.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.graphics_talent.participation.international_level") != null) {
                                    international_poster.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }

                            //_______________video editing__________________
                            if(documentSnapshot.get("talent.graphics_talent.skills.video_editing")!=null) {
                                toggleButton_video.setChecked(true);
                                if (documentSnapshot.get("talent.graphics_talent.participation.college_level") != null) {
                                    college_video.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.graphics_talent.participation.district_level") != null) {
                                    district_video.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.graphics_talent.participation.state_level") != null) {
                                    state_video.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.graphics_talent.participation.national_level") != null) {
                                    national_video.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.graphics_talent.participation.international_level") != null) {
                                    international_video.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }

                            //_______________animation__________________
                            if(documentSnapshot.get("talent.graphics_talent.skills.animation")!=null) {
                                toggleButton_animation.setChecked(true);
                                if (documentSnapshot.get("talent.graphics_talent.participation.college_level") != null) {
                                    college_animation.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.graphics_talent.participation.district_level") != null) {
                                    district_animation.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.graphics_talent.participation.state_level") != null) {
                                    state_animation.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.graphics_talent.participation.national_level") != null) {
                                    national_animation.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.graphics_talent.participation.international_level") != null) {
                                    international_animation.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }
                        }
                    }
                });



        return view;
    }
}
