package com.ibasoft.todoapp.presentation.presenter;

/**
 * Created by JorgeIbanez on 30/11/2017.
 */
public interface LoginContract {

    interface View {
        void goToSignUpFragment();

        void goToMainActivity();

        void showMessageError(Exception error);
    }

    //Interface que implementara el presenter
    interface UserActionsLister {
        void onLogin(String fullname, String email, boolean remember);
    }

}
