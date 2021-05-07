package com.apps.yeltssin.examen.Register;

public interface MVPRegister {

    interface view{
        void SuccesRegister(String msj);
        void ErrorRegister(String msj);
    }

    interface  presenter{
        void SuccesRegister(String msj);
        void ErrorRegister(String msj);

        void Register(String name,String user,String pass);
    }

    interface  model{
        void Register(String name,String user,String pass);
    }
}
