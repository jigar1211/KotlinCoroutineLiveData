package eventown.com.eventown.service

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import eventown.com.eventown.model.ApiResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ProjectRepository {

    private val BASEURL = "http://192.168.0.10/~jigars/"
    private var apiInterface: ApiInterface? = null

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASEURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        apiInterface = retrofit.create(ApiInterface::class.java)
    }

    companion object {

        private var projectRepository: ProjectRepository? = null

        @Synchronized
        @JvmStatic
        fun getInstance(): ProjectRepository {
            if (projectRepository == null) {
                projectRepository = ProjectRepository()
            }
            return projectRepository!!
        }
    }

    fun getImageList(): LiveData<List<ApiResponseModel>> {

        val data = MutableLiveData<List<ApiResponseModel>>()
        GlobalScope.launch(Dispatchers.Main) {
            val result = apiInterface?.getImageList()
            try {
                data.value = result!!.await()
            } catch (e: HttpException) {
                data.value = null
                e.printStackTrace()
            } catch (e: Throwable) {
                data.value = null
                e.printStackTrace()
            }
        }

//        apiInterface?.getImageList()?.enqueue(object : Callback<List<ApiResponseModel>> {
//            override fun onFailure(call: Call<List<ApiResponseModel>>, t: Throwable) {
//                data.value = null
//                t.message
//            }
//
//            override fun onResponse(call: Call<List<ApiResponseModel>>, response: Response<List<ApiResponseModel>>) {
//                val listData = response.body()!!
//                data.value = listData
//            }
//
//        })

        return data
    }

}