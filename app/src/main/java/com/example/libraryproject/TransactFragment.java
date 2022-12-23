package com.example.libraryproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;

public class TransactFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_transact, container, false);
       Button bScan=view.findViewById(R.id.bScan);
       ImageView iv=view.findViewById(R.id.iv);
       iv.setImageResource(R.drawable.nitte_logo);
       TextView tWelcome=view.findViewById(R.id.cardTxtWelcome);
       TextView tDate=view.findViewById(R.id.cardTxtDate);
       TextView tQuote=view.findViewById(R.id.cardTxtQuote);
       Date d=new Date();
       CharSequence s= DateFormat.format("MMMM d, yyyy ", d.getTime());
       tDate.setText(s.toString());
       tWelcome.setText("Welcome to the app");
       tQuote.setText("Some quote");
       //Button to scan Student QR
       bScan.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i = new Intent(getActivity(), TransactActivity.class);
               //getActivity--return the context of activity the fragment is currently associated with
               startActivity(i);
               }
       });
       return view;
    }
}