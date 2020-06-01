package com.kev.books;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookGenre extends AppCompatActivity {
    private static final String TAG = BookGenre.class.getSimpleName();
    @BindView (R.id.bookInput) EditText mBookInput;
    @BindView(R.id.authorText) TextView mAuthorText;
    @BindView(R.id.TitleText) TextView mTitleText;
    AwesomeValidation awesomeValidation;
    @BindView(R.id.contactUs) Button mContactUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_genre);

        ButterKnife.bind(this);

    }

    public void searchBooks(View view){
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this, R.id.bookInput, RegexTemplate.NOT_EMPTY, R.string.invalid_name);

        if (awesomeValidation.validate()){
            String queryString = mBookInput.getText().toString();
            Toast.makeText(BookGenre.this, "Your query is being processed", Toast.LENGTH_LONG);
            new FetchBook(mTitleText, mAuthorText).execute(queryString);
        }

        else {
            Toast.makeText(this, "Name cannot be blank", Toast.LENGTH_LONG);
        }

        mContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContactUs();
            }
        });
    }

    public void openContactUs(){
        Intent intent = new Intent(this, ContactUs.class);
        startActivity(intent);
    }
}