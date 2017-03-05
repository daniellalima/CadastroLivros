package com.example.daniella.cadastrolivros;

import android.app.DatePickerDialog;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.*;
import android.widget.*;

import com.example.daniella.cadastrolivros.app.MensageBox;
import com.example.daniella.cadastrolivros.database.DataBase;
import com.example.daniella.cadastrolivros.dominio.BuscarLivros;
import com.example.daniella.cadastrolivros.dominio.entidades.Livro;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class ActCadLivros extends ActionBarActivity {

    private EditText txt_nome;
    private EditText txt_autor;
    private EditText txt_data;
    private EditText txt_obs;


    private DataBase database;
    private SQLiteDatabase conn;
    private BuscarLivros buscarLivros;
    private Livro livro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(com.example.daniella.cadastrolivros.R.layout.act_cad_livros);


        txt_nome = (EditText)findViewById(com.example.daniella.cadastrolivros.R.id.txt_nome);
        txt_autor = (EditText)findViewById(com.example.daniella.cadastrolivros.R.id.txt_autor);
        txt_obs = (EditText) findViewById(com.example.daniella.cadastrolivros.R.id.txt_obs);
        txt_data = (EditText) findViewById(com.example.daniella.cadastrolivros.R.id.txt_data);


        ExibirDataListener listener = new ExibirDataListener();
        txt_data.setOnClickListener(listener);
        txt_data.setOnFocusChangeListener(listener);
        txt_data.setKeyListener(null);

        livro = new Livro();

        Bundle bundle = getIntent().getExtras();

        if ((bundle != null) && (bundle.containsKey("LIVRO"))) {
            livro = (Livro)bundle.getSerializable("LIVRO");
            preencherDados();
        }  else

        livro = new Livro();

        try{
            database = new DataBase(this);
            conn = database.getWritableDatabase();

            buscarLivros = new BuscarLivros(conn);


        } catch (SQLException ex){
            MensageBox.Show(this, "Erro", "Erro ao criar o banco" + ex.getMessage());
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(com.example.daniella.cadastrolivros.R.menu.menu_act_cad_livros, menu);

        if(livro.getId() != 0)
        menu.getItem(1).setVisible(true);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case com.example.daniella.cadastrolivros.R.id.menu_acao1:
               salvar();
               finish();

                break;

            case com.example.daniella.cadastrolivros.R.id.menu_acao2:
                excluir();
                MensageBox.Show(this, "Erro", "Excluido com Sucesso!!");
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private void excluir(){
        try{
            buscarLivros.excluir(livro.getId());
        } catch (Exception ex){
            MensageBox.Show(this, "Erro", "Erro ao excluir!" + ex.getMessage());
        }


    }


    private void preencherDados(){
        txt_nome.setText( livro.getNome() );
        txt_autor.setText( livro.getAutor() );

        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
        String dt = format.format( livro.getData() );


        txt_data.setText( dt );
        txt_obs.setText( livro.getObservacoes() );

    }

    private void salvar(){

        try {

            livro.setNome(txt_nome.getText().toString());
            livro.setAutor(txt_autor.getText().toString());
            livro.setObservacoes(txt_obs.getText().toString());


            if(livro.getId() == 0)
                buscarLivros.inserir(livro);
            else
                buscarLivros.editar(livro);


         } catch (Exception ex){
            MensageBox.Show(this, "Erro", "Erro ao cadastrar" + ex.getMessage());
    }
    }

    private void exibirData(){

        Calendar calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dig = new DatePickerDialog(this, new selecionaDataListener(), ano, mes, dia);
        dig.show();
    }

    private class ExibirDataListener implements View.OnClickListener, View.OnFocusChangeListener {

        @Override
        public void onClick(View v) {
            exibirData();
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                exibirData();
            }

        }

    }

        private class selecionaDataListener implements DatePickerDialog.OnDateSetListener {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);

                Date data = calendar.getTime();
                DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
                String dt = format.format(data);
                txt_data.setText(dt);

                livro.setData(data);
            }
        }




}
