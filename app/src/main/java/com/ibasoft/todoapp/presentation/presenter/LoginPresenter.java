package com.ibasoft.todoapp.presentation.presenter;

import android.util.Log;

import com.ibasoft.todoapp.domain.model.User;
import com.ibasoft.todoapp.domain.usecase.UserUseCase;
import com.ibasoft.todoapp.domain.usecase.impl.UserUseCaseImpl;
import com.ibasoft.todoapp.helpers.Callback;

/**
 * Created by JorgeIbanez on 30/11/2017.
 */
public class LoginPresenter implements LoginContract.UserActionsLister{

    private LoginContract.View view;
    private UserUseCase userUseCase;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        this.userUseCase = new UserUseCaseImpl();
    }

    @Override
    public void onLogin(String email, String password, boolean remember) {
        Log.d("IBASOFT", "onLogin");
        userUseCase.login(email, password, remember, new Callback<User>() {
            @Override
            public void success(User result) {
                Log.d("IBASOFT", "Success onLogin");
                view.goToMainActivity();
            }

            @Override
            public void error(Exception error) {
                Log.d("IBASOFT", "Error onLogin");
                view.showMessageError(error);
            }
        });
    }
}
