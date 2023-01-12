package com.example.huangyoude.sa;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;

public class Fragment_Personalfile_Settings extends android.support.v4.app.Fragment {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    private static final int CHOOSE_IMAGE =101 ;
    ImageView imageViewUserPhoto;
    EditText editTextUserName;
    EditText editTextUserIntroduction;
    ProgressBar progressBarProfileImage;
    Uri uriProfileImage;
    String profileImageUrl;

    FirebaseAuth mAuth;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View settingsView = inflater.inflate(R.layout.fragment_personalfile_settings, container, false);
        //setContentView(R.layout.activity_settings);

        mAuth = FirebaseAuth.getInstance();

        imageViewUserPhoto = settingsView.findViewById(R.id.imageViewUserPhoto);
        editTextUserName = settingsView.findViewById(R.id.editTextUserName);
        editTextUserIntroduction = settingsView.findViewById(R.id.editTextUserIntroduction);
        progressBarProfileImage = settingsView.findViewById(R.id.progressbarProfileImage);

        imageViewUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageChooser();

            }
        });
        settingsView.findViewById(R.id.buttonSaveUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserInformation();

            }
        });
        //loadUserInformation();


        //登出app
        settingsView.findViewById(R.id.buttonSignOut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                //finish();
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.personalfilelayout , new Fragment_Personalfile_Login())
                        .addToBackStack(null)
                        .commit();
                //startActivity(new Intent(SettingsActivity.this,LoginActivity.class));

            }
        });
    return settingsView;
    }
    //private void loadUserInformation() {
    //FirebaseUser user = mAuth.getCurrentUser();
    //if (user!=null){
    //if (user.getPhotoUrl()!=null){
    //Glide.with(this).load(user.getPhotoUrl().toString()).into(imageViewUserPhoto);
    //}
    //if (user.getDisplayName()!=null){
    //editTextUserName.setText(user.getDisplayName());
    //}
    //}

    //}

    //自動登入
    @Override
    public void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() ==null){
            //finish();
            fragmentManager = getFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.personalfilelayout , new Fragment_Personalfile_Login())
                    .addToBackStack(null)
                    .commit();
            //startActivity(new Intent(this,LoginActivity.class));
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
                        Toast.makeText(getActivity(),"Profile Updated",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==CHOOSE_IMAGE && resultCode == Activity_Main.RESULT_OK && data != null && data.getData()!= null){
            uriProfileImage = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),uriProfileImage);
                imageViewUserPhoto.setImageBitmap(bitmap);
                //uploadImageToFirebaseStorage();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //private void uploadImageToFirebaseStorage() {
    //StorageReference profileImageReference = FirebaseStorage.getInstance().getReference("profilepics/"+System.currentTimeMillis()+".jpg");
    //if (uriProfileImage != null){
    //progressBarProfileImage.setVisibility(View.VISIBLE);
    //profileImageReference.putFile(uriProfileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
    //@Override
    //public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
    //progressBarProfileImage.setVisibility(View.GONE);
    //profileImageUrl = taskSnapshot.getDownloadUrl().toString();
    //}
    //})
    //.addOnFailureListener(new OnFailureListener() {
    //@Override
    //public void onFailure(@NonNull Exception e) {
    //progressBarProfileImage.setVisibility(View.GONE);
    //Toast.makeText(SettingsActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
    //}
    //});

    //}
    //}

    private void showImageChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Profile Image"),CHOOSE_IMAGE);
    }
}
