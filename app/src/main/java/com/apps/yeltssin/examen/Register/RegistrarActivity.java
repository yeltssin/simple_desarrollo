package com.apps.yeltssin.examen.Register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Toast;

import com.apps.yeltssin.examen.Login.MVPMAinPresenter;
import com.apps.yeltssin.examen.R;
import com.apps.yeltssin.examen.Register.MVPRegister;
import com.apps.yeltssin.examen.Register.MVPRegisterPresenter;
import com.apps.yeltssin.examen.databinding.ActivityRegistrarBinding;

public class RegistrarActivity extends AppCompatActivity implements MVPRegister.view {

    ActivityRegistrarBinding registrarBinding;
    MVPRegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registrarBinding= DataBindingUtil.setContentView(this, R.layout.activity_registrar);
        setContentView(registrarBinding.getRoot());
        init();
    }



    private void init(){
        presenter=new MVPRegisterPresenter(this);
        registrarBinding.btnERegistrar.setOnClickListener(view->{
            presenter.Register(registrarBinding.etnamer.getEditText().getText().toString(),
                    registrarBinding.etUser.getEditText().getText().toString(),
                    registrarBinding.etpass.getEditText().getText().toString());
        });
    }

    @Override
    public void SuccesRegister(String msj) {
        mensajes(msj);
    }

    @Override
    public void ErrorRegister(String msj) {
        mensajes(msj);
    }

    private void mensajes(String msj){
        Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
    }

}