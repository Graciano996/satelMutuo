package com.example.satelprojetos.helper;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.satelprojetos.model.Formulario;

import java.util.ArrayList;
import java.util.List;

public class FormularioDAO implements IFormularioDAO {

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public FormularioDAO(Context context){
        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();

    }
    @Override
    public boolean salvar(Formulario formulario) {
        ContentValues cv = new ContentValues();
        cv.put("data", formulario.getData());
        cv.put("endereco", formulario.getEndereco());
        cv.put("nome", formulario.getNome());
        cv.put("municipio", formulario.getMunicipio());
        cv.put("alturaCarga", formulario.getAlturaCarga());
        cv.put("latitude", formulario.getLatitude());
        cv.put("longitude", formulario.getLongitude());
        cv.put("tipoPoste",formulario.getTipoPoste());
        cv.put("normal", formulario.getNormal());
        cv.put("ferragemExposta", formulario.getFerragemExposta());
        cv.put("fletido", formulario.getFletido());
        cv.put("danificado", formulario.getDanificado());
        cv.put("abalrroado", formulario.getAbalrroado());
        cv.put("trincado", formulario.getTrincado());
        cv.put("ramalSubt", formulario.getRamalSubt());
        cv.put("observacaoFisicas", formulario.getObservacaoFisicas());
        cv.put("trafo", formulario.getTrafo());
        cv.put("religador", formulario.getReligador());
        cv.put("medicao", formulario.getMedicao());
        cv.put("chFusivel", formulario.getChFusivel());
        cv.put("chFaca", formulario.getChFaca());
        cv.put("observacaoAtivos", formulario.getObservacaoAtivos());
        cv.put("tipoPot", formulario.getTipoPot());
        cv.put("potReator", formulario.getPotReator());
        cv.put("vinteEQuatro", formulario.getVinteEQuatro());
        cv.put("codigo", formulario.getCodigo());
        cv.put("reservaTec", formulario.getReservaTec());
        cv.put("comSemMedicao", formulario.getComSemMedicao());
        cv.put("descidaCabos", formulario.getDescidaCabos());
        cv.put("placaIdent", formulario.getPlacaIdent());
        cv.put("descricaoIrregularidade", formulario.getDescricaoIrregularidade());
        cv.put("dimensaoVegetacao", formulario.getDimensaoVegetacao());
        cv.put("distanciaBaixa", formulario.getDistaciaBaixa());
        cv.put("distanciaMedia", formulario.getDistanciaMedia());
        cv.put("observacaoVegetacao", formulario.getObservacaoVegetacao());

        try{
            escreve.insert(DbHelper.TABLE_FORMULARIO,null, cv);
            Log.i("INFO","Formulario salvo com sucesso!");

        }catch (Exception e){
            Log.e("INFO","Erro ao salvar formulario" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean atualizar(Formulario formulario) {
        ContentValues cv = new ContentValues();
        cv.put("data", formulario.getData());
        cv.put("endereco", formulario.getEndereco());
        cv.put("nome", formulario.getNome());
        cv.put("municipio", formulario.getMunicipio());
        cv.put("alturaCarga", formulario.getAlturaCarga());
        cv.put("latitude", formulario.getLatitude());
        cv.put("longitude", formulario.getLongitude());
        cv.put("tipoPoste",formulario.getTipoPoste());
        cv.put("normal", formulario.getNormal());
        cv.put("ferragemExposta", formulario.getFerragemExposta());
        cv.put("fletido", formulario.getFletido());
        cv.put("danificado", formulario.getDanificado());
        cv.put("abalrroado", formulario.getAbalrroado());
        cv.put("trincado", formulario.getTrincado());
        cv.put("ramalSubt", formulario.getRamalSubt());
        cv.put("observacaoFisicas", formulario.getObservacaoFisicas());
        cv.put("trafo", formulario.getTrafo());
        cv.put("religador", formulario.getReligador());
        cv.put("medicao", formulario.getMedicao());
        cv.put("chFusivel", formulario.getChFusivel());
        cv.put("chFaca", formulario.getChFaca());
        cv.put("observacaoAtivos", formulario.getObservacaoAtivos());
        cv.put("tipoPot", formulario.getTipoPot());
        cv.put("potReator", formulario.getPotReator());
        cv.put("vinteEQuatro", formulario.getVinteEQuatro());
        cv.put("codigo", formulario.getCodigo());
        cv.put("reservaTec", formulario.getReservaTec());
        cv.put("comSemMedicao", formulario.getComSemMedicao());
        cv.put("descidaCabos", formulario.getDescidaCabos());
        cv.put("placaIdent", formulario.getPlacaIdent());
        cv.put("descricaoIrregularidade", formulario.getDescricaoIrregularidade());
        cv.put("dimensaoVegetacao", formulario.getDimensaoVegetacao());
        cv.put("distanciaBaixa", formulario.getDistaciaBaixa());
        cv.put("distanciaMedia", formulario.getDistanciaMedia());
        cv.put("observacaoVegetacao", formulario.getObservacaoVegetacao());

        String[] args = {formulario.getId().toString()};

        try{
            escreve.update(DbHelper.TABLE_FORMULARIO,cv,"id=?",args);
            Log.i("INFO","Formulario atualizado com sucesso!");

        }catch (Exception e){
            Log.e("INFO","Erro ao atualizar formulario" + e.getMessage());
            return false;
        }
        return true;



    }

    @Override
    public boolean deletar(Formulario formulario) {

        try{
            String[] args = {formulario.getId().toString()};
            escreve.delete(DbHelper.TABLE_FORMULARIO,"id=?",args);
            Log.i("INFO","Formulario atualizado com sucesso!");

        }catch (Exception e){
            Log.e("INFO","Erro ao atualizar formulario" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Formulario> listar() {
        List<Formulario> formularios = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.TABLE_FORMULARIO + " ;";
        Cursor c = le.rawQuery(sql,null);

        while (c.moveToNext()){
            Long id = c.getLong(c.getColumnIndex("id"));
            String nome = c.getString(c.getColumnIndex("nome"));
            String endereco = c.getString((c.getColumnIndex("endereco")));
            String data = c.getString(c.getColumnIndex("data"));
            String municipio = c.getString(c.getColumnIndex("municipio"));
            String alturaCarga = c.getString(c.getColumnIndex("alturaCarga"));
            String latitude = c.getString(c.getColumnIndex("latitude"));
            String longitude = c.getString(c.getColumnIndex("longitude"));
            String tipoPoste = c.getString(c.getColumnIndex("tipoPoste"));
            String normal = c.getString(c.getColumnIndex("normal"));
            String ferragemExposta = c.getString(c.getColumnIndex("ferragemExposta"));
            String fletido = c.getString(c.getColumnIndex("fletido"));
            String danificado = c.getString(c.getColumnIndex("danificado"));
            String abalrroado = c.getString(c.getColumnIndex("abalrroado"));
            String trincado = c.getString(c.getColumnIndex("trincado"));
            String ramalSubt = c.getString(c.getColumnIndex("ramalSubt"));
            String observacaoFisicas = c.getString(c.getColumnIndex("observacaoFisicas"));
            String trafo = c.getString(c.getColumnIndex("trafo"));
            String religador = c.getString(c.getColumnIndex("religador"));
            String medicao = c.getString(c.getColumnIndex("medicao"));
            String chFusivel = c.getString(c.getColumnIndex("chFusivel"));
            String chFaca = c.getString(c.getColumnIndex("chFaca"));
            String observacaoAtivos = c.getString(c.getColumnIndex("observacaoAtivos"));
            String tipoPot = c.getString(c.getColumnIndex("tipoPot"));
            String potReator = c.getString(c.getColumnIndex("potReator"));
            String vinteEQuatro = c.getString(c.getColumnIndex("vinteEQuatro"));
            String codigo = c.getString(c.getColumnIndex("codigo"));
            String reservaTec = c.getString(c.getColumnIndex("reservaTec"));
            String comSemMedicao = c.getString(c.getColumnIndex("comSemMedicao"));
            String descidaCabos = c.getString(c.getColumnIndex("descidaCabos"));
            String placaIdent = c.getString(c.getColumnIndex("placaIdent"));
            String descricaoIrregularidade = c.getString(c.getColumnIndex("descricaoIrregularidade"));
            String dimensaoVegetacao = c.getString(c.getColumnIndex("dimensaoVegetacao"));
            String distanciaBaixa = c.getString(c.getColumnIndex("distanciaBaixa"));
            String distanciaMedia = c.getString(c.getColumnIndex("distanciaMedia"));
            String observacaoVegetacao = c.getString(c.getColumnIndex("observacaoVegetacao"));

            Formulario formulario = new Formulario();
            formulario.setId(id);
            formulario.setNome(nome);
            formulario.setEndereco(endereco);
            formulario.setData(data);
            formulario.setMunicipio(municipio);
            formulario.setAlturaCarga(alturaCarga);
            formulario.setLatitude(latitude);
            formulario.setLongitude(longitude);
            formulario.setTipoPoste(tipoPoste);
            formulario.setNormal(normal);
            formulario.setFerragemExposta(ferragemExposta);
            formulario.setFletido(fletido);
            formulario.setDanificado(danificado);
            formulario.setAbalrroado(abalrroado);
            formulario.setTrincado(trincado);
            formulario.setRamalSubt(ramalSubt);
            formulario.setObservacaoFisicas(observacaoFisicas);
            formulario.setTrafo(trafo);
            formulario.setReligador(religador);
            formulario.setMedicao(medicao);
            formulario.setChFusivel(chFusivel);
            formulario.setChFaca(chFaca);
            formulario.setObservacaoAtivos(observacaoAtivos);
            formulario.setTipoPot(tipoPot);
            formulario.setPotReator(potReator);
            formulario.setVinteEQuatro(vinteEQuatro);
            formulario.setCodigo(codigo);
            formulario.setReservaTec(reservaTec);
            formulario.setComSemMedicao(comSemMedicao);
            formulario.setDescidaCabos(descidaCabos);
            formulario.setPlacaIdent(placaIdent);
            formulario.setDescricaoIrregularidade(descricaoIrregularidade);
            formulario.setDimensaoVegetacao(dimensaoVegetacao);
            formulario.setDistaciaBaixa(distanciaBaixa);
            formulario.setDistanciaMedia(distanciaMedia);
            formulario.setObservacaoVegetacao(observacaoVegetacao);

            formularios.add(formulario);
        }

        return formularios;
    }
}
