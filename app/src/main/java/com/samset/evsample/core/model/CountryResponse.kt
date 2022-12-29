package com.samset.evsample.core.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/*
  Copyright (C) EVSample - All Rights Reserved Ⓒ
  Unauthorized copying of this file, via any medium is strictly prohibited
  Proprietary and confidential.
  See the License for the specific language governing permissions and limitations under the License.
 
  Created by Sanjay Singh on 28,December,2022 at 7:02 PM for EVSample.
  New Delhi,India
 */


data class CountryResponse(@SerializedName("error") var error:String, @SerializedName("msg") var msg:String, @SerializedName("data") var dataList:ArrayList<Country>) :
    Serializable