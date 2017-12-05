package com.ibasoft.todoapp.presentation.presenter;

/**
 * Created by JorgeIbanez on 4/12/2017.
 */
public interface RecoveryContract {

    interface View{
        void showMessageError(Exception error);
    }

    interface UserActionsLister {
        void onRecoveryPassword(String email);
    }
}

