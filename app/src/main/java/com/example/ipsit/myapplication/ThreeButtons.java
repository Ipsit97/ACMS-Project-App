package com.example.ipsit.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ThreeButtons extends AppCompatActivity {

    FirebaseAuth AuthUI;
    UserSessionManager session;
    Button b1,b2,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_buttons);

        b1 = (Button) findViewById(R.id.buttonProfile);
        b2 = (Button) findViewById(R.id.buttonUpload);
        b3 = (Button) findViewById(R.id.buttonLogout);

        session = new UserSessionManager(getApplicationContext());



        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),Profile.class);
                i.putExtra("category","Books&Stationary");

                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(),Categories.class);
                i.putExtra("category","Clothes");

                startActivity(i);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                AuthUI.getInstance().signOut();
                session.logoutUser();
                Intent intent = new Intent(ThreeButtons.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });


    }
    @Override
    public void onBackPressed()
    {
      finish();
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



    }
}
