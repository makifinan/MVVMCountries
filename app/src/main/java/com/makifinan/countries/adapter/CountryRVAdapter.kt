package com.makifinan.countries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.makifinan.countries.R
import com.makifinan.countries.model.Country
import com.makifinan.countries.util.extensions.downloadFromUrl
import com.makifinan.countries.util.extensions.placeholderProgressBar
import com.makifinan.countries.view.FeedFragmentDirections

class CountryRVAdapter(val countryList : ArrayList<Country>):RecyclerView.Adapter<CountryRVAdapter.CountryViewHolder> (){


    class CountryViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        var rvCountryNameTextView : TextView
        var rvRegionTextView : TextView
        var rvimageView : ImageView
        init {
            rvCountryNameTextView=itemView.findViewById(R.id.rvCountryNameTextView)
            rvRegionTextView=itemView.findViewById(R.id.rvRegionTextView)
            rvimageView=itemView.findViewById(R.id.rvimageView)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.country_row,parent,false)
        return CountryViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.rvCountryNameTextView.text= countryList[position].countryName
        holder.rvRegionTextView.text= countryList[position].countryRegion
        holder.itemView.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment()
            Navigation.findNavController(it).navigate(action)
        }
        holder.rvimageView.downloadFromUrl(countryList[position].imageUrl, placeholderProgressBar(holder.itemView.context))
        
    }

    fun updateRecyclerView(newList : List<Country>){
        countryList.clear()
        countryList.addAll(newList)
        notifyDataSetChanged()
    }
}