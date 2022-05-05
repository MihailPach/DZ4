package com.example.dz4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dz4.CountFragment
import com.example.dz4.fragment.ContentFragment


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, ContentFragment())
                .commit()
        }
    }
}