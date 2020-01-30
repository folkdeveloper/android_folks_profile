package com.creation.android.folkapp2019.fragments.fragments_talents;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

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


public class Instruments extends Fragment {

    private String phone;

    private Button college_keyboard,district_keyboard,state_keyboard,national_keyboard,international_keyboard;
    private Button college_guitar,district_guitar,state_guitar,national_guitar,international_guitar;
    private Button college_mirdanga, district_mirdanga, state_mirdanga, national_mirdanga, international_mirdanga;
    private Button college_flute, district_flute, state_flute, national_flute, international_flute;
    private Button college_tabla, district_tabla, state_tabla, national_tabla, international_tabla;
    private Button college_violin, district_violin, state_violin, national_violin, international_violin;
    private Button college_trumpet, district_trumpet, state_trumpet, national_trumpet, international_trumpet;

    private ToggleButton toggleButton_keyboard, toggleButton_guitar, toggleButton_mirdanga, toggleButton_flute, toggleButton_tabla;
    private ToggleButton toggleButton_violin, toggleButton_trumpet;


    public Instruments() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_talents_instruments, container, false);

        college_keyboard = view.findViewById(R.id.college_keyboard);
        district_keyboard = view.findViewById(R.id.District_keyboard);
        state_keyboard = view.findViewById(R.id.State_keyboard);
        national_keyboard = view.findViewById(R.id.national_keyboard);
        international_keyboard = view.findViewById(R.id.International_keyboard);
        toggleButton_keyboard = view.findViewById(R.id.select_keyboard);

        college_guitar = view.findViewById(R.id.college_guitar);
        district_guitar = view.findViewById(R.id.District_guitar);
        state_guitar = view.findViewById(R.id.State_guitar);
        national_guitar = view.findViewById(R.id.national_guitar);
        international_guitar = view.findViewById(R.id.International_guitar);
        toggleButton_guitar = view.findViewById(R.id.select_guitar);

        college_mirdanga = view.findViewById(R.id.college_mirdanga);
        district_mirdanga = view.findViewById(R.id.District_mirdanga);
        state_mirdanga = view.findViewById(R.id.State_mirdanga);
        national_mirdanga = view.findViewById(R.id.national_mirdanga);
        international_mirdanga = view.findViewById(R.id.International_mirdanga);
        toggleButton_mirdanga = view.findViewById(R.id.select_mirdanga);

        college_flute = view.findViewById(R.id.college_flute);
        district_flute = view.findViewById(R.id.District_flute);
        state_flute = view.findViewById(R.id.State_flute);
        national_flute = view.findViewById(R.id.national_flute);
        international_flute = view.findViewById(R.id.International_flute);
        toggleButton_flute = view.findViewById(R.id.select_flute);

        college_tabla = view.findViewById(R.id.college_tabla);
        district_tabla = view.findViewById(R.id.District_tabla);
        state_tabla = view.findViewById(R.id.State_tabla);
        national_tabla = view.findViewById(R.id.national_tabla);
        international_tabla = view.findViewById(R.id.International_tabla);
        toggleButton_tabla = view.findViewById(R.id.select_tabla);

        college_violin = view.findViewById(R.id.college_violin);
        district_violin = view.findViewById(R.id.District_violin);
        state_violin = view.findViewById(R.id.State_violin);
        national_violin = view.findViewById(R.id.national_violin);
        international_violin = view.findViewById(R.id.International_violin);
        toggleButton_violin = view.findViewById(R.id.select_violin);

        college_trumpet = view.findViewById(R.id.college_trumpet);
        district_trumpet = view.findViewById(R.id.District_trumpet);
        state_trumpet = view.findViewById(R.id.State_trumpet);
        national_trumpet = view.findViewById(R.id.national_trumpet);
        international_trumpet = view.findViewById(R.id.International_trumpet);
        toggleButton_trumpet = view.findViewById(R.id.select_trumpet);

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

                            //__________keyboard_______________________
                            if(documentSnapshot.get("talent.music_instruments_talent.skills.keyboard")!=null) {
                                toggleButton_keyboard.setChecked(true);
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.college_level") != null) {
                                    college_keyboard.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.district_level") != null) {
                                    district_keyboard.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.state_level") != null) {
                                    state_keyboard.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.national_level") != null) {
                                    national_keyboard.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.international_level") != null) {
                                    international_keyboard.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }

                            //_________________guitar_______________________
                            if(documentSnapshot.get("talent.music_instruments_talent.skills.guitar")!=null) {
                                toggleButton_guitar.setChecked(true);
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.college_level") != null) {
                                    college_guitar.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.district_level") != null) {
                                    district_guitar.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.state_level") != null) {
                                    state_guitar.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.national_level") != null) {
                                    national_guitar.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.international_level") != null) {
                                    international_guitar.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }

                            //_________________mirdanga_______________________
                            if(documentSnapshot.get("talent.music_instruments_talent.skills.mridanga")!=null) {
                                toggleButton_mirdanga.setChecked(true);
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.college_level") != null) {
                                    college_mirdanga.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.district_level") != null) {
                                    district_mirdanga.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.state_level") != null) {
                                    state_mirdanga.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.national_level") != null) {
                                    national_mirdanga.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.international_level") != null) {
                                    international_mirdanga.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }

                            //_________________flute_______________________
                            if(documentSnapshot.get("talent.music_instruments_talent.skills.flute")!=null) {
                                toggleButton_flute.setChecked(true);
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.college_level") != null) {
                                    college_flute.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.district_level") != null) {
                                    district_flute.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.state_level") != null) {
                                    state_flute.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.national_level") != null) {
                                    national_flute.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.international_level") != null) {
                                    international_flute.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }

                            //_________________tabla_______________________
                            if(documentSnapshot.get("talent.music_instruments_talent.skills.tabla")!=null) {
                                toggleButton_tabla.setChecked(true);
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.college_level") != null) {
                                    college_tabla.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.district_level") != null) {
                                    district_tabla.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.state_level") != null) {
                                    state_tabla.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.national_level") != null) {
                                    national_tabla.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.international_level") != null) {
                                    international_tabla.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }

                            //_________________violin_______________________
                            if(documentSnapshot.get("talent.music_instruments_talent.skills.violin")!=null) {
                                toggleButton_violin.setChecked(true);
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.college_level") != null) {
                                    college_violin.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.district_level") != null) {
                                    district_violin.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.state_level") != null) {
                                    state_violin.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.national_level") != null) {
                                    national_violin.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.international_level") != null) {
                                    international_violin.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }

                            //_________________trumpet_______________________
                            if(documentSnapshot.get("talent.music_instruments_talent.skills.trumpet")!=null) {
                                toggleButton_trumpet.setChecked(true);
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.college_level") != null) {
                                    college_trumpet.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.district_level") != null) {
                                    district_trumpet.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.state_level") != null) {
                                    state_trumpet.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.national_level") != null) {
                                    national_trumpet.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.music_instruments_talent.participation.international_level") != null) {
                                    international_trumpet.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }
                        }
                    }
                });


        return view;
    }
}
