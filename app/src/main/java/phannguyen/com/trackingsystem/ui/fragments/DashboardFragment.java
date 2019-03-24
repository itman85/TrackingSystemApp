package phannguyen.com.trackingsystem.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import phannguyen.com.trackingsystem.R;
import phannguyen.com.trackingsystem.Utils;
import phannguyen.com.trackingsystem.ui.HomeActivity;
import phannguyen.com.trackingsystem.ui.NavigationHost;
import phannguyen.com.trackingsystem.ui.components.DashboardItemLayout;
import phannguyen.com.trackingsystem.ui.components.NavigationIconClickListener;
import phannguyen.com.trackingsystem.ui.parse.ParseHelper;

public class DashboardFragment extends Fragment {
    DashboardItemLayout contractDB;
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.dashboard_fragment, container, false);
        // Set up the toolbar
        setUpToolbar(view);
        contractDB = view.findViewById(R.id.contractsDB);
        contractDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationHost) getActivity()).navigateTo(new ContractsFragment(), true); // Navigate to the next Fragment
            }
        });
        TextView tvUser = view.findViewById(R.id.tvUser);
        String sourceString = "<b>" + "Hi " + "</b>, " + Utils.getLoginUserFullName(this.getActivity());
        tvUser.setText(Html.fromHtml(sourceString));
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }

        toolbar.setNavigationOnClickListener(new NavigationIconClickListener(getContext(), view.findViewById(R.id.dashboard_grid),new AccelerateDecelerateInterpolator(),
                getContext().getResources().getDrawable(R.drawable.menu), // Menu open icon
                getContext().getResources().getDrawable(R.drawable.close_menu))); // Menu close icon
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
               handleLogout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void handleLogout(){
        new AlertDialog.Builder(this.getActivity()).setTitle("Confirmation")
                .setMessage("Do you want to logout?")
                .setPositiveButton("Yes",
                        (dialog, which) -> {
                            ((HomeActivity) getActivity()).removeLocationUpdate();
                            ParseHelper.submitUserStatusToServer(Utils.getLoginUser(getActivity()),0);
                            Utils.logoutAction(getActivity());
                            // Perform Action & Dismiss dialog
                            dialog.dismiss();
                        })
                .setNegativeButton("No", (dialog, which) -> {
                    // Do nothing
                    dialog.dismiss();
                })
                .create()
                .show();
    }
}
