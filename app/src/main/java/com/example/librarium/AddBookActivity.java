package com.example.librarium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddBookActivity extends AppCompatActivity {

    static final String ADDED_BOOK_FIELD = "BookName";
    static final String INTENT_ID_FIELD = "IntentID";
    static final String ACTIVITY_ADDBOOK_ID = "AddBookActivity";


    // creating variables for our edittext, button and dbhandler
    private EditText bookName, authorName, location;
    private Spinner genreSpn;
    private Button addBookBtn;
    private LibraryDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        // initializing all our variables.
        bookName = findViewById(R.id.idEdtBookName);
        authorName = findViewById(R.id.idEdtAuthorName);
        location = findViewById(R.id.idEdtLocation);
        addBookBtn = findViewById(R.id.idBtnAddBook);

        genreSpn = findViewById(R.id.SpnGenre);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,
                R.array.genres, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        genreSpn.setAdapter(adapter);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new LibraryDBHandler(AddBookActivity.this);

        // below line is to add on click listener for our add course button.
        addBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String bookName = AddBookActivity.this.bookName.getText().toString();
                String authorName = AddBookActivity.this.authorName.getText().toString();
                String genre = AddBookActivity.this.genreSpn.getSelectedItem().toString();
                String location = AddBookActivity.this.location.getText().toString();

                // validating if the text fields are empty or not.
                if (bookName.isEmpty() && authorName.isEmpty() && location.isEmpty()) {
                    Toast.makeText(AddBookActivity.this, "Please enter all the data", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                dbHandler.addNewBook(bookName, authorName, genre, location);

                // after adding the data we are resetting the edit texts.
                AddBookActivity.this.bookName.setText("");
                AddBookActivity.this.genreSpn.resetPivot();
                AddBookActivity.this.authorName.setText("");
                AddBookActivity.this.location.setText("");

                Intent intent = new Intent(getApplicationContext(), ListLibraryActivity.class);
                intent.putExtra(ADDED_BOOK_FIELD, bookName);
                intent.putExtra(INTENT_ID_FIELD, ACTIVITY_ADDBOOK_ID);
                startActivity(intent);
                finish();
            }
        });
    }
}