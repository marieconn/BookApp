package com.example.bookapp2;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.bookapp2.db.AppDatabase;

import java.util.Calendar;

public class AddBookActivity extends AppCompatActivity {

     EditText etNewBook;
     static TextView tvDate;
     private Button btnSave, btnCancel, btnDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        etNewBook =findViewById(R.id.etNewBook);
        tvDate =findViewById(R.id.textViewDate);

        btnCancel =findViewById(R.id.btnCancel);
        btnDate =findViewById(R.id.btnDate);
        btnSave =findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                startActivity(new Intent(AddBookActivity.this, NewBookListActivity.class));
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
    }

    public void saveData() {
        String bookName = etNewBook.getText().toString();
        String date = tvDate.getText().toString();

        NewBook newBook = new NewBook();
        newBook.setBookName(bookName);
        newBook.setDate(date);

        AppDatabase.getInstance(getApplicationContext()).bookDao().insertBook(newBook);

        etNewBook.setText("");
        tvDate.setText("");

        Toast.makeText(AddBookActivity.this, "Added book successfully", Toast.LENGTH_LONG ).show();
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            tvDate.setText(day + "." + (month+1) + "." + year + ".");
        }
    }
}