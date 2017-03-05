package com.example.daniella.cadastrolivros.database;

import android.content.Context;
import android.database.sqlite.*;

public class DataBase extends SQLiteOpenHelper {

    public DataBase(Context context){

        super(context, "LIVROS", null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScriptSQL.getCreateCliente());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
