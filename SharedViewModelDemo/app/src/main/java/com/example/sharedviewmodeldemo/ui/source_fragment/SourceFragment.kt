package com.example.sharedviewmodeldemo.ui.source_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.sharedviewmodeldemo.R
import com.example.sharedviewmodeldemo.SharedViewModel
import kotlinx.android.synthetic.main.fragment_source.*

class SourceFragment  : Fragment() {

    lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_source, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = SharedViewModel()

        viewModel.textToSend.observe(viewLifecycleOwner, Observer {text ->
            if (viewModel.isSent){
                et_source.setText(text)
                viewModel.isSent = false
            }
        })

        bt_source_send.setOnClickListener{
            viewModel.isSent = true
            viewModel.setText(et_source.text.toString())
            //et_source.text.clear()
        }
    }
}