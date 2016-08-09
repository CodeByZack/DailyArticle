package com.zack.dayilarticle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zackv on 2016/4/26.
 */
public class MySqliteHelper extends SQLiteOpenHelper {



    public MySqliteHelper(Context context) {
        super(context, "usr.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists dayilyArticle(_id integer primary key autoincrement,author text noy null,title text noy null,context_html text noy null)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
