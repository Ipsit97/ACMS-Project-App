package com.example.ipsit.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {
    private static final String TAG = "Profile";
    ListView listview;
    String lsel;
    Button b;
    Toast t;
    private FirebaseStorage imgDel;
    StorageReference imgDel1;

    FirebaseAuth mAuth;
    private DatabaseReference mdata,ddata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);

        mAuth = FirebaseAuth.getInstance();
        imgDel = FirebaseStorage.getInstance();
        imgDel1 = FirebaseStorage.getInstance().getReference();
        listview = (ListView) findViewById(R.id.list1);

        b = (Button) findViewById(R.id.delete);
        final ArrayList<String> list1 = new ArrayList<>();

        final ArrayAdapter itemsAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list1);

        listview.setAdapter(itemsAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lsel = (String) listview.getItemAtPosition(i);
                Toast.makeText(getApplicationContext(),"To delete, click on delete button after clicking the particular item",Toast.LENGTH_SHORT).show();

            }
        });



        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                ddata = FirebaseDatabase.getInstance().getReference();
                ddata.child("MedicalSupplies").addValueEventListener(new ValueEventListener() {

                    @Override

                    public void onDataChange(DataSnapshot datasnp) {

                        //Iterable<DataSnapshot> allchildren = datasnp.getChildren();

                        for (DataSnapshot uniquekeySnapshot : datasnp.getChildren()) {

                            String name = uniquekeySnapshot.child("description").getValue().toString();
                            String user = uniquekeySnapshot.child("userid").getValue().toString();
                            String s = mAuth.getCurrentUser().getUid();
                            if(name.equals(lsel) && user.equals(s))
                            {
                              String key =   uniquekeySnapshot.getKey();
                              ddata.child("MedicalSupplies").child(key).removeValue();
                                String url = uniquekeySnapshot.child("name").getValue().toString();
                                FirebaseStorage storage = FirebaseStorage.getInstance();
                                imgDel1 = storage.getReferenceFromUrl("gs://firstproject-8adca.appspot.com");


                                StorageReference r1 = imgDel1.child("MedicalSupplies/"+url);
                                r1.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // File deleted successfully
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        // Uh-oh, an error occurred!
                                    }
                                });

                            }



                           // startActivity(getIntent());
                            if(lsel!=null)
                            {

                                Toast.makeText(getApplicationContext(),"Selected Item deleted Successfully",Toast.LENGTH_SHORT).show();

                                finish();
                            }



                        }

                    }

                    @Override

                    public void onCancelled(DatabaseError databaseError)

                    { }

                });



                ddata = FirebaseDatabase.getInstance().getReference();
                ddata.child("Clothes").addValueEventListener(new ValueEventListener() {

                    @Override

                    public void onDataChange(DataSnapshot datasnp) {

                        //Iterable<DataSnapshot> allchildren = datasnp.getChildren();

                        for (DataSnapshot uniquekeySnapshot : datasnp.getChildren()) {

                            String name = uniquekeySnapshot.child("description").getValue().toString();
                            String user = uniquekeySnapshot.child("userid").getValue().toString();
                            String s = mAuth.getCurrentUser().getUid();
                            if(name.equals(lsel) && user.equals(s))
                            {
                                String key =   uniquekeySnapshot.getKey();
                                ddata.child("Clothes").child(key).removeValue();
                                String url = uniquekeySnapshot.child("name").getValue().toString();
                                FirebaseStorage storage = FirebaseStorage.getInstance();
                                imgDel1 = storage.getReferenceFromUrl("gs://firstproject-8adca.appspot.com");


                                StorageReference r1 = imgDel1.child("Clothes/"+url);
                                r1.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // File deleted successfully
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        // Uh-oh, an error occurred!
                                    }
                                });

                            }



                            // startActivity(getIntent());
                            if(lsel!=null)
                            {
                                Toast.makeText(getApplicationContext(),"Selected Item deleted Successfully",Toast.LENGTH_SHORT).show();

                                finish();
                            }



                        }

                    }

                    @Override

                    public void onCancelled(DatabaseError databaseError)

                    { }

                });



                ddata = FirebaseDatabase.getInstance().getReference();
                ddata.child("Books&Stationary").addValueEventListener(new ValueEventListener() {

                    @Override

                    public void onDataChange(DataSnapshot datasnp) {

                        //Iterable<DataSnapshot> allchildren = datasnp.getChildren();

                        for (DataSnapshot uniquekeySnapshot : datasnp.getChildren()) {

                            String name = uniquekeySnapshot.child("description").getValue().toString();
                            String user = uniquekeySnapshot.child("userid").getValue().toString();
                            String s = mAuth.getCurrentUser().getUid();
                            if(name.equals(lsel) && user.equals(s))
                            {
                                String key =   uniquekeySnapshot.getKey();
                                ddata.child("Books&Stationary").child(key).removeValue();
                                String url = uniquekeySnapshot.child("name").getValue().toString();
                                FirebaseStorage storage = FirebaseStorage.getInstance();
                                imgDel1 = storage.getReferenceFromUrl("gs://firstproject-8adca.appspot.com");


                                StorageReference r1 = imgDel1.child("Books$Stationary/"+url);
                                r1.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // File deleted successfully
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        // Uh-oh, an error occurred!
                                    }
                                });

                            }



                            // startActivity(getIntent());
                            if(lsel!=null)
                            {
                                t = Toast.makeText(getApplicationContext(),"Selected Item deleted Successfully",Toast.LENGTH_SHORT);
                                t.show();
                                finish();
                            }



                        }

                    }

                    @Override

                    public void onCancelled(DatabaseError databaseError)

                    { }

                });



                ddata = FirebaseDatabase.getInstance().getReference();
                ddata.child("Food").addValueEventListener(new ValueEventListener() {

                    @Override

                    public void onDataChange(DataSnapshot datasnp) {

                        //Iterable<DataSnapshot> allchildren = datasnp.getChildren();

                        for (DataSnapshot uniquekeySnapshot : datasnp.getChildren()) {

                            String name = uniquekeySnapshot.child("description").getValue().toString();
                            String user = uniquekeySnapshot.child("userid").getValue().toString();
                            String s = mAuth.getCurrentUser().getUid();
                            if(name.equals(lsel) && user.equals(s))
                            {
                                String key =   uniquekeySnapshot.getKey();
                                ddata.child("Food").child(key).removeValue();
                                String url = uniquekeySnapshot.child("name").getValue().toString();
                                FirebaseStorage storage = FirebaseStorage.getInstance();
                                imgDel1 = storage.getReferenceFromUrl("gs://firstproject-8adca.appspot.com");


                                StorageReference r1 = imgDel1.child("Food/"+url);
                                r1.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // File deleted successfully
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        // Uh-oh, an error occurred!
                                    }
                                });

                            }



                            // startActivity(getIntent());
                            if(lsel!=null)
                            {
                                t = Toast.makeText(getApplicationContext(),"Selected Item deleted Successfully",Toast.LENGTH_SHORT);
                                t.show();
                                finish();
                            }



                        }

                    }

                    @Override

                    public void onCancelled(DatabaseError databaseError)

                    { }

                });




                ddata = FirebaseDatabase.getInstance().getReference();
                ddata.child("General").addValueEventListener(new ValueEventListener() {

                    @Override

                    public void onDataChange(DataSnapshot datasnp) {

                        //Iterable<DataSnapshot> allchildren = datasnp.getChildren();

                        for (DataSnapshot uniquekeySnapshot : datasnp.getChildren()) {

                            String name = uniquekeySnapshot.child("description").getValue().toString();
                            String user = uniquekeySnapshot.child("userid").getValue().toString();
                            String s = mAuth.getCurrentUser().getUid();
                            if(name.equals(lsel) && user.equals(s))
                            {
                                String key =   uniquekeySnapshot.getKey();
                                ddata.child("General").child(key).removeValue();
                                String url = uniquekeySnapshot.child("name").getValue().toString();
                                FirebaseStorage storage = FirebaseStorage.getInstance();
                                imgDel1 = storage.getReferenceFromUrl("gs://firstproject-8adca.appspot.com");


                                StorageReference r1 = imgDel1.child("General/"+url);
                                r1.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // File deleted successfully
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        // Uh-oh, an error occurred!
                                    }
                                });

                            }



                            // startActivity(getIntent());
                            if(lsel!=null)
                            {
                                t = Toast.makeText(getApplicationContext(),"Selected Item deleted Successfully",Toast.LENGTH_SHORT);
                                t.show();
                                finish();
                            }



                        }

                    }

                    @Override

                    public void onCancelled(DatabaseError databaseError)

                    { }

                });





                ddata = FirebaseDatabase.getInstance().getReference();
                ddata.child("Toys").addValueEventListener(new ValueEventListener() {

                    @Override

                    public void onDataChange(DataSnapshot datasnp) {

                        //Iterable<DataSnapshot> allchildren = datasnp.getChildren();

                        for (DataSnapshot uniquekeySnapshot : datasnp.getChildren()) {

                            String name = uniquekeySnapshot.child("description").getValue().toString();
                            String user = uniquekeySnapshot.child("userid").getValue().toString();
                            String s = mAuth.getCurrentUser().getUid();
                            if(name.equals(lsel) && user.equals(s))
                            {
                                String key =   uniquekeySnapshot.getKey();
                                ddata.child("Toys").child(key).removeValue();
                                String url = uniquekeySnapshot.child("name").getValue().toString();
                                FirebaseStorage storage = FirebaseStorage.getInstance();
                                imgDel1 = storage.getReferenceFromUrl("gs://firstproject-8adca.appspot.com");


                                StorageReference r1 = imgDel1.child("Toys/"+url);
                                r1.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        // File deleted successfully
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        // Uh-oh, an error occurred!
                                    }
                                });

                            }



                            // startActivity(getIntent());
                            if(lsel!=null)
                            {
                                t = Toast.makeText(getApplicationContext(),"Selected Item deleted Successfully",Toast.LENGTH_SHORT);
                                t.show();
                                finish();
                            }



                        }

                    }

                    @Override

                    public void onCancelled(DatabaseError databaseError)

                    { }

                });



            }
        });








        mdata = FirebaseDatabase.getInstance().getReference();
        mdata.child("Toys").addValueEventListener(new ValueEventListener() {

            @Override

            public void onDataChange(DataSnapshot datasnp) {

                //Iterable<DataSnapshot> allchildren = datasnp.getChildren();

                for (DataSnapshot uniquekeySnapshot : datasnp.getChildren()) {

                    String name = uniquekeySnapshot.child("userid").getValue().toString();
                    String s = mAuth.getCurrentUser().getUid();
                   if(name.equals(s))
                    {
                        String des =uniquekeySnapshot.child("description").getValue().toString();
                        System.out.print(des);
                        list1.add(des);

                    }




                    itemsAdapter.notifyDataSetChanged();


                }

            }

            @Override

            public void onCancelled(DatabaseError databaseError)

            { }

        });




        mdata.child("Books&Stationary").addValueEventListener(new ValueEventListener() {

            @Override

            public void onDataChange(DataSnapshot datasnp) {

                //Iterable<DataSnapshot> allchildren = datasnp.getChildren();

                for (DataSnapshot uniquekeySnapshot : datasnp.getChildren()) {

                    String name = uniquekeySnapshot.child("userid").getValue().toString();
                    String s = mAuth.getCurrentUser().getUid();
                    if(name.equals(s))
                    {
                        String des =uniquekeySnapshot.child("description").getValue().toString();
                        System.out.print(des);
                        list1.add(des);

                    }




                    itemsAdapter.notifyDataSetChanged();


                }

            }

            @Override

            public void onCancelled(DatabaseError databaseError)

            { }

        });



        mdata.child("Food").addValueEventListener(new ValueEventListener() {

            @Override

            public void onDataChange(DataSnapshot datasnp) {

                //Iterable<DataSnapshot> allchildren = datasnp.getChildren();

                for (DataSnapshot uniquekeySnapshot : datasnp.getChildren()) {

                    String name = uniquekeySnapshot.child("userid").getValue().toString();
                    String s = mAuth.getCurrentUser().getUid();
                    if(name.equals(s))
                    {
                        String des =uniquekeySnapshot.child("description").getValue().toString();
                        System.out.print(des);
                        list1.add(des);

                    }




                    itemsAdapter.notifyDataSetChanged();


                }

            }

            @Override

            public void onCancelled(DatabaseError databaseError)

            { }

        });




        mdata.child("Clothes").addValueEventListener(new ValueEventListener() {

            @Override

            public void onDataChange(DataSnapshot datasnp) {

                //Iterable<DataSnapshot> allchildren = datasnp.getChildren();

                for (DataSnapshot uniquekeySnapshot : datasnp.getChildren()) {

                    String name = uniquekeySnapshot.child("userid").getValue().toString();
                    String s = mAuth.getCurrentUser().getUid();
                    if(name.equals(s))
                    {
                        String des =uniquekeySnapshot.child("description").getValue().toString();
                        System.out.print(des);
                        list1.add(des);

                    }




                    itemsAdapter.notifyDataSetChanged();


                }

            }

            @Override

            public void onCancelled(DatabaseError databaseError)

            { }

        });



        mdata.child("General").addValueEventListener(new ValueEventListener() {

            @Override

            public void onDataChange(DataSnapshot datasnp) {

                //Iterable<DataSnapshot> allchildren = datasnp.getChildren();

                for (DataSnapshot uniquekeySnapshot : datasnp.getChildren()) {

                    String name = uniquekeySnapshot.child("userid").getValue().toString();
                    String s = mAuth.getCurrentUser().getUid();
                    if(name.equals(s))
                    {
                        String des =uniquekeySnapshot.child("description").getValue().toString();
                        System.out.print(des);
                        list1.add(des);

                    }




                    itemsAdapter.notifyDataSetChanged();


                }

            }

            @Override

            public void onCancelled(DatabaseError databaseError)

            { }

        });



        mdata.child("MedicalSupplies").addValueEventListener(new ValueEventListener() {

            @Override

            public void onDataChange(DataSnapshot datasnp) {

                //Iterable<DataSnapshot> allchildren = datasnp.getChildren();

                for (DataSnapshot uniquekeySnapshot : datasnp.getChildren()) {

                    String name = uniquekeySnapshot.child("userid").getValue().toString();
                    String s = mAuth.getCurrentUser().getUid();
                    if(name.equals(s))
                    {
                        String des =uniquekeySnapshot.child("description").getValue().toString();
                        System.out.print(des);
                        list1.add(des);

                    }




                    itemsAdapter.notifyDataSetChanged();


                }

            }

            @Override

            public void onCancelled(DatabaseError databaseError)

            { }

        });










    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(Profile.this, ThreeButtons.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        clearMemory();
    }

    public  void clearMemory(){


        b.setOnClickListener(null);
        ddata = null;
        mdata = null;
        imgDel1 = null;
        lsel=null;
        listview=null;
        b=null;
        t = null;
        Log.e(TAG,  "Deleted");

    }
}
