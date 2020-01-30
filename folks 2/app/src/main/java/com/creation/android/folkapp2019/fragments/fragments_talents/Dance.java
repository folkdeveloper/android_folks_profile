package com.creation.android.folkapp2019.fragments.fragments_talents;

import android.content.Context;
import android.net.Uri;
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


public class Dance extends Fragment {

    private String phone;

    private Button college_freestyle,district_freestyle,state_freestyle,national_freestyle,international_freestyle;
    private Button college_western,district_western,state_western,national_western,international_western;
    private Button college_classical,district_classical,state_classical,national_classical,international_classical;

    private ToggleButton toggleButton_freestyle, toggleButton_western, toggleButton_classical;

    public Dance() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_talents_dance, container, false);

        college_freestyle = view.findViewById(R.id.college_freestyle);
        district_freestyle = view.findViewById(R.id.District_freestyle);
        state_freestyle = view.findViewById(R.id.State_freestyle);
        national_freestyle = view.findViewById(R.id.national_freestyle);
        international_freestyle = view.findViewById(R.id.International_freestyle);
        toggleButton_freestyle = view.findViewById(R.id.select_freestyle);

        college_western = view.findViewById(R.id.college_western);
        district_western = view.findViewById(R.id.District_western);
        state_western = view.findViewById(R.id.State_western);
        national_western = view.findViewById(R.id.national_western);
        international_western = view.findViewById(R.id.International_western);
        toggleButton_western = view.findViewById(R.id.select_western);

        college_classical = view.findViewById(R.id.college_classical);
        district_classical = view.findViewById(R.id.District_classical);
        state_classical = view.findViewById(R.id.State_classical);
        national_classical = view.findViewById(R.id.national_classical);
        international_classical = view.findViewById(R.id.International_classical);
        toggleButton_classical = view.findViewById(R.id.select_classical);


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

                            //_______________freestyle__________________
                            if(documentSnapshot.get("talent.dance_talent.skills.free_style")!=null) {
                                toggleButton_freestyle.setChecked(true);
                                if (documentSnapshot.get("talent.dance_talent.participation.college_level") != null) {
                                    college_freestyle.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.dance_talent.participation.district_level") != null) {
                                    district_freestyle.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.dance_talent.participation.state_level") != null) {
                                    state_freestyle.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.dance_talent.participation.national_level") != null) {
                                    national_freestyle.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.dance_talent.participation.international_level") != null) {
                                    international_freestyle.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }

                            //_______________western__________________
                            if(documentSnapshot.get("talent.dance_talent.skills.western")!=null) {
                                toggleButton_western.setChecked(true);
                                if (documentSnapshot.get("talent.dance_talent.participation.college_level") != null) {
                                    college_western.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.dance_talent.participation.district_level") != null) {
                                    district_western.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.dance_talent.participation.state_level") != null) {
                                    state_western.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.dance_talent.participation.national_level") != null) {
                                    national_western.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.dance_talent.participation.international_level") != null) {
                                    international_western.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }

                            //_______________classical__________________
                            if(documentSnapshot.get("talent.dance_talent.skills.classical_dance_(indian)")!=null) {
                                toggleButton_classical.setChecked(true);
                                if (documentSnapshot.get("talent.dance_talent.participation.college_level") != null) {
                                    college_classical.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.dance_talent.participation.district_level") != null) {
                                    district_classical.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.dance_talent.participation.state_level") != null) {
                                    state_classical.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.dance_talent.participation.national_level") != null) {
                                    national_classical.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.dance_talent.participation.international_level") != null) {
                                    international_classical.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }
                        }
                    }
                });


        return view;
    }
}
