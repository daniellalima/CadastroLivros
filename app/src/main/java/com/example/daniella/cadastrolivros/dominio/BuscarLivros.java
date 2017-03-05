package com.example.daniella.cadastrolivros.dominio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.*;

import com.example.daniella.cadastrolivros.dominio.entidades.Livro;

import java.util.Date;


public class BuscarLivros {

    private SQLiteDatabase conn;

    public BuscarLivros(SQLiteDatabase conn){

        this.conn = conn;

    }

    private ContentValues preencherContentValues(Livro livro){

        ContentValues values = new ContentValues();
        values.put("NOME", livro.getNome());
        values.put("AUTOR", livro.getAutor());
        values.put("DATAS", livro.getData().getTime());
        values.put("OBSERVACOES", livro.getObservacoes());

        return values;
    }

    public void excluir(Long id){
       conn.delete(Livro.TABELA, "_id = ?", new String[] {String.valueOf(id)});
    }

    public void editar(Livro livro){

        ContentValues values = preencherContentValues(livro);
        conn.update(Livro.TABELA, values, "_id = ?", new String[] {String.valueOf(livro.getId())});
    }


    public void inserir(Livro livro){

        ContentValues values = preencherContentValues(livro);
        conn.insertOrThrow(Livro.TABELA, null, values);
    }



    public ArrayAdapter<Livro>buscaClientes(Context context){

        ArrayAdapter<Livro>adpClientes = new ArrayAdapter<Livro>(context, android.R.layout.simple_list_item_1);
       Cursor cursor = conn.query("LIVROS", null, null, null, null, null, null);

        if(cursor.getCount() > 0){

            cursor.moveToFirst();

            do{
                Livro livro = new Livro();

                livro.setId(cursor.getLong(cursor.getColumnIndex(Livro.ID)));
                livro.setNome(cursor.getString(cursor.getColumnIndex(Livro.NOME)));
                livro.setAutor(cursor.getString(cursor.getColumnIndex(Livro.AUTOR)));
                livro.setData(new Date(cursor.getLong(cursor.getColumnIndex(Livro.DATA))));
                livro.setObservacoes(cursor.getString( cursor.getColumnIndex(Livro.OBSERVACOES ) ));


                adpClientes.add(livro);

            }
            while (cursor.moveToNext());

        }

        return adpClientes;
    }
}
