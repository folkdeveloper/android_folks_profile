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


public class Literary_and_public_speaking extends Fragment {


    private String phone;

    private Button college_debate,district_debate,state_debate,national_debate,international_debate;
    private Button college_public,district_public,state_public,national_public,international_public;
    private Button college_anchor,district_anchor,state_anchor,national_anchor,international_anchor;
    private Button college_writing,district_writing,state_writing,national_writing,international_writing;
    private Button college_magazine,district_magazine,state_magazine,national_magazine,international_magazine;
    private Button college_paper,district_paper,state_paper,national_paper,international_paper;

    private ToggleButton toggleButton_debate, toggleButton_public, toggleButton_anchor, toggleButton_writing, toggleButton_magazine, toggleButton_paper;

    public Literary_and_public_speaking() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_talents_literary_and_public_speaking, container, false);

        college_debate = view.findViewById(R.id.college_debate);
        district_debate = view.findViewById(R.id.District_debate);
        state_debate= view.findViewById(R.id.State_debate);
        national_debate= view.findViewById(R.id.national_debate);
        international_debate= view.findViewById(R.id.International_debate);
        toggleButton_debate= view.findViewById(R.id.select_debate);

        college_public = view.findViewById(R.id.college_public);
        district_public = view.findViewById(R.id.District_public);
        state_public= view.findViewById(R.id.State_public);
        national_public= view.findViewById(R.id.national_public);
        international_public= view.findViewById(R.id.International_public);
        toggleButton_public= view.findViewById(R.id.select_public);

        college_anchor = view.findViewById(R.id.college_anchoring);
        district_anchor = view.findViewById(R.id.District_anchoring);
        state_anchor= view.findViewById(R.id.State_anchoring);
        national_anchor= view.findViewById(R.id.national_anchoring);
        international_anchor= view.findViewById(R.id.International_anchoring);
        toggleButton_anchor= view.findViewById(R.id.select_anchoring);

        college_writing = view.findViewById(R.id.college_writing);
        district_writing = view.findViewById(R.id.District_writing);
        state_writing= view.findViewById(R.id.State_writing);
        national_writing= view.findViewById(R.id.national_writing);
        international_writing= view.findViewById(R.id.International_writing);
        toggleButton_writing= view.findViewById(R.id.select_writing);

        college_magazine = view.findViewById(R.id.college_magazine);
        district_magazine = view.findViewById(R.id.District_magazine);
        state_magazine= view.findViewById(R.id.State_magazine);
        national_magazine= view.findViewById(R.id.national_magazine);
        international_magazine= view.findViewById(R.id.International_magazine);
        toggleButton_magazine= view.findViewById(R.id.select_magazine);

        college_paper = view.findViewById(R.id.college_paper);
        district_paper = view.findViewById(R.id.District_paper);
        state_paper= view.findViewById(R.id.State_paper);
        national_paper= view.findViewById(R.id.national_paper);
        international_paper= view.findViewById(R.id.International_paper);
        toggleButton_paper= view.findViewById(R.id.select_paper);

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

                            //_______________debate__________________
                            if(documentSnapshot.get("talent.literary_talent.skills.debating")!=null) {
                                toggleButton_debate.setChecked(true);
                                if (documentSnapshot.get("talent.literary_talent.participation.college_level") != null) {
                                    college_debate.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.literary_talent.participation.district_level") != null) {
                                    district_debate.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.literary_talent.participation.state_level") != null) {
                                    state_debate.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.literary_talent.participation.national_level") != null) {
                                    national_debate.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.literary_talent.participation.international_level") != null) {
                                    international_debate.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }

                            //_______________public speaking__________________
                            if(documentSnapshot.get("talent.literary_talent.skills.public_speaking")!=null) {
                                toggleButton_public.setChecked(true);
                                if (documentSnapshot.get("talent.literary_talent.participation.college_level") != null) {
                                    college_public.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.literary_talent.participation.district_level") != null) {
                                    district_public.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.literary_talent.participation.state_level") != null) {
                                    state_public.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.literary_talent.participation.national_level") != null) {
                                    national_public.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.literary_talent.participation.international_level") != null) {
                                    international_public.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }
                            //_______________anchoring__________________
                            if(documentSnapshot.get("talent.literary_talent.skills.anchoring")!=null) {
                                toggleButton_anchor.setChecked(true);
                                if (documentSnapshot.get("talent.literary_talent.participation.college_level") != null) {
                                    college_anchor.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.literary_talent.participation.district_level") != null) {
                                    district_anchor.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.literary_talent.participation.state_level") != null) {
                                    state_anchor.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.literary_talent.participation.national_level") != null) {
                                    national_anchor.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.literary_talent.participation.international_level") != null) {
                                    international_anchor.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }

                            //___________________Creative Writing__________________
                            if(documentSnapshot.get("talent.literary_talent.skills.creative_writing")!=null) {
                                toggleButton_writing.setChecked(true);
                                if (documentSnapshot.get("talent.literary_talent.participation.college_level") != null) {
                                    college_writing.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.literary_talent.participation.district_level") != null) {
                                    district_writing.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.literary_talent.participation.state_level") != null) {
                                    state_writing.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.literary_talent.participation.national_level") != null) {
                                    national_writing.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.literary_talent.participation.international_level") != null) {
                                    international_writing.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }

                            //___________________magazine editing__________________
                            if(documentSnapshot.get("talent.literary_talent.skills.magazine_editing")!=null) {
                                toggleButton_magazine.setChecked(true);
                                if (documentSnapshot.get("talent.literary_talent.participation.college_level") != null) {
                                    college_magazine.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.literary_talent.participation.district_level") != null) {
                                    district_magazine.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.literary_talent.participation.state_level") != null) {
                                    state_magazine.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.literary_talent.participation.national_level") != null) {
                                    national_magazine.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.literary_talent.participation.international_level") != null) {
                                    international_magazine.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }

                            //___________________paper presentation__________________
                            if(documentSnapshot.get("talent.literary_talent.skills.paper_presentation")!=null) {
                                toggleButton_paper.setChecked(true);
                                if (documentSnapshot.get("talent.literary_talent.participation.college_level") != null) {
                                    college_paper.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.literary_talent.participation.district_level") != null) {
                                    district_paper.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.literary_talent.participation.state_level") != null) {
                                    state_paper.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.literary_talent.participation.national_level") != null) {
                                    national_paper.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.literary_talent.participation.international_level") != null) {
                                    international_paper.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }
                        }
                    }
                });
        return view;
    }
}
