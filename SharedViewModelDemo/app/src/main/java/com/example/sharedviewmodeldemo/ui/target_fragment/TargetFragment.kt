package com.example.sharedviewmodeldemo.ui.target_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.sharedviewmodeldemo.R
import com.example.sharedviewmodeldemo.SharedViewModel
import kotlinx.android.synthetic.main.fragment_source.*
import kotlinx.android.synthetic.main.fragment_target.*
import timber.log.Timber

class TargetFragment  : Fragment() {

    lateinit var sharedViewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_target, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedViewModel = SharedViewModel()

        sharedViewModel.textToSend.observe(viewLifecycleOwner, Observer {text ->
            if (sharedViewModel.isSent){
                et_target.setText(text)
                sharedViewModel.isSent = false
            }
        })

        bt_target_send.setOnClickListener{
            sharedViewModel.isSent = true
            sharedViewModel.setText(et_target.text.toString())
            //et_target.text.clear()
        }
    }
}