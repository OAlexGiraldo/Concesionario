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
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

        EditText jetidentificacion,jetnombre,jetemail,jetpassword1,jetpassword2;
        TextView jtvactivo;
        Button jbtnregresa,jbtnguardar,jbtncancelar,jbtnanular,jbtnconsultar;
        long resp;
        int sw;
        String identificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        getSupportActionBar().hide();

        jetidentificacion=findViewById(R.id.edtnidentificacion);
        jetnombre=findViewById(R.id.etnombre);
        jetemail=findViewById(R.id.etEmail);
        jetpassword1=findViewById(R.id.etpassword);
        jetpassword2=findViewById(R.id.etPassword2);
       jtvactivo=findViewById(R.id.tvactivo);
        jbtnanular=findViewById(R.id.btnAnular);
        jbtncancelar=findViewById(R.id.btncancelar);
        jbtnguardar=findViewById(R.id.btnguardar);
        jbtnconsultar=findViewById(R.id.btnConsultar);
        jbtnregresa=findViewById(R.id.btnRegresar);
        sw=0;

    }

    public void limpiar_campos(){
        sw=0;
        jetidentificacion.setText("");
        jetnombre.setText("");
        jetemail.setText("");
        jetpassword1.setText("");
        jetpassword2.setText("");
        jetidentificacion.requestFocus();
    }

    public void Guardar(View view){
        String  nombre, usuario,clave1,clave2;
        identificacion=jetidentificacion.getText().toString();
        nombre=jetnombre.getText().toString();
        usuario=jetemail.getText().toString();
        clave1=jetpassword1.getText().toString();
        clave2=jetpassword2.getText().toString();
        if (identificacion.isEmpty() || nombre.isEmpty() || usuario.isEmpty()
                || clave1.isEmpty() || clave2.isEmpty()) {
            Toast.makeText(this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show();
            jetidentificacion.requestFocus();
        }
        else{
            if (!clave1.equals(clave2)){
                Toast.makeText(this, "Clave y confirmacion de clave son diferentes", Toast.LENGTH_SHORT).show();
                jetpassword1.requestFocus();
            }
            else{
                Conexion_Concesionario admin=new Conexion_Concesionario(this,"concesionario.bd",null,1);
                SQLiteDatabase db=admin.getWritableDatabase();
                ContentValues dato=new ContentValues();
                dato.put("Identificacion",identificacion);
                dato.put("nombre",nombre);
                dato.put("usuario",usuario);
                dato.put("clave",clave1);
                if (sw == 0)
                    resp=db.insert("TblCliente",null,dato);
                else{
                    sw=0;
                    resp=db.update("TblCliente",dato,"identificacion='" + identificacion + "'",null);
                }
                if (resp > 0){
                    Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show();
                    limpiar_campos();
                }
                else{
                    Toast.makeText(this, "Error guardando registro", Toast.LENGTH_SHORT).show();
                }
                db.close();
            }
        }
    }

    public void Consultar(View view){
        Consultar_Cliente();
    }

    public void Consultar_Cliente(){

        identificacion=jetidentificacion.getText().toString();
        if (identificacion.isEmpty()){
            Toast.makeText(this, "Identificacion requerida", Toast.LENGTH_SHORT).show();
            jetidentificacion.requestFocus();
        }
        else {
            Conexion_Concesionario admin = new Conexion_Concesionario(this, "concesionario.bd", null, 1);
            SQLiteDatabase db=admin.getReadableDatabase();
            Cursor fila=db.rawQuery("select * from TblCliente where identificacion='" + identificacion + "'",null);
            if (fila.moveToNext()){
                sw=1;
                jetnombre.setText(fila.getString(1));
                jetemail.setText(fila.getString(2));
                jetpassword1.setText(fila.getString(3));
                Toast.makeText(this, "Registrado encontrado", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Registro no existe", Toast.LENGTH_SHORT).show();
            }
            db.close();
        }
    }
    public void Anular(View view){
        Consultar_Cliente();
        if (sw == 1){
            Conexion_Concesionario admin=new Conexion_Concesionario(this,"concesionario.bd",null,1);
            SQLiteDatabase db=admin.getWritableDatabase();
            ContentValues dato=new ContentValues();
            dato.put("identificacion",identificacion);
            dato.put("activo","no");
            resp=db.update("TblCliente",dato,"identificacion='"+identificacion+"'");
            if (resp>0){
                Toast.makeText(this,"Registro eliminado",Toast.LENGTH_LONG).show();
                limpiar_campos();
            }else
            {
                Toast.makeText(this,"Error eliminando registro",Toast.LENGTH_LONG).show();
            }

        }
    }

    public void Cancelar(View view){
        limpiar_campos();
    }

    public void Regresar(View view){
        Intent intmain=new Intent(this,MainActivity.class);
        startActivity(intmain);
    }
}

