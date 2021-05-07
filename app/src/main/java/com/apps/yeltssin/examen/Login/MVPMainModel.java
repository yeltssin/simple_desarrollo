package com.apps.yeltssin.examen.Login;

import com.apps.yeltssin.examen.AuthProvider;

public class MVPMainModel implements MVPMain.model{
   MVPMAinPresenter presenter;

    public MVPMainModel(MVPMAinPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void Login(String user, String pass) {
        AuthProvider authProvider=new AuthProvider();
        try{
            authProvider.Login(user,pass).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    presenter.SuccesLogin("Login correcto");
                }else{
                    presenter.ErrorLogin("error al iniciar sesion");
                }

            });

        }catch (Exception e){
            presenter.ErrorLogin(e.getMessage());
        }

    }
}
