package phannguyen.com.trackingsystem.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import phannguyen.com.trackingsystem.R;
import phannguyen.com.trackingsystem.ui.components.ContractListViewAdapter;

public class ContractsFragment extends Fragment {
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.contracts_fragment, container, false);
        // Set up the toolbar
        setUpToolbar(view);
        setupListContracts(view);
        return view;
    }



    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    private void setupListContracts(View view){
        ListView lstview= view.findViewById(R.id.listContract);
        // Inflate header view
        ViewGroup headerView = (ViewGroup)getLayoutInflater().inflate(R.layout.header, lstview,false);
        // Add header view to the ListView
        lstview.addHeaderView(headerView);
        // Get the string array defined in strings.xml file
        String[] items=getResources().getStringArray(R.array.list_items);
        // Create an adapter to bind data to the ListView
        ContractListViewAdapter adapter=new ContractListViewAdapter(this.getActivity(),R.layout.rowlayout,R.id.txtname,items);
        // Bind data to the ListView
        lstview.setAdapter(adapter);
    }

}
