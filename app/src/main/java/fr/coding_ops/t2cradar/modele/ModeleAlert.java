package fr.coding_ops.t2cradar.modele;

import java.util.ArrayList;
import java.util.List;

/**
 * Class managing all the current received alerts
 *
 * Created by Alix on 29/10/2014.
 */
public class ModeleAlert
{
    private static ModeleAlert instance = null ;

    private List<ReceivedAlert> alerts = null ;

    private ModeleAlert()
    {
        alerts = new ArrayList<ReceivedAlert>();
    }

    public static ModeleAlert getInstance()
    {
        if(instance == null)
        {
            instance = new ModeleAlert();
        }
        return instance ;
    }

    public void addAlert(ReceivedAlert alert)
    {
        alerts.add(alert);
    }

    /**
     * Remove all the alerts older than 10 minutes (600 000 ms)
     */
    public void cleanAlerts()
    {
        List<ReceivedAlert> listToRemove = new ArrayList<ReceivedAlert>();
        for (ReceivedAlert alert : alerts)
        {
            if (System.currentTimeMillis()>= alert.getDate().getTime()+alert.getBonusTime().getTime()+600000)
            {
                listToRemove.add(alert);
            }
        }
        for (ReceivedAlert alert : listToRemove)
        {
            alerts.remove(alert);
        }
    }

}
