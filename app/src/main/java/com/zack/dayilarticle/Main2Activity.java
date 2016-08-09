package com.zack.dayilarticle;

import android.content.Intent;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView listView;
    private List<ArticleBean> mData ;
    private myAdapter adpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        SQliteTool tool = new SQliteTool(this);
        mData = tool.readAllData();
        tool.colsedb();
        listView = (ListView) findViewById(R.id.loveArticle);
        adpter = new myAdapter(mData,this);
        listView.setAdapter(adpter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("tilte",mData.get(position).getTitle());
        intent.putExtra("author",mData.get(position).getAuthor());
        intent.putExtra("context_html",mData.get(position).getContext_html());
        intent.setClass(this,Main3Activity.class);
        startActivity(intent);
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
