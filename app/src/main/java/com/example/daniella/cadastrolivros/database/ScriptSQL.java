package com.example.daniella.cadastrolivros.database;


public class ScriptSQL {

    public static String getCreateCliente(){
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(" CREATE TABLE IF NOT EXISTS LIVROS ( ");
        //sqlBuilder.append(" CREATE OR REPLACE TABLE LIVROS ( ");
        sqlBuilder.append(" _id                INTEGER       NOT NULL ");
        sqlBuilder.append("  PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("   NOME               VARCHAR (50), ");
        sqlBuilder.append("   AUTOR               VARCHAR (255), ");
        sqlBuilder.append("   DATAS              DATE, ");
        sqlBuilder.append("   OBSERVACOES        VARCHAR (255) ");
        sqlBuilder.append("  );");

        return sqlBuilder.toString();
    }
}



