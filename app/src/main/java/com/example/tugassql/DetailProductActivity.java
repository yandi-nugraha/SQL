package com.example.tugassql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailProductActivity extends AppCompatActivity {

    EditText nameEditText1, priceEditText1;
    Button updateBtn, deleteBtn;
    String name, price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        nameEditText1 = findViewById(R.id.name_editText1);
        priceEditText1 = findViewById(R.id.price_editText1);

        Intent intent = getIntent();
        name = intent.getStringExtra(ProductAdapter.NAME_EXTRA);
        price = intent.getStringExtra(ProductAdapter.PRICE_EXTRA);

        nameEditText1.setText(name);
        priceEditText1.setText(price);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });
    }

    private void updateData() {
        Product product = MainActivity.databaseHelper.getProductFromName(name);
        product.setName(nameEditText1.getText().toString());
        product.setPrice(Integer.valueOf(priceEditText1.getText().toString()));

        MainActivity.databaseHelper.updateProduct(product);
    }
}