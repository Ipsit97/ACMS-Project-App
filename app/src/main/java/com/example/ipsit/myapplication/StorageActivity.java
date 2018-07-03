package com.example.ipsit.myapplication;
import android.app.ProgressDialog;
        import android.content.ContentResolver;
        import android.content.Intent;
        import android.net.Uri;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.text.TextUtils;
        import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
        import android.webkit.MimeTypeMap;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.firebase.ui.database.FirebaseRecyclerAdapter;
        import com.google.android.gms.tasks.OnFailureListener;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.Query;
        import com.google.firebase.storage.FirebaseStorage;
        import com.google.firebase.storage.OnPausedListener;
        import com.google.firebase.storage.OnProgressListener;
        import com.google.firebase.storage.StorageReference;
        import com.google.firebase.storage.UploadTask;
        import com.example.ipsit.myapplication.models.UploadInfo;
        import com.example.ipsit.myapplication.viewholder.ImgViewHolder;
        import com.squareup.picasso.Picasso;

public class StorageActivity extends AppCompatActivity {
    private static final String TAG ="Storage" ;


    //track Choosing Image Intent;

    LinearLayoutManager layoutManager;


    private DatabaseReference mDataReference;


    private RecyclerView rcvListImg;
    private FirebaseRecyclerAdapter<UploadInfo, ImgViewHolder> mAdapter;


    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        //edtFileName = (EditText) findViewById(R.id.edt_file_name);


        rcvListImg = (RecyclerView) findViewById(R.id.rcv_list_img);
        String name1 = getIntent().getStringExtra("category");


        mDataReference = FirebaseDatabase.getInstance().getReference(name1);

        //progressDialog = new ProgressDialog(this);



         layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(false);
        rcvListImg.setHasFixedSize(false);
        rcvListImg.setLayoutManager(layoutManager);

        Query query = mDataReference.limitToLast(5);

        mAdapter = new FirebaseRecyclerAdapter<UploadInfo, ImgViewHolder>(
                UploadInfo.class, R.layout.item_image, ImgViewHolder.class, query) {
            @Override
            protected void populateViewHolder(ImgViewHolder viewHolder, UploadInfo model, int position) {

                Picasso.with(StorageActivity.this)
                        .load(model.url)
                        .error(R.drawable.common_google_signin_btn_icon_dark)
                        .into(viewHolder.imageView);
                viewHolder.nameView1.setText("Description : "+model.description);
                viewHolder.nameView2.setText("Contact : "+model.P_No);
                viewHolder.nameView3.setText("City : "+model.Address);
                viewHolder.nameView4.setText("Free Hours : "+model.free_hours);
                
            }

        };

        rcvListImg.setAdapter(mAdapter);



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        clearMemory();
    }

    public  void clearMemory(){


        mAdapter.cleanup();
        rcvListImg = null;
        mDataReference = null;
        mAdapter = null;
        intent=null;
        layoutManager=null;
        Log.e(TAG,  "Deleted");

    }



}