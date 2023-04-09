package com.example.bookapp2.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bookapp2.NewBook;

import java.util.List;

@Dao
public interface BookDao {

    @Query("SELECT * FROM NewBook")
    List<NewBook> getAllBooks();

    @Query("SELECT * FROM NewBook WHERE bid = :id")
    NewBook findById(int id);

    @Insert()
    void insertBook(NewBook... newBook);

    @Delete
    void deleteBook(NewBook newBook);

    @Update()
    void update(NewBook newBook);
}
