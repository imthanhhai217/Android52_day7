package com.vndevpro.android52_day7;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ILoadProduct, ILoadImage {

    private static final String TAG = "MainActivity";
    private RecyclerView rvDemo;
    private ProgressBar pbLoading;
    private ImageView imgDemo;
    private LinearLayout llLoading;
    private ArrayList<Product> mListProduct;
    private ProductAdapter mProductAdapter;
    private SqliteHelper mSqliteHelper;

    public static final String URL = "https://images.pexels.com/photos/1738675/pexels-photo-1738675.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initView() {
        rvDemo = findViewById(R.id.rvDemo);
        pbLoading = findViewById(R.id.pbLoading);
        llLoading = findViewById(R.id.llLoading);
        imgDemo = findViewById(R.id.imgDemo);

        pbLoading.setMax(100);

        mProductAdapter = new ProductAdapter(mListProduct);
        mProductAdapter.setCallback(clickListener);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvDemo.setLayoutManager(linearLayoutManager);
        rvDemo.setHasFixedSize(true);
//        rvDemo.setItemViewCacheSize(10);
        rvDemo.setAdapter(mProductAdapter);

        llLoading.setVisibility(View.VISIBLE);

        new LoadImageAsyncTask(this).execute(URL);
    }

    private IItemClickListener clickListener = new IItemClickListener() {
        @Override
        public void onItemClick(int pos) {

        }

        @Override
        public void onChangeWishList(int position) {
            Product productModel = mListProduct.get(position);
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
            Product productModel = mListProduct.get(position);
            productModel.setTitle(productModel.getTitle() + " new");
            mListProduct.set(position, productModel);
            mProductAdapter.notifyDataSetChanged();
        }
    };

    private void initData() {
//        mListProduct = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            ProductModel productModel = new ProductModel();
//            productModel.setProductName("Product " + i);
//            productModel.setProductImage("https://play-lh.googleusercontent.com/j9zl-GpzBaNY_nAE4XJ5LquJihqK3FqrhwEKNwwdFsp7RcIz0b-CNFGL5OEk_hiSPKnr");
//            productModel.setProductPrices("$" + (i + 1 * 1000));
//            productModel.setRate(new Random().nextInt(5) + "");
//            productModel.setWish(false);
//            mListProduct.add(productModel);
//        }
        mSqliteHelper = new SqliteHelper(this);

//        Product product = new Product();
//        product.setTitle("iPhone 9");
//        product.setDescription("An apple mobile ");
//        product.setPrice(549);
//        product.setDiscountPercentage(12.96);
//        product.setRating(4.69);
//        product.setStock(94);
//        product.setBrand("Apple");
//        product.setCategory("smartphones");
//        product.setThumbnail("https://i.dummyjson.com/data/products/1/thumbnail.jpg");
//        product.setImages("[https://i.dummyjson.com/data/products/1/1.jpg" + ",https://i.dummyjson.com/data/products/1/2.jpg" + ",https://i.dummyjson.com/data/products/1/3.jpg" + ", https://i.dummyjson.com/data/products/1/4.jpg" + ", https://i.dummyjson.com/data/products/1/thumbnail.jpg]");

//        mSqliteHelper.insertNewProduct(product);
//        Product product1 = product;
//        product1.setTitle("Iphone 8");
//        mSqliteHelper.updateNewProduct(3,product1);
//
//        Product product2 = product;
//        product1.setTitle("Iphone 7");
//        mSqliteHelper.updateNewProduct(4,product2);

//        mSqliteHelper.deleteProduct(1);

//        mListProduct = mSqliteHelper.getListProduct();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LoadingAsyncTask loadingAsyncTask = new LoadingAsyncTask(mSqliteHelper, MainActivity.this);
                loadingAsyncTask.setActivity(MainActivity.this);
                loadingAsyncTask.setProgressBar(pbLoading);
                loadingAsyncTask.execute();
            }
        }, 1000);

    }

    @Override
    public void onLoadProductSuccess(ArrayList<Product> data) {
//        llLoading.setVisibility(View.GONE);
        mProductAdapter.update(data);
    }

    @Override
    public void onLoadProductFailed(String message) {
//        llLoading.setVisibility(View.GONE);
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoadImageSuccess(Bitmap bitmap) {
        imgDemo.setImageBitmap(null);
        imgDemo.setImageBitmap(bitmap);
    }

    @Override
    public void onLoadImageFailed(String message) {
        imgDemo.setImageDrawable(getResources().getDrawable(R.drawable.star));
    }
}