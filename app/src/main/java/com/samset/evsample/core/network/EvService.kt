package com.samset.evsample.core.network

import com.samset.evsample.core.model.CountryResponse
import com.samset.evsample.utils.Constants.COUNTRY
import retrofit2.Response
import retrofit2.http.GET

/*
  Copyright (C) EVSample - All Rights Reserved Ⓒ
  Unauthorized copying of this file, via any medium is strictly prohibited
  Proprietary and confidential.
  See the License for the specific language governing permissions and limitations under the License.
 
  Created by Sanjay Singh  on 28,December,2022 at 6:54 PM for EVSample.
  New Delhi,India
 */


interface EvService {

    @GET(COUNTRY)
    suspend fun getCountry() : Response<CountryResponse>
}