package com.sundeep1501.weatherforecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.sundeep1501.weatherforecast.databinding.FragmentSecondBinding
import com.sundeep1501.weatherforecast.viewmodels.LocationInfoViewModel
import com.sundeep1501.weatherforecast.viewmodels.LocationSharedViewModel

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
        val binding = FragmentSecondBinding.inflate(inflater, container, false)

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            // findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }
    }
}
