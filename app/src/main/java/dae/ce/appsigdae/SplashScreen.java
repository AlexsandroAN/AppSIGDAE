package dae.ce.appsigdae;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Chamar a Tela de Login
        Intent i = new Intent(SplashScreen.this, Login.class);
        startActivity(i);
        finish();
    }
}
