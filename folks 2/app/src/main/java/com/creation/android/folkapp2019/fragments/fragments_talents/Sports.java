package com.creation.android.folkapp2019.fragments.fragments_talents;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.creation.android.folkapp2019.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class

Sports extends Fragment {

    private String phone;

    private Button college_sports,district_sports,state_sports,national_sports,international_sports;
    private EditText sportsView;

    private ToggleButton toggleButton_sports;

    public Sports() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_talents_sports, container, false);

        college_sports = view.findViewById(R.id.college_sports);
        district_sports = view.findViewById(R.id.District_sports);
        state_sports = view.findViewById(R.id.State_sports);
        national_sports = view.findViewById(R.id.national_sports);
        international_sports = view.findViewById(R.id.International_sports);
        toggleButton_sports = view.findViewById(R.id.select_sports);

        sportsView = view.findViewById(R.id.Sports_played);

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

                            //_______________sports__________________
                            if(documentSnapshot.get("talent.sports_talent")!=null) {
                                toggleButton_sports.setChecked(true);
                                if (documentSnapshot.get("talent.sports_talent.participation.college_level") != null) {
                                    college_sports.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.sports_talent.participation.district_level") != null) {
                                    district_sports.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.sports_talent.participation.state_level") != null) {
                                    state_sports.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.sports_talent.participation.national_level") != null) {
                                    national_sports.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.sports_talent.participation.international_level") != null) {
                                    international_sports.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }
                            String Sports = documentSnapshot.get("talent.sports_talent.skills").toString().trim().replaceAll("=true","").replaceAll("\\{","").replaceAll("\\}","").replaceAll(" ","");
                            String[] all_sports = Sports.split(",");

                            String Sports_played ="";
                            for(int i=0;i<all_sports.length;i++){
                                Sports_played += all_sports[i]+"\n";
                            }
                            sportsView.setText(Sports_played);

                            Log.d("TAG", "onSuccess: "+Sports_played);
                        }
                    }
                });
        return view;
    }
}
