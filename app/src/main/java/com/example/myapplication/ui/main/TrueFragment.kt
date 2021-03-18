package com.example.myapplication.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.myapplication.R
import com.example.myapplication.Resource

class TrueFragment : Fragment() {
    companion object {
        fun newInstance() = TrueFragment()
    }
    private val viewModel: MainViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view =  inflater.inflate(R.layout.main_fragment, container, false)
        viewModel.user.observe(viewLifecycleOwner){
            if (it is Resource.Loading){
                    view.findViewById<ProgressBar>(R.id.progressbar).visibility = View.VISIBLE
                }
            if (it is Resource.Success){
                    view.findViewById<ProgressBar>(R.id.progressbar).visibility = View.GONE
                view.findViewById<TextView>(R.id.message).text = it.data
                }

            }
        return view
    }

}