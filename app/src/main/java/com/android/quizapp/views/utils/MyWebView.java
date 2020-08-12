package com.android.quizapp.views.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.android.quizapp.R;

import androidx.core.content.ContextCompat;

public class MyWebView extends WebView {

    private static final String TAG = "MyWebView";
    private String display_text;

    private static final float default_text_size = 18;

    private int text_color;
    private int text_size;
    private boolean clickable = false;

    private boolean enable_zoom_in_controls = false;

    public MyWebView(Context context) {
        super(context);
        configurationSettingWebView(enable_zoom_in_controls);
        setDefaultTextColor(context);
        setDefaultTextSize();
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        configurationSettingWebView(enable_zoom_in_controls);
        TypedArray mTypeArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.MyWebView,
                0, 0);
        try {
            setBackgroundColor(mTypeArray.getInteger(R.styleable.MyWebView_setViewBackgroundColor, ContextCompat.getColor(context, android.R.color.transparent)));
            setTextColor(mTypeArray.getColor(R.styleable.MyWebView_setTextColor, ContextCompat.getColor(context, android.R.color.black)));
            pixelSizeConversion(mTypeArray.getDimension(R.styleable.MyWebView_setTextSize, default_text_size));
            setDisplayText(mTypeArray.getString(R.styleable.MyWebView_setText));
            setClickable(mTypeArray.getBoolean(R.styleable.MyWebView_setClickable, false));


        } catch (Exception e) {
            Log.d(TAG, "Exception:" + e.toString());
        }
    }


    public void setDisplayText(String displayText) {
        display_text = displayText;
    }


    /**
     * Load Web View with url
     */
    public void load() {
        if (this.display_text != null) {
//            this.loadDataWithBaseURL("null",
//                    "<p><script type='text/javascript' "
//                            + "src='file:///android_asset/MathJax/unpacked/MathJax.js?config=TeX-AMS-MML_HTMLorMML'>"
//                            + "</script>" + display_text + "</p>", "text/html",
//                    "utf-8", "");
            this.loadDataWithBaseURL("null", getOfflineMathJaxConfig(), "text/html", "UTF-8", "about:blank");

        }
    }

    private String getOfflineMathJaxConfig() {
        String offline_config = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "<p><script type='text/javascript' "
                + "src='file:///android_asset/MathJax/unpacked/MathJax.js?config=TeX-AMS-MML_HTMLorMML'>"
                + "</script>" + display_text + "</p>" +
                "        <meta name=\"viewport\" content=\"width=device-width\"/>\n" +
                "<style type='text/css'>" +
                "body {" +
                "margin: 0px;" +
                "padding: 0px;" +
                "font-size:" + this.text_size + "px;" +
                "color:" + getHexColor(this.text_color) + ";" +
                " }" +
                " </style>" +
                "    </head>\n" +
                "</html>";

        return offline_config.replace("{formula}", this.display_text);

    }

    public void setViewBackgroundColor(int color) {
        setBackgroundColor(color);
        this.invalidate();
    }

    private void pixelSizeConversion(float dimension) {
        if (dimension == default_text_size) {
            setTextSize((int) default_text_size);
        } else {
            int pixel_dimen_equivalent_size = (int) ((double) dimension / 1.6);
            setTextSize(pixel_dimen_equivalent_size);
        }
    }

    public void setTextSize(int size) {
        this.text_size = size;
        load();

    }

    public void setTextColor(int color) {

        this.text_color = color;
        load();
    }

    private void setDefaultTextColor(Context context) {
        //sets default text color to black
        this.text_color = ContextCompat.getColor(context, android.R.color.black);

    }

    private void setDefaultTextSize() {
        //sets view default text size to 18
        this.text_size = (int) default_text_size;
    }

    private String getHexColor(int intColor) {
        //Android and javascript color format differ javascript support Hex color, so the android color which user sets is converted to hexcolor to replicate the same in javascript.
        String hexColor = String.format("#%06X", (0xFFFFFF & intColor));
        return hexColor;
    }

    public void setClickable(boolean is_clickable) {
        this.setEnabled(true);
        this.clickable = is_clickable;
        this.enable_zoom_in_controls = !is_clickable;
        configurationSettingWebView(this.enable_zoom_in_controls);
        this.invalidate();
    }

    private void configurationSettingWebView(boolean enable_zoom_in_controls) {
        this.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        WebSettings settings = this.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setDisplayZoomControls(enable_zoom_in_controls);
        settings.setBuiltInZoomControls(enable_zoom_in_controls);
        settings.setSupportZoom(enable_zoom_in_controls);
        this.setVerticalScrollBarEnabled(enable_zoom_in_controls);
        this.setHorizontalScrollBarEnabled(enable_zoom_in_controls);
        Log.d(TAG, "Zoom in controls:" + enable_zoom_in_controls);
    }

}