package com.sundeep1501.weatherforecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.sundeep1501.weatherforecast.databinding.FragmentPastLocationInfoBinding
import com.sundeep1501.weatherforecast.viewmodels.LocationInfoViewModel
import com.sundeep1501.weatherforecast.viewmodels.PastLocationInfoViewModel
import java.text.SimpleDateFormat
import java.util.*


class PastLocationInfoFragment : Fragment() {

    private val locationInfoViewModel: LocationInfoViewModel by activityViewModels()
    private val pastLocationInfoViewModel: PastLocationInfoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPastLocationInfoBinding.inflate(inflater, container, false)

        pastLocationInfoViewModel.getPastLocationInfo()
            .observe(viewLifecycleOwner, Observer { mwWeatherList ->
                binding.apply {
                    binding.mwWeather = mwWeatherList[0]
                    executePendingBindings()
                }
            })

        locationInfoViewModel.getLocationInfo()
            .observe(viewLifecycleOwner, Observer { mwLocationInfo ->

                // get date and parse to previous date.

                val date =
                    SimpleDateFormat("yyyy-MM-dd").parse(mwLocationInfo.consolidated_weather[0].applicable_date)
                Calendar.getInstance()
                pastLocationInfoViewModel.getLocationDetails(
                    mwLocationInfo.woeid, SimpleDateFormat("yyyy").format(date).toInt(),
                    SimpleDateFormat("MM").format(date).toInt(),
                    SimpleDateFormat("dd").format(date).toInt() - 1
                )
                binding.apply {
                    mwLocationInfo.copy()
                    binding.title = mwLocationInfo.title
                    binding.parent = mwLocationInfo.parent.title
                    executePendingBindings()
                }

            })

        return binding.root
    }
}
