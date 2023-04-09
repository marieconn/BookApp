package com.example.bookapp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchBookAdapter extends RecyclerView.Adapter<SearchBookAdapter.BookViewHolder> {

    private ArrayList<BookInfo> bookInfoArrayList;
    private Context context;


    public SearchBookAdapter(ArrayList<BookInfo> bookInfoArrayList, Context context) {
        this.bookInfoArrayList = bookInfoArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_view_holder, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {

        BookInfo bookInfo = bookInfoArrayList.get(position);
        holder.tvBookTitle.setText(bookInfo.getTitle());
        holder.tvPublisher.setText(bookInfo.getPublisher());
        holder.tvDescription.setText(bookInfo.getDescription());
        Picasso.get().load(bookInfo.getThumbnail()).into(holder.imBook);

    }


    @Override
    public int getItemCount() {
        return bookInfoArrayList.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        TextView tvBookTitle, tvPublisher, tvDescription;
        ImageView imBook;

        public BookViewHolder(View itemView) {
            super(itemView);
            tvBookTitle = itemView.findViewById(R.id.tvBookTitle);
            tvPublisher = itemView.findViewById(R.id.tvBookPublisher);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            imBook = itemView.findViewById(R.id.ivBook);

        }
    }
}
