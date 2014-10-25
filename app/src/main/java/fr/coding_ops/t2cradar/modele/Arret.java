package fr.coding_ops.t2cradar.modele;

import android.location.Location;

/**
 * Created by Alix on 22/10/2014.
 */
public class Arret  {

    private double latitude ;
    private double longitude ;
    private String name = null ;
    private String id = null ;

    /**
     * Constructor for an Arret
     * @param id the identifier of the Arret
     * @param name the name of the Arret
     * @param latitude the latitude of the Arret
     * @param longitude the longitude of the Arret
     */
    public Arret(String id, String name, double latitude, double longitude) {

        this.latitude = latitude ;
        this.longitude = longitude ;
        this.name = name;
        this.id = id ;
    }

    /**
     * Gets the Name of the Arret
     *
     * @return the name of the Arret
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the latitude of the Arret
     *
     * @return the latitude of the Arret
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Gets the longitude of the Arret
     *
     * @return the longitude of the Arret
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Gets the identifier of the Arret
     *
     * @return the identifier
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the distance in meters to the given coordinates
     * @param latitude latitude
     * @param longitude longitude
     * @return the distance in meters between the Arret and the coordinates given in parameters
     */
    public double distanceTo(double latitude, double longitude)
    {
        float[] results = new float[3];
        Location.distanceBetween(this.getLatitude(), this.getLongitude(), latitude, longitude, results);
        return results[0];
    }
}
