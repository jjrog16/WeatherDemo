package com.example.sharedviewmodeldemo.ui.target_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sharedviewmodeldemo.R
import com.example.sharedviewmodeldemo.SharedViewModel
import kotlinx.android.synthetic.main.fragment_source.*
import kotlinx.android.synthetic.main.fragment_target.*
import timber.log.Timber

class TargetFragment  : Fragment() {

    lateinit var viewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_target, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bt_target_send.setOnClickListener{
            viewModel.setText(et_target.text.toString())
            et_target.text.clear()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // ViewModelProvider needs the Activity in order to communicate between its fragment children
        viewModel = activity?.run {
            ViewModelProvider(this).get(SharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        viewModel.textToSend.observe(viewLifecycleOwner, Observer {text ->
            et_target.setText(text)
        })
    }
}