package com.ibasoft.todoapp.presentation.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ibasoft.todoapp.R;
import com.ibasoft.todoapp.presentation.presenter.AuthContract;
import com.ibasoft.todoapp.presentation.presenter.AuthPresenter;
import com.ibasoft.todoapp.presentation.view.fragment.LoginFragment;

public class AuthActivity extends AppCompatActivity implements AuthContract.View{

    private AuthContract.UserActionsLister mActionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mActionListener = new AuthPresenter(this);
        mActionListener.goToFirstFragment();
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack){
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,  fragment);

        //Permite guardar el fragment en la pila para la funcionalidad back
        if(addToBackStack){
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void goToLoginFragment() {
        replaceFragment(LoginFragment.getInstance(), true);
    }

    @Override
    public void goToMainActivity() {

    }
}
