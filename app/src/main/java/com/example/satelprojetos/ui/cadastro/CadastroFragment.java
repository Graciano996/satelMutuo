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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.satelprojetos.R;
import com.example.satelprojetos.helper.FormularioDAO;
import com.example.satelprojetos.model.Formulario;
import com.example.satelprojetos.ui.cadastrados.CadastradosFragment;

import java.util.Calendar;
import java.util.Objects;

public class CadastroFragment extends Fragment {

    private EditText endereco, latitude, longitude, observacaoFisicas,
              observacaoAtivos,quantidadeLampada,quantidadeLampada2,quantidadeLampada3,
            potReator,potReator2,potReator3,quantidade24H,quantidade24H2,quantidade24H3 ,
                 distaciaBaixa, distanciaMedia, observacaoVegetacao, observacaoIP,
                quantidadeCabos, quantidadeCabos2, quantidadeCabos3, quantidadeCabos4, quantidadeCabos5,
                nome, nome2, nome3, nome4, nome5, descricaoIrregularidade, descricaoIrregularidade2,
                descricaoIrregularidade3, descricaoIrregularidade4,descricaoIrregularidade5,
                observacaoMutuo, observacaoMutuo2, observacaoMutuo3, observacaoMutuo4, observacaoMutuo5;
    private Spinner municipio,alturaCarga, tipoPoste,ipEstrutura,ipEstrutura2,ipEstrutura3,tipoPot,
            tipoPot2,tipoPot3, placaIdent, dimensaoVegetacao, ipAtivacao,ipAtivacao2,ipAtivacao3,
            trafoTrifasico, trafoMono,ramalSubt, quantidadeOcupantes,
            tipoCabo, tipoCabo2, tipoCabo3, tipoCabo4, tipoCabo5, finalidade, finalidade2, finalidade3,
            finalidade4, finalidade5, ceans, ceans2, ceans3, ceans4, ceans5, tar, tar2, tar3, tar4,
            tar5, reservaTec, reservaTec2, reservaTec3, reservaTec4, reservaTec5, backbone,
            backbone2, backbone3, backbone4,backbone5;
    private CheckBox normal, ferragemExposta, fletido, danificado, abalrroado, trincado, religador, medicao,
            chFusivel, chFaca,vinteEQuatro,vinteEQuatro2,vinteEQuatro3,
            ativos,chkTrafoTrifasico, chkTrafoMono, ip,ip2,ip3,chFusivelReligador, chBanco, mutuo,
            placaIdentificadora, placaIdentificadora2, placaIdentificadora3, placaIdentificadora4,
            placaIdentificadora5,descidaCabos, descidaCabos2, descidaCabos3, descidaCabos4, descidaCabos5;
    private Formulario formularioAtual;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        final View root = inflater.inflate(R.layout.fragment_cadastro, container, false);



        endereco = root.findViewById(R.id.textCadastroEndereco);
        municipio = root.findViewById(R.id.spinCadastroMunicipio);
        latitude = root.findViewById(R.id.textCadastroLatitude);
        longitude = root.findViewById(R.id.textCadastroLongitude);
        alturaCarga= root.findViewById(R.id.spinCadastroAlturaCarga);

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

        ip2 = root.findViewById(R.id.chkCadastroIP2);
        ipEstrutura2 = root.findViewById(R.id.spinCadastroIPEstrutura2);
        quantidadeLampada2 = root.findViewById(R.id.textCadastroQuantidadeLampada2);
        ipEstrutura2.setEnabled(false);
        tipoPot2 = root.findViewById(R.id.spinCadastroTipoPot2);
        tipoPot2.setEnabled(false);
        potReator2 = root.findViewById(R.id.textCadastroPotReator2);
        ipAtivacao2 = root.findViewById(R.id.spinCadastroIPAtivacao2);
        ipAtivacao2.setEnabled(false);
        vinteEQuatro2 = root.findViewById(R.id.chkCadastroVinteEQuatro2);
        quantidade24H2 = root.findViewById(R.id.txtCadastroQuantidade24H2);

        ip3 = root.findViewById(R.id.chkCadastroIP3);
        ipEstrutura3 = root.findViewById(R.id.spinCadastroIPEstrutura3);
        quantidadeLampada3 = root.findViewById(R.id.textCadastroQuantidadeLampada3);
        ipEstrutura3.setEnabled(false);
        tipoPot3 = root.findViewById(R.id.spinCadastroTipoPot3);
        tipoPot3.setEnabled(false);
        potReator3 = root.findViewById(R.id.textCadastroPotReator3);
        ipAtivacao3 = root.findViewById(R.id.spinCadastroIPAtivacao3);
        ipAtivacao3.setEnabled(false);
        vinteEQuatro3 = root.findViewById(R.id.chkCadastroVinteEQuatro3);
        quantidade24H3 = root.findViewById(R.id.txtCadastroQuantidade24H3);
        observacaoIP = root.findViewById(R.id.textCadastroObservacaoIP);

        //TRAFO
        ativos = root.findViewById(R.id.chkAtivos);
        chkTrafoTrifasico = root.findViewById(R.id.chkCadastroTrafoTrifasico);
        chkTrafoMono = root.findViewById(R.id.chkCadastroTrafoMono);
        trafoTrifasico = root.findViewById(R.id.spinCadastroTrafoTrifasico);
        trafoTrifasico.setEnabled(false);
        trafoMono = root.findViewById(R.id.spinCadastroTrafoMono);
        trafoMono.setEnabled(false);
        religador = root.findViewById(R.id.chkCadastroReligador);
        medicao = root.findViewById(R.id.chkCadastroMedicao);
        chFusivel = root.findViewById(R.id.chkCadastroFusivel);
        chFaca = root.findViewById(R.id.chkCadastroFaca);
        chBanco = root.findViewById(R.id.chkCadastroBanco);
        chFusivelReligador = root.findViewById(R.id.chkCadastroFusivelReligador);
        ramalSubt = root.findViewById(R.id.spinCadastroRamalSubt);
        ramalSubt.setEnabled(false);
        observacaoAtivos = root.findViewById(R.id.textCadastroObservacaoAtivo);

        //MUTUO
        mutuo = root.findViewById(R.id.chkCadastroMutuo);
        quantidadeOcupantes = root.findViewById(R.id.spinCadastroMutuoOcupantes);

        quantidadeCabos = root.findViewById(R.id.textCadastroMutuoQuantidadeCabos);
        tipoCabo = root.findViewById(R.id.spinCadastroMutuoTipoCabo);
        nome = root.findViewById(R.id.textCadastroNome);
        finalidade = root.findViewById(R.id.spinCadastroFinalidade);
        ceans = root.findViewById(R.id.spinCadastroCeans);
        tar = root.findViewById(R.id.spinCadastroTar);
        reservaTec = root.findViewById(R.id.spinCadastroReservaTec);
        backbone = root.findViewById(R.id.spinCadastroBackbone);
        placaIdentificadora = root.findViewById(R.id.chkCadastroPlaca);
        descidaCabos = root.findViewById(R.id.chkCadastroDescidaCabos);
        descricaoIrregularidade = root.findViewById(R.id.textCadastroDescricao);
        observacaoMutuo = root.findViewById(R.id.textCadastroObservacaoMutuo);

        quantidadeCabos2 = root.findViewById(R.id.textCadastroMutuoQuantidadeCabos2);
        tipoCabo2 = root.findViewById(R.id.spinCadastroMutuoTipoCabo2);
        nome2 = root.findViewById(R.id.textCadastroNome2);
        finalidade2 = root.findViewById(R.id.spinCadastroFinalidade2);
        ceans2 = root.findViewById(R.id.spinCadastroCeans2);
        tar2 = root.findViewById(R.id.spinCadastroTar2);
        reservaTec2 = root.findViewById(R.id.spinCadastroReservaTec2);
        backbone2 = root.findViewById(R.id.spinCadastroBackbone2);
        placaIdentificadora2 = root.findViewById(R.id.chkCadastroPlaca2);
        descidaCabos2 = root.findViewById(R.id.chkCadastroDescidaCabos2);
        descricaoIrregularidade2 = root.findViewById(R.id.textCadastroDescricao2);
        observacaoMutuo2 = root.findViewById(R.id.textCadastroObservacaoMutuo2);

        quantidadeCabos3 = root.findViewById(R.id.textCadastroMutuoQuantidadeCabos3);
        tipoCabo3 = root.findViewById(R.id.spinCadastroMutuoTipoCabo3);
        nome3 = root.findViewById(R.id.textCadastroNome3);
        finalidade3 = root.findViewById(R.id.spinCadastroFinalidade3);
        ceans3 = root.findViewById(R.id.spinCadastroCeans3);
        tar3 = root.findViewById(R.id.spinCadastroTar3);
        reservaTec3 = root.findViewById(R.id.spinCadastroReservaTec3);
        backbone3 = root.findViewById(R.id.spinCadastroBackbone3);
        placaIdentificadora3 = root.findViewById(R.id.chkCadastroPlaca3);
        descidaCabos3 = root.findViewById(R.id.chkCadastroDescidaCabos3);
        descricaoIrregularidade3 = root.findViewById(R.id.textCadastroDescricao3);
        observacaoMutuo3 = root.findViewById(R.id.textCadastroObservacaoMutuo3);

        quantidadeCabos4 = root.findViewById(R.id.textCadastroMutuoQuantidadeCabos4);
        tipoCabo4 = root.findViewById(R.id.spinCadastroMutuoTipoCabo4);
        nome4 = root.findViewById(R.id.textCadastroNome4);
        finalidade4 = root.findViewById(R.id.spinCadastroFinalidade4);
        ceans4 = root.findViewById(R.id.spinCadastroCeans4);
        tar4 = root.findViewById(R.id.spinCadastroTar4);
        reservaTec4 = root.findViewById(R.id.spinCadastroReservaTec4);
        backbone4 = root.findViewById(R.id.spinCadastroBackbone4);
        placaIdentificadora4 = root.findViewById(R.id.chkCadastroPlaca4);
        descidaCabos4 = root.findViewById(R.id.chkCadastroDescidaCabos4);
        descricaoIrregularidade4 = root.findViewById(R.id.textCadastroDescricao4);
        observacaoMutuo4 = root.findViewById(R.id.textCadastroObservacaoMutuo4);

        quantidadeCabos5 = root.findViewById(R.id.textCadastroMutuoQuantidadeCabos5);
        tipoCabo5 = root.findViewById(R.id.spinCadastroMutuoTipoCabo5);
        nome5 = root.findViewById(R.id.textCadastroNome5);
        finalidade5 = root.findViewById(R.id.spinCadastroFinalidade5);
        ceans5 = root.findViewById(R.id.spinCadastroCeans5);
        tar5 = root.findViewById(R.id.spinCadastroTar5);
        reservaTec5 = root.findViewById(R.id.spinCadastroReservaTec5);
        backbone5 = root.findViewById(R.id.spinCadastroBackbone5);
        placaIdentificadora5 = root.findViewById(R.id.chkCadastroPlaca5);
        descidaCabos5 = root.findViewById(R.id.chkCadastroDescidaCabos5);
        descricaoIrregularidade5 = root.findViewById(R.id.textCadastroDescricao5);
        observacaoMutuo5 = root.findViewById(R.id.textCadastroObservacaoMutuo5);


        //VEGETAÇÃO
        distaciaBaixa = root.findViewById(R.id.textCadastroDistanciaBaixaTensao);
        distanciaMedia = root.findViewById(R.id.textCadastroDistanciaMediaTensao);
        observacaoVegetacao = root.findViewById(R.id.textCadastroObservacaoVegetacao);
        placaIdent = root.findViewById(R.id.chkCadastroPlaca);
        dimensaoVegetacao = root.findViewById(R.id.spinCadastroDimensaoVegetacao);

        descidaCabos = root.findViewById(R.id.chkCadastroDescidaCabos);
        Button buttonCadastrar = root.findViewById(R.id.btnCadastroSalvar);
        try {
            assert this.getArguments() != null;
            formularioAtual = (Formulario) this.getArguments().getSerializable("formularioSelecionado");
            if(formularioAtual != null){
                //LOCALIZAÇÃO

                endereco.setText(formularioAtual.getEndereco());
                if (formularioAtual.getMunicipio().equals("-")) {
                    municipio.setSelection(0);
                }else {
                    for (int i = 0; i < municipio.getAdapter().getCount(); i++) {
                        municipio.setSelection(i);
                        if (municipio.getSelectedItem().toString().equals(formularioAtual.getMunicipio())) {
                            break;
                        }
                    }
                }
                latitude.setText(formularioAtual.getLatitude());
                longitude.setText(formularioAtual.getLongitude());
                if (formularioAtual.getAlturaCarga().equals("-")) {
                    alturaCarga.setSelection(0);
                }else {
                    for (int i = 0; i < alturaCarga.getAdapter().getCount(); i++) {
                        alturaCarga.setSelection(i);
                        if (alturaCarga.getSelectedItem().toString().equals(formularioAtual.getAlturaCarga())) {
                            break;
                        }
                    }
                }


                //CARACTERISTICAS FÍSICAS

                if (formularioAtual.getTipoPoste().equals("-")) {
                    tipoPoste.setSelection(0);
                }else {
                    for (int i = 0; i < tipoPoste.getAdapter().getCount(); i++) {
                        tipoPoste.setSelection(i);
                        if (tipoPoste.getSelectedItem().toString().equals(formularioAtual.getTipoPoste())) {
                            break;
                        }
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
                observacaoFisicas.setText(formularioAtual.getObservacaoFisicas());


                //ILUMINAÇÃO

                if(formularioAtual.getIp().equals("Sim")){
                    ip.setChecked(true);
                    if (formularioAtual.getIpEstrutura().equals("-")) {
                        ipEstrutura.setSelection(0);
                    }else {
                        for (int i = 0; i < ipEstrutura.getAdapter().getCount(); i++) {
                            ipEstrutura.setSelection(i);
                            if (ipEstrutura.getSelectedItem().toString().equals(formularioAtual.getIpEstrutura())) {
                                break;
                            }
                        }
                    }
                    quantidadeLampada.setText(formularioAtual.getQuantidadeLampada());
                    if (formularioAtual.getTipoPot().equals("-")) {
                        tipoPot.setSelection(0);
                    }else {
                        for (int i = 0; i < tipoPot.getAdapter().getCount(); i++) {
                            tipoPot.setSelection(i);
                            if (tipoPot.getSelectedItem().toString().equals(formularioAtual.getTipoPot())) {
                                break;
                            }
                        }
                    }
                    potReator.setText(formularioAtual.getPotReator());
                    if (formularioAtual.getIpAtivacao().equals("-")) {
                        ipAtivacao.setSelection(0);
                    }else {
                        for (int i = 0; i < ipAtivacao.getAdapter().getCount(); i++) {
                            ipAtivacao.setSelection(i);
                            if (ipAtivacao.getSelectedItem().toString().equals(formularioAtual.getIpAtivacao())) {
                                break;
                            }
                        }
                    }
                    if(formularioAtual.getVinteEQuatro().equals("Sim")){
                        vinteEQuatro.setChecked(true);
                        quantidade24H.setEnabled(true);
                    }
                    quantidade24H.setText(formularioAtual.getQuantidade24H());
                    ipEstrutura.setEnabled(true);
                    quantidadeLampada.setEnabled(true);
                    tipoPot.setEnabled(true);
                    potReator.setEnabled(true);
                    ipAtivacao.setEnabled(true);
                    vinteEQuatro.setEnabled(true);
                    ip2.setEnabled(true);
                    if(formularioAtual.getIp2().equals("Sim")){
                        ip2.setChecked(true);
                        if (formularioAtual.getIpEstrutura2().equals("-")) {
                            ipEstrutura2.setSelection(0);
                        }else {
                            for (int i = 0; i < ipEstrutura2.getAdapter().getCount(); i++) {
                                ipEstrutura2.setSelection(i);
                                if (ipEstrutura2.getSelectedItem().toString().equals(formularioAtual.getIpEstrutura2())) {
                                    break;
                                }
                            }
                        }
                        quantidadeLampada2.setText(formularioAtual.getQuantidadeLampada2());
                        if (formularioAtual.getTipoPot2().equals("-")) {
                            tipoPot2.setSelection(0);
                        }else {
                            for (int i = 0; i < tipoPot2.getAdapter().getCount(); i++) {
                                tipoPot2.setSelection(i);
                                if (tipoPot2.getSelectedItem().toString().equals(formularioAtual.getTipoPot2())) {
                                    break;
                                }
                            }
                        }
                        potReator2.setText(formularioAtual.getPotReator2());
                        if (formularioAtual.getIpAtivacao2().equals("-")) {
                            ipAtivacao2.setSelection(0);
                        }else {
                            for (int i = 0; i < ipAtivacao2.getAdapter().getCount(); i++) {
                                ipAtivacao2.setSelection(i);
                                if (ipAtivacao2.getSelectedItem().toString().equals(formularioAtual.getIpAtivacao2())) {
                                    break;
                                }
                            }
                        }
                        if(formularioAtual.getVinteEQuatro2().equals("Sim")){
                            vinteEQuatro2.setChecked(true);
                            quantidade24H2.setEnabled(true);
                        }
                        quantidade24H2.setText(formularioAtual.getQuantidade24H2());
                        ipEstrutura2.setEnabled(true);
                        quantidadeLampada2.setEnabled(true);
                        tipoPot2.setEnabled(true);
                        potReator2.setEnabled(true);
                        ipAtivacao2.setEnabled(true);
                        vinteEQuatro2.setEnabled(true);
                        ip3.setEnabled(true);
                        if(formularioAtual.getIp3().equals("Sim")){
                            ip3.setChecked(true);
                            if (formularioAtual.getIpEstrutura3().equals("-")) {
                                ipEstrutura3.setSelection(0);
                            }else {
                                for (int i = 0; i < ipEstrutura3.getAdapter().getCount(); i++) {
                                    ipEstrutura3.setSelection(i);
                                    if (ipEstrutura3.getSelectedItem().toString().equals(formularioAtual.getIpEstrutura3())) {
                                        break;
                                    }
                                }
                            }
                            quantidadeLampada3.setText(formularioAtual.getQuantidadeLampada3());
                            if (formularioAtual.getTipoPot3().equals("-")) {
                                tipoPot3.setSelection(0);
                            }else {
                                for (int i = 0; i < tipoPot3.getAdapter().getCount(); i++) {
                                    tipoPot3.setSelection(i);
                                    if (tipoPot3.getSelectedItem().toString().equals(formularioAtual.getTipoPot3())) {
                                        break;
                                    }
                                }
                            }
                            potReator3.setText(formularioAtual.getPotReator3());
                            if (formularioAtual.getIpAtivacao3().equals("-")) {
                                ipAtivacao3.setSelection(0);
                            }else {
                                for (int i = 0; i < ipAtivacao3.getAdapter().getCount(); i++) {
                                    ipAtivacao3.setSelection(i);
                                    if (ipAtivacao3.getSelectedItem().toString().equals(formularioAtual.getIpAtivacao3())) {
                                        break;
                                    }
                                }
                            }
                            if(formularioAtual.getVinteEQuatro3().equals("Sim")){
                                vinteEQuatro3.setChecked(true);
                                quantidade24H3.setEnabled(true);
                            }
                            quantidade24H3.setText(formularioAtual.getQuantidade24H3());
                            ipEstrutura3.setEnabled(true);
                            quantidadeLampada3.setEnabled(true);
                            tipoPot3.setEnabled(true);
                            potReator3.setEnabled(true);
                            ipAtivacao3.setEnabled(true);
                            vinteEQuatro3.setEnabled(true);

                        }

                    }


                }
                observacaoIP.setText(formularioAtual.getObservacaoIP());


                //TRAFO

                if(formularioAtual.getAtivos().equals("Sim")){
                    ativos.setChecked(true);
                    chkTrafoMono.setEnabled(true);
                    chkTrafoTrifasico.setEnabled(true);
                    trafoMono.setEnabled(true);
                    trafoTrifasico.setEnabled(true);
                    chFusivel.setEnabled(true);
                    chFaca.setEnabled(true);
                    religador.setEnabled(true);
                    medicao.setEnabled(true);
                    chBanco.setEnabled(true);
                    chFusivelReligador.setEnabled(true);
                    ramalSubt.setEnabled(true);
                }
                if(formularioAtual.getChkTrafoTrifasico().equals("Sim")){
                    chkTrafoTrifasico.setChecked(true);
                }
                if(formularioAtual.getChkTrafoMono().equals("Sim")){
                    chkTrafoMono.setChecked(true);
                }
                if (formularioAtual.getTrafoTrifasico().equals("-")) {
                    trafoTrifasico.setSelection(0);
                }else {
                    for (int i = 0; i < trafoTrifasico.getAdapter().getCount(); i++) {
                        trafoTrifasico.setSelection(i);
                        if (trafoTrifasico.getSelectedItem().toString().equals(formularioAtual.getTrafoTrifasico())) {
                            break;
                        }
                    }
                }
                if (formularioAtual.getTrafoMono().equals("-")) {
                    trafoMono.setSelection(0);
                }else {
                    for (int i = 0; i < trafoMono.getAdapter().getCount(); i++) {
                        trafoMono.setSelection(i);
                        if (trafoMono.getSelectedItem().toString().equals(formularioAtual.getTrafoMono())) {
                            break;
                        }
                    }
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
                if(formularioAtual.getBanco().equals("Sim")){
                    chBanco.setChecked(true);
                }
                if(formularioAtual.getChFusivelReligador().equals("Sim")){
                    chFusivelReligador.setChecked(true);
                }
                if (formularioAtual.getRamalSubt().equals("-")) {
                    ramalSubt.setSelection(0);
                }else {
                    for (int i = 0; i < ramalSubt.getAdapter().getCount(); i++) {
                        ramalSubt.setSelection(i);
                        if (ramalSubt.getSelectedItem().toString().equals(formularioAtual.getRamalSubt())) {
                            break;
                        }
                    }
                }
                observacaoAtivos.setText(formularioAtual.getObservacaoAtivos());

                //MUTUO
                nome.setText(formularioAtual.getNome());


                descricaoIrregularidade.setText(formularioAtual.getDescricaoIrregularidade());
                distaciaBaixa.setText(formularioAtual.getDistaciaBaixa());
                distanciaMedia.setText(formularioAtual.getDistanciaMedia());
                observacaoVegetacao.setText(formularioAtual.getObservacaoVegetacao());

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
                if(formularioAtual.getDescidaCabos().equals("Sim")){
                    descidaCabos.setChecked(true);
                }

            }
        }catch (Exception e ){
         Log.e("ERRO", "erro: " + e);
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

        vinteEQuatro2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
          if(vinteEQuatro2.isChecked()){
             quantidade24H2.setEnabled(true);
          }else{
             quantidade24H2.setEnabled(false);
              quantidade24H2.setText("");
          }
              }
           }
        );

        vinteEQuatro3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(vinteEQuatro3.isChecked()){
              quantidade24H3.setEnabled(true);
            }else{
              quantidade24H3.setEnabled(false);
              quantidade24H3.setText("");
            }
          }
        }
        );


        //Listener para só habilitar os dados da própria ip e as próximas ip caso a primeira
        // tenha sido checada
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
                    ip2.setEnabled(true);
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
                    vinteEQuatro.setChecked(false);
                    ipAtivacao.setSelection(0);
                    ip2.setChecked(false);
                    ip2.setEnabled(false);
                }
            }
        });

        //Listener para só habilitar os dados da própria ip e as próximas ip caso a segunda
        // tenha sido checada
        ip2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(ip2.isChecked()){
                    ipEstrutura2.setEnabled(true);
                    tipoPot2.setEnabled(true);
                    potReator2.setEnabled(true);
                    quantidadeLampada2.setEnabled(true);
                    ipAtivacao2.setEnabled(true);
                    vinteEQuatro2.setEnabled(true);
                    ip3.setEnabled(true);
                }else{
                    ipEstrutura2.setEnabled(false);
                    quantidadeLampada2.setEnabled(false);
                    tipoPot2.setEnabled(false);
                    tipoPot2.setSelection(0);
                    potReator2.setEnabled(false);
                    potReator2.setText("");
                    quantidadeLampada2.setText("");
                    ipEstrutura2.setSelection(0);
                    ipAtivacao2.setEnabled(false);
                    vinteEQuatro2.setChecked(false);
                    vinteEQuatro2.setEnabled(false);
                    ipAtivacao2.setSelection(0);
                    ip3.setChecked(false);
                    ip3.setEnabled(false);
                }
            }
        });

        //Listener para só habilitar os dados da própria ip caso tenha sido checada
        ip3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(ip3.isChecked()){
                    ipEstrutura3.setEnabled(true);
                    tipoPot3.setEnabled(true);
                    potReator3.setEnabled(true);
                    quantidadeLampada3.setEnabled(true);
                    ipAtivacao3.setEnabled(true);
                    vinteEQuatro3.setEnabled(true);
                }else{
                    ipEstrutura3.setEnabled(false);
                    quantidadeLampada3.setEnabled(false);
                    tipoPot3.setEnabled(false);
                    tipoPot3.setSelection(0);
                    potReator3.setEnabled(false);
                    potReator3.setText("");
                    quantidadeLampada3.setText("");
                    ipEstrutura3.setSelection(0);
                    ipAtivacao3.setEnabled(false);
                    vinteEQuatro3.setChecked(false);
                    vinteEQuatro3.setEnabled(false);
                    ipAtivacao3.setSelection(0);
                }
            }
        });

        ativos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ativos.isChecked()){
                    chkTrafoTrifasico.setEnabled(true);
                    chkTrafoMono.setEnabled(true);
                    religador.setEnabled(true);
                    medicao.setEnabled(true);
                    chFusivel.setEnabled(true);
                    chFaca.setEnabled(true);
                    chFusivelReligador.setEnabled(true);
                    chBanco.setEnabled(true);
                    ramalSubt.setEnabled(true);
                }else{
                    chkTrafoTrifasico.setChecked(false);
                    chkTrafoTrifasico.setEnabled(false);
                    chkTrafoMono.setChecked(false);
                    chkTrafoMono.setEnabled(false);
                    chkTrafoTrifasico.setChecked(false);
                    trafoTrifasico.setSelection(0);
                    trafoTrifasico.setEnabled(false);
                    trafoMono.setSelection(0);
                    trafoMono.setEnabled(false);
                    religador.setEnabled(false);
                    religador.setChecked(false);
                    medicao.setEnabled(false);
                    medicao.setChecked(false);
                    chFusivel.setEnabled(false);
                    chFusivel.setChecked(false);
                    chFaca.setEnabled(false);
                    chFaca.setChecked(false);
                    chFusivelReligador.setEnabled(false);
                    chFusivelReligador.setChecked(false);
                    chBanco.setEnabled(false);
                    chBanco.setChecked(false);
                    ramalSubt.setSelection(0);
                    ramalSubt.setEnabled(false);
                }
            }
        });

        chkTrafoTrifasico.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(chkTrafoTrifasico.isChecked()){
                    chkTrafoMono.setChecked(false);
                    trafoMono.setSelection(0);
                    trafoMono.setEnabled(false);
                    trafoTrifasico.setEnabled(true);
                }else{
                    trafoTrifasico.setSelection(0);
                    trafoTrifasico.setEnabled(false);
                }

            }
        });
        chkTrafoMono.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(chkTrafoMono.isChecked()){
                    chkTrafoTrifasico.setChecked(false);
                    trafoTrifasico.setSelection(0);
                    trafoTrifasico.setEnabled(false);
                    trafoMono.setEnabled(true);
                }else{
                    trafoMono.setSelection(0);
                    trafoMono.setEnabled(false);
                }

            }
        });
        /*trafoTrifasico.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                trafoTrifasico.setFocusable(true);
                return false;
            }
        });*/


        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormularioDAO formularioDAO = new FormularioDAO(requireActivity().getApplicationContext());
                    Formulario formulario = new Formulario();
                //LOCALIZAÇÂO

                formulario.setEndereco(Objects.requireNonNull(endereco.getText()).toString());
                setLista(formulario,municipio, "municipio");
                formulario.setLatitude(Objects.requireNonNull(latitude.getText()).toString());
                formulario.setLongitude(Objects.requireNonNull(longitude.getText()).toString());
                setLista(formulario, alturaCarga, "alturaCarga");


                //CARACTERISTICAS FÍSICAS

                setLista(formulario,tipoPoste, "tipoPoste");
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
                formulario.setObservacaoFisicas(Objects.requireNonNull(observacaoFisicas.getText()).toString());


                //ILUMINAÇÃO

                if (ip.isChecked()) {
                    formulario.setIp("Sim");
                } else {
                    formulario.setIp("Não");
                }
                setLista(formulario,ipEstrutura,"ipEstrutura");
                formulario.setQuantidadeLampada(Objects.requireNonNull(quantidadeLampada.getText().toString()));
                setLista(formulario, tipoPot, "tipoPot");
                formulario.setPotReator(Objects.requireNonNull(potReator.getText()).toString());
                setLista(formulario, ipAtivacao, "ipAtivacao");
                if (vinteEQuatro.isChecked()) {
                    formulario.setVinteEQuatro("Sim");
                } else {
                    formulario.setVinteEQuatro("Não");
                }
                formulario.setQuantidade24H(Objects.requireNonNull(quantidade24H.getText().toString()));

                if (ip2.isChecked()) {
                    formulario.setIp2("Sim");
                } else {
                    formulario.setIp2("Não");
                }
                setLista(formulario,ipEstrutura2,"ipEstrutura2");
                formulario.setQuantidadeLampada2(Objects.requireNonNull(quantidadeLampada2.getText().toString()));
                setLista(formulario, tipoPot2, "tipoPot2");
                formulario.setPotReator2(Objects.requireNonNull(potReator2.getText()).toString());
                setLista(formulario, ipAtivacao2, "ipAtivacao2");
                if (vinteEQuatro2.isChecked()) {
                    formulario.setVinteEQuatro2("Sim");
                } else {
                    formulario.setVinteEQuatro2("Não");
                }
                formulario.setQuantidade24H2(Objects.requireNonNull(quantidade24H2.getText().toString()));

                if (ip3.isChecked()) {
                    formulario.setIp3("Sim");
                } else {
                    formulario.setIp3("Não");
                }
                setLista(formulario,ipEstrutura3,"ipEstrutura3");
                formulario.setQuantidadeLampada3(Objects.requireNonNull(quantidadeLampada3.getText().toString()));
                setLista(formulario, tipoPot3, "tipoPot3");
                formulario.setPotReator3(Objects.requireNonNull(potReator3.getText()).toString());
                setLista(formulario, ipAtivacao3, "ipAtivacao3");
                if (vinteEQuatro3.isChecked()) {
                    formulario.setVinteEQuatro3("Sim");
                } else {
                    formulario.setVinteEQuatro3("Não");
                }
                formulario.setQuantidade24H3(Objects.requireNonNull(quantidade24H3.getText().toString()));
                formulario.setObservacaoIP(Objects.requireNonNull(observacaoIP.getText().toString()));


                //TRAFO

                if (ativos.isChecked()) {
                    formulario.setAtivos("Sim");
                } else {
                    formulario.setAtivos("Não");
                }
                if (chkTrafoTrifasico.isChecked()) {
                    formulario.setChkTrafoTrifasico("Sim");
                } else {
                    formulario.setChkTrafoTrifasico("Não");
                }
                if (chkTrafoMono.isChecked()) {
                    formulario.setChkTrafoMono("Sim");
                } else {
                    formulario.setChkTrafoMono("Não");
                }
                setLista(formulario,trafoTrifasico,"trafoTrifasico");
                setLista(formulario,trafoMono,"trafoMono");
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
                if (chBanco.isChecked()) {
                    formulario.setBanco("Sim");
                } else {
                    formulario.setBanco("Não");
                }
                if (chFusivelReligador.isChecked()) {
                    formulario.setChFusivelReligador("Sim");
                } else {
                    formulario.setChFusivelReligador("Não");
                }
                setLista(formulario,ramalSubt,"ramalSubt");
                formulario.setObservacaoAtivos(Objects.requireNonNull(observacaoAtivos.getText()).toString());

                //MUTUO
                if (mutuo.isChecked()) {
                    formulario.setAtivos("Sim");
                } else {
                    formulario.setAtivos("Não");
                }
                setLista(formulario,quantidadeOcupantes,"quantidadeOcupantes");

                formulario.setQuantidadeCabos(Objects.requireNonNull(quantidadeCabos.getText()).toString());
                setLista(formulario,tipoCabo,"tipoCabo");
                formulario.setNome(Objects.requireNonNull(nome.getText()).toString());
                setLista(formulario,finalidade,"finalidade");
                setLista(formulario,ceans,"ceans");
                setLista(formulario,tar,"tar");
                setLista(formulario,reservaTec,"reservaTec");
                setLista(formulario,backbone,"backbone");
                formulario.setDescricaoIrregularidade(Objects.requireNonNull(descricaoIrregularidade.getText()).toString());
                formulario.setObservacaoMutuo(Objects.requireNonNull(observacaoMutuo.getText()).toString());

                formulario.setQuantidadeCabos2(Objects.requireNonNull(quantidadeCabos2.getText()).toString());
                setLista(formulario,tipoCabo2,"tipoCabo2");
                formulario.setNome2(Objects.requireNonNull(nome2.getText()).toString());
                setLista(formulario,finalidade2,"finalidade2");
                setLista(formulario,ceans2,"ceans2");
                setLista(formulario,tar2,"tar2");
                setLista(formulario,reservaTec2,"reservaTec2");
                setLista(formulario,backbone2,"backbone2");
                formulario.setDescricaoIrregularidade2(Objects.requireNonNull(descricaoIrregularidade2.getText()).toString());
                formulario.setObservacaoMutuo2(Objects.requireNonNull(observacaoMutuo2.getText()).toString());

                formulario.setQuantidadeCabos3(Objects.requireNonNull(quantidadeCabos3.getText()).toString());
                setLista(formulario,tipoCabo3,"tipoCabo3");
                formulario.setNome3(Objects.requireNonNull(nome3.getText()).toString());
                setLista(formulario,finalidade3,"finalidade3");
                setLista(formulario,ceans3,"ceans3");
                setLista(formulario,tar3,"tar3");
                setLista(formulario,reservaTec3,"reservaTec3");
                setLista(formulario,backbone3,"backbone3");
                formulario.setDescricaoIrregularidade3(Objects.requireNonNull(descricaoIrregularidade3.getText()).toString());
                formulario.setObservacaoMutuo3(Objects.requireNonNull(observacaoMutuo3.getText()).toString());

                formulario.setQuantidadeCabos4(Objects.requireNonNull(quantidadeCabos4.getText()).toString());
                setLista(formulario,tipoCabo4,"tipoCabo4");
                formulario.setNome4(Objects.requireNonNull(nome4.getText()).toString());
                setLista(formulario,finalidade4,"finalidade4");
                setLista(formulario,ceans4,"ceans4");
                setLista(formulario,tar4,"tar4");
                setLista(formulario,reservaTec4,"reservaTec4");
                setLista(formulario,backbone4,"backbone4");
                formulario.setDescricaoIrregularidade4(Objects.requireNonNull(descricaoIrregularidade4.getText()).toString());
                formulario.setObservacaoMutuo4(Objects.requireNonNull(observacaoMutuo4.getText()).toString());

                formulario.setQuantidadeCabos5(Objects.requireNonNull(quantidadeCabos5.getText()).toString());
                setLista(formulario,tipoCabo5,"tipoCabo5");
                formulario.setNome5(Objects.requireNonNull(nome5.getText()).toString());
                setLista(formulario,finalidade5,"finalidade5");
                setLista(formulario,ceans5,"ceans5");
                setLista(formulario,tar5,"tar5");
                setLista(formulario,reservaTec5,"reservaTec5");
                setLista(formulario,backbone5,"backbone5");
                formulario.setDescricaoIrregularidade5(Objects.requireNonNull(descricaoIrregularidade5.getText()).toString());
                formulario.setObservacaoMutuo5(Objects.requireNonNull(observacaoMutuo5.getText()).toString());

                //VEGETAÇÃO
                formulario.setDistaciaBaixa(Objects.requireNonNull(distaciaBaixa.getText()).toString());
                formulario.setDistanciaMedia(Objects.requireNonNull(distanciaMedia.getText()).toString());
                formulario.setObservacaoVegetacao(Objects.requireNonNull(observacaoVegetacao.getText()).toString());



                formulario.setPlacaIdent(placaIdent.getSelectedItem().toString());
                formulario.setDimensaoVegetacao(dimensaoVegetacao.getSelectedItem().toString());



                if (descidaCabos.isChecked()) {
                    formulario.setDescidaCabos("Sim");
                } else {
                    formulario.setDescidaCabos("Não");
                }
                formulario.setNome(Objects.requireNonNull(nome.getText()).toString());
                if (formularioAtual != null) {
                    formulario.setId(formularioAtual.getId());
                    formulario.setData(formularioAtual.getData());
                    if (formularioDAO.atualizar(formulario)) {
                        CadastradosFragment cadastradosFragment = new CadastradosFragment();

                        FragmentManager fm = getParentFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();
                        transaction.replace(R.id.nav_host_fragment, cadastradosFragment);
                        transaction.commit();
                        Toast.makeText(requireActivity().getApplicationContext(), "Sucesso ao atualizar formulário", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireActivity().getApplicationContext(), "Erro ao atualizar formulário", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    String thisDayText, thisMonthText, thisYearText;
                    //region Inicialização da data
                    Calendar calendar = Calendar.getInstance();

                    int thisYear = calendar.get(Calendar.YEAR);
                    thisYearText = String.valueOf(thisYear);

                    int thisMonth = calendar.get(Calendar.MONTH) + 1;
                    if (thisMonth < 9) {
                        thisMonthText = "0" + thisMonth;
                    } else {
                        thisMonthText = String.valueOf(thisMonth);
                    }

                    int thisDay = calendar.get(Calendar.DAY_OF_MONTH);
                    if (thisDay < 9) {
                        thisDayText = "0" + thisDay;
                    } else {
                        thisDayText = String.valueOf(thisDay);
                    }
                    String data = thisDayText + "/" + thisMonthText + "/" + thisYearText;
                    //endregion
                    formulario.setData(data);
                    if (formularioDAO.salvar(formulario)) {
                        CadastradosFragment cadastradosFragment = new CadastradosFragment();

                        FragmentManager fm = getParentFragmentManager();
                        FragmentTransaction transaction = fm.beginTransaction();
                        transaction.replace(R.id.nav_host_fragment, cadastradosFragment);
                        transaction.commit();
                        Toast.makeText(requireActivity().getApplicationContext(), "Sucesso ao salvar formulário", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireActivity().getApplicationContext(), "Erro ao salvar formulário", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return root;
    }
//Pega o valor do spinner e coloca um "-" caso, o usuário não tenha escolhido nenhuma opção.
    public void setLista(Formulario formulario, Spinner spinner, String atributo){
        if(spinner.getSelectedItem().toString().equals(spinner.getItemAtPosition(0).toString())){
            formulario.GenericSetter(atributo,"-");
        }else{
            formulario.GenericSetter(atributo,spinner.getSelectedItem().toString());
        }
    }
}