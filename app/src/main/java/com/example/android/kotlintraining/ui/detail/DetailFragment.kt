package com.example.android.kotlintraining.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android.kotlintraining.databinding.FragmentDetailBinding
import com.example.android.kotlintraining.view_model.user_detail.DetailViewModel
import com.example.android.kotlintraining.view_model.user_detail.DetailViewModelFactory

class DetailFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        
        val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val viewModelFactory = DetailViewModelFactory(
            DetailFragmentArgs.fromBundle(arguments!!).selectedProperty.login,
            application)
        binding.viewModel = ViewModelProvider(
                this, viewModelFactory).get(DetailViewModel::class.java)

        return binding.root
    }
}