package com.example.daniella.cadastrolivros.app;

import android.content.Context;
import android.support.v7.app.AlertDialog;


public class MensageBox {

    public static void ShowAlert(Context ctx, String title, String msg){
       Show(ctx, title, msg, android.R.drawable.ic_dialog_alert) ;
    }

    public static void ShowInfo(Context ctx, String title, String msg){
        Show(ctx, title, msg, android.R.drawable.ic_dialog_info) ;
    }

    public static void Show(Context ctx, String title, String msg){
        Show(ctx, title, msg, 0) ;
    }

    public static void Show(Context ctx, String title, String msg, int iconId){

        AlertDialog.Builder dig = new AlertDialog.Builder(ctx);
        dig.setIcon(iconId);
        dig.setTitle(title);
        dig.setMessage(msg);
        dig.setNeutralButton("OK", null);
        dig.show();

    }
}
