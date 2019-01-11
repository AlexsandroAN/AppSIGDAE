package dae.ce.appsigdae;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import dae.ce.appsigdae.service.LoginService;
import dae.ce.appsigdae.validation.LoginValidation;
//import dae.ce.appsigdae.validation.LoginValidation;

public class Login extends AppCompatActivity {

    private EditText edtUsuario, edtSenha;
    private Button btnLogar;
    private SharedPreferences sharedPreferences;
    private LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Intanciando a class LoginService
        loginService = new LoginService();

        // Recuperando a prefêrencia
        sharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        String usuario = sharedPreferences.getString("usuario", null);
        String senha = sharedPreferences.getString("senha", null);

        // Se preferencia estiver preenchida pula tela de Login
        if (usuario != null && senha != null) {
            // Intenção
            Intent intent = new Intent(Login.this, Home.class);
            startActivity(intent);
            finish();
        }

        edtUsuario = (EditText) findViewById(R.id.edtUsuario);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnLogar = (Button) findViewById(R.id.btnLogar);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = edtUsuario.getText().toString();
                String senha = edtSenha.getText().toString();

                LoginValidation validation = new LoginValidation();
                validation.setActivity(Login.this);
                validation.setEdtUsuario(edtUsuario);
                validation.setEdtSenha(edtSenha);
                validation.setUsuario(usuario);
                validation.setSenha(senha);

                loginService.validarCamposLogin(validation);
            }
        });
    }
}
