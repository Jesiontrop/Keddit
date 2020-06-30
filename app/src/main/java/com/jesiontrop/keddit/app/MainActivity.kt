package com.jesiontrop.keddit.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.jesiontrop.keddit.R

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG : String = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp(): Boolean =
            findNavController(R.id.navigation).navigateUp()

}
