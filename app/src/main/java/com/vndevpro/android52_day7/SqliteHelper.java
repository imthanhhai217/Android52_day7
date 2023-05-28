package com.vndevpro.android52_day7;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SqliteHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "products.db";
    public static final String TABLE_NAME = "product";
    public static final String ID_COLUMN = "id";
    public static final String TITLE_COLUMN = "title";
    public static final String DES_COLUMN = "description";
    public static final String PRICES_COLUMN = "price";
    public static final String DISCOUNT_COLUMN = "discountPercentage";
    public static final String RATING_COLUMN = "rating";
    public static final String STOCK_COLUMN = "stock";
    public static final String BRAND_COLUMN = "brand";
    public static final String CATEGORY_COLUMN = "category";
    public static final String THUMBNAIL_COLUMN = "thumbnail";
    public static final String IMAGES_COLUMN = "images";

    public SqliteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + TITLE_COLUMN + " TEXT NOT NULL," + DES_COLUMN + " TEXT," + PRICES_COLUMN + " INTEGER NOT NULL," + DISCOUNT_COLUMN + " INTEGER NOT NULL," + RATING_COLUMN + " INTEGER NOT NULL," + STOCK_COLUMN + " INTEGER NOT NULL," + BRAND_COLUMN + " TEXT NOT NULL," + CATEGORY_COLUMN + " TEXT NOT NULL," + THUMBNAIL_COLUMN + " TEXT NOT NULL," + IMAGES_COLUMN + " TEXT NOT NULL)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertNewProduct(Product product) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE_COLUMN, product.getTitle());
        contentValues.put(DES_COLUMN, product.getDescription());
        contentValues.put(PRICES_COLUMN, product.getPrice());
        contentValues.put(DISCOUNT_COLUMN, product.getDiscountPercentage());
        contentValues.put(RATING_COLUMN, product.getRating());
        contentValues.put(STOCK_COLUMN, product.getStock());
        contentValues.put(BRAND_COLUMN, product.getBrand());
        contentValues.put(CATEGORY_COLUMN, product.getCategory());
        contentValues.put(THUMBNAIL_COLUMN, product.getThumbnail());
        contentValues.put(IMAGES_COLUMN, product.getImages());

        long newRecord = database.insert(TABLE_NAME, null, contentValues);

        database.close();
    }

    public void updateNewProduct(int id, Product product) {
        SQLiteDatabase database = getWritableDatabase();
        String whereClause = ID_COLUMN + " LIKE? ";

        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE_COLUMN, product.getTitle());
        contentValues.put(DES_COLUMN, product.getDescription());
        contentValues.put(PRICES_COLUMN, product.getPrice());
        contentValues.put(DISCOUNT_COLUMN, product.getDiscountPercentage());
        contentValues.put(RATING_COLUMN, product.getRating());
        contentValues.put(STOCK_COLUMN, product.getStock());
        contentValues.put(BRAND_COLUMN, product.getBrand());
        contentValues.put(CATEGORY_COLUMN, product.getCategory());
        contentValues.put(THUMBNAIL_COLUMN, product.getThumbnail());
        contentValues.put(IMAGES_COLUMN, product.getImages());

        database.update(TABLE_NAME, contentValues, whereClause, new String[]{id + ""});
        database.close();
    }

    public void deleteProduct(int id) {
        SQLiteDatabase database = getWritableDatabase();
        String whereClause = ID_COLUMN + " LIKE? ";

        database.delete(TABLE_NAME, whereClause, new String[]{id + ""});
        database.close();
    }

    public ArrayList<Product> getListProduct() {
        ArrayList<Product> result = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(ID_COLUMN));
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(TITLE_COLUMN));
            @SuppressLint("Range") String des = cursor.getString(cursor.getColumnIndex(DES_COLUMN));
            @SuppressLint("Range") int price = cursor.getInt(cursor.getColumnIndex(PRICES_COLUMN));
            @SuppressLint("Range") double discount = cursor.getDouble(cursor.getColumnIndex(DISCOUNT_COLUMN));
            @SuppressLint("Range") double rating = cursor.getDouble(cursor.getColumnIndex(RATING_COLUMN));
            @SuppressLint("Range") int stock = cursor.getInt(cursor.getColumnIndex(STOCK_COLUMN));
            @SuppressLint("Range") String brand = cursor.getString(cursor.getColumnIndex(BRAND_COLUMN));
            @SuppressLint("Range") String category = cursor.getString(cursor.getColumnIndex(CATEGORY_COLUMN));
            @SuppressLint("Range") String thumbnail = cursor.getString(cursor.getColumnIndex(THUMBNAIL_COLUMN));
            @SuppressLint("Range") String images = cursor.getString(cursor.getColumnIndex(IMAGES_COLUMN));

            Product product = new Product();
            product.setId(id);
            product.setTitle(title);
            product.setDescription(des);
            product.setPrice(price);
            product.setDiscountPercentage(discount);
            product.setRating(rating);
            product.setStock(stock);
            product.setBrand(brand);
            product.setCategory(category);
            product.setThumbnail(thumbnail);
            product.setImages(images);

            result.add(product);
        }

        return result;
    }
}
