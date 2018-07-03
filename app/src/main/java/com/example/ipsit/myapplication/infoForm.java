package com.example.ipsit.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class infoForm extends AppCompatActivity {

    EditText e1,e2,e3,e4;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_form);



        e1 = (EditText) findViewById(R.id.description);
        e2 = (EditText) findViewById(R.id.phone_number);
        e3 = (EditText) findViewById(R.id.address);
        e4 = (EditText) findViewById(R.id.free_hours);
        button = (Button) findViewById(R.id.button_submit);


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                 if (validat()){
                     String name = getIntent().getStringExtra("category");
                     Intent i = new Intent(getApplicationContext(),cameracamera.class);
                     Bundle extras = new Bundle();
                     extras.putString("Description",e1.getText().toString());
                     extras.putString("Phone_Number",e2.getText().toString());
                     extras.putString("Address",e3.getText().toString());
                     extras.putString("Free_Hours",e4.getText().toString());
                     i.putExtra("category",name);
                     i.putExtras(extras);
                     startActivity(i);
                 }

            }
        });
    }

    boolean validat()
    {
        String des = e1.getText().toString().trim();
        String pno = e2.getText().toString().trim();
        String add = e3.getText().toString().trim();
        String hrs = e4.getText().toString().trim();
        if (des.isEmpty()) {
            e1.setError("Description is required");
            e1.requestFocus();
            return false;
        }
        if (pno.isEmpty()) {
            e2.setError("Contact is required");
            e2.requestFocus();
            return false;
        }
        if(!isValidPno(pno))
        {
            e2.setError("Enter Valid Contact");
            e2.requestFocus();
            return false;
        }
        if (add.isEmpty()) {
            e3.setError("Address is required");
            e3.requestFocus();
            return false;
        }
        if (hrs.isEmpty()) {
            e4.setError("Free Hours is required");
            e4.requestFocus();
            return false;
        }
        return true;
    }

    boolean isValidPno(String pno)
    {
        String pno1 = "^[0-9]{10}$";

        Pattern pattern = Pattern.compile(pno1);
        Matcher matcher = pattern.matcher(pno);
        return matcher.matches();
    }
}
