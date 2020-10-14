package com.example.android.geopagosapplication.ui.paymentmethod.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.geopagosapplication.R
import com.example.android.geopagosapplication.databinding.PaymentMethodsFragmentBinding
import com.example.android.geopagosapplication.model.PaymentMethod
import com.example.android.geopagosapplication.ui.paymentmethod.viewmodel.PaymentMethodViewModel
import com.example.android.geopagosapplication.utils.Status
import org.koin.android.viewmodel.ext.android.viewModel

class PaymentMethodsFragment : Fragment(), PaymentMethodAdapter.OnPaymentMethodSelected {
    private val paymentMethodViewModel: PaymentMethodViewModel by viewModel()
    private lateinit var binding: PaymentMethodsFragmentBinding
    private lateinit var adapter: PaymentMethodAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.payment_methods_fragment,
            container,
            false
        )
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        setupRecyclerView()

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_paymentMethodFragment_to_amountFragment)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun setupObserver() {
        paymentMethodViewModel.paymentMethods.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { paymentMethods -> renderList(paymentMethods) }
                    binding.recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = PaymentMethodAdapter(mutableListOf(), this)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerView.context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
    }

    private fun renderList(paymentMethods: List<PaymentMethod>) {
        adapter.addPaymentMethods(paymentMethods)
        adapter.notifyDataSetChanged()
    }

    override fun onClick(paymentMethod: PaymentMethod) {
        paymentMethodViewModel.paymentMethodSelected(paymentMethod)
        findNavController().navigate(R.id.action_paymentMethodFragment_to_bankFragment)
    }

}