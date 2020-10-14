package com.example.android.geopagosapplication.ui.installment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.android.geopagosapplication.R
import com.example.android.geopagosapplication.databinding.FragmentInstallmentBinding
import com.example.android.geopagosapplication.ui.installment.viewmodel.InstallmentViewModel
import com.example.android.geopagosapplication.utils.Status
import org.koin.android.viewmodel.ext.android.viewModel

class InstallmentFragment : Fragment() {
    private val installmentViewModel: InstallmentViewModel by viewModel()
    private lateinit var binding: FragmentInstallmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_installment,
            container,
            false
        )
        binding.lifecycleOwner = this
        binding.viewmodel = installmentViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
    }

    private fun setupObserver() {
        installmentViewModel.installments.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    findNavController().navigate(R.id.action_installmentFragment_to_successFragment)
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}