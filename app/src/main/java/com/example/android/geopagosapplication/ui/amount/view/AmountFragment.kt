package com.example.android.geopagosapplication.ui.amount.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android.geopagosapplication.R
import com.example.android.geopagosapplication.databinding.FragmentAmountBinding
import com.example.android.geopagosapplication.ui.amount.viewmodel.AmountViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class AmountFragment : Fragment() {
    private val amountViewModel: AmountViewModel by viewModel()
    private lateinit var binding: FragmentAmountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_amount,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewModel = amountViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
    }

    private fun setupObserver() {
        amountViewModel.isAmountValid.observe(viewLifecycleOwner, {
            if (it) {
                hideError()
                amountViewModel.onAmountEntered()
                findNavController().navigate(R.id.action_amountFragment_to_paymentMethodFragment2)
            } else {
                showError()
            }
        })

        amountViewModel.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                hideError()
            }
        })
    }


    private fun hideError() {
        binding.error.visibility = View.INVISIBLE
    }

    private fun showError() {
        binding.error.visibility = View.VISIBLE
    }
}