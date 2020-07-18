package com.example.satelprojetos.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static int VERSION = 1;
    public static String DB_NAME = "DB_FORMULARIO";
    public static String TABLE_FORMULARIO = "formulario";


    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_FORMULARIO + " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " data TEXT NOT NULL, endereco TEXT NOT NULL, nome TEXT NOT NULL, municipio TEXT NOT NULL," +
                " alturaCarga TEXT NOT NULL, " +
                " latitude TEXT NOT NULL, longitude TEXT NOT NULL, tipoPoste TEXT NOT NULL ,normal TEXT NOT NULL, ferragemExposta TEXT NOT NULL, fletido TEXT NOT NULL, danificado TEXT NOT NULL, " +
                " abalrroado TEXT NOT NULL, trincado TEXT NOT NULL, ramalSubt TEXT NOT NULL, observacaoFisicas TEXT NOT NULL," +
                " trafo TEXT NOT NULL, religador TEXT NOT NULL, medicao TEXT NOT NULL, chFusivel TEXT NOT NULL," +
                " chFaca TEXT NOT NULL, observacaoAtivos TEXT NOT NULL, tipoPot TEXT NOT NULL, potReator TEXT NOT NULL, " +
                " vinteEQuatro TEXT NOT NULL, codigo TEXT NOT NULL, reservaTec TEXT NOT NULL, " +
                " comSemMedicao TEXT NOT NULL, descidaCabos TEXT NOT NULL, placaIdent TEXT NOT NULL, descricaoIrregularidade TEXT NOT NULL," +
                " dimensaoVegetacao TEXT NOT NULL, distanciaBaixa TEXT NOT NULL, distanciaMedia TEXT NOT NULL, observacaoVegetacao TEXT NOT NULL);";

        try{
            db.execSQL(sql);
            Log.i("INFO DB", "Sucesso ao criar a tabela");
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao criar tabela" + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        /* municipio TEXT NOT NULL, alturaCarga TEXT NOT NULL," +
        " latitude TEXT NOT NULL, longitude TEXT NOT NULL, ferragemExposta TEXT NOT NULL, danificado TEXT NOT NULL," +
                " abalrroado TEXT NOT NULL, trincado TEXT NOT NULL, ramalSubt TEXT NOT NULL, observacaoFisicas TEXT NOT NULL," +
                " trafo TEXT NOT NULL, religador TEXT NOT NULL, medicao TEXT NOT NULL, chFusivel TEXT NOT NULL," +
                " chFaca TEXT NOT NULL, observacaoAtivos TEXT NOT NULL, tipoPot TEXT NOT NULL, potReator TEXT NOT NULL," +
                " vinteEQuatro TEXT NOT NULL, nome TEXT NOT NULL, codigo TEXT NOT NULL, reservaTec TEXT NOT NULL," +
                " comSemMediacao TEXT NOT NULL, descidaCabos TEXT NOT NULL, placaIdent TEXT NOT NULL, descricaoIrregularidade TEXT NOT NULL," +
                " dimensaoVegetacao TEXT NOT NULL, distanciaBaixa TEXT NOT NULL, distanciaMedia TEXT NOT NULL, observacaoVegetacao TEXT NOT NULL);*/
    }
}
