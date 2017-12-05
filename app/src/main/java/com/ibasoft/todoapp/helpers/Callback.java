package com.ibasoft.todoapp.helpers;

import com.ibasoft.todoapp.domain.model.User;

/**
 * Created by JorgeIbanez on 2/12/2017.
 */
public interface Callback<T> {

    //Tipo generico, devuelve cualquier información (Objeto, arreglo, etc)
    void success(T result);

    void error(Exception e);
}
