package com.zack.dayilarticle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Zackv on 2016/4/26.
 */
public class SQliteTool {
    private SQLiteDatabase db = null;
    private MySqliteHelper dbhelper = null;
    SQliteTool(Context context){
        dbhelper = new MySqliteHelper(context);
        db = dbhelper.getWritableDatabase();

    }
    public boolean isExist(ArticleBean bean){
        Cursor cursor = db.query("dayilyArticle", null, "title like ? and author like ?", new String[]{bean.getTitle(),bean.getAuthor()}, null, null, null);
        if (cursor.getCount()!=0)return true;
        return false;
    }
    //传入一个ArticleBean并储存到本地数据库
    public void saveData(ArticleBean item){
        ContentValues values = new ContentValues();
        values.put("author",item.getAuthor());
        values.put("title",item.getTitle());
        values.put("context_html",item.getContext_html());
        db.insert("dayilyArticle", null, values);
    }
    //传入一个ArticleBean的集合，储存到本地数据库
    public void saveData(List<ArticleBean> mData){
        for (ArticleBean item:mData) {
            saveData(item);
        }
    }
    //读取本地数据库中所有内容，返回一个ArticleBean的集合
    public List<ArticleBean> readAllData(){
        List<ArticleBean> mdata =new LinkedList<ArticleBean>();
        Cursor cursor = db.rawQuery("select * from dayilyArticle",null);
        if (cursor!=null){
            while (cursor.moveToNext()){
                ArticleBean item = new ArticleBean();
                item.setAuthor(cursor.getString(cursor.getColumnIndex("author")));
                item.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                item.setContext_html(cursor.getString(cursor.getColumnIndex("context_html")));
                mdata.add(item);
            }
            cursor.close();
        }

        return mdata;
    }

    //删除本地数据库中的一条数据，入口参数ArticleBean
    public void deleteData(ArticleBean item){
        db.delete("dayilyArticle", "title=?", new String[]{item.getTitle()});
    }

    public void colsedb(){
        db.close();
    }
}
