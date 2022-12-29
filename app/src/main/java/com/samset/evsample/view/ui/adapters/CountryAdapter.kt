package com.samset.evsample.view.ui.adapters

import android.content.Context
import android.icu.util.UniversalTimeScale.toLong
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import com.samset.evsample.R
import com.samset.evsample.core.model.Country


/*
  Copyright (C) EVSample - All Rights Reserved Ⓒ
  Unauthorized copying of this file, via any medium is strictly prohibited
  Proprietary and confidential.
  See the License for the specific language governing permissions and limitations under the License.
 
  Created by Sanjay Singh (a17iiuvf) on 28,December,2022 at 8:40 PM for EVSample.
  New Delhi,India
 */


class CountryAdapter(context: Context, resource: Int, var items: List<Country>)  : ArrayAdapter<Country>(context, resource, items) {
     private lateinit var dataSource:ArrayList<Country>
     val inflater: LayoutInflater = LayoutInflater.from(context)

    public fun addData(data: ArrayList<Country>){
        this.dataSource=data
        notifyDataSetChanged()
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view: View? = convertView
        if (view == null) {
            view = inflater.inflate(R.layout.spin_country_items, parent, false)
        }
        Log.e("tag"," items "+items.get(position)?.country)
        (view?.findViewById(android.R.id.text1) as TextView).text = items.get(position)?.country
        return view
    }

    override fun getDropDownView(position: Int, convertView: View, parent: ViewGroup): View {
        var view: View? = convertView
        if (view == null) {
            view = inflater.inflate(R.layout.spin_country_items, parent, false)
        }
        Log.e("tag"," items 3 "+items.get(position)?.country)
        (view?.findViewById(android.R.id.text1) as TextView).text = items.get(position)?.country
        return view
    }
}