package com.zack.dayilarticle;

import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ObservableScrollView;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private WebView wv;
    private FloatingActionButton fab;
    private ObservableScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitView();
        ArticleLoadingTask articleLoadingTask = new ArticleLoadingTask(wv,fab,this);
        articleLoadingTask.execute(Costant.TODAYURL);

    }

    private void InitView(){
        wv = (WebView) findViewById(R.id.wv);
        scrollView = (ObservableScrollView) findViewById(R.id.scrollView);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"随机文章",Toast.LENGTH_SHORT).show();
                ArticleLoadingTask articleLoadingTask2 = new ArticleLoadingTask(wv,fab,MainActivity.this);
                articleLoadingTask2.execute(Costant.RANDOMURL);
            }
        });
        fab.attachToScrollView( scrollView);
        //test
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.collect, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.todayArticle:
                Toast.makeText(this,"今日文章",Toast.LENGTH_SHORT).show();
                ArticleLoadingTask articleLoadingTask = new ArticleLoadingTask(wv,fab,this);
                articleLoadingTask.execute(Costant.TODAYURL);
                break;
            case R.id.randomArticle:
                Toast.makeText(this,"随机文章",Toast.LENGTH_SHORT).show();
                ArticleLoadingTask articleLoadingTask2 = new ArticleLoadingTask(wv,fab,this);
                articleLoadingTask2.execute(Costant.RANDOMURL);
                break;
            case R.id.collect:
                goLove();
                break;
            case R.id.help:
                goHelp();
                break;
            case R.id.about:
                goAbout();
                break;
            case R.id.collect_article:
                SQliteTool tool = new SQliteTool(getBaseContext());
                if(tool.isExist(parseH.getArticleBean())){
                    tool.colsedb();
                    Toast.makeText(MainActivity.this,"文章已收藏！！！",Toast.LENGTH_SHORT).show();
                    break;
                }
                tool.saveData(parseH.getArticleBean());
                tool.colsedb();
                Toast.makeText(MainActivity.this,"收藏成功！！！",Toast.LENGTH_SHORT).show();
                break;
        }
        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.todayArticle:
                Toast.makeText(this,"今日文章",Toast.LENGTH_SHORT).show();
                ArticleLoadingTask articleLoadingTask = new ArticleLoadingTask(wv,fab,this);
                articleLoadingTask.execute(Costant.TODAYURL);
                break;
            case R.id.randomArticle:
                Toast.makeText(this,"随机文章",Toast.LENGTH_SHORT).show();
                ArticleLoadingTask articleLoadingTask2 = new ArticleLoadingTask(wv,fab,this);
                articleLoadingTask2.execute(Costant.RANDOMURL);
                break;
            case R.id.collect:
                goLove();
                break;
            case R.id.help:
                goHelp();
                break;
            case R.id.about:
                goAbout();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void goAbout(){
        Intent it = new Intent(MainActivity.this,About.class);
        startActivity(it);
    }
    private void goLove(){
        Intent it = new Intent(MainActivity.this,Main2Activity.class);
        startActivity(it);
    }
    private void goHelp(){
        Intent it = new Intent(MainActivity.this,Main4Activity.class);
        startActivity(it);
    }
}
