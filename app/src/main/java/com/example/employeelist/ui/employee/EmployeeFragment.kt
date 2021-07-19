package com.example.employeelist.ui.employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import com.example.employeelist.EmployeeViewModel
import com.example.employeelist.databinding.FragmentEmployeeBinding


@AndroidEntryPoint
class EmployeeFragment : Fragment() {
    lateinit var binding: FragmentEmployeeBinding

    val viewModel: EmployeeViewModel by viewModels()


    val employeeAdapter = EmployeePagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentEmployeeBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setRecyclerView()

        binding.employeeSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.setQuery(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })


        viewModel.list.observe(viewLifecycleOwner) {
            employeeAdapter.submitData(lifecycle, it)
        }


    }

    private fun setRecyclerView() {
        binding.employeeRecycler.apply {
            adapter = employeeAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }


}