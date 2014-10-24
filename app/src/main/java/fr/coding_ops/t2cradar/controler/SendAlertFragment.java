package fr.coding_ops.t2cradar.controler;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.coding_ops.t2cradar.R;


public class SendAlertFragment extends Fragment implements CodingOpsFragment {

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     *
     * @return A new instance of fragment SendAlertFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SendAlertFragment newInstance() {
        return new SendAlertFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_send_alert, container, false);
    }

    @Override
    public String getTitle()
    {
        return "sendAlert" ;
    }


}
