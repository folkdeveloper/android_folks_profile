package com.creation.android.folkapp2019.fragments.fragments_talents;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.creation.android.folkapp2019.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class Music extends Fragment {

    private Button college,district,state,national,international;
    private String phone;
    private EditText PracticingYears;
    private ToggleButton toggleButton;

    public Music() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_talents_music, container, false);

        college = view.findViewById(R.id.college);
        district = view.findViewById(R.id.District);
        state = view.findViewById(R.id.State);
        national = view.findViewById(R.id.national);
        international = view.findViewById(R.id.International);
        PracticingYears = view.findViewById(R.id.years_of_practice);
        toggleButton = view.findViewById(R.id.select_music);

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
                            if(documentSnapshot.get("talent.flags.music_vocals_talent")!=null) {
                                toggleButton.setChecked(true);
                                if (documentSnapshot.get("talent.music_vocals_talent.vocals_experience") != null) {
                                    PracticingYears.setText("Practicing for " + documentSnapshot.get("talent.music_vocals_talent.vocals_experience").toString());
                                }
                                if (documentSnapshot.get("talent.music_vocals_talent.participation.college_level") != null) {
                                    college.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_vocals_talent.participation.district_level") != null) {
                                    district.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_vocals_talent.participation.state_level") != null) {
                                    state.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_vocals_talent.participation.national_level") != null) {
                                    national.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_vocals_talent.participation.international_level") != null) {
                                    international.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }
                        }
                    }
                });

        return view;
    }

}
