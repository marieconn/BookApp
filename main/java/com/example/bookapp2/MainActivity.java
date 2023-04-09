package com.example.bookapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnSearchBook, btnAddToRead, btnMaps, btnOpenMaps;
    static private final String TAG = "BookApp2";
    static private final int ADD_ITEM_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSearchBook = findViewById(R.id.btnSearchBook);
        btnAddToRead = findViewById(R.id.btnToRead);
        btnMaps = findViewById(R.id.btnMaps);
        btnOpenMaps = findViewById(R.id.openMapApp);

        btnSearchBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Entered MainActivity");
                Intent intent =new Intent(MainActivity.this, SearchBookActivity.class);
                startActivityForResult(intent, ADD_ITEM_REQUEST_CODE);
            }
        });

        btnAddToRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Entered MainActivity addToRead");
                Intent intent =new Intent(MainActivity.this, NewBookListActivity.class);
                startActivityForResult(intent, ADD_ITEM_REQUEST_CODE);
            }
        });

        btnMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this, MapsActivity.class);
                startActivityForResult(intent, ADD_ITEM_REQUEST_CODE);
            }
        });

        btnOpenMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
        });
    }
}