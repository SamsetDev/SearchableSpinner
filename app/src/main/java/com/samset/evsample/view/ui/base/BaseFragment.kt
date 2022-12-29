package com.samset.evsample.view.ui.base

import android.app.Dialog
import android.app.ProgressDialog
import android.app.ProgressDialog.show
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import com.samset.evsample.R

/*
  Copyright (C) EVSample - All Rights Reserved Ⓒ
  Unauthorized copying of this file, via any medium is strictly prohibited
  Proprietary and confidential.
  See the License for the specific language governing permissions and limitations under the License.
 
  Created by Sanjay Singh (a17iiuvf) on 28,December,2022 at 7:25 PM for EVSample.
  New Delhi,India
 */


abstract class BaseFragment : Fragment() {
    private lateinit var progressBar: Dialog

    public fun showProgress(){
        progressBar= ProgressDialog.progressDialog(requireContext())
        if (progressBar.isShowing) progressBar.dismiss()
        if (isAdded && !progressBar.isShowing){
            progressBar.show()
        }

    }
    public fun hideProgress(){
        if (activity!=null && progressBar.isShowing){
            progressBar?.dismiss()
        }
    }

    class ProgressDialog {
        companion object {
            fun progressDialog(context: Context): Dialog {
                val dialog = Dialog(context)
                val inflate = LayoutInflater.from(context).inflate(R.layout.progress_dialog, null)
                dialog.setContentView(inflate)
                dialog.setCancelable(false)
                dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                return dialog
            }
        }
    }
}