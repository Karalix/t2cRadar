package fr.coding_ops.t2cradar.modele;

import android.location.Location;

import java.util.Date;

/**
 * Created by Alix on 22/10/2014.
 */
public class SentAlert {
    private Date alertDate ;
    private Arret arret ;

    public SentAlert( Arret arret) {
        this.alertDate = new Date(System.currentTimeMillis());
        this.arret = arret;
    }
}
