package com.apps.yeltssin.examen.Register;

import com.apps.yeltssin.examen.AuthProvider;

public class MVPRegisterModel implements MVPRegister.model{
   MVPRegisterPresenter presenter;

    public MVPRegisterModel(MVPRegisterPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void Register(String name,String user, String pass) {
        AuthProvider authProvider=new AuthProvider();
        try{
            authProvider.Register(user,pass).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    presenter.SuccesRegister("registro correcto");
                }else{
                    presenter.ErrorRegister(task.getException().getMessage());
                }

            });

        }catch (Exception e){
            presenter.ErrorRegister(e.getMessage());
        }

    }
}
