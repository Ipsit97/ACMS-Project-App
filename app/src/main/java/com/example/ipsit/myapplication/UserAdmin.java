package com.example.ipsit.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_admin);


        Button button=(Button)findViewById(R.id.buttonAdmin);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                Intent i = new Intent(getApplicationContext(),AdminSignUp.class);

                startActivity(i);
            }
        });
        button=(Button)findViewById(R.id.buttonUser);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent i = new Intent(getApplicationContext(),SignUpActivity.class);

                startActivity(i);
            }
        });






    }
}
