package com.betterzw.navigation

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController

class MainActivity : AppCompatActivity(), BlankFragment.OnFragmentInteractionListener{
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

   /* override fun onSupportNavigateUp(): Boolean {
        return findNavController(this, R.id.nav_host_fragment).navigateUp()
    }*/

    /*override fun onSupportNavigateUp()
            = findNavController(R.id.nav_host_fragment).navigateUp()*/
}
