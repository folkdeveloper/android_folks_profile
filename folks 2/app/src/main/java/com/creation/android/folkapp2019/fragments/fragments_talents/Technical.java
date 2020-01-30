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


public class Technical extends Fragment {

    private String phone;

    private Button college_app,district_app,state_app,national_app,international_app;
    private Button college_ai,district_ai,state_ai,national_ai,international_ai;
    private Button college_cc,district_cc,state_cc,national_cc,international_cc;
    private Button college_datasci,district_datasci,state_datasci,national_datasci,international_datasci;
    private Button college_olymp,district_olymp,state_olymp,national_olymp,international_olymp;
    private Button college_software,district_software,state_software,national_software,international_software;
    private Button college_uiux,district_uiux,state_uiux,national_uiux,international_uiux;
    private Button college_web,district_web,state_web,national_web,international_web;

    private ToggleButton toggleButton_app, toggleButton_ai, toggleButton_cc, toggleButton_datasci, toggleButton_olymp;
    private ToggleButton toggleButton_software, toggleButton_uiux, toggleButton_web;

    public Technical() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_talents_technical, container, false);


        college_app = view.findViewById(R.id.college_app);
        district_app = view.findViewById(R.id.District_app);
        state_app = view.findViewById(R.id.State_app);
        national_app = view.findViewById(R.id.national_app);
        international_app = view.findViewById(R.id.International_app);
        toggleButton_app = view.findViewById(R.id.select_add_dev);

        college_ai = view.findViewById(R.id.college_ai);
        district_ai = view.findViewById(R.id.District_ai);
        state_ai = view.findViewById(R.id.State_ai);
        national_ai = view.findViewById(R.id.national_ai);
        international_ai = view.findViewById(R.id.International_ai);
        toggleButton_ai = view.findViewById(R.id.select_AI);

        college_cc = view.findViewById(R.id.college_cc);
        district_cc = view.findViewById(R.id.District_cc);
        state_cc = view.findViewById(R.id.State_cc);
        national_cc = view.findViewById(R.id.national_cc);
        international_cc = view.findViewById(R.id.International_cc);
        toggleButton_cc = view.findViewById(R.id.select_CC);

        college_datasci = view.findViewById(R.id.college_datasci);
        district_datasci = view.findViewById(R.id.District_datasci);
        state_datasci = view.findViewById(R.id.State_datasci);
        national_datasci = view.findViewById(R.id.national_datasci);
        international_datasci = view.findViewById(R.id.International_datasci);
        toggleButton_datasci = view.findViewById(R.id.select_data_sci);

        college_olymp = view.findViewById(R.id.college_olmp);
        district_olymp = view.findViewById(R.id.District_olmp);
        state_olymp = view.findViewById(R.id.State_olmp);
        national_olymp = view.findViewById(R.id.national_olmp);
        international_olymp = view.findViewById(R.id.International_olmp);
        toggleButton_olymp = view.findViewById(R.id.select_olympiad);

        college_software = view.findViewById(R.id.college_software);
        district_software = view.findViewById(R.id.District_software);
        state_software = view.findViewById(R.id.State_software);
        national_software = view.findViewById(R.id.national_software);
        international_software = view.findViewById(R.id.International_software);
        toggleButton_software = view.findViewById(R.id.select_software_dev);

        college_uiux = view.findViewById(R.id.college_uiux);
        district_uiux = view.findViewById(R.id.District_uiux);
        state_uiux = view.findViewById(R.id.State_uiux);
        national_uiux = view.findViewById(R.id.national_uiux);
        international_uiux = view.findViewById(R.id.International_uiux);
        toggleButton_uiux = view.findViewById(R.id.select_uiux);

        college_web = view.findViewById(R.id.college_web);
        district_web = view.findViewById(R.id.District_web);
        state_web = view.findViewById(R.id.State_web);
        national_web = view.findViewById(R.id.national_web);
        international_web = view.findViewById(R.id.International_web);
        toggleButton_web = view.findViewById(R.id.select_web_dev);


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

                            //__________app developement_______________________
                            if(documentSnapshot.get("talent.technical_talent.skills.app_development")!=null) {
                                toggleButton_app.setChecked(true);
                                if (documentSnapshot.get("talent.technical_talent.participation.college_level") != null) {
                                    college_app.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.district_level") != null) {
                                    district_app.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.state_level") != null) {
                                    state_app.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.national_level") != null) {
                                    national_app.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.international_level") != null) {
                                    international_app.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }

                            //__________Artificial Intelligence_______________________
                            if(documentSnapshot.get("talent.technical_talent.skills.artificial_intelligence")!=null) {
                                toggleButton_ai.setChecked(true);
                                if (documentSnapshot.get("talent.technical_talent.participation.college_level") != null) {
                                    college_ai.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.district_level") != null) {
                                    district_ai.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.state_level") != null) {
                                    state_ai.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.national_level") != null) {
                                    national_ai.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.international_level") != null) {
                                    international_ai.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }

                            //__________Cloud computing_______________________
                            if(documentSnapshot.get("talent.technical_talent.skills.cloud_computing")!=null) {
                                toggleButton_cc.setChecked(true);
                                if (documentSnapshot.get("talent.technical_talent.participation.college_level") != null) {
                                    college_cc.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.district_level") != null) {
                                    district_cc.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.state_level") != null) {
                                    state_cc.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.national_level") != null) {
                                    national_cc.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.international_level") != null) {
                                    international_cc.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }

                            //__________Data Science_______________________
                            if(documentSnapshot.get("talent.technical_talent.skills.data_science")!=null) {
                                toggleButton_datasci.setChecked(true);
                                if (documentSnapshot.get("talent.technical_talent.participation.college_level") != null) {
                                    college_datasci.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.district_level") != null) {
                                    district_datasci.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.state_level") != null) {
                                    state_datasci.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.national_level") != null) {
                                    national_datasci.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.international_level") != null) {
                                    international_datasci.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }

                            //__________olympiad_______________________
                            if(documentSnapshot.get("talent.technical_talent.skills.olympiad")!=null) {
                                toggleButton_olymp.setChecked(true);
                                if (documentSnapshot.get("talent.technical_talent.participation.college_level") != null) {
                                    college_olymp.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.district_level") != null) {
                                    district_olymp.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.state_level") != null) {
                                    state_olymp.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.national_level") != null) {
                                    national_olymp.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.international_level") != null) {
                                    international_olymp.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }

                            //__________software developement_______________________
                            if(documentSnapshot.get("talent.technical_talent.skills.software_development")!=null) {
                                toggleButton_software.setChecked(true);
                                if (documentSnapshot.get("talent.technical_talent.participation.college_level") != null) {
                                    college_software.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.district_level") != null) {
                                    district_software.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.state_level") != null) {
                                    state_software.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.national_level") != null) {
                                    national_software.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.international_level") != null) {
                                    international_software.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }

                            //__________ui ux_______________________
                            if(documentSnapshot.get("talent.technical_talent.skills.uiux")!=null) {
                                toggleButton_uiux.setChecked(true);
                                if (documentSnapshot.get("talent.technical_talent.participation.college_level") != null) {
                                    college_uiux.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.district_level") != null) {
                                    district_uiux.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.state_level") != null) {
                                    state_uiux.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.national_level") != null) {
                                    national_uiux.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.international_level") != null) {
                                    international_uiux.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }

                            //__________web development_______________________
                            if(documentSnapshot.get("talent.technical_talent.skills.web_development")!=null) {
                                toggleButton_web.setChecked(true);
                                if (documentSnapshot.get("talent.technical_talent.participation.college_level") != null) {
                                    college_web.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.district_level") != null) {
                                    district_web.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.state_level") != null) {
                                    state_web.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.national_level") != null) {
                                    national_web.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.technical_talent.participation.international_level") != null) {
                                    international_web.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }
                        }
                    }
                });


        return view;
    }
}
