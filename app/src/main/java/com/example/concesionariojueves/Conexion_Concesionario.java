package com.example.concesionariojueves;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexion_Concesionario  extends SQLiteOpenHelper {

    public Conexion_Concesionario(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table TbLCliente(identificacion text primary key,nombre text not null,"+
                "usuario text not null,clave text not null,activo text not null default'si')");

        sqLiteDatabase.execSQL("create table TbLVehiculos(Placa text primary key,Marca text not null,"+
                "Modelo text not null,Valor text not null,activo text not null default'si')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE Tblclientes");{
          onCreate(sqLiteDatabase);
        }
        sqLiteDatabase.execSQL("DROP TABLE TblcVehiculos");{
            onCreate(sqLiteDatabase);
        }
    }
}
