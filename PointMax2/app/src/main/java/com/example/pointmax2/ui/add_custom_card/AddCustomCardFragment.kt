package com.example.pointmax2.ui.add_custom_card

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pointmax2.ActivityViewModel
import com.example.pointmax2.R

class AddCustomCardFragment : Fragment(){

    private lateinit var viewModel: ActivityViewModel
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_custom_card, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = activity?.run{
            ViewModelProvider(this).get(ActivityViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }
}