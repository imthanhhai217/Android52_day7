package com.vndevpro.android52_day7;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView rvDemo;
    private ArrayList<ProductModel> mListProduct;
    private ProductAdapter mProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initView() {
        rvDemo = findViewById(R.id.rvDemo);
        mProductAdapter = new ProductAdapter(mListProduct);
        mProductAdapter.setCallback(clickListener);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvDemo.setLayoutManager(linearLayoutManager);
//        rvDemo.setHasFixedSize(true);
//        rvDemo.setItemViewCacheSize(10);
        rvDemo.setAdapter(mProductAdapter);
    }

    private IItemClickListener clickListener = pos -> Log.d(TAG, "onItemClick: " + pos);

    private void initData() {
        mListProduct = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ProductModel productModel = new ProductModel();
            productModel.setProductName("Product " + i);
            productModel.setProductImage("https://play-lh.googleusercontent.com/j9zl-GpzBaNY_nAE4XJ5LquJihqK3FqrhwEKNwwdFsp7RcIz0b-CNFGL5OEk_hiSPKnr");
            productModel.setProductPrices("$" + (i + 1 * 1000));
            productModel.setRate(new Random().nextInt(5) + "");
            productModel.setWish(false);
            mListProduct.add(productModel);
        }
    }
}