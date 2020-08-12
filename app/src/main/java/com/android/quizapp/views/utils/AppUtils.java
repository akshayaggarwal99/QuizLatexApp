package com.android.quizapp.views.utils;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class AppUtils {

    public static String getAssetJsonData(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("questions.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        Log.e("data", json);
        return json;

    }
}
