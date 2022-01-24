package com.example.librarium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditBookActivity extends AppCompatActivity {

    static final String NAME_FIELD = "NAME";
    static final String AUTHOR_FIELD = "AUTHOR";
    static final String GENRE_FIELD = "GENRE";
    static final String LOCATION_FIELD = "LOCATION";
    static final String EDIT_BOOK_ACTIVITY = "EditBookActivity";

    final String TAG = "EditBookActivity";

    EditText tvName, tvAuthor, tvLocation;
    Spinner spnGenre;
    Button updateButton, deleteButton;

    LibraryDBHandler libraryDBHandler;

    String name, author, genre, location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        tvName = (EditText)findViewById(R.id.idEdtUpdateBookName);
        tvAuthor = (EditText)findViewById(R.id.idEdtUpdateAuthorName);
        tvLocation = (EditText)findViewById(R.id.idEdtUpdateLocation);

        spnGenre = (Spinner)findViewById(R.id.SpnUpdateGenre);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,
                R.array.genres, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnGenre.setAdapter(adapter);

        updateButton = (Button)findViewById(R.id.idBtnUpdateBook);
        deleteButton = (Button)findViewById(R.id.idBtnDeleteBook);

        // instance of the database handler
        libraryDBHandler = new LibraryDBHandler(EditBookActivity.this);


        // get intent from AddBook activity
        Intent intent = this.getIntent();
        if(intent != null){
            Log.d(TAG, "intent is not null");
            Bundle extras = intent.getExtras();
            if(extras != null){
                Log.d(TAG, "extras are not null");
                name = extras.getString(EditBookActivity.NAME_FIELD);
                author = extras.getString(EditBookActivity.AUTHOR_FIELD);
                genre = extras.getString(EditBookActivity.GENRE_FIELD);
                location = extras.getString(EditBookActivity.LOCATION_FIELD);

                tvName.setText(name);
                tvAuthor.setText(author);
                tvLocation.setText(location);
                spnGenre.setSelection(adapter.getPosition(genre));

                Toast.makeText(this, "updating the book " + name, Toast.LENGTH_SHORT).show();
            }
        }

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // inside this method we are calling an update course
                // method and passing all our edit text values.
                libraryDBHandler.updateBook(name, tvName.getText().toString(), tvAuthor.getText().toString(),
                        spnGenre.getSelectedItem().toString(), tvLocation.getText().toString());

                // displaying a toast message that our course has been updated.
                Toast.makeText(EditBookActivity.this, "Book Updated", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(EditBookActivity.this, ListLibraryActivity.class);
                startActivity(i);
                finish();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                libraryDBHandler.deleteBook(tvName.getText().toString());

                // displaying a toast message that our course has been updated.
                Toast.makeText(EditBookActivity.this, "Book Deleted", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(EditBookActivity.this, ListLibraryActivity.class);
                startActivity(i);
                finish();
            }

        });

    }
}