package fr.coding_ops.t2cradar.modele;

/**
 * Created by Alix on 22/10/2014.
 */
public class Arret  {

    private double latitude ;
    private double longitude ;
    private String name = null ;
    private String id = null ;

    public Arret(String id, String name, double latitude, double longitude) {

        this.latitude = latitude ;
        this.longitude = longitude ;
        this.name = name;
        this.id = id ;
    }


    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

}
