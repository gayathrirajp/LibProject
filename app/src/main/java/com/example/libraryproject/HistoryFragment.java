package com.example.libraryproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class HistoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        //enable option menu for this fragment
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        //inflate menu
        inflater.inflate(R.menu.history_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        RecyclerView recyclerView;
        ArrayList<String> usn,name,bookname,staffname;
        MyAdapter adapter;
        usn=new ArrayList<>();
        name=new ArrayList<>();
        bookname=new ArrayList<>();
        staffname=new ArrayList<>();
        recyclerView =getView().findViewById(R.id.recyclerView);
        adapter=new MyAdapter(getActivity(),usn,name,bookname,staffname);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //handle menu item click
        int id=item.getItemId();
        ResultSet set = null;
        if(id==R.id.iIssue_td){
            try {
                 set= businesslayer.HistoryLayer.issueHistory();
                while(set.next()){
                    usn.add(set.getString(1));
                    name.add(set.getString(2));
                    bookname.add(set.getString(3));
                    staffname.add(set.getString(4));

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }



        }
        if(id==R.id.iReturn_td){

            try {
                set= businesslayer.HistoryLayer.returnHistory();
                while(set.next()){
                    usn.add(set.getString(1));
                    name.add(set.getString(2));
                    bookname.add(set.getString(3));
                    staffname.add(set.getString(4));

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(id==R.id.iDue_td){
            try {
                set= businesslayer.HistoryLayer.dueHistory();
                while(set.next()){
                    usn.add(set.getString(1));
                    name.add(set.getString(2));
                    bookname.add(set.getString(3));
                    staffname.add(set.getString(4));

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


        return super.onOptionsItemSelected(item);
    }
}