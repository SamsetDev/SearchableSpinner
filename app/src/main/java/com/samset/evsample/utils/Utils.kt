package com.samset.evsample.utils

import android.content.Context
import android.content.DialogInterface
import android.view.View
import androidx.appcompat.app.AlertDialog

/*
  Copyright (C) EVSample - All Rights Reserved Ⓒ
  Unauthorized copying of this file, via any medium is strictly prohibited
  Proprietary and confidential.
  See the License for the specific language governing permissions and limitations under the License.
 
  Created by Sanjay Singh on 29,December,2022 at 9:09 PM for EVSample.
  New Delhi,India
 */


object Utils {

 public fun  validation(str:String,validate:String): Boolean{
   return if (str.equals(validate,true)) false else true
 }
}