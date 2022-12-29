package com.samset.evsample.view.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.samset.evsample.R
import com.samset.evsample.utils.OnItemClickListeners

/*
  Copyright (C) EVSample - All Rights Reserved Ⓒ
  Unauthorized copying of this file, via any medium is strictly prohibited
  Proprietary and confidential.
  See the License for the specific language governing permissions and limitations under the License.
 
  Created by Sanjay Singh (a17iiuvf) on 29,December,2022 at 7:50 PM for EVSample.
  New Delhi,India
 */


public class CityAdapter(val ctx: Context, var countryList:ArrayList<String>) : RecyclerView.Adapter<CityAdapter.MyViewHolder>(),
 Filterable {
 private lateinit var listeners: OnItemClickListeners
 var countryFilterList = ArrayList<String>()

 init {
  countryFilterList = countryList
 }

 public fun setListeners(mylisteners: OnItemClickListeners){
  this.listeners=mylisteners
 }

 override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
  holder.bind(countryFilterList.get(position),listeners)
 }

 override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
  val layoutInflater = LayoutInflater.from(parent.context)
  return MyViewHolder(layoutInflater.inflate(R.layout.recycler_itemviews, parent, false))
 }

 override fun getItemCount(): Int {
  return countryFilterList.size
 }

 inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  var tvtext=itemView.findViewById<TextView>(R.id.text)
  fun bind(model: String, listeners: OnItemClickListeners): Unit {
   tvtext.text= model
   tvtext.setOnClickListener {
    if (listeners!=null){
     listeners.onItemClick(model,0)
    }
   }
  }
 }

 override fun getFilter(): Filter {
  return object : Filter() {
   override fun performFiltering(constraint: CharSequence?): FilterResults {
    val charSearch = constraint.toString()
    if (charSearch.isEmpty()) {
     countryFilterList = countryList
    } else {
     val resultList = ArrayList<String>()
     for (row in countryList) {
      if (row.toLowerCase().contains(constraint.toString().toLowerCase())) {
       resultList.add(row)
      }
     }
     countryFilterList = resultList
    }
    val filterResults = FilterResults()
    filterResults.values = countryFilterList
    return filterResults
   }

   override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
    countryFilterList = results?.values as ArrayList<String>
    notifyDataSetChanged()
   }
  }
 }
}