package com.samset.evsample.view.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.samset.evsample.R
import com.samset.evsample.core.model.Country
import com.samset.evsample.databinding.FragmentEvCountryBinding
import com.samset.evsample.utils.Constants.BOTTOM_SHEET_DIALOG_TAG
import com.samset.evsample.utils.Constants.CITY_DATA_RESULT
import com.samset.evsample.utils.Constants.DIALOG_TAG
import com.samset.evsample.utils.Constants.FROM
import com.samset.evsample.utils.Constants.FROM_KEY
import com.samset.evsample.utils.Constants.RESULT_KEY
import com.samset.evsample.utils.EvDialogFragment
import com.samset.evsample.utils.Status
import com.samset.evsample.utils.Utils.validation
import com.samset.evsample.utils.ViewType
import com.samset.evsample.view.ui.base.BaseFragment
import com.samset.evsample.view.vm.EvViewModel


class EvCountryFragment : BaseFragment() {
    private val viewModel: EvViewModel by viewModels()
    private lateinit var binding: FragmentEvCountryBinding
    private var countryList= ArrayList<Country>()
    private var cityList= ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       handleCallbackResult()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view=FragmentEvCountryBinding.inflate(inflater,container,false)
        binding=view
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
        requestApiCall()
        setupCountryData()
        Log.e("tag","this is sample")
    }

    private fun requestApiCall(){
        viewModel.getCountry()
    }

    private fun observeData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer{ response ->
            when(response.status){
                Status.LOADING  -> showProgress()
                Status.SUCCESS ->{
                    hideProgress()
                    //Log.e("tag"," Success response "+response.data)
                    if(response!=null && response.data!=null && response.data.dataList!=null){
                        var data=response.data.dataList
                        countryList.addAll(data)
                    }
                }
                Status.ERROR -> {
                   hideProgress()
                    Log.e("tag"," error msg "+response.message)
                }
            }
        } )
    }
    // setup country dialog for making searchable spinner
    private fun setupCountryData(){
        binding.Spincountry.setOnClickListener {
            val fragment=EvDialogFragment()
            val bundle=Bundle()
            bundle.putSerializable("data",countryList)
            bundle.putString(FROM,ViewType.COUNTRY.name)
            fragment.arguments=bundle
            fragment.show(childFragmentManager,DIALOG_TAG)
        }
    }

    // handle callback result that is incoming from searchable dialog and payment mode dialog
    fun handleCallbackResult(){
        childFragmentManager.setFragmentResultListener(DIALOG_TAG, this) { key, bundle ->
            val result = bundle.getString(RESULT_KEY)
            val resFrom = bundle.getString(FROM_KEY)

            binding.Spincity.isEnabled=validation(binding.Spincountry.text.toString(),getString(R.string.select_city))
            val color= if (validation(binding.Spincountry.text.toString(),getString(R.string.select_country))) Color.BLACK else Color.GRAY
            binding.Spincountry.setTextColor(color)
            if (resFrom!=null && resFrom.equals(ViewType.CITY.name,true)){
                val cityColor= if (!validation(binding.Spincity.text.toString(),getString(R.string.select_city))) Color.BLACK else Color.GRAY
                binding.Spincity.setTextColor(cityColor)
                binding.Spincity.text=result
                val dialog=BottomSheetFragment()
                dialog.show(childFragmentManager,BOTTOM_SHEET_DIALOG_TAG)
            }else{
                val cityResult = bundle.getStringArrayList(CITY_DATA_RESULT)
                binding.Spincountry.text=result
                binding.Spincity.text=resources.getString(R.string.select_city)
                cityList.clear()
                cityResult?.let { cityList.addAll(it) }
                binding.Spincity.setOnClickListener {
                    val fragment=EvDialogFragment()
                    val bundle=Bundle()
                    bundle.putSerializable(CITY_DATA_RESULT,cityResult)
                    bundle.putString(FROM,ViewType.CITY.name)
                    fragment.arguments=bundle
                    fragment.show(childFragmentManager, DIALOG_TAG)
                }
            }
        }
        childFragmentManager.setFragmentResultListener(BOTTOM_SHEET_DIALOG_TAG, this) { key, bundle ->
            binding.Spincountry.text=getString(R.string.select_country)
            binding.Spincity.text=getString(R.string.select_city)
        }
    }
}