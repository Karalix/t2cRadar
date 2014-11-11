package fr.coding_ops.t2cradar.controler;

import android.os.Bundle;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fr.coding_ops.t2cradar.R;
import fr.coding_ops.t2cradar.modele.ModeleArret;
import fr.coding_ops.t2cradar.modele.MyApplication;


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
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        setUpDistanceField();
    }

    @Override
    public String getTitle()
    {
        return "sendAlert" ;
    }

    private void setUpDistanceField()
    {
        TextView tv1 = (TextView) getView().findViewById(R.id.currentArretText);
        tv1.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3)
            {
                //Nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3)
            {
                //Still Nothing
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                TextView tv2 = (TextView) getView().findViewById(R.id.currentArretDistance);
                tv2.setText(String.valueOf(ModeleArret.getInstance().findDistanceTo(((sendAlertActivity) getActivity()).getCurrentLocation(), ModeleArret.getInstance().findByName(editable.toString()))));
            }
        });
    }


}
