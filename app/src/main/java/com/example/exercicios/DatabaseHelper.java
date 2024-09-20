package com.example.exercicios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "historicoTreinos.db";
    static final String TABLE_NAME = "historico";
    private static final String COL1 = "ID";
    private static final String COL2 = "NOME";
    private static final String COL3 = "EXERCICIO";
    private static final String COL4 = "DATA";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NOME TEXT, EXERCICIO TEXT, DATA TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean adicionarExercicio(String nome, String exercicio, String data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, nome);
        contentValues.put(COL3, exercicio);
        contentValues.put(COL4, data);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            Log.e("DatabaseHelper", "Erro ao adicionar exercício.");
        }
        return result != -1;
    }

    public Cursor verHistorico() {
        return null;
    }

    public void limparHistorico() {

    }
}
