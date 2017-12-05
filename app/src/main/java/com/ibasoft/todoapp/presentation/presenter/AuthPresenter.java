package com.ibasoft.todoapp.presentation.presenter;

/**
 * Created by JorgeIbanez on 30/11/2017.
 */
public class AuthPresenter implements AuthContract.UserActionsLister{

    private AuthContract.View view;

    public AuthPresenter(AuthContract.View view){
        this.view = view;
    }

    @Override
    public void goToFirstFragment() {
        view.goToLoginFragment();
    }
}
