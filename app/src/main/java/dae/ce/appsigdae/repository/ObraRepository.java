package dae.ce.appsigdae.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import dae.ce.appsigdae.entity.Obra;
import dae.ce.appsigdae.util.Constantes;

/**
 * Created by 39091 on 02/08/2017.
 */

public class ObraRepository extends SQLiteOpenHelper {

    public ObraRepository(Context context) {
        super(context, Constantes.NOME_BD, null, Constantes.VERSAO_BD);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE IF NOT EXISTS OBRA (");
        query.append(" ID INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append(" CODIGO TEXT(30) NOT NULL,");
        query.append(" DESCRICAO TEXT(500))");
        db.execSQL(query.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void inserteObras(Obra obra) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = getContentValuesObra(obra);

        db.insert("OBRA", null, contentValues);
    }

  /*  public void atualizarObras(Obra obra) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = getContentValuesPessoas(obra);

        db.update("OBRA", contentValues, "ID = ?", new String[]{String.valueOf(obra.getId())});
    }*/

    @NonNull
    private ContentValues getContentValuesObra(Obra obra) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("CODIGO", obra.getCodigoObra());
        contentValues.put("DESCRICAO", obra.getDescricaoObra());
        return contentValues;
    }


    /*public List<VoObras> listarObras() {
        List<VoObras> lista = new ArrayList<VoObras>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("OBRA", null, null, null, null, null, "CODIGO");

        while (cursor.moveToNext()) {
            VoObras obra = new VoObras();
            setObraFromCursor(cursor, obra);

            lista.add(obra);
        }

        return lista;
    }*/


  /*  public VoObras consultarObraPorId(int idObra) {
        VoObras obra = new VoObras();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("OBRA", null, "ID = ?", new String[]{String.valueOf(idObra)}, null, null, "CODIGO");

        if (cursor.moveToNext()) {
            setObraFromCursor(cursor, obra);
        }

        return obra;
    }*/
/*

    public void removerObraPorId(int idObra) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("OBRA", "ID = ?", new String[]{String.valueOf(idObra)});
    }
*/


 /*   private void setObraFromCursor(Cursor cursor, VoObras obra) {
        obra.setId(cursor.getInt(cursor.getColumnIndex("ID")));
        obra.setCodigoObra(cursor.getString(cursor.getColumnIndex("CODIGO")));
        obra.setDescricaoObra(cursor.getString(cursor.getColumnIndex("DESCRICAO")));
    }
*/


}
