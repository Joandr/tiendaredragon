package com.example.tiendacosas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tiendacosas.Adapters.ProductAdapter;
import com.example.tiendacosas.Entities.Product;
import com.example.tiendacosas.databinding.ActivityMainBinding;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private FirebaseFirestore db;
    ArrayList<Product> productArrayList;
    ProductAdapter productAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);
        db = FirebaseFirestore.getInstance();
        productArrayList = new ArrayList<>();
        productAdapter = new ProductAdapter(this.productArrayList);
        mainBinding.rvProducts.setHasFixedSize(true);
        mainBinding.rvProducts.setLayoutManager(new LinearLayoutManager(this));
        mainBinding.rvProducts.setAdapter(productAdapter);
        getProducts();

    }

    public void  getProducts(){
        db.collection("products")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            Toast.makeText(getApplicationContext(),
                                    "failed to retrieve data", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        for(DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                productArrayList.add(dc.getDocument().toObject(Product.class));
                            }
                        }
                        productAdapter.notifyDataSetChanged();

                    }
                });

    }
}