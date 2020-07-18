package com.example.satelprojetos.ui.cadastro;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.example.satelprojetos.R;
import com.example.satelprojetos.helper.FormularioDAO;
import com.example.satelprojetos.model.Formulario;
import com.example.satelprojetos.ui.cadastrados.CadastradosFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class CadastroFragment extends Fragment {

    private CadastroViewModel cadastroViewModel;
    private Button buttonCadastrar;
    private EditText endereco, latitude, longitude, ramalSubt, observacaoFisicas,
             trafo, observacaoAtivos,quantidadeLampada, potReator,quantidade24H, nome, codigo, reservaTec,
                descricaoIrregularidade, distaciaBaixa, distanciaMedia, observacaoVegetacao;
    private Spinner municipio,alturaCarga, tipoPoste,ipEstrutura,tipoPot, placaIdent, dimensaoVegetacao, ipAtivacao;
    private CheckBox normal, ferragemExposta, fletido, danificado, abalrroado, trincado, religador, medicao,
            chFusivel, chFaca, comSemMedicao,descidaCabos,vinteEQuatro,ip;
    private Formulario formularioAtual;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cadastroViewModel =
                ViewModelProviders.of(this).get(CadastroViewModel.class);

        final View root = inflater.inflate(R.layout.fragment_cadastro, container, false);
        endereco = root.findViewById(R.id.textCadastroEndereco);
        municipio = root.findViewById(R.id.spinCadastroMunicipio);
        latitude = root.findViewById(R.id.textCadastroLatitude);
        longitude = root.findViewById(R.id.textCadastroLongitude);
        alturaCarga= root.findViewById(R.id.spinCadastroAlturaCarga);
        //ramalSubt = root.findViewById(R.id.textCadastroRamalSubt);
        //Fisicas
        tipoPoste = root.findViewById(R.id.spinCadastroTipoPoste);
        normal = root.findViewById(R.id.chkCadastroNormal);
        ferragemExposta = root.findViewById(R.id.chkCadastroFerragem);
        fletido = root.findViewById(R.id.chkCadastroFletido);
        danificado = root.findViewById(R.id.chkCadastroDanificado);
        abalrroado = root.findViewById(R.id.chkCadastroAbalrroado);
        trincado = root.findViewById(R.id.chkCadastroTrincado);
        observacaoFisicas = root.findViewById(R.id.textCadastroObservacaoFisicas);
        //Iluminação
        ip = root.findViewById(R.id.chkCadastroIP);
        ipEstrutura = root.findViewById(R.id.spinCadastroIPEstrutura);
        quantidadeLampada = root.findViewById(R.id.textCadastroQuantidadeLampada);
        ipEstrutura.setEnabled(false);
        tipoPot = root.findViewById(R.id.spinCadastroTipoPot);
        tipoPot.setEnabled(false);
        potReator = root.findViewById(R.id.textCadastroPotReator);
        ipAtivacao = root.findViewById(R.id.spinCadastroIPAtivacao);
        ipAtivacao.setEnabled(false);
        vinteEQuatro = root.findViewById(R.id.chkCadastroVinteEQuatro);
        quantidade24H = root.findViewById(R.id.txtCadastroQuantidade24H);
        //Trafo
        trafo = root.findViewById(R.id.textCadastroTrafo);
        observacaoAtivos = root.findViewById(R.id.textCadastroObservacaoAtivo);
        nome = root.findViewById(R.id.textCadastroNome);
        codigo = root.findViewById(R.id.textCadastroCodigo);
        reservaTec = root.findViewById(R.id.textCadastroReservaTec);
        descricaoIrregularidade = root.findViewById(R.id.textCadastroDescricao);
        distaciaBaixa = root.findViewById(R.id.textCadastroDistanciaBaixaTensao);
        distanciaMedia = root.findViewById(R.id.textCadastroDistanciaMediaTensao);
        observacaoVegetacao = root.findViewById(R.id.textCadastroObservacaoVegetacao);
        placaIdent = root.findViewById(R.id.spinCadastroPlaca);
        dimensaoVegetacao = root.findViewById(R.id.spinCadastroDimensaoVegetacao);
        religador = root.findViewById(R.id.chkCadastroReligador);
        medicao = root.findViewById(R.id.chkCadastroMedicao);
        chFusivel = root.findViewById(R.id.chkCadastroFusivel);
        chFaca = root.findViewById(R.id.chkCadastroFaca);
        comSemMedicao = root.findViewById(R.id.chkCadastroComSemMedicao);
        descidaCabos = root.findViewById(R.id.chkCadastroDescidaCabos);
        buttonCadastrar = root.findViewById(R.id.btnCadastroSalvar);
        try {
            formularioAtual = (Formulario) this.getArguments().getSerializable("formularioSelecionado");
            if(formularioAtual != null){
                endereco.setText(formularioAtual.getEndereco());
                latitude.setText(formularioAtual.getLatitude());
                longitude.setText(formularioAtual.getLongitude());
                ramalSubt.setText(formularioAtual.getRamalSubt());
                observacaoFisicas.setText(formularioAtual.getObservacaoFisicas());
                trafo.setText(formularioAtual.getTrafo());
                observacaoAtivos.setText(formularioAtual.getObservacaoAtivos());
                potReator.setText(formularioAtual.getPotReator());
                nome.setText(formularioAtual.getNome());
                codigo.setText(formularioAtual.getCodigo());
                reservaTec.setText(formularioAtual.getReservaTec());
                descricaoIrregularidade.setText(formularioAtual.getDescricaoIrregularidade());
                distaciaBaixa.setText(formularioAtual.getDistaciaBaixa());
                distanciaMedia.setText(formularioAtual.getDistanciaMedia());
                observacaoVegetacao.setText(formularioAtual.getObservacaoVegetacao());
                for (int i=0; i<municipio.getAdapter().getCount();i++){
                    municipio.setSelection(i);
                    if (municipio.getSelectedItem().toString().equals(formularioAtual.getMunicipio())){
                        break;
                    }
                }
                for (int i=0; i<alturaCarga.getAdapter().getCount();i++){
                    alturaCarga.setSelection(i);
                    if (alturaCarga.getSelectedItem().toString().equals(formularioAtual.getAlturaCarga())){
                        break;
                    }
                }
                for (int i=0; i<tipoPoste.getAdapter().getCount();i++){
                    tipoPoste.setSelection(i);
                    if (tipoPoste.getSelectedItem().toString().equals(formularioAtual.getTipoPoste())){
                        break;
                    }
                }
                for (int i=0; i<tipoPot.getAdapter().getCount();i++){
                    tipoPot.setSelection(i);
                    if (tipoPot.getSelectedItem().toString().equals(formularioAtual.getTipoPot())){
                        break;
                    }
                }
                for (int i=0; i<placaIdent.getAdapter().getCount();i++){
                    placaIdent.setSelection(i);
                    if (placaIdent.getSelectedItem().toString().equals(formularioAtual.getPlacaIdent())){
                        break;
                    }
                }
                for (int i=0; i<dimensaoVegetacao.getAdapter().getCount();i++){
                    dimensaoVegetacao.setSelection(i);
                    if (dimensaoVegetacao.getSelectedItem().toString().equals(formularioAtual.getDimensaoVegetacao())){
                        break;
                    }
                }
                if(formularioAtual.getNormal().equals("Sim")){
                    normal.setChecked(true);
                }else{
                    normal.setChecked(false);
                    ferragemExposta.setEnabled(true);
                    fletido.setEnabled(true);
                    abalrroado.setEnabled(true);
                    trincado.setEnabled(true);
                    danificado.setEnabled(true);
                }
                if(formularioAtual.getFerragemExposta().equals("Sim")){
                    ferragemExposta.setChecked(true);
                }
                if(formularioAtual.getFletido().equals("Sim")){
                    fletido.setChecked(true);
                }
                if(formularioAtual.getDanificado().equals("Sim")){
                    danificado.setChecked(true);
                }
                if(formularioAtual.getAbalrroado().equals("Sim")){
                    abalrroado.setChecked(true);
                }
                if(formularioAtual.getTrincado().equals("Sim")){
                    trincado.setChecked(true);
                }
                if(formularioAtual.getVinteEQuatro().equals("Sim")){
                    vinteEQuatro.setChecked(true);
                }
                if(formularioAtual.getReligador().equals("Sim")){
                    religador.setChecked(true);
                }
                if(formularioAtual.getMedicao().equals("Sim")){
                    medicao.setChecked(true);
                }
                if(formularioAtual.getChFusivel().equals("Sim")){
                    chFusivel.setChecked(true);
                }
                if(formularioAtual.getChFaca().equals("Sim")){
                    chFaca.setChecked(true);
                }
                if(formularioAtual.getComSemMedicao().equals("Sim")){
                    comSemMedicao.setChecked(true);
                }
                if(formularioAtual.getDescidaCabos().equals("Sim")){
                    descidaCabos.setChecked(true);
                }

            }
        }catch (Exception e ){
            
        }

        normal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(normal.isChecked()){
                    ferragemExposta.setChecked(false);
                    fletido.setChecked(false);
                    danificado.setChecked(false);
                    abalrroado.setChecked(false);
                    trincado.setChecked(false);
                    ferragemExposta.setEnabled(false);
                    fletido.setEnabled(false);
                    danificado.setEnabled(false);
                    abalrroado.setEnabled(false);
                    trincado.setEnabled(false);
                }else{
                    ferragemExposta.setEnabled(true);
                    fletido.setEnabled(true);
                    danificado.setEnabled(true);
                    abalrroado.setEnabled(true);
                    trincado.setEnabled(true);
                }


            }
        }
        );

        vinteEQuatro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(vinteEQuatro.isChecked()){
                    quantidade24H.setEnabled(true);
                }else{
                    quantidade24H.setEnabled(false);
                    quantidade24H.setText("");
                }
            }
        }
        );

        ip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(ip.isChecked()){
                    ipEstrutura.setEnabled(true);
                    tipoPot.setEnabled(true);
                    potReator.setEnabled(true);
                    quantidadeLampada.setEnabled(true);
                    ipAtivacao.setEnabled(true);
                    vinteEQuatro.setEnabled(true);
                }else{
                    ipEstrutura.setEnabled(false);
                    quantidadeLampada.setEnabled(false);
                    tipoPot.setEnabled(false);
                    tipoPot.setSelection(0);
                    potReator.setEnabled(false);
                    potReator.setText("");
                    quantidadeLampada.setText("");
                    ipEstrutura.setSelection(0);
                    ipAtivacao.setEnabled(false);
                    vinteEQuatro.setEnabled(false);
                    ipAtivacao.setSelection(0);
                }
            }
        });

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormularioDAO formularioDAO = new FormularioDAO(getActivity().getApplicationContext());
                if (formularioAtual != null) {
                    Formulario formulario = new Formulario();
                    formulario.setId(formularioAtual.getId());
                    formulario.setData(formularioAtual.getData());
                    formulario.setEndereco(Objects.requireNonNull(endereco.getText()).toString());
                    formulario.setLatitude(Objects.requireNonNull(latitude.getText()).toString());
                    formulario.setLongitude(Objects.requireNonNull(longitude.getText()).toString());
                    formulario.setRamalSubt(Objects.requireNonNull(ramalSubt.getText()).toString());
                    formulario.setObservacaoFisicas(Objects.requireNonNull(observacaoFisicas.getText()).toString());
                    formulario.setTrafo(Objects.requireNonNull(trafo.getText()).toString());
                    formulario.setObservacaoAtivos(Objects.requireNonNull(observacaoAtivos.getText()).toString());
                    formulario.setPotReator(Objects.requireNonNull(potReator.getText()).toString());
                    formulario.setNome(Objects.requireNonNull(nome.getText()).toString());
                    formulario.setCodigo(Objects.requireNonNull(codigo.getText()).toString());
                    formulario.setReservaTec(Objects.requireNonNull(reservaTec.getText()).toString());
                    formulario.setDescricaoIrregularidade(Objects.requireNonNull(descricaoIrregularidade.getText()).toString());
                    formulario.setDistaciaBaixa(Objects.requireNonNull(distaciaBaixa.getText()).toString());
                    formulario.setDistanciaMedia(Objects.requireNonNull(distanciaMedia.getText()).toString());
                    formulario.setObservacaoVegetacao(Objects.requireNonNull(observacaoVegetacao.getText()).toString());
                    formulario.setMunicipio(municipio.getSelectedItem().toString());
                    formulario.setAlturaCarga(alturaCarga.getSelectedItem().toString());
                    formulario.setTipoPoste(tipoPoste.getSelectedItem().toString());
                    formulario.setTipoPot(tipoPot.getSelectedItem().toString());
                    formulario.setPlacaIdent(placaIdent.getSelectedItem().toString());
                    formulario.setDimensaoVegetacao(dimensaoVegetacao.getSelectedItem().toString());
                    if (normal.isChecked()) {
                        formulario.setNormal("Sim");
                    } else {
                        formulario.setNormal("Não");
                    }
                    if (ferragemExposta.isChecked()) {
                        formulario.setFerragemExposta("Sim");
                    } else {
                        formulario.setFerragemExposta("Não");
                    }
                    if (fletido.isChecked()) {
                        formulario.setFletido("Sim");
                    } else {
                        formulario.setFletido("Não");
                    }
                    if (danificado.isChecked()) {
                        formulario.setDanificado("Sim");
                    } else {
                        formulario.setDanificado("Não");
                    }
                    if (abalrroado.isChecked()) {
                        formulario.setAbalrroado("Sim");
                    } else {
                        formulario.setAbalrroado("Não");
                    }
                    if (trincado.isChecked()) {
                        formulario.setTrincado("Sim");
                    } else {
                        formulario.setTrincado("Não");
                    }
                    if (vinteEQuatro.isChecked()) {
                        formulario.setVinteEQuatro("Sim");
                    } else {
                        formulario.setVinteEQuatro("Não");
                    }
                    if (religador.isChecked()) {
                        formulario.setReligador("Sim");
                    } else {
                        formulario.setReligador("Não");
                    }
                    if (medicao.isChecked()) {
                        formulario.setMedicao("Sim");
                    } else {
                        formulario.setMedicao("Não");
                    }
                    if (chFusivel.isChecked()) {
                        formulario.setChFusivel("Sim");
                    } else {
                        formulario.setChFusivel("Não");
                    }
                    if (chFaca.isChecked()) {
                        formulario.setChFaca("Sim");
                    } else {
                        formulario.setChFaca("Não");
                    }
                    if (comSemMedicao.isChecked()) {
                        formulario.setComSemMedicao("Sim");
                    } else {
                        formulario.setComSemMedicao("Não");
                    }
                    if (descidaCabos.isChecked()) {
                        formulario.setDescidaCabos("Sim");
                    } else {
                        formulario.setDescidaCabos("Não");
                    }
                    formulario.setNome(Objects.requireNonNull(nome.getText()).toString());

                    if(formularioDAO.atualizar(formulario)){
                        CadastradosFragment cadastradosFragment = new CadastradosFragment();

                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();
                        transaction.replace(R.id.nav_host_fragment, cadastradosFragment);
                        transaction.commit();
                        Toast.makeText(getActivity().getApplicationContext(), "Sucesso ao atualizar formulário", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity().getApplicationContext(), "Erro ao atualizar formulário", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    String thisDayText, thisMonthText, thisYearText;


                    //region Inicialização da data
                    Calendar calendar = Calendar.getInstance();

                    int thisYear = calendar.get(Calendar.YEAR);
                    Log.d("Data", "# thisYear : " + thisYear);
                    thisYearText = String.valueOf(thisYear);

                    int thisMonth = calendar.get(Calendar.MONTH) + 1;
                    Log.d("Data", "@ thisMonth : " + thisMonth);
                    if (thisMonth < 9) {
                        thisMonthText = "0" + thisMonth;
                    } else {
                        thisMonthText = String.valueOf(thisMonth);
                    }

                    int thisDay = calendar.get(Calendar.DAY_OF_MONTH);
                    Log.d("Data", "$ thisDay : " + thisDay);
                    if (thisDay < 9) {
                        thisDayText = "0" + thisDay;
                    } else {
                        thisDayText = String.valueOf(thisDay);
                    }
                    String data = thisDayText + "/" + thisMonthText + "/" + thisYearText;
                    Log.d("Data", "$ data : " + data);
                    //endregion

                    Formulario formulario = new Formulario();
                    formulario.setData(data);
                    formulario.setEndereco(Objects.requireNonNull(endereco.getText()).toString());
                    formulario.setLatitude(Objects.requireNonNull(latitude.getText()).toString());
                    formulario.setLongitude(Objects.requireNonNull(longitude.getText()).toString());
                    formulario.setRamalSubt(Objects.requireNonNull(ramalSubt.getText()).toString());
                    formulario.setObservacaoFisicas(Objects.requireNonNull(observacaoFisicas.getText()).toString());
                    formulario.setTrafo(Objects.requireNonNull(trafo.getText()).toString());
                    formulario.setObservacaoAtivos(Objects.requireNonNull(observacaoAtivos.getText()).toString());
                    formulario.setPotReator(Objects.requireNonNull(potReator.getText()).toString());
                    formulario.setNome(Objects.requireNonNull(nome.getText()).toString());
                    formulario.setCodigo(Objects.requireNonNull(codigo.getText()).toString());
                    formulario.setReservaTec(Objects.requireNonNull(reservaTec.getText()).toString());
                    formulario.setDescricaoIrregularidade(Objects.requireNonNull(descricaoIrregularidade.getText()).toString());
                    formulario.setDistaciaBaixa(Objects.requireNonNull(distaciaBaixa.getText()).toString());
                    formulario.setDistanciaMedia(Objects.requireNonNull(distanciaMedia.getText()).toString());
                    formulario.setObservacaoVegetacao(Objects.requireNonNull(observacaoVegetacao.getText()).toString());
                    formulario.setMunicipio(municipio.getSelectedItem().toString());
                    formulario.setAlturaCarga(alturaCarga.getSelectedItem().toString());
                    formulario.setTipoPoste(tipoPoste.getSelectedItem().toString());
                    formulario.setTipoPot(tipoPot.getSelectedItem().toString());
                    formulario.setPlacaIdent(placaIdent.getSelectedItem().toString());
                    formulario.setDimensaoVegetacao(dimensaoVegetacao.getSelectedItem().toString());
                    if (normal.isChecked()) {
                        formulario.setNormal("Sim");
                    } else {
                        formulario.setNormal("Não");
                    }
                    if (ferragemExposta.isChecked()) {
                        formulario.setFerragemExposta("Sim");
                    } else {
                        formulario.setFerragemExposta("Não");
                    }
                    if (fletido.isChecked()) {
                        formulario.setFletido("Sim");
                    } else {
                        formulario.setFletido("Não");
                    }
                    if (danificado.isChecked()) {
                        formulario.setDanificado("Sim");
                    } else {
                        formulario.setDanificado("Não");
                    }
                    if (abalrroado.isChecked()) {
                        formulario.setAbalrroado("Sim");
                    } else {
                        formulario.setAbalrroado("Não");
                    }
                    if (trincado.isChecked()) {
                        formulario.setTrincado("Sim");
                    } else {
                        formulario.setTrincado("Não");
                    }
                    if (vinteEQuatro.isChecked()) {
                        formulario.setVinteEQuatro("Sim");
                    } else {
                        formulario.setVinteEQuatro("Não");
                    }
                    if (religador.isChecked()) {
                        formulario.setReligador("Sim");
                    } else {
                        formulario.setReligador("Não");
                    }
                    if (medicao.isChecked()) {
                        formulario.setMedicao("Sim");
                    } else {
                        formulario.setMedicao("Não");
                    }
                    if (chFusivel.isChecked()) {
                        formulario.setChFusivel("Sim");
                    } else {
                        formulario.setChFusivel("Não");
                    }
                    if (chFaca.isChecked()) {
                        formulario.setChFaca("Sim");
                    } else {
                        formulario.setChFaca("Não");
                    }
                    if (comSemMedicao.isChecked()) {
                        formulario.setComSemMedicao("Sim");
                    } else {
                        formulario.setComSemMedicao("Não");
                    }
                    if (descidaCabos.isChecked()) {
                        formulario.setDescidaCabos("Sim");
                    } else {
                        formulario.setDescidaCabos("Não");
                    }
                    formulario.setNome(Objects.requireNonNull(nome.getText()).toString());
                    if(formularioDAO.salvar(formulario)){
                        CadastradosFragment cadastradosFragment = new CadastradosFragment();

                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();
                        transaction.replace(R.id.nav_host_fragment, cadastradosFragment);
                        transaction.commit();
                        Toast.makeText(getActivity().getApplicationContext(), "Sucesso ao salvar formulário", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity().getApplicationContext(), "Erro ao salvar formulário", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        return root;
    }

    public boolean onBackPressed() {
        return false;
    }
}