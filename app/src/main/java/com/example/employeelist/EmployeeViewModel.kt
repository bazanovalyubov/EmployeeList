package com.example.employeelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.employeelist.remote.EmployeeInterface
import com.example.employeelist.ui.employee.EmployeePaging
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(private val employeeInterface: EmployeeInterface) :
    ViewModel() {

    private val query = MutableLiveData<String>()
    val list = query.switchMap {query->
        Pager(PagingConfig(pageSize = 10)) {
            EmployeePaging(query, employeeInterface)
        }.liveData.cachedIn(viewModelScope)
    }
    fun setQuery(s:String) {
        query.postValue(s)
    }
}