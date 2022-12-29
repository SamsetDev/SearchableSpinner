package com.samset.evsample.utils

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.samset.evsample.R
import com.samset.evsample.core.model.Country
import com.samset.evsample.databinding.DialogSearchableSpinnerBinding
import com.samset.evsample.databinding.FragmentEvCountryBinding
import com.samset.evsample.utils.Constants.CITY_DATA_RESULT
import com.samset.evsample.utils.Constants.DIALOG_TAG
import com.samset.evsample.utils.Constants.FROM
import com.samset.evsample.utils.Constants.FROM_KEY
import com.samset.evsample.utils.Constants.RESULT_KEY
import com.samset.evsample.view.ui.adapters.CityAdapter
import com.samset.evsample.view.ui.adapters.RecyAdapter

/*
  Copyright (C) EVSample - All Rights Reserved Ⓒ
  Unauthorized copying of this file, via any medium is strictly prohibited
  Proprietary and confidential.
  See the License for the specific language governing permissions and limitations under the License.
 
  Created by Sanjay Singh (a17iiuvf) on 29,December,2022 at 6:52 PM for EVSample.
  New Delhi,India
 */


class EvDialogFragment : DialogFragment() {
    private var countryList= ArrayList<Country>()
    private var cityList= ArrayList<String>()
    private var from :String =ViewType.COUNTRY.name
    private lateinit var binding :DialogSearchableSpinnerBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments!=null){
            from= arguments?.getString(FROM).toString()
            if (from.equals(ViewType.CITY.name)){
                cityList= arguments?.getStringArrayList(CITY_DATA_RESULT) as ArrayList<String>
            }else{
                countryList=arguments?.getSerializable("data") as ArrayList<Country> /* = java.util.ArrayList<com.samset.evsample.core.model.Country> */
            }
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.rounded_dialog);
        val view= DialogSearchableSpinnerBinding.inflate(inflater,container,false)
        binding=view
        return view.root
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog?.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupData()
    }

    private fun setupData() {
        var layout = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layout
        val divider = DividerItemDecoration(binding.recyclerView.context, layout.orientation)
        binding.recyclerView.addItemDecoration(divider)

         if (from.equals(ViewType.COUNTRY.name,true)){
             var myadapter = RecyAdapter(requireContext(), countryList)
             binding.recyclerView.adapter = myadapter
             myadapter.setListeners(object : OnItemClickListeners {
                 override fun onItemClick(str: String, id: Int) {
                     val index = countryList.indexOfFirst{ it.country == str }
                     val bundle=Bundle()
                     bundle.putString(RESULT_KEY,str)
                     bundle.putString(FROM_KEY,ViewType.COUNTRY.name)
                     bundle.putStringArrayList(CITY_DATA_RESULT,countryList.get(index).cities)
                     setFragmentResult(DIALOG_TAG,bundle)
                     dismiss()
                 }
             })
             binding.editText.addTextChangedListener(object : TextWatcher {
                 override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                 override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                 override fun afterTextChanged(s: Editable?) {
                     myadapter.filter.filter(s)
                 }
             })
        } else {
             var myadapter= CityAdapter(requireContext(), cityList)
             binding.recyclerView.adapter = myadapter
             myadapter.setListeners(object : OnItemClickListeners {
                 override fun onItemClick(str: String, id: Int) {
                     //Toast.makeText(requireContext(), "You click " + str, Toast.LENGTH_LONG).show()
                     val bundle=Bundle()
                     bundle.putString(RESULT_KEY,str)
                     bundle.putString(FROM_KEY,ViewType.CITY.name)
                     setFragmentResult(DIALOG_TAG,bundle)
                     dismiss()
                 }
             })
             binding.editText.addTextChangedListener(object : TextWatcher {
                 override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                 override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                 override fun afterTextChanged(s: Editable?) {
                     myadapter.filter.filter(s)
                 }
             })
         }


    }
}