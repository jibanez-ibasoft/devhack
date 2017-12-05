package com.ibasoft.todoapp.repository.impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ibasoft.todoapp.domain.model.User;
import com.ibasoft.todoapp.helpers.Callback;
import com.ibasoft.todoapp.repository.UserRepository;

/**
 * Created by JorgeIbanez on 30/11/2017.
 */

public class UserFirebaseRepository implements UserRepository {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    public UserFirebaseRepository() {
        this.mAuth = FirebaseAuth.getInstance();
        this.mDatabase = FirebaseDatabase.getInstance().getReference("users");
    }

    @Override
    public void login(String email, String password, final Callback<User> callback) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            FirebaseUser firebaseUser = task.getResult().getUser();
                            mDatabase.child(firebaseUser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {

                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    User user = dataSnapshot.getValue(User.class);
                                    callback.success(user);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    callback.error(databaseError.toException());
                                }
                            });

                                    /*
                                        Permite obtener en tiempo real los cambios que se realicen en Firebase
                                        addValueEventListener(new  v)
                                     */
                        } else {
                            callback.error(task.getException());
                        }
                    }

                });
    }

    @Override
    public void signUp(final User user, final Callback<User> callback) {
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //Respuesta de la creacion del uuario en FirebaseAuth
                        if (task.isSuccessful() && task.getResult() != null) {
                            FirebaseUser firebaseUser = task.getResult().getUser();
                            user.setId(firebaseUser.getUid());
                            user.setPassword(null);
                            mDatabase.child(user.getId()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    //Respuesta de la creacion del usuario en FirebaseDatabase
                                    if (task.isSuccessful()) {
                                        callback.success(user);
                                    } else {
                                        callback.error(task.getException());
                                    }
                                }
                            });
                        } else {
                            callback.error(task.getException());
                        }
                    }
                });
    }

    @Override
    public void recoveryPassword(String email, final Callback<Boolean> callback) {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    callback.success(true);
                } else {
                    callback.error(task.getException());
                }
            }
        });
    }
}
