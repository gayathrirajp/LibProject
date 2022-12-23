package com.example.libraryproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import adapterlayer.FindBookAdapter;
import businesslayer.StudentLayer;


public class SearchBookFragment extends Fragment {

    ListView listBranches;
    ArrayAdapter<String> adp;
    ArrayList<String> arr, bookName, author, copiesAvailable;
    //TableLayout tbl;
    FindBookAdapter adapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_search_book, container, false);
        listBranches=view.findViewById(R.id.listBranches);
        //tbl= findViewById(R.id.tblBooks);

        String str="AIML CSE ISE EC EEE MECH BT MCA";
        arr= new ArrayList<String>(Arrays.asList(str.split(" ")));

        adp=new ArrayAdapter<String>(getActivity(), android.R.layout.select_dialog_item, arr);
        listBranches.setAdapter(adp);
        listBranches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String info= ((TextView)view).getText().toString();
                /*TextView txt= findViewById(R.id.txtView);
                txt.setText(info);*/
                //getBooks(info);
                bookName= new ArrayList<String>();
                author= new ArrayList<String>();
                copiesAvailable= new ArrayList<String>();
                recyclerView= view.findViewById(R.id.recyclerView);
                adapter = new FindBookAdapter(getActivity(), bookName , author, copiesAvailable);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                displayData(info);

            }
        });
        return view;
    }

    void displayData(String info){
        ResultSet rSet= StudentLayer.getBooksOfBranch(info);
        if(rSet!= null){
            try{
                while(rSet.next()){
                    bookName.add("Title: "+rSet.getString(1));
                    author.add("Author: "+rSet.getString(2));
                    copiesAvailable.add("Copies Available: "+rSet.getString(3));
                }
            }catch (Exception e){
                Log.e("Error", e.getMessage());
            }
        }
    }
}