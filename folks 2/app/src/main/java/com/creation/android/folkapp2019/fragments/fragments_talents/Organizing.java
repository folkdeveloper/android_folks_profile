package com.creation.android.folkapp2019.fragments.fragments_talents;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class Organizing extends Fragment {

    private String phone;

    private ToggleButton CollegeFest,EventCoordination,Others;
    private EditText collegeEvents,eventEvents,otherEvents,othersOrganizing;

    public Organizing() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_talents_organizing, container, false);

        CollegeFest = view.findViewById(R.id.select_college_fest);
        EventCoordination = view.findViewById(R.id.select_event_coordination);
        Others = view.findViewById(R.id.select_organizing_others);

        collegeEvents = view.findViewById(R.id.no_events_college);
        eventEvents = view.findViewById(R.id.no_events_events);
        otherEvents = view.findViewById(R.id.no_events_others);
        othersOrganizing = view.findViewById(R.id.others_organize);


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

                            //_______________college_fest_organizing__________________
                            if(documentSnapshot.get("talent.organizing_talent.skills.college_fest_organizing")!=null) {
                                CollegeFest.setChecked(true);
                                if (documentSnapshot.get("talent.organizing_talent.event_organized")!= null) {
                                    collegeEvents.setText("Number od Events "+documentSnapshot.get("talent.organizing_talent.event_organized").toString().trim());
                                }
                            }
                            //_______________event_organizing__________________
                            if(documentSnapshot.get("talent.organizing_talent.skills.event_coordinating")!=null) {
                                EventCoordination.setChecked(true);
                                if (documentSnapshot.get("talent.organizing_talent.event_organized")!= null) {
                                    eventEvents.setText("Number od Events "+documentSnapshot.get("talent.organizing_talent.event_organized").toString().trim());
                                }
                            }
                            //_______________other_organizing__________________
                            if(documentSnapshot.get("talent.organizing_talent.skills.others")!=null) {
                                Others.setChecked(true);
                                if (documentSnapshot.get("talent.organizing_talent.event_organized")!= null) {
                                    otherEvents.setText("Number od Events "+documentSnapshot.get("talent.organizing_talent.event_organized").toString().trim());
                                }
                            }
                        }
                    }
                });

        return view;
    }
}
