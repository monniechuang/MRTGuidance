package com.example.user.mrtguidance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        final EditText FeedbackEmail =(EditText) findViewById(R.id.editTextFeedbackEmail);
        final EditText FeedbackEmailSubject =(EditText) findViewById(R.id.editTextFeedbackSubject);
        final EditText FeedbackEmailText =(EditText) findViewById(R.id.editTextFeedbackEmailText);

        Button sendFeedback = (Button)findViewById(R.id.buttonSendFeedback);
        sendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feedbackEmail = FeedbackEmail.getText().toString();
                String feedbackEmailSubject = FeedbackEmailSubject.getText().toString();
                String feedbackEmailText = FeedbackEmailText.getText().toString();

                Intent intentEmail = new Intent(Intent.ACTION_SEND);

                intentEmail.putExtra(Intent.EXTRA_EMAIL,new String[]{"mrt_guidance@gmail.com"});
                intentEmail.putExtra(Intent.EXTRA_SUBJECT,feedbackEmailSubject);
                intentEmail.putExtra(Intent.EXTRA_TEXT,feedbackEmailText);

                intentEmail.setType("message/rfc822");
                startActivity(Intent.createChooser(intentEmail,"Choose app to sed mail"));
            }
        });

    }
}
