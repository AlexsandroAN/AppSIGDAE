package dae.ce.appsigdae.async;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadDados extends AsyncTask<Void, Void, String> {

    private ProgressDialog load;
    private Context context;

    @Override
    protected String doInBackground(Void... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        try {
            //URL url = new URL("http://pokeapi.co/api/v2/pokemon/1/");
            URL url = new URL("http://192.168.2.27:8080/WebServiceJSON/webresources/dae/usuario/get");

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
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            if (urlConnection != null) {
                System.out.print("Erro: "+e.getMessage());
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
        System.out.print("teste: "+dados);
        // Faça alguma coisa com os dados
    }

    //Exibe pop-up indicando que está sendo feito o download do JSON
   /* @Override
    protected void onPreExecute() {
        super.onPreExecute();
        load = ProgressDialog.show(context, "Aguarde",
                "Fazendo download do JSON");
    }*/
}
