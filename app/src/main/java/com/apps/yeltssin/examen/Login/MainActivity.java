package com.apps.yeltssin.examen.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.apps.yeltssin.examen.PantallaPrincipal.PantallaPrincipalActivity;
import com.apps.yeltssin.examen.R;
import com.apps.yeltssin.examen.Register.RegistrarActivity;
import com.apps.yeltssin.examen.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements MVPMain.view {

    ActivityMainBinding mainBinding;
    MVPMAinPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        setContentView(mainBinding.getRoot());
        init();
    }

    private void init(){
        presenter=new MVPMAinPresenter(this);
        mainBinding.btnEntrar.setOnClickListener(view->{
            presenter.Login(mainBinding.etUser.getEditText().getText().toString(),
                    mainBinding.etpass.getEditText().getText().toString());
        });

        mainBinding.btnregistrar.setOnClickListener(view->{
            startActivity(new Intent(this, RegistrarActivity.class));
        });
    }

    @Override
    public void SuccesLogin(String msj) {
        mensajes(msj);
        startActivity(new Intent(this, PantallaPrincipalActivity.class));
        finish();
    }

    @Override
    public void ErrorLogin(String msj) {
        mensajes(msj);
    }


    private void mensajes(String msj){
        Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();
    }
}