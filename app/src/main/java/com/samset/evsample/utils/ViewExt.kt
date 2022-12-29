package com.samset.evsample.utils

import android.content.Context
import android.content.DialogInterface
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.samset.evsample.R


/*
  Copyright (C) EVSample - All Rights Reserved Ⓒ
  Unauthorized copying of this file, via any medium is strictly prohibited
  Proprietary and confidential.
  See the License for the specific language governing permissions and limitations under the License.
 
  Created by Sanjay Singh (a17iiuvf) on 30,December,2022 at 1:11 AM for EVSample.
  New Delhi,India
 */


fun View.warningAlert(ctx: Context,msg:String) {
    val builder = AlertDialog.Builder(ctx)
    with(builder)
    {
        //setTitle(ctx.getString(R.string.app_name))
        setMessage(msg)
        setPositiveButton(ctx.getString(R.string.ok), object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface, p1: Int) {
                dialog.dismiss()
            }
        })
        show()
    }

}
    fun View.AlertBox(ctx: Context,msg:String){
        val alertDialog = AlertDialog.Builder(ctx) //set icon
            .setIcon(android.R.drawable.ic_dialog_alert) //set title
            .setTitle(ctx.getString(R.string.app_name)) //set message
            .setMessage(msg) //set positive button
            .setPositiveButton(ctx.getString(R.string.ok)) { dialogInterface, i -> //set what would happen when positive button is clicked
               dialogInterface.dismiss()
            }.show()
    }
