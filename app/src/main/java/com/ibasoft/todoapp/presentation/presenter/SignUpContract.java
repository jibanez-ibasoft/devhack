package com.ibasoft.todoapp.presentation.presenter;

/**
 * Created by JorgeIbanez on 30/11/2017.
 */
public interface SignUpContract {

    interface View {
        void goToLoginFragment();

        void goToMainActivity();

        void showMessageError(Exception error);
    }

    //Interface que implementara el presenter
    interface UserActionsLister {
        void onSignUp(String fullname, String email, String password);
    }
}
