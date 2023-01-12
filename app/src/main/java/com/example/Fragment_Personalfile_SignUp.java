package com.example.huangyoude.sa;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Fragment_Personalfile_SignUp extends Fragment implements View.OnClickListener {

    private FirebaseAuth mAuth;

    ProgressBar progressBarSignUp;
    EditText editTextEmail , editTextPassword;


    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View signUpView = inflater.inflate(R.layout.fragment_personalfile_signup, container, false);
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.sign_up);

        editTextEmail = (EditText) signUpView.findViewById(R.id.editTextEmail);
        editTextPassword =(EditText) signUpView.findViewById(R.id.editTextPassword);
        progressBarSignUp = (ProgressBar) signUpView.findViewById(R.id.progressbarSignUp);

        mAuth = FirebaseAuth.getInstance();
        signUpView.findViewById(R.id.buttonSignUp).setOnClickListener(this);
        signUpView.findViewById(R.id.textViewLogin).setOnClickListener(this);

        return signUpView;
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;

        }
        if(password.length() < 6){
            editTextPassword.setError("Minimum length of a password should be 6");
            editTextPassword.requestFocus();
            return;

        }

        progressBarSignUp.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBarSignUp.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    Toast.makeText(getActivity().getApplicationContext(),"User Registered Successful",Toast.LENGTH_SHORT).show();
                }else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getActivity().getApplicationContext(),"You are already registered",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity().getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonSignUp:
                registerUser();
                break;
            case R.id.textViewLogin:
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.personalfilelayout , new Fragment_Personalfile_Login())
                        .addToBackStack(null)
                        .commit();
                //startActivity(new Intent(this,LoginActivity.class));
                break;
        }

    }


}

