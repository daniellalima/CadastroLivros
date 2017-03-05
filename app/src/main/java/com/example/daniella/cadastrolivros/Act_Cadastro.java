package com.example.daniella.cadastrolivros;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.database.sqlite.*;
import android.database.*;

import com.example.daniella.cadastrolivros.dominio.entidades.Livro;
import com.example.daniella.cadastrolivros.app.MensageBox;
import com.example.daniella.cadastrolivros.database.DataBase;
import com.example.daniella.cadastrolivros.dominio.BuscarLivros;

public class Act_Cadastro extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    private ImageButton btn_adicionar;
    private EditText txt_pesquisa;
    private ListView lst_clientes;
    private ArrayAdapter<Livro>adpClientes;

    private DataBase database;
    private SQLiteDatabase conn;
    private BuscarLivros buscarLivros;

    private FiltrarDados filtrarDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.daniella.cadastrolivros.R.layout.act__cadastro);

        btn_adicionar = (ImageButton) findViewById(com.example.daniella.cadastrolivros.R.id.btn_adicionar);
        txt_pesquisa = (EditText) findViewById(com.example.daniella.cadastrolivros.R.id.txt_pesquisa);
        lst_clientes = (ListView) findViewById(com.example.daniella.cadastrolivros.R.id.lst_clientes);

        btn_adicionar.setOnClickListener(this);

        lst_clientes.setOnItemClickListener(this);



        try {


            database = new DataBase(this);
            conn = database.getWritableDatabase();

            buscarLivros = new BuscarLivros(conn);
            adpClientes = buscarLivros.buscaClientes(this);
            lst_clientes.setAdapter(adpClientes);


            filtrarDados = new FiltrarDados(adpClientes);
            txt_pesquisa.addTextChangedListener(filtrarDados);

        } catch (SQLException ex) {
            MensageBox.Show(this, "Erro", "Erro ao criar o banco" + ex.getMessage());
        }
    }

    @Override
    public void onClick(View v) {
       Intent it = new Intent(this, ActCadLivros.class);
       startActivityForResult(it, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        adpClientes = buscarLivros.buscaClientes(this);
        filtrarDados.setArrayAdapter(adpClientes);
        lst_clientes.setAdapter(adpClientes);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Livro livro = adpClientes.getItem(position);
        Intent it = new Intent(this, ActCadLivros.class);
        it.putExtra("LIVRO", livro);//CLIENTE
        startActivityForResult(it, 0);
    }

    private class FiltrarDados implements TextWatcher{

        private ArrayAdapter<Livro>arrayAdapter;

        private FiltrarDados(ArrayAdapter<Livro>arrayAdapter){
            this.arrayAdapter = arrayAdapter;
        }

        public void setArrayAdapter(ArrayAdapter<Livro>arrayAdapter){
            this.arrayAdapter = arrayAdapter;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        arrayAdapter.getFilter().filter(s);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}