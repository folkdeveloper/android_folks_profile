package com.creation.android.folkapp2019;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.creation.android.folkapp2019.fragments.fragments_talents.Cooking;
import com.creation.android.folkapp2019.fragments.fragments_talents.Dance;
import com.creation.android.folkapp2019.fragments.fragments_talents.Graphics;
import com.creation.android.folkapp2019.fragments.fragments_talents.Instruments;
import com.creation.android.folkapp2019.fragments.fragments_talents.Leadership;
import com.creation.android.folkapp2019.fragments.fragments_talents.Literary_and_public_speaking;
import com.creation.android.folkapp2019.fragments.fragments_talents.Music;
import com.creation.android.folkapp2019.fragments.fragments_talents.Organizing;
import com.creation.android.folkapp2019.fragments.fragments_talents.Others;
import com.creation.android.folkapp2019.fragments.fragments_talents.Sports;
import com.creation.android.folkapp2019.fragments.fragments_talents.Technical;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Profile_talents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_talents);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbarTalents);
        setSupportActionBar(toolbar);
        Button back = findViewById(R.id.back);

        ViewPager viewPager = (ViewPager) findViewById(R.id.talentsVIew);
        TabLayout talentsTitles = (TabLayout) findViewById(R.id.TalentsTitles);
        addTabs(viewPager);
        talentsTitles.setupWithViewPager(viewPager);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    private void addTabs(ViewPager viewPager) {
        Profile_talents.ViewPagerAdapter adapter = new Profile_talents.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Music(), "Music");
        adapter.addFrag(new Instruments(), "Instruments");
        adapter.addFrag(new Dance(),"Dance");
        adapter.addFrag(new Graphics(),"Graphics and Video Making");
        adapter.addFrag(new Cooking(),"Cooking");
        adapter.addFrag(new Literary_and_public_speaking(),"Literary and public speaking");
        adapter.addFrag(new Organizing(),"Organizing");
        adapter.addFrag(new Leadership(),"Leadership");
        adapter.addFrag(new Technical(),"Technical");
        adapter.addFrag(new Sports(),"Sports");
        adapter.addFrag(new Others(),"Others");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        private ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        private void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
