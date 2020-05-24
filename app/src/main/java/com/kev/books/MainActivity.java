package com.kev.books;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

     @BindView(R.id.findBooksButton) Button mFindBooksButton;
     @BindView(R.id.titleEditText) EditText mTitleEditText;

    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

       // Declaring Validation
       awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
       awesomeValidation.addValidation(this, R.id.titleEditText,
               RegexTemplate.NOT_EMPTY, R.string.invalid_name);

       mFindBooksButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
       //Control Statements

               if (awesomeValidation.validate()){
                   String title = mTitleEditText.getText().toString();
                   Log.d(TAG, title);
                   Intent intent = new Intent(MainActivity.this, BookGenre.class);
                   intent.putExtra("title", title);
                   startActivity(intent);
               }

               else{
                   Toast.makeText(MainActivity.this, "Title cannot be blank", Toast.LENGTH_LONG).show();
               }

           }
       });

     }
}
