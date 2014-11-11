package fr.coding_ops.t2cradar.controler;



import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import fr.coding_ops.t2cradar.R;
import fr.coding_ops.t2cradar.modele.ModeleAlert;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.view.CardListView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListAlertFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ListAlertFragment extends Fragment implements CodingOpsFragment {

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     * @return A new instance of fragment ListAlertFragment.
     */
    public static ListAlertFragment newInstance() {
        return new ListAlertFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_alert, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);



        ArrayList<Card> cards = new ArrayList<Card>();

        for(int i = 0 ; i < 5 ; i++)
        {
            //Create a Card
            Card card = new Card(getActivity());
            CardThumbnail thumb = new CardThumbnail(getActivity());
            thumb.setDrawableResource(R.drawable.ic_launcher);

            card.addCardThumbnail(thumb);

            //Create a CardHeader
            CardHeader header = new CardHeader(getActivity());
            header.setTitle("Coucou");

            //Add Header to card
            card.addCardHeader(header);

            cards.add(card);
        }


        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(), ModeleAlert.getInstance().getListCardAlerts());

        CardListView listView = (CardListView) getView().findViewById(R.id.list_alerts_cards);
        if (listView!=null){
            listView.setAdapter(mCardArrayAdapter);
        }
    }

    @Override
    public String getTitle()
    {
        return "listAlert";
    }

}
