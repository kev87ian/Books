package com.kev.books;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ContactUs extends AppCompatActivity {
  //  private EditText editTextName, editTextEmail, editTextMessage;
    @BindView(R.id.editTextName) EditText editTextName;
    @BindView(R.id.editTextEmail) EditText editTextEmail;
    Button submitMessageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us);

        ButterKnife.bind(this);
        submitMessageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactUs.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
