package com.example.anthony.clinicplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    private TextView texto;
    private EditText Password;
    private ProgressBar pb;
    private String correo;
    private EditText rPassword;
    private Button enviar;
    private EditText username;
    private boolean Coinciden(String password1, String password2){

        if (password1.equals(password2))
        {
            return true;
        }
        else
            return false;
    }
    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence.length()>1) {
                //Ultimo caracter
                // texto2.setText(charSequence.subSequence(charSequence.length() - 1, charSequence.length()));

            }
        }
        @Override
        public void afterTextChanged(Editable editable) {
            if(editable.length()==0)
            {
                texto.setText("Muy corta");
                pb.setProgress(0);
            }
            else if(editable.length()<6)
            {
                texto.setText("Muy Debil");
                pb.setProgress(25);
            }

            else if(editable.length()<10)
            {
                texto.setText("Debil");
                pb.setProgress(50);
            }

            else if(editable.length()<15)
            {
                texto.setText("Buena");
                pb.setProgress(75);
            }
            else
            {
                texto.setText("Excelente");
                pb.setProgress(100);
            }

            if(editable.length()>=20)
            {
                texto.setText("Muy larga");
                pb.setProgress(0);
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Password = (EditText) (findViewById(R.id.contraseña));
        pb= (ProgressBar)(findViewById(R.id.robustez));
        texto= (TextView)(findViewById(R.id.progress));
        Password.addTextChangedListener(watcher);
        enviar = (Button)(findViewById(R.id.enviar));
        rPassword= (EditText)(findViewById(R.id.contraseña2));
        username=(EditText)(findViewById(R.id.usuario));
        final MapperHelper helper;
        helper = new MapperHelper(getApplicationContext());
        //Tomo el correo del activity anterior
        correo=(getIntent().getExtras().getString("correo"));
        //ENVIAR
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(pb.getProgress()<25)
                    Password.setError("Contraseña debil");
                else {

                    if (Coinciden(Password.getText().toString(), rPassword.getText().toString()) == false) {
                        rPassword.setError("No coiniciden las contraseñas");
                    } else {

                        Usuario objUsuario = new Usuario();
                        objUsuario.setCorreo(correo);
                        objUsuario.setContraseña(Password.getText().toString());
                        objUsuario.setUserid("1");
                        objUsuario.setTipo("User");
                        objUsuario.setUsername(username.getText().toString());
                        helper.mapper.save(objUsuario);
                        Intent signInIntent = new Intent(RegisterActivity.this, WelcomeActivity.class);
                        signInIntent.putExtra("correo",correo);
                        signInIntent.putExtra("username",username.getText().toString());
                        startActivity(signInIntent);
                    }
                }
            }
        });

    }
}
