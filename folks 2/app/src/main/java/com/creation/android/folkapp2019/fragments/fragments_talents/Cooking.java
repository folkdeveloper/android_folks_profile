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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.creation.android.folkapp2019.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class Cooking extends Fragment {

    private String phone;

    private Button SouthIndian, NorthIndian, Continential, Bakery;
    private ToggleButton select_cooking;
    private TextView CanCookFor;
    private RatingBar rating;

    public Cooking() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_talents_cooking, container, false);

        SouthIndian = view.findViewById(R.id.South_indian);
        NorthIndian = view.findViewById(R.id.North_Indian);
        Continential = view.findViewById(R.id.Continential);
        Bakery = view.findViewById(R.id.Bakery);
        select_cooking = view.findViewById(R.id.select_cooking);
        CanCookFor = view.findViewById(R.id.canCookFor);
        rating = view.findViewById(R.id.Cooking_rating);


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
                            if(documentSnapshot.get("talent.cooking")!=null) {
                                select_cooking.setChecked(true);
                                if(documentSnapshot.get("talent.cooking.cooking_self_rating")!=null){
                                    int rat = Integer.parseInt(documentSnapshot.get("talent.cooking.cooking_self_rating").toString().trim());
                                    Log.d("TAG", "onSuccess: "+String.valueOf(rat));
                                    rating.setRating(rat);
                                }
                                if(documentSnapshot.get("talent.cooking.can_cook_for")!=null){
                                    CanCookFor.setText("Can Cook for "+documentSnapshot.get("talent.cooking.can_cook_for"));
                                }
                                if (documentSnapshot.get("talent.cooking.skills.south_indian") != null) {
                                    SouthIndian.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.cooking.skills.north_indian") != null) {
                                    NorthIndian.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.cooking.skills.continental") != null) {
                                    Continential.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                                if (documentSnapshot.get("talent.cooking.skills.bakery") != null) {
                                    Bakery.setBackgroundResource(R.drawable.button_bg_selected);
                                }
                            }
                        }
                    }
                });

        return view;
    }
}
