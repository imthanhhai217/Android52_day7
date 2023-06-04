package com.vndevpro.android52_day7;

import java.util.ArrayList;

public interface ILoadProduct {

    void onLoadProductSuccess(ArrayList<Product> data);

    void onLoadProductFailed(String message);
}
