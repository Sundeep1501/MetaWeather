package com.sundeep1501.weatherforecast

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.sundeep1501.weatherforecast.adapter.LocationAdapter
import com.sundeep1501.weatherforecast.databinding.FragmentSearchLocationBinding
import com.sundeep1501.weatherforecast.viewmodels.LocationSearchViewModel
import com.sundeep1501.weatherforecast.viewmodels.LocationSharedViewModel
import kotlinx.android.synthetic.main.fragment_search_location.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SearchLocationFragment : Fragment() {

    private val locationSearchViewModel: LocationSearchViewModel by activityViewModels()
    private val locationSharedViewModel: LocationSharedViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentSearchLocationBinding.inflate(inflater, container, false)

        // setup the adapter for search results
        val adapter =
            LocationAdapter(object :
                LocationAdapter.InteractionListener {
                override fun onItemSelected(mwLocationID: Int) {
                    locationSharedViewModel.selectedLocation(mwLocationID)
                    val action =
                        SearchLocationFragmentDirections.actionFirstFragmentToSecondFragment()
                    findNavController().navigate(action)
                }
            });

        binding.recentSearchList.adapter = adapter
        locationSearchViewModel.getLocations().observe(viewLifecycleOwner, Observer { list->
            adapter.submitList(list)
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

        search_box.doAfterTextChanged { s: Editable? ->
            locationSearchViewModel.search(s.toString())
        }
    }
}
