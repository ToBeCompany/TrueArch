package com.example.myapplication.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.observe
import com.example.myapplication.R
import com.example.myapplication.Resource
import com.example.myapplication.Todos
import com.example.myapplication.TodosItem
import org.koin.android.viewmodel.ext.android.viewModel


class TrueFragment : Fragment() {
    companion object {
        fun newInstance() = TrueFragment()
    }
    private val viewModel: MainViewModel by viewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view =  inflater.inflate(R.layout.main_fragment, container, false)
        val listView = view.findViewById<ListView>(R.id.message)

        viewModel.todos.observe(viewLifecycleOwner){
            when (it){
                is Resource.Success -> {
                    listView.adapter =
                        ArrayAdapter<TodosItem>(
                            requireContext(),
                            android.R.layout.simple_list_item_1,
                            it.data ?: Todos()
                        )
                    view.findViewById<ProgressBar>(R.id.progressbar).visibility = View.GONE
                }
                is Resource.Loading ->{
                    view.findViewById<ProgressBar>(R.id.progressbar).visibility = View.VISIBLE
                }
                is Resource.Error ->{
                    view.findViewById<TextView>(R.id.textViewShowItem).visibility = View.VISIBLE
                    view.findViewById<TextView>(R.id.textViewShowItem).text = it.message
                    view.findViewById<ProgressBar>(R.id.progressbar).visibility = View.GONE
                }
            }
        }

        return view
    }

}