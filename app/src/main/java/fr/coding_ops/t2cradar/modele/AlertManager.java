package fr.coding_ops.t2cradar.modele;

/**
 * Singleton class sending alerts
 *
 * Created by Alix on 01/11/2014.
 */
public class AlertManager
{
    private static AlertManager instance = null ;

    private AlertManager()
    {

    }

    public static AlertManager getInstance()
    {
        if(instance == null)
        {
            instance =new AlertManager();
        }

        return instance ;
    }

    public void sendAlert(SentAlert alert)
    {
        //TODO
    }



}
