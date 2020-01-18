package com.creation.android.folkapp2019.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.creation.android.folkapp2019.MainActivity;
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

    private Spinner occupation,college,branch,stream,year,designation;
    private String phone;
    private EditText name,whatsapp,city,mobile,email,dob,workWith;
    private EditText stay;
    private RadioButton married,unmarried;
    private RadioGroup maritalStatus;
    private Spinner gender;
    String DocID;

    String m_text;


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

        final List<String> gens = new ArrayList<>();
        gens.add("Male");
        gens.add("Female");
        gender = view.findViewById(R.id.gender);
        gender.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()),android.R.layout.simple_spinner_dropdown_item, gens);
        adapter.setDropDownViewResource(support_simple_spinner_dropdown_item);
        gender.setAdapter(adapter);

        final List<String> colg = new ArrayList<>();
        colg.add("CMR University");
        colg.add("PES University");
        colg.add("Others");
        college = view.findViewById(R.id.college);
        college.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        final ArrayAdapter<String> adaptercol = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, colg);
        adaptercol.setDropDownViewResource(support_simple_spinner_dropdown_item);
        college.setAdapter(adaptercol);

        final List<String> occ = new ArrayList<>();
        occ.add("Student");
        occ.add("Working");
        occ.add("Self Employed");
        occ.add("Others");
        occupation = view.findViewById(R.id.Occupation);
        occupation.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        final ArrayAdapter<String> adapterocc = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, occ);
        adapterocc.setDropDownViewResource(support_simple_spinner_dropdown_item);
        occupation.setAdapter(adapterocc);

        final List<String> bran = new ArrayList<>();
        bran.add("B.Tech");
        bran.add("M.Tech");
        bran.add("Others");
        branch = view.findViewById(R.id.branch);
        branch.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        final ArrayAdapter<String> adapterBranch = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, bran);
        adapterBranch.setDropDownViewResource(support_simple_spinner_dropdown_item);
        branch.setAdapter(adapterBranch);

        final List<String> str = new ArrayList<>();
        str.add("Information Technology");
        str.add("Computer Science");
        str.add("Others");
        stream = view.findViewById(R.id.stream);
        stream.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        final ArrayAdapter<String> adapterStream = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, str);
        adapterStream.setDropDownViewResource(support_simple_spinner_dropdown_item);
        stream.setAdapter(adapterStream);

        final List<String> yer = new ArrayList<>();
        yer.add("1");
        yer.add("2");
        yer.add("3");
        yer.add("4");
        yer.add("5");
        year = view.findViewById(R.id.year);
        year.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        final ArrayAdapter<String> adapterYear = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, yer);
        adapterYear.setDropDownViewResource(support_simple_spinner_dropdown_item);
        year.setAdapter(adapterYear);

        final  List<String> des = new ArrayList<>();
        des.add("Student");
        des.add("Intern");
        designation = view.findViewById(R.id.designation);
        designation.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        final ArrayAdapter<String> adapterDesign = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, des);
        adapterDesign.setDropDownViewResource(support_simple_spinner_dropdown_item);
        designation.setAdapter(adapterDesign);

        // Inflate the layout for this fragment
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        phone = user.getPhoneNumber().substring(3);

        stay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                                name.setText(documentSnapshot.get("name").toString().trim());
                            }
                            if(documentSnapshot.get("dob")!=null){
                                dob.setText(documentSnapshot.get("dob").toString().trim());
                            }
                            if(documentSnapshot.get("gender")!=null){
                                int pos = adapter.getPosition(documentSnapshot.get("gender").toString().trim());
                                gender.setSelection(pos);
                            }
                            if(documentSnapshot.get("whatsapp")!= null){
                                whatsapp.setText(documentSnapshot.get("whatsapp").toString().trim());
                            }
                            if(documentSnapshot.get("address.stay")!= null){
                                stay.setText(documentSnapshot.get("address.stay").toString().trim());
                            }
                            if(documentSnapshot.get("city")!= null){
                                city.setText(documentSnapshot.get("city").toString().trim());
                            }
                            if(documentSnapshot.get("mobile")!= null){
                                mobile.setText(documentSnapshot.get("mobile").toString().trim());
                            }
                            if(documentSnapshot.get("email")!= null){
                                email.setText(documentSnapshot.get("email").toString().trim());
                            }
                            if(documentSnapshot.get("marital_status")!=null){
                                String status = documentSnapshot.get("marital_status").toString().trim();
                                if(status.contains("Single")){
                                    unmarried.setChecked(true);
                                }
                                else{
                                    married.setChecked(true);
                                }
                            }
                            if(documentSnapshot.get("education.branch")!=null){
                                int pos = adapterBranch.getPosition(documentSnapshot.get("education.branch").toString().trim());
                                branch.setSelection(pos);
                            }
                            if(documentSnapshot.get("education.college")!=null){
                                int pos = adaptercol.getPosition(documentSnapshot.get("education.college").toString().trim());
                                college.setSelection(pos);
                            }
                            if(documentSnapshot.get("education.course_year")!=null){
                                int pos = adapterYear.getPosition(documentSnapshot.get("education.course_year").toString().trim());
                                year.setSelection(pos);
                            }
                            if(documentSnapshot.get("education.stream")!=null){
                                int pos = adapterStream.getPosition(documentSnapshot.get("education.stream").toString().trim());
                                stream.setSelection(pos);
                            }
                            if(documentSnapshot.get("occupation")!=null){
                                int pos = adapterocc.getPosition(documentSnapshot.get("occupation").toString().trim());
                                occupation.setSelection(pos);
                            }
                            if(documentSnapshot.get("occupation_details.designation")!=null){
                                int pos = adapterDesign.getPosition(documentSnapshot.get("occupation_details.designation").toString().trim());
                                designation.setSelection(pos);
                            }
                            if(documentSnapshot.get("occupation_details.working_with")!=null){
                                workWith.setText(documentSnapshot.get("occupation_details.working_with").toString().trim());
                            }
                        }
                    }
                });
//----------------------------------------------------------------------------------------------------------------------------------------------------

        college.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(college.getSelectedItem().toString().trim().contains("Others")){
                    final EditText input = new EditText(getContext());
                    final AlertDialog dialog = new AlertDialog.Builder(getContext())
                            .setTitle("College")
                            .setView(input)
                            .setMessage("Enter college name here")
                            .setPositiveButton("Ok", null)
                            .setNegativeButton("Cancel", null)
                            .show();

                    Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    positiveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            m_text = input.getText().toString();
                            int index = adaptercol.getPosition("Others");
                            colg.add(index,m_text);
                            college.setAdapter(adaptercol);
                            college.setSelection(index);
                            dialog.dismiss();
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


        branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(branch.getSelectedItem().toString().trim().contains("Others")){
                    final EditText input = new EditText(getContext());
                    final AlertDialog dialog = new AlertDialog.Builder(getContext())
                            .setTitle("Branch")
                            .setView(input)
                            .setMessage("enter branch name here")
                            .setPositiveButton("Ok", null)
                            .setNegativeButton("Cancel", null)
                            .show();

                    Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    positiveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            m_text = input.getText().toString();
                            int index = adapterBranch.getPosition("Others");
                            bran.add(index,m_text);
                            branch.setAdapter(adapterBranch);
                            branch.setSelection(index);
                            dialog.dismiss();
                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        stream.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(stream.getSelectedItem().toString().trim().contains("Others")){
                    final EditText input = new EditText(getContext());
                    final AlertDialog dialog = new AlertDialog.Builder(getContext())
                            .setTitle("Stream")
                            .setView(input)
                            .setMessage("enter your stream here")
                            .setPositiveButton("Ok", null)
                            .setNegativeButton("Cancel", null)
                            .show();

                    Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    positiveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            m_text = input.getText().toString();
                            int index = adapterStream.getPosition("Others");
                            str.add(index,m_text);
                            stream.setAdapter(adapterStream);
                            stream.setSelection(index);
                            dialog.dismiss();
                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        occupation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(occupation.getSelectedItem().toString().trim().contains("Others")){
                    final EditText input = new EditText(getContext());
                    final AlertDialog dialog = new AlertDialog.Builder(getContext())
                            .setTitle("Occupation")
                            .setView(input)
                            .setMessage("Enter your occupation here")
                            .setPositiveButton("Ok", null)
                            .setNegativeButton("Cancel", null)
                            .show();

                    Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    positiveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            m_text = input.getText().toString();
                            int index = adapterocc.getPosition("Others");
                            occ.add(index,m_text);
                            occupation.setAdapter(adapterocc);
                            occupation.setSelection(index);
                            dialog.dismiss();
                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//----------------------------------------------------------------------------------------------------------------------------------------------------
//        update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ProfileCollection.whereEqualTo("mobile",phone).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        for(QueryDocumentSnapshot documentSnapshot: queryDocumentSnapshots){
//                            DocID = documentSnapshot.getId().toString().trim();
//                        }
//                    }
//                });
//
//                int radioID = maritalStatus.getCheckedRadioButtonId();
//                RadioButton btn = view.findViewById(radioID);
//
//                if(DocID==null){
//                    Toast.makeText(getContext(), "Update unsuccessful\n Try updating again", Toast.LENGTH_SHORT).show();
//                }
//
//                DocumentReference docref = db.document("Profile/"+DocID);
//                docref.update("name",name.getText().toString().trim());
//                docref.update("mobile",mobile.getText().toString().trim());
//                docref.update("marital_status",btn.getText().toString());
//                docref.update("dob",dob.getText().toString().trim());
//                docref.update("gender",gender.getSelectedItem().toString().trim());
//                docref.update("address.stay",stay.getText().toString().trim());
//                docref.update("city",city.getText().toString().trim());
//                docref.update("whatsapp",whatsapp.getText().toString().trim());
//                docref.update("email",email.getText().toString().trim());
//                docref.update("education.college",college.getSelectedItem().toString().trim());
//                docref.update("education.branch",branch.getSelectedItem().toString().trim());
//                docref.update("education.stream",stream.getSelectedItem().toString().trim());
//                docref.update("education.year",year.getSelectedItem().toString().trim());
//                docref.update("occupation",occupation.getSelectedItem().toString().trim());
//                docref.update("occupation_details.designation",designation.getSelectedItem().toString().trim());
//                docref.update("occupation_details.working_with",workWith.getText().toString().trim());
//
//                if(DocID!=null) {
//                    Toast.makeText(getContext(), "Update Successful", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
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
