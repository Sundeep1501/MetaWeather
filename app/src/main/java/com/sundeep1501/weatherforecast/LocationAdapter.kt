package com.sundeep1501.weatherforecast

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sundeep1501.weatherforecast.backend.data.MWLocation
import com.sundeep1501.weatherforecast.databinding.ViewLocationBinding

class LocationAdapter : ListAdapter<MWLocation, RecyclerView.ViewHolder>(LocationDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LocationViewHolder(ViewLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as LocationViewHolder).bind(getItem(position))
    }

    class LocationViewHolder(private val binding:ViewLocationBinding):RecyclerView.ViewHolder(binding.root){
        init {
            binding.setClickListener {view ->
                binding.mwLocation?.let{ mwLocation ->
                    navigateToLocation(view, mwLocation)
                }
            }
        }

        private fun navigateToLocation(view: View, mwLocation: MWLocation) {
            val action = SearchLocationFragmentDirections.actionFirstFragmentToSecondFragment(mwLocation.woeid)
            view.findNavController().navigate(action)
        }

        fun bind(item: MWLocation) {
            binding.apply {
                mwLocation = item
                executePendingBindings()
            }
        }
    }

    private class LocationDiffCallback : DiffUtil.ItemCallback<MWLocation>(){
        override fun areItemsTheSame(oldItem: MWLocation, newItem: MWLocation): Boolean {
            return oldItem.woeid == newItem.woeid
        }

        override fun areContentsTheSame(oldItem: MWLocation, newItem: MWLocation): Boolean {
            return oldItem == newItem
        }
    }
}