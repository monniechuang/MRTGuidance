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

public class Fragment_Personalfile_Login extends Fragment implements View.OnClickListener{

    FirebaseAuth mAuth;


    ProgressBar progressBarSignUp;

    EditText editTextEmail , editTextPassword;


    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View loginView = inflater.inflate(R.layout.fragment_personalfile_login, container, false);


        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.login);

        mAuth = FirebaseAuth.getInstance();

        editTextEmail = (EditText) loginView.findViewById(R.id.editTextEmail);
        editTextPassword =(EditText) loginView.findViewById(R.id.editTextPassword);
        progressBarSignUp = (ProgressBar) loginView.findViewById(R.id.progressbarSignUp);

        loginView.findViewById(R.id.textViewSignUp).setOnClickListener(this);
        loginView.findViewById(R.id.buttonLogin).setOnClickListener(this);

        return loginView;
    }

    private void userLogin(){

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

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBarSignUp.setVisibility(View.GONE);
                if (task.isSuccessful()){
                    fragmentManager = getFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.personalfilelayout , new Fragment_Personalfile_Profile())
                            .commit();
                    /*
                    finish();
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    */
                }else {
                    Toast.makeText(getActivity().getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    //自動登入
    @Override
    public void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() !=null){
            fragmentManager = getFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.personalfilelayout , new Fragment_Personalfile_Profile())
                    .commit();
            /*finish();
            startActivity(new Intent(this,MainActivity.class));
            */
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.textViewSignUp:
                fragmentManager = getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.personalfilelayout , new Fragment_Personalfile_SignUp())
                        .commit();
                /*finish();
                startActivity(new Intent(this,SignUpActivity.class));
                */
                break;
            case  R.id.buttonLogin:
                userLogin();
                break;
        }

    }
}
