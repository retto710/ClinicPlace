package com.example.anthony.clinicplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import java.util.regex.Pattern;

public class VerifyActivity extends AppCompatActivity {
    private ImageView logos;
    private EditText correoUsuario;
    private Button verificar;
    private String url = "https://s3.amazonaws.com/clinicplace/logo.png";

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
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
        setContentView(R.layout.activity_verify);
        logos=(ImageView)findViewById(R.id.logo);
        CargarLogo();
        final MapperHelper helper;
        helper = new MapperHelper(getApplicationContext());
        correoUsuario = (EditText)findViewById(R.id.correonuevo);
        Button SignIn= (Button) findViewById(R.id.enviar);
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validarEmail(correoUsuario.getText().toString())){
                    correoUsuario.setError("Email no válido");
                }
                else {
                    Usuario objUsuario = helper.mapper.load(Usuario.class,"1",correoUsuario.getText().toString());

                    if (objUsuario==null)
                    {
                        Intent editIntent = new Intent(VerifyActivity.this, RegisterActivity.class);
                        editIntent.putExtra("correo",correoUsuario.getText().toString());
                        startActivity(editIntent);

                    }
                    else
                    {
                        correoUsuario.setError("Este correo ya esta vinculado a una cuenta");
                    }


                }

            }
        });
    }
}
