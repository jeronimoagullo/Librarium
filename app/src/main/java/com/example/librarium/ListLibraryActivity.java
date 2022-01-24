package com.example.librarium;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ListLibraryActivity extends AppCompatActivity {

    final String TAG = "MainActivity";

    String addedBookName = null;
    TextView textView;

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<Book> bookArrayList;
    private LibraryDBHandler dbHandler;
    private BookRVAdapter bookRVAdapter;
    private RecyclerView booksRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_library);

        textView = (TextView)findViewById(R.id.textView);

        // get intent from AddBook activity
        Intent intent = this.getIntent();
        if(intent != null){
            Log.d(TAG, "intent is not null");
            Bundle extras = intent.getExtras();
            if(extras != null){
                Log.d(TAG, "extras are not null");
                String activityId = extras.getString(AddBookActivity.INTENT_ID_FIELD);
                Log.d(TAG, "Activity ID: " + activityId);
                if(activityId.equals(AddBookActivity.ACTIVITY_ADDBOOK_ID)) {
                    addedBookName = extras.getString(AddBookActivity.ADDED_BOOK_FIELD);
                    Log.d(TAG, "Added book: " + addedBookName);
                    Toast.makeText(this, "Added the book " + addedBookName, Toast.LENGTH_LONG).show();
                }
            }
        }

        // FLOATING BUTTON
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddBookActivity.class);
                startActivity(intent);
                Log.d(TAG,"The floating button was pressed");
                finish();
            }
        });


        // initializing our all variables.
        bookArrayList = new ArrayList<>();
        dbHandler = new LibraryDBHandler(ListLibraryActivity.this);

        // getting our course array
        // list from db handler class.
        bookArrayList = dbHandler.readBooks();

        // on below line passing our array lost to our adapter class.
        bookRVAdapter = new BookRVAdapter(bookArrayList, ListLibraryActivity.this);
        booksRV = findViewById(R.id.idRVBooks);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListLibraryActivity.this, RecyclerView.VERTICAL, false);
        booksRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        booksRV.setAdapter(bookRVAdapter);

    }
}