package eventown.com.eventown.service

import eventown.com.eventown.model.ApiResponseModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiInterface {

    @GET("CountryApi/image.json")
    fun getImageList(): Deferred<List<ApiResponseModel>>

}