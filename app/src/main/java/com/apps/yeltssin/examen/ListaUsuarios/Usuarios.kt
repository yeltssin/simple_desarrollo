package com.apps.yeltssin.examen.ListaUsuarios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import androidx.databinding.DataBindingUtil
import com.apps.yeltssin.examen.R
import com.apps.yeltssin.examen.databinding.ActivityUsuariosBinding
import com.google.android.material.tabs.TabLayoutMediator

class Usuarios : AppCompatActivity() {

    lateinit var usuarioBinding:ActivityUsuariosBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuarios)
        usuarioBinding=DataBindingUtil.setContentView(Usuarios@this,R.layout.activity_usuarios)
        init()
    }

    private fun init(){

        val pagerAdapter=PagerAdapter(this,numtabs = 2)
        val tablayout= TabLayoutMediator(tab)

        if(!isUpdate) {
            pagerAdapter = new PagerAdapter(this, tabOptions.getTabCount(),false,null,null,"");
        }else{
            pagerAdapter=(action.equals(ACCION_USER))?
            new PagerAdapter(this, tabOptions.getTabCount(),isUpdate,user,null,action):
            new PagerAdapter(this, tabOptions.getTabCount(),isUpdate,null, perfil,action);

        }
        VPConfig.setAdapter(pagerAdapter);



        new TabLayoutMediator(tabOptions, VPConfig, ((tab, position) -> {
            switch (position) {
                case 0:
                tab.setText(getString(R.string.text_user));
                //  new UsuarioFragment();
                break;
                case 1:
                tab.setText(getString(R.string.text_Perfiles));
                //new PerfilFragment();

                break;
            }
        })).attach();
    }
}