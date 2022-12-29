package com.samset.evsample.core.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.samset.evsample.core.model.CountryResponse
import com.samset.evsample.core.network.EvRetrofit
import com.samset.evsample.core.network.EvService
import com.samset.evsample.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
  Copyright (C) EVSample - All Rights Reserved Ⓒ
  Unauthorized copying of this file, via any medium is strictly prohibited
  Proprietary and confidential.
  See the License for the specific language governing permissions and limitations under the License.
 
  Created by Sanjay Singh on 28,December,2022 at 6:40 PM for EVSample.
  New Delhi,India
 */


class EvRepository {

    private  var apiServices: EvService
    init {
        apiServices=EvRetrofit.api
    }


    public fun getContry(response: MutableLiveData<Resource<CountryResponse>>){
        response.postValue(Resource.loading(null))
        CoroutineScope(Dispatchers.IO).launch {
            val result = apiServices.getCountry()
            if (result != null)
                response.postValue(Resource.success(result.body()))
            Log.e("samset: ", result.body().toString())
        }

    }

}