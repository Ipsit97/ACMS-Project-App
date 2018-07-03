package com.example.ipsit.myapplication.models;

/**
 * Created by Ipsit on 5/26/2018.
 */




import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class UploadInfo {

    public String name;
    public String url;
    public String description;
    public String P_No;
    public String Address;
    public String free_hours;
    public String userid;
    //public String Uid;

    public UploadInfo() {
    }

    public UploadInfo(String name, String url,String des,String P_No,String add,String free_h,String userID) {
        this.name = name;
        this.url= url;
        this.description  = des;
        this.P_No  = P_No;
        this.Address  = add;
        this.free_hours  = free_h;
        this.userid = userID;
      //  this.Uid = Uid;
    }
}
