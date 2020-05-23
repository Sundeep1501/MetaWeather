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
import com.sundeep1501.weatherforecast.databinding.FragmentFirstBinding
import com.sundeep1501.weatherforecast.viewmodels.LocationViewModel
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SearchLocationFragment : Fragment() {

    private val model: LocationViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentFirstBinding.inflate(inflater, container, false)
        context?: return binding.root

        // setup the adapter for search results
        val adapter = LocationAdapter()
        binding.recentSearchList.adapter = adapter
        model.getLocations().observe(viewLifecycleOwner, Observer { list->
            adapter.submitList(list)
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

        search_box.doAfterTextChanged { s: Editable? ->
            model.search(s.toString())
        }
    }
}
