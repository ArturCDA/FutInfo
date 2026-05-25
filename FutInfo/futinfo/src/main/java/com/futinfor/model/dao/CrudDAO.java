package com.futinfor.model.dao;

import java.util.List;

public interface CrudDAO<T> {

    void salvar(T entidade);

    void atualizar(T entidade);

    void deletar(Long id);

    List<T> listar();
}