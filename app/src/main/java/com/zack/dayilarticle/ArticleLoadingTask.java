package com.zack.dayilarticle;

import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.webkit.WebView;

import java.io.IOException;

/**
 * Created by Zackv on 2016/8/6.
 */
public class ArticleLoadingTask  extends AsyncTask<String,Void,Void>{

    private  FloatingActionButton fab;
    private WebView wv;
    private String article = null;
    private Context context;

    public ArticleLoadingTask(WebView wv, FloatingActionButton fab,Context context) {
        this.wv = wv;
        this.fab = fab;
        this.context = context;
    }


    @Override
    protected Void doInBackground(String... params) {

        try {
            article = parseH.getContext(params[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        wv.loadDataWithBaseURL(null, article, "text/html", "utf-8",null);
    }
}
