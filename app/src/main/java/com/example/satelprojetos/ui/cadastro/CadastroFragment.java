package com.example.satelprojetos.ui.cadastro;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
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
    private EditText endereco, alturaCarga, latitude, longitude, ramalSubt, observacaoFisicas,
             trafo, observacaoAtivos, tipoPot, potReator, nome, codigo, reservaTec,
                descricaoIrregularidade, distaciaBaixa, distanciaMedia, observacaoVegetacao;
    private Spinner municipio, vinteEQuatro, placaIdent, dimensaoVegetacao;
    private CheckBox ferragemExposta, danificado, abalrroado, trincado, religador, medicao,
            chFusivel, chFaca, comSemMedicao,descidaCabos;
    private Formulario formularioAtual;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cadastroViewModel =
                ViewModelProviders.of(this).get(CadastroViewModel.class);

        final View root = inflater.inflate(R.layout.fragment_cadastro, container, false);
        endereco = root.findViewById(R.id.textCadastroEndereco);
        alturaCarga= root.findViewById(R.id.textCadastroAlturaCarga);
        latitude = root.findViewById(R.id.textCadastroLatitude);
        longitude = root.findViewById(R.id.textCadastroLongitude);
        ramalSubt = root.findViewById(R.id.textCadastroRamalSubt);
        observacaoFisicas = root.findViewById(R.id.textCadastroObservacaoFisicas);
        trafo = root.findViewById(R.id.textCadastroTrafo);
        observacaoAtivos = root.findViewById(R.id.textCadastroObservacaoAtivo);
        tipoPot = root.findViewById(R.id.textCadastroTipoPot);
        potReator = root.findViewById(R.id.textCadastroPotReator);
        nome = root.findViewById(R.id.textCadastroNome);
        codigo = root.findViewById(R.id.textCadastroCodigo);
        reservaTec = root.findViewById(R.id.textCadastroReservaTec);
        descricaoIrregularidade = root.findViewById(R.id.textCadastroDescricao);
        distaciaBaixa = root.findViewById(R.id.textCadastroDistanciaBaixaTensao);
        distanciaMedia = root.findViewById(R.id.textCadastroDistanciaMediaTensao);
        observacaoVegetacao = root.findViewById(R.id.textCadastroObservacaoVegetacao);
        municipio = root.findViewById(R.id.spinCadastroMunicipio);
        vinteEQuatro = root.findViewById(R.id.spin24H);
        placaIdent = root.findViewById(R.id.spinCadastroPlaca);
        dimensaoVegetacao = root.findViewById(R.id.spinCadastroDimensaoVegetacao);
        ferragemExposta = root.findViewById(R.id.chkCadastroFerragem);
        danificado = root.findViewById(R.id.chkCadastroDanificado);
        abalrroado = root.findViewById(R.id.chkCadastroAbalrroado);
        trincado = root.findViewById(R.id.chkCadastroTrincado);
        religador = root.findViewById(R.id.chkCadastroReligador);
        medicao = root.findViewById(R.id.chkCadastroMedicao);
        chFusivel = root.findViewById(R.id.chkCadastroFusivel);
        chFaca = root.findViewById(R.id.chkCadastroFaca);
        comSemMedicao = root.findViewById(R.id.chkCadastroComSemMedicao);
        descidaCabos = root.findViewById(R.id.chkCadastroDescidaCabos);
        buttonCadastrar = root.findViewById(R.id.btnCadastroSalvar);
        try {
            formularioAtual = (Formulario) this.getArguments().getSerializable("formularioSelecionado");
            Log.i("TAGA","Texto"+formularioAtual.getMunicipio());
            if(formularioAtual != null){
                endereco.setText(formularioAtual.getEndereco());
                alturaCarga.setText(formularioAtual.getAlturaCarga());
                latitude.setText(formularioAtual.getLatitude());
                longitude.setText(formularioAtual.getLongitude());
                ramalSubt.setText(formularioAtual.getRamalSubt());
                observacaoFisicas.setText(formularioAtual.getObservacaoFisicas());
                trafo.setText(formularioAtual.getTrafo());
                observacaoAtivos.setText(formularioAtual.getObservacaoAtivos());
                tipoPot.setText(formularioAtual.getTipoPot());
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
                for (int i=0; i<vinteEQuatro.getAdapter().getCount();i++){
                    vinteEQuatro.setSelection(i);
                    if (vinteEQuatro.getSelectedItem().toString().equals(formularioAtual.getVinteEQuatro())){
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
                if(formularioAtual.getFerragemExposta().equals("X")){
                    ferragemExposta.setChecked(true);
                }
                if(formularioAtual.getDanificado().equals("X")){
                    danificado.setChecked(true);
                }
                if(formularioAtual.getAbalrroado().equals("X")){
                    abalrroado.setChecked(true);
                }
                if(formularioAtual.getTrincado().equals("X")){
                    trincado.setChecked(true);
                }
                if(formularioAtual.getReligador().equals("X")){
                    religador.setChecked(true);
                }
                if(formularioAtual.getMedicao().equals("X")){
                    medicao.setChecked(true);
                }
                if(formularioAtual.getChFusivel().equals("X")){
                    chFusivel.setChecked(true);
                }
                if(formularioAtual.getChFaca().equals("X")){
                    chFaca.setChecked(true);
                }
                if(formularioAtual.getComSemMedicao().equals("X")){
                    comSemMedicao.setChecked(true);
                }
                if(formularioAtual.getDescidaCabos().equals("X")){
                    descidaCabos.setChecked(true);
                }

            }
        }catch (Exception e ){
            
        }

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormularioDAO formularioDAO = new FormularioDAO(getActivity().getApplicationContext());
                if (formularioAtual != null) {
                    Formulario formulario = new Formulario();
                    formulario.setId(formularioAtual.getId());
                    formulario.setData(formularioAtual.getData());
                    formulario.setEndereco(Objects.requireNonNull(endereco.getText()).toString());
                    Log.i("TAG2", endereco.getText().toString());
                    formulario.setAlturaCarga(Objects.requireNonNull(alturaCarga.getText()).toString());
                    formulario.setLatitude(Objects.requireNonNull(latitude.getText()).toString());
                    formulario.setLongitude(Objects.requireNonNull(longitude.getText()).toString());
                    formulario.setRamalSubt(Objects.requireNonNull(ramalSubt.getText()).toString());
                    formulario.setObservacaoFisicas(Objects.requireNonNull(observacaoFisicas.getText()).toString());
                    formulario.setTrafo(Objects.requireNonNull(trafo.getText()).toString());
                    formulario.setObservacaoAtivos(Objects.requireNonNull(observacaoAtivos.getText()).toString());
                    formulario.setTipoPot(Objects.requireNonNull(tipoPot.getText()).toString());
                    formulario.setPotReator(Objects.requireNonNull(potReator.getText()).toString());
                    formulario.setNome(Objects.requireNonNull(nome.getText()).toString());
                    formulario.setCodigo(Objects.requireNonNull(codigo.getText()).toString());
                    formulario.setReservaTec(Objects.requireNonNull(reservaTec.getText()).toString());
                    formulario.setDescricaoIrregularidade(Objects.requireNonNull(descricaoIrregularidade.getText()).toString());
                    formulario.setDistaciaBaixa(Objects.requireNonNull(distaciaBaixa.getText()).toString());
                    formulario.setDistanciaMedia(Objects.requireNonNull(distanciaMedia.getText()).toString());
                    formulario.setObservacaoVegetacao(Objects.requireNonNull(observacaoVegetacao.getText()).toString());
                    formulario.setMunicipio(municipio.getSelectedItem().toString());
                    Log.i("TAGC", formulario.getMunicipio());
                    formulario.setVinteEQuatro(vinteEQuatro.getSelectedItem().toString());
                    formulario.setPlacaIdent(placaIdent.getSelectedItem().toString());
                    formulario.setDimensaoVegetacao(dimensaoVegetacao.getSelectedItem().toString());
                    if (ferragemExposta.isChecked()) {
                        formulario.setFerragemExposta("X");
                    } else {
                        formulario.setFerragemExposta("");
                    }
                    if (danificado.isChecked()) {
                        formulario.setDanificado("X");
                    } else {
                        formulario.setDanificado("");
                    }
                    if (abalrroado.isChecked()) {
                        formulario.setAbalrroado("X");
                    } else {
                        formulario.setAbalrroado("");
                    }
                    if (trincado.isChecked()) {
                        formulario.setTrincado("X");
                    } else {
                        formulario.setTrincado("");
                    }
                    if (religador.isChecked()) {
                        formulario.setReligador("X");
                    } else {
                        formulario.setReligador("");
                    }
                    if (medicao.isChecked()) {
                        formulario.setMedicao("X");
                    } else {
                        formulario.setMedicao("");
                    }
                    if (chFusivel.isChecked()) {
                        formulario.setChFusivel("X");
                    } else {
                        formulario.setChFusivel("");
                    }
                    if (chFaca.isChecked()) {
                        formulario.setChFaca("X");
                    } else {
                        formulario.setChFaca("");
                    }
                    if (comSemMedicao.isChecked()) {
                        formulario.setComSemMedicao("X");
                    } else {
                        formulario.setComSemMedicao("");
                    }
                    if (descidaCabos.isChecked()) {
                        formulario.setDescidaCabos("X");
                    } else {
                        formulario.setDescidaCabos("");
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
                    Log.i("TAGF", data);
                    formulario.setData(data);
                    formulario.setEndereco(Objects.requireNonNull(endereco.getText()).toString());
                    Log.i("TAG2", endereco.getText().toString());
                    formulario.setAlturaCarga(Objects.requireNonNull(alturaCarga.getText()).toString());
                    formulario.setLatitude(Objects.requireNonNull(latitude.getText()).toString());
                    formulario.setLongitude(Objects.requireNonNull(longitude.getText()).toString());
                    formulario.setRamalSubt(Objects.requireNonNull(ramalSubt.getText()).toString());
                    formulario.setObservacaoFisicas(Objects.requireNonNull(observacaoFisicas.getText()).toString());
                    formulario.setTrafo(Objects.requireNonNull(trafo.getText()).toString());
                    formulario.setObservacaoAtivos(Objects.requireNonNull(observacaoAtivos.getText()).toString());
                    formulario.setTipoPot(Objects.requireNonNull(tipoPot.getText()).toString());
                    formulario.setPotReator(Objects.requireNonNull(potReator.getText()).toString());
                    formulario.setNome(Objects.requireNonNull(nome.getText()).toString());
                    formulario.setCodigo(Objects.requireNonNull(codigo.getText()).toString());
                    formulario.setReservaTec(Objects.requireNonNull(reservaTec.getText()).toString());
                    formulario.setDescricaoIrregularidade(Objects.requireNonNull(descricaoIrregularidade.getText()).toString());
                    formulario.setDistaciaBaixa(Objects.requireNonNull(distaciaBaixa.getText()).toString());
                    formulario.setDistanciaMedia(Objects.requireNonNull(distanciaMedia.getText()).toString());
                    formulario.setObservacaoVegetacao(Objects.requireNonNull(observacaoVegetacao.getText()).toString());
                    formulario.setMunicipio(municipio.getSelectedItem().toString());
                    Log.i("TAGC", formulario.getMunicipio());
                    formulario.setVinteEQuatro(vinteEQuatro.getSelectedItem().toString());
                    formulario.setPlacaIdent(placaIdent.getSelectedItem().toString());
                    formulario.setDimensaoVegetacao(dimensaoVegetacao.getSelectedItem().toString());
                    if (ferragemExposta.isChecked()) {
                        formulario.setFerragemExposta("X");
                    } else {
                        formulario.setFerragemExposta("");
                    }
                    if (danificado.isChecked()) {
                        formulario.setDanificado("X");
                    } else {
                        formulario.setDanificado("");
                    }
                    if (abalrroado.isChecked()) {
                        formulario.setAbalrroado("X");
                    } else {
                        formulario.setAbalrroado("");
                    }
                    if (trincado.isChecked()) {
                        formulario.setTrincado("X");
                    } else {
                        formulario.setTrincado("");
                    }
                    if (religador.isChecked()) {
                        formulario.setReligador("X");
                    } else {
                        formulario.setReligador("");
                    }
                    if (medicao.isChecked()) {
                        formulario.setMedicao("X");
                    } else {
                        formulario.setMedicao("");
                    }
                    if (chFusivel.isChecked()) {
                        formulario.setChFusivel("X");
                    } else {
                        formulario.setChFusivel("");
                    }
                    if (chFaca.isChecked()) {
                        formulario.setChFaca("X");
                    } else {
                        formulario.setChFaca("");
                    }
                    if (comSemMedicao.isChecked()) {
                        formulario.setComSemMedicao("X");
                    } else {
                        formulario.setComSemMedicao("");
                    }
                    if (descidaCabos.isChecked()) {
                        formulario.setDescidaCabos("X");
                    } else {
                        formulario.setDescidaCabos("");
                    }
                    formulario.setNome(Objects.requireNonNull(nome.getText()).toString());
                    if(formularioDAO.salvar(formulario)){

                    }else{
                        Toast.makeText(getActivity().getApplicationContext(), "Erro ao salvar formulário", Toast.LENGTH_SHORT).show();
                    }

                    CadastradosFragment cadastradosFragment = new CadastradosFragment();

                    FragmentManager fm = getFragmentManager();
                    FragmentTransaction transaction = fm.beginTransaction();
                    transaction.replace(R.id.nav_host_fragment, cadastradosFragment);
                    transaction.commit();
                    Toast.makeText(getActivity().getApplicationContext(), "Sucesso ao salvar formulário", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }

    public boolean onBackPressed() {
        return false;
    }
}