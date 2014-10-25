package fr.coding_ops.t2cradar.modele;

import android.location.Location;

import com.google.android.gms.internal.di;
import com.google.android.gms.location.Geofence;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alix on 22/10/2014.
 */
public class ModeleArret {

    private static ModeleArret instance = null ;

    private List<Arret> arrets = null ;


    /**
     * Private constructor
     */
    private ModeleArret() {
        arrets = new ArrayList<Arret>();
    }

    /**
     * Getter of the singleton ModeleArret
     *
     * @return the instance of ModeleArret
     */
    public static ModeleArret getInstance()
    {
        if (instance == null)
        {
            instance = new ModeleArret() ;
        }

        return instance ;
    }

    /**
     * Add an Arret to the ModeleArret
     */
    public void addArret(Arret a)
    {
        this.arrets.add(a);
    }

    /**
     * Find the nearest Arret to a given location
     *
     * @param location the location
     * @return the nearest Arret
     */
    public Arret findNearestTo(Location location)
    {
        if(arrets.isEmpty())
        {
            return null ;
        }
        Float distance = null ;
        int index = 0 ;
        float[] results = new float[3];
        for (int i = 0 ; i<arrets.size() ; i++)
        {
            Location.distanceBetween(arrets.get(i).getLatitude(), arrets.get(i).getLongitude(), location.getLatitude(), location.getLongitude(), results);
            if(distance == null)
            {
                distance = new Float(results[0]);
            }
            if (results[0]<= distance)
            {
                distance = results[0];
                index = i ;
            }
        }
        return arrets.get(index) ;
    }
}
