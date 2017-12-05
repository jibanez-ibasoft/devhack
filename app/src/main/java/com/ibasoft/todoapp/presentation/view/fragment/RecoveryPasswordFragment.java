package com.ibasoft.todoapp.presentation.view.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ibasoft.todoapp.R;
import com.ibasoft.todoapp.helpers.Utilities;
import com.ibasoft.todoapp.presentation.presenter.RecoveryContract;
import com.ibasoft.todoapp.presentation.presenter.RecoveryPresenter;

public class RecoveryPasswordFragment extends DialogFragment implements RecoveryContract.View,
        View.OnClickListener {

    private RecoveryContract.UserActionsLister mActionsListener;

    private TextInputLayout tilEmail;
    private Button btnRecovery;

    public RecoveryPasswordFragment() {}

    public static RecoveryPasswordFragment getInstance(){
        return new RecoveryPasswordFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recover_password, container, false);

        mActionsListener = new RecoveryPresenter(this);

        tilEmail = view.findViewById(R.id.tilEmail);
        btnRecovery = view.findViewById(R.id.btnRecovery);

        btnRecovery.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRecovery:
                Log.d("IBASOFT", "btnRecovery");
                recoveryPassword();
                break;
        }
    }

    private void recoveryPassword(){
        try {
            boolean result = true;
            String email = tilEmail.getEditText().getText().toString();

            if(Utilities.isEmpty(email)) {
                tilEmail.setError(getString(R.string.is_required));
                tilEmail.setErrorEnabled(true);
                result = false;
            } else {
                tilEmail.setError(null);
                tilEmail.setErrorEnabled(false);
            }

            //Si la validaciones no generaron errores
            if(result) {
                mActionsListener.onRecoveryPassword(email);
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void showMessageError(Exception error) {

    }
}
