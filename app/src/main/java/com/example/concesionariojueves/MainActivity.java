package com.example.concesionariojueves;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etusuario,etclave;
    Button btningresar,btnregistrarse,btncancelr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        etclave=findViewById(R.id.etPassword);
        etusuario=findViewById(R.id.etusuario);
        btncancelr=findViewById(R.id.btncancelar);
        btningresar=findViewById(R.id.btnIngresar);
        btnregistrarse=findViewById(R.id.btnRegistra);


    }
    public void limpiar_campos(){
        etclave.setText("");
        etusuario.setText("");
        etusuario.requestFocus();
    }
    public void Ingresar(View view){
        String usuario,clave;
        usuario=etusuario.getText().toString();
        clave=etclave.getText().toString();
        if(usuario.isEmpty() || clave.isEmpty()){
            Toast.makeText(this, "Usuario y clave requeridos", Toast.LENGTH_SHORT).show();
            etusuario.requestFocus();

        }
        else {
            Conexion_Concesionario admin = new Conexion_Concesionario(this, "concesionario.bd", null, 1);
            SQLiteDatabase db=admin.getReadableDatabase();
            Cursor fila=db.rawQuery("select identificacion from TbLCliente where usuario='"+usuario+"'and clave='"+clave+"'",null);
            if (fila.moveToNext()){
                Intent intVehiculos=new Intent(this,VehiculosActivyty.class);
                startActivity(intVehiculos);
            }else
                Toast.makeText(this, "Usuario o Clave Incorrectos", Toast.LENGTH_SHORT).show();
            etusuario.requestFocus();
        }

    }
    public  void  Registrarse(View view){
        Intent intregistrarse=new Intent(this,Activity2.class);
        startActivity(intregistrarse);
    }
    public void CancelarIng(View view){

        limpiar_campos();
    }
}