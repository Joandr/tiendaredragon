package com.example.tiendacosas.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiendacosas.Entities.Product;
import com.example.tiendacosas.databinding.ProductItemBinding;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private ProductItemBinding productItemBinding;
    private ArrayList<Product> productArrayList;
    public  ProductAdapter(Context context, ArrayList<Product> productArrayList){
        this.context = context;
        this.productArrayList = productArrayList;

    }

    public ProductAdapter(ArrayList<Product> productArrayList) {
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        productItemBinding = ProductItemBinding.inflate(LayoutInflater.from(context));
        return new ProductViewHolder(productItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        Product product = productArrayList.get(position);
        holder.itemBinding.tvName.setText(product.getName());
        holder.itemBinding.tvDescripcion.setText(product.getDescription());
        holder.itemBinding.tvStock.setText(String.valueOf(product.getStock()));
        holder.itemBinding.tvPrice.setText(String.valueOf(product.getPrice()));
        holder.itemBinding.tvCategory.setText(product.getCategory());


    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ProductItemBinding itemBinding;
        public ProductViewHolder(@NonNull ProductItemBinding itemBinding) {
            super(itemBinding.getRoot());
            productItemBinding = itemBinding;

        }
    }
}
