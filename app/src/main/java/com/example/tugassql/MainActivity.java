package com.example.tugassql;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Product> products = new ArrayList<>();
    private ProductAdapter productAdapter;

    public static DatabaseHelper databaseHelper;

    EditText searchEditText;
    Button searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        getAllProducts();

        productAdapter = new ProductAdapter(this, products);
        recyclerView = findViewById(R.id.rv_data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productAdapter);

        searchEditText = findViewById(R.id.search_editText);
        searchBtn = findViewById(R.id.search_btn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (searchEditText.getText().toString() != "") {
                    String key = searchEditText.getText().toString();
                    filterList(key);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_button, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_btn) {
            Intent intent = new Intent(MainActivity.this, AddProductActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void getAllProducts() {
        ArrayList<Product> allData = databaseHelper.getAllProducts();
        products.addAll(allData);
    }

    public void filterList(String keyword) {
        ArrayList<Product> filteredProducts = new ArrayList<>();

        for (Product product : products) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase())) {
                filteredProducts.add(product);
            }
        }

        if (filteredProducts.isEmpty()) {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        } else {
            productAdapter.setFilteredList(filteredProducts);
        }
    }
}