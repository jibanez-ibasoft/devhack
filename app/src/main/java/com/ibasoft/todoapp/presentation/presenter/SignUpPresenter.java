package com.ibasoft.todoapp.presentation.presenter;

import android.util.Log;
import android.widget.Toast;

import com.ibasoft.todoapp.domain.model.User;
import com.ibasoft.todoapp.domain.usecase.UserUseCase;
import com.ibasoft.todoapp.domain.usecase.impl.UserUseCaseImpl;
import com.ibasoft.todoapp.helpers.Callback;

/**
 * Created by JorgeIbanez on 30/11/2017.
 */
public class SignUpPresenter implements SignUpContract.UserActionsLister {

    private SignUpContract.View view;
    private UserUseCase userUseCase;

    public SignUpPresenter(SignUpContract.View view) {
        this.view = view;
        this.userUseCase = new UserUseCaseImpl();
    }

    @Override
    public void onSignUp(String fullname, String email, String password) {
        userUseCase.signUp(fullname, email, password, new Callback<User>() {
            @Override
            public void success(User result) {
                Log.d("IBASOFT", "Success onSignUp");
                view.goToMainActivity();
            }

            @Override
            public void error(Exception error) {
                Log.d("IBASOFT", "Error onSignUp");
                view.showMessageError(error);
            }
        });
    }
}
