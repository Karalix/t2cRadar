package fr.coding_ops.t2cradar.controler;



import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.coding_ops.t2cradar.R;


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
    public String getTitle()
    {
        return "listAlert";
    }

}
