package dae.ce.appsigdae.service;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

//import org.apache.http.Header;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import cz.msebera.android.httpclient.Header;
import dae.ce.appsigdae.Home;
import dae.ce.appsigdae.async.AsyncLoginHttpClient;
import dae.ce.appsigdae.async.HttpService;
import dae.ce.appsigdae.entity.Usuario;
import dae.ce.appsigdae.util.Util;
import dae.ce.appsigdae.async.DownloadDados;
import dae.ce.appsigdae.validation.LoginValidation;

/**
 * Created by 39091 on 04/07/2016.
 */
public class LoginService {

    private Usuario usuario = new Usuario();

    public void validarCamposLogin(final LoginValidation validation) {
        final Activity activity = validation.getActivity();

        boolean resultado = true;
        if (validation.getUsuario() == null || "".equals(validation.getUsuario())) {
            validation.getEdtUsuario().setError("Campo obrigatorio");
            resultado = false;
        }
        if (validation.getSenha() == null || "".equals(validation.getSenha())) {
            validation.getEdtSenha().setError("Campo obrigatorio");
            resultado = false;
        }
        if (resultado) {
            RequestParams params = new RequestParams();
            params.put("usuario", validation.getUsuario());
            params.put("senha", validation.getSenha());

            try {
                String usuarioRetorno = new HttpService(validation.getUsuario(), validation.getSenha()).execute().get();

                Type type = new TypeToken<Usuario>() {
                }.getType();

                usuario = new Gson().fromJson(usuarioRetorno, type);

                if (usuarioRetorno != null) {
                    SharedPreferences.Editor editor = activity.getSharedPreferences("pref", Context.MODE_PRIVATE).edit();
                    editor.putString("usuario", validation.getUsuario());
                    editor.putString("senha", validation.getSenha());
                    editor.putString("nome", usuario.getNomeReferencia());
                    editor.putString("nomeCompleto", usuario.getNomeCompleto());
                    editor.putString("email", usuario.getEmail());
                    editor.putString("logado", "sim");
                    editor.commit();

                    // Chamar a Tela de Home
                    Intent intent = new Intent(activity, Home.class);
                    activity.startActivity(intent);
                    activity.finish();
                } else {
                    Util.showMsgSimpleToast(activity, "Login/Senha Invalidos!");
                }
                // resposta.setText(retorno.toString());
            } catch (InterruptedException e) {
                Util.showMsgSimpleToast(activity, "Erro ao conectar ao WebService");
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        Util.showMsgSimpleToast(activity, "Não foi possível conectar ao WebService");
    }
}
