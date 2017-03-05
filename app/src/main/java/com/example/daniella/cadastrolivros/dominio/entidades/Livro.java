package com.example.daniella.cadastrolivros.dominio.entidades;

import java.io.Serializable;
import java.util.Date;


public class Livro implements Serializable {

    public static String TABELA = "LIVROS";
    public static String ID = "_id";
    public static String NOME = "NOME";
    public static String AUTOR = "AUTOR";
    public static String DATA = "DATAS";
    public static String OBSERVACOES = "OBSERVACOES";



    private long id;
    private String nome;
    private String autor;
    private Date data;
    private String observacoes;

    public Livro() {
        id = 0;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }


    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }


    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public String toString(){
        return nome + " - " + observacoes;
    }}


