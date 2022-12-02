package jp.ac.it_college.std.s21025.pokemonsecret

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {
    @GET("pokemon/{random}/")
    fun fetchPoke(@Path("random") random: Int): Call<PokemonResponce>
}
