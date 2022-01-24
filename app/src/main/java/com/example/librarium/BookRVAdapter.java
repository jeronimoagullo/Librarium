package com.example.librarium;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookRVAdapter extends RecyclerView.Adapter<BookRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<Book> bookArrayList;
    private Context context;

    // constructor
    public BookRVAdapter(ArrayList<Book> bookArrayList, Context context) {
        this.bookArrayList = bookArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        Book book = bookArrayList.get(position);
        holder.bookName.setText(book.getName());
        holder.authorName.setText(book.getAuthorName());
        holder.genre.setText(book.getGenre());
        holder.location.setText(book.getLocation());

        // below line is to add on click listener for our recycler view item.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are calling an intent.
                Intent i = new Intent(context, EditBookActivity.class);

                // below we are passing all our values.
                i.putExtra(EditBookActivity.NAME_FIELD, book.getName());
                i.putExtra(EditBookActivity.AUTHOR_FIELD, book.getAuthorName());
                i.putExtra(EditBookActivity.GENRE_FIELD, book.getGenre());
                i.putExtra(EditBookActivity.LOCATION_FIELD, book.getLocation());

                // starting our activity.
                context.startActivity(i);
            }
        });
    }



    @Override
    public int getItemCount() {
        // returning the size of our array list
        return bookArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView bookName, authorName, genre, location;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            bookName = itemView.findViewById(R.id.idTVBookName);
            authorName = itemView.findViewById(R.id.idTVAuthorNAme);
            genre = itemView.findViewById(R.id.idTVGenre);
            location = itemView.findViewById(R.id.idTVLocation);
        }
    }
}