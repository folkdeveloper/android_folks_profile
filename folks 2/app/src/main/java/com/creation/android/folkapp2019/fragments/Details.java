package com.creation.android.folkapp2019.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.creation.android.folkapp2019.EditDetails;
import com.creation.android.folkapp2019.MainActivity;
import com.creation.android.folkapp2019.Profile_talents;
import com.creation.android.folkapp2019.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.creation.android.folkapp2019.R.layout.support_simple_spinner_dropdown_item;


public class Details extends Fragment implements AdapterView.OnItemSelectedListener{

    private EditText occupation,college,branch,stream,year,designation;
    private String phone;
    private EditText name,whatsapp,city,mobile,email,dob,workWith;
    private EditText stay;
    private RadioButton married,unmarried;
    private RadioGroup maritalStatus;
    private EditText gender;
    private Button talentsButton;
    private Button editButton;



    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference ProfileCollection = db.collection("Profile");

    public Details() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_details, container, false);

        editButton = view.findViewById(R.id.btn_edit);
        name = view.findViewById(R.id.name);
        whatsapp = view.findViewById(R.id.whatsapp);
        stay = view.findViewById(R.id.stay);
        city = view.findViewById(R.id.city);
        mobile = view.findViewById(R.id.mobile);
        email = view.findViewById(R.id.email);
        dob = view.findViewById(R.id.dob);
        married = view.findViewById(R.id.m1);
        unmarried = view.findViewById(R.id.m2);
        maritalStatus = view.findViewById(R.id.marriage_status);
        year = view.findViewById(R.id.year);
        workWith = view.findViewById(R.id.workwith);
        gender = view.findViewById(R.id.gender);
        college = view.findViewById(R.id.college);
        occupation = view.findViewById(R.id.Occupation);
        branch = view.findViewById(R.id.branch);
        stream = view.findViewById(R.id.stream);
        year = view.findViewById(R.id.year);
        designation = view.findViewById(R.id.designation);
        talentsButton = view.findViewById(R.id.TalentsButton);

        // Inflate the layout for this fragment
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        phone = user.getPhoneNumber().substring(3);

        stay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        talentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent talents = new Intent(getContext(), Profile_talents.class);
                startActivity(talents);
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditDetails.class);
                startActivity(intent);
            }
        });


        //loading the data from database and entering it in their corresponding fields
        ProfileCollection
                .whereEqualTo("mobile",phone)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){
                            if(documentSnapshot.get("name")!= null){
                                name.setText(documentSnapshot.getString("name"));
                            }
                            if(documentSnapshot.get("dob")!=null){
                                dob.setText(documentSnapshot.getString("dob"));
                            }
                            if(documentSnapshot.get("gender")!=null){
                                gender.setText(documentSnapshot.getString("gender"));
                            }
                            if(documentSnapshot.get("whatsapp")!= null){
                                whatsapp.setText(documentSnapshot.getString("whatsapp"));
                            }
                            if(documentSnapshot.get("address.stay")!= null){
                                stay.setText(documentSnapshot.getString("address.stay"));
                            }
                            if(documentSnapshot.get("city")!= null){
                                city.setText(documentSnapshot.getString("city"));
                            }
                            if(documentSnapshot.get("mobile")!= null){
                                mobile.setText(documentSnapshot.getString("mobile"));
                            }
                            if(documentSnapshot.get("email")!= null){
                                email.setText(documentSnapshot.getString("email"));
                            }
                            if(documentSnapshot.get("marital_status")!=null){
                                String status = documentSnapshot.getString("marital_status").trim();
                                if(status.contains("Single")){
                                    unmarried.setChecked(true);
                                    unmarried.setEnabled(true);
                                    married.setEnabled(false);
                                }
                                else{
                                    married.setChecked(true);
                                    married.setEnabled(true);
                                    unmarried.setEnabled(false);
                                }
                            }
                            if(documentSnapshot.get("education.branch")!=null){
                                branch.setText(documentSnapshot.getString("education.branch"));
                            }
                            if(documentSnapshot.get("education.college")!=null){
                                college.setText(documentSnapshot.getString("education.college"));
                            }
                            if(documentSnapshot.get("education.course_year")!=null){
                                year.setText(documentSnapshot.getString("education.course_year"));
                            }
                            if(documentSnapshot.get("education.stream")!=null){
                                stream.setText(documentSnapshot.getString("education.stream"));
                            }
                            if(documentSnapshot.get("occupation")!=null){
                                occupation.setText(documentSnapshot.getString("occupation"));
                            }
                            if(documentSnapshot.get("occupation_details.designation")!=null){
                                designation.setText(documentSnapshot.getString("occupation_details.designation"));
                            }
                            if(documentSnapshot.get("occupation_details.working_with")!=null){
                                workWith.setText(documentSnapshot.getString("occupation_details.working_with"));
                            }
                        }
                    }
                });
        return view;
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }



    static class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
        }
        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }
    }
}
