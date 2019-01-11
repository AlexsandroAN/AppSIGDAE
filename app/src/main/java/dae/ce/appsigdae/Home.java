package dae.ce.appsigdae;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
//import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
//import com.mikepenz.fontawesome_typeface_library.FontAwesome;
//import com.mikepenz.google_material_typeface_library.GoogleMaterial;
/*import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.holder.BadgeStyle;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;*/

import org.json.JSONArray;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import dae.ce.appsigdae.async.AsyncLoginHttpClient;
import dae.ce.appsigdae.entity.Obra;
import dae.ce.appsigdae.repository.BancoController;
import dae.ce.appsigdae.repository.CriaBanco;
import cz.msebera.android.httpclient.Header;

public class Home extends AppCompatActivity {

    //private Drawer drawer;
    private ViewPager viewPager;
    private static final long ID_ND_FOOTER = 500;
    private List<Obra> listaObra = new ArrayList<Obra>();
    private ListView listaObras;
    ArrayAdapter adapter;
    private SQLiteDatabase db;
    private CriaBanco banco;

    private String nome, matricula, email, logado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // <editor-fold defaultstate="collapsed" desc=">>> Configurações do Toolbar">
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Minhas Obras");
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc=">>> Buscando as Preferências do App">
        SharedPreferences pref = getSharedPreferences("pref", 0);
        nome = pref.getString("nome", null);
        matricula = pref.getString("usuario", null);
        email = pref.getString("email", null);
        logado = pref.getString("logado", null);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc=">>> Adicionando Parametros para busca no WebService">
        RequestParams params = new RequestParams();
        params.put("usuario", matricula);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc=">>> Método para buscar obras do Fiscal no WebService">

        if (logado.equals("sim")) {
            AsyncLoginHttpClient.post("voDadosObraRest/voDadosObrasFiscal?", params, new JsonHttpResponseHandler() {
                // Chamado quando o status HTTP de resposta é "200 OK"
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                    Type type = new TypeToken<List<Obra>>() {
                    }.getType();
                    listaObra = new Gson().fromJson(response.toString(), type);

                    listaObras = (ListView) findViewById(R.id.listaObras);
                    adapter = new ArrayAdapter(Home.this, android.R.layout.simple_list_item_1);
                    setArrayAdapterObras();

                    salvarObra();

                    listaObras.setOnItemClickListener(clickListenerObras);

                    Integer qtdObra = listaObra.size();
                    Toast toast = Toast.makeText(Home.this, "Encontrado " + qtdObra.toString() + " Obra(s)", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                }
            });
        } else {
            BancoController crud = new BancoController(getBaseContext());
            listaObra = crud.listarObras(matricula);

            listaObras = (ListView) findViewById(R.id.listaObras);
            adapter = new ArrayAdapter(Home.this, android.R.layout.simple_list_item_1);
            setArrayAdapterObras();

            listaObras.setOnItemClickListener(clickListenerObras);
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc=">>> NavigationView">
       /* final PrimaryDrawerItem itemPerfil = new PrimaryDrawerItem()
                .withName("Perfil")
                .withIcon(GoogleMaterial.Icon.gmd_person);
        final PrimaryDrawerItem itemProdutos = new PrimaryDrawerItem()
                .withName("Obras")
                .withBadge("43")
                .withIcon(FontAwesome.Icon.faw_th_list)
                .withBadgeStyle(new BadgeStyle()
                        .withTextColor(Color.WHITE)
                        .withColorRes(R.color.md_orange_700));
        final PrimaryDrawerItem itemCompras = new PrimaryDrawerItem()
                .withName("Medições")
                .withBadge("2")
                .withIcon(GoogleMaterial.Icon.gmd_shop_two)
                .withBadgeStyle(new BadgeStyle()
                        .withTextColor(Color.WHITE)
                        .withColorRes(R.color.md_orange_700));
        // Create the AccountHeader / Criando o Cabeçalho
        AccountHeader drawerHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header1)
                .addProfiles(
                        new ProfileDrawerItem().withName(nome + " / " + matricula).withEmail(email)
                ).build();

        //create the drawer and remember the `Drawer` result object
        drawer = new DrawerBuilder()
                .withAccountHeader(drawerHeader)
                .withActivity(this)
                .withToolbar(toolbar)
                .addDrawerItems(
                        new SecondaryDrawerItem().withName("Conta do Usuário"),
                        itemPerfil,
                        new SecondaryDrawerItem().withName("Ações do Sistema"),
                        itemProdutos,
                        itemCompras
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        configuraItensDrawer(position, drawerItem);
                        return true;
                    }
                })
                .build();
        drawer.addStickyFooterItem(new PrimaryDrawerItem().withName("Sobre o App")
                .withIcon(GoogleMaterial.Icon.gmd_info)
                .withIdentifier(ID_ND_FOOTER));*/
        // </editor-fold>
    }

    // <editor-fold defaultstate="collapsed" desc=">>> Métodos">

    public void salvarObra() {
        BancoController crud = new BancoController(getBaseContext());
        for (Obra obra : listaObra) {
            crud.inserirObra(obra);
        }
    }

    public void deleteObra() {
        BancoController crud = new BancoController(getBaseContext());
        crud.deleteObras(matricula);
    }

    private void setArrayAdapterObras() {
        List<String> valores = new ArrayList<String>();
        int qtdObra = 1;
        for (Obra obra : listaObra) {
            valores.add(obra.getCodigoObra());
            qtdObra++;
        }
        adapter.clear();
        adapter.addAll(valores);
        listaObras.setAdapter(adapter);
    }

   /* private void configuraItensDrawer(int position, IDrawerItem drawerItem) {
        viewPager.setCurrentItem(position);
        switch ((int) drawerItem.getIdentifier()) {
            case (int) ID_ND_FOOTER:
                try {
                    PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), 0);
                    String versao = info.versionName;
                    Toast.makeText(this, "Versão" + versao, Toast.LENGTH_SHORT).show();
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                break;
        }
        drawer.closeDrawer();
    }*/

    private AdapterView.OnItemClickListener clickListenerObras = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            BancoController crud = new BancoController(getBaseContext());
            Obra obra = crud.consultarObraPorId(listaObra.get(position).getId());

        /*    Intent i = new Intent(Home.this, VoDadosObra.class);
            i.putExtra("obra", listaObra.get(position));
            startActivity(i);
            finish();*/

        }
    };
    // </editor-fold>

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        SharedPreferences.Editor editor;

        if (item.getItemId() == R.id.action_search) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.sair:
                editor = getSharedPreferences("pref", Context.MODE_PRIVATE).edit();
                editor.remove("usuario");
                editor.remove("senha");
                editor.clear();
                finish();
                break;
            case R.id.logout:
                deleteObra();
                editor = getSharedPreferences("pref", Context.MODE_PRIVATE).edit();
                editor.remove("usuario");
                editor.remove("senha");
                editor.commit();

                // Chamar a Tela de Login
                intent = new Intent(this, Login.class);
                startActivity(intent);
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
