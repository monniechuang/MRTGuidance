package com.example.user.mrtguidance;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.view.View;


import static com.example.user.mrtguidance.R.id.btnSubmit;
public class MainActivity extends AppCompatActivity {
    private Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuider = new AlertDialog.Builder(MainActivity.this);
                mBuider.setTitle("確認通知");
                mBuider.setIcon(R.drawable.star);
                mBuider.setMessage("確認輸入店家資訊?");
                mBuider.setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
            }
        });

    }
}

