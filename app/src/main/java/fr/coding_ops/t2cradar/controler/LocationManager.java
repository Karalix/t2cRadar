package fr.coding_ops.t2cradar.controler;

import android.app.Activity;
import android.content.IntentSender;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;

/**
 * Created by Alix on 22/10/2014.
 */
public class LocationManager implements GooglePlayServicesClient.ConnectionCallbacks, GooglePlayServicesClient.OnConnectionFailedListener {

    private Activity activity;

    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;


    public LocationManager(Activity activity)
    {
        this.activity = activity ;
    }

    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(activity.getApplicationContext(), "Le service de géolocalisation est connecté.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisconnected() {
        Toast.makeText(activity.getApplicationContext(), "Le service de géolocalisation est déconnecté.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult( activity, CONNECTION_FAILURE_RESOLUTION_REQUEST);
                /*
                 * Thrown if Google Play services canceled the original
                 * PendingIntent
                 */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */
            Toast.makeText(activity.getApplicationContext(), "La connection au service de géolocalisation a échoué : "+connectionResult.getErrorCode(), Toast.LENGTH_SHORT).show();
        }

    }
}
