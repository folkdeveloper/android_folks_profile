package com.creation.android.folkapp2019.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.creation.android.folkapp2019.EditDetails;
import com.creation.android.folkapp2019.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class Profile extends Fragment {

    private TextView activity,KC_academics,Details;

    public Profile() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        List<Fragment> fragments = getFragments();

        MyPageAdapter pageAdapter = new MyPageAdapter(getChildFragmentManager(), fragments);
        ViewPager pager = view.findViewById(R.id.pager);
        pager.setAdapter(pageAdapter);

        return view;

    }

    @Override
    public void onResume() {
        super.onResume();

        activity = getActivity().findViewById(R.id.activities);
        KC_academics = getActivity().findViewById(R.id.kcacademics);
        Details = getActivity().findViewById(R.id.details);


        List<Fragment> fragments = getFragments();
        MyPageAdapter pageAdapter = new MyPageAdapter(getChildFragmentManager(),fragments);
        final ViewPager pager = getActivity().findViewById(R.id.pager);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position==0){
                    activity.setTextColor(Color.parseColor("#0000ff"));
                    KC_academics.setTextColor(Color.parseColor("#FF292929"));
                    Details.setTextColor(Color.parseColor("#FF292929"));
                    activity.setBackgroundColor(Color.parseColor("#F6F6F6"));
                    KC_academics.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    Details.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }
                if(position==1){
                    activity.setTextColor(Color.parseColor("#FF292929"));
                    KC_academics.setTextColor(Color.parseColor("#0000ff"));
                    Details.setTextColor(Color.parseColor("#FF292929"));
                    activity.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    KC_academics.setBackgroundColor(Color.parseColor("#F6F6F6"));
                    Details.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }
                if(position==2){
                    activity.setTextColor(Color.parseColor("#FF292929"));
                    KC_academics.setTextColor(Color.parseColor("#FF292929"));
                    Details.setTextColor(Color.parseColor("#0000ff"));
                    activity.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    KC_academics.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    Details.setBackgroundColor(Color.parseColor("#F6F6F6"));
                }

            }
            @Override
            public void onPageSelected(int position) {
                Log.d("TAGG", "onPageSelected: "+position);

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(0);
            }
        });
        KC_academics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(1);
            }
        });
        Details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(2);
            }
        });


        pager.setAdapter(pageAdapter);

    }

    private List<Fragment> getFragments() {
        List<Fragment> fList = new ArrayList<>();
        fList.add(new Activities_fragment_profile());
        fList.add(new KC_Academics());
        fList.add(new Details());
        return fList;
    }

    private class MyPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;
        private int[] mResources;

        public MyPageAdapter(FragmentManager fm, List<Fragment> fragments ) {
            super(fm);
            this.fragments = fragments;
        }
        @Override
        public Fragment getItem(int position)
        {
            return this.fragments.get(position);
        }

        @Override
        public int getCount()
        {
            return this.fragments.size();
        }
    }


    @Override
    public void onStart() {
        super.onStart();

        String phone;
        final TextView username,folkID,folkGuide,folkLevel;

        // Access a Cloud Firestore instance from Activity
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assert user != null;
        phone = user.getPhoneNumber();

//        UI elements initialisation
        username = getActivity().findViewById(R.id.username);
        folkID = getActivity().findViewById(R.id.id);
        folkGuide = getActivity().findViewById(R.id.folk_guide);
        folkLevel = getActivity().findViewById(R.id.folk_lvl);






        db.collection("Profile")
                .whereEqualTo("mobile", phone)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Toast.makeText(getContext(),"Hello World",Toast.LENGTH_LONG).show();
                                Toast.makeText(getContext(),username.getText().toString(),Toast.LENGTH_LONG).show();
                                if(document.getData().get("name")!= null) {
                                    username.setText(document.getData().get("name").toString());
                                }
                                if(document.getData().get("folk_id")!= null) {
                                    folkID.setText("folk ID:"+document.getData().get("folk_id").toString());
                                }
                                if(document.getData().get("folk_guide")!= null) {
                                    folkGuide.setText("folk guide:"+document.getData().get("folk_guide").toString());
                                }
                                if(document.getData().get("folk_level")!= null) {
                                    folkLevel.setText("folk level:"+document.getData().get("folk_level").toString());
                                }

                            }
                        } else {
                            Log.d("Tag!!!!!", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

}
