package com.example.user.mrtguidance;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;


import static com.example.user.mrtguidance.R.id.buttonSubmitArticle;

public class AddArticleActivity extends AppCompatActivity {
    Button buttonSubmitArticle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);
        buttonSubmitArticle = (Button) findViewById(R.id.buttonSubmitArticle);


        buttonSubmitArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuider = new AlertDialog.Builder(AddArticleActivity.this);
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

