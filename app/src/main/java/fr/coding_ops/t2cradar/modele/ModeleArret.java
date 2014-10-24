package fr.coding_ops.t2cradar.modele;

import com.google.android.gms.location.Geofence;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alix on 22/10/2014.
 */
public class ModeleArret {

    private List<Arret> arrets = new ArrayList<Arret>();

    public ModeleArret() {
        Geofence.Builder builder = new Geofence.Builder();
        builder.setCircularRegion(45.747,3.1, 1000);

        //arrets.add(new Arret());
    }
}
