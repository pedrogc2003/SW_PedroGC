package com.example.sw_pedrogc

import com.example.sw_pedrogc.Modelos.PersonajesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getPersonajes(@Url url:String): Response<PersonajesResponse>
}