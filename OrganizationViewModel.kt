package com.example.myapplication.ui

import androidx.lifecycle.*
import com.example.myapplication.data.remote.Organization
import com.example.myapplication.data.remote.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrganizationViewModel : ViewModel() {

    private val _organizations = MutableLiveData<List<Organization>>()
    val organizations: LiveData<List<Organization>> = _organizations

    fun loadOrganizations() {
        RetrofitInstance.api.getOrganizations().enqueue(object : Callback<List<Organization>> {
            override fun onResponse(call: Call<List<Organization>>, response: Response<List<Organization>>) {
                _organizations.value = response.body() ?: emptyList()
            }

            override fun onFailure(call: Call<List<Organization>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun createOrganization(org: Organization) {
        RetrofitInstance.api.createOrganization(org).enqueue(object : Callback<Organization> {
            override fun onResponse(call: Call<Organization>, response: Response<Organization>) {
                loadOrganizations()
            }

            override fun onFailure(call: Call<Organization>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun updateOrganization(org: Organization) {
        org.id?.let {
            RetrofitInstance.api.updateOrganization(it, org).enqueue(object : Callback<Organization> {
                override fun onResponse(call: Call<Organization>, response: Response<Organization>) {
                    loadOrganizations()
                }

                override fun onFailure(call: Call<Organization>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
    }

    fun deleteOrganization(id: Int) {
        RetrofitInstance.api.deleteOrganization(id).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                loadOrganizations()
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}