package com.apps.yeltssin.examen.Login;

public interface MVPMain {

    interface view{
        void SuccesLogin(String msj);
        void ErrorLogin(String msj);
    }

    interface  presenter{
        void SuccesLogin(String msj);
        void ErrorLogin(String msj);

        void Login(String user,String pass);
    }

    interface  model{
        void Login(String user,String pass);
    }
}
