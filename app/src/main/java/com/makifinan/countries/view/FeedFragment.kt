package com.makifinan.countries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.makifinan.countries.R
import com.makifinan.countries.adapter.CountryRVAdapter
import com.makifinan.countries.databinding.FragmentFeedBinding
import com.makifinan.countries.viewmodel.FeedViewModel

class FeedFragment : Fragment(R.layout.fragment_feed) {
    private  val countryAdapter = CountryRVAdapter(arrayListOf())
    private  var viewmodel = FeedViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentFeedBinding.bind(view)
        viewmodel=ViewModelProvider(this).get(FeedViewModel::class.java)
        viewmodel.refershData()
        binding.countryList.layoutManager = LinearLayoutManager(context)
        binding.countryList.adapter=countryAdapter


        observeLiveData()


        super.onViewCreated(view, savedInstanceState)
    }
    val recyclerView = view?.findViewById<RecyclerView>(R.id.countryList)
    val errorMessage = view?.findViewById<TextView>(R.id.errorTextView)
    val loadingProgress = view?.findViewById<ProgressBar>(R.id.loadingProgressBar)
    fun observeLiveData(){

        viewmodel.countries.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {

                recyclerView?.visibility =  View.GONE
                errorMessage?.visibility = View.GONE
                loadingProgress?.visibility = View.GONE

                countryAdapter.updateRecyclerView(countries)
            }
        })

        viewmodel.countryError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it){
                    errorMessage?.visibility = View.VISIBLE
                    loadingProgress?.visibility = View.GONE
                    recyclerView?.visibility =  View.GONE
                }else{
                    errorMessage?.visibility = View.GONE
                }
            }
        })

        viewmodel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it){
                    loadingProgress?.visibility = View.VISIBLE
                    recyclerView?.visibility =  View.GONE
                    errorMessage?.visibility = View.GONE
                }else{
                    loadingProgress?.visibility = View.GONE
                }
            }
        })
    }


}