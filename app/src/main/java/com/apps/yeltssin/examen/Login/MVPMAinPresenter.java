package com.apps.yeltssin.examen.Login;

public class MVPMAinPresenter implements MVPMain.presenter{

    MVPMain.view view;
    MVPMain.model model;

    public MVPMAinPresenter(MVPMain.view view) {
        this.view = view;
        model=new MVPMainModel(this);
    }

    @Override
    public void SuccesLogin(String msj) {
        if(view !=null){
            view.SuccesLogin(msj);
        }

    }

    @Override
    public void ErrorLogin(String msj) {
        if(view !=null){
            view.SuccesLogin(msj);
        }
    }

    @Override
    public void Login(String user, String pass) {
        if(view !=null){
            model.Login(user, pass);
        }
    }

}
