package com.samset.evsample.view.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samset.evsample.core.EvRepository
import com.samset.evsample.core.model.CountryResponse
import com.samset.evsample.utils.Resource

/*
  Copyright (C) EVSample - All Rights Reserved Ⓒ
  Unauthorized copying of this file, via any medium is strictly prohibited
  Proprietary and confidential.
  See the License for the specific language governing permissions and limitations under the License.
 
  Created by Sanjay Singh (a17iiuvf) on 28,December,2022 at 6:40 PM for EVSample.
  New Delhi,India
 */


class EvViewModel : ViewModel() {

    public var countryLiveData= MutableLiveData<Resource<CountryResponse>>()

    private var repository: EvRepository
    init {
        repository= EvRepository()
    }

    fun getCountry(){
        repository.getContry(countryLiveData)
    }



}