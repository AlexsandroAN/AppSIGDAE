package dae.ce.appsigdae.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 39091 on 02/08/2017.
 */

public class CriaBanco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco.db";
    private static final int VERSAO = 1;

    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE IF NOT EXISTS OBRA (");
        query.append(" ID INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append(" CODIGO TEXT(30) NOT NULL,");
        query.append(" DESCRICAO TEXT(500), ");
        query.append(" MUNICIPIO TEXT(50), ");
        query.append(" STATUS TEXT(50), ");
        query.append(" CONTRATANTE TEXT(50), ");
        query.append(" VALOR_ATUAL TEXT(12), ");
        query.append(" TOTAL_EXECUTADO TEXT(12), ");
        query.append(" SALDO_A_MEDIR TEXT(12), ");
        query.append(" CONTRATADA TEXT(50), ");
        query.append(" MATRICULA_FISCAL TEXT(12))");

        db.execSQL(query.toString());
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS OBRA");
        onCreate(db);
    }

    public static String getNomeBanco() {
        return NOME_BANCO;
    }

    public static int getVERSAO() {
        return VERSAO;
    }
}