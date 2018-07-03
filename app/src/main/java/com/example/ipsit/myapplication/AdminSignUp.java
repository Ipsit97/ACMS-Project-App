package com.example.ipsit.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdminSignUp extends AppCompatActivity {

    EditText e1,e2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_up);

        e1 = (EditText) findViewById(R.id.emailid);
        e2 = (EditText) findViewById(R.id.username);

        button = (Button) findViewById(R.id.buttonproceed);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(orgLogin())
                {
                    Intent i = new Intent(getApplicationContext(),AdminOption.class);
                    Bundle extras1 = new Bundle();
                    extras1.putString("AdminEmail",e1.getText().toString());
                    extras1.putString("AdminUsername",e2.getText().toString());
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    i.putExtras(extras1);
                    startActivity(i);
                }

            }
        });

    }

    private boolean orgLogin() {
        String email = e1.getText().toString().trim();
        String orgname = e2.getText().toString().trim();

        if (email.isEmpty()) {
            e1.setError("Email is required");
            e1.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            e1.setError("Please enter a valid email");
            e1.requestFocus();
            return false;
        }

        if (orgname.isEmpty()) {
            e2.setError("Organization Name is required");
            e2.requestFocus();
            return false;
        }
 return true;
    }

    public void onBackPressed()
    {
        Intent i = new Intent(AdminSignUp.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(i);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        clearMemory();
    }

    public  void clearMemory(){


        button.setOnClickListener(null);
        e1.setText(null);
        e2.setText(null);


    }

}
