package com.apps.yeltssin.examen.PantallaPrincipal;

import com.apps.yeltssin.examen.response.data;

import io.reactivex.Single;

public interface MVPPantallaPrincipal {

    interface view{
        void SuccesResponse(String msj);
        void ErrorResponse(String msj);


        void SuccesDownload(String path);
        void ErrorDownload(String msj);



        void SuccesDescomprimido(String path);
        void ErrorDescomprimido(String msj);


        void Succesempleado(String msj);
        void Errorempleado(String msj);

    }

    interface  presenter{
        void SuccesResponse(String msj);
        void ErrorResponse(String msj);

        void SuccesDownload(String msj);
        void ErrorDownload(String msj);

        void Succesempleado(String msj);
        void Errorempleado(String msj);

        void getResponse();
        void Download(String file);

        void Descomprime(String path);

        void onDestroy();

        void procesaArchivo();

    }

    interface  model{
        void getResponse();
        Single<Boolean> Download(String file);
        Single<Boolean> Descomprime(String path);
        void procesaArchivo();
    }
}
