package com.example.tugassql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DetailProductActivity extends AppCompatActivity {

    EditText nameEditText1, priceEditText1;
    Button updateBtn, deleteBtn;
    String name, price;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        nameEditText1 = findViewById(R.id.name_editText1);
        priceEditText1 = findViewById(R.id.price_editText1);
        updateBtn = findViewById(R.id.update_btn);
        deleteBtn = findViewById(R.id.delete_btn);

        Intent intent = getIntent();
        id = intent.getIntExtra(ProductAdapter.ID_EXTRA, 0);
        name = intent.getStringExtra(ProductAdapter.NAME_EXTRA);
        price = intent.getStringExtra(ProductAdapter.PRICE_EXTRA);

        nameEditText1.setText(name);
        priceEditText1.setText(price);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData(id);
                toMainActivity();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData(id);
                toMainActivity();
            }
        });
    }

    private void deleteData(int id) {
        MainActivity.databaseHelper.deleteProduct(id);
        Toast.makeText(DetailProductActivity.this, "Deteled", Toast.LENGTH_SHORT).show();
    }

    private void updateData(int id) {
        Product product = new Product();
        product.setId(id);
        product.setName(nameEditText1.getText().toString());
        product.setPrice(Integer.valueOf(priceEditText1.getText().toString()));
        MainActivity.databaseHelper.updateProduct(product);
        Toast.makeText(DetailProductActivity.this, "Updated", Toast.LENGTH_SHORT).show();
    }

    private void toMainActivity() {
        Intent intent = new Intent(DetailProductActivity.this, MainActivity.class);
        startActivity(intent);
    }
}