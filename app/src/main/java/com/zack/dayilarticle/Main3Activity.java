package com.zack.dayilarticle;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;

public class Main3Activity extends AppCompatActivity {

    private WebView show;
    private ArticleBean bean = new ArticleBean();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        show = (WebView) findViewById(R.id.showArticle);
        Intent it = getIntent();
        bean.setTitle(it.getStringExtra("title"));
        bean.setAuthor(it.getStringExtra("author"));
        bean.setContext_html(it.getStringExtra("context_html"));
        show.loadDataWithBaseURL(null, bean.getContext_html(), "text/html", "utf-8",null);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
