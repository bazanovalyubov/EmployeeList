package com.example.employeelist.hilt

import com.example.employeelist.remote.EmployeeInterface
import com.example.employeelist.utils.UrlConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object HiltModules {

    //provider creates a link to DetailsFragment and EmployeeFragment
    @Provides
    fun provideRetrofitInterface():EmployeeInterface{
        return Retrofit.Builder().baseUrl(UrlConstants.BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(EmployeeInterface::class.java)
    }
}