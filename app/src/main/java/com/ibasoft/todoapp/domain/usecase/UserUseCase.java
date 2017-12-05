package com.ibasoft.todoapp.domain.usecase;

import com.ibasoft.todoapp.domain.model.User;
import com.ibasoft.todoapp.helpers.Callback;

/**
 * Created by JorgeIbanez on 2/12/2017.
 */
public interface UserUseCase {
    void login(String email, String password, boolean remember, Callback<User> callback);

    void signUp(String fullname, String email, String password, Callback<User> callback);

    void recoveryPassword(String email, Callback<Boolean> callback);
}
