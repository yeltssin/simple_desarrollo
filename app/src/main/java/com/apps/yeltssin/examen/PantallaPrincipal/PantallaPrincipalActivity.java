package com.apps.yeltssin.examen.PantallaPrincipal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.apps.yeltssin.examen.R;
import com.apps.yeltssin.examen.databinding.ActivityPantallaPrincipalBinding;
import com.apps.yeltssin.examen.response.data;

public class PantallaPrincipalActivity extends AppCompatActivity implements
        MVPPantallaPrincipal.view {

    ActivityPantallaPrincipalBinding pantallaPrincipalBinding;
    MVPPantallaPrincipal.presenter presenter;
    private String TAG =PantallaPrincipalActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pantallaPrincipalBinding= DataBindingUtil.setContentView(this,R.layout.activity_pantalla_principal);
        setContentView(pantallaPrincipalBinding.getRoot());
        init();
    }


    private  void init(){
        presenter=new MVPPantallaPrincipalPresenter(this,
                PantallaPrincipalActivity.this);
        pantallaPrincipalBinding.btnEdescarga.setOnClickListener(view->{
            presenter.getResponse();
        });
    }

    @Override
    public void SuccesResponse(String msj) {
        mensajes(msj);
        presenter.Download(msj);
    }

    @Override
    public void ErrorResponse(String msj) {
        mensajes(msj);
    }

    @Override
    public void SuccesDownload(String path) {
      //  presenter.procesaArchivo();
        Log.d(TAG, "SuccesDownload: "+path);
        presenter.Descomprime(path);
        mensajes(path);
    }

    @Override
    public void ErrorDownload(String msj) {
        mensajes(msj);
    }

    @Override
    public void SuccesDescomprimido(String path) {
        presenter.procesaArchivo();
    }

    @Override
    public void ErrorDescomprimido(String msj) {
        mensajes(msj);
    }

    @Override
    public void Succesempleado(String msj) {
        //
        mensajes(msj);
    }

    @Override
    public void Errorempleado(String msj) {
        mensajes(msj);
    }

    private void mensajes(String msj){
        Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}