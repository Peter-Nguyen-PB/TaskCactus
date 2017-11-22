package com.uottawa.thirstycactus.taskcactus;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.uottawa.thirstycactus.taskcactus.fragments.CalendarFragment;
import com.uottawa.thirstycactus.taskcactus.fragments.TaskFragment;
import com.uottawa.thirstycactus.taskcactus.fragments.UsersFragment;

public class MainActivity extends AppCompatActivity
{
    // ATTRIBUTES

    private static final String TAG = "MainActivity";
    private SectionsStatePagerAdapter adapter;
    private ViewPager mViewPager;

    private UsersFragment usr;
    private CalendarFragment cal;
    private TaskFragment task;


    // =============================================================================================

    // METHODS

    // =============================================================================================


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Log.d(TAG, "onCreate: Started");


        mViewPager = (ViewPager) findViewById(R.id.container);
        setUpViewPager(mViewPager);


        TabLayout tabLayout=(TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        getSupportActionBar().setElevation(0); // Remove shadow under action bar
    }

    /**
     * Initializes 3 main fragments (UserFragment, CalendarFragment and TaskFragment)
     * and appends them to the viewPager.
     *
     * @param viewPager
     */
    private void setUpViewPager(ViewPager viewPager)
    {
        adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());

        // Initialize the fragments
        usr = new UsersFragment();
        cal = new CalendarFragment();
        task = new TaskFragment();

        adapter.addFragment(usr, "Users");
        adapter.addFragment(cal, "Calendar");
        adapter.addFragment(task, "Task");


        viewPager.setAdapter(adapter);
    }

}
