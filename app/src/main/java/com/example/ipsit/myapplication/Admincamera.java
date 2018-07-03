package com.example.ipsit.myapplication;

        import android.app.Activity;
        import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.Intent;
        import android.database.Cursor;
        import android.graphics.Bitmap;
        import android.graphics.Canvas;
        import android.graphics.Matrix;
        import android.graphics.Paint;
        import android.net.Uri;
        import android.provider.MediaStore;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.ipsit.myapplication.models.AdminUpload;
        import com.example.ipsit.myapplication.models.UploadInfo;
        import com.google.android.gms.tasks.OnFailureListener;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.storage.FirebaseStorage;
        import com.google.firebase.storage.OnProgressListener;
        import com.google.firebase.storage.StorageReference;
        import com.google.firebase.storage.UploadTask;

        import java.io.ByteArrayOutputStream;
        import java.io.File;

public class Admincamera extends AppCompatActivity {
    private static final String TAG = "Admincamera";
    private static final int CAMERA_REQUEST = 1888;
    private StorageReference mStorage;
    private ImageView Imview;
    private DatabaseReference mDataReference;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    String userID;
    TextView content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admincamera);

        mStorage = FirebaseStorage.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        mDataReference = FirebaseDatabase.getInstance().getReference("AdminAccounts");
        this.Imview = (ImageView) this.findViewById(R.id.imageView1);
        content = (TextView) this.findViewById(R.id.content1);
        Button photoButton = (Button) this.findViewById(R.id.button1);

        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);

            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            imageBitmap=scaleBitmap(imageBitmap,2160,2160);
            Imview.setImageBitmap(imageBitmap);

            Uri tempUri = getImageUri(getApplicationContext(), imageBitmap);
            File finalFile = new File(getRealPathFromURI(tempUri));

             progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();
            progressDialog.setCanceledOnTouchOutside(false);


            Uri file = Uri.fromFile(finalFile);

            FirebaseStorage storage = FirebaseStorage.getInstance();
            mStorage = storage.getReferenceFromUrl("gs://firstproject-8adca.appspot.com");


            StorageReference riversRef = mStorage.child("AdminAccounts/"+file.getLastPathSegment());
            //StorageReference riversRef = mStorage.child(name1);
            riversRef.putFile(file)

// Register observers to listen for when the download is done or if it fails
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.

                    String name = taskSnapshot.getMetadata().getName();
                    String url = taskSnapshot.getDownloadUrl().toString();
                    //String s =  content.getText().toString();

                    Bundle extras = getIntent().getExtras();
                    String emailid = extras.getString("AdminEmail");
                    String usern = extras.getString("AdminUsername");



                   // FirebaseUser user= mAuth.getCurrentUser();
                    //String userID = user.getUid();

                    Log.e(TAG, "Uri: " + url);
                    Log.e(TAG, "Name: " + name);
                    Log.e(TAG,"AdminEmail"+ emailid);
                    Log.e(TAG,"AdminUsername"+ usern);




                    writeNewImageInfoToDB(name, url,emailid,usern);
                    Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(Admincamera.this,MainActivity.class);
                    startActivity(i);
                    // ...
                }
            })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //calculating progress percentage
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            //displaying percentage in progress dialog
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });



        }
    }
    public static Bitmap scaleBitmap(Bitmap bitmap, int newWidth, int newHeight) {
        Bitmap scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);

        float scaleX = newWidth / (float) bitmap.getWidth();
        float scaleY = newHeight / (float) bitmap.getHeight();
        float pivotX = 0;
        float pivotY = 0;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(scaleX, scaleY, pivotX, pivotY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap, 0, 0, new Paint(Paint.FILTER_BITMAP_FLAG));

        return scaledBitmap;
    }
    private void writeNewImageInfoToDB(String name, String url,String emailid,String usern) {
       AdminUpload info = new AdminUpload(name, url,emailid,usern);

        String key = mDataReference.push().getKey();
        mDataReference.child(key).setValue(info);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    public void onBackPressed()
    {
        Intent i = new Intent(Admincamera.this, AdminOption.class);

        startActivity(i);
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (progressDialog!=null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }
}
