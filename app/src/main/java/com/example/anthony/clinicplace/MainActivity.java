package com.example.anthony.clinicplace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by Anthony on 03/11/2017.
 */

public class MainActivity extends Activity {
    private ImageView logos;
    private EditText nombreUsuario;
    private EditText contraseñaUsuario;
    private Button ingresar;
    private TextView registrar;
    private String url = "https://s3.amazonaws.com/clinicplace/logo.png";
    private void CargarLogo(){
        Glide.with(getBaseContext())
                .load(url)
                .override(150,150)
                .centerCrop()
                .error(R.drawable.ic_launcher_background)
                .into(logos);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        logos= (ImageView)findViewById(R.id.logo);
        ingresar=(Button)findViewById(R.id.btnLogIn);
        nombreUsuario=(EditText)findViewById(R.id.user);
        contraseñaUsuario=(EditText)findViewById(R.id.password);
        registrar=(TextView)(findViewById(R.id.signUp));
        CargarLogo();
        final MapperHelper helper;
        helper = new MapperHelper(getApplicationContext());
        ingresar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (nombreUsuario.getText().toString().isEmpty())
                {
                    nombreUsuario.setError("Ingrese nombre de usuario");
                }
                else
                {
                    Usuario objUsuario = helper.mapper.load(Usuario.class,"1",nombreUsuario.getText().toString());

                    if (objUsuario==null)
                        nombreUsuario.setError("Usuario no encontrado");
                    else    {
                    if (objUsuario.getContraseña().equals(contraseñaUsuario.getText().toString()))
                    {

                        if (objUsuario.getTipo().equals("Admin"))
                        { Intent editIntent = new Intent(MainActivity.this, AdminProfileActivity.class);
                        startActivity(editIntent);
                        }
                        if (objUsuario.getTipo().equals("User"))
                        {
                            Intent editIntent  = new Intent(MainActivity.this, UserProfileActivity.class);
                            startActivity(editIntent);
                        }
                    }
                    else    {
                        contraseñaUsuario.setError("Contraseña incorrecta");
                    }
                    }
                }

            }
        });
        registrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent editIntent = new Intent(MainActivity.this, VerifyActivity.class);
                startActivity(editIntent);
            }
        });
    }

}
