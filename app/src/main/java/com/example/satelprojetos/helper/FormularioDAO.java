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
        cv.put("observacaoFisicas", formulario.getObservacaoFisicas());


        cv.put("ip", formulario.getIp());
        cv.put("ipEstrutura", formulario.getIpEstrutura());
        cv.put("quantidadeLampada", formulario.getQuantidadeLampada());
        cv.put("tipoPot", formulario.getTipoPot());
        cv.put("potReator", formulario.getPotReator());
        cv.put("ipAtivacao", formulario.getIpAtivacao());
        cv.put("vinteEQuatro", formulario.getVinteEQuatro());
        cv.put("quantidade24H", formulario.getQuantidade24H());

        cv.put("ip2", formulario.getIp2());
        cv.put("ipEstrutura2", formulario.getIpEstrutura2());
        cv.put("quantidadeLampada2", formulario.getQuantidadeLampada2());
        cv.put("tipoPot2", formulario.getTipoPot2());
        cv.put("potReator2", formulario.getPotReator2());
        cv.put("ipAtivacao2", formulario.getIpAtivacao2());
        cv.put("vinteEQuatro2", formulario.getVinteEQuatro2());
        cv.put("quantidade24H2", formulario.getQuantidade24H2());

        cv.put("ip3", formulario.getIp3());
        cv.put("ipEstrutura3", formulario.getIpEstrutura3());
        cv.put("quantidadeLampada3", formulario.getQuantidadeLampada3());
        cv.put("tipoPot3", formulario.getTipoPot3());
        cv.put("potReator3", formulario.getPotReator3());
        cv.put("ipAtivacao3", formulario.getIpAtivacao3());
        cv.put("vinteEQuatro3", formulario.getVinteEQuatro3());
        cv.put("quantidade24H3", formulario.getQuantidade24H3());
        cv.put("observacaoIP", formulario.getObservacaoIP());

        cv.put("ativos", formulario.getAtivos());
        cv.put("chkTrafoTrifasico", formulario.getChkTrafoTrifasico());
        cv.put("chkTrafoMono", formulario.getChkTrafoMono());
        cv.put("trafoTrifasico", formulario.getTrafoTrifasico());
        cv.put("trafoMono", formulario.getTrafoMono());
        cv.put("religador", formulario.getReligador());
        cv.put("medicao", formulario.getMedicao());
        cv.put("chFusivel", formulario.getChFusivel());
        cv.put("chFaca", formulario.getChFaca());
        cv.put("banco", formulario.getBanco());
        cv.put("chFusivelReligador", formulario.getChFusivelReligador());
        cv.put("ramalSubt", formulario.getRamalSubt());
        cv.put("observacaoAtivos", formulario.getObservacaoAtivos());




        cv.put("reservaTec", formulario.getReservaTec());
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
        cv.put("observacaoFisicas", formulario.getObservacaoFisicas());


        cv.put("ip", formulario.getIp());
        cv.put("ipEstrutura", formulario.getIpEstrutura());
        cv.put("quantidadeLampada", formulario.getQuantidadeLampada());
        cv.put("tipoPot", formulario.getTipoPot());
        cv.put("potReator", formulario.getPotReator());
        cv.put("ipAtivacao", formulario.getIpAtivacao());
        cv.put("vinteEQuatro", formulario.getVinteEQuatro());
        cv.put("quantidade24H", formulario.getQuantidade24H());

        cv.put("ip2", formulario.getIp2());
        cv.put("ipEstrutura2", formulario.getIpEstrutura2());
        cv.put("quantidadeLampada2", formulario.getQuantidadeLampada2());
        cv.put("tipoPot2", formulario.getTipoPot2());
        cv.put("potReator2", formulario.getPotReator2());
        cv.put("ipAtivacao2", formulario.getIpAtivacao2());
        cv.put("vinteEQuatro2", formulario.getVinteEQuatro2());
        cv.put("quantidade24H2", formulario.getQuantidade24H2());

        cv.put("ip3", formulario.getIp3());
        cv.put("ipEstrutura3", formulario.getIpEstrutura3());
        cv.put("quantidadeLampada3", formulario.getQuantidadeLampada3());
        cv.put("tipoPot3", formulario.getTipoPot3());
        cv.put("potReator3", formulario.getPotReator3());
        cv.put("ipAtivacao3", formulario.getIpAtivacao3());
        cv.put("vinteEQuatro3", formulario.getVinteEQuatro3());
        cv.put("quantidade24H3", formulario.getQuantidade24H3());
        cv.put("observacaoIP", formulario.getObservacaoIP());


        cv.put("ativos", formulario.getAtivos());
        cv.put("chkTrafoTrifasico", formulario.getChkTrafoTrifasico());
        cv.put("chkTrafoMono", formulario.getChkTrafoMono());
        cv.put("trafoTrifasico", formulario.getTrafoTrifasico());
        cv.put("trafoMono", formulario.getTrafoMono());
        cv.put("religador", formulario.getReligador());
        cv.put("medicao", formulario.getMedicao());
        cv.put("chFusivel", formulario.getChFusivel());
        cv.put("chFaca", formulario.getChFaca());
        cv.put("banco", formulario.getBanco());
        cv.put("chFusivelReligador", formulario.getChFusivelReligador());
        cv.put("ramalSubt", formulario.getRamalSubt());
        cv.put("observacaoAtivos", formulario.getObservacaoAtivos());


        cv.put("reservaTec", formulario.getReservaTec());
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
            String observacaoFisicas = c.getString(c.getColumnIndex("observacaoFisicas"));


            String ip = c.getString(c.getColumnIndex("ip"));
            String ipEstrutura = c.getString(c.getColumnIndex("ipEstrutura"));
            String quantidadeLampada = c.getString(c.getColumnIndex("quantidadeLampada"));
            String tipoPot = c.getString(c.getColumnIndex("tipoPot"));
            String potReator = c.getString(c.getColumnIndex("potReator"));
            String ipAtivacao = c.getString(c.getColumnIndex("ipAtivacao"));
            String vinteEQuatro = c.getString(c.getColumnIndex("vinteEQuatro"));
            String quantidade24H = c.getString(c.getColumnIndex("quantidade24H"));

            String ip2 = c.getString(c.getColumnIndex("ip2"));
            String ipEstrutura2 = c.getString(c.getColumnIndex("ipEstrutura2"));
            String quantidadeLampada2 = c.getString(c.getColumnIndex("quantidadeLampada2"));
            String tipoPot2 = c.getString(c.getColumnIndex("tipoPot2"));
            String potReator2 = c.getString(c.getColumnIndex("potReator2"));
            String ipAtivacao2 = c.getString(c.getColumnIndex("ipAtivacao2"));
            String vinteEQuatro2 = c.getString(c.getColumnIndex("vinteEQuatro2"));
            String quantidade24H2 = c.getString(c.getColumnIndex("quantidade24H2"));

            String ip3 = c.getString(c.getColumnIndex("ip3"));
            String ipEstrutura3 = c.getString(c.getColumnIndex("ipEstrutura3"));
            String quantidadeLampada3 = c.getString(c.getColumnIndex("quantidadeLampada3"));
            String tipoPot3 = c.getString(c.getColumnIndex("tipoPot3"));
            String potReator3 = c.getString(c.getColumnIndex("potReator3"));
            String ipAtivacao3 = c.getString(c.getColumnIndex("ipAtivacao3"));
            String vinteEQuatro3 = c.getString(c.getColumnIndex("vinteEQuatro3"));
            String quantidade24H3 = c.getString(c.getColumnIndex("quantidade24H3"));
            String observacaoIP = c.getString(c.getColumnIndex("observacaoIP"));

            String ativos = c.getString(c.getColumnIndex("ativos"));
            String chkTrafoTrifasico = c.getString(c.getColumnIndex("chkTrafoTrifasico"));
            String chkTrafoMono = c.getString(c.getColumnIndex("chkTrafoMono"));
            String trafoTrifasico = c.getString(c.getColumnIndex("trafoTrifasico"));
            String trafoMono = c.getString(c.getColumnIndex("trafoMono"));
            String religador = c.getString(c.getColumnIndex("religador"));
            String medicao = c.getString(c.getColumnIndex("medicao"));
            String chFusivel = c.getString(c.getColumnIndex("chFusivel"));
            String chFaca = c.getString(c.getColumnIndex("chFaca"));
            String banco = c.getString(c.getColumnIndex("banco"));
            String chFusivelReligador = c.getString(c.getColumnIndex("chFusivelReligador"));
            String ramalSubt = c.getString(c.getColumnIndex("ramalSubt"));
            String observacaoAtivos = c.getString(c.getColumnIndex("observacaoAtivos"));


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
            formulario.setObservacaoFisicas(observacaoFisicas);


            formulario.setIp(ip);
            formulario.setIpEstrutura(ipEstrutura);
            formulario.setQuantidadeLampada(quantidadeLampada);
            formulario.setTipoPot(tipoPot);
            formulario.setPotReator(potReator);
            formulario.setIpAtivacao(ipAtivacao);
            formulario.setVinteEQuatro(vinteEQuatro);
            formulario.setQuantidade24H(quantidade24H);

            formulario.setIp2(ip2);
            formulario.setIpEstrutura2(ipEstrutura2);
            formulario.setQuantidadeLampada2(quantidadeLampada2);
            formulario.setTipoPot2(tipoPot2);
            formulario.setPotReator2(potReator2);
            formulario.setIpAtivacao2(ipAtivacao2);
            formulario.setVinteEQuatro2(vinteEQuatro2);
            formulario.setQuantidade24H2(quantidade24H2);

            formulario.setIp3(ip3);
            formulario.setIpEstrutura3(ipEstrutura3);
            formulario.setQuantidadeLampada3(quantidadeLampada3);
            formulario.setTipoPot3(tipoPot3);
            formulario.setPotReator3(potReator3);
            formulario.setIpAtivacao3(ipAtivacao3);
            formulario.setVinteEQuatro3(vinteEQuatro3);
            formulario.setQuantidade24H3(quantidade24H3);
            formulario.setObservacaoIP(observacaoIP);


            formulario.setAtivos(ativos);
            formulario.setChkTrafoTrifasico(chkTrafoTrifasico);
            formulario.setChkTrafoMono(chkTrafoMono);
            formulario.setTrafoTrifasico(trafoTrifasico);
            formulario.setTrafoMono(trafoMono);
            formulario.setReligador(religador);
            formulario.setMedicao(medicao);
            formulario.setChFusivel(chFusivel);
            formulario.setChFaca(chFaca);
            formulario.setBanco(banco);
            formulario.setChFusivelReligador(chFusivelReligador);
            formulario.setRamalSubt(ramalSubt);
            formulario.setObservacaoAtivos(observacaoAtivos);



            formulario.setReservaTec(reservaTec);
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
