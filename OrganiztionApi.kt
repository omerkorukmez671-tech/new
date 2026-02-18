package com.example.myapplication.data.remote

import retrofit2.Call
import retrofit2.http.*

data class Organization(
    val id: Int? = null,
    val name: String,
    val description: String? = null,
    val parent_id: Int? = null
)

interface OrganizationApi {

    @GET("organizations/")
    fun getOrganizations(): Call<List<Organization>>

    @POST("organizations/")
    fun createOrganization(@Body org: Organization): Call<Organization>

    @PUT("organizations/{id}")
    fun updateOrganization(@Path("id") id: Int, @Body org: Organization): Call<Organization>

    @DELETE("organizations/{id}")
    fun deleteOrganization(@Path("id") id: Int): Call<Unit>
}