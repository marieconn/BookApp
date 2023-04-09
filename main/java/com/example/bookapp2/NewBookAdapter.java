package com.example.bookapp2;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookapp2.db.AppDatabase;
import com.example.bookapp2.db.BookDao;

import java.util.List;

public class NewBookAdapter extends RecyclerView.Adapter<NewBookAdapter.NewBookViewHolder> {
    List<NewBook> newBookList;
    Context context;

    public NewBookAdapter(List<NewBook> newBookList, Context context) {
        this.newBookList = newBookList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_book, parent, false);
          return new NewBookViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewBookViewHolder holder, int position) {

        NewBook newBook = newBookList.get(position);
        holder.tvListBook.setText(newBook.getBookName());
        holder.tvDate.setText(newBook.getDate());
        holder.checkBoxRead.setTag(position);
        holder.checkBoxRead.setChecked(newBook.isCompleted());

        holder.checkBoxRead.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                newBookList.get((Integer) buttonView.getTag()).setCompleted(isChecked);
                AppDatabase con = AppDatabase.getInstance(context.getApplicationContext());
                NewBook book = newBookList.get(holder.getAdapterPosition());
                con.bookDao().update(book);
            }
        });


        holder.imDeleteBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewBook book = newBookList.get(holder.getAdapterPosition());
                AppDatabase.getInstance(context.getApplicationContext()).bookDao().deleteBook(book);
                int position = holder.getAdapterPosition();
                newBookList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, newBookList.size());
            }
        });
    }



    @Override
    public int getItemCount() {
        return newBookList.size();
    }

    public class NewBookViewHolder extends RecyclerView.ViewHolder {
        public TextView tvListBook, tvDate;
        public CheckBox checkBoxRead;
        public ImageButton imDeleteBook;

        public NewBookViewHolder(View v) {
            super(v);
            tvListBook = v.findViewById(R.id.tvListBook);
            tvDate = v.findViewById(R.id.textViewDate);
            imDeleteBook = v.findViewById(R.id.imbtnDeleteBook);
            checkBoxRead = v.findViewById(R.id.checkBoxRead);
        }
    }
}
