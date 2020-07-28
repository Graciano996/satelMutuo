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
                " latitude TEXT NOT NULL, longitude TEXT NOT NULL, tipoPoste TEXT NOT NULL ,normal TEXT NOT NULL," +
                " ferragemExposta TEXT NOT NULL, fletido TEXT NOT NULL, danificado TEXT NOT NULL, " +
                " abalrroado TEXT NOT NULL, trincado TEXT NOT NULL, observacaoFisicas TEXT NOT NULL," +
                " ip TEXT NOT NULL,ipEstrutura TEXT NOT NULL,quantidadeLampada TEXT NOT NULL," +
                " ipAtivacao TEXT NOT NULL,vinteEQuatro TEXT NOT NULL,quantidade24H TEXT NOT NULL," +
                " tipoPot TEXT NOT NULL, potReator TEXT NOT NULL, " +
                " ip2 TEXT NOT NULL,ipEstrutura2 TEXT NOT NULL,quantidadeLampada2 TEXT NOT NULL," +
                " ipAtivacao2 TEXT NOT NULL,vinteEQuatro2 TEXT NOT NULL,quantidade24H2 TEXT NOT NULL," +
                " tipoPot2 TEXT NOT NULL, potReator2 TEXT NOT NULL, " +
                " ip3 TEXT NOT NULL,ipEstrutura3 TEXT NOT NULL,quantidadeLampada3 TEXT NOT NULL," +
                " ipAtivacao3 TEXT NOT NULL,vinteEQuatro3 TEXT NOT NULL,quantidade24H3 TEXT NOT NULL," +
                " tipoPot3 TEXT NOT NULL, potReator3 TEXT NOT NULL, observacaoIP," +
                " ativos TEXT NOT NULL, chkTrafoTrifasico TEXT NOT NULL, chkTrafoMono TEXT NOT  NULL,"+
                " trafoTrifasico TEXT NOT NULL, trafoMono TEXT NOT NULL,chFusivel TEXT NOT NULL,"+
                " chFaca TEXT NOT NULL,religador TEXT NOT NULL, medicao TEXT NOT NULL, banco TEXT NOT NULL," +
                " chFusivelReligador TEXT NOT NULL, ramalSubt TEXT NOT NULL ,observacaoAtivos TEXT NOT NULL,"+
                " codigo TEXT NOT NULL, reservaTec TEXT NOT NULL, " +
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

    }
}
