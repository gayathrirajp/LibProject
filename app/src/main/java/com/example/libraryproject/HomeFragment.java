package com.example.libraryproject;


import static com.example.libraryproject.StudentActivity.StudentUsn;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.sql.ResultSet;
import java.sql.SQLException;

import businesslayer.StudentLayer;

public class HomeFragment extends Fragment {

    TextView txtName, txtUsn, txtBatch, txtBranch, txtPhone;
    Button btnChangePassword;
    ImageView iv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        txtName=view.findViewById(R.id.txtCardName);
        txtUsn=view.findViewById(R.id.txtCardUsn);
        txtBatch=view.findViewById(R.id.txtCardBatch);
        txtBranch=view.findViewById(R.id.txtCardBranch);
        txtPhone=view.findViewById(R.id.txtCardPhone);
        iv=view.findViewById(R.id.imageView);

        //TENTATIVE: Referencing Buttons
        btnChangePassword=view.findViewById(R.id.btnChangePassword);

        //Fetching Details of student and setting them on the CardView
        getStudentDetails();

        //TENTATIVE: Button leading to the history activity

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k= new Intent(getActivity(), ChangePasswordActivity.class);
                k.putExtra("usn", StudentUsn);
                startActivity(k);
            }
        });
        return view;
    }

    void getStudentDetails(){
        ResultSet s= StudentLayer.getProfile(StudentUsn);
        if(s!=null){
            try {
                s.next();
                txtName.setText(s.getString(2));
                txtUsn.setText(s.getString(1));
                txtBatch.setText(s.getString(3));
                txtBranch.setText(s.getString(4));
                txtPhone.setText(s.getString(5));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            MultiFormatWriter writer =new MultiFormatWriter();
            try {
                BitMatrix matrix=writer.encode(StudentUsn, BarcodeFormat.QR_CODE,350,350);
                BarcodeEncoder encoder =new BarcodeEncoder();
                Bitmap bitmap =encoder.createBitmap(matrix);
                iv.setImageBitmap(bitmap);
             /*   InputMethodManager manager =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(txtName.getApplicationWindowToken(),0);*/
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
    }

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