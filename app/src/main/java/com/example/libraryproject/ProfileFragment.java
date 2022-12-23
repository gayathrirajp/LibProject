package com.example.libraryproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileFragment extends Fragment {
    //to get the count of clicks on change password button
    int count=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        TextView t_staffId,t_staffName,t_staffPhone,t_staffDOB;
        EditText staffPass;
        Button p_submit;
        ImageButton ib;
        ib=view.findViewById(R.id.p_changePassword);
        staffPass=view.findViewById(R.id.p_staffpassword);
        p_submit=view.findViewById(R.id.p_submit);
        t_staffId=view.findViewById(R.id.p_staffId);
        t_staffName=view.findViewById(R.id.p_staffName);
        t_staffPhone=view.findViewById(R.id.p_staffPhone);
        t_staffDOB=view.findViewById(R.id.p_StaffDOB);
        staffPass.setVisibility(view.INVISIBLE);
        p_submit.setVisibility(view.INVISIBLE);

        try {
            ResultSet set= businesslayer.StaffProfileLayer.staffDetails(LoginActivity.StaffId);
            set.next();
            t_staffId.setText("NM20LIB001");
            t_staffName.setText(set.getString(1));
            t_staffPhone.setText(set.getString(2));
            t_staffDOB.setText(set.getString(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }


        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                if(count%2==0){
                    staffPass.setVisibility(view.INVISIBLE);
                    p_submit.setVisibility(view.INVISIBLE);
                }
                else{
                    staffPass.setVisibility(view.VISIBLE);
                    p_submit.setVisibility(view.VISIBLE);
                }
            }
        });
        p_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    ResultSet set=businesslayer.StaffProfileLayer.staffChangePassword("NM20LIB001", staffPass.getText().toString());
                    set.next();
                    if(set.getString(1).equals("SUCCESS")){
                        staffPass.setVisibility(view.GONE);
                        p_submit.setVisibility(view.INVISIBLE);
                        //AlertDialog to confirm Password change
                        AlertDialog.Builder adProfile = new AlertDialog.Builder(getActivity());
                        adProfile.setTitle("Successful");
                        adProfile.setMessage("Password changed successfully!");
                        adProfile.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        AlertDialog dprofile=adProfile.create();
                        dprofile.show();
                        staffPass.setVisibility(view.INVISIBLE);
                        p_submit.setVisibility(view.INVISIBLE);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }
    //for log out
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        //inflate menu
        inflater.inflate(R.menu.library_logout,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.libLogout){
            //Alert Dialog to confirm logout
            AlertDialog.Builder ad=new AlertDialog.Builder(getActivity());
            ad.setCancelable(false);
            ad.setTitle("Alert!");
            ad.setMessage("Do you want to log out?");
            ad.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent iLogout=new Intent(getActivity(),LoginActivity.class);
                    startActivity(iLogout);
                    getActivity().finish();
                    dialogInterface.cancel();
                }
            });
            ad.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            AlertDialog a1=ad.create();
            a1.show();

        }
        return super.onOptionsItemSelected(item);
    }
}