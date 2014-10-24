package fr.coding_ops.t2cradar.modele;

import com.google.android.gms.location.Geofence;

/**
 * Created by Alix on 22/10/2014.
 */
public class Arret  {

    private Geofence geofence = null ;
    private String name = null ;

    public Arret(Geofence geofence, String name) {
        this.geofence = geofence;
        this.name = name;
    }

    public Geofence getGeofence() {
        return geofence;
    }

    public String getName() {
        return name;
    }

}
