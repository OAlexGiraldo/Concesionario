package com.example.concesionariojueves;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

        EditText etidentificacion,etnombre,etemail,etpassword1,etpassword2;
        TextView tvactivo;
        Button btnregresa,btnguardar,btncancelar,btnanular,btnconsultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        getSupportActionBar().hide();

        etidentificacion=findViewById(R.id.edtnidentificacion);
        etnombre=findViewById(R.id.etnombre);
        etemail=findViewById(R.id.etEmail);
        etpassword1=findViewById(R.id.etpassword);
        etpassword2=findViewById(R.id.etPassword2);
        tvactivo=findViewById(R.id.tvactivo);
        btnanular=findViewById(R.id.btnAnular);
        btncancelar=findViewById(R.id.btncancelar);
        btnguardar=findViewById(R.id.btnguardar);
        btnconsultar=findViewById(R.id.btnConsultar);
        btnregresa=findViewById(R.id.btnRegresar);

    }
    public  void Guardar(View view){
        String identificacion,nombre,usuario,password1,password2;
        identificacion=etidentificacion.getText().toString();
        nombre=etnombre.getText().toString();
        usuario=etemail.getText().toString();
        password1=etpassword1.getText().toString();
        password2=etpassword2.getText().toString();
        if (identificacion.isEmpty() || nombre.isEmpty() || usuario.isEmpty() || password1.isEmpty() || password2.isEmpty()){
            Toast.makeText(this,"Todos los Datos son obligatorios",Toast.LENGTH_LONG).show();
            etidentificacion.requestFocus();
        }else{
            if (password1.equals(password2)){
                Toast.makeText(this,"Las contraseña no coiciden",Toast.LENGTH_LONG).show();
                etpassword2.requestFocus();
            }else{
                Conexion_Concesionario admin=new Conexion_Concesionario(this,"concesionario.bd",null,1);
                
            }
        }
    }
}