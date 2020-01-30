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


public class Others extends Fragment {

    private String phone;

    private Button college_others,district_others,state_others,national_others,international_others;
    private EditText otherTalent;

    private ToggleButton toggleButton_others;

    public Others() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_talents_others, container, false);

        college_others = view.findViewById(R.id.college_other_talent);
        district_others = view.findViewById(R.id.District_other_talent);
        state_others = view.findViewById(R.id.State_other_talent);
        national_others = view.findViewById(R.id.national_other_talent);
        international_others = view.findViewById(R.id.International_other_talent);
        toggleButton_others = view.findViewById(R.id.select_talent_others);

        otherTalent = view.findViewById(R.id.other_talent);

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

                            if(documentSnapshot.get("talent.other_talent.skills")!=null) {
                                toggleButton_others.setChecked(true);
                                if (documentSnapshot.get("talent.other_talent.participation.college_level") != null) {
                                    college_others.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.other_talent.participation.district_level") != null) {
                                    district_others.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.other_talent.participation.state_level") != null) {
                                    state_others.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.other_talent.participation.national_level") != null) {
                                    national_others.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.other_talent.participation.international_level") != null) {
                                    international_others.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }
                            String Other = documentSnapshot.get("talent.other_talent.skills").toString().trim().replaceAll("=true","").replaceAll("\\{","").replaceAll("\\}","").replaceAll(" ","").replaceAll("other_talents","");
                            String[] all_sports = Other.split(",");

                            String otherTalents ="";
                            for(int i=0;i<all_sports.length;i++){
                                otherTalents += all_sports[i]+"\n";
                            }
                            otherTalent.setText(otherTalents);

                        }
                    }
                });



        return view;
    }
}
