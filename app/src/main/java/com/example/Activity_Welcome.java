package com.example.huangyoude.sa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class Activity_Welcome extends AppCompatActivity{

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.tiny_activity_main);


            Button buttonStartUse = (Button)findViewById(R.id.start_use);


            buttonStartUse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent intent = new Intent();
                    //intent.setClass(Activity_Welcome.this , Activity_Main.class);
                    //開啟Activity
                    //startActivity(intent);
                    finish();
                    startActivity(new Intent(Activity_Welcome.this,LoginActivity.class));

                }
            });
                }


}

