package com.samset.evsample.core.model

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

/*
  Copyright (C) EVSample - All Rights Reserved Ⓒ
  Unauthorized copying of this file, via any medium is strictly prohibited
  Proprietary and confidential.
  See the License for the specific language governing permissions and limitations under the License.
 
  Created by Sanjay Singh on 28,December,2022 at 8:35 PM for EVSample.
  New Delhi,India
 */


data class Country(@SerializedName("iso2") var iso2:String, @SerializedName("iso3") var iso3:String, @SerializedName("country") var country:String, @SerializedName("cities") var cities:ArrayList<String>)