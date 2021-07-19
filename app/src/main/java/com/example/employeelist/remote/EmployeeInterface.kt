package com.example.employeelist.remote

import com.example.employeelist.data.EmployeeResponce
import retrofit2.Response
import retrofit2.http.GET

interface EmployeeInterface {
    @GET("b/60bf82449fc30168f1c94107")
    suspend fun getAllEmployees():Response<EmployeeResponce>
}