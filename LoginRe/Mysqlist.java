package com.example.myapplications.LoginRe;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Mysqlist extends SQLiteOpenHelper {
    public static final String TB_NAME = "user_mo";

    private String sql="create table if not exists "+TB_NAME+"(_id Integer primary key autoincrement,name varchar(30) not null,pass varchar(50) not null)";

    public Mysqlist(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TB_NAME);
        onCreate(db);
    }
}
