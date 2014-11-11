package fr.coding_ops.t2cradar.modele;

import android.location.Location;

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
        float result = 0 ;
        Location arret = new Location("arret");
        for (int i = 0 ; i<arrets.size() ; i++)
        {
            arret.setLatitude(arrets.get(i).getLatitude());
            arret.setLongitude(arrets.get(i).getLongitude());
            result = location.distanceTo(arret);
            if(distance == null)
            {
                distance = new Float(result);
            }
            if (result<= distance)
            {
                distance = result;
                index = i ;
            }
        }
        return arrets.get(index) ;
    }


    /**
     * Gets the distance in meters from location to arret
     * @param location the initial location
     * @param arret the arret we want to know the distance to
     * @return the distance in meters from location to arret
     */
    public float findDistanceTo(Location location, Arret arret)
    {
        Location locationArret = new Location("arret");
        locationArret.setLatitude(arret.getLatitude());
        locationArret.setLongitude(arret.getLongitude());

        return location.distanceTo(locationArret);
    }


    /**
     * Gets the Arret characterized by a specific ID
     * @param id the id of the the Arret we are looking for
     * @return the Arret the given id, or null if none found
     */
    public Arret findById(String id)
    {
        for(Arret a : arrets)
        {
            if (a.getId().equals(id))
            {
                return a ;
            }
        }

        return null ;
    }

    /**
     * Gets the Arret characterized by a specific name
     * @param name the name of the first Arret with the corresponding name
     * @return the Arret the given id, or null if none found
     */
    public Arret findByName(String name)
    {
        for(Arret a : arrets)
        {
            if (a.getName().equals(name))
            {
                return a ;
            }
        }

        return null ;
    }

}
