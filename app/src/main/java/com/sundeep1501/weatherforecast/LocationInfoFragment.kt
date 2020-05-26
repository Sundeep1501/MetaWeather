package com.sundeep1501.weatherforecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.sundeep1501.weatherforecast.databinding.FragmentLocationInfoBinding
import com.sundeep1501.weatherforecast.viewmodels.LocationInfoViewModel
import com.sundeep1501.weatherforecast.viewmodels.LocationSharedViewModel
import kotlinx.android.synthetic.main.fragment_location_info.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class LocationInfoFragment : Fragment() {

    private val locationInfoViewModel: LocationInfoViewModel by activityViewModels()
    private val locationSharedViewModel: LocationSharedViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLocationInfoBinding.inflate(inflater, container, false)

        locationSharedViewModel.getLocations()
            .observe(viewLifecycleOwner, Observer { mwLocationID ->
                locationInfoViewModel.getLocationDetails(mwLocationID)
            })

        locationInfoViewModel.getLocationInfo()
            .observe(viewLifecycleOwner, Observer { mwLocationInfo ->
                binding.apply {
                    binding.mwLocationInfo = mwLocationInfo
                    binding.forecastSize = mwLocationInfo.consolidated_weather.size
                    executePendingBindings()
                }
            })

        locationInfoViewModel.getProgressBar().observe(viewLifecycleOwner, Observer {
            progress_bar.visibility = when {
                it -> {
                    View.VISIBLE
                }
                else -> {
                    View.INVISIBLE
                }
            }
        })

        binding.root.findViewById<TextView>(R.id.past).setOnClickListener {
            findNavController().navigate(R.id.action_locationInfoFragment_to_pastLocationInoFragment)
        }
        return binding.root
    }

}