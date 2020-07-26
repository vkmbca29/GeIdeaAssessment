package Connection

import MainPage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDataServiceInterface {
    @GET("users")
    fun getResult(
        @Query("per_page") per_page: Int?
    ): Call<MainPage?>?
}