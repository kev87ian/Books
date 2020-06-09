package com.kev.books;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {

    ArrayList<Book> mBooks;

    public BooksAdapter(ArrayList<Book> books) {
        this.mBooks = books;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.books_list, viewGroup, false);
        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookViewHolder bookViewHolder, int i) {
        Book book = mBooks.get(i);
        bookViewHolder.bind(book);
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvBookTitle;
        TextView tvAuthor;
        TextView tvPublisher;
        TextView tvPublishedDate;

        public BookViewHolder(View itemView) {
            super(itemView);
            tvBookTitle = itemView.findViewById(R.id.tvBookTitle);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvPublisher = itemView.findViewById(R.id.tvPublisher);
            tvPublishedDate = itemView.findViewById(R.id.tvPbDate);

            itemView.setOnClickListener(this);
        }

        public void bind(Book book) {
            tvBookTitle.setText(book.title);
            tvAuthor.setText(book.authors);
            tvPublisher.setText(book.publisher);
            tvPublishedDate.setText(book.publishedDate);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Book selectedBook = mBooks.get(position);
            Intent intent = new Intent(v.getContext(), BookDetailActivity.class);
            intent.putExtra("Book",selectedBook);
            v.getContext().startActivity(intent);
        }
    }
}
