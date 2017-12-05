package com.ibasoft.todoapp.repository;

import com.ibasoft.todoapp.domain.model.User;
import com.ibasoft.todoapp.helpers.Callback;

/**
 * Created by JorgeIbanez on 30/11/2017.
 */
public interface UserRepository {

    void login(String email, String password, Callback<User> callback);

    void signUp(User user, Callback<User> callback);

    void recoveryPassword(String email, Callback<Boolean> callback);
}
