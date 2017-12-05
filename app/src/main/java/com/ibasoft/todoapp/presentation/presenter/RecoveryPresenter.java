package com.ibasoft.todoapp.presentation.presenter;

import android.util.Log;

import com.ibasoft.todoapp.domain.model.User;
import com.ibasoft.todoapp.domain.usecase.UserUseCase;
import com.ibasoft.todoapp.domain.usecase.impl.UserUseCaseImpl;
import com.ibasoft.todoapp.helpers.Callback;


/**
 * Created by JorgeIbanez on 4/12/2017.
 */
public class RecoveryPresenter  implements RecoveryContract.UserActionsLister {

    private RecoveryContract.View view;
    private UserUseCase userUseCase;

    public RecoveryPresenter(RecoveryContract.View view) {
        this.view = view;
        this.userUseCase = new UserUseCaseImpl();
    }

    @Override
    public void onRecoveryPassword(String email) {
        userUseCase.recoveryPassword(email, new Callback<Boolean>() {
            @Override
            public void success(Boolean validate) {
            }

            @Override
            public void error(Exception error) {

            }
        });
    }
}
