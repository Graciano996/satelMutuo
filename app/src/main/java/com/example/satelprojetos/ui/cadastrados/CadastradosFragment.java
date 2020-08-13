package com.example.satelprojetos.ui.cadastrados;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.satelprojetos.R;
import com.example.satelprojetos.adapter.FormularioAdapter;
import com.example.satelprojetos.config.ConfiguracaoFirebase;
import com.example.satelprojetos.helper.Base64Custom;
import com.example.satelprojetos.helper.EnviadoDAO;
import com.example.satelprojetos.helper.FormularioDAO;
import com.example.satelprojetos.helper.RecyclerItemClickListener;
import com.example.satelprojetos.model.Formulario;
import com.example.satelprojetos.ui.cadastro.CadastroFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CadastradosFragment extends Fragment {

    private CadastradosViewModel cadastradosViewModel;
    private RecyclerView recyclerCadastrados;
    private FormularioAdapter formularioAdapter;
    private Formulario formularioSelecionado;
    Button btnEnviarCadastrados;
    private List<Formulario> listaFormulario = new ArrayList<>();
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth autentificacao;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cadastrados, container, false);
        //configurar RecyclerView
        ProgressDialog loading;
        btnEnviarCadastrados = root.findViewById(R.id.BtnEnviarCadastrados);
        recyclerCadastrados = root.findViewById(R.id.recyclerCadastrados);
        recyclerCadastrados.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getActivity().getApplicationContext(),
                        recyclerCadastrados,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Formulario formularioSelecionado = listaFormulario.get(position);
                                CadastroFragment cadastroFragment = new CadastroFragment();
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("formularioSelecionado", formularioSelecionado);
                                cadastroFragment.setArguments(bundle);
                                Log.i("TAGB",formularioSelecionado.toString());
                                NavigationView navigationView = requireActivity().findViewById(R.id.nav_view);
                                navigationView.setCheckedItem(R.id.nav_cadastro);
                                FragmentManager fm = getParentFragmentManager();
                                FragmentTransaction transaction = fm.beginTransaction();
                                transaction.replace(R.id.nav_host_fragment, cadastroFragment).addToBackStack(null);
                                transaction.commit();

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                formularioSelecionado = listaFormulario.get(position);


                                final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity(), R.style.LightDialogTheme);
                                dialog.setTitle("Confirmar exclusão?");

                                dialog.setMessage("Deseja excluir o formulário: " + formularioSelecionado.getNome() + " ?");
                                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        FormularioDAO formularioDAO = new FormularioDAO(getActivity().getApplicationContext());
                                        if(formularioDAO.deletar(formularioSelecionado)){
                                            carregarFormulariosCadastrados();
                                            Toast.makeText(getActivity().getApplicationContext(), "Sucesso ao excluir formulário", Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(getActivity().getApplicationContext(), "Erro ao excluir formulário", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                                dialog.setNegativeButton("Não", null);
                                dialog.create();
                                dialog.show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );

        btnEnviarCadastrados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FormularioDAO formularioDAO = new FormularioDAO(getActivity().getApplicationContext());
                listaFormulario = formularioDAO.listar();
                autentificacao = ConfiguracaoFirebase.getFirebaseAuth();
                DatabaseReference formularios = referencia.child("usuarios").child(Base64Custom.codificarBase64(autentificacao.getCurrentUser().getEmail())).child("formulario");
                for(int i=0; i<listaFormulario.size();i++){
                    formularios.child(listaFormulario.get(i).getId().toString()).setValue(listaFormulario.get(i));
                }
                /*for(int a=0; a<listaFormulario.size();a++){
                    EnviadoDAO enviadoDAO = new EnviadoDAO(getActivity().getApplicationContext());
                                enviarParaGS(listaFormulario.get(a), autentificacao.getCurrentUser().getEmail(), a);
                                Log.i("TAGB", "entrei no " + a);
                                enviadoDAO.salvar(listaFormulario.get(a));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }*/
                enviarParaGS(listaFormulario, autentificacao.getCurrentUser().getEmail());


                Toast.makeText(getActivity().getApplicationContext(), "Sucesso ao enviar formulários", Toast.LENGTH_SHORT).show();
            }
        });

        return root;

    }


    public void carregarFormulariosCadastrados() {
        //Listar Formulários
        FormularioDAO formularioDAO = new FormularioDAO(getActivity().getApplicationContext());
        listaFormulario = formularioDAO.listar();
        Log.i("TAG",String.valueOf(listaFormulario.size()));
        if(listaFormulario.size() <= 0){
            btnEnviarCadastrados.setEnabled(false);
            Toast.makeText(requireActivity().getApplicationContext(), String.valueOf(listaFormulario.size()), Toast.LENGTH_SHORT).show();
        }
        else {
            btnEnviarCadastrados.setEnabled(true);
        }

        //Configurar Adapter
        formularioAdapter = new FormularioAdapter(listaFormulario);

        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerCadastrados.setLayoutManager(layoutManager);
        recyclerCadastrados.setHasFixedSize(true);
        recyclerCadastrados.addItemDecoration(new DividerItemDecoration(getActivity().getApplicationContext(), LinearLayout.VERTICAL));
        recyclerCadastrados.setAdapter(formularioAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        carregarFormulariosCadastrados();
    }

    public void enviarParaGS(final List<Formulario> formularioLista, final String email){
        final EnviadoDAO enviadoDAO = new EnviadoDAO(getActivity().getApplicationContext());
        final FormularioDAO formularioDAO = new FormularioDAO(getActivity().getApplicationContext());
        final ProgressDialog loading = ProgressDialog.show(requireContext(),"Enviando dados para banco de dados","Por favor espere");
            StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbzZJUnvHaDYfO13T9t7NyhLcweYuuYp38D1n0JzH0Hs4FVR0mrO/exec",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            loading.dismiss();
                            carregarFormulariosCadastrados();
                            Toast.makeText(requireActivity().getApplicationContext(), response, Toast.LENGTH_LONG).show();


                        }
                    },
                    new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.i("TAGB", "ENTREI AQUI1");
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> parmas = new HashMap<>();
                    //here we pass params
                    Log.i("ERRO2", String.valueOf(formularioLista.size()));
                    parmas.put("action", "addItem");
                    parmas.put("email", email);
                    parmas.put("urlImagem", formularioLista.get(0).getUrlImagem());
                    parmas.put("urlImagem2", formularioLista.get(0).getUrlImagem2());
                    parmas.put("urlImagem3", formularioLista.get(0).getUrlImagem3());
                    parmas.put("data", formularioLista.get(0).getData());
                    parmas.put("municipio", formularioLista.get(0).getMunicipio());
                    parmas.put("endereco", formularioLista.get(0).getEndereco());
                    Log.i("LATIDUDE", formularioLista.get(0).getLatitude());
                    parmas.put("latitude", "'" + formularioLista.get(0).getLatitude());
                    parmas.put("longitude", "'" + formularioLista.get(0).getLongitude());
                    parmas.put("tipoPoste", formularioLista.get(0).getTipoPoste());
                    parmas.put("alturaCarga", formularioLista.get(0).getAlturaCarga());
                    parmas.put("normal", formularioLista.get(0).getNormal());
                    parmas.put("ferragemExposta", formularioLista.get(0).getFerragemExposta());
                    parmas.put("fletido", formularioLista.get(0).getFletido());
                    parmas.put("danificado", formularioLista.get(0).getDanificado());
                    parmas.put("abalroado", formularioLista.get(0).getAbalroado());
                    parmas.put("trincado", formularioLista.get(0).getTrincado());
                    parmas.put("observacaoFisicas", formularioLista.get(0).getObservacaoFisicas());
                    parmas.put("ip", formularioLista.get(0).getIp());
                    parmas.put("ipEstrutura", formularioLista.get(0).getIpEstrutura());
                    parmas.put("quantidadeLampada", formularioLista.get(0).getQuantidadeLampada());
                    parmas.put("tipoPot", formularioLista.get(0).getTipoPot());
                    parmas.put("potReator", formularioLista.get(0).getPotReator());
                    parmas.put("ipAtivacao", formularioLista.get(0).getIpAtivacao());
                    parmas.put("vinteEQuatro", formularioLista.get(0).getVinteEQuatro());
                    parmas.put("quantidade24H", formularioLista.get(0).getQuantidade24H());

                    parmas.put("ip2", formularioLista.get(0).getIp2());
                    parmas.put("ipEstrutura2", formularioLista.get(0).getIpEstrutura2());
                    parmas.put("quantidadeLampada2", formularioLista.get(0).getQuantidadeLampada2());
                    parmas.put("tipoPot2", formularioLista.get(0).getTipoPot2());
                    parmas.put("potReator2", formularioLista.get(0).getPotReator2());
                    parmas.put("ipAtivacao2", formularioLista.get(0).getIpAtivacao2());
                    parmas.put("vinteEQuatro2", formularioLista.get(0).getVinteEQuatro2());
                    parmas.put("quantidade24H2", formularioLista.get(0).getQuantidade24H2());

                    parmas.put("ip3", formularioLista.get(0).getIp3());
                    parmas.put("ipEstrutura3", formularioLista.get(0).getIpEstrutura3());
                    parmas.put("quantidadeLampada3", formularioLista.get(0).getQuantidadeLampada3());
                    parmas.put("tipoPot3", formularioLista.get(0).getTipoPot3());
                    parmas.put("potReator3", formularioLista.get(0).getPotReator3());
                    parmas.put("ipAtivacao3", formularioLista.get(0).getIpAtivacao3());
                    parmas.put("vinteEQuatro3", formularioLista.get(0).getVinteEQuatro3());
                    parmas.put("quantidade24H3", formularioLista.get(0).getQuantidade24H3());
                    parmas.put("observacaoIp", formularioLista.get(0).getObservacaoIP());

                    parmas.put("ativos", formularioLista.get(0).getAtivos());
                    parmas.put("chkTrafoTrifasico", formularioLista.get(0).getChkTrafoTrifasico());
                    parmas.put("chkTrafoMono", formularioLista.get(0).getChkTrafoMono());
                    parmas.put("trafoTrifasico", formularioLista.get(0).getTrafoTrifasico());
                    parmas.put("trafoMono", formularioLista.get(0).getTrafoMono());
                    parmas.put("religador", formularioLista.get(0).getReligador());
                    parmas.put("medicao", formularioLista.get(0).getMedicao());
                    parmas.put("chFusivel", formularioLista.get(0).getChFusivel());
                    parmas.put("chFaca", formularioLista.get(0).getChFaca());
                    parmas.put("banco", formularioLista.get(0).getBanco());
                    parmas.put("chFusivelReligador", formularioLista.get(0).getChFusivelReligador());
                    parmas.put("ramalSubt", formularioLista.get(0).getRamalSubt());
                    parmas.put("outros", formularioLista.get(0).getOutros());
                    parmas.put("observacaoAtivos", formularioLista.get(0).getObservacaoAtivos());

                    parmas.put("mutuo", formularioLista.get(0).getMutuo());
                    parmas.put("quantidadeOcupantes", formularioLista.get(0).getQuantidadeOcupantes());

                    parmas.put("quantidadeCabos", formularioLista.get(0).getQuantidadeCabos());
                    parmas.put("tipoCabo", formularioLista.get(0).getTipoCabo());
                    parmas.put("nomeEmpresa", formularioLista.get(0).getNome());
                    parmas.put("finalidade", formularioLista.get(0).getFinalidade());
                    parmas.put("ceans", formularioLista.get(0).getCeans());
                    parmas.put("tar", formularioLista.get(0).getTar());
                    parmas.put("reservaTec", formularioLista.get(0).getReservaTec());
                    parmas.put("backbone", formularioLista.get(0).getBackbone());
                    parmas.put("placaIdent", formularioLista.get(0).getPlacaIdent());
                    parmas.put("descidaCabos", formularioLista.get(0).getDescidaCabos());
                    parmas.put("descricaoIrregularidade", formularioLista.get(0).getDescricaoIrregularidade());
                    parmas.put("observacaoMutuo", formularioLista.get(0).getObservacaoMutuo());

                    parmas.put("quantidadeCabos2", formularioLista.get(0).getQuantidadeCabos2());
                    parmas.put("tipoCabo2", formularioLista.get(0).getTipoCabo2());
                    parmas.put("quantidadeCabos2dois", formularioLista.get(0).getQuantidadeCabos2dois());
                    parmas.put("tipoCabo2dois", formularioLista.get(0).getTipoCabo2dois());
                    parmas.put("nomeEmpresa2", formularioLista.get(0).getNome2());
                    parmas.put("finalidade2", formularioLista.get(0).getFinalidade2());
                    parmas.put("ceans2", formularioLista.get(0).getCeans2());
                    parmas.put("tar2", formularioLista.get(0).getTar2());
                    parmas.put("reservaTec2", formularioLista.get(0).getReservaTec2());
                    parmas.put("backbone2", formularioLista.get(0).getBackbone2());
                    parmas.put("placaIdent2", formularioLista.get(0).getPlacaIdent2());
                    parmas.put("descidaCabos2", formularioLista.get(0).getDescidaCabos2());
                    parmas.put("descricaoIrregularidade2", formularioLista.get(0).getDescricaoIrregularidade2());
                    parmas.put("observacaoMutuo2", formularioLista.get(0).getObservacaoMutuo2());

                    parmas.put("quantidadeCabos3", formularioLista.get(0).getQuantidadeCabos3());
                    parmas.put("tipoCabo3", formularioLista.get(0).getTipoCabo3());
                    parmas.put("nomeEmpresa3", formularioLista.get(0).getNome3());
                    parmas.put("finalidade3", formularioLista.get(0).getFinalidade3());
                    parmas.put("ceans3", formularioLista.get(0).getCeans3());
                    parmas.put("tar3", formularioLista.get(0).getTar3());
                    parmas.put("reservaTec3", formularioLista.get(0).getReservaTec3());
                    parmas.put("backbone3", formularioLista.get(0).getBackbone3());
                    parmas.put("placaIdent3", formularioLista.get(0).getPlacaIdent3());
                    parmas.put("descidaCabos3", formularioLista.get(0).getDescidaCabos3());
                    parmas.put("descricaoIrregularidade3", formularioLista.get(0).getDescricaoIrregularidade3());
                    parmas.put("observacaoMutuo3", formularioLista.get(0).getObservacaoMutuo3());

                    parmas.put("quantidadeCabos4", formularioLista.get(0).getQuantidadeCabos4());
                    parmas.put("tipoCabo4", formularioLista.get(0).getTipoCabo4());
                    parmas.put("nomeEmpresa4", formularioLista.get(0).getNome4());
                    parmas.put("finalidade4", formularioLista.get(0).getFinalidade4());
                    parmas.put("ceans4", formularioLista.get(0).getCeans4());
                    parmas.put("tar4", formularioLista.get(0).getTar4());
                    parmas.put("reservaTec4", formularioLista.get(0).getReservaTec4());
                    parmas.put("backbone4", formularioLista.get(0).getBackbone4());
                    parmas.put("placaIdent4", formularioLista.get(0).getPlacaIdent4());
                    parmas.put("descidaCabos4", formularioLista.get(0).getDescidaCabos4());
                    parmas.put("descricaoIrregularidade4", formularioLista.get(0).getDescricaoIrregularidade4());
                    parmas.put("observacaoMutuo4", formularioLista.get(0).getObservacaoMutuo4());

                    parmas.put("quantidadeCabos5", formularioLista.get(0).getQuantidadeCabos5());
                    parmas.put("tipoCabo5", formularioLista.get(0).getTipoCabo5());
                    parmas.put("nomeEmpresa5", formularioLista.get(0).getNome5());
                    parmas.put("finalidade5", formularioLista.get(0).getFinalidade5());
                    parmas.put("ceans5", formularioLista.get(0).getCeans5());
                    parmas.put("tar5", formularioLista.get(0).getTar5());
                    parmas.put("reservaTec5", formularioLista.get(0).getReservaTec5());
                    parmas.put("backbone5", formularioLista.get(0).getBackbone5());
                    parmas.put("placaIdent5", formularioLista.get(0).getPlacaIdent5());
                    parmas.put("descidaCabos5", formularioLista.get(0).getDescidaCabos5());
                    parmas.put("descricaoIrregularidade5", formularioLista.get(0).getDescricaoIrregularidade5());
                    parmas.put("observacaoMutuo5", formularioLista.get(0).getObservacaoMutuo5());

                    parmas.put("vegetacao", formularioLista.get(0).getDimensaoVegetacao());
                    parmas.put("distanciaBaixa", formularioLista.get(0).getDistaciaBaixa());
                    parmas.put("distanciaMedia", formularioLista.get(0).getDistanciaMedia());
                    parmas.put("estadoArvore", formularioLista.get(0).getEstadoArvore());
                    parmas.put("quedaArvore", formularioLista.get(0).getQuedaArvore());
                    parmas.put("localArvore", formularioLista.get(0).getLocalArvore());
                    parmas.put("observacaoArvore", formularioLista.get(0).getObservacaoVegetacao());
                    parmas.put("tamanhoLista", String.valueOf(listaFormulario.size()));
                    if(enviadoDAO.salvar(formularioLista.get(0))){
                        if(formularioDAO.deletar(formularioLista.get(0))){
                            formularioLista.remove(0);
                        }
                    }



                    return parmas;
                }
            };

            int socketTimeOut = 50000;// u can change this .. here it is 50 seconds

            RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            stringRequest.setRetryPolicy(retryPolicy);
            Log.i("TAGB", "ENTREI AQUI 3");
            RequestQueue queue = Volley.newRequestQueue(requireContext());

            queue.add(stringRequest);
        }

}