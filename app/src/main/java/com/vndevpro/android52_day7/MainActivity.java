package com.vndevpro.android52_day7;

import android.os.Bundle;

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

    private IItemClickListener clickListener = new IItemClickListener() {
        @Override
        public void onItemClick(int pos) {

        }

        @Override
        public void onChangeWishList(int position) {
            ProductModel productModel = mListProduct.get(position);
            productModel.setWish(!productModel.isWish());
            mListProduct.set(position, productModel);
//            mProductAdapter.notifyDataSetChanged();
            mProductAdapter.notifyItemChanged(position);
        }

        @Override
        public void onDelete(int position) {
            mListProduct.remove(position);
//            mProductAdapter.notifyDataSetChanged();
            mProductAdapter.notifyItemRemoved(position);

        }

        @Override
        public void onUpdate(int position) {
            ProductModel productModel = mListProduct.get(position);
            productModel.setProductName(productModel.getProductName() + " new");
            mListProduct.set(position, productModel);
            mProductAdapter.notifyDataSetChanged();
        }
    };

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