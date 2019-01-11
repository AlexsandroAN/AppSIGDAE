package dae.ce.appsigdae.async;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import dae.ce.appsigdae.util.Util;
import dae.ce.appsigdae.validation.LoginValidation;

public class HttpService extends AsyncTask<Void, Void, String> {

    private String usuario;
    private String senha;
    private AlertDialog alertDialog;
    private Context context;



    public HttpService(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    @Override
    protected void onPreExecute(){
        System.out.println("teste...");
    }

    @Override
    protected String doInBackground(Void... voids) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL("http://192.168.0.148:8080/WebServiceJSON/webresources/dae/usuario/get/" + usuario + "/" + senha);

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String linha;
            StringBuffer buffer = new StringBuffer();

            while((linha = reader.readLine()) != null) {
                    buffer.append(linha);
                    buffer.append("\n");

            }
            if(linha == null){
                Toast.makeText(context, "Usuario ou Senha invalida", Toast.LENGTH_SHORT).show();
            }

            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            if (urlConnection != null) {
               //Log.i(TAG, e.getMessage());
                //System.out.print("Erro: "+e.getMessage());
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String dados) {
        // Faça alguma coisa com os dados
      //alertDialog("Downloaded " + result + " bytes");
    }
}
