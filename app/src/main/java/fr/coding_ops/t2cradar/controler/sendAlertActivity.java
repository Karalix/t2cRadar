package fr.coding_ops.t2cradar.controler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.location.Location;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;

import fr.coding_ops.t2cradar.R;
import fr.coding_ops.t2cradar.modele.ModeleArret;
import fr.coding_ops.t2cradar.modele.SentAlert;


public class sendAlertActivity extends Activity implements ActionBar.TabListener {

    private boolean canGetLocation = false ;

    private LocationClient locationClient = null ;

    private Location captedtLocation = null ;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_alert);

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }


        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        // If Google Play services is available
        if (ConnectionResult.SUCCESS == resultCode) {
            // In debug mode, log the status
            Toast.makeText(getApplicationContext(), "Google Services available", Toast.LENGTH_SHORT).show();
            canGetLocation = true ;
            MyLocationManager locationManager = new MyLocationManager(this);
            locationClient = new LocationClient(this, locationManager,locationManager);

        } else
        {
            Toast.makeText(getApplicationContext(), "Google Services unavailable", Toast.LENGTH_SHORT).show();
            canGetLocation = false ;
        }

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    protected void onStart() {
        super.onStart();
        locationClient.connect();
    }

    @Override
    protected void onStop() {
        locationClient.disconnect();
        super.onStop();
    }

    public void sendAlert(View view)
    {
        if(!canGetLocation)
        {
            return ;
        }
        captedtLocation = locationClient.getLastLocation();
        Toast.makeText(getApplicationContext(), "lat : "+captedtLocation.getLatitude()+" lng : "+captedtLocation.getLongitude(), Toast.LENGTH_SHORT).show();
        SentAlert alert = new SentAlert(ModeleArret.getInstance().findNearestTo(captedtLocation));
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        List<Fragment> tabs = new ArrayList<Fragment>() ;

        public SectionsPagerAdapter(FragmentManager fm)
        {
            super(fm);
            tabs.add(new SendAlertFragment());
            tabs.add(new fr.coding_ops.t2cradar.controler.ListAlertFragment());
        }

        @Override
        public Fragment getItem(int position)
        {
            if(tabs.size()> position) {
                return tabs.get(position);
            }
            return null ;
        }

        @Override
        public int getCount()
        {
            return tabs.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            return ((CodingOpsFragment)tabs.get(position)).getTitle();
        }
    }
}
