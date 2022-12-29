package com.samset.evsample.view.ui.fragments


import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.samset.evsample.R
import com.samset.evsample.databinding.BottomSheetDialogBinding
import com.samset.evsample.utils.AlertBox
import com.samset.evsample.utils.Constants.BOTTOM_SHEET_DIALOG_TAG
import com.samset.evsample.utils.warningAlert


/*
  Copyright (C) EVSample - All Rights Reserved Ⓒ
  Unauthorized copying of this file, via any medium is strictly prohibited
  Proprietary and confidential.
  See the License for the specific language governing permissions and limitations under the License.
 
  Created by Sanjay Singh (a17iiuvf) on 29,December,2022 at 5:01 AM for EVSample.
  New Delhi,India
 */


public class BottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: BottomSheetDialogBinding
    private var payMode:String="none"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btncancel.setOnClickListener {
            dismiss()
        }
        binding.btnrecharge.setOnClickListener {
            if (validate()){
                binding.btnrecharge.warningAlert(requireContext(),getString(R.string.success_added_amt))
                dismiss()
                setFragmentResult(BOTTOM_SHEET_DIALOG_TAG,Bundle())
            }
        }

        setupRadio()
    }

    private fun setupRadio(){
        binding.radiogroup.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = group.findViewById(checkedId)
                var input=radio.text.toString().substring(1)
                 binding.etAmt.setText(input)
                //Toast.makeText(requireContext()," On checked change :"+ " ${radio.text}", Toast.LENGTH_SHORT).show()
            })
        binding.payradiogroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = group.findViewById(checkedId)
                payMode=radio.text.toString()
                //Toast.makeText(requireContext()," On checked change :"+ " ${radio.text}", Toast.LENGTH_SHORT).show()
            })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=BottomSheetDialogBinding.inflate(inflater,container,false)
        binding=view
        return binding.root
    }
    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        val contentView: View = View.inflate(context, R.layout.bottom_sheet_dialog, null)
        dialog.setContentView(contentView)
        (contentView.getParent() as View).setBackgroundColor(resources.getColor(android.R.color.transparent,null))
    }

    private fun validate() : Boolean{
        var amount=binding.etAmt.getText().toString()
        if (amount.isEmpty()){
            binding.etAmt.AlertBox(requireContext(),getString(R.string.please_enter_amt))
            return false
        }else if (!amount.isEmpty() && payMode.equals("none")){
            binding.etAmt.AlertBox(requireContext(),getString(R.string.please_select_payment_mode))
             return false
        }else{
            return true
        }

    }
}