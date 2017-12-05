package com.ibasoft.todoapp.presentation.presenter;

/**
 * Created by JorgeIbanez on 30/11/2017.
 */
public interface AuthContract {

    //Interface que implementara el Fragment o Activity
    interface View{
        void goToLoginFragment();

        void goToMainActivity();
    }

    //Interface que implementara el presenter
    interface UserActionsLister{
        void goToFirstFragment();
    }
}
