package com.example.tugassql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddProductActivity extends AppCompatActivity {

    EditText nameEditText, priceEditText;
    Button addBtn;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        nameEditText = findViewById(R.id.name_editText);
        priceEditText = findViewById(R.id.price_editText);
        addBtn = findViewById(R.id.add_btn);

        databaseHelper = new DatabaseHelper(this);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();

                Intent intent = new Intent(AddProductActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void insertData() {
        Product product = new Product();
        product.setName(nameEditText.getText().toString());
        product.setPrice(Integer.valueOf(priceEditText.getText().toString()));

        databaseHelper.insertRecord(product);
        Toast.makeText(AddProductActivity.this, "Data successfully added!", Toast.LENGTH_SHORT).show();
    }
}