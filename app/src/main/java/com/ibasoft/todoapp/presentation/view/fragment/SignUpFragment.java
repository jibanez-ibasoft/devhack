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

import com.ibasoft.todoapp.R;
import com.ibasoft.todoapp.helpers.Utilities;
import com.ibasoft.todoapp.presentation.presenter.SignUpContract;
import com.ibasoft.todoapp.presentation.presenter.SignUpPresenter;
import com.ibasoft.todoapp.presentation.view.activity.MainActivity;

public class SignUpFragment extends Fragment implements SignUpContract.View, View.OnClickListener{

    private SignUpContract.UserActionsLister mActionsListener;
    private TextInputLayout tilFullName;
    private TextInputLayout tilEmail;
    private TextInputLayout tilPassword;
    private Button btnSignUp;
    private Button btnHaveAccount;

    public SignUpFragment() { }

    public static SignUpFragment getInstance(){
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup, container, false);

        mActionsListener = new SignUpPresenter(this);

        tilFullName = view.findViewById(R.id.tilFullName);
        tilEmail = view.findViewById(R.id.tilEmail);
        tilPassword = view.findViewById(R.id.tilPassword);
        btnSignUp = view.findViewById(R.id.btnSignUp);
        btnHaveAccount = view.findViewById(R.id.btnHaveAccount);

        btnSignUp.setOnClickListener(this);
        btnHaveAccount.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignUp:
                signUp();
                break;
            case  R.id.btnHaveAccount:
                Log.d("IBASOFT", "btnHaveAccount");
                goToLoginFragment();
                break;
        }
    }

    @Override
    public void goToLoginFragment() {
        /*AuthActivity authActivity = (AuthActivity) getActivity();
        authActivity.replaceFragment(LoginFragment.getInstance(), false);*/
        Log.d("IBASOFT", "btnHaveAccount");
        getChildFragmentManager().popBackStack();
    }

    @Override
    public void goToMainActivity() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showMessageError(Exception error) {
        Snackbar.make(getView(), error.getMessage(), Snackbar.LENGTH_LONG).show();
    }

    private void signUp() {
        try {
            boolean result = true;
            String fullName = tilFullName.getEditText().getText().toString();
            String email = tilEmail.getEditText().getText().toString();
            String password = tilPassword.getEditText().getText().toString();

            if(Utilities.isEmpty(fullName)) {
                tilFullName.setError(getString(R.string.is_required));
                tilFullName.setErrorEnabled(true);
                result = false;
            } else {
                tilFullName.setError(null);
                tilFullName.setErrorEnabled(false);
            }

            if(Utilities.isEmpty(email)) {
                tilEmail.setError(getString(R.string.is_required));
                tilEmail.setErrorEnabled(true);
                result = false;
            } else {
                tilEmail.setError(null);
                tilEmail.setErrorEnabled(false);
            }

            if(Utilities.isEmpty(password)) {
                tilPassword.setError(getString(R.string.is_required));
                tilPassword.setErrorEnabled(true);
                result = false;
            } else {
                tilPassword.setError(null);
                tilPassword.setErrorEnabled(false);
            }

            //Si la validaciones no generaron errores
            if(result) {
                mActionsListener.onSignUp(fullName, email, password);
            }
        } catch (Exception e) {

        }
    }
}
