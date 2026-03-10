package com.example.fhubo.Main

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface filmsService {

    @GET("api/films")
    suspend fun llistaFilms(): Response<List<Main>>

    /*@GET("items/categoria/{idcategoria}")
    suspend fun llistaItemsPerCategoria(
        @Path("idcategoria") idcategoria: Int
    ): Response<List<Main>>*/
}