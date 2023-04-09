package com.example.bookapp2;

import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NewBook {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    int bid;
    @ColumnInfo(name = "book_name")
    String bookName;
    @ColumnInfo(name = "date")
    String date;
    @ColumnInfo(name = "completed")
    boolean completed;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) { this.bid = bid; }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDate() { return date; }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
