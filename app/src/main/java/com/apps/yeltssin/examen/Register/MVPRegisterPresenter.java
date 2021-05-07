package com.apps.yeltssin.examen.Register;

public class MVPRegisterPresenter implements MVPRegister.presenter{

    MVPRegister.view view;
    MVPRegister.model model;

    public MVPRegisterPresenter(MVPRegister.view view) {
        this.view = view;
        model=new MVPRegisterModel(this);
    }

    @Override
    public void SuccesRegister(String msj) {
        if(view !=null){
            view.SuccesRegister(msj);
        }

    }

    @Override
    public void ErrorRegister(String msj) {
        if(view !=null){
            view.SuccesRegister(msj);
        }
    }

    @Override
    public void Register(String name,String user, String pass) {
        if(view !=null){
            model.Register(name,user, pass);
        }
    }

}
