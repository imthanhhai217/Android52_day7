package com.vndevpro.android52_day7;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class LoadingAsyncTask extends AsyncTask<Void, Double, ArrayList<Product>> {

    private static final String TAG = "LoadingAsyncTask";
    private SqliteHelper mSqliteHelper;
    private ILoadProduct callback;

    private Activity activity;

    private ProgressBar mProgressBar;

    public void setProgressBar(ProgressBar progressBar) {
        this.mProgressBar = progressBar;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public LoadingAsyncTask(SqliteHelper sqliteHelper, ILoadProduct loadProduct) {
        this.mSqliteHelper = sqliteHelper;
        this.callback = loadProduct;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Double... values) {
        super.onProgressUpdate(values);
        Log.d(TAG, "onProgressUpdate: " + values[0]);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mProgressBar.setProgress((int) Math.round(values[0]),true);
        }
    }

    @Override
    protected ArrayList<Product> doInBackground(Void... voids) {
        ArrayList<Product> list = new ArrayList<>();
        //Do something
        int res = 0;
        for (double i = 0; i < 10000; i++) {
            res += i;
            double progress = i / 10000 * 100;
            Log.d(TAG, "doInBackground: " + progress);
            publishProgress(progress);
        }
        Log.d(TAG, "doInBackground: " + res);
//        list.addAll(mSqliteHelper.getListProduct());

        return list;
    }

    @Override
    protected void onPostExecute(ArrayList<Product> products) {
        super.onPostExecute(products);
        if (products != null) {
            callback.onLoadProductSuccess(products);
            Toast.makeText(activity.getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
        } else {
            callback.onLoadProductFailed("Load failed !");
        }
    }
}
