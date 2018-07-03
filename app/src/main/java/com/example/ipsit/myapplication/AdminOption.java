package com.example.ipsit.myapplication;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.Snackbar;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;

public class AdminOption extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_option);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        button = (Button) findViewById(R.id.buttonproceed1);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Bundle extras1 = getIntent().getExtras();


                Intent i = new Intent(getApplicationContext(),Admincamera.class);

                i.putExtras(extras1);
                startActivity(i);
            }
        });

    }
    public void onBackPressed()
    {
        Intent i = new Intent(AdminOption.this, AdminSignUp.class);
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


    }


}


