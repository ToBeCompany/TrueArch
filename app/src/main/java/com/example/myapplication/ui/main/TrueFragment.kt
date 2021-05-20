package com.example.myapplication.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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
        val view = inflater.inflate(R.layout.main_fragment, container, false)
        val listView = view.findViewById<RecyclerView>(R.id.recyclerView)

        val adapter = NumberAdapter()
        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(requireContext())


        view.findViewById<Button>(R.id.textViewShowItem).setOnClickListener {
            viewModel.getNum()
            submit(adapter)
        }

//        viewModel.todos.observe(viewLifecycleOwner){
//            when (it){
//                is Resource.Success -> {
//                    listView.adapter =
//                        ArrayAdapter<TodosItem>(
//                            requireContext(),
//                            android.R.layout.simple_list_item_1,
//                            it.data ?: Todos()
//                        )
//                    view.findViewById<ProgressBar>(R.id.progressbar).visibility = View.GONE
//                }
//                is Resource.Loading ->{
//                    view.findViewById<ProgressBar>(R.id.progressbar).visibility = View.VISIBLE
//                }
//                is Resource.Error ->{
//                    view.findViewById<TextView>(R.id.textViewShowItem).visibility = View.VISIBLE
//                    view.findViewById<TextView>(R.id.textViewShowItem).text = it.message
//                    view.findViewById<ProgressBar>(R.id.progressbar).visibility = View.GONE
//                }
//            }
//        }

        return view
    }

    private fun submit(adapter: NumberAdapter) {
        viewLifecycleOwner.lifecycle.coroutineScope.launch {
            viewModel.flow.collectLatest {
                adapter.submitList(it)
            }
        }
    }

}