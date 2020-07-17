package com.example.satelprojetos.ui.cadastrados;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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

import com.example.satelprojetos.R;
import com.example.satelprojetos.activity.DrawerActivity;
import com.example.satelprojetos.adapter.FormularioAdapter;
import com.example.satelprojetos.helper.FormularioDAO;
import com.example.satelprojetos.helper.RecyclerItemClickListener;
import com.example.satelprojetos.model.Formulario;
import com.example.satelprojetos.ui.cadastro.CadastroFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CadastradosFragment extends Fragment {

    private CadastradosViewModel cadastradosViewModel;
    private RecyclerView recyclerCadastrados;
    private FormularioAdapter formularioAdapter;
    private Formulario formularioSelecionado;
    private Button btnEnviarCadastrados;
    private List<Formulario> listaFormulario = new ArrayList<>();
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_cadastrados, container, false);
        //configurar RecyclerView
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

                                FragmentManager fm = getFragmentManager();
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
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("sessionId",0);
                String sessionId = sharedPreferences.getString("Id","");
                Log.i("sessionId2", sessionId);
                FormularioDAO formularioDAO = new FormularioDAO(getActivity().getApplicationContext());
                listaFormulario = formularioDAO.listar();
                DatabaseReference formularios = referencia.child("usuarios").child(sessionId).child("formulario");
                for(int i=0; i<listaFormulario.size();i++){
                    formularios.child(listaFormulario.get(i).getId().toString()).setValue(listaFormulario.get(i));
                }
            }
        });

        return root;

    }


    public void carregarFormulariosCadastrados() {
        //Listar Formulários
        FormularioDAO formularioDAO = new FormularioDAO(getActivity().getApplicationContext());
        listaFormulario = formularioDAO.listar();
        Log.i("TAG",listaFormulario.toString());

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
}