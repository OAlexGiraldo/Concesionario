package com.example.concesionariojueves;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VehiculosActivyty extends AppCompatActivity {
    EditText jetplaca, jetmarca, jetmodelo, jetvalor;
    Button jbtnguardarv, jbtncancelar, jbtnconsultar, jbtanular, jbtnregresar;
    int vh;
    long resp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehiculos_activyty);

        getSupportActionBar().hide();
        jetplaca = findViewById(R.id.etplaca);
        jetmarca = findViewById(R.id.etmarca);
        jetmodelo = findViewById(R.id.etmodelo);
        jetvalor = findViewById(R.id.etvalor);
        jbtanular = findViewById(R.id.btanularv);
        jbtncancelar = findViewById(R.id.btncancelarv);
        jbtnguardarv = findViewById(R.id.btnguardarv);
        jbtnconsultar = findViewById(R.id.btncansultarv);
        jbtnregresar = findViewById(R.id.btnregresarv);
        vh = 0;
    }

    public void limpiar_campos() {
        jetvalor.setText("");
        jetmodelo.setText("");
        jetmarca.setText("");
        jetplaca.setText("");
        jetplaca.requestFocus();
    }

    public void GuardarV(View view) {
        String placa, marca, modelo, valor;
        placa = jetplaca.getText().toString();
        marca = jetmarca.getText().toString();
        modelo = jetmodelo.getText().toString();
        valor = jetvalor.getText().toString();
        if (placa.isEmpty() || marca.isEmpty() || modelo.isEmpty()
                || valor.isEmpty()) {
            Toast.makeText(this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show();
            jetplaca.requestFocus();
        } else {

            Conexion_Concesionario admin = new Conexion_Concesionario(this, "concesionario.bd", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();
            ContentValues dato = new ContentValues();
            dato.put("placa", placa);
            dato.put("marca", marca);
            dato.put("modelo", modelo);
            dato.put("valor", valor);
            if (vh == 0)
                resp = db.insert("TblVehiculo", null, dato);
            else {
                vh = 0;
                resp = db.update("TblVehiculo", dato, "placa='" + placa + "'", null);
            }
            if (resp > 0) {
                Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show();
                limpiar_campos();
            } else {
                Toast.makeText(this, "Error guardando registro", Toast.LENGTH_SHORT).show();
            }
            db.close();

        }
    }
    public void ConsultarV(View view){

        Consultar_Vehiculo();
    }

    public void Consultar_Vehiculo(){
        String placa;
        placa=jetplaca.getText().toString();
        if (placa.isEmpty()){
            Toast.makeText(this, "Identificacion requerida", Toast.LENGTH_SHORT).show();
            jetplaca.requestFocus();
        }
        else {
            Conexion_Concesionario admin = new Conexion_Concesionario(this, "concesionario.bd", null, 1);
            SQLiteDatabase db=admin.getReadableDatabase();
            Cursor fila=db.rawQuery("select * from TblVehiculo where Placa='" + placa + "'",null);
            if (fila.moveToNext()){
                vh=1;
                jetmarca.setText(fila.getString(1));
                jetmodelo.setText(fila.getString(2));
                jetvalor.setText(fila.getString(3));
                Toast.makeText(this, "Registrado encontrado", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Registro no existe", Toast.LENGTH_SHORT).show();
            }
            db.close();
        }
    }
    public void RegresarV(View view){
        Intent intvehi=new Intent(this,MainActivity.class);
        startActivity(intvehi);
    }
    public void CancelarV(View view){

        limpiar_campos();
    }
}