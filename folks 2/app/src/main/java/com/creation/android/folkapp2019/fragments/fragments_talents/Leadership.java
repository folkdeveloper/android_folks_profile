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


public class Leadership extends Fragment {

    private String phone;

    private ToggleButton classRep, clubPresident, culturalSecretary, unionPresident, others;
    private EditText otherLeadership;

    public Leadership() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_talents_leadership, container, false);

        classRep = view.findViewById(R.id.select_class_representative);
        clubPresident = view.findViewById(R.id.select_club_president);
        culturalSecretary = view.findViewById(R.id.select_cultural_secretary);
        unionPresident = view.findViewById(R.id.select_union_president);
        others = view.findViewById(R.id.select_leadership_others);

        otherLeadership = view.findViewById(R.id.others_leadership);

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

                            if(documentSnapshot.get("talent.leadership_talent.skills.class_representative")!=null) {
                                classRep.setChecked(true);
                            }
                            if(documentSnapshot.get("talent.leadership_talent.skills.club_president")!=null) {
                                clubPresident.setChecked(true);
                            }
                            if(documentSnapshot.get("talent.leadership_talent.skills.cultural_secretary")!=null) {
                                culturalSecretary.setChecked(true);
                            }
                            if(documentSnapshot.get("talent.leadership_talent.skills.union_president")!=null) {
                                unionPresident.setChecked(true);
                            }
                            if(documentSnapshot.get("talent.leadership_talent.skills.others")!=null) {
                                others.setChecked(true);
                            }
                        }
                    }
                });


        return view;
    }
}
