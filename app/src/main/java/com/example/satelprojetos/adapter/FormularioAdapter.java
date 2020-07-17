package com.example.satelprojetos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.satelprojetos.R;
import com.example.satelprojetos.model.Formulario;

import org.w3c.dom.Text;

import java.util.List;

public class FormularioAdapter extends RecyclerView.Adapter<FormularioAdapter.MyViewHolder> {
    private List<Formulario> listaFormulario;

    public FormularioAdapter(List<Formulario> lista) {
        this.listaFormulario = lista;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                                        .inflate(R.layout.lista_formulario_adapter, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Formulario formulario = listaFormulario.get(position);
        holder.formularioNome.setText(formulario.getNome());
        holder.formularioData.setText(formulario.getData());
        holder.formularioEndereco.setText(formulario.getEndereco());
    }

    @Override
    public int getItemCount() {
        return this.listaFormulario.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView formularioNome, formularioData, formularioEndereco;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            formularioNome = itemView.findViewById(R.id.cadastradosNome);
            formularioData = itemView.findViewById(R.id.cadastradosData);
            formularioEndereco= itemView.findViewById(R.id.cadastradosEndereco);
        }
    }
}
