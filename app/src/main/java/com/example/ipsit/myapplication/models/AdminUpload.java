package com.example.ipsit.myapplication.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class AdminUpload {

    public String name;
    public String url;
    public String email;
    public String username;

    //public String Uid;

    public AdminUpload() {
    }

    public AdminUpload(String name, String url,String email,String username) {
        this.name = name;
        this.url= url;
        this.email  = email;
        this.username  = username;

        //  this.Uid = Uid;
    }
}
