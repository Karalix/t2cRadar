package fr.coding_ops.t2cradar.modele;

import java.util.ArrayList;
import java.util.List;

import fr.coding_ops.t2cradar.R;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;

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

    public ArrayList<Card> getListCardAlerts()
    {
        ArrayList<Card> list = new ArrayList<Card>();
        for(ReceivedAlert a: alerts)
        {
            //Create a Card
            Card card = new Card(MyApplication.getAppContext());
            CardThumbnail thumb = new CardThumbnail(MyApplication.getAppContext());
            thumb.setDrawableResource(R.drawable.ic_launcher);

            card.addCardThumbnail(thumb);
            card.setTitle("Indice de confiace : "+String.valueOf(a.getUpvote()/(a.getUpvote()+a.getDownvote())));

            //Create a CardHeader
            CardHeader header = new CardHeader(MyApplication.getAppContext());
            header.setTitle(a.getArret().getName());

            //Add Header to card
            card.addCardHeader(header);

            list.add(card);
        }

        return  list ;

    }
}
