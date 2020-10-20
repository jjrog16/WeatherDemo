package com.example.sharedviewmodeldemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import com.example.sharedviewmodeldemo.ui.source_fragment.SourceFragment
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    lateinit var sourceFragment: Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Plant tree to enable Debugging with Timber
        Timber.plant(Timber.DebugTree())



        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if(source != null){
            if (savedInstanceState != null){
                return
            }

            supportFragmentManager.beginTransaction()
                .add(R.id.source, SourceFragment())
                .commit()
        }

    }
}