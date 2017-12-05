package com.ibasoft.todoapp.presentation.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.ibasoft.todoapp.R;
import com.ibasoft.todoapp.helpers.Utilities;
import com.ibasoft.todoapp.presentation.presenter.LoginContract;
import com.ibasoft.todoapp.presentation.presenter.LoginPresenter;
import com.ibasoft.todoapp.presentation.view.activity.AuthActivity;
import com.ibasoft.todoapp.presentation.view.activity.MainActivity;

public class LoginFragment extends Fragment implements LoginContract.View, View.OnClickListener {

    private LoginContract.UserActionsLister mActionListener;
    private TextInputLayout tilEmail;
    private TextInputLayout tilPassword;
    private TextView tvForgotPassword;
    private Switch swRemember;
    private Button btnStart;
    private Button btnNotHaveAccount;

    public LoginFragment() {}

    public static LoginFragment getInstance(){
        return new LoginFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mActionListener = new LoginPresenter(this);

        tilEmail = view.findViewById(R.id.tilEmail);
        tilPassword = view.findViewById(R.id.tilPassword);
        tvForgotPassword = view.findViewById(R.id.tvForgotPassword);
        swRemember = view.findViewById(R.id.swRemember);
        btnStart = view.findViewById(R.id.btnStart);
        btnNotHaveAccount = view.findViewById(R.id.btnNotHaveAccount);

        btnNotHaveAccount.setOnClickListener(this);
        btnStart.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.btnStart:
                onLogin();
                break;
            case R.id.btnNotHaveAccount:
                goToSignUpFragment();
                break;
            case  R.id.tvForgotPassword:
                goToRecoveryPassword();
                break;
        }
    }

    @Override
    public void goToSignUpFragment() {
        AuthActivity authActivity = (AuthActivity) getActivity();
        authActivity.replaceFragment(SignUpFragment.getInstance(), true);
    }

    @Override
    public void goToMainActivity() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }

    public void goToRecoveryPassword(){
        RecoveryPasswordFragment recoveryPasswordFragment = RecoveryPasswordFragment.getInstance();
        recoveryPasswordFragment.show(getFragmentManager(), null);
    }

    @Override
    public void showMessageError(Exception error) {
        Snackbar.make(getView(), error.getMessage(), Snackbar.LENGTH_LONG).show();
    }

    private void  onLogin(){
        try{

            boolean result = true;

            String email = tilEmail.getEditText().getText().toString();
            String password = tilPassword.getEditText().getText().toString();
            boolean remember = swRemember.isChecked();

            Log.d("IBASOFT", "onLogin " + email);
            Log.d("IBASOFT", "onLogin " + password);

            if(Utilities.isEmpty(email)){
                tilEmail.setError(getString(R.string.is_required));
                tilEmail.setErrorEnabled(true);
                result = false;
            }else{
                tilEmail.setError(null);
                tilEmail.setErrorEnabled(true);
            }

            if(Utilities.isEmpty(password)){
                tilPassword.setError(getString(R.string.is_required));
                tilPassword.setErrorEnabled(true);
                result = false;
            }else{
                tilPassword.setError(null);
                tilPassword.setErrorEnabled(true);
            }

            if(result){
                mActionListener.onLogin(email, password, remember);
            }
        }catch (Exception e){

        }
    }
}
