package fr.coding_ops.t2cradar.controler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationRequest;

import fr.coding_ops.t2cradar.R;
import fr.coding_ops.t2cradar.modele.AlertManager;
import fr.coding_ops.t2cradar.modele.Arret;
import fr.coding_ops.t2cradar.modele.ModeleAlert;
import fr.coding_ops.t2cradar.modele.ModeleArret;
import fr.coding_ops.t2cradar.modele.ReceivedAlert;
import fr.coding_ops.t2cradar.modele.SentAlert;
import fr.coding_ops.t2cradar.modele.dataloader.JSONReader;


public class sendAlertActivity extends Activity implements ActionBar.TabListener {



    private LocationClient locationClient = null ;


    private Location currentLocation = null ;

    LocationRequest locRequest = null ;

    MyLocationManager locationManager = null ;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;


    /**
     * Gets the LocationClient.
     * @return the locationClient
     */
    public LocationClient getLocationClient()
    {
        return locationClient;
    }


    /**
     * Gets the current location
     * @return the current location
     */
    public Location getCurrentLocation()
    {
        return currentLocation;
    }

    /**
     * Sets the current location to a new value
     * @param currentLocation the new Location
     */
    public void setCurrentLocation(Location currentLocation)
    {
        this.currentLocation = currentLocation;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_alert);


        generateTabs();



        for(Arret a : JSONReader.getInstance().readArrets())
        {
            ModeleArret.getInstance().addArret(a);
        }

        for (ReceivedAlert ra : JSONReader.getInstance().readAlerts())
        {
            ModeleAlert.getInstance().addAlert(ra);
        }

        checkGooglePlayServicesAvailbility();


    }



    /**
     * Code generated by Google to generate tabs
     */
    private void generateTabs()
    {

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
    }

    /**
     * Checks if Google play services are available
     *
     */
    private void checkGooglePlayServicesAvailbility()
    {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        // If Google Play services is available
        if (ConnectionResult.SUCCESS == resultCode) {
            // In debug mode, log the status
            Toast.makeText(getApplicationContext(), "Google Services available", Toast.LENGTH_SHORT).show();

            setUpUpdates();
        } else
        {
            Toast.makeText(getApplicationContext(), "Google Services unavailable", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Instanciates the LocationRequest field and the MyLocationManager
     * then sets up the priority and the intervals of the location requests
     * finally intaciates the locationClient using the locationManager
     */
    private void setUpUpdates()
    {
        locRequest = LocationRequest.create();
        locationManager = new MyLocationManager(this, locRequest);


        locRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        locRequest.setInterval(5000);

        locRequest.setFastestInterval(1000);

        locationClient = new LocationClient(this, locationManager,locationManager);
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
        if(locationClient.isConnected())
        {
            locationClient.removeLocationUpdates(locationManager);
        }
        locationClient.disconnect();
        super.onStop();
    }

    /**
     * Called method when user push the button to send an alert
     *
     * @param view
     */
    public void sendAlert(View view)
    {
        if(!canGetLocation()||currentLocation==null)
        {
            return ;
        }
        Toast.makeText(getApplicationContext(), ModeleArret.getInstance().findNearestTo(currentLocation).getName(), Toast.LENGTH_SHORT).show();
        AlertManager.getInstance().sendAlert(new SentAlert(ModeleArret.getInstance().findNearestTo(currentLocation)));
    }

    /**
     * Checks if Location services are enabled either GPS or network
     *
     * @return true if the services are activated, false otherwise
     */
    private boolean canGetLocation()
    {
        boolean networkEnabled = false ;
        boolean gpsEnabled = false ;

        LocationManager lm = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);

        networkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if(!gpsEnabled && !networkEnabled)
        {


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(this.getResources().getString(R.string.gps_network_not_enabled));
            builder.setPositiveButton(this.getResources().getString(R.string.open_location_settings), new DialogInterface.OnClickListener()
            {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt)
                {
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(myIntent);
                    //get gps
                }
            });
            builder.setNegativeButton(this.getApplicationContext().getString(R.string.Cancel), new DialogInterface.OnClickListener()
            {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt)
                {
                    // TODO Auto-generated method stub

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();




           Toast.makeText(getApplicationContext(), "Veuillez activer les services de localisation.", Toast.LENGTH_SHORT).show();
            return false ;
        }

        return true ;
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
