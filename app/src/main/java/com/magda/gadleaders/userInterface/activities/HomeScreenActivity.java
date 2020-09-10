package com.magda.gadleaders.userInterface.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.tabs.TabLayout;
import com.magda.gadleaders.R;
import com.magda.gadleaders.adapter.HomePagerAdapter;
import com.magda.gadleaders.userInterface.fragments.LearningLeadersFragment;
import com.magda.gadleaders.userInterface.fragments.SkillIqLeadersFragment;

public class HomeScreenActivity extends AppCompatActivity {
    private Button mSubmit;
    private ViewPager mViewPager;
    private TabLayout mTab;
    private HomePagerAdapter mHomePagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);


        initViews();


        mSubmit.setOnClickListener(v -> toSubmitProjectActivity());
    }

    private void toSubmitProjectActivity() {
        startActivity(new Intent(this, SubmitProjectActivity.class));
    }

    private void initViews() {
        mSubmit = findViewById(R.id.btSubmit);
        mViewPager = findViewById(R.id.leaderViewPager);
        mTab = findViewById(R.id.leaderTab);
        mHomePagerAdapter = new HomePagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mHomePagerAdapter.addFragment(new LearningLeadersFragment(),"Learning Leaders");
        mHomePagerAdapter.addFragment(new SkillIqLeadersFragment(),"Skill IQ Leaders");
        mViewPager.setAdapter(mHomePagerAdapter);
        mTab.setupWithViewPager(mViewPager);
    }
}