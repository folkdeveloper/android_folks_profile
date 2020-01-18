package com.creation.android.folkapp2019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.creation.android.folkapp2019.fragments.Activities_fragment_profile;
import com.creation.android.folkapp2019.fragments.Basic;
import com.creation.android.folkapp2019.fragments.Details;
import com.creation.android.folkapp2019.fragments.KC_Academics;
import com.creation.android.folkapp2019.fragments.Others;
import com.creation.android.folkapp2019.fragments.Profile;
import com.creation.android.folkapp2019.fragments.Talents;

import java.util.ArrayList;
import java.util.List;

public class EditDetails extends AppCompatActivity {

    private ViewPager viewPager;
    TextView Basic,Talents,Others;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);

        Basic = findViewById(R.id.basic);
        Talents = findViewById(R.id.talents);
        Others = findViewById(R.id.others);

        viewPager = (ViewPager) findViewById(R.id.pagerDetails);
        addTabs(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position==0){
                    Basic.setTextColor(Color.parseColor("#0000ff"));
                    Talents.setTextColor(Color.parseColor("#FF292929"));
                    Others.setTextColor(Color.parseColor("#FF292929"));
                    Basic.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    Talents.setBackgroundColor(Color.parseColor("#F6F6F6"));
                    Others.setBackgroundColor(Color.parseColor("#F6F6F6"));
                }
                if(position==1){
                    Basic.setTextColor(Color.parseColor("#FF292929"));
                    Talents.setTextColor(Color.parseColor("#0000ff"));
                    Others.setTextColor(Color.parseColor("#FF292929"));
                    Basic.setBackgroundColor(Color.parseColor("#F6F6F6"));
                    Talents.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    Others.setBackgroundColor(Color.parseColor("#F6F6F6"));
                }
                if(position==2){
                    Basic.setTextColor(Color.parseColor("#FF292929"));
                    Talents.setTextColor(Color.parseColor("#FF292929"));
                    Others.setTextColor(Color.parseColor("#0000ff"));
                    Basic.setBackgroundColor(Color.parseColor("#F6F6F6"));
                    Talents.setBackgroundColor(Color.parseColor("#F6F6F6"));
                    Others.setBackgroundColor(Color.parseColor("#FFFFFF"));
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Basic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });
        Talents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });
        Others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });

    }
    private void addTabs(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Basic(), "Basic");
        adapter.addFrag(new Talents(), "Talent");
        adapter.addFrag(new Others(), "Others");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
