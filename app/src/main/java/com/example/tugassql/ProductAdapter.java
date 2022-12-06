package com.example.tugassql;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private ArrayList<Product> values;
    private final LayoutInflater inflater;

    public static final String NAME_EXTRA = "NAME-KEY";
    public static final String PRICE_EXTRA = "PRICE-KEY";
    public static final String ID_EXTRA = "ID-KEY";

    public ProductAdapter(Context context, ArrayList<Product> values) {
        this.values = values;
        this.inflater = LayoutInflater.from(context);
    }

    public void setFilteredList(ArrayList<Product> filteredList) {
        this.values = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Product product = values.get(position);
        holder.txtProductName.setText(product.getName());
        holder.txtPrice.setText("Rp"+String.valueOf(product.getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailProductActivity.class);
                intent.putExtra(NAME_EXTRA, product.getName());
                intent.putExtra(PRICE_EXTRA, String.valueOf(product.getPrice()));
                intent.putExtra(ID_EXTRA, product.getId());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtProductName, txtPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProductName = itemView.findViewById(R.id.txt_productName);
            txtPrice = itemView.findViewById(R.id.txt_price);
        }
    }
}
