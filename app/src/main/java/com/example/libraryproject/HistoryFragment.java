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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import adapterlayer.LibraryHistoryAdapter;

public class HistoryFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //enable option menu for this fragment
        setHasOptionsMenu(true);
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
        LibraryHistoryAdapter adapter;
        usn=new ArrayList<>();
        name=new ArrayList<>();
        bookname=new ArrayList<>();
        staffname=new ArrayList<>();
        recyclerView =getView().findViewById(R.id.recyclerView);
        adapter=new LibraryHistoryAdapter(getActivity(),usn,name,bookname,staffname);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //handle menu item click
        int id=item.getItemId();
        ResultSet set = null;
        //for issue history
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
        //for return history
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
        //for due history
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