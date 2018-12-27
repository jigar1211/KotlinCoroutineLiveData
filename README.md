# KotlinCoroutineLiveData
kotlin Coroutin using retrofit and Live Data with MVVM Architecture

Add below dependencies in app level build.gradle file

For kotlin coroutine:
                      
                      implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.0.1'
                      implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.0.1"
                      implementation 'com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2'
                      
For Live data using android lifrecycle:

                       implementation "android.arch.lifecycle:extensions:1.1.1"
                       annotationProcessor "android.arch.lifecycle:compiler:1.1.1"
                       kapt "com.android.databinding:compiler:3.1.4"
                       implementation "android.arch.lifecycle:runtime:1.1.1"
                       
For retrofit:

                       implementation 'com.squareup.retrofit2:retrofit:2.4.0'
                       implementation 'com.squareup.retrofit2:converter-gson:2.4.0'
                       implementation 'com.squareup.okhttp3:logging-interceptor:3.10.0'
                       
For coroutin use "Deferred" keyword to call API using retrofit in Api interface class like below:

                       @GET("CountryApi/image.json")
                           fun getImageList(): Deferred<List<ApiResponseModel>>
                            
Use CoroutineCallAdapterFactory() in retrofit object like below:

                       val retrofit: Retrofit = Retrofit.Builder()
                                  .baseUrl(BASEURL)
                                  .client(client)
                                  .addConverterFactory(GsonConverterFactory.create())
                                  .addCallAdapterFactory(CoroutineCallAdapterFactory())
                                  .build()
                                  
Call API as per below:

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
                              return data
                          }
