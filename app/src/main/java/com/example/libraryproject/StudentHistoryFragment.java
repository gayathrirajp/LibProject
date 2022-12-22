package com.example.libraryproject;

import static com.example.libraryproject.StudentActivity.StudentUsn;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.Toast;

import java.sql.ResultSet;
import java.util.ArrayList;

import businesslayer.HistoryAdapter;
import businesslayer.StudentLayer;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StudentHistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StudentHistoryFragment extends Fragment {

    TableLayout tbl;
    RecyclerView recyclerView;
    ArrayList<String> bookName,issuedOn, returnedOn, dueDate;
    HistoryAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StudentHistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StudentHistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StudentHistoryFragment newInstance(String param1, String param2) {
        StudentHistoryFragment fragment = new StudentHistoryFragment();
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
        View view=inflater.inflate(R.layout.fragment_student_history, container, false);
        bookName= new ArrayList<String>();
        issuedOn= new ArrayList<String>();
        returnedOn= new ArrayList<String>();
        dueDate= new ArrayList<String>();
        recyclerView= view.findViewById(R.id.recyclerHistory1);
        adapter = new HistoryAdapter(getActivity(), bookName , issuedOn, returnedOn, dueDate);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        displayData();
        return view;
    }

    void displayData(){
        ResultSet s= StudentLayer.getHistory(StudentUsn);
        if(s!=null){
            try{
                while(s.next()){
                    bookName.add("Title: "+s.getString(2));
                    issuedOn.add("Issued on: "+s.getString(4));
                    returnedOn.add("Returned on: "+s.getString(7));
                    dueDate.add("Due date: "+s.getString(5));
                }

            }
            catch(Exception e){
                Toast.makeText(getActivity(), "Error occured", Toast.LENGTH_LONG).show();
            }

        }
    }
}