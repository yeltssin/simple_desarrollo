package com.apps.yeltssin.examen;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthProvider {

    FirebaseAuth mAuth;
    public  AuthProvider(){
        mAuth=FirebaseAuth.getInstance();
    }
    public Task<AuthResult> Register(String Email, String Password){
        return mAuth.createUserWithEmailAndPassword(Email,Password);
    }

    public Task<AuthResult> Login(String Email, String Password){
        return mAuth.signInWithEmailAndPassword(Email,Password);
    }

    public  void Logout(){
        mAuth.signOut();
    }

    public String getID(){
        return mAuth.getCurrentUser().getUid();
    }

    public boolean UserExist(){
        boolean Exist=false;
        if(mAuth.getCurrentUser() != null){
            Exist=true;
        }

        return Exist;
    }
}
