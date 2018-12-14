package dae.ce.appsigdae.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import dae.ce.appsigdae.entity.Obra;

/**
 * Created by 39091 on 02/08/2017.
 */

public class BancoController {

    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context) {
        banco = new CriaBanco(context);
    }

    public void inserirObra(Obra obra) {
        db = banco.getWritableDatabase();
        ContentValues contentValues = getContentValuesObra(obra);

        db.insert("OBRA", null, contentValues);

        db.close();
    }

    public List<Obra> listarObras(String matriculaFiscal) {
        List<Obra> lista = new ArrayList<Obra>();
        db = banco.getWritableDatabase();
        Cursor cursor = db.query("OBRA", null, null, null, null, null, "CODIGO");

        while (cursor.moveToNext()) {
            Obra obra = new Obra();
            setObraFromCursor(cursor, obra);

            lista.add(obra);
        }
        return lista;
    }

    public Obra consultarObraPorId(int idObra) {
        Obra obra = new Obra();
        db = banco.getReadableDatabase();

        Cursor cursor = db.query("OBRA", null, "ID = ?", new String[]{String.valueOf(idObra)}, null, null, "ID");

        if (cursor.moveToNext()) {
            setObraFromCursor(cursor, obra);
        }
        return obra;
    }

    public void deleteObras(String matricula) {
        db = banco.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("MATRICULA", matricula);

        db.delete("OBRA", "MATRICULA_FISCAL = ?", new String[]{matricula});
    }

    @NonNull
    private ContentValues getContentValuesObra(Obra obra) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("CODIGO", obra.getCodigoObra());
        contentValues.put("DESCRICAO", obra.getDescricaoObra());
        contentValues.put("MUNICIPIO", obra.getMunicipio());
        contentValues.put("STATUS", obra.getStatusObra());
        contentValues.put("CONTRATANTE", obra.getContratanteNomeFantasia());
        contentValues.put("VALOR_ATUAL", obra.getValorAtual().toString());
        contentValues.put("TOTAL_EXECUTADO", obra.getTotalExecutado().toString());
        contentValues.put("SALDO_A_MEDIR", obra.getSaldoAMedir().toString());
        contentValues.put("CONTRATADA", obra.getContratadaNomeFantasia());
        contentValues.put("MATRICULA_FISCAL", obra.getMatriculaFiscal());
        return contentValues;
    }

    private void setObraFromCursor(Cursor cursor, Obra obra) {
        obra.setId(cursor.getInt(cursor.getColumnIndex("ID")));
        obra.setCodigoObra(cursor.getString(cursor.getColumnIndex("CODIGO")));
        obra.setDescricaoObra(cursor.getString(cursor.getColumnIndex("DESCRICAO")));
        obra.setMunicipio(cursor.getString(cursor.getColumnIndex("MUNICIPIO")));
        obra.setStatusObra(cursor.getString(cursor.getColumnIndex("STATUS")));
        obra.setContratanteNomeFantasia(cursor.getString(cursor.getColumnIndex("CONTRATANTE")));
        obra.setValorAtual(cursor.getDouble(cursor.getColumnIndex("VALOR_ATUAL")));
        obra.setTotalExecutado(cursor.getDouble(cursor.getColumnIndex("TOTAL_EXECUTADO")));
        obra.setSaldoAMedir(cursor.getDouble(cursor.getColumnIndex("SALDO_A_MEDIR")));
        obra.setContratadaNomeFantasia(cursor.getString(cursor.getColumnIndex("CONTRATADA")));
        obra.setMatriculaFiscal(cursor.getString(cursor.getColumnIndex("MATRICULA_FISCAL")));
    }
}
