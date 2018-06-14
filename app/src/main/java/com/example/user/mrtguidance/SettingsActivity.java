package com.example.user.mrtguidance;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class SettingsActivity extends AppCompatActivity {

    private static final int CHOOSE_IMAGE =101 ;
    ImageView imageViewUserPhoto;
    EditText editTextUserName;
    EditText editTextUserIntroduction;
    ProgressBar progressBarProfileImage;
    Uri uriProfileImage;
    String profileImageUrl;

    FirebaseAuth mAuth;

    //分享
    TextView textViewShare;
    Intent shareIntent;
    String shareBody = "This is a great App,you should try it out!";


    //回報與建議
    TextView textViewFeedback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mAuth = FirebaseAuth.getInstance();

        imageViewUserPhoto =findViewById(R.id.imageViewUserPhoto);
        editTextUserName = findViewById(R.id.editTextUserName);
        editTextUserIntroduction = findViewById(R.id.editTextUserIntroduction);
        progressBarProfileImage = findViewById(R.id.progressbarProfileImage);

        imageViewUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageChooser();

            }
        });
        findViewById(R.id.buttonSaveUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserInformation();

            }
        });
        loadUserInformation();

        //分享
        textViewShare = (TextView)findViewById(R.id.textViewShareUs);
        textViewShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,"My App");
                shareIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(shareIntent,"Share Via"));

            }
        });

        //回報與建議
        textViewFeedback = (TextView)findViewById(R.id.textViewFeedback);
        textViewFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this,FeedbackActivity.class));
            }
        });


        //登出app
        findViewById(R.id.buttonSignOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(SettingsActivity.this,LoginActivity.class));

            }
        });
    }
    private void loadUserInformation() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user!=null){
            if (user.getPhotoUrl()!=null){
                Glide.with(this).load(user.getPhotoUrl().toString()).into(imageViewUserPhoto);
            }
            if (user.getDisplayName()!=null){
                editTextUserName.setText(user.getDisplayName());
            }
        }

    }

    //自動登入
    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() ==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
    }

    private void saveUserInformation() {
        String displayProfileName =editTextUserName.getText().toString();
        if (displayProfileName.isEmpty()){
            editTextUserName.setError("Name required");
            editTextUserName.requestFocus();
            return;
        }
        FirebaseUser user =mAuth.getCurrentUser();
        if (user!=null && profileImageUrl != null){
            UserProfileChangeRequest profileImage = new UserProfileChangeRequest.Builder()
                    .setDisplayName(displayProfileName).setPhotoUri(Uri.parse(profileImageUrl)).build();
            user.updateProfile(profileImage).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(SettingsActivity.this,"Profile Updated",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData()!= null){
            uriProfileImage = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uriProfileImage);
                imageViewUserPhoto.setImageBitmap(bitmap);
                uploadImageToFirebaseStorage();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImageToFirebaseStorage() {
        StorageReference profileImageReference = FirebaseStorage.getInstance().getReference("profilepics/"+System.currentTimeMillis()+".jpg");
        if (uriProfileImage != null){
            progressBarProfileImage.setVisibility(View.VISIBLE);
            profileImageReference.putFile(uriProfileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressBarProfileImage.setVisibility(View.GONE);
                    profileImageUrl = taskSnapshot.getDownloadUrl().toString();
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBarProfileImage.setVisibility(View.GONE);
                    Toast.makeText(SettingsActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private void showImageChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Profile Image"),CHOOSE_IMAGE);
    }
}
