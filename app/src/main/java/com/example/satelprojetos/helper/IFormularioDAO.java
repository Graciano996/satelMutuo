package com.example.satelprojetos.helper;

import com.example.satelprojetos.model.Formulario;

import java.io.FileOutputStream;
import java.util.List;

public interface IFormularioDAO{
    public boolean salvar(Formulario formulario);
    public boolean atualizar(Formulario formulario);
    public boolean deletar(Formulario formulario);
    public List<Formulario> listar();


}
