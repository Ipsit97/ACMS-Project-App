package com.example.ipsit.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Categories extends AppCompatActivity {

    private static final String TAG ="Categories" ;
    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);


        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        b5 = (Button) findViewById(R.id.button5);
        b6 = (Button) findViewById(R.id.button6);


        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),infoForm.class);
                i.putExtra("category","Books&Stationary");
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),infoForm.class);
                i.putExtra("category","Clothes");
                startActivity(i);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),infoForm.class);
                i.putExtra("category","Toys");
                startActivity(i);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),infoForm.class);
                i.putExtra("category","Food");
                startActivity(i);
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),infoForm.class);
                i.putExtra("category","General");
                startActivity(i);
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),infoForm.class);
                i.putExtra("category","MedicalSupplies");
                startActivity(i);
            }
        });


    }

    public void onBackPressed()
    {
        Intent i = new Intent(Categories.this,ThreeButtons.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        clearMemory();
    }

    public  void clearMemory(){


        b1.setOnClickListener(null);
        b2.setOnClickListener(null);
        b3.setOnClickListener(null);
        b4.setOnClickListener(null);
        b5.setOnClickListener(null);
        b6.setOnClickListener(null);


        Log.e(TAG,  "Deleted");

    }

}
