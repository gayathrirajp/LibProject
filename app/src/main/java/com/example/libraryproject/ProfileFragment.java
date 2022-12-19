package com.example.libraryproject;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
            ResultSet set= businesslayer.StaffProfileLayer.staffDetails("NM20LIB001");
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
                staffPass.setVisibility(view.VISIBLE);
               p_submit.setVisibility(view.VISIBLE);

            }
        });
        p_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int valresult=businesslayer.StaffProfileLayer.staffChangePassword("NM20LIB001", staffPass.getText().toString());
                    if(valresult==0){
                        staffPass.setVisibility(view.GONE);
                        p_submit.setVisibility(view.INVISIBLE);
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
}