package com.apps.yeltssin.examen.ListaUsuarios

import android.view.View
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(@NonNull fragment:Fragment,var numtabs: Int =0 ): FragmentStateAdapter(fragment) {


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0-> {
                ListFragment()
            }

            1-> {
                MapFragment()
            }
            else -> ListFragment()
        }
    }

    override fun getItemCount(): Int = numtabs
}