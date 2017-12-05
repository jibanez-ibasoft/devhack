package com.ibasoft.todoapp.domain.usecase.impl;

import android.util.Log;

import com.ibasoft.todoapp.domain.model.User;
import com.ibasoft.todoapp.domain.usecase.UserUseCase;
import com.ibasoft.todoapp.helpers.Callback;
import com.ibasoft.todoapp.repository.UserRepository;
import com.ibasoft.todoapp.repository.impl.UserFirebaseRepository;

/**
 * Created by JorgeIbanez on 2/12/2017.
 */

public class UserUseCaseImpl implements UserUseCase {

    private UserRepository userRepository;

    public UserUseCaseImpl() {
        this.userRepository = new UserFirebaseRepository();
    }

    @Override
    public void login(String email, String password, final boolean remember, final Callback<User> callback) {
        Log.d("IBASOFT", "onLogin UserUseCaseImpl");
        userRepository.login(email, password, new Callback<User>() {

            @Override
            public void success(User user) {
                if(user != null && remember){
                    //TODO Almacenar Email sharedpreferences
                }
                Log.d("IBASOFT", "onLogin UserUseCaseImpl success");
                callback.success(user);
            }

            @Override
            public void error(Exception error) {
                //TODO Excepciones personalizadas
                Log.d("IBASOFT", "onLogin UserUseCaseImpl error");
                callback.error(error);
            }
        });
    }

    @Override
    public void signUp(String fullname, String email, String password, final Callback<User> callback) {
        User user = new User(fullname, email, password);
        userRepository.signUp(user, new Callback<User>() {
            @Override
            public void success(User user) {
                callback.success(user);
            }

            @Override
            public void error(Exception error) {
                callback.error(error);
            }
        });
    }

    @Override
    public void recoveryPassword(String email,final Callback<Boolean> callback) {
        userRepository.recoveryPassword(email, new Callback<Boolean>() {

            @Override
            public void success(Boolean validate) {
                callback.success(validate);
            }

            @Override
            public void error(Exception error) {
                callback.error(error);
            }
        });
    }
}
