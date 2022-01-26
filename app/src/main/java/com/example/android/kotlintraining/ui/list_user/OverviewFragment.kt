package com.example.android.kotlintraining.ui.list_user

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.android.kotlintraining.R
import com.example.android.kotlintraining.databinding.FragmentOverviewBinding
import com.example.android.kotlintraining.models.UserModel
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

        // Sets the adapter of the RecyclerView
        userAdapter = UserAdapter(OnClickListener {
            model, isOpenDetail ->
            if (isOpenDetail) {
                // Open Detail page
                viewModel.displayPropertyDetails(model)
            } else {
                // Open Url
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(model.url)))
            }
        })

        binding.listItem.apply {
            adapter = userAdapter
        }

        viewModel.navigateToSelectedProperty.observe(this, Observer {
            if ( it != null ) {
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

    private var userAdapter: UserAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.listUser.observe(viewLifecycleOwner, Observer {
            data -> data.apply { userAdapter?.data = data }
        })
    }
}
