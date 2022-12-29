package com.samset.evsample.core.network

import com.samset.evsample.utils.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
  Copyright (C) EVSample - All Rights Reserved Ⓒ
  Unauthorized copying of this file, via any medium is strictly prohibited
  Proprietary and confidential.
  See the License for the specific language governing permissions and limitations under the License.
 
  Created by Sanjay Singh on 28,December,2022 at 6:58 PM for EVSample.
  New Delhi,India
 */


object EvRetrofit {
 val api : EvService by lazy {
  Retrofit.Builder()
   .baseUrl(Constants.BASE_URL)
   .client(OkHttpClient.Builder().build())
   .addConverterFactory(GsonConverterFactory.create())
   .build()
   .create(EvService::class.java)
 }

 var client = OkHttpClient.Builder().addInterceptor(object : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
   val newRequest: Request = chain.request().newBuilder()
   // .addHeader("Authorization", "Bearer $TOKEN")
    .build()
   return chain.proceed(newRequest)
  }

 }).addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build()
}