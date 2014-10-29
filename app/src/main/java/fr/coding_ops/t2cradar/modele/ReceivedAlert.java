package fr.coding_ops.t2cradar.modele;

import java.util.Date;
import java.util.List;

/**
 * Class representing an alert received by the users
 *
 * Created by Alix on 29/10/2014.
 */
public class ReceivedAlert
{


    private String idAlert = null ;

    private Arret arret = null ;

    private Date date = null ;

    private Date bonusTime = null ;

    private long upvote = 0 ;

    private long downvote = 0 ;

    private List<String> messages = null ;


    public ReceivedAlert(String idAlert, Arret arret, Date date, Date bonusTime, long upvote, long downvote, List<String> messages)
    {
        this.idAlert = idAlert;
        this.arret = arret;
        this.date = date;
        this.bonusTime = bonusTime;
        this.upvote = upvote;
        this.downvote = downvote;
        this.messages = messages;
    }

    public String getIdAlert()
    {
        return idAlert;
    }

    public Arret getArret()
    {
        return arret;
    }

    public Date getDate()
    {
        return date;
    }

    public Date getBonusTime()
    {
        return bonusTime;
    }

    public long getUpvote()
    {
        return upvote;
    }

    public long getDownvote()
    {
        return downvote;
    }

    public List<String> getMessages()
    {
        return messages;
    }


}
