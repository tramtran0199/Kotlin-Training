package com.example.android.kotlintraining.ui.list_user

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.kotlintraining.databinding.FragmentOverviewBinding
import com.example.android.kotlintraining.view_model.list_user.ListUserViewModelFactory
import com.example.android.kotlintraining.view_model.list_user.OverviewViewModel

class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        val activity = requireNotNull(this.activity)
        binding.viewModel = ViewModelProvider(
            this, ListUserViewModelFactory(activity.application)
        ).get(OverviewViewModel::class.java)

        // Sets the adapter of the photosGrid RecyclerView
        binding.photosGrid.adapter = PhotoGridAdapter(PhotoGridAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })

        viewModel.navigateToSelectedProperty.observe(this, Observer {
            if ( null != it ) {
                // Must find the NavController from the Fragment
                this.findNavController().navigate(
                    OverviewFragmentDirections.actionShowDetail(it)
                )
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                viewModel.displayPropertyDetailsComplete()
            }
        })

        return binding.root
    }
}
