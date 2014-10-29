package fr.coding_ops.t2cradar.modele.dataloader;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import fr.coding_ops.t2cradar.R;
import fr.coding_ops.t2cradar.modele.Arret;
import fr.coding_ops.t2cradar.modele.ModeleArret;
import fr.coding_ops.t2cradar.modele.MyApplication;
import fr.coding_ops.t2cradar.modele.ReceivedAlert;

/**
 * Class JSONReader able to read json files
 *
 * Created by Alix on 25/10/2014.
 */
public class JSONReader {

    private static JSONReader instance = null ;

    private JSONReader()
    {

    }

    public static JSONReader getInstance()
    {
        if(instance == null)
        {
            instance = new JSONReader();
        }

        return instance ;
    }


    /**
     * Open the json file containing the Arrets and returns a List containing all the Arrets.
     *
     * @return the List of Arret
     */
    public List<Arret> readArrets()  {
        List<Arret> arrets = null ;
        InputStream is = MyApplication.getAppContext().getResources().openRawResource(R.raw.arrets);
        InputStreamReader isr = new InputStreamReader(is);
        JsonReader reader = new JsonReader(isr);
        try
        {
            arrets = readArrayArrets(reader);
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return arrets ;
    }

    /**
     * Reads the array of Arret inside the json file
     *
     * @param reader the reader of the json file
     * @return the fulfilled array of Arret
     */
    private List<Arret> readArrayArrets(JsonReader reader) {
        List<Arret> arrets = new ArrayList<Arret>();

        try {
            reader.beginArray();
            while (reader.hasNext())
            {
                arrets.add(readAnArret(reader));
            }
            reader.endArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arrets ;
    }

    /**
     * Reads an object Arret from json file
     *
     * @param reader the reader of the json file
     * @return an object Arret
     */
    private Arret readAnArret(JsonReader reader)
    {
        Arret arret = null ;
        double latitude = 0 ;
        double longitude = 0 ;
        String name = null ;
        String id = null ;

        try
        {
            reader.beginObject();
            while(reader.hasNext())
            {
                String attribute = reader.nextName();
                if(attribute.equals("id"))
                {
                    id = reader.nextString();
                }
                else if (attribute.equals("name"))
                {
                    name = reader.nextString();
                }
                else if (attribute.equals("latitude"))
                {
                    latitude = reader.nextDouble();
                }
                else if (attribute.equals("longitude"))
                {
                    longitude = reader.nextDouble();
                }
                else
                {
                    reader.skipValue();
                }
            }
            reader.endObject();

            arret = new Arret(id,name,latitude,longitude);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return arret ;

    }

    public List<ReceivedAlert> readAlerts()
    {
        List<ReceivedAlert> alerts = new ArrayList<ReceivedAlert>();

        InputStream is = MyApplication.getAppContext().getResources().openRawResource(R.raw.sample_list_alerts);
        InputStreamReader isr = new InputStreamReader(is);
        JsonReader reader = new JsonReader(isr);
        try
        {
            alerts = readArrayAlerts(reader);
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return alerts ;
    }

    private List<ReceivedAlert> readArrayAlerts(JsonReader reader)
    {
        List<ReceivedAlert> alerts = new ArrayList<ReceivedAlert>();

        try {
            reader.beginArray();
            while (reader.hasNext())
            {
                alerts.add(readAnAlert(reader));
            }
            reader.endArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return alerts ;
    }

    private ReceivedAlert readAnAlert(JsonReader reader)
    {

        ReceivedAlert alert = null ;

        String idAlert = null ;
        String idArret = null ;
        long date = 0 ;
        long bonusTime = 0;
        long upvote = 0 ;
        long downvote = 0 ;
        List<String> messages = null ;

        try
        {
            reader.beginObject();
            while(reader.hasNext())
            {
                String attribute = reader.nextName();
                if(attribute.equals("idAlert"))
                {
                    idAlert = reader.nextString();
                }
                else if (attribute.equals("idArret"))
                {
                    idArret = reader.nextString();
                }
                else if (attribute.equals("date"))
                {
                    date = reader.nextLong();
                }
                else if (attribute.equals("bonus_time"))
                {
                    bonusTime = reader.nextLong();
                }
                else if (attribute.equals("upvote"))
                {
                    upvote = reader.nextLong();
                }
                else if (attribute.equals("downvote"))
                {
                    downvote = reader.nextLong();
                }
                else if (attribute.equals("messages"))
                {
                    messages = readAlertMessages(reader) ;
                }
                else
                {
                    reader.skipValue();
                }
            }
            reader.endObject();

            alert = new ReceivedAlert(idAlert, ModeleArret.getInstance().findById(idArret), new Date(date), new Date(bonusTime), upvote, downvote, messages);

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return alert ;
    }

    private List<String> readAlertMessages(JsonReader reader)
    {
        List<String> messages = new ArrayList<String>();

        try{
            reader.beginArray();
            while(reader.hasNext())
            {
                messages.add(reader.nextString());
            }
            reader.endArray();

        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return messages ;
    }
}
