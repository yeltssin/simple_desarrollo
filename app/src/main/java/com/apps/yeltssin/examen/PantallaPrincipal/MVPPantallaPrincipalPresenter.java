package com.apps.yeltssin.examen.PantallaPrincipal;

import android.content.Context;

import com.apps.yeltssin.examen.response.data;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MVPPantallaPrincipalPresenter implements MVPPantallaPrincipal.presenter{

    MVPPantallaPrincipal.view view;
    MVPPantallaPrincipal.model model;
    Disposable disposable;
    CompositeDisposable compositeDisposable;

    public MVPPantallaPrincipalPresenter(MVPPantallaPrincipal.view view, Context context) {
        this.view = view;
        model=new MVPPantallaPrincipalModel(this,context);
        compositeDisposable=new CompositeDisposable();
    }

    @Override
    public void SuccesResponse(String msj) {
        if(view !=null){
            view.SuccesResponse(msj);
        }

    }

    @Override
    public void ErrorResponse(String msj) {
        if(view !=null){
            view.SuccesResponse(msj);
        }
    }


    @Override
    public void getResponse() {
        if(view !=null){
            model.getResponse();
        }
    }


    @Override
    public void SuccesDownload(String msj) {
        if(view !=null){
            view.SuccesDownload(msj);
        }
    }

    @Override
    public void ErrorDownload(String msj) {
        if(view !=null){
           view.ErrorDownload(msj);
        }
    }

    @Override
    public void Succesempleado(String msj) {
        if (view != null){
            view.Succesempleado(msj);
        }
    }

    @Override
    public void Errorempleado(String msj) {
        if (view != null){
            view.Errorempleado(msj);
        }
    }


    @Override
    public void Download(String file) {
        if(view !=null){
            model.Download(file).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new SingleObserver<Boolean>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    compositeDisposable.add(d);
                }

                @Override
                public void onSuccess(@NonNull Boolean aBoolean) {
                    if(aBoolean) view.SuccesDownload("Descarga correcta");
                    else view.ErrorDownload("Descarga incorrecta");
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    view.ErrorDownload(e.getMessage());
                }
            });
        }
    }

    @Override
    public void Descomprime(String path) {
        if(view !=null){
            model.Descomprime(path).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<Boolean>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                           compositeDisposable.add(d);
                        }

                        @Override
                        public void onSuccess(@NonNull Boolean aBoolean) {
                            if(aBoolean) view.SuccesDescomprimido("Descompresion correcta");
                            else view.ErrorDescomprimido("Descompresion incorrecta");
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            view.ErrorDescomprimido(e.getMessage());
                        }
                    });
        }
    }



    @Override
    public void onDestroy(){
    /*    if(disposable!= null)disposable.dispose();*/
        if(compositeDisposable != null) compositeDisposable.clear();
    }

    @Override
    public void procesaArchivo() {
        if (view != null){
         model.procesaArchivo();
        }
    }

}
