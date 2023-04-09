package com.example.bookapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchBookActivity extends AppCompatActivity {

    private EditText etSearchBooks;
    private ImageButton imBtnSearch;
    private RequestQueue requestQueue;
    private ArrayList<BookInfo> bookInfoArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);

        etSearchBooks = findViewById(R.id.etSearchBooks);
        imBtnSearch = findViewById(R.id.imBtnSearch);

        imBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etSearchBooks.getText().toString().isEmpty()) {
                    etSearchBooks.setError("Please enter search query");
                    return;
                }
                getBooksInfo(etSearchBooks.getText().toString());
            }
        });
    }

    private void getBooksInfo(String query) {

        bookInfoArrayList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(SearchBookActivity.this);
        requestQueue.getCache().clear();
        String url = "https://www.googleapis.com/books/v1/volumes?q=" + query;


        JsonObjectRequest booksObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray itemsArray = response.getJSONArray("items");

                    for (int i = 0; i < itemsArray.length(); i++) {
                        JSONObject itemsObj = itemsArray.getJSONObject(i);
                        JSONObject volumeObj = itemsObj.getJSONObject("volumeInfo");
                        String title = volumeObj.optString("title");
                        String publisher = volumeObj.optString("publisher");
                        String description = volumeObj.optString("description");
                        JSONObject imageLinks = volumeObj.optJSONObject("imageLinks");
                        String thumbnail = imageLinks.optString("thumbnail");



                        BookInfo bookInfo = new BookInfo(title, publisher, description, thumbnail);
                        bookInfoArrayList.add(bookInfo);
                        SearchBookAdapter adapter = new SearchBookAdapter(bookInfoArrayList, SearchBookActivity.this);

                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchBookActivity.this, RecyclerView.VERTICAL, false);
                        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvBooks);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    System.out.println("x12 test");
                    e.printStackTrace();
                    Toast.makeText(SearchBookActivity.this, "No Data Found" + e, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SearchBookActivity.this, "Error found is " + error, Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(booksObjRequest);
    }
}