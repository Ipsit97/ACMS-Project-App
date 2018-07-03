package com.example.ipsit.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class splash extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;
    UserSessionManager session;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        session = new UserSessionManager(getApplicationContext());



        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {


                /* Create an Intent that will start the Menu-Activity. */
                if(session.checkLogin())
                    finish();
                else
                {

                    DatabaseReference mdata = FirebaseDatabase.getInstance().getReference();
                    HashMap<String, String> user = session.getUserDetails();
                    String uid = user.get(UserSessionManager.KEY_UID);

                    DatabaseReference mostafa = mdata.child("users_type").child(uid).child("Type");

                    mostafa.addValueEventListener(new ValueEventListener() {

                        @Override

                        public void onDataChange(DataSnapshot dataSnapshot) {

                            //Iterable<DataSnapshot> allchildren = datasnp.getChildren();


                            String type = dataSnapshot.getValue(String.class);
                            //System.out.println(type);
                            if (type.equals("user")) {

                                Intent intent = new Intent(splash.this, ThreeButtons.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(intent);
                            } else {

                                Intent intent = new Intent(splash.this, AdminCategory.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(intent);
                            }



                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }


                    }
        }, SPLASH_DISPLAY_LENGTH);



    }
    public void onBackPressed()
    {
        finish();
    }

    }

