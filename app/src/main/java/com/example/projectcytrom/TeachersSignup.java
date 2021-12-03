package com.example.projectcytrom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;
import java.util.Random;

public class TeachersSignup extends AppCompatActivity {

    TextView textView;

    private EditText email, password, name, department, ph;
    private Button button, browse;
    private FirebaseAuth auth;

    ProgressDialog dialog;

    Uri filepath;
    ImageView img;
    Bitmap bitmap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_signup);

        textView = findViewById(R.id.loginPage);

        img=findViewById(R.id.imgView);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        name=findViewById(R.id.name);
        department=findViewById(R.id.department);
        ph=findViewById(R.id.ph);
        button=findViewById(R.id.button);
        browse=findViewById(R.id.browse);

        auth=FirebaseAuth.getInstance();

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Dexter.withActivity(TeachersSignup.this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
//                    @Override
//                    public void onPermissionGranted(PermissionGrantedResponse response) {
//                        Intent intent = new Intent();
//                        intent.setAction(Intent.ACTION_GET_CONTENT);
//                        intent.setType("image/*");
//                        startActivityForResult(intent , 1);
//                    }
//
//                    @Override
//                    public void onPermissionDenied(PermissionDeniedResponse response) {
//
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
//                        token.continuePermissionRequest();
//                    }
//                }).check();

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, 1);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text_email=email.getText().toString().trim();
                String text_password=password.getText().toString().trim();
                String text_name=name.getText().toString().trim();
                String text_dep=department.getText().toString().trim();
                String text_ph=ph.getText().toString().trim();


                if (TextUtils.isEmpty(text_email) || TextUtils.isEmpty(text_password) || TextUtils.isEmpty(text_name) || TextUtils.isEmpty(text_dep) || TextUtils.isEmpty(text_ph))
                {
                    Toast.makeText(TeachersSignup.this, "Kindly fill all the fields!", Toast.LENGTH_SHORT).show();

                }else if (text_password.length()<6)
                {
                    Toast.makeText(TeachersSignup.this, "Password must be of 6 characters", Toast.LENGTH_SHORT).show();
                }else
                {
                    uploadToFirebase();
                }
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TeachersSignup.this, TeachersLogin.class);
                startActivity(intent);
            }
        });


    }

    private void uploadToFirebase() {
        dialog = new ProgressDialog(TeachersSignup.this);
        dialog.setTitle("File Uploader");
        dialog.show();

        String txtName = name.getText().toString().trim();
        String txtDept = department.getText().toString().trim();
        String txtPh = ph.getText().toString().trim();
        String txtEmail = email.getText().toString().trim();
        String txtPass = password.getText().toString().trim();




        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference uploader = storage.getReference("Image"+new Random().nextInt(50));

        uploader.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        dialog.dismiss();

                        FirebaseDatabase db = FirebaseDatabase.getInstance();
                        DatabaseReference root = db.getReference("teachers");

                        dataholder obj = new dataholder(txtName, txtPh, txtDept, txtEmail, uri.toString());
                        root.child(txtName).setValue(obj);

                        signupuser(txtEmail, txtPass);


                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                long percent = (100*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                dialog.setMessage("Uploaded : "+(int) percent + "%");
            }
        });




    }

//    private void signupData() {
//
//
//        Dexter.withActivity(TeachersSignup.this).withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE).withListener(new PermissionListener() {
//            @Override
//            public void onPermissionGranted(PermissionGrantedResponse response) {
//                Intent intent=new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(Intent.createChooser(intent,"select"),1);
//            }
//
//            @Override
//            public void onPermissionDenied(PermissionDeniedResponse response) {
//
//            }
//
//            @Override
//            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
//
//                token.continuePermissionRequest();
//            }
//        }).check();
//
//
//
//
//    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        if (requestCode==1 && resultCode==RESULT_OK)
        {
            filepath=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(filepath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);
            }
            catch (Exception ex)
            {

            }
        }
        super.onActivityResult(requestCode, resultCode, data);


    }





    private void signupuser(String text_email, String text_password) {

        auth.createUserWithEmailAndPassword(text_email,text_password).addOnCompleteListener(TeachersSignup.this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(TeachersSignup.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(TeachersSignup.this ,TeachersLogin.class));
                    finish();
                }else
                {
                    Toast.makeText(TeachersSignup.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}