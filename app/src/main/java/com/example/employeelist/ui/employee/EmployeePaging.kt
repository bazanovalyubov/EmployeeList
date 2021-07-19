package com.example.employeelist.ui.employee

import androidx.paging.PagingSource
import androidx.paging.PagingState

import com.example.employeelist.data.Employee
import com.example.employeelist.remote.EmployeeInterface
import java.lang.Exception

class EmployeePaging(val s: String, val employeeInterface: EmployeeInterface) : PagingSource<Int, Employee>(){
    override fun getRefreshKey(state: PagingState<Int, Employee>): Int? {
        //if it is not null
        return state.anchorPosition?.let {
            val basePage = state?.closestPageToPosition(it)
            basePage?.prevKey?.plus(1)?:basePage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Employee> {
        val page = params.key ?: 1
        return try {
            val data = employeeInterface.getAllEmployees()

            LoadResult.Page(
                //return list of Employees if it is not null
                data = data.body()?.response!!,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.body()?.response?.isEmpty()!!) null else page + 1
            )

        } catch(e: Exception){
            LoadResult.Error(e)
        }

    }
}